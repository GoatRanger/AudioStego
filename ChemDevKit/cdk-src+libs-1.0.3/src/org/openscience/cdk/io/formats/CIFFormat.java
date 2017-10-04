/* $RCSfile$
 * $Author: egonw $
 * $Date: 2007-01-04 18:46:10 +0100 (Thu, 04 Jan 2007) $
 * $Revision: 7636 $
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
 * @cdk.module io
 * @cdk.set    io-formats
 */
public class CIFFormat implements IChemFormatMatcher {

	private static IResourceFormat myself = null;
	
    private CIFFormat() {}
    
    public static IResourceFormat getInstance() {
    	if (myself == null) myself = new CIFFormat();
    	return myself;
    }

    public String getFormatName() {
        return "Crystallographic Interchange Format";
    }

    public String getMIMEType() {
        return "chemical/x-cif";
    }
    public String getPreferredNameExtension() {
        return getNameExtensions()[0];
    }
    public String[] getNameExtensions() {
        return new String[]{"cif"};
    }

    public String getReaderClassName() { 
      return "org.openscience.cdk.io.CIFReader";
    }
    public String getWriterClassName() { return null; }

    public boolean matches(int lineNumber, String line) {
        if (line.startsWith("_cell_length_a") ||
            line.startsWith("_audit_creation_date") ||
            line.startsWith("loop_")) {
            return true;
        }
        return false;
    }

	public boolean isXMLBased() {
		return false;
	}

	public int getSupportedDataFeatures() {
		return DataFeatures.NONE;
	}

	public int getRequiredDataFeatures() {
		return DataFeatures.NONE;
	}
}
