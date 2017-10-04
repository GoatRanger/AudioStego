/*
 * Classification.java
 *
 * Created on February 27, 2001, 7:28 PM
 */

package imageproc.utilities;

/**
 *
 * @author  Karl A. Gossett
 * @version
 */
import imageproc.utilities.Constants;

public class DecisionNode extends java.lang.Object {
    
    private String key;
    
    private int clearCount;
    
    private int crystalCount;
    
    private int edgeCount;
    
    private int precipCount;
    
    private int totalCount;
    
    /** Creates new Classification */
    public DecisionNode() {
        key = "empty";
        clearCount = 0;
        crystalCount = 0;
        edgeCount = 0;
        precipCount = 0;
        totalCount = 0;
    }
    
    public DecisionNode(String k, int clear,int crystal,int edge,int precip) {
        if (clear < 0) clear = 0;
        if (crystal < 0) crystal = 0;
        if (edge < 0) edge = 0;
        if (precip < 0) precip = 0;
        
        key = k;
        clearCount = clear;
        crystalCount = crystal;
        edgeCount = edge;
        precipCount = precip;
        totalCount = clear + crystal + edge + precip;
    }
    
    public String getKey () {
        return key;
    }
    
    public int countTotal() {
        return totalCount;
    }
    
    public int countClear() {
        return clearCount;
    }
    
    public int countCrystal() {
        return crystalCount;
    }
    
    public int countEdge() {
        return edgeCount;
    }
    
    public int countPrecip() {
        return precipCount;
    }
    
    public double clearProb() {
        double prob = 0;
        if (totalCount > 0) {
            prob = (double)clearCount/(double)totalCount;
        }
        return prob;
    }
    
    public double crystalProb() {
        double prob = 0;
        if (totalCount > 0) {
            prob = (double)crystalCount/(double)totalCount;
        }
        return prob;
    }
    
    public double edgeProb() {
        double prob = 0;
        if (totalCount > 0) {
            prob = (double)edgeCount/(double)totalCount;
        }
        return prob;
    }
    
    public double precipProb() {
        double prob = 0;
        if (totalCount > 0) {
            prob = (double)precipCount/(double)totalCount;
        }
        return prob;
    }
    
    public void set(int clear,int crystal,int edge,int precip) {
        if (clear < 0) clear = 0;
        if (crystal < 0) crystal = 0;
        if (edge < 0) edge = 0;
        if (precip < 0) precip = 0;
        clearCount = clear;
        crystalCount = crystal;
        edgeCount = edge;
        precipCount = precip;
    }
    
    public int getCategory () {
        int cl = Constants.class_clear;
        
        int temp = clearCount;
        if (precipCount > temp) {
            temp = precipCount;
            cl = Constants.class_precip;
        }
        if (edgeCount > temp) {
            temp = edgeCount;
            cl = Constants.class_edge;
        }
        if (crystalCount > temp) {
            cl = Constants.class_crystal;
        }
        
        return cl;
    }
}
