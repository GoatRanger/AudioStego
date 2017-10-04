/*
 *  $RCSfile$
 *  $Author: egonw $
 *  $Date: 2007-04-16 10:40:19 +0200 (Mon, 16 Apr 2007) $
 *  $Revision: 8201 $
 *
 *  Copyright (C) 2004-2007  The JChemPaint project
 *
 *  Contact: jchempaint-devel@lists.sourceforge.net
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
package org.openscience.cdk.applications.jchempaint.action;

import java.awt.Color;
import java.awt.event.ActionEvent;

import org.openscience.cdk.applications.jchempaint.JChemPaintModel;
import org.openscience.cdk.renderer.Renderer2DModel;


/**
 * Opens a dialog allowing to adjust the coloring scheme for 
 * drawn molecules
 *
 * @cdk.module jchempaint
 * @author     steinbeck
 */
public class SetColorSchemeAction extends JCPAction
{

    private static final long serialVersionUID = 8812815445839360119L;

	public SetColorSchemeAction()
	{
		super();
	}

	public void actionPerformed(ActionEvent e)
	{
		logger.info("Changing color scheme to: ", type);
		if (jcpPanel.getJChemPaintModel() != null)
		{
			JChemPaintModel jcpmodel = jcpPanel.getJChemPaintModel();
			Renderer2DModel renderModel = jcpmodel.getRendererModel();
			if ("blackOnWhite".equals(type))
			{
				renderModel.setForeColor(Color.BLACK);
				renderModel.setBackColor(Color.WHITE);
			} else if ("whiteOnBlack".equals(type))
			{
				renderModel.setForeColor(Color.WHITE);
				renderModel.setBackColor(Color.BLACK);
			}
			jcpmodel.fireChange();
		}
	}

}

