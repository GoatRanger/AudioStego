package imageproc.utilities;

import javax.swing.*;

public class Utility {
	final static public int PREPROCESSOR = 0;

	final static public int IMAGEDIALOG = 1;

	final static public int HISTOGRAM = 2;

	final static public int FILEWRITER = 3;

	final static private int TYPES = 4;

	public static boolean[] active = new boolean[TYPES];

	public static String[] name = new String[] { "PREPROCESSOR", "IMAGEDIALOG",
			"HISTOGRAM", "FILEWRITER" };

	static {
		for (int i = 0; i < TYPES; i++)
			active[i] = true;
	}

	// --------------Class Bug Report Functions---------

	public static void reportBug(String msg) {
		Exception e = new Exception();
		reportCondition(msg + "\nPress button to terminate");
		e.printStackTrace();
		System.exit(0);
	}

	public static void reportBug(Exception e) {
		reportBug(e.toString());
	}

	public static void reportCondition(String msg) {
		//        JFrame frame = new JFrame ("Bug Report");
		//        frame.setSize (800, 500);
		//        frame.setLocation (0,0);
		//        frame.setVisible (true);
		JOptionPane.showMessageDialog(null, // Parent component.
				msg, // Object message.
				(String) null, // String title.
				JOptionPane.INFORMATION_MESSAGE // int messageType
				);
		System.out.println("\n*** " + msg + " ***\n");
	}

	public static void reportException(Exception e) {
		reportCondition(e.toString());
	}

	// --------------Class Debug Info Per Flags------------

	public static void diagnostic(int type, Object o, String msg) {
		int headSize = 20;
		String header = o.getClass().getName();

		while (header.length() < headSize - 2)
			header = "-" + header + "-";
		while (header.length() < headSize)
			header = header + "-";
		try {
			if (active[type])
				System.out.println(header + "> " + msg);
		} catch (IndexOutOfBoundsException e) {
		}
	}
}