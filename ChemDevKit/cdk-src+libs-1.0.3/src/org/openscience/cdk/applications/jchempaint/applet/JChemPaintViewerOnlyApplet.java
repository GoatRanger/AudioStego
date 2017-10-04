/*
 *  Copyright (C) 2002-2007  The Jmol Development Team
 *
 *  Contact: jmol-developers@lists.sf.net
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public License
 *  as published by the Free Software Foundation; either version 2.1
 *  of the License, or (at your option) any later version.
 *  All we ask is that proper credit is given for our work, which includes
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
 */

package org.openscience.cdk.applications.jchempaint.applet;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openscience.cdk.applications.jchempaint.JChemPaintViewerOnlyPanel;
import org.openscience.cdk.controller.Controller2D;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IChemObject;

/**
 * The
 * 
 * @cdk.module jchempaint.applet
 * @author dirk49
 * @cdk.created 04. Mai 2005
 */
public class JChemPaintViewerOnlyApplet extends JChemPaintAbstractApplet implements MouseMotionListener{

    private static final long serialVersionUID = -3840571150179448187L;
    
    private Applet spectrumApplet;
    private Object lastHighlighted=null;
    private Controller2D controller;
    int oldnumber=-1;
  
    /* (non-Javadoc)
	 * @see java.applet.Applet#init()
	 */
	public void init() {
		JChemPaintViewerOnlyPanel jcpvop = new JChemPaintViewerOnlyPanel(new Dimension((int)this.getSize().getWidth(),(int)this.getSize().getHeight()), "applet", !(getParameter("scrollbars")!=null && getParameter("scrollbars").equals("false")));
		setTheJcpp(jcpvop);
		/*String atomNumbers=getParameter("spectrumRenderer");
    	if(atomNumbers!=null){*/
			getTheJcpp().getDrawingPanel().addMouseMotionListener(this);
		//}
		super.init();
	}
	
	/* (non-Javadoc)
	 * @see java.applet.Applet#start()
	 */
	public void start() {
		super.start();
	}
	
	/* (non-Javadoc)
	 * @see java.applet.Applet#stop()
	 */
	public void stop() {
		super.stop();
	}
  
  
  public void mouseDragged(MouseEvent event)
  {
  }
  
  
  public void mouseMoved(MouseEvent event)
  {
    //if(getParameter("spectrumRenderer")==null)
    //  return;
    try{
      getSpectrumApplet();
      if(controller==null)
        controller=new Controller2D(getTheJcpp().getChemModel(),getTheJcpp().getJChemPaintModel().getRendererModel());
      int[] screenCoords = {event.getX(), event.getY()};
      int[] mouseCoords = controller.getWorldCoordinates(screenCoords);
      int mouseX = mouseCoords[0];
      int mouseY = mouseCoords[1];
      IChemObject objectInRange = controller.getChemObjectInRange(mouseX, mouseY);
      if (objectInRange!=lastHighlighted && objectInRange instanceof IAtom)
      {
   	    getTheJcpp().getJChemPaintModel().getRendererModel().setExternalHighlightColor(Color.RED);
   	    IAtomContainer ac=getTheJcpp().getJChemPaintModel().getChemModel().getMoleculeSet().getBuilder().newAtomContainer();
   	    ac.addAtom((IAtom)objectInRange);
   	    getTheJcpp().getJChemPaintModel().getRendererModel().setExternalSelectedPart(ac);
        highlightPeakInSpectrum(getTheJcpp().getChemModel().getMoleculeSet().getMolecule(0).getAtomNumber((IAtom)objectInRange));
        highlightPeakInTable(getTheJcpp().getChemModel().getMoleculeSet().getMolecule(0).getAtomNumber((IAtom)objectInRange));
        repaint();
        lastHighlighted=objectInRange;
      }
    }catch(Exception ex){
      ex.printStackTrace();
    }
  }
  
  
  /**
   * Handles interaction with structure viewer,
   * highlighted atoms in spectrum view will be highlighted in structure
   * @param atomNumber atom number of peaks highlighted in spectrum
   */
  public void highlightPeakInSpectrum(int atomNumber) throws Exception{
    if(getParameter("spectrumRenderer")==null)
      return;
	Method highlightMethod = getSpectrumApplet().getClass().getMethod("highlightPeakInSpectrum", new Class[] { Integer.TYPE });
    highlightMethod.invoke(getSpectrumApplet(),	new Object[] { new Integer(atomNumber) });
    spectrumApplet.repaint();
  }

  /**
   * Handles interaction with a peak table
   * @param atomNumber atom number of peaks highlighted in table
   */
  public void highlightPeakInTable(int atomNumber) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
    if(getParameter("highlightTable")==null || getParameter("highlightTable").equals("false"))
      return;
    

	Class[] paratypes={new Applet().getClass()};
	Class jso = Class.forName("netscape.javascript.JSObject");
    Method getWindowMethod=jso.getMethod("getWindow", paratypes);
    Object win=getWindowMethod.invoke(jso, new Object[] {this});
	Class[] paratypes2={new String("").getClass()};
	Method evalMethod=jso.getMethod("eval", paratypes2);
	Class[] paratypes3={new String("").getClass(),new Object().getClass()};
	Method setMemberMethod=jso.getMethod("setMember", paratypes3);

    if(oldnumber!=-1){
    	Object tr = evalMethod.invoke(win,new Object[]{"document.getElementById(\"tableid"+oldnumber+"\").style"});
        if((oldnumber+1)%2==0)
        	setMemberMethod.invoke(tr, new Object[]{"backgroundColor","#D3D3D3"});
        else
        	setMemberMethod.invoke(tr, new Object[]{"backgroundColor","white"});
    }
    Object tr = evalMethod.invoke(win,new Object[]{"document.getElementById(\"tableid"+atomNumber+"\").style"});
    if(tr==null){
    	oldnumber=-1;
    }else{
    	setMemberMethod.invoke(tr, new Object[]{"backgroundColor","#FF6600"});
	    oldnumber=atomNumber;
    }
  }

  private Applet getSpectrumApplet() {
      if (spectrumApplet == null) {
          String s = getParameter("spectrumRenderer");
          if ((s != null) && (s.length() > 0)) {
              spectrumApplet = getAppletContext().getApplet(s);
          }
      }
      return spectrumApplet;
  }
}
