/* $RCSfile$
 * $Author: egonw $
 * $Date: 2007-02-19 15:17:08 +0100 (Mon, 19 Feb 2007) $
 * $Revision: 7979 $
 *
 * Copyright (C) 2003-2007  The Chemistry Development Kit (CDK) project
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
 * See <a href="http://www.schrodinger.com/">here</a>.
 * 
 * @cdk.module io
 * @cdk.set    io-formats
 */
public class JaguarFormat implements IChemFormatMatcher {

	private static IResourceFormat myself = null;
	
    private JaguarFormat() {}
    
    public static IResourceFormat getInstance() {
    	if (myself == null) myself = new JaguarFormat();
    	return myself;
    }

    public String getFormatName() {
        return "Jaguar";
    }

    public String getMIMEType() {
        return null;
    }
    public String getPreferredNameExtension() {
        return getNameExtensions()[0];
    }
    public String[] getNameExtensions() {
        return new String[]{"j","jin","jout"};
    }

    public String getReaderClassName() { return null; }
    public String getWriterClassName() { return null; }

    public boolean matches(int lineNumber, String line) {
        if (line.indexOf("Jaguar") >= 0 &&
            line.indexOf("Schrodinger") >= 0) {
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
