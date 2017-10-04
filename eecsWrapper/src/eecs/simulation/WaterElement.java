/*
 * Created on Mar 18, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package eecs.simulation;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;

import jago.element.Collidable;
import jago.element.FlatObject;
import jago.element.VisibleElement;

/**
 * @author DM0266
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class WaterElement extends VisibleElement 
implements Collidable, FlatObject{

int width;
Color color;
GeneralPath path;
boolean fillPath;
boolean pendingCollision=false;

public WaterElement(GeneralPath path, Color color, boolean fillPath,int width)
{
	super();
	
	this.width=width;
    this.path=path;
    this.color=color;  //probably should be set to blue?
    this.fillPath=fillPath;
}
/**
 * Undocumented
 *
 * @param pending Empty
 */
public void pendingCollision(boolean pending) {
    pendingCollision = pending;
}

/**
 * Determines the way this object bounces off another Collidable object
 *
 * @param collisionElement DOCUMENT ME!
 */
public void bounceOff(Collidable collisionElement) {

    // does nothing
}


/**
 * React to a collision with another Collidable object
 *
 * @param collisionElement DOCUMENT ME!
 */
public void handleCollision(Collidable collisionElement) {

   
}

public void createArea()
{
	this.myShape=new Area(path);
}
public void draw(Graphics2D g2) {
   
    g2.setColor(color);
    
    g2.setStroke(new java.awt.BasicStroke((float)width, java.awt.BasicStroke.CAP_ROUND,java.awt.BasicStroke.JOIN_ROUND));
    if (fillPath)
       g2.fill(path);
    g2.draw(path);
}

}
