/*
 *  $RCSfile$
 *  $Author: egonw $
 *  $Date: 2007-04-16 10:40:19 +0200 (Mon, 16 Apr 2007) $
 *  $Revision: 8201 $
 *
 *  Copyright (C) 2003-2007  The JChemPaint project
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

import java.awt.Point;
import java.awt.event.ActionEvent;

import org.openscience.cdk.applications.jchempaint.JChemPaintModel;
import org.openscience.cdk.renderer.Renderer2DModel;


/**
 * @cdk.module jchempaint
 * @author     steinbeck
 */
public class ZoomAction extends JCPAction
{

    private static final long serialVersionUID = -2459332630141921895L;

	public void actionPerformed(ActionEvent e)
	{
		logger.debug("Zooming in/out in mode: ", type);
		JChemPaintModel jcpm = jcpPanel.getJChemPaintModel();
		Renderer2DModel renderModel = jcpm.getRendererModel();
		if (type.equals("in"))
		{
			renderModel.setZoomFactor(renderModel.getZoomFactor() * 1.5);
			jcpPanel.getScrollPane().getViewport().setViewPosition(new Point((int)(jcpPanel.getScrollPane().getViewport().getViewPosition().x*1.5),(int)(jcpPanel.getScrollPane().getViewport().getViewPosition().y*1.5)));
		} else if (type.equals("out"))
		{
			renderModel.setZoomFactor(renderModel.getZoomFactor() / 1.5);
			jcpPanel.getScrollPane().getViewport().setViewPosition(new Point((int)(jcpPanel.getScrollPane().getViewport().getViewPosition().x/1.5),(int)(jcpPanel.getScrollPane().getViewport().getViewPosition().y/1.5)));
		} else if (type.equals("original"))
		{
			renderModel.setZoomFactor(1.0);
		} else
		{
			logger.error("Unkown zoom command: " + type);
		}
		jcpm.fireChange();
	}

}

