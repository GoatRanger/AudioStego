/*
 * ImageStatistics.java
 *
 * Created on March 14, 2001, 9:35 PM
 */

package imageproc.utilities;

/**
 *
 * @author  Karl A. Gossett
 * @version 0.1 14 March 2001
 */
import java.awt.*;
import java.awt.image.*;
import java.awt.image.renderable.ParameterBlock;

import javax.media.jai.*;

public class ImageStatistics extends java.lang.Object {
    
    private double[] edgeMean;
    
    private double[] edgeMax;
    
    private double[] origMean;
    
    private double[] origMin;
    
    private double[] origStdev;
        
    /** The mean pixel value in each band of the image */
    private double[] imageMean;
    
    //private double imageStdev;
    
    protected Histogram[] histogram;
    
    protected ImageHistogram histogramOp;
    
    BufferedImage edgeImage;
    BufferedImage origImage;
    /** Creates new ImageStatistics */
    public ImageStatistics() {
    }
    
    public void calculateStats (BufferedImage img, BufferedImage orig,
                            Shape[] regions) {
        
        histogramOp = new ImageHistogram(1,64);
        histogram = histogramOp.getHistogram (regions, orig);
        edgeImage = img;
        origImage = orig;
        
        // Compute mean pixel value for image
        ParameterBlock pb = new ParameterBlock();
        pb.addSource(edgeImage);   // The source image
        pb.add(null);       // null ROI means whole image
        pb.add(1);          // check every pixel horizontally
        pb.add(1);          // check every pixel vertically
        
        // Perform the mean operation on the source image.
        //RenderedImage meanImage = JAI.create("mean", pb, null);
        
        // Retrieve and report the mean pixel value.
        //double[] imageMean = (double[])meanImage.getProperty("mean");
        
        int numWide = 39; // Ignore the last column of the image. Noise in many
        int numHigh = 30;
        edgeMean = new double[numWide * numHigh];
        edgeMax = new double[numWide * numHigh];
        origMean = new double[numWide * numHigh];
        origMin = new double[numWide * numHigh];
        origStdev = new double[numWide * numHigh];
        
        //int width = 16;
        //int height = 16;
        int q=0;
        for (int x = 0; x < numWide; x++) {
            for (int y = 0; y < numHigh; y++) {
                edgeMean[q] = regionMean (0, regions[q]);
                edgeMax[q] = regionMax (0, regions[q]);
                origMean[q] = regionMean (1, regions[q]);
                origMin[q] = regionMin (1, regions[q]);
                origStdev[q] = regionStDev (histogram[q], 1, regions[q]);
                q = q + 1;
            }
        }
    }
        
    protected double regionMean (int image, Shape region) {
        ROIShape roi = new ROIShape(region);
        ParameterBlock pb = new ParameterBlock();
        if (image == 0) pb.addSource (edgeImage);
        else pb.addSource (origImage);
        pb.add(roi);
        pb.add(1);
        pb.add(1);
        RenderedImage meanImage = JAI.create("mean", pb, null);
        double[] roiMean = (double[]) meanImage.getProperty("mean");
        return roiMean[0]; // Returns only a single band mean
    }
    
    protected double regionMax (int image, Shape region) {
        
        int bands;
        ROIShape roi = new ROIShape(region);
        ParameterBlock pb = new ParameterBlock();
        if (image == 0) {
            pb.addSource (edgeImage);
            bands = edgeImage.getSampleModel().getNumBands();
        } else { 
            pb.addSource (origImage);
            bands = origImage.getSampleModel().getNumBands();
        }
        pb.add(roi);
        pb.add(1);
        pb.add(1);
        RenderedOp op = JAI.create ("extrema",pb);
        double[][] extrema = new double[2][bands];
        extrema = (double[][])op.getProperty ("extrema");
        return extrema [1][0]; // Return the max for a single band
    }
    
    protected double regionMin (int image, Shape region) {
        int bands;
        ROIShape roi = new ROIShape(region);
        ParameterBlock pb = new ParameterBlock();
        if (image == 0) {
            pb.addSource (edgeImage);
            bands = edgeImage.getSampleModel().getNumBands();
        } else { 
            pb.addSource (origImage);
            bands = origImage.getSampleModel().getNumBands();
        }
        pb.add(roi);
        pb.add(1);
        pb.add(1);
        RenderedOp op = JAI.create ("extrema",pb);
        double[][] extrema = new double[2][bands];
        extrema = (double[][])op.getProperty ("extrema");
        return extrema [0][0]; // Return the max for a single band
    }
    
    protected double regionStDev (Histogram hist, int image, 
                                  Shape region) {
        
        //int n=0;   // Number of samples
        double sse=0;
        
        if (hist == null) return 0.0;
        double mean = regionMean (image, region);
        for (int q=0; q<hist.getNumBins(0); q++) {
            for (int b=0; b < hist.getBinSize(0,q); b++) {
                sse = sse + Math.pow(q*4-mean,2.0);
            }
        }
        return (Math.sqrt(sse / (255)));
    }
    
    public double getImageMean () {
        return imageMean[0];
    }
    
    public double[] getRegionEdgeMeans() {
        return edgeMean;
    }
    
    public double[] getRegionEdgeMax() {
        return edgeMax;
    }
    
    public double[] getUnprocessedMeans() {
        return origMean;
    }
    
    public double[] getUnprocessedRegionMins() {
        return origMin;
    }
    
    public double[] getUnprocessedRegionStdev() {
        return origStdev;
    }    
}
