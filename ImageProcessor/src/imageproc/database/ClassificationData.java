/*
 * ClassificationData.java
 *
 * Created on April 15, 2001, 11:51 AM
 */

package imageproc.database;
import java.awt.Point;
/**
 *
 * @author  Default
 * @version 
 */
public class ClassificationData extends java.lang.Object {
    
    /** Stores the classifications for each of the subregions in the image */
    int[] regionClasses;
    
    /** Stores a string identifier index to the decision tree for each region */
    String[] nodeList;
    
    /** Stores the euclidean center of each detected regional category */
    Point[] classCenter;
    
    /** Stores the average distance to the center of each regional category */
    double[] averageDistToCenter;
    
    /** Stores the number of regions in each category for the current image */
    int[] classFrequency;
    
    /** Stores the standard deviation of the class center for each category */
    double[] classCenterStdev;
    
    /** Stores a component standard deviation for each category center */
    Point[] directionalStdev;
    
    /** Stores the probability of each major class for this image.  This
     * provides the final classification for the image.
     */
    double[] classProbability;

    /** Creates new ClassificationData */
    public ClassificationData () {
    }

    public boolean equals (java.lang.Object obj) {
        return false;
    }
    
    public java.lang.String toString () {
        return "empty";
    }
    
}
