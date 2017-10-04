/*
 * Triage.java
 *
 * Created on April 7, 2001, 11:32 PM
 */

package imageproc;

//import imageproc.Triage.Item;
/**
 * 
 * @author Default
 * @version
 */
//import imageproc.database.*;
import imageproc.utilities.TriageList;
import java.util.*;
import java.io.*;

import javax.swing.*;

public class Triage extends java.lang.Object {

	private static final char QUOTE = '"';

	//private Database database;

	private List<Item> triageList;

	private String[] result;

	/** Creates new Triage */
	public Triage() {
		triageList = new ArrayList<Item>();
	}

	/** Reorder the classification list */
	public void execute() {
		// Read the file
		if (triageList.isEmpty())
			readTriage();
		// Sort it
		Collections.sort(triageList);
	}

	/** Set the contents for triage. Not Implemented */
	public void setTriage() {
	}

	/** Read a classification file and prepare to reorder */
	public void readTriage() {
		File diskFile = null;
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Open Classification Results File");
		fc.setFileFilter(new ResultFilter());
		int returnVal = fc.showDialog(new JFrame(), "Open");
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			diskFile = fc.getSelectedFile();
		}
		InputStream is = null;
		try {
			is = new FileInputStream(diskFile);
		} catch (Exception e) {
		}
		Reader r = new BufferedReader(new InputStreamReader(is));
		StreamTokenizer st = new StreamTokenizer(r);

		// Set up the parameters for parsing
		st.eolIsSignificant(false);
		st.quoteChar(QUOTE);
		st.whitespaceChars(',', ',');
		st.whitespaceChars(' ', ' ');
		st.whitespaceChars(':', ':');
		st.whitespaceChars('(', ')');
		Item record = null;
		process: while (true) {
			try {

				// Read name
				String name = null;
				switch (st.nextToken()) {
				case StreamTokenizer.TT_WORD:
				case QUOTE:
					name = st.sval;
					break;
				default:
					name = "Unidentified";
					break;
				}

				switch (st.nextToken()) {
				case StreamTokenizer.TT_WORD:
				case QUOTE:
					if (st.sval.startsWith("(Image")) {
						record = new Item();
						record.setIndex(st.sval.substring(1,
								st.sval.length() - 1));
						record.setName(name);
					}
					break;
				case StreamTokenizer.TT_EOF:
					break process;
				}

				for (int i = 0; i < 4; i++) {
					switch (st.nextToken()) {
					case StreamTokenizer.TT_WORD:
						String heading = st.sval;
						st.nextToken();
						double val = st.nval;
						if (heading.equalsIgnoreCase("clear")) {
							record.setClear(val);
						} else if (heading.equalsIgnoreCase("precipitate")) {
							record.setPrecip(val);
						} else if (heading.equalsIgnoreCase("microcrystal")) {
							record.setMicrocrystal(val);
						} else if (heading.equalsIgnoreCase("crystal")) {
							record.setCrystal(val);
						}
						break;
					default:
						break;
					}
				}
				triageList.add(record);
			} catch (Exception e) {
			}
		}
		try {
			is.close();
		} catch (Exception e) {
		}
	} // End readTriage

	public java.lang.String toString() {
		return triageList.toString();
	}

	public void save() {
	}

	public void print() {

		int[] thresholds = { 700, 500, 300, 100, -1000 };
		String[] thresholdLabel = { "Crystal/Very Interesting",
				"Crystal/Precip Mix", "Interesting/Difficult to Classify",
				"Precipitate/Faint", "Light Development/Clear" };

		// Create an array for the result list, + 15 for categories/dividers
		result = new String[triageList.size() + 15];
		int ptr = 0;
		int currentThreshold = 0;
		int lastThreshold = -1;
		for (Iterator<Item> i = triageList.iterator(); i.hasNext();) {
			Item curr = (Item) i.next();
			if (curr.getTotalWeight() < thresholds[currentThreshold]) {
				currentThreshold++;
			}
			if (currentThreshold != lastThreshold) {
				lastThreshold = currentThreshold;
				result[ptr++] = "---------------------------------";
				result[ptr++] = thresholdLabel[currentThreshold];
				result[ptr++] = "---------------------------------";
			}
			result[ptr++] = curr.toString();

		}
		TriageList tl = new TriageList(new JFrame(), true);
		tl.set(result);
		tl.setVisible(true);
	}

	public List<Item> getResults() {
		return triageList;
	}

	public void writeResults() {
		OutputStream fos;
		try {
			fos = new FileOutputStream("TriageResult.out");
		} catch (Exception e) {
			return;
		}
		PrintWriter pw = new PrintWriter(fos);

		for (int i = 0; i < result.length; i++) {
			try {
				pw.write(result[i] + "\n");
			} catch (Exception e) {
			}
		}

		try {
			pw.flush();
			fos.close();
		} catch (Exception e) {
		}
	}

	class Item implements Comparable<Object> {
		String name;

		String index;

		double clear;

		double precip;

		double micro;

		double crystal;

		public Item() {
		}

		public Item(String name, String idx, double cl, double pr, double mi,
				double cr) {
			if (cl < 0 || pr < 0 || mi < 0 || cr < 0) {
				throw new IllegalArgumentException();
			}
			this.name = name;
			index = idx;
			clear = cl;
			precip = pr;
			micro = mi;
			crystal = cr;
		}

		// Getters
		public String name() {
			return name;
		}

		public String index() {
			return index;
		}

		public double clear() {
			return clear;
		}

		public double precip() {
			return precip;
		}

		public double micro() {
			return micro;
		}

		public double crystal() {
			return crystal;
		}

		private double getCrystalWeight() {
			return crystal * 9.0;
		}

		private double getMicroWeight() {
			return (micro > 10 ? (micro - 10.0) * 4.0 : 0.0);
		}

		private double getPrecipWeight() {
			if (crystal > 50) {
				return (precip > 15 ? (precip - 15.0) * 1.5 : 0.0);
			} else {
				return (precip > 15 ? (precip - 15.0) * 2.0 : 0.0);
			}
		}

		private double getClearWeight() {
			if (clear > crystal && clear > micro && clear > precip) {
				return clear;

			} else {
				return -clear;
			}
		}

		public double getTotalWeight() {
			return getCrystalWeight() + getMicroWeight() + getPrecipWeight()
					+ getClearWeight();
		}

		// Setters
		public void setName(String name) {
			if (name != null) {
				this.name = name;
			}
		}

		public void setIndex(String index) {
			if (index != null) {
				this.index = index;
			}
		}

		public void setClear(double clear) {
			if (clear >= 0.0) {
				this.clear = clear;
			}
		}

		public void setCrystal(double crystal) {
			if (crystal >= 0.0) {
				this.crystal = crystal;
			}
		}

		public void setMicrocrystal(double micro) {
			if (micro >= 0.0) {
				this.micro = micro;
			}
		}

		public void setPrecip(double precip) {
			if (precip >= 0.0) {
				this.precip = precip;
			}
		}

		// Comparisons
		public boolean equals(Object o) {
			if (!(o instanceof Item))
				return false;
			Item i = (Item) o;
			return (name == i.name && clear == i.clear && precip == i.precip
					&& micro == i.micro && crystal == i.crystal);
		}

		public int hashCode() {
			return 31 * name.hashCode()
					+ (int) (clear + precip + micro + crystal);
		}

		public String toString() {
			return "<" + index + "> " + name + ": Clear (" + clear
					+ "), Precip (" + precip + "), Microcrystal (" + micro
					+ "), Crystal (" + crystal + ")";
		}

		public int compareTo(Object o) {
			Item i = (Item) o;

			double val = getTotalWeight();

			double inVal = i.getTotalWeight();

			if (inVal < val)
				return -1;
			if (inVal == val)
				return 0;
			return 1;
		}
	}
	class ResultFilter extends javax.swing.filechooser.FileFilter {
	    public boolean accept(File f) {
	        return f.isDirectory() || f.getName().toLowerCase().endsWith(".ccr");
	    }
	    
	    public String getDescription() {
	        return "Results (*.ccr)";
	    }
	}
}