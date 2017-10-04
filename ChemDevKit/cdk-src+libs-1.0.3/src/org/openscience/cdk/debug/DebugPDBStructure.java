/* $Revision: 6913 $ $Author: egonw $ $Date: 2006-09-18 18:13:21 +0000 (Mon, 18 Sep 2006) $
 *
 *  Copyright (C) 2004-2007  Miguel Rojas <miguel.rojas@uni-koeln.de>
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
package org.openscience.cdk.debug;

import org.openscience.cdk.protein.data.PDBStructure;
import org.openscience.cdk.tools.LoggingTool;

/**
 * Debugging data class.
 * 
 * @author     Miguel Rojas
 * @cdk.module datadebug
 */
public class DebugPDBStructure extends PDBStructure {

	
	private static final long serialVersionUID = 1934748703085969097L;

	LoggingTool logger = new LoggingTool(DebugAtomContainer.class);

    public char getEndChainID() {
    	logger.debug("Getting End Chain ID: ", super.getEndChainID());
		return super.getEndChainID();
    }

    public void setEndChainID(char endChainID) {
    	logger.debug("Setting End Chain ID: ", endChainID);
		super.setEndChainID(endChainID);
    }

    public char getEndInsertionCode() {
    	logger.debug("Getting End Insertion Code: ", super.getEndInsertionCode());
		return super.getEndInsertionCode();
    }

    public void setEndInsertionCode(char endInsertionCode) {
    	logger.debug("Setting End Insertion Code: ", endInsertionCode);
		super.setEndInsertionCode(endInsertionCode);
    }

    public int getEndSequenceNumber() {
    	logger.debug("Getting End Sequence Number: ", super.getEndSequenceNumber());
		return super.getEndSequenceNumber();
    }

    public void setEndSequenceNumber(int endSequenceNumber) {
    	logger.debug("Setting End Sequence Number: ", endSequenceNumber);
		super.setEndSequenceNumber(endSequenceNumber);
    }

    public char getStartChainID() {
    	logger.debug("Getting Start Chain ID: ", super.getStartChainID());
		return super.getStartChainID();
    }

    public void setStartChainID(char startChainID) {
    	logger.debug("Setting Start Chain ID: ", startChainID);
		super.setStartChainID(startChainID);
    }

    public char getStartInsertionCode() {
    	logger.debug("Getting Start Insertion Code: ", super.getStartInsertionCode());
		return super.getStartInsertionCode();
    }

    public void setStartInsertionCode(char startInsertionCode) {
    	logger.debug("Setting Star tInsertion Code: ", startInsertionCode);
		super.setStartInsertionCode(startInsertionCode);
    }

    public int getStartSequenceNumber() {
    	logger.debug("Getting Start Sequence Number: ", super.getStartSequenceNumber());
		return super.getStartSequenceNumber();
    }

    public void setStartSequenceNumber(int startSequenceNumber) {
    	logger.debug("Setting Start Sequence Number: ", startSequenceNumber);
		super.setStartSequenceNumber(startSequenceNumber);
    }

    public String getStructureType() {
    	logger.debug("Getting Structure Type: ", super.getStructureType());
		return super.getStructureType();
    }

    public void setStructureType(String structureType) {
    	logger.debug("Setting Structure Type: ", structureType);
		super.setStructureType(structureType);
    }
	

}
