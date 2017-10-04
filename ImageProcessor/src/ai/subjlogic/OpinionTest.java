/*
 * Created on Sep 24, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package ai.subjlogic;

import junit.framework.TestCase;

/**
 * @author Karl
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class OpinionTest extends TestCase {

	Opinion base;
	public static void main(String[] args) {
		junit.swingui.TestRunner.run(OpinionTest.class);
	}
	
	public void setUp() {
		base = new Opinion();
	}
	
	public void testConsensus() {
		Opinion peter1 = new Opinion(0.99,0.01,0.0,1.0/3.0);
		Opinion peter2 = new Opinion(0.0,1.0,0.0,1.0/3.0);
		Opinion peterC = peter1.consensus(peter2);
		System.out.println(peterC);
		assertEquals("Peter W1/W2 p10/b",0.495,peterC.getBelief(),0.0001);
		assertEquals("Peter W1/W2 p10/d",0.505,peterC.getDisbelief(),0.0001);
		assertEquals("Peter W1/W2 p10/u",0.0,peterC.getUncertainty(),0.0001);
		assertEquals("Peter W1/W2 p10/a",1.0/3.0,peterC.getAtomicity(),0.0001);
		base = new Opinion(1.0,0.0,0.0,1.0/6.0);
		Opinion conflict = new Opinion(0.0,1.0,0.0,1.0/6.0);
		Opinion consensus = base.consensus(conflict,1.0/5.0);
		System.out.println("Consensus: " + consensus);
		assertEquals("Conflict w/dogmatism/b",1.0/6.0,consensus.getBelief(),0.0001);
		assertEquals("Conflict w/dogmatism/d",5.0/6.0,consensus.getDisbelief(),0.0001);
		assertEquals("Conflict w/dogmatism/u",0.0,consensus.getUncertainty(),0.0001);
		assertEquals("Conflict w/dogmatism/a",1.0/6.0,consensus.getAtomicity(),0.0001);
		Opinion a1 = new Opinion(0.0,1.0,0.0,1.0);
		Opinion a2 = new Opinion(0.326,0.339,0.333,0.5);
		Opinion c3 = a1.consensus(a2);
		assertEquals("Complete disbelief/b",0.0,c3.getBelief(),0.0001);
		assertEquals("Complete disbelief/d",1.0,c3.getDisbelief(),0.0001);
		assertEquals("Complete disbelief/u",0.0,c3.getUncertainty(),0.0001);
		assertEquals("Complete disbelief/a",1.0,c3.getAtomicity(),0.0001);
	}
	
	public void testConjunction() {
		base = new Opinion(0.8,0.1,0.1,0.5);
		Opinion other = new Opinion(0.1,0.8,0.1,0.5);
		Opinion conjunct = base.conjunction(other);
		assertEquals("Belief",0.11,conjunct.getBelief(),0.0001);
		assertEquals("Disbelief",0.82,conjunct.getDisbelief(),0.0001);
		assertEquals("Ignorance",0.07,conjunct.getUncertainty(),0.0001);
		assertEquals("Atomicity",0.25,conjunct.getAtomicity(),0.0001);
		
		base = new Opinion(0.8,0.2,0.0,0.4);
		other = new Opinion(0.2,0.8,0.0,0.6);
		conjunct = base.conjunction(other);
		assertEquals("Belief",0.16,conjunct.getBelief(),0.0001);
		assertEquals("Disbelief",0.84,conjunct.getDisbelief(),0.0001);
		assertEquals("Ignorance",0.0,conjunct.getUncertainty(),0.0001);
		assertEquals("Atomicity",0.24,conjunct.getAtomicity(),0.0001);
	}
	
	public void testComplement() {
		base = new Opinion(0.1,0.8,0.1,0.2);
		Opinion negate = base.complement();
		assertEquals("Belief",0.8,negate.getBelief(),0.0001);
		assertEquals("Disbelief",0.1,negate.getDisbelief(),0.0001);
		assertEquals("Ignorance",0.1,negate.getUncertainty(),0.0001);
		assertEquals("Atomicity",0.8,negate.getAtomicity(),0.0001);
	}
	
	public void testAdjustExpectation() {
		base = new Opinion(0.1,0.7,0.2,0.5);
		Opinion adjusted = base.adjustExpectation(0.7);
		assertEquals("Belief",0.66,adjusted.getBelief(),0.0001);
		assertEquals("Disbelief",0.26,adjusted.getDisbelief(),0.0001);
		assertEquals("Ignorance",0.08,adjusted.getUncertainty(),0.0001);
		assertEquals("Atomicity",0.5,adjusted.getAtomicity(),0.0001);
		
		base = new Opinion(0.2,0.55,0.25,0.41);
		adjusted = base.adjustExpectation(0.1);
		assertEquals("Belief",0.07,adjusted.getBelief(),0.0001);
		assertEquals("Disbelief",0.85,adjusted.getDisbelief(),0.0001);
		assertEquals("Ignorance",0.08,adjusted.getUncertainty(),0.0001);
		assertEquals("Atomicity",0.1,adjusted.getAtomicity(),0.0001);
	}

}
