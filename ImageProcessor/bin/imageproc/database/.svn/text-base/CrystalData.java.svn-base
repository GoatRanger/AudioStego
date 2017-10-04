/*
 * CrystalData.java
 *
 * Created on March 29, 2001, 11:46 PM
 */

package imageproc.database;

/**
 *
 * @author  Default
 * @version
 */
import java.io.*;

public class CrystalData extends java.lang.Object implements Serializable {
    
    public static final int CLEAR = 1;
    
    public static final int PRECIPITATE = 2;
    
    public static final int MICROCRYSTAL = 3;
    
    public static final int CRYSTAL = 4;
    
    private String comments;
    
    private int concentration;
    
    private String entryDate;
    
    private int imageDirNumber;
    
    private int index;
    
    private int majorClass;
    
    private int minorClass;
    
    private String protein;
    
    private int screen;
    
    private String tray;
    
    private String trayDate;
    
    private int volume;
    
    private String well;
    
    private static int rollingIndex;
    
    {
        rollingIndex = 0;
    }
    /** Creates new CrystalData */
    public CrystalData () {
        index = rollingIndex++;
    }
    
    public java.lang.String toString () {
        String s = "Index: " + index + ", Protein: " + protein
        + ", (" + concentration + "mg/ml, " + volume
        + "ul)\n  Screen: HS" + screen + "  Tray: " + tray + ", Well: " + well
        + ", Tray Date: " + trayDate + ", Image Date: " + entryDate
        + ", Major class: " + majorName (majorClass)
        + " Minor class: " + minorName (minorClass) + "\n  Comments:"
        + comments;
        return s;
    }
    
    public int hashCode () {
        return index % 24;
    }
    
    public boolean equals (java.lang.Object obj) {
        boolean eql = false;
        if (obj.getClass ().equals (this.getClass ())) {
            if (((CrystalData)obj).index == this.index) {
                eql = true;
            }
        }
        return eql;
    }
    
    public String getComments () {
        return comments;
    }
    
    public int getConcentration () {
        return concentration;
    }
    
    public String getEntryDate () {
        return entryDate;
    }
    
    public int getImageDirNumber () {
        return imageDirNumber;
    }
    
    public int getIndex () {
        return index;
    }
    
    public int getMajorClass () {
        return majorClass;
    }
    
    public int getMinorClass () {
        return minorClass;
    }
    
    public String getProtein () {
        return protein;
    }
    
    public int getScreen() {
        return screen;
    }
    
    public String getTray () {
        return tray;
    }
    
    public String getTrayDate () {
        return trayDate;
    }
    
    public int getVolume () {
        return volume;
    }
    
    public String getWell () {
        return well;
    }
    
    public String majorName(int major) {
        String s;
        switch (major) {
            case CLEAR:
                s = "Clear";
                break;
            case PRECIPITATE:
                s = "Precipitate";
                break;
            case MICROCRYSTAL:
                s = "Microcrystal";
                break;
            case CRYSTAL:
                s = "Crystal";
                break;
            default:
                s = "Unknown";
                break;
        }
        return s;
    }
    
    public String minorName(int minor) {
        return Integer.toString(minor);
    }
    
    public void setComments (java.lang.String comment) {
        comments = comment;
    }
    
    public void setConcentration (int concentration) {
        this.concentration = concentration;
    }
    
    public void setEntryDate (java.lang.String date) {
        entryDate = date;
    }
    
    public void setImageDirNumber (int dirNumber) {
        imageDirNumber = dirNumber;
    }
    
    public void setMajorClass (int majorClass) {
        this.majorClass = majorClass;
    }
    
    public void setMinorClass (int minorClass) {
        this.minorClass = minorClass;
    }
    
    public void setProtein (java.lang.String protein) {
        this.protein = protein;
    }
    
    public void setScreen (int screen) {
        this.screen = screen;
    }
    
    public void setTray (java.lang.String tray) {
        this.tray = tray;
    }
    
    public void setTrayDate (java.lang.String date) {
        trayDate = date;
    }
    
    public void setVolume (int volume) {
        this.volume = volume;
    }

    public void setWell (java.lang.String well) {
        this.well = well;
    }
    
}
