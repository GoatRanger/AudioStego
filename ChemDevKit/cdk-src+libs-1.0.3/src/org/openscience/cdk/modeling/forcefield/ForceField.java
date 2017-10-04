package org.openscience.cdk.modeling.forcefield;

import javax.vecmath.GVector;

import org.openscience.cdk.AtomContainer;
import org.openscience.cdk.Molecule;
import org.openscience.cdk.graph.ConnectivityChecker;
//import org.openscience.cdk.tools.LoggingTool;

/**
 *  To work with the coordinates of the molecule, like get the 3d coordinates of the atoms or 
 *  calculate the distance between two atoms.
 *@author     vlabarta
 *@cdk.module     forcefield
 *
 */
public class ForceField extends GeometricMinimizer{
	
	private String potentialFunction="mmff94";
	
	boolean sdm_flag=true;
        boolean cgm_flag=true;
        boolean nrm_flag=true;
	
	//private LoggingTool logger;


	/**
	 *  Constructor for the ForceField object
	 */
	public ForceField() {        
		//logger = new LoggingTool(this);
	}
	
	public ForceField(Molecule molecule) throws Exception {
		setMolecule(molecule, false);
		//logger = new LoggingTool(this);
	}


	public void setPotentialFunction(String potentialName){
		potentialFunction=potentialName;
	}
	
        public void setUsedGMMethods(boolean sdm, boolean cgm,boolean nrm){
	       sdm_flag=sdm;
               cgm_flag=cgm;
               nrm_flag=nrm;
        }
	
	public void minimize( ) throws Exception{
		long start, stop;
		if (!ConnectivityChecker.isConnected(molecule)) {
			throw new Exception("CDKError: Molecule is NOT connected,could not layout.");
		}
		GVector moleculeCoords = new GVector(3);
		MMFF94EnergyFunction mmff94PF=null;
		if (potentialFunction=="mmff94"){
		    //logger.debug("SET POTENTIAL FUNCTION TO MMFF94");
		    setMMFF94Tables(molecule);
		    mmff94PF=new MMFF94EnergyFunction((AtomContainer)molecule,getPotentialParameterSet());
			//logger.debug("PotentialFunction set:"+potentialFunction+", Hashtable:" +getPotentialParameterSet().size());
		}
		moleculeCoords.setSize(molecule.getAtomCount() * 3);
		moleculeCoords.set(ForceFieldTools.getCoordinates3xNVector((AtomContainer)molecule));
		
		//logger.debug("PotentialFunction set:"+potentialFunction+" Molecule Coords set:"+moleculeCoords.getSize()+" Hashtable:"+getPotentialParameterSet().size());
		//logger.debug(moleculeCoords.toString());
		start = System.currentTimeMillis();
		//logger.debug("Starting minmization at " + start);
		if (sdm_flag) steepestDescentsMinimization(moleculeCoords,mmff94PF);

		if (cgm_flag)conjugateGradientMinimization(moleculeCoords, mmff94PF);
		//conjugateGradientMinimization(moleculeCoords, tpf);
		stop = System.currentTimeMillis();
		//logger.debug("Finished minmization at " + stop);
		//if ((stop - start)/1000 < 60) {
			//logger.debug("Time for minimization: " + (stop - start)/1000 + " sec");
		//}
		//	logger.debug("Time for minimization: " + (stop - start)/60000 + " min");

		//}
		//logger.debug("Minimization READY");
		//ForceFieldTools.assignCoordinatesToMolecule(moleculeCoords, (AtomContainer) molecule); 
	}
			



}


