/* Copyright (C) 1998 Michael Fowler under Gnu Public License
 * http://www.gnu.org/copyleft/gpl.html
 *
 * CmdCheckbox.java
 * This class exists because Java uses the label of a checkbox to identify it
 * in an ItemEvent.  When you use international strings, you never know what
 * the label will be.  Bucketheads.
 */
 
import java.awt.*;

public class CmdCheckbox extends Checkbox {
	String sCommand;

	public CmdCheckbox(String label)
	{
		super(label);
        sCommand = null;
	}

	public CmdCheckbox(String label, boolean bSet)
	{
		super(label,bSet);
        sCommand = null;
	}

	public CmdCheckbox(String label, boolean bSet, String cmd)
	{
		super(label,bSet);
        sCommand = cmd;
	}
        

    public void setCommand(String cmd)
    {
        sCommand = cmd;
    }

    public String getCommand()
    {
        return sCommand;
    }
}

