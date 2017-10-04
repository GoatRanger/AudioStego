/*
 * Constants.java
 *
 * Created on February 27, 2001, 7:42 PM
 */

package imageproc.utilities;

import java.awt.Rectangle;

/**
 *
 * @author  Default
 * @version 
 */
public class Constants extends java.lang.Object {

    public static final int class_clear = 0;
    
    public static final int class_precip = 1;
    
    public static final int class_edge = 2;

    public static final int class_crystal = 3;
    
    public static final int NUMBER_OF_CLASSES = 4;
    
    public static final int GLOBAL_CLASSES = 4;
    
    public static final int CLEAR = 0;
    
    public static final int PRECIP = 1;
    
    public static final int MICRO = 2;
    
    public static final int CRYSTAL = 3;
    
    /** The dimensions of a region (square) */
    public static final int REGION_SIZE = 16;
    
    /** The number of regions across an image */
    public static final int REGIONS_HORIZ = 40;
    
    /** The number of regions vertically in an image */
    public static final int REGIONS_VERTICAL = 30;
    
    public static final Rectangle[] rect = new Rectangle [Constants.REGIONS_HORIZ 
							                              * Constants.REGIONS_VERTICAL];
    static {
    	for (int horiz = 0; horiz < Constants.REGIONS_HORIZ; horiz++) {
            for (int vert = 0; vert < Constants.REGIONS_VERTICAL; vert++) {
                rect[horiz*Constants.REGIONS_VERTICAL+vert] = new Rectangle (
                                        horiz*Constants.REGION_SIZE,
                                        vert * Constants.REGION_SIZE,
                                        Constants.REGION_SIZE, 
                                        Constants.REGION_SIZE);
            }
        }
    }
    /** Creates new Constants */
    public Constants() {
    }
    /**
     * Create an array of rectangles that form a grid over the input image.
     * Can be used for regional image processing (ROI).
     */
    protected void calcRectangleArray () {
        
        
    }
    public static String globalName (int id) {
        String s = "";
        switch (id) {
            case CLEAR:
                s = "Clear";
                break;
            case PRECIP:
                s = "Precipitate";
                break;
            case MICRO:
                s = "Microcrystal";
                break;
            case CRYSTAL:
                s = "Crystal";
                break;
        }
        return s;
    }
}
