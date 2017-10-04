/* $RCSfile$   
 * $Author: egonw $   
 * $Date: 2007-01-04 18:26:00 +0100 (Thu, 04 Jan 2007) $    
 * $Revision: 7634 $
 * 
 * Copyright (C) 2001-2007  The Chemistry Development Kit (CDK) project
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
 */
package org.openscience.cdk.applications.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.EventObject;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.openscience.cdk.event.ICDKChangeListener;

/**
 * @cdk.module  applications
 * @cdk.require swing
 */
public class AtomicTable extends JPanel implements ICDKChangeListener {

	private static final long serialVersionUID = -7017202227040620173L;
	
	private JTable table;
	private String title = "Molecule Viewer";
  
	public AtomicTable(org.openscience.cdk.interfaces.IAtomContainer atomContainer) {
    AtomContainerModel acm = new AtomContainerModel(atomContainer);
    table = new JTable(acm);
    table.setPreferredScrollableViewportSize(new Dimension(500,300));
    JScrollPane scrollPane = new JScrollPane(table);
    this.add(scrollPane, BorderLayout.CENTER);
	}
	
	/**
	 * Contructs a JFrame into which this JPanel is
	 * put and displays the frame with the molecule
	 */
	public void display()
	{
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.setTitle(title);
		frame.pack();
		frame.setVisible(true);		
	}

  public void stateChanged(EventObject e) {
    // not implemented yet
  }
	
  class AtomContainerModel extends AbstractTableModel {

	private static final long serialVersionUID = -3331835007097257315L;

	private org.openscience.cdk.interfaces.IAtomContainer atomContainer;
    
    final String[] columnNames = {"atom", "x2", "y2", "x3", 
                                  "y3", "z3", "charge"};
    
    public AtomContainerModel(org.openscience.cdk.interfaces.IAtomContainer ac) {
      atomContainer = ac;
    }
    
    public int getColumnCount() {
      return columnNames.length;
    }
    
    public int getRowCount() {
      return atomContainer.getAtomCount();
    }
    
    public String getColumnName(int col) {
      return columnNames[col];
    }
    
    public Class getColumnClass(int col) {
      Object o = getValueAt(0,col);
      if (o == null) {
        return (new String()).getClass();
      } else {
        return o.getClass();
      }
    }
    
    public Object getValueAt(int row, int col) {
      if (getColumnName(col).equals("atom")) {
        return (atomContainer.getAtom(row)).getSymbol();
      } else if (getColumnName(col).equals("x2")) {
        return new Double((atomContainer.getAtom(row)).getPoint2d().x);
      } else if (getColumnName(col).equals("y2")) {
        return new Double((atomContainer.getAtom(row)).getPoint2d().y);
      } else if (getColumnName(col).equals("x3")) {
        return new Double((atomContainer.getAtom(row)).getPoint3d().x);
      } else if (getColumnName(col).equals("y3")) {
        return new Double((atomContainer.getAtom(row)).getPoint3d().y);
      } else if (getColumnName(col).equals("z3")) {
        return new Double((atomContainer.getAtom(row)).getPoint3d().z);
      } else if (getColumnName(col).equals("charge")) {
        return new Double((atomContainer.getAtom(row)).getCharge());
      } else {
        return null;
      }
    }
    
    public boolean isCellEditable(int row, int col) {
      return false;
    }
  }
}


