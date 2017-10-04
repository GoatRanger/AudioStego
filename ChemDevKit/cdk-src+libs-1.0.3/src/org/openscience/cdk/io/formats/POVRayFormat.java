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


/**
 * See <a href="http://www.povray.org/">here</a>.
 * 
 * @author Miguel Rojas
 * 
 * @cdk.module io
 * @cdk.set    io-formats
 */
public class POVRayFormat implements IResourceFormat {

	private static IResourceFormat myself = null;
	
    private POVRayFormat() {}
    
    public static IResourceFormat getInstance() {
    	if (myself == null) myself = new POVRayFormat();
    	return myself;
    }
    
    public String getFormatName() {
        return "POV Ray";
    }

    public String getMIMEType() {
        return null;
    }
    public String getPreferredNameExtension() {
        return getNameExtensions()[0];
    }
    public String[] getNameExtensions() {
        return new String[]{"pov"};
    }

	public boolean isXMLBased() {
		return false;
	}
}
