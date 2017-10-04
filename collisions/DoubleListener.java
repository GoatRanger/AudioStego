// Copyright (C) 1998 Michael Fowler under Gnu Public License
// http://www.gnu.org/copyleft/gpl.html


	// This interface is called BuddyBar.BuddyListener by other objects.
	// An object implementing this interface, or implementing the method shown below,
	// can register itself as a buddyListener with the next method.
public interface DoubleListener
{
	// This method is called for every listener every time the real value of
	// a buddybar changes.
	public void doubleValueChanged(String command, double val);
}

