/*
 * Created on Mar 20, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package eecs.instructor.cobb;

import jago.element.SolidElement;

/**
 * @author dm0266
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ObstacleElement {
	 static SolidElement leftObstacle;
	static SolidElement rightObstacle;
	
public	ObstacleElement()
	{
		
	}
public  void setLeftObstacle(SolidElement obstacle)
	{
		leftObstacle=obstacle;
	}
public void setRightObstacle(SolidElement obstacle)
	{
		rightObstacle=obstacle;
	}
	/**
	 * @return Returns the leftObstacle.
	 */
	public  SolidElement getLeftObstacle() {
		return leftObstacle;
	}
	/**
	 * @return Returns the rightObstacle.
	 */
	public SolidElement getRightObstacle() {
		return rightObstacle;
	}
}
