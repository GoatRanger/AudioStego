/* $RCSfile$
 * $Author: egonw $
 * $Date: 2007-01-04 18:46:10 +0100 (Thu, 04 Jan 2007) $
 * $Revision: 7636 $
 * 
 * Copyright (C) 2003-2007  The Chemistry Development Kit (CDK) project
 * 
 * Contact: cdk-devel@lists.sourceforge.net
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA. 
 * 
 */
package org.openscience.cdk.validate;

import org.openscience.cdk.Atom;
import org.openscience.cdk.AtomContainer;
import org.openscience.cdk.Bond;
import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IElement;
import org.openscience.cdk.interfaces.IIsotope;
import org.openscience.cdk.interfaces.IMoleculeSet;
import org.openscience.cdk.Molecule;
import org.openscience.cdk.PseudoAtom;
import org.openscience.cdk.Reaction;
import org.openscience.cdk.config.AtomTypeFactory;
import org.openscience.cdk.config.IsotopeFactory;
import org.openscience.cdk.tools.LoggingTool;

/**
 * Validator which tests a number of basic chemical semantics.
 *
 * @author   Egon Willighagen
 * @cdk.created  2003-08-22
 */ 
public class BasicValidator extends AbstractValidator {

    private static LoggingTool logger;
    
    static {
        logger = new LoggingTool(BasicValidator.class);
    }
    
    public BasicValidator() {
    }
    
    public ValidationReport validateAtom(Atom subject) {
        ValidationReport report = new ValidationReport();
        report.addReport(validateCharge(subject));
        report.addReport(validateHydrogenCount(subject));
        report.addReport(validatePseudoAtom(subject));
        return report;
    }
    public ValidationReport validateBond(Bond subject) {
        ValidationReport report = new ValidationReport();
        report.addReport(validateStereoChemistry(subject));
        report.addReport(validateMaxBondOrder(subject));
        return report;
    }
    public ValidationReport validateIsotope(IIsotope subject) {
        return validateIsotopeExistence(subject);
    }
    public ValidationReport validateMolecule(Molecule subject) {
        ValidationReport report = new ValidationReport();
        ValidationTest emptyMolecule = new ValidationTest(subject,
            "Molecule does not contain any atom"
        );
        
        if (subject.getAtomCount() == 0) {
            report.addError(emptyMolecule);
        } else {
            report.addOK(emptyMolecule);
            ValidationTest massCalcProblem = new ValidationTest(subject,
                "Molecule contains PseudoAtom's. Won't be able to calculate some properties, like molecular mass."
            );
            boolean foundMassCalcProblem = false;
            for (int i=0; i<subject.getAtomCount(); i++) {
                if (subject.getAtom(i) instanceof PseudoAtom) {
                    foundMassCalcProblem = true;
                } else {
                    report.addReport(validateBondOrderSum(subject.getAtom(i), subject));
                }
            }
            if (foundMassCalcProblem) {
                report.addWarning(massCalcProblem);
            } else {
                report.addOK(massCalcProblem);
            }
        }
        return report;
    }
    public ValidationReport validateReaction(Reaction subject) {
        ValidationReport report = new ValidationReport();
        AtomContainer container1 = new org.openscience.cdk.AtomContainer();
        IMoleculeSet reactants = subject.getReactants();
        for (int i=0; i<reactants.getAtomContainerCount(); i++) {
            container1.add(reactants.getMolecule(i));
        }
        AtomContainer container2 = new org.openscience.cdk.AtomContainer();
        IMoleculeSet products = subject.getProducts();
        for (int i=0; i<products.getAtomContainerCount(); i++) {
            container2.add(products.getMolecule(i));
        }
        report.addReport(validateAtomCountConservation(subject, container1, container2));
        report.addReport(validateChargeConservation(subject, container1, container2));
        return report;
    }
    
    // the Atom tests
    
    private ValidationReport validateCharge(Atom atom) {
        ValidationReport report = new ValidationReport();
        ValidationTest tooCharged = new ValidationTest(atom, "Atom has an unlikely large positive or negative charge");
        if (atom.getSymbol().equals("O") || atom.getSymbol().equals("N") ||
            atom.getSymbol().equals("C") || atom.getSymbol().equals("H")) {
            if (atom.getFormalCharge() == 0) {
                report.addOK(tooCharged);
            } else {
                tooCharged.setDetails("Atom " + atom.getSymbol() + " has charge " + atom.getFormalCharge() );
                if (atom.getFormalCharge() < -3) {
                    report.addError(tooCharged);
                } else if (atom.getFormalCharge() < -1) {
                    report.addWarning(tooCharged);
                } else if (atom.getFormalCharge() > 3) {
                    report.addError(tooCharged);
                } else if (atom.getFormalCharge() > 1) {
                    report.addWarning(tooCharged);
                }
            }
        } else {
            if (atom.getFormalCharge() == 0) {
                report.addOK(tooCharged);
            } else {
                tooCharged.setDetails("Atom " + atom.getSymbol() + " has charge " + atom.getFormalCharge() );
                if (atom.getFormalCharge() < -4) {
                    report.addError(tooCharged);
                } else if (atom.getFormalCharge() < -3) {
                    report.addWarning(tooCharged);
                } else if (atom.getFormalCharge() > 4) {
                    report.addError(tooCharged);
                } else if (atom.getFormalCharge() > 3) {
                    report.addWarning(tooCharged);
                }
            }
        }
        return report;
    }

    private ValidationReport validateHydrogenCount(Atom atom) {
        ValidationReport report = new ValidationReport();
        ValidationTest negativeHydrogenCount = new ValidationTest(atom,
            "An Atom cannot have a negative number of hydrogens attached."
        );
        if (atom.getHydrogenCount() < 0 ) {
            negativeHydrogenCount.setDetails(
                "Atom has " + atom.getHydrogenCount() + " hydrogens."
            );
            report.addError(negativeHydrogenCount);
        } else {
            report.addOK(negativeHydrogenCount);
        }
        return report;
    }

    private ValidationReport validatePseudoAtom(Atom atom) {
        ValidationReport report = new ValidationReport();
        ValidationTest isElementOrPseudo = new ValidationTest(atom,
            "Non-element atom must be of class PseudoAtom."
        );
        if (atom instanceof PseudoAtom) {
            // that's fine
            report.addOK(isElementOrPseudo);
        } else {
            // check wether atom is really an element
            try {
                IsotopeFactory isotopeFactory = IsotopeFactory.getInstance(atom.getBuilder());
                IElement element = isotopeFactory.getElement(atom.getSymbol());
                if (element == null) {
                    isElementOrPseudo.setDetails(
                        "Element " + atom.getSymbol() + " does not exist."
                    );
                    report.addError(isElementOrPseudo);
                } else {
                    report.addOK(isElementOrPseudo);
                }
            } catch (Exception exception) {
                // well... don't throw an error then.
                isElementOrPseudo.setDetails(exception.toString());
                report.addCDKError(isElementOrPseudo);
            }
        }
        return report;
    }
    
    // the Bond tests
    
    private ValidationReport validateStereoChemistry(Bond bond) {
        ValidationReport report = new ValidationReport();
        ValidationTest bondStereo = new ValidationTest(bond,
            "Defining stereochemistry on bonds is not safe.",
            "Use atom based stereochemistry."
        );
        if (bond.getStereo() != CDKConstants.STEREO_BOND_NONE) {
            report.addWarning(bondStereo);
        } else {
            report.addOK(bondStereo);
        }
        return report;
    }
    
    private ValidationReport validateMaxBondOrder(Bond bond) {
        ValidationReport report = new ValidationReport();
        ValidationTest maxBO = new ValidationTest(bond,
            "Bond order exceeds the maximum for one of its atoms."
        );
        try {
            AtomTypeFactory structgenATF = AtomTypeFactory.getInstance(
                "org/openscience/cdk/config/data/structgen_atomtypes.xml", 
                bond.getBuilder()
            );
            for (int i=0; i<bond.getAtomCount(); i++) {
                org.openscience.cdk.interfaces.IAtom atom = bond.getAtom(i);
                if (atom instanceof PseudoAtom) {
                    // ok, all is fine; we don't know the properties of pseudo atoms
                    break;
                }
                org.openscience.cdk.interfaces.IAtomType[] atomTypes = structgenATF.getAtomTypes(atom.getSymbol());
                org.openscience.cdk.interfaces.IAtomType failedOn = null;
                boolean foundMatchingAtomType = false;
                for (int j=0; j<atomTypes.length; j++) {
                    if (bond.getOrder() <= atomTypes[j].getMaxBondOrder()) {
                        foundMatchingAtomType = true;
                    } else {
                        failedOn = atomTypes[j];
                    }
                }
                if (foundMatchingAtomType) {
                    report.addOK(maxBO);
                } else {
                    if (failedOn != null) {
                        maxBO.setDetails(
                            "Bond order exceeds the one allowed for atom " +
                            atom.getSymbol() + " for which the maximum bond order is " +
                            failedOn.getMaxBondOrder()
                        );
                    }
                    report.addError(maxBO);
                }
            }
        } catch (Exception exception) {
            logger.error("Error while performing atom bos validation");
            logger.debug(exception);
            maxBO.setDetails("Error while performing atom bos validation: " +
              exception.toString());
            report.addCDKError(maxBO);
        }
        return report;
    }
    
    // the Isotope tests
    
    public ValidationReport validateIsotopeExistence(IIsotope isotope) {
        ValidationReport report = new ValidationReport();
        ValidationTest isotopeExists = new ValidationTest(isotope,
            "Isotope with this mass number is not known for this element."
        );
        try {
            IsotopeFactory isotopeFac = IsotopeFactory.getInstance(isotope.getBuilder());
            IIsotope[] isotopes = isotopeFac.getIsotopes(isotope.getSymbol());
            if (isotope.getMassNumber() != 0) {
                boolean foundKnownIsotope = false;
                for (int i=0; i<isotopes.length; i++) {
                    if (isotopes[i].getMassNumber() == isotope.getMassNumber()) {
                        foundKnownIsotope = true;
                    }
                }
                if (!foundKnownIsotope) {
                    report.addError(isotopeExists);
                } else {
                    report.addOK(isotopeExists);
                }
            } else {
                // isotopic number is not set
                report.addOK(isotopeExists);
            }
        } catch (Exception exception) {
            // too bad...
        }
        return report;
    }
    
    // the Molecule tests

    private ValidationReport validateBondOrderSum(org.openscience.cdk.interfaces.IAtom atom, Molecule molecule) {
        ValidationReport report = new ValidationReport();
        ValidationTest checkBondSum = new ValidationTest(atom,
            "The atom's total bond order is too high."
        );
        try {
            AtomTypeFactory structgenATF = AtomTypeFactory.getInstance(
                "org/openscience/cdk/config/data/valency_atomtypes.xml", 
                atom.getBuilder()
            );
            int bos = (int)molecule.getBondOrderSum(atom);
            org.openscience.cdk.interfaces.IAtomType[] atomTypes = structgenATF.getAtomTypes(atom.getSymbol());
            if (atomTypes.length == 0) {
                checkBondSum.setDetails(
                    "Cannot validate bond order sum for atom not in valency atom type list: " +
                    atom.getSymbol()
                );
                report.addWarning(checkBondSum);
            } else {
                org.openscience.cdk.interfaces.IAtomType failedOn = null;
                boolean foundMatchingAtomType = false;
                for (int j=0; j<atomTypes.length; j++) {
                    org.openscience.cdk.interfaces.IAtomType type = atomTypes[j];
                    if (atom.getFormalCharge() == type.getFormalCharge()) {
                        foundMatchingAtomType = true;
                        if (bos == type.getBondOrderSum()) {
                            // skip this atom type
                        } else {
                            failedOn = atomTypes[j];
                        }
                    }
                }
                if (foundMatchingAtomType) {
                    report.addOK(checkBondSum);
                } else {
                    if (failedOn != null) {
                        checkBondSum.setDetails(
                            "Bond order exceeds the one allowed for atom " +
                            atom.getSymbol() + " for which the total bond order is " +
                            failedOn.getBondOrderSum()
                        );
                    }
                    report.addError(checkBondSum);
                }
            }
        } catch (Exception exception) {
            logger.error("Error while performing atom bos validation: ", exception.getMessage());
            logger.debug(exception);
        }
        return report;
    }

    private ValidationReport validateAtomCountConservation(Reaction reaction,
                                                        AtomContainer reactants,
                                                        AtomContainer products) {
        ValidationReport report = new ValidationReport();
        ValidationTest atomCount = new ValidationTest(reaction,
            "Atom count mismatch for reaction: the product side has a different atom count than the reactant side."
        );
        if (reactants.getAtomCount() != products.getAtomCount()) {
            report.addError(atomCount);
        } else {
            report.addOK(atomCount);
        }
        return report;
    }

    private ValidationReport validateChargeConservation(Reaction reaction,
                                                     AtomContainer reactants,
                                                     AtomContainer products) {
        ValidationReport report = new ValidationReport();
        ValidationTest chargeConservation = new ValidationTest(reaction,
            "Total formal charge is not preserved during the reaction"
        );
        java.util.Iterator atoms1 = reactants.atoms();
        int totalCharge1 = 0;
        while (atoms1.hasNext()) {
            totalCharge1 =+ ((IAtom)atoms1.next()).getFormalCharge();
        }
        java.util.Iterator atoms2 = products.atoms();
        int totalCharge2 = 0;
        while (atoms2.hasNext()) {
            totalCharge2 =+ ((IAtom)atoms2.next()).getFormalCharge();
        }
        if (totalCharge1 != totalCharge2) {
            report.addError(chargeConservation);
        } else {
            report.addOK(chargeConservation);
        }
        return report;
    }
    
}
