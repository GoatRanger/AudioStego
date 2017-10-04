/* $RCSfile$
 * $Author: egonw $
 * $Date: 2007-02-19 15:13:57 +0100 (Mon, 19 Feb 2007) $
 * $Revision: 7978 $
 *
 * Copyright (C) 2004-2007  The Chemistry Development Kit (CDK) project
 *
 * Contact: cdk-devel@lists.sourceforge.net
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package org.openscience.cdk.io.formats;

import org.openscience.cdk.tools.DataFeatures;

/**
 * See <a href="http://wwmm.ch.cam.ac.uk/moin/ChemicalMarkupLanguage">here</a>.
 * 
 * @cdk.module io
 * @cdk.set    io-formats
 */
public class CMLFormat implements IChemFormatMatcher {

	private static IResourceFormat myself = null;
	
    private CMLFormat() {}
    
    public static IResourceFormat getInstance() {
    	if (myself == null) myself = new CMLFormat();
    	return myself;
    }
    
    public String getFormatName() {
        return "Chemical Markup Language";
    }

    public String getMIMEType() {
        return "chemical/x-cml";
    }
    public String getPreferredNameExtension() {
        return getNameExtensions()[0];
    }
    public String[] getNameExtensions() {
        return new String[]{"cml", "xml"};
    }

    public String getReaderClassName() { 
      return "org.openscience.cdk.io.CMLReader";
    }
    public String getWriterClassName() { 
      return "org.openscience.cdk.io.CMLWriter";
    }

    public boolean matches(int lineNumber, String line) {
        if ((line.indexOf("http://www.xml-cml.org/schema") != -1) ||
            (line.indexOf("<atom") != -1) ||
            (line.indexOf("<molecule") != -1) ||
            (line.indexOf("<reaction") != -1) ||
            (line.indexOf("<cml") != -1) ||
            (line.indexOf("<bond") != -1)) {
            return true;
        }
        return false;
    }

	public boolean isXMLBased() {
		return true;
	}

	public int getSupportedDataFeatures() {
		return DataFeatures.HAS_2D_COORDINATES |
               DataFeatures.HAS_3D_COORDINATES |
               DataFeatures.HAS_ATOM_PARTIAL_CHARGES |
               DataFeatures.HAS_ATOM_FORMAL_CHARGES |
               DataFeatures.HAS_ATOM_MASS_NUMBERS |
               DataFeatures.HAS_ATOM_ISOTOPE_NUMBERS |
               DataFeatures.HAS_GRAPH_REPRESENTATION |
               DataFeatures.HAS_ATOM_ELEMENT_SYMBOL;
	}

	public int getRequiredDataFeatures() {
		return DataFeatures.NONE;
	}
}
