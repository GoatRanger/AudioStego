/*
 * ImageHistogram.java
 *
 * Created on October 28, 2000, 9:56 PM
 */

package imageproc.utilities;

/**
 *
 * @author  Karl Gossett
 * @version 
 */
import java.awt.image.*;
import java.awt.*;
import java.awt.image.renderable.ParameterBlock;
import javax.media.jai.JAI;
import javax.media.jai.*;

public class ImageHistogram extends Object {
    protected int numBands; // Number of color bands in the image;
    protected int numBins;  // Number of bins to use for histogram collections
    private int[] bins;
    private double[] low;
    private double[] high;
    
    /** Creates new ImageHistogram */
    public ImageHistogram() {
        numBands = 3;  // Assume RGB Image
        numBins = 10;  // Default to 2 bins (divide image in half)
        createBins();
    }
    
    public ImageHistogram(int imageBands, int imageBins) {
        numBands = imageBands;
        numBins = imageBins;
        createBins();
    }
    
    public void createBins() {
        
        bins = new int[numBands];
        low = new double[numBands];
        high = new double[numBands];
        for (int i=0; i<numBands; i++) {
            bins[i] = numBins;
            low[i] = 0.0D;
            high[i] = 256.0D;
        }
    }
        
    public Histogram[] getHistogram(Shape[] grid, BufferedImage histImage) {
        
        Histogram[] histogram;
        int totalSamples = grid.length;        
        histogram = new Histogram[totalSamples];
        
        for (int count = 0; count < totalSamples; count++) {
            
            ROIShape roi = new ROIShape(grid[count]);

            // Create the parameter block.
            histogram[count] = new Histogram(bins, low, high);
            ParameterBlock pb = new ParameterBlock();
            pb.addSource(histImage);
            pb.add(roi);
            pb.add(1); //Period x
            pb.add(1); // Period y
            pb.add(bins);
            pb.add(low);
            pb.add(high);
            
//            pb.addSource(histImage);    // Source Image
//            pb.add(histogram[count]);  // Operation -- Histogram
//            pb.add(roi);		// Using the ROI in grid
//            pb.add(1);		// Sampling frequency (all pixels)
//            pb.add(1);		// Sampling Period (all pixels)

            PlanarImage dst = (PlanarImage)JAI.create("histogram",pb);
            // Save the histogram in the histogram array
            histogram[count] = (Histogram) dst.getProperty("histogram");
        } // End For count
        return histogram;
    } // End getImageHistogram
}