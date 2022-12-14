/* $
 *  
 *  $RCSfile: $
 *  $Author: sushil_ronghe $
 *  $Date: 2007-04-24 16:43:57 +0200 (Tue, 24 Apr 2007) $
 *  $Revision: 8262 $
 *
 *  Copyright (C) 1997-2007   Christoph Steinbeck <steinbeck@users.sf.net>
 *
 *  Contact: cdk-devel@lists.sourceforge.net
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public License
 *  as published by the Free Software Foundation; either version 2.1
 *  of the License, or (at your option) any later version.
 *  All I ask is that proper credit is given for my work, which includes
 *  - but is not limited to - adding the above copyright notice to the beginning
 *  of your source code files, and to any copyright notice that you may distribute
 *  with programs based on this work.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 */
package org.openscience.cdk;

/**
 * An interface providing predefined values for a number of
 * constants used throughout the CDK. Classes using these constants should
 * <b>not</b> implement this interface, but use it like:
 * <pre>
 *   double singleBondOrder = CDKConstants.BONDORDER_SINGLE;
 * </pre>
 *
 * <p>The lazyCreation patch has been applied to this class.
 *
 * @cdk.module  core
 *
 * @cdk.keyword bond order
 * @cdk.keyword stereochemistry
 */
public class CDKConstants {
	
	public final static int UNSET = Integer.MIN_VALUE;	

	/** A bond of degree 1.0. */
	public final static double BONDORDER_SINGLE = 1.0;

	/** A bond of degree 1.5. To indicate aromaticity, the flag ISAROMATIC should be used instead.
	 *  This constant is used for specific file formats only, and should generally not be used. */
    public final static double BONDORDER_AROMATIC = 1.5;

    /** A bond of degree 2.0. */
    public final static double BONDORDER_DOUBLE = 2.0;

    /** A bond of degree 3.0. */
    public final static double BONDORDER_TRIPLE = 3.0;

    /** A bonds which end is above the drawing plane. */
    public final static int STEREO_BOND_UP = 1;
    /** A bonds which start is above the drawing plane. */
    public final static int STEREO_BOND_UP_INV = 2;

    /** A bonds for which the stereochemistry is undefined. */
    public final static int STEREO_BOND_UNDEFINED = 4;

    /** A bonds for which there is no stereochemistry. */
    public final static int STEREO_BOND_NONE = 0;

    /** A bonds which end is below the drawing plane.
     *  The bond is draw from the first to the second bond atom.
     */
    public final static int STEREO_BOND_DOWN = -1;
    /** A bonds which end is below the drawing plane.
     *  The bond is draw from the second to the first bond atom.
     */
    public final static int STEREO_BOND_DOWN_INV = -2;

    /** A positive atom parity. */
    public final static int STEREO_ATOM_PARITY_PLUS = 1;
    /** A negative atom parity. */
    public final static int STEREO_ATOM_PARITY_MINUS = -1;
    /** A undefined atom parity. */
    public final static int STEREO_ATOM_PARITY_UNDEFINED = 0;

    /** A undefined hybridization. */
    public final static int HYBRIDIZATION_UNSET = 0;
    /** A geometry of neighboring atoms when an s orbital is hybridized
     *  with one p orbital. */
    public final static int HYBRIDIZATION_SP1 = 1;
    /** A geometry of neighboring atoms when an s orbital is hybridized
     *  with two p orbitals. */
    public final static int HYBRIDIZATION_SP2 = 2;
    /** A geometry of neighboring atoms when an s orbital is hybridized
     *  with three p orbitals. */
    public final static int HYBRIDIZATION_SP3 = 3;
    /** A geometry of neighboring atoms when an s orbital is hybridized
     *  with three p orbitals with one d orbital. */
    public final static int HYBRIDIZATION_SP3D1 = 4;
    /** A geometry of neighboring atoms when an s orbital is hybridized
     *  with three p orbitals with two d orbitals. */
    public final static int HYBRIDIZATION_SP3D2 = 5;
    /** A geometry of neighboring atoms when an s orbital is hybridized
     *  with three p orbitals with three d orbitals. */
    public final static int HYBRIDIZATION_SP3D3 = 6;
    /** A geometry of neighboring atoms when an s orbital is hybridized
     *  with three p orbitals with four d orbitals. */
    public final static int HYBRIDIZATION_SP3D4 = 7;
    /** A geometry of neighboring atoms when an s orbital is hybridized
     *  with three p orbitals with five d orbitals. */
    public final static int HYBRIDIZATION_SP3D5 = 8;
    /** 
     * Carbon NMR shift contant for use as a key in the
     * IChemObject.physicalProperties hashtable.
     * @see org.openscience.cdk.ChemObject
     */
    public final static String NMRSHIFT_CARBON = "carbon nmr shift";
    /** Hydrogen NMR shift contant for use as a key in the
     * IChemObject.physicalProperties hashtable.
     * @see org.openscience.cdk.ChemObject
     */
    public final static String NMRSHIFT_HYDROGEN = "hydrogen nmr shift";
    /** Nitrogen NMR shift contant for use as a key in the
     * IChemObject.physicalProperties hashtable.
     * @see org.openscience.cdk.ChemObject
     */
    public final static String NMRSHIFT_NITROGEN = "nitrogen nmr shift";

    /** Phosphorus NMR shift contant for use as a key in the
     * IChemObject.physicalProperties hashtable.
     * @see org.openscience.cdk.ChemObject
     */
    public final static String NMRSHIFT_PHOSPORUS = "phosphorus nmr shift";

    /** Fluorine NMR shift contant for use as a key in the
     * IChemObject.physicalProperties hashtable.
     * @see org.openscience.cdk.ChemObject
     */
    public final static String NMRSHIFT_FLUORINE = "fluorine nmr shift";

    /** Deuterium NMR shift contant for use as a key in the
     * IChemObject.physicalProperties hashtable.
     * @see org.openscience.cdk.ChemObject
     */
    public final static String NMRSHIFT_DEUTERIUM = "deuterium nmr shift";


    /****************************************
     * Some predefined flags - keep the     *
     * numbers below 50 free for other      *
     * purposes                             *
     ****************************************/

    /** Flag that is set if the chemobject is placed (somewhere).
     */
    public final static int ISPLACED = 0;
    /** Flag that is set when the chemobject is part of a ring.
     */
    public final static int ISINRING = 1;
    /** Flag that is set when the chemobject is part of a ring.
     */
    public final static int ISNOTINRING = 2;
    /** Flag that is set if a chemobject is part of an alipahtic chain.
     */
    public final static int ISALIPHATIC = 3;
    /** Flag is set if chemobject has been visited.
     */
    public final static int VISITED = 4; // Use in tree searches
    /** Flag is set if chemobject is part of an aromatic system. */
    public final static int ISAROMATIC = 5;
    /** Flag is set if chemobject is part of a conjugated system. */
    public final static int ISCONJUGATED = 6;
    /** Flag is set if a chemobject is mapped to another chemobject.
     *  It is used for example in subgraph isomorphism search.
     */
    public final static int MAPPED = 7;

    /** Set to true if the atom is an hydrogen bond donor. */
    public final static int IS_HYDROGENBOND_DONOR = 8;
    /** Set to true if the atom is an hydrogen bond acceptor. */
    public final static int IS_HYDROGENBOND_ACCEPTOR = 9;
        
    /** Flag is set if a chemobject has reactive center.
     *  It is used for example in reaction.
     */
    public static final int REACTIVE_CENTER = 10;
    /**
     * Maximum flags array index.
     */
    public final static int MAX_FLAG_INDEX = 10;
    /**
     * Flag used for JUnit testing the pointer functionality.
     */
    public final static int DUMMY_POINTER = 1;
    /**
     * Maximum pointers array index.
     */
    public final static int MAX_POINTER_INDEX = 1;
    
    /****************************************
     * Some predefined property names for    *
     * ChemObjects                           *
     ****************************************/

    /** The title for a IChemObject. */
    public static final String TITLE = "Title";

    /** A remark for a IChemObject.*/
    public static final String REMARK = "Remark";

    /** A String comment. */
    public static final String COMMENT = "Comment";

    /** A List of names. */
    public static final String NAMES = "Names";

    /** A List of annotation remarks. */
    public static final String ANNOTATIONS = "Annotations";

    /** A description for a IChemObject. */
    public static final String DESCRIPTION = "Description";


    /****************************************
    * Some predefined property names for    *
    * Molecules                             *
    ****************************************/

    /** The Daylight SMILES. */
	public static final String SMILES = "SMILES";
    
    /** The IUPAC International Chemical Identifier. */
	public static final String INCHI = "InChI";
    
    /** The IUPAC compatible name generated with AutoNom. */
    public static final String AUTONOMNAME = "AutonomName";

    /** The Beilstein Registry Number. */
    public static final String BEILSTEINRN = "BeilsteinRN";

    /** The CAS Registry Number. */
    public static final String CASRN = "CasRN";
    
    /** A set of all rings computed for this molecule. */
    public static final String ALL_RINGS = "AllRings";

    /** A smallest set of smallest rings computed for this molecule. */
    public static final String SMALLEST_RINGS = "SmallestRings";

    /** The essential rings computed for this molecule. 
     *  The concept of Essential Rings is defined in 
     *  SSSRFinder
     */
    public static final String ESSENTIAL_RINGS = "EssentialRings";
    
    /** The relevant rings computed for this molecule. 
     *  The concept of relevant Rings is defined in 
     *  SSSRFinder
     */
    public static final String RELEVANT_RINGS = "RelevantRings";

    
    /****************************************
    * Some predefined property names for    *
    * Atoms                                 *
    ****************************************/

    /**
     * This property will contain an ArrayList of Integers. Each
     * element of the list indicates the size of the ring the given
     * atom belongs to (if it is a ring atom at all).
     */
    public static final String RING_SIZES = "RingSizes";

    /**
     * This property indicates how many ring bonds are connected to
     * the given atom.
     */
    public static final String RING_CONNECTIONS = "RingConnections";
    
     /*
     *This property indicate how many bond are present on the atom.
     *
     */
    public static final String TOTAL_CONNECTIONS = "TotalConnections";
    /*
     *Hydrogen count
     *
     */
    public static final String TOTAL_H_COUNT = "TotalHydrogenCount";

    /** The Isotropic Shielding, usually calculated by
      * a quantum chemistry program like Gaussian.
      * This is a property used for calculating NMR chemical
      * shifts by subtracting the value from the 
      * isotropic shielding value of a standard (e.g. TMS).
      */
    public static final String ISOTROPIC_SHIELDING = "IsotropicShielding";

    /****************************************
    * Some predefined property names for    *
    * AtomTypes                             *
    ****************************************/

    /** Used as property key for indicating the ring size of a certain atom type. */
    public static final String PART_OF_RING_OF_SIZE = "Part of ring of size";

    /** Used as property key for indicating the chemical group of a certain atom type. */
    public static final String CHEMICAL_GROUP_CONSTANT = "Chemical Group";

    /** Used as property key for indicating the HOSE code for a certain atom type. */
    public static final String SPHERICAL_MATCHER = "HOSE code spherical matcher";

}


