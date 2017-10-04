/*
 * Database.java
 *
 * Created on March 30, 2001, 12:53 AM
 */

package imageproc.database;

/**
 * Manages the database of images.  Allows import and export using a formatted
 * text file, as well as saving and loading a serialized version of CrystalData
 * records.
 *
 * @author  Karl A. Gossett
 * @version 0.1 7 April 2001
 */
import java.util.*;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JFileChooser;

public class Database extends java.lang.Object {
    
    static final char QUOTE = '"';
    List<CrystalData> records;
    
    /** The current record pointer */
    int currentRecord;

    File diskFile;
    
    /** Creates new Database */
    public Database () {
        records = new ArrayList<CrystalData> ();
        currentRecord = 0;
        diskFile = null;
    }
    
    /** Inserts a new record into the database at the current position */
    public void insert (CrystalData record) {
        records.add (currentRecord, record);
    }
    
    /** Deletes the current record from the database */
    public Object delete () {
        return records.remove (currentRecord);
    }
    
    /** Moves the pointer to the next record in the database. 
     *
     * @return      <code>true</code> if the pointer moved.
     */
    public boolean next () {
        if (currentRecord < (records.size ()-1)) {
            currentRecord++;
            return true;
        } else {
            return false;
        }
    }
    
    /** Moves the pointer to the previous record in the database.
     *
     * @return      <code>true</code> if the pointer moved.
     */
    public boolean previous () {
        if (currentRecord > 0) {
            currentRecord--;
            return true;
        } else {
            return false;
        }
    }
    
    /** Moves the pointer to the first record in the database. */
    public boolean first () {
        currentRecord = 0;
        return true;
    }
    
    /** Moves the pointer to the last record in the database */
    public boolean last () {
        currentRecord = records.size();
        return true;
    }
    
    /** Moves the pointer to the specified record in the database.
     *  Returns <code>true</code> if the specified record number exists.
     */
    public boolean go (int dest) {
        if (dest < records.size() && dest >= 0) {
            currentRecord = dest;
            return true;
        } else {
            return false;
        }
    }
    
    /** Returns the current record from the database as an Object. */
    public Object getCurrent () {
        return records.get (currentRecord);
    }
    
    /** Returns the value of the record pointer */
    public int getPosition () {
        return currentRecord;
    }
    
    /** Changes the Major Classification of the current record */
    public void setMajorClass(int cl) {
        CrystalData cd = (CrystalData) records.get(currentRecord);
        cd.setMajorClass(cl);
    }
    
    /** Changes the comment of the current record */
    public void setComment(String comment) {
        CrystalData cd = (CrystalData) records.get(currentRecord);
        cd.setComments(comment);
    }
        
    public void close () {
    }
    
    /** Import a crystal database from a formatted text file. */
    public void importDB () {
        
        // Open a file dialog to get the text file to import
        File diskFile = null;
        JFileChooser fc = new JFileChooser ();
        fc.setDialogTitle ("Open Crystal Database");
        int returnVal = fc.showDialog (new JFrame (), "Open");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            diskFile = fc.getSelectedFile ();
        }
        InputStream is = null;
        try {
            is = new FileInputStream (diskFile);
        } catch (Exception e) {}
        Reader r = new BufferedReader (new InputStreamReader (is));
        StreamTokenizer st = new StreamTokenizer (r);
        
        // Set up the parameters for parsing
        st.eolIsSignificant (false);
        st.whitespaceChars (',',',');
        st.quoteChar (QUOTE);
        
        // Begin reading records
        process:
            while (true) {
                CrystalData record = new CrystalData ();
                try {
                    // Read Index
                    switch(st.nextToken ()) {
                        case StreamTokenizer.TT_WORD:
                            // Index is set by default for each record
                            // record.setIndex((int)st.nval);
                            break;
                        case StreamTokenizer.TT_EOF:
                            break process;
                    }
                    // Read File ID
                    switch(st.nextToken ()) {
                        case StreamTokenizer.TT_EOF:
                            break process;
                            default:
                                record.setImageDirNumber (Integer.valueOf (st.sval).intValue ());
                                break;
                    }
                    // Read Protein
                    switch(st.nextToken ()) {
                        case StreamTokenizer.TT_EOF:
                            break process;
                            default:
                                record.setProtein (st.sval);
                                break;
                    }
                    // Read Concentration
                    switch(st.nextToken ()) {
                        case StreamTokenizer.TT_EOF:
                            break process;
                            default:
                                StringTokenizer concen = new StringTokenizer (st.sval);
                                record.setConcentration (Integer.valueOf (concen.nextToken ()).intValue ());
                                break;
                    }
                    
                    // Read Tray Date
                    switch(st.nextToken ()) {
                        case StreamTokenizer.TT_EOF:
                            break process;
                            default:
                                record.setTrayDate (st.sval);
                                break;
                    }
                    
                    // Read Tray Number
                    switch(st.nextToken ()) {
                        case StreamTokenizer.TT_EOF:
                            break process;
                            default:
                                record.setTray (st.sval);
                                break;
                    }
                    
                    // Read Screen Number
                    switch(st.nextToken ()) {
                        case StreamTokenizer.TT_EOF:
                            break process;
                            default:
                                record.setScreen (Integer.valueOf (st.sval).intValue ());
                                break;
                    }
                    
                    // Read Well
                    switch(st.nextToken ()) {
                        case StreamTokenizer.TT_EOF:
                            break process;
                            default:
                                record.setWell (st.sval);
                                break;
                    }
                    
                    // Read Entry Date
                    switch(st.nextToken ()) {
                        case StreamTokenizer.TT_EOF:
                            break process;
                            default:
                                record.setEntryDate (st.sval);
                                break;
                    }
                    
                    // Read Major Classification
                    switch(st.nextToken ()) {
                        case StreamTokenizer.TT_EOF:
                            break process;
                            default:
                                record.setMajorClass (Integer.valueOf (st.sval).intValue ());
                                break;
                    }
                    
                    // Read Minor Classification
                    switch(st.nextToken ()) {
                        case StreamTokenizer.TT_EOF:
                            break process;
                        case StreamTokenizer.TT_WORD:
                            record.setMinorClass (Integer.valueOf (st.sval).intValue ());
                            break;
                        default:
                            break;
                    }
                    
                    // Read Comments
                    switch(st.nextToken ()) {
                        case StreamTokenizer.TT_EOF:
                            break process;
                        default:
                            record.setComments (st.sval);
                            break;
                    }
                 
                } catch (IOException e) {
                    // Stop reading if any of the above failed to process
                    break process;
                }
                
                // Add the record to the database
                records.add (record);
            }
            try {
                is.close ();
            } catch (IOException e) {
                System.out.println ("Couldn't close import file.");
            }
    }
    
    /** Exports the current database to a formatted text file */
    public void exportFile() {
        
        // Get the file name to export to
        File export = null;
        JFileChooser fc = new JFileChooser ();
        fc.setDialogTitle ("Export Database");
        int returnVal = fc.showDialog (new JFrame (), "Export");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            export = fc.getSelectedFile ();
        }
        FileWriter fw = null;
        try {
            fw = new FileWriter(export);
        } catch (Exception e) {}
        
        // Begin exporting 
        for (int i=0; i < records.size(); i++) {
            try {
                // Get a record from the database    
                CrystalData cd = (CrystalData)records.get(i);
                
                // Use a StringBuffer to create the output string for a record
                StringBuffer s = new StringBuffer();
                String quote = "\"";
                String comma = ",";
                s.append(quote + cd.getIndex() + quote + comma);
                s.append(quote + cd.getImageDirNumber() + quote + comma);
                s.append(quote + cd.getProtein() + quote + comma);
                s.append(quote + cd.getConcentration() + quote + comma);
                s.append(quote + cd.getTrayDate() + quote + comma);
                s.append(quote + cd.getTray() + quote + comma);
                s.append(quote + cd.getScreen() + quote + comma);
                s.append(quote + cd.getWell() + quote + comma);
                s.append(quote + cd.getEntryDate() + quote + comma);
                s.append(quote + cd.getMajorClass() + quote + comma);
                s.append(quote + cd.getMinorClass() + quote + comma);
                s.append(quote + cd.getComments() + quote);
       
                // Write the formatted string to the text file
                fw.write(s.toString() + "\n");
            } catch (Exception e) {}
        }
        try {
            fw.close();
        } catch (Exception e) {}
    }
    
    /** Loads a database from a serialized Object file (CrystalData objects) */
    public void loadFile() {
        JFileChooser fc = new JFileChooser ();
        fc.setDialogTitle ("Open Crystal Database");
        fc.setFileFilter(new DBFilter());
        int returnVal = fc.showDialog (new JFrame (), "Open");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            diskFile = fc.getSelectedFile ();
        }

        InputStream is = null;
        ObjectInputStream s = null;
        try {
            is = new FileInputStream (diskFile);
            s = new ObjectInputStream(is);
        } catch (Exception e) {
            System.out.println("Couldn't get input streams.");
        }
        /* Create a new Arraylist to store the records */
        records = new ArrayList<CrystalData> ();
        readProcess:
            while (true) {
                try {
                    CrystalData cd = new CrystalData ();
                    cd = (CrystalData) s.readObject();
                    records.add(cd);
                } catch (IOException e) {
                    System.out.println("Finished reading database - " 
                                       + records.size() + "read.");
                    break readProcess;
                } catch (Exception e) {
                    System.out.println("Error reading database.");
                    break readProcess;
                }
            }
        try {
            s.close();
            is.close();
        } catch (Exception e) {
            System.out.println("Couldn't close database.");
        }
    }
    
    /** Saves the current database to a serialized (CrystalData) Object file */
    public void saveFile () {
        if (diskFile == null) {
            JFileChooser fc = new JFileChooser ();
            fc.setDialogTitle ("Save Crystal Database");
            int returnVal = fc.showDialog (new JFrame (), "Save");
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                diskFile = fc.getSelectedFile ();
            }
        }
        
        OutputStream os = null;
        ObjectOutputStream s = null;
        try {
            os = new FileOutputStream (diskFile);
            s = new ObjectOutputStream(os);
        } catch (Exception e) {}
        // Begin writing records
        for (int i = 0; i < records.size(); i++) {
            try {
                s.writeObject(records.get(i));
            } catch (IOException e) {
                break;
            } catch (Exception e) {
                break;
            }
        }
        try {
            s.flush();
            s.close();
            os.close();
        } catch (Exception e) {}
    }

    /** Returns the number of records in the database */
    public int size () {
        return records.size ();
    }
    
    class DBFilter extends javax.swing.filechooser.FileFilter {
        public boolean accept(File f) {
            return f.isDirectory() || f.getName().toLowerCase().endsWith(".cdb");
        }
        
        public String getDescription() {
            return "Databases (*.cdb)";
        }
    }
}