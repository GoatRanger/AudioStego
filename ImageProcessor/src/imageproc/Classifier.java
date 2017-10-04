/*
 * Classifier.java
 *
 * Created on February 27, 2001, 7:57 PM
 */

package imageproc;

/**
 * Performs classification of an image, either at a regional level or overall
 * classification.  Uses a pre-generated Decision tree as input for regional
 * classification.  The decision tree was trained using a set of training
 * examples that were divided into regions, then the tree was trained to
 * recognize regions based on pixel statistics, both before and after performing
 * edge detection.
 *<pre>
 * The image classifier uses a set of rules to develop a classification.  The
 * regional classifications above are used in to develop regional statistics
 * (location, frequency, geometric center, etc) of the different regional
 * categories.  The rules are then applied to these statistics to develop an
 * expected classification for the overall image.
 *
 * @author  Karl A. Gossett
 * @version 0.1
 */
import java.io.*;
import java.awt.Point;
import java.util.*;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import ai.subjlogic.Opinion;

import imageproc.utilities.*;

public class Classifier extends java.lang.Object {
    
    /** The node list with results from the Quest decision tree */
    Hashtable<String, DecisionNode> decisionTree;
    
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
    Opinion clearOpinion;
    Opinion precipOpinion;
    Opinion microOpinion;
    Opinion crystalOpinion;
    
    /** Stores the narrative for the overall classification.  Used to explain
     * the reasoning behind the classification
     */
    StringBuffer narrative;
    
    /** The decision tree file */
    File dtFile = null;
    
    /** Temporary statistics of the current image */
    //private String tempStats;
    
    private FileOutputStream logfile = null;
    private PrintWriter log = null;
    
    private int imagesProcessed = 0;
    
    /** The prior probability of each category, based on frequency.
     * <p>
     * Four vectors: Clear, Precipitate, Microcrystal, Crystal
     */
    double[][] belief
    = {{0.85d, 0.75d, 0.70d, 0.60d, 0.45d, 0.20d},      // Clear
    {0.85d, 0.80d, 0.75d, 0.75d, 0.60d, 0.30d},         // Precipitate
    {0.40d, 0.60d, 0.20d},                              // Microcrystal
    {0.55d, 0.70d, 0.70d, 0.75d, 0.65d, 0.60d, 0.50d, 0.30d}}; // Crystal
    
    /** The disbelief of each class based on frequency */
    double[][] disbelief
    = {{0.05d, 0.20d, 0.25d, 0.35d, 0.40d, 0.70d},     // Clear
    {0.05d, 0.15d, 0.15d, 0.15d, 0.30d, 0.60d},          // Precipitate
    {0.30d, 0.15d, 0.65d},                            // Microcrystal
    {0.40d, 0.25d, 0.2d, 0.1d, 0.15d, 0.4d, 0.45d, 0.50d}};    // Crystal
    
    /** The frequency margins used to determine the prior probabilities.
     * @see #belief
     */
    int[][] priorMargin
    = {{1100, 1050, 1030, 1000, 900},   // Clear
    { 350,  200,  120,   90,  55},      // Precipitate
    { 300,  120},                       // Microcrystal (based on crystal freq)
    { 130,  110, 80, 30, 20,  10, 8}};    // Crystal
    
    
    /** Creates new Classifier */
    public Classifier () {
        decisionTree = new Hashtable<String, DecisionNode> (160,0.9f);
        initDecisionTree ();
        CrystalClassifier.myLog.info ("Classifier Initialization Completed");
    }
    
    /** Classifies the regions based on the provided statistics.
     *
     * @param edgeMean         The mean pixel value from the edge detection
     * @param edgeMax          The max pixel value of each region (edge det)
     * @param originalMean     The mean pixel value of the region (orig. image)
     * @param originalMin      The minimum pixel value from the original region
     * @param originalStdev    The standard deviation of pixel intensity
     */
    public void classifyRegions (double[] edgeMean, double[] edgeMax,
    double[] originalMean, double[] originalMin,
    double[] originalStdev) {
        
        nodeList = new String[originalStdev.length];
        regionClasses = new int[originalStdev.length];
        for (int q = 0; q < regionClasses.length; q++) {
            // Get the correct decision tree node for the statistics provided
            nodeList[q] = getNode (edgeMean[q], edgeMax[q], originalMean[q],
            originalMin[q], originalStdev[q]);
            
            // Use this node to get the classification of the image
            regionClasses[q] = classifyRegion (nodeList[q]);
        }
        
        /* Once the initial regional classifications are made, collect
         * spatial statistics for each classification.  Based on these
         * statistics, adjust the classification of some of the regions
         * (those regions that appear to be outside the drop)
         * and recollect the spatial statistics based on the reclassifications.
         */
        //tempStats = new String ();
        narrative = new StringBuffer (150);
        spatialStats ();
        checkRegions ();
        // Recompute statistics based on reclassifications
        spatialStats ();
    }
    
    /** Finds the node that classifies as clear, but with maximal probability
     * of the specified class (so most likely misclassified as specified class)
     *
     * @param cl        The class to find maximal clear node for
     *
     * @return          The name of the node found
     */
    String minMisClassNode (int cl) {
        double min = 0.0d;
        String name = "";
        for (int n=0; n < nodeList.length; n++) {
            DecisionNode dn = (DecisionNode) decisionTree.get (nodeList[n]);
            double temp = 0.0d;
            
            // If this is a clear node, then a candidate for reassignment target
            try {
                if (dn.getCategory () == Constants.class_clear) {
                    switch (cl) {
                        case Constants.class_clear:
                            temp = dn.clearProb ();
                            break;
                        case Constants.class_precip:
                            temp = dn.precipProb ();
                            break;
                        case Constants.class_edge:
                            temp = dn.edgeProb ();
                            break;
                        case Constants.class_crystal:
                            temp = dn.crystalProb ();
                            break;
                    }
                    if (temp > min) {
                        min = temp;
                        name = new String (nodeList[n]);
                    }
                }
            } catch (Exception e) {
                System.out.println ("failed");
                System.out.println ("Class Requested: " + cl + ", n = " + n
                + ", nodeList[n] = " + nodeList[n] + ", min = " + min);
                System.exit (2);
            }
            
        }
        return name;
    }
    
    /** Finds the node that classifies as crystal, but with maximal probability
     * of the edge (most likely node to misclassify edges as crystals)
     *
     * @return          The name of the node found
     */
    String minMisclassCrystalNode () {
        double min = 0.0d;
        String name = "";
        for (int n=0; n < nodeList.length; n++) {
            DecisionNode dn = (DecisionNode) decisionTree.get (nodeList[n]);
            double temp = 0.0d;
            
            // If this is a crystal node, then a candidate for reassignment target
            try {
                if (dn.getCategory () == Constants.class_crystal) {
                    temp = dn.edgeProb ();
                    
                    if (temp > min) {
                        min = temp;
                        name = new String (nodeList[n]);
                    }
                }
            } catch (Exception e) {
                System.out.println ("failed to change edge -> crystal");
                System.out.println (" n = " + n + ", nodeList[n] = "
                + nodeList[n] + ", min = " + min);
                System.exit (2);
            }
            
        }
        return name;
    }
    
    
    /** Finds the node that classifies as edge, but with maximal probability
     * of the crystal (most likely node to misclassify crystals as edges)
     *
     * @return          The name of the node found
     */
    String minMisclassEdgeNode () {
        double min = 0.0d;
        String name = "";
        for (int n=0; n < nodeList.length; n++) {
            DecisionNode dn = (DecisionNode) decisionTree.get (nodeList[n]);
            double temp = 0.0d;
            
            // If this is a crystal node, then a candidate for reassignment target
            try {
                if (dn.getCategory () == Constants.class_edge) {
                    temp = dn.crystalProb ();
                    
                    if (temp > min) {
                        min = temp;
                        name = new String (nodeList[n]);
                    }
                }
            } catch (Exception e) {
                System.out.println ("failed to change crystal -> edge");
                System.out.println (" n = " + n + ", nodeList[n] = "
                + nodeList[n] + ", min = " + min);
                System.exit (2);
            }
            
        }
        return name;
    }
    
    /**
     * Verify the classification of precipitate.  If there are widely
     * dispersed precipitate, then likely that the most distant ones are
     * dirt, not actual precipitate, so reclassify as clear.
     */
    void checkRegions () {
        // Update regional classifications for precipitate
        int q = 0;
        
        // Find the node that is closest to classifying edges as crystals
        String minCrystalNode = minMisclassCrystalNode ();
        String minEdgeNode = minMisclassEdgeNode ();
        
        boolean changedPrecip = false;
        boolean changedCrystal = false;
        boolean changedEdge = false;
        
        // Reclassify outlying precipitate
        int edgeThreshold = 35;
        for (int x = 0; x < Constants.REGIONS_HORIZ - 1; x++) {
            for (int y = 0; y < Constants.REGIONS_VERTICAL; y++) {
                double dist
                = classCenter[Constants.class_edge].distance (x
                * Constants.REGION_SIZE,
                y * Constants.REGION_SIZE);
                
                // If likely misclassfied as precipitate reclassify it
                if ((averageDistToCenter[Constants.class_edge]+10) >
                classCenterStdev[Constants.class_edge]
                && dist > (classCenterStdev[Constants.class_edge] * 1.5)
                && classFrequency[Constants.class_edge] > edgeThreshold
                && regionClasses[q] == Constants.class_precip) {
                    CrystalClassifier.myLog.info ("Changing region " + q + ": Distance - " + dist
                    + ", Stdev - " + classCenterStdev[Constants.class_edge]*1.5);
                    nodeList[q] = minMisClassNode (regionClasses[q]);
                    regionClasses[q] = Constants.class_clear;
                    changedPrecip = true;
                }
                
                // Now process for crystals
                // Check for edge proximity to small crystal--may be misclassified
                double crystalSize = 120;
                dist = classCenter[regionClasses[q]].distance (x
                        * Constants.REGION_SIZE, y * Constants.REGION_SIZE);
                double distCrystal
                        = classCenter[Constants.class_crystal].distance (x
                          * Constants.REGION_SIZE, y * Constants.REGION_SIZE);
                if (classCenterStdev[Constants.class_crystal] < crystalSize
                && regionClasses[q] == Constants.class_edge
                && classFrequency[Constants.class_crystal] > /* 4 */ 2
                && dist < (classCenterStdev[Constants.class_edge] * 2/3)
                && distCrystal < (classCenterStdev[Constants.class_crystal])) {
                    nodeList[q] = minCrystalNode;
                    regionClasses[q] = Constants.class_crystal;
                    CrystalClassifier.myLog.info ("Changing region *Crystal*" + q
                    + ": Distance - " + dist + ", Stdev(edge) - "
                    + classCenterStdev[regionClasses[q]]*2/3
                    + ", Stdev(cry) - "
                    + classCenterStdev[Constants.class_crystal]);
                    changedEdge = true;
                }
                
                // Check for outlying crystals --may be misclassified edges
                dist = classCenter[Constants.class_edge].distance (x *
                Constants.REGION_SIZE,
                y * Constants.REGION_SIZE);
                if (regionClasses[q] == Constants.class_crystal
                && (averageDistToCenter[Constants.class_edge]+10) >
                classCenterStdev[Constants.class_edge]
                && dist > classCenterStdev[Constants.class_edge]) {
                    nodeList[q] = minEdgeNode;
                    regionClasses[q] = Constants.class_edge;
                    CrystalClassifier.myLog.info ("Changing region *Edge*" + q + ": Distance - "
                    + dist + " (average-"
                    + averageDistToCenter[Constants.class_edge] + ")"
                    + ", Stdev(edge) - "
                    + classCenterStdev[Constants.class_edge]);
                    changedCrystal = true;
                }
                
                // Proceed to next region
                q++;
            }
        }
        
        // Add a comment to the narrative indicating reclassification
        if (changedPrecip) {
            narrative.append ("  Some precipitates detected appear to be"
            + " outside the boundary of the drop and were "
            + "recategorized.\n");
        }
        
        // Change made, so make a comment in the narrative
        if (changedEdge) {
            narrative.append ("  Some edges were detected in close "
            + "proximity to likely crystals and were recategorized.\n");
        }
        // Make narrative entry for reclassifying crystals as edges
        if (changedCrystal) {
            narrative.append ("  Some outlying regions were detected as "
            + "crystal, but seem to be edges and have been "
            + "recategorized.\n");
        }
    }
    
    /** Calculate all the regional spatial statistics to use when determining
     * overall classification.
     */
    void spatialStats () {
        int[] centerx = new int[Constants.NUMBER_OF_CLASSES];
        int[] centery = new int[Constants.NUMBER_OF_CLASSES];
        
        classCenter = new Point[Constants.NUMBER_OF_CLASSES];
        classFrequency = new int[Constants.NUMBER_OF_CLASSES];
        classCenterStdev = new double[Constants.NUMBER_OF_CLASSES];
        directionalStdev = new Point[Constants.NUMBER_OF_CLASSES];
        averageDistToCenter = new double[Constants.NUMBER_OF_CLASSES];
        
        // Empty all the arrays
        Arrays.fill (centerx,0);
        Arrays.fill (centery,0);
        Arrays.fill (classFrequency, 0);
        Arrays.fill (classCenterStdev, 0.0d);
        
        int q=0;
        for (int x = 0; x < Constants.REGIONS_HORIZ - 1; x++) {
            for (int y = 0; y < Constants.REGIONS_VERTICAL; y++) {
                centerx[regionClasses[q]] += x;
                centery[regionClasses[q]] += y;
                classFrequency[regionClasses[q]] += 1;
                q++;
            }
        }
        for (int i=0; i<Constants.NUMBER_OF_CLASSES; i++) {
            CrystalClassifier.myLog.info ("Count " + i + ": " + classFrequency[i]);
            
            if (classFrequency[i] > 0)
                classCenter[i] = new Point (centerx[i] / classFrequency[i]
                * Constants.REGION_SIZE,
                centery[i] / classFrequency[i]
                * Constants.REGION_SIZE);
            else classCenter[i] = new Point (0,0);
        }
        q = 0;
        double[] xStdev = new double[Constants.NUMBER_OF_CLASSES];
        double[] yStdev = new double[Constants.NUMBER_OF_CLASSES];
        Arrays.fill (xStdev,0.0d);
        Arrays.fill (yStdev,0.0d);
        for (int x = 0; x < Constants.REGIONS_HORIZ - 1; x++) {
            for (int y = 0; y < Constants.REGIONS_VERTICAL; y++) {
                Point xy = new Point (x * Constants.REGION_SIZE,
                y * Constants.REGION_SIZE);
                classCenterStdev[regionClasses[q]] +=
                classCenter[regionClasses[q]].distanceSq (xy);
                averageDistToCenter[regionClasses[q]] +=
                classCenter[regionClasses[q]].distance (xy);
                xStdev[regionClasses[q]] += Math.pow (x*Constants.REGION_SIZE
                - classCenter[regionClasses[q]].getX (), 2.0);
                yStdev[regionClasses[q]] += Math.pow (y*Constants.REGION_SIZE
                - classCenter[regionClasses[q]].getY (), 2.0);
                q++;
            }
        }
        for (int i = 0; i < Constants.NUMBER_OF_CLASSES; i++) {
            classCenterStdev[i] = Math.sqrt (classCenterStdev[i]
            / (classFrequency[i]-1));
            xStdev[i] = Math.sqrt (xStdev[i]
            / (classFrequency[i]-1));
            yStdev[i] = Math.sqrt (yStdev[i]
            / (classFrequency[i]-1));
            directionalStdev[i] = new Point ((int)xStdev[i], (int)yStdev[i]);
            averageDistToCenter[i] /= classFrequency[i];
        }
    }
    
    /** Returns the array of regional classifications */
    public int[] getRegionClasses () {
        return regionClasses;
    }
    
    /** Returns the array of the center of mass of each category */
    public Point[] getClassCenters () {
        return classCenter;
    }
    
    /** Returns the standard deviation of the center of each category */
    public double[] getCenterStdev () {
        return classCenterStdev;
    }
    
    /** Returns the component standard deviation */
    public Point[] getComponentStdev () {
        return directionalStdev;
    }
    
    /** Gets the File object for the Quest input file. Used by initDecisionTree
     * to specify the source for the decision tree.
     */
    private void getInputFile () {
        dtFile = null;
        JFileChooser fc = new JFileChooser ();
        fc.setFileSelectionMode (JFileChooser.FILES_ONLY);
        fc.setApproveButtonText ("Accept");
        fc.setDialogTitle ("Select Decision Tree Input File");
        fc.setFileFilter(new DTreeFilter());
        int returnVal = fc.showOpenDialog (new JFrame ());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            dtFile = fc.getSelectedFile ();
            System.out.println (fc.getSelectedFile ().getPath ());
            CrystalClassifier.myLog.info ("Decision Tree file: " + dtFile.toString ());
        }
    }
    
    /**
     * Gets the probability of a given class at the specified node.
     *
     * @param node       A string specifying the name of the decision node
     * @param category   The probability returned is the probability of this
     *                   category at this node.
     * @return           A double value indicating the probability of the
     *                   specified classification.
     *
     * @see imageproc.utilities.Constants
     */
    public double getProbability (String node, int category) {
        DecisionNode dn = (DecisionNode) decisionTree.get (node);
        double prob = 0;
        switch (category) {
            case Constants.class_clear:
                prob = dn.clearProb ();
                break;
            case Constants.class_precip:
                prob = dn.precipProb ();
                break;
            case Constants.class_crystal:
                prob = dn.crystalProb ();
                break;
            case Constants.class_edge:
                prob = dn.edgeProb ();
                break;
                default:
                    prob = 0;
                    break;
        }
        
        return prob;
    }
    
    /**
     * Classifies a region with the supplied statistics.  Returns a
     * classification from four classes in the Constants class
     *
     * @param edgeMean          The mean value of the edge strengths (Frei-chen)
     * @param edgeMax           The maximum detected edge strength
     * @param origMean          The mean intensity of the pixels in the original
     *                          (unprocessed) image
     * @param origMin           The minimum intensity of the pixels in the
     *                          unprocessed image
     * @param origStdev         The standard deviation of the pixel intensities
     *                          in the original image
     * @return                  The classification of this region.
     *
     * @see imageproc.utilities.Constants
     */
    protected int classifyRegion (double edgeMean, double edgeMax,
    double origMean, double origMin, double origStdev) {
        String node = getNode (edgeMean, edgeMax, origMean, origMin, origStdev);
        DecisionNode dn = (DecisionNode) decisionTree.get (node);
        return dn.getCategory ();
    }
    
    /**
     * Returns the classification of the specified node from the decision tree.
     *
     * @param node            A string identifier of the desired node (i.e.
     *                        "Node 34") from the Quest decision tree
     * @return                The classification specified by this node.
     */
    public int classifyRegion (String node) {
        DecisionNode dn = (DecisionNode) decisionTree.get (node);
        return dn.getCategory ();
    }
    
    @SuppressWarnings("unused")
	private String majorName (int major) {
        String s;
        switch (major) {
            case Constants.CLEAR:
                s = "Clear";
                break;
            case Constants.PRECIP:
                s = "Precipitate";
                break;
            case Constants.MICRO:
                s = "Microcrystal";
                break;
            case Constants.CRYSTAL:
                s = "Crystal";
                break;
                default:
                    s = "Unknown";
                    break;
        }
        return s;
    }
    
    /**
     * Finds the ID of the node that will classify the parameters specified.
     * Traverses the decision tree to find the terminal node that classifies
     * according to the provided statistics.
     *
     * @param edgeMean          The mean value of the edge strengths (Frei-chen)
     * @param edgeMax           The maximum detected edge strength
     * @param origMean          The mean intensity of the pixels in the original
     *                          (unprocessed) image
     * @param origMin           The minimum intensity of the pixels in the
     *                          unprocessed image
     * @param origStdev         The standard deviation of the pixel intensities
     *                          in the original image
     * @return                  The String identifier of the node.
     */
    /* Uses the decision tree file Dated 2/27/01, 9:21 PM
     * Need to modify so rules aren't hard coded
     */
    public String getNode (double edgeMean, double edgeMax, double origMean,
    double origMin,double origStdev) {
        String cl;
        // Set clear clusters
        if (edgeMean <= 8.576) {                           // Node 1
            if (origStdev <= 4.394) {                    // Node 2
                cl = "Node 4";
            } else if (origMean <= 109.6) {              // Node 5
                cl = "Node 136";
            } else if (origStdev <= 5.078) {             // Node 137
                cl = "Node 214";
            } else if (edgeMean <= 2.082) {                // Node 215
                if (origMean <= 126.3) {                 // Node 256
                    cl = "Node 258";
                } else {
                    cl = "Node 259";
                }
            } else if (origMean <= 132.0) {              // Node 257
                cl = "Node 274";
            } else if (origMean <= 149.9) {              // Node 275
                if (origStdev <= 6.008) {                // Node 312
                    if (origMean <= 140.8) {             // Node 314
                        cl = "Node 316";
                    } else {
                        cl = "Node 317";
                    }
                } else if (origStdev <= 7.54) {          // Node 315
                    if (origMin <= 113.5) {              // Node 326
                        cl = "Node 328";
                    } else {
                        cl = "Node 329";
                    }
                } else if (origStdev <= 8.54) {          // Node 327
                    cl = "Node 330";
                } else {
                    cl = "Node 331";
                }
            } else {
                cl = "Node 313";
            }
        } else if (origMin <= 82.5) {                    // Node 3
            if (edgeMean <= 42.07) {                       // Node 336
                if (origMin <= 27.5) {                   // Node 338
                    if (origMean <= 31.54) {             // Node 340
                        if (edgeMean <= 19.57) {           // Node 342
                            if (origMean <= 14.1) {      // Node 344
                                cl = "Node 346";
                            } else {
                                cl = "Node 347";
                            }
                        } else {
                            cl = "Node 345";
                        }
                    } else if (origMin < 0.5) {          // Node 343
                        if (origMean <= 106.0) {         // Node 348
                            cl = "Node 350";
                        } else if (origStdev <= 39.93) { // Node 351
                            cl = "Node 358";
                        } else {
                            cl = "Node 359";
                        }
                    } else if (edgeMax <= 81.5) {         // Node 349
                        cl = "Node 360";
                    } else if (origStdev <= 12.69) {     // Node 361
                        cl = "Node 364";
                    } else {
                        cl = "Node 365";
                    }
                } else if (edgeMax <= 108.5) {            // Node 341
                    if (edgeMax <= 80.5) {                // Node 368
                        cl = "Node 370";
                    } else if (edgeMean <= 14.41) {        // Node 371
                        cl = "Node 376";
                    } else if (origMean <= 117.0) {      // Node 377
                        cl = "Node 380";
                    } else {
                        cl = "Node 381";
                    }
                } else if (origStdev <= 13.87) {         // Node 369
                    if (origStdev <= 8.753) {            // Node 384
                        if (edgeMean <= 13.37) {           // Node 386
                            cl = "Node 388";
                        } else {
                            cl = "Node 389";
                        }
                    } else if (edgeMean <= 21.39) {        // Node 387
                        cl = "Node 390";
                    } else {
                        cl = "Node 391";
                    }
                } else if (edgeMax <= 170.5) {            // Node 385
                    if (origMin <= 73.5) {               // Node 400
                        if (origMin <= 113.4) {          // Node 402
                            if (edgeMax <= 124.5) {       // Node 404
                                cl = "Node 406";
                            } else {
                                cl = "Node 407";
                            }
                        } else {
                            cl = "Node 405";
                        }
                    } else {
                        cl = "Node 403";
                    }
                } else {
                    cl = "Node 423";
                }
            } else if (origMean <= 89.82) {              // Node 339
                if (edgeMean <= 64.99) {                   // Node 418
                    if (origStdev <= 33.88) {            // Node 420
                        cl = "Node 422";
                    } else {
                        cl = "Node 423";
                    }
                } else {
                    cl = "Node 421";
                }
            } else if (origStdev <= 33.03) {             // Node 419
                cl = "Node 438";
            } else if (edgeMean <= 67.72) {                // Node 439
                cl = "Node 456";
            } else if (origMin <= 0.5) {                 // Node 457
                cl = "Node 460";
            } else {
                cl = "Node 461";
            }
        } else if (edgeMean <= 15.59) {                    // Node 337
            if (origStdev <= 7.335) {                    // Node 462
                if (origMin <= 105.5) {                  // Node 464
                    cl = "Node 466";
                } else if (origMean <= 153.3) {          // Node 467
                    if (origStdev <= 6.275) {            // Node 480
                        if (edgeMean <= 9.354) {           // Node 482
                            cl = "Node 484";
                        } else {
                            cl = "Node 485";
                        }
                    } else {
                        cl = "Node 483";
                    }
                } else {
                    cl = "Node 481";
                }
            } else if (origStdev <= 11.51) {             // Node 465
                if (origMin <= 100.5) {                  // Node 492
                    if (origMin <= 94.5) {               // Node 494
                        if (edgeMax <= 112.0) {           // Node 496
                            cl = "Node 498";
                        } else {
                            cl = "Node 499";
                        }
                    } else {
                        cl = "Node 497";
                    }
                } else if (origMin <= 144.3) {           // Node 495
                    cl = "Node 504";
                } else if (origMean <= 149.8) {          // Node 505
                    cl = "Node 510";
                } else {
                    cl = "Node 511";
                }
            } else if (edgeMax <= 68.0) {                 // Node 493
                cl = "Node 512";
            } else {
                cl = "Node 513";
            }
        } else if (edgeMax <= 131.5) {                    // Node 463
            cl = "Node 514";
        } else if (origMean <= 146.7) {                  // Node 515
            if (edgeMean <= 29.23) {                       // Node 532
                if (origStdev <= 10.37) {                // NOde 534
                    cl = "Node 536";
                } else {
                    cl = "Node 537";
                }
            } else {
                cl = "Node 535";
            }
        } else if (edgeMax <= 253.0) {                    // Node 533
            cl = "Node 550";
        } else {
            cl = "Node 551";
        }
        
        return cl;
    }
    
    /** Calculates the prior probability of each class according to the region
     * frequencies.
     */
    double[] calcPriors () {
        double[] tempProb = new double[Constants.GLOBAL_CLASSES];
        for (int cl = 0; cl < Constants.GLOBAL_CLASSES; cl++) {
            boolean marginFound = false;
            int freq = cl;
            
            // if microcrystal, use crystal frequency for priors
            if (cl == Constants.MICRO) {
                freq = Constants.CRYSTAL;
            }
            
            // Get the priors from the prior probability table
            for (int margin = 0; margin < priorMargin[cl].length; margin++) {
                if (classFrequency[freq] >= priorMargin[cl][margin]) {
                    tempProb[cl] = belief[cl][margin];
                    marginFound = true;
                    break;
                }
            }
            
            // If wasn't found, assume frequency is less than all margins
            if (!marginFound) {
                tempProb[cl] = belief[cl][priorMargin[cl].length];
            }
        }
        return tempProb;
    }
    
    /** Returns the a priori opinion of a class, based solely on frequency */
    Opinion priorOpinion (int cl) {
        Opinion opinion = new Opinion ();
        
        boolean marginFound = false;
        int freq = cl;
        if (cl == Constants.MICRO) freq = Constants.CRYSTAL;
        // Get the priors from the prior probability table
        for (int margin = 0; margin < priorMargin[cl].length; margin++) {
            if (classFrequency[freq] >= priorMargin[cl][margin]) {
                opinion.change (belief[cl][margin],disbelief[cl][margin],
                (1.0-(belief[cl][margin]+disbelief[cl][margin])));
                marginFound = true;
                break;
            }
        }
        
        // If didn't find a margin, it must be below smallest margin
        if (!marginFound) {
            int last = priorMargin[cl].length;
            opinion.change (belief[cl][last],disbelief[cl][last],
            (1.0-(belief[cl][last]+disbelief[cl][last])));
        }
        return opinion;
    }
    
    public void finalize () throws Throwable {
        try {
            logfile.flush ();
            logfile.close ();
        } catch (Exception e)
        {}
        super.finalize ();
    }
    
    Opinion getClassOpinion (int cl) {
        Opinion recommend = new Opinion ();
        // Calculate number of regions,
        int numRegions = nodeList.length;
        //boolean isFirst = true;
        double prob = 0;
        double count = 0;
        // If a crystal detected, equal belief that it is a microcrystal
        if (cl == Constants.MICRO) cl = Constants.CRYSTAL;
        for (int i = 0; i < numRegions; i++) {
            if (regionClasses[i] == cl) {
                prob += getProbability (nodeList[i],regionClasses[i]);
                count += 1;
            }
        }
        double avgProb = 0.0;
        if (count > 0) {
            avgProb = prob/count;
        }
        recommend.change (avgProb, (1.0-avgProb) / 2.0, (1.0-avgProb) / 2.0);
        // Assume 70% correctness, so get consensus between assumption and
        // expected from the decision tree
        if (count > 5) recommend = recommend.consensus (new Opinion (0.7,0.2,0.1));
        return recommend;
    }
    
    /**
     * Returns the classification of the image.
     */
    public int classify () {
        // Setup Files
        
        if (logfile == null) {
            try {
                logfile = new FileOutputStream ("classification.log");
            } catch (Exception e) {
                e.printStackTrace ();
                return 0;
            }
            log = new PrintWriter (logfile, true);
        }
        
        log.println ("------------------------------------");
        log.println ("Image " + imagesProcessed);
        imagesProcessed++;
        /* Adjust thresholds based on image properties */
        
        /* Rule 0: Use prior probability of each class.
         *    Implementation:  Calculate probabilities based on initial region
         *           detection.  Use the average probability of each region
         *           from the decision tree true rate, and adjust initial
         *           likelihood.
         */
        
        classProbability = calcPriors ();
        clearOpinion = priorOpinion (Constants.CLEAR);
        precipOpinion = priorOpinion (Constants.PRECIP);
        microOpinion = priorOpinion (Constants.MICRO);
        crystalOpinion = priorOpinion (Constants.CRYSTAL);
        
        /* Rule 1:  If a region is difficult to identify, then the associated
         *         classification is less certain.  The decision tree (QUEST)
         *         establishes a probability of misclassification based on
         *         the correctness of each node in the tree.
         *    Implementation: Calculate the average correctness probability
         *         across all regions of each class, and adjust the initial
         *         classes based on this average probability.
         */
        log.println ("\nInitial\n-------\nClear: "
        + clearOpinion.toString () + "\n"
        + "Precipitate: " + precipOpinion.toString () + "\n"
        + "MicroCrystal: " + microOpinion.toString () + "\n"
        + "Crystal: " + crystalOpinion.toString ());
        clearOpinion
        = getClassOpinion (Constants.CLEAR).recommendation (clearOpinion);
        precipOpinion
        = getClassOpinion (Constants.PRECIP).recommendation (precipOpinion);
        microOpinion
        = getClassOpinion (Constants.MICRO).recommendation (microOpinion);
        crystalOpinion
        = getClassOpinion (Constants.CRYSTAL).recommendation (crystalOpinion);
        
        log.println ("\nRecommended\n-------\nClear: "
        + clearOpinion.toString () + "\n"
        + "Precipitate: " + precipOpinion.toString () + "\n"
        + "MicroCrystal: " + microOpinion.toString () + "\n"
        + "Crystal: " + crystalOpinion.toString ());
        
        /* Rule 2:  If clear frequency is questionable, crystal is very low,
         *          and precip detected is in lower part of possible
         *          precipitate, then image is possibly a very rounded clear
         *          drop that is just being misdetected due to light
         *          reflections on the surface.
         *    Implementation: If 950 < Clear Frequency < 1000,
         *         Crystal Freq < 10, and Precip Freq < 150,
         *         increase clear probability.
         */
        if ((classFrequency[Constants.class_clear] < 1000)
        && (classFrequency[Constants.class_clear] > 950)
        && (classFrequency[Constants.class_crystal] < 10)
        && (classFrequency[Constants.class_precip] < 150)) {
            clearOpinion = clearOpinion.consensus (new Opinion (0.7,0.15,0.25));
            precipOpinion = precipOpinion.consensus (new Opinion (0.6, 0.15, 0.25));
            classProbability[Constants.CLEAR] += 0.15;
            CrystalClassifier.myLog.config ("*** Rule 2 ***");
            log.println ("Rule 2\n-------\nClear: "
            + clearOpinion.toString () + "\n"
            + "Precipitate: " + precipOpinion.toString () + "\n"
            + "MicroCrystal: " + microOpinion.toString () + "\n"
            + "Crystal: " + crystalOpinion.toString ());
            String s = "Regions detected indicate not clear, but low crystal "
            + "and precipitate frequency indicates this might be"
            + " a well defined clear drop.";
            log.println (s);
            narrative.append (s+ "\n");
        }
        
        /* Rule 3:  If large quantity of crystal detected and
         *      as likely, then this is an indication of "sparkle"--the
         *      formation of very small (potentially) microcrystals that reflect
         *      light like a crystal might, but are too small to identify
         *      positiviely.  Usually indicates precipitate as well.
         *   Implementation:
         *       If crystal count is in the microcrystal range and precipitate
         *       is above prior probability margin 3 (80%), then adjust beliefs.
         */
        if ((classFrequency[Constants.class_crystal] >
        priorMargin[Constants.MICRO][1]) &&
        (classFrequency[Constants.class_precip] >
        priorMargin[Constants.PRECIP][3])) {
            clearOpinion = clearOpinion.consensus (new Opinion (0.3,0.65,0.05));
            precipOpinion = precipOpinion.consensus (new Opinion (0.65,0.2,0.15));
            microOpinion = microOpinion.consensus (new Opinion (0.6,0.20,0.20));
            crystalOpinion = crystalOpinion.consensus (new Opinion (0.6,0.2,0.2));
            CrystalClassifier.myLog.info ("*** Rule 3 ***");
            log.println ("  Sparkle detected in image");
            log.println ("Rule 3\n-------\nClear: "
            + clearOpinion.toString () + "\n"
            + "Precipitate: " + precipOpinion.toString () + "\n"
            + "MicroCrystal: " + microOpinion.toString () + "\n"
            + "Crystal: " + crystalOpinion.toString ());
            narrative.append ("  Sparkle detected in image\n");
        }
        
        /* Rule 4: Crystals and Precipitate in the same drop tend to form
         *         in similar distributions.
         *    Implementation:  If the center of the crystal regions far from
         *         the center of the precipitate regions (and at least 2
         *         crystal regions to create a center), then decrease crystal
         *         probability.  If low precip count, indicates possible
         *         misclassification of crystals, so increase clear prob.  If
         *         high precip count, indicates possible precip misclassified,
         *         so increase precip prob.
         */
        double distThreshold = 100.0;
        double dist = classCenter[Constants.class_crystal].distance (classCenter[Constants.class_precip]);
        
        if (dist > distThreshold
        && classFrequency[Constants.class_crystal] > 1) {
            // Based on threshold, initial belief is about 0.7
            double prob = 0.7 - distThreshold/dist;
            // But dispersion never reduces belief below 40%
            if (prob < 0.40) prob = 0.40;
            // Divide disbelief between disbelief and ignorance
            crystalOpinion = crystalOpinion.consensus (
            new Opinion (prob,(1.0-prob)*3.0/5.0, (1.0-prob)*2.0/5.0));
            // If small amount of precip (but still detectable), increase clear
            if (classFrequency[Constants.class_precip] < 50
            && classFrequency[Constants.class_precip] > 10) {
                clearOpinion = clearOpinion.consensus (new Opinion (0.6, 0.2, 0.2));
            } else if (classFrequency[Constants.class_precip] > 150) {
                // Lots of precip, so possibly misclassified precipitate
                precipOpinion = precipOpinion.consensus (new Opinion (0.75, 0.15, 0.1));
            }
            // Otherwise, minor possibility of either clear or precip
            if (classFrequency[Constants.class_precip] > 50
            && classFrequency[Constants.class_precip] < 150) {
                precipOpinion = precipOpinion.consensus (new Opinion (0.6, 0.1, 0.3));
                clearOpinion = clearOpinion.consensus (new Opinion (0.55, 0.2, 0.25));
            }
            CrystalClassifier.myLog.info ("*** Rule 4 ***\nCrystal & Precip Distribution");
            CrystalClassifier.myLog.info ("  Distance: " + ((int)(dist*100)/100.0) + "> " + 100);
            String message = "  " + adverb2 (distThreshold/dist) + " dissimilar "
            + "distribution of crystal and precipitate.";
            log.println (message);
            log.println ("Rule 4\n-------\nClear: "
            + clearOpinion.toString () + "\n"
            + "Precipitate: " + precipOpinion.toString () + "\n"
            + "MicroCrystal: " + microOpinion.toString () + "\n"
            + "Crystal: " + crystalOpinion.toString ());
            narrative.append (message + "\n");
        }
        
        /* Rule 5: If precipitate detected is minimal, it tends to form
         *         in the same area.  If it is spread over a large area, more
         *         likely to just be noise (dirt) in the image that is showing
         *         up as precipitate.
         *    Implementation: If ratio of precip < threshold and
         *         stdev > threshold, adjust precip probability, penalized
         *         for higher deviation/count ratio.
         */
        int precipDevThreshold = 180;
        int precipCountThreshold = 80;
        int dev = (int) classCenterStdev[Constants.class_precip] + 1;
        //int freq = classFrequency[Constants.class_precip]; // why was this here? commented in 2016...
        if (classFrequency[Constants.class_precip] < precipCountThreshold
        && classCenterStdev[Constants.class_precip] > precipDevThreshold){
            if (crystalOpinion.compareTo (clearOpinion) < 0) {
                crystalOpinion = crystalOpinion.consensus (new Opinion (0.6,0.2,0.2));
                microOpinion = microOpinion.consensus (new Opinion (0.55,0.35,0.1));
            } else {
                clearOpinion = clearOpinion.consensus (new Opinion (0.6,0.2,0.2));
            }
            double prob = precipDevThreshold / dev - 0.5;
            if (prob < 0.2) prob = 0.2;
            double disb = (1.0-prob) * 4.0 / 5.0;
            
            precipOpinion = precipOpinion.consensus (new Opinion (prob,
            disb,(1.0-prob-disb)));
            CrystalClassifier.myLog.info ("*** Rule 5 ***\nPrecipitate Locality");
            CrystalClassifier.myLog.info ("  Precip Frequency: "
            + classFrequency[Constants.class_precip] +" < "
            + precipCountThreshold + " Deviation: " +
            + ((int)(classCenterStdev[Constants.class_precip]*10)/10.0) + " > "
            + precipDevThreshold);
            String message = "  Image appears to be dirty.";
            log.println (message);
            log.println ("Rule 5\n-------\nClear: "
            + clearOpinion.toString () + "\n"
            + "Precipitate: " + precipOpinion.toString () + "\n"
            + "MicroCrystal: " + microOpinion.toString () + "\n"
            + "Crystal: " + crystalOpinion.toString ());
            narrative.append (message + "\n");
        }
        
        /* Rule 6: Clear areas are seldom mistaken for precipitate.  If there
         *         are a lot of precipitate regions, less likely clear (but
         *         since still may be misclassified crystals, no
         *         discrimination between other classes.)
         */
        precipCountThreshold = 90;
        double precipCount = classFrequency[Constants.class_precip];
        if (precipCount > precipCountThreshold) {
            // double change = (precipCount - (double)precipCountThreshold)
            // / ((double)precipCount * 3.0);
            // Only change clear if precip isn't distributed like edges
            if (!(Math.abs ((averageDistToCenter[Constants.class_edge]
            - averageDistToCenter[Constants.class_precip])
            / averageDistToCenter[Constants.class_edge]) < 0.15
            && averageDistToCenter[Constants.class_precip] > 130)) {
                double blf = (double)precipCountThreshold / precipCount - 0.55;
                if (blf < 0.4) blf = 0.4;
                clearOpinion = clearOpinion.consensus (new Opinion (blf,
                (1.0-blf)/2.0,
                (1.0-blf)/2.0));
                CrystalClassifier.myLog.info ("*** Rule 6 ***\nPrecipitate Detected");
                CrystalClassifier.myLog.info ("  Precip Frequency: "
                + classFrequency[Constants.class_precip] + " > "
                + precipCountThreshold);
                String message = "Precipitate indicates this isn't a "
                +"clear drop";
                log.println (message);
                log.println ("Rule 6\n-------\nClear: "
                + clearOpinion.toString () + "\n"
                + "Precipitate: " + precipOpinion.toString () + "\n"
                + "MicroCrystal: " + microOpinion.toString () + "\n"
                + "Crystal: " + crystalOpinion.toString ());
                narrative.append (message + "\n");
            }
        }
        
        /* Rule 7:  Detecting edges as crystals.
         *            Regions of crystal at similar average distance from the
         *            center, then decreased prob of crystal.  Although some
         *            obvious misdetections have been changed, this accounts
         *            for others that aren't so obvious, but still likely
         *            misdetection.
         *    Implementation:
         *       if edge count > 30 and avg dist from center > 100,
         *       and average distance from center for edge & crystal w/in 20%,
         *       decrease crystal prob by 10%
         */
        // += averageDistToCenter[Constants.class_edge] + ", " +
        //averageDistToCenter[Constants.class_crystal] + ", ";
        if (classFrequency[Constants.class_edge] > 30
        && (averageDistToCenter[Constants.class_edge] > 100.0)
        && (Math.abs ((averageDistToCenter[Constants.class_edge]
        - averageDistToCenter[Constants.class_crystal])
        / averageDistToCenter[Constants.class_crystal]) < 0.20)) {
            
            microOpinion = microOpinion.consensus (new Opinion (0.45,0.3,0.25));
            crystalOpinion = crystalOpinion.consensus (new Opinion (0.5,0.2,0.3));
            CrystalClassifier.myLog.info ("*** Rule 7 ***\nMisdetected edges");
            CrystalClassifier.myLog.info ("  Edges: " + classFrequency[Constants.class_edge]
            + " > 30" + " Ratio: "
            + ((int)(Math.abs ((averageDistToCenter[Constants.class_edge]
            - averageDistToCenter[Constants.class_crystal])
            / averageDistToCenter[Constants.class_crystal])*1000)/10.0)
            + " < 20%");
            log.println ("  Possibly detecting edges as crystals");
            log.println ("Rule 7\n-------\nClear: "
            + clearOpinion.toString () + "\n"
            + "Precipitate: " + precipOpinion.toString () + "\n"
            + "MicroCrystal: " + microOpinion.toString () + "\n"
            + "Crystal: " + crystalOpinion.toString ());
            narrative.append ("  Appears that some crystals are probably just "
            + " the edge of the drop.\n");
        }
        
        /* Rule 8: If center of precip regions and crystal regions are very
         *         close together, and amount of crystals detected is high
         *         enough to stabilize the center, adds confidence that
         *         crystals detected are true crystals (in precipitate).
         *   Implementation: If distance between crystal and precip regions
         *         is less than 50 and crystal count is greater than 50,
         *         increase confidence in crystal.  If Precip count is > 50,
         *         also forms a confident opinion about precip.
         */
        if (dist /* from Rule 5 */ < 60
        && classFrequency[Constants.class_crystal] > 10
        && classFrequency[Constants.class_crystal] < 120) {
            crystalOpinion = crystalOpinion.consensus (new Opinion (0.5,0.1,0.4));
            microOpinion = microOpinion.consensus (new Opinion (0.45,0.3,0.25));
            // If Precip count is high, then high opinion of precip in drop
            if (classFrequency[Constants.class_precip] > 150) {
                precipOpinion = precipOpinion.consensus (new Opinion (0.7,0.05,0.25));
            }
            if (classFrequency[Constants.class_precip] > 50
            && classFrequency[Constants.class_precip] <= 150) {
                precipOpinion = precipOpinion.consensus (new Opinion (0.6, 0.1, 0.3));
            }
            CrystalClassifier.myLog.info ("*** Rule 8 ***\nConfidence in Crystal Detection");
            String message = "Similar distribution of precipitate and crystal "
            + "indicates crystals in precipitate.";
            log.println (message);
            log.println ("Rule 9\n-------\nClear: "
            + clearOpinion.toString () + "\n"
            + "Precipitate: " + precipOpinion.toString () + "\n"
            + "MicroCrystal: " + microOpinion.toString () + "\n"
            + "Crystal: " + crystalOpinion.toString ());
            narrative.append (message + "\n");
        }
        
        /* Rule 9:  If the average distance to the center of detected
         *           crystal is small compared to edge distance to center,
         *           and average distance to center of edges is small
         *           (indicates some possible crystals detected as edges)
         *           more likely to be well formed crystals.
         *     Implementation: If Avg dist to center of crystal < 40% of
         *           of edge distance, number of edges > 20, avg dist to center
         *           for edges < 200, increase crystal likelihood.
         */
        if ((averageDistToCenter[Constants.class_crystal] <
        averageDistToCenter[Constants.class_edge] * 0.4)
        && averageDistToCenter[Constants.class_edge] < 200.0
        && classFrequency[Constants.class_edge] > 20
        && classFrequency[Constants.class_crystal] >= 10) {
            crystalOpinion = crystalOpinion.consensus (new Opinion (0.7, 0.1, 0.2));
            
            CrystalClassifier.myLog.info ("*** Rule 9 ***\nSmall Crystal Compared to Drop");
            CrystalClassifier.myLog.info ("  Edge Distance to Center: "
            + averageDistToCenter[Constants.class_edge] + ", Crystal Distance "
            + "to Center: " + averageDistToCenter[Constants.class_crystal]);
            log.println ("  Drop appears to have a visible edge with localized"
            + " crystals.");
            log.println ("Rule 9\n-------\nClear: "
            + clearOpinion.toString () + "\n"
            + "Precipitate: " + precipOpinion.toString () + "\n"
            + "MicroCrystal: " + microOpinion.toString () + "\n"
            + "Crystal: " + crystalOpinion.toString ());
            narrative.append ("  Visible edge and crystals formation looks "
            + "well defined crystals.\n");
        }
        
        /* Rule 10:  High count of edges tend to be misclassified precipitate.
         *           If there are many edge regions and not a large number of
         *           crystal regions, then edges are probably some precip
         *    Implementation: If Edge Frequency > 40 and Crystal Freq < 40,
         *           Increase precip belief, and decrease crystal belief
         */
        if (classFrequency[Constants.class_edge] > 60
        && classFrequency[Constants.class_crystal] < 30
        && classFrequency[Constants.class_clear] < 950) {
            precipOpinion = precipOpinion.consensus (new Opinion (0.65, 0.1, 0.25));
            crystalOpinion = crystalOpinion.consensus (new Opinion (0.55, 0.25, 0.2));
            CrystalClassifier.myLog.info ("*** Rule 10 ***\nDetecting Precipitate as Edges");
            log.println ("  Frequency of edges is very high; indicates possible "
            + "precipitate.");
            log.println ("Rule 10\n-------\nClear: "
            + clearOpinion.toString () + "\n"
            + "Precipitate: " + precipOpinion.toString () + "\n"
            + "MicroCrystal: " + microOpinion.toString () + "\n"
            + "Crystal: " + crystalOpinion.toString ());
            narrative.append ("  Frequency of edges is very high; indicates "
            + "possible misdetection of precipitate.\n");
        }
        
        /* Rule 11:  If the average distance to the center of detected
         *           precipitate is close to the avg distance to the
         *           center of edge regions, then more likely that
         *           precipitate detected is partially light edges.
         *     Implementation: If Avg dist to center of precip is within 15%
         *           of edge distance, and edge has large distribution, and
         *           small enough precip detected to likely be in error,
         *           decrease precipitate likelihood.  If low crystal belief
         *           at this point, also increase belief of clear.
         */
        // int crystalSizeThreshold = 100; // might need this later...
        if (Math.abs ((averageDistToCenter[Constants.class_edge]
        - averageDistToCenter[Constants.class_precip])
        / averageDistToCenter[Constants.class_edge]) < 0.15
        && averageDistToCenter[Constants.class_edge] > 175
        && classFrequency[Constants.class_precip] < 100) {
            precipOpinion = precipOpinion.consensus (new Opinion (0.6, 0.15, 0.25));
            if (crystalOpinion.getBelief () < 0.5) {
                clearOpinion = clearOpinion.consensus (new Opinion (0.6, 0.2, 0.2));
            } else {
                crystalOpinion = crystalOpinion.consensus (new Opinion (0.6,0.2,0.2));
            }
            CrystalClassifier.myLog.info ("*** Rule 11 ***\nLight Edges");
            CrystalClassifier.myLog.info ("  Edge Distance to Center: "
            + averageDistToCenter[Constants.class_edge] + ", Precip Distance "
            + "to Center: " + averageDistToCenter[Constants.class_precip]);
            log.println ("  Some edges look like precipitate.");
            log.println ("Rule 12\n-------\nClear: "
            + clearOpinion.toString () + "\n"
            + "Precipitate: " + precipOpinion.toString () + "\n"
            + "MicroCrystal: " + microOpinion.toString () + "\n"
            + "Crystal: " + crystalOpinion.toString ());
            narrative.append ("  Outlying precipitate might be the edge of "
            + "the drop.\n");
        }
        
        /** Rule 12: If average edge is close to the center, but count is high,
         *       very likely they are misclassified, probably just precipitate.
         *    (If the drop is small, then the edge count will be small.  If the
         *     drop is large, the edge count will be large).
         *  Implementation: If average dist to center of edges < 150 and
         *     edge count > 50, Opinion is that clear is less likely and
         *     Precip is more likely.  No opinion on crystal, because if average
         *     dist of edges is low, then hard to tell if crystal or precip.
         */
        if (averageDistToCenter[Constants.class_edge] < 150
        && classFrequency[Constants.class_edge] > 50) {
            clearOpinion = clearOpinion.consensus (new Opinion (0.5, 0.3, 0.2));
            precipOpinion = precipOpinion.consensus (new Opinion (0.65, 0.25, 0.1));
            CrystalClassifier.myLog.info ("**** Rule 12 ***\nEdges Close to Center");
            log.println ("  Apparent edges are too close to the center.  It "
            + "appears they might be precipitate.");
            log.println ("Rule 13\n-------\nClear: "
            + clearOpinion.toString () + "\n"
            + "Precipitate: " + precipOpinion.toString () + "\n"
            + "MicroCrystal: " + microOpinion.toString () + "\n"
            + "Crystal: " + crystalOpinion.toString ());
            narrative.append ("  Apparent edges near center are probably "
            + "precipitate.\n");
        }
        
        /** Rule 13:  Uniform well.  If nothing is detected (very high clear,
         *           almost nothing else), likely to be an image that is either
         *           dry or improperly taken.
         *  Implementation: If frequency of clear > 1150, very uncertain about
         *          clear, and possibly precipitate (dry well).
         */
        if (classFrequency[Constants.class_clear] > 1150) {
            clearOpinion = clearOpinion.consensus (new Opinion (0.4, 0.4, 0.2));
            precipOpinion = precipOpinion.consensus (new Opinion (0.7, 0.1, 0.2));
            CrystalClassifier.myLog.info ("**** Rule 13 ***\nNothing Detected");
            log.println ("  Appears to be a poor image or a dry well.");
            log.println ("Rule 14\n-------\nClear: "
            + clearOpinion.toString () + "\n"
            + "Precipitate: " + precipOpinion.toString () + "\n"
            + "MicroCrystal: " + microOpinion.toString () + "\n"
            + "Crystal: " + crystalOpinion.toString ());
            narrative.append ("  Cannot detect anything. Might be a poor "
            + "image or a dry well.\n");
        }
        
        /** Rule 14: High count of crystals in very small area indicate
         *           probability of true crystals.
         *  Implementation: If Avg Dist to Center of Crystal < 100 and crystal
         *       frequency > 100, belief of crystal is 65%
         */
        if (averageDistToCenter[Constants.class_crystal] < 80
        && classFrequency[Constants.class_crystal] > 50) {
            crystalOpinion = crystalOpinion.consensus (new Opinion (0.65, 0.2, 0.15));
            CrystalClassifier.myLog.info ("**** Rule 14 ***\nLocalized Crystals");
            log.println ("  Appears to be many small crystals in a cluster.");
            log.println ("Rule 14\n-------\nClear: "
            + clearOpinion.toString () + "\n"
            + "Precipitate: " + precipOpinion.toString () + "\n"
            + "MicroCrystal: " + microOpinion.toString () + "\n"
            + "Crystal: " + crystalOpinion.toString ());
            narrative.append ("  Appears to be have many small crystals in "
            + "a tight cluster.\n");
        }
        
        /** Rule 15: If anything is detected in a small group, very likely
         *          that the image is not clear.
         *  Implementation:  If edges or precip > 10 and small group, or if
         *          more than 2 crystals close together, probably not clear,
         *          could be anything else.
         */
        if ((averageDistToCenter[Constants.class_crystal] < 100
        && classFrequency[Constants.class_crystal] > 2) ||
        (averageDistToCenter[Constants.class_edge] < 100
        && classFrequency[Constants.class_edge] > 10) ||
        (averageDistToCenter[Constants.class_precip] < 100
        && classFrequency[Constants.class_precip] > 10)) {
            clearOpinion = clearOpinion.consensus (new Opinion (0.45, 0.45, 0.1));
            CrystalClassifier.myLog.info ("**** Rule 15 ***\nLocalized Unknown");
            log.println ("  Something detected near the middle.");
            log.println ("Rule 15\n-------\nClear: "
            + clearOpinion.toString () + "\n"
            + "Precipitate: " + precipOpinion.toString () + "\n"
            + "MicroCrystal: " + microOpinion.toString () + "\n"
            + "Crystal: " + crystalOpinion.toString ());
            narrative.append ("  Although not a strong indication, this image "
            + "appears to have something near the center.\n");
        }
        if (averageDistToCenter[Constants.class_edge] < 60
        && classFrequency[Constants.class_edge] > 10) {
            clearOpinion = clearOpinion.consensus (new Opinion (0.40,0.55,0.05));
            log.println ("Object near center is very small and well defined.");
            narrative.append ("  The object near the center is small and well "
            + "defined.\n");
        }
        
        Opinion bothCP = precipOpinion.conjunction (crystalOpinion);
        log.println ("Contains crystals in precip: "
        + bothCP.toString ());
        Opinion microP = microOpinion.conjunction (precipOpinion);
        log.println ("Microcrystals with Precipitate: "
        + microP.toString ());
        
        Opinion maxOpinion = new Opinion (clearOpinion);
        int maxClass = 0;
        if ((maxOpinion = maxOpinion.max (precipOpinion)).equals (precipOpinion)) {
            maxClass = Constants.PRECIP;
        }
        if ((maxOpinion = maxOpinion.max (microOpinion)).equals (microOpinion)) {
            maxClass = Constants.MICRO;
        }
        if ((maxOpinion = maxOpinion.max (crystalOpinion)).equals (crystalOpinion)) {
            maxClass = Constants.CRYSTAL;
        }
        String className = Constants.globalName (maxClass);
        
        log.println ("Predicted Class &" + className + "&("
        + maxOpinion.toString () + ")");
        // Done
        return 0;
    }
    
    /** Assign an adverb to the specified value (0 < value < 1) */
    String adverb (double value) {
        String adv;
        int margin = (int) (value * 4);
        switch (margin) {
            case 3:
                adv = "slightly";
                break;
            case 2:
                adv = "a little";
                break;
            case 1:
                adv = "somewhat";
                break;
            case 0:
                adv = "very";
                break;
                default:
                    adv = "";
                    break;
        }
        return adv;
    }
    
    /** Assign an adverb to the specified value (0 < value < 1) */
    String adverb2 (double value) {
        String adv;
        int margin = (int) (value * 4);
        switch (margin) {
            case 3:
                adv = "slight";
                break;
            case 2:
                adv = "marginal";
                break;
            case 1:
                adv = "noticeable";
                break;
            case 0:
                adv = "significant";
                break;
                default:
                    adv = "";
                    break;
        }
        return adv;
    }
    
    /** Gets a string with only the opinions of each class for this image
     */
    String classExpectations () {
        StringBuffer result = new StringBuffer (100);
        result.append ("("+clearOpinion.toString ()+ "), ");
        result.append ("("+precipOpinion.toString ()+ "), ");
        result.append ("("+microOpinion.toString ()+ "), ");
        result.append ("("+crystalOpinion.toString ()+ ")");
        return (result.toString ());
    }
    
    /** Gets the frequencies of each regional class */
    String classFrequencies () {
        String s = "";
        for (int i = 0; i < classFrequency.length; i++) {
            s += "," + classFrequency[i];
        }
        return s;
    }
    
    /** Returns a string description of the classification of this image.
     * String includes a classification and the complete narrative.
     */
    String classificationResults () {
        // double temp = 0.0d;
        // int cls = -1;
        
        Opinion bothCP = precipOpinion.conjunction (crystalOpinion);
        log.println ("Contains crystals in precip: "
        + bothCP.toString ());
        Opinion microP = microOpinion.conjunction (precipOpinion);
        log.println ("Microcrystals with Precipitate: "
        + microP.toString ());
        
        Opinion maxOpinion = new Opinion (clearOpinion);
        int maxClass = 0;
        if ((maxOpinion = maxOpinion.max (precipOpinion)).equals (precipOpinion)) {
            maxClass = Constants.PRECIP;
        }
        if ((maxOpinion = maxOpinion.max (microOpinion)).equals (microOpinion)) {
            maxClass = Constants.MICRO;
        }
        if ((maxOpinion = maxOpinion.max (crystalOpinion)).equals (crystalOpinion)) {
            maxClass = Constants.CRYSTAL;
        }
        String className = Constants.globalName (maxClass);
        
        StringBuffer result = new StringBuffer (250);
        result.append ("Most likely classification: "
        + className + " (" + maxOpinion.toString () + ")");
        
        if (bothCP.getBelief () >= 0.5) {
            if (maxClass != Constants.CRYSTAL) {
                result.append (", has an indication of crystals");
            } else {
                result.append (", appear to be in precipitate");
            }
        }
        if (microP.getBelief () >=0.5) {
            if (maxClass != Constants.MICRO) {
                result.append (", sparkle might be microcrystals");
            } else {
                result.append (", but seem to be in precipitate.");
            }
        }
        if (maxOpinion.getUncertainty () > 0.5) {
            result.append (", but there are no strong indications of "
            + " any real formation.");
        }
        return result.toString ();
    }
    
    /** Gets the narrative description of the current classification */
    public String classificationNarrative () {
        return narrative.toString ();
    }
    
    /**
     * Reads the precomputed decision tree from a text file.
     * Requires that Quest classify a training set, and that the output file
     * (with the classification tree info) be produced, which will be read here.
     * Assumes the format of output files used by Quest 1.8.4.
     */
    private void initDecisionTree () {
        if (dtFile == null) {
            getInputFile ();
        }
        
        FileInputStream fis = null;
        try {
            fis = new FileInputStream (dtFile);
        }
        catch (Exception E) {
            CrystalClassifier.myLog.severe ("Couldn't Open File " + dtFile);
        }
        try {
            if (fis != null) {
                readFile (fis);
            }
            fis.close ();
        }
        catch (Exception E) {
            CrystalClassifier.myLog.severe ("File cannot be found, or is empty");
        }
        // OutputStream os = null; // removed 2016, why os in initialization?
    }
    
    /**
     * Reads the decision tree from the specified file.  Called by
     * initDecisionTree.  This method should not be called by a user.
     * Requires four output classes--"ClrDirt", "Crystal", "Edge", and
     * "Precipitate", and assumes that "Precipitate" will be the final class
     * in any given node.
     *
     * @param is            The input stream of the source file
     */
    protected void readFile (InputStream is)
    throws IOException { //, FileFormatException {
        CrystalClassifier.myLog.info ("Reading Decision Tree Data File");
        
        Reader r = new BufferedReader (new InputStreamReader (is));
        StreamTokenizer st = new StreamTokenizer (r);
        
        st.commentChar ('*');
        st.commentChar ('-');
        st.eolIsSignificant (true);
        st.parseNumbers ();
        
        int count = 0;
        int node = 0;
        int clear = 0;
        int crystal = 0;
        int edge = 0;
        int precip = 0;
        boolean nodeComplete = false;
        process:
            while (true) {
                switch(st.nextToken ()) {
                    case StreamTokenizer.TT_EOF:
                        CrystalClassifier.myLog.info ("Completed Decision Tree, " + count
                        + " nodes added");
                        break process;
                    case StreamTokenizer.TT_WORD:
                        String s = st.sval;
                        if (s.equals ("Node")) {
                            st.nextToken ();
                            node = (int)st.nval;
                        }
                        if (s.equals ("ClrDirt")) {
                            st.nextToken ();
                            clear = (int)st.nval;
                        }
                        if (s.equals ("Crystal")) {
                            st.nextToken ();
                            crystal = (int)st.nval;
                        }
                        if (s.equals ("Edge")) {
                            st.nextToken ();
                            edge = (int)st.nval;
                        }
                        if (s.equals ("Precipitate")) {
                            st.nextToken ();
                            precip = (int)st.nval;
                            nodeComplete = true;
                        }
                        break;
                        default:
                            break;
                }
                if (nodeComplete) {
                    DecisionNode dn = new DecisionNode ("Node " + node, clear,
                    crystal, edge, precip);
                    decisionTree.put (dn.getKey (), dn);
                    nodeComplete = false;
                    count++;
                }
            }
    }
    class DTreeFilter extends javax.swing.filechooser.FileFilter {
        public boolean accept(File f) {
            return f.isDirectory() || f.getName().toLowerCase().endsWith(".ctr");
        }
        
        public String getDescription() {
            return "Decision Trees (*.ctr)";
        }
    }
}