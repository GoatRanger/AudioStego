/*
 * Created on Nov 26, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package jago;

/**
 * @author Karl
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class TimerCheck {

	public static void main(String[] args) {
		long ms = System.currentTimeMillis();
		long time = System.nanoTime();
		while (System.currentTimeMillis() <= ms+1000);
		System.out.print("ns " + (System.nanoTime()-time));
	}
}
