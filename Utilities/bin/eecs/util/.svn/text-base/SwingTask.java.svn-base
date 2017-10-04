/*
 * Created on Dec 18, 2003
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package eecs.util;

/**
 * @author DK8685
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public interface SwingTask {
	/**
	   * Called from ProgressBarDemo to start the task.
	   */
	public abstract void go();
	/**
	   * Called from ProgressBarDemo to find out how much work needs
	   * to be done.
	   */
	public abstract int getLengthOfTask();
	/**
	   * Called from ProgressBarDemo to find out how much has been done.
	   */
	public abstract int getCurrent();
	public abstract void stop();
	/**
	   * Called from ProgressBarDemo to find out if the task has completed.
	   */
	public abstract boolean done();
}