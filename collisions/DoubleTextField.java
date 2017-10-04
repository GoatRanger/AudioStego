// Copyright (C) 1998 Michael Fowler under Gnu Public License
// http://www.gnu.org/copyleft/gpl.html
/**
 * BuddyBar is a control made up of a label, a text input field, and a scrollbar.
 * Together, they allow a user to input a single real number.
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.util.Locale;
import java.text.ParseException;

// This panel listens for adjustments of its scrollbar and entries in its text field.
public class DoubleTextField extends TextField implements ActionListener, DoubleListener {
	// barValue is the real number this buddy bar currently represents.
	double barValue;
	// The user can, however, enter less reasonable numbers in the text field.
	double textMin, textMax;
	Vector Listeners; // list of objects to notify when the barValue changes.
	String sCommand;
	DecimalFormat decform;
	
	public DoubleTextField(double tMin,double tMax,double init,String com)
	{
		super(Double.toString(init),6);
		textMin = tMin;
		textMax = tMax;
		barValue = init;
		sCommand = com;

		NumberFormat numform = NumberFormat.getInstance(Locale.US);
		if (numform instanceof DecimalFormat) {
			decform = (DecimalFormat) numform;
			decform.applyPattern("#####0.###");
		} else decform = null;
		
		// Tell the textField to notify us when someone hits enter after typing a number.
		addActionListener(this);

		// Noone knows you have to hit enter in the text field to make the value
		// take effect, so we make losing focus an equivalent of hitting enter.
		// This is an anonymous class just for fun.
		this.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent evt) {
				if (!evt.isTemporary()) enterValue();
			}
		});

		// Initialize the list of listeners to our textfield.  There will usually only
		// be one or two.
		Listeners = new Vector(5);
	}

	// Tell our listeners that the barValue of this buddybar has changed.
	private void changeValue()
	{
		for (int i = 0; i<Listeners.size(); i++) {
			DoubleListener ting = (DoubleListener) Listeners.elementAt(i);
			ting.doubleValueChanged(sCommand,barValue);
		}
	}

	// Returns the current value of the buddybar.
	public double getValue()
	{
		// barValue isn't always completely up to date because, while it does get updated
		// any time the scrollbar moves, it only reads the textfield when someone hits
		// the enter key.  In case someone typed in a number and didn't hit enter,
		// we read whatever is in the textfield each time someone asks us for barValue.
		readTextField();
		return barValue;
	}

	// implementation of actionListener.  Called when someone hits enter in a textField.
	public void actionPerformed(ActionEvent e) {
		enterValue();
	}

	protected void enterValue()
	{
		double oldVal = barValue;
		this.readTextField();
		if (oldVal != barValue)
			changeValue();
	}

	private void readTextField()
	{
		String s1 = this.getText();
		try {
			Double dVal = new Double(s1);
			double newval = dVal.doubleValue();
			if (newval<textMin) {
				barValue = textMin;
				formatText(barValue);
			} else if (newval>textMax) {
				barValue = textMax;
				formatText(barValue);
			}
			barValue = newval;
		} catch (NumberFormatException nfe) {
			formatText(barValue);
			// This exception happens when the textField doesn't contain a number.
		}
	}

	public void doubleValueChanged(String cmd, double dVal)
	{
		if (dVal<textMin) {
			barValue = textMin;
		} else if (dVal>textMax) {
			barValue = textMax;
		} else {
			barValue = dVal;
		}
		formatText(barValue);
	}
	
	public void setValue(double dVal)
	{
		if (dVal<textMin) {
			barValue = textMin;
		} else if (dVal>textMax) {
			barValue = textMax;
		} else {
			barValue = dVal;
		}
		formatText(barValue);
		changeValue();
	}
	
	private void formatText(double barValue)
	{
		if (decform != null)
			this.setText(decform.format(barValue));
		else
			this.setText(Double.toString(barValue));
	}
	
	public void addDoubleListener(DoubleListener bl)
	{
		Listeners.addElement(bl);
	}

	public void removeDoubleListener(DoubleListener b)
	{
		Listeners.removeElement((Object)b);
	}
}
