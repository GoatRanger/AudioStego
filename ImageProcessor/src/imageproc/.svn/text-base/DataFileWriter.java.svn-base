package imageproc;

import java.io.*;

/**
 * This class allows reading and writing to a neural network
 * example data file (for training or testing).  The file must
 * contain a header of the number of cases in the file, the
 * number of features per case, and the number of outputs per case
 * (both of which must match the network setup parameters for the
 *  number of units in the first and last layers of the network).
 * Usage:
 * <pre>
 * DataFileWriter trainingData = new DataFileWriter(filename);
 * trainingData.set...();
 * trainingData.writeFile();
 * </pre>
 *
 * @author	Karl Gossett
 * @version 1.0
 * @see java.io.StreamTokenizer
 */
public class DataFileWriter {
    
    FileOutputStream fos;
    PrintWriter pw;
    int numCases = 0;
    int featuresPerCase = 0;
    int outputsPerCase = 0;
    int[][] featureMatrix;
    double[][] targetArray;
    int[] caseNumList;
    boolean featuresFull = false;
    boolean targetsFull = false;
    
    /**
     *
     */
    public DataFileWriter() {
        numCases = 0;
    }
    
    
    /**
     *
     */
    public DataFileWriter(String outFile) { 
        try {
            fos = new FileOutputStream(outFile);
            pw = new PrintWriter(fos);
        }
        catch (Exception E) {
            System.out.println("Couldn't Open File " + outFile);
        }
    }
    
    public void writeHeader()
    throws IOException {
        
        if (numCases > 0) {
            System.out.println("Writing Data File");
            
            pw.println("; Neural Network Data File\n\n");
            pw.println(numCases + " ; Number of cases in this file");
            pw.println(featuresPerCase + " ; Number of input features in each case");
            pw.println(outputsPerCase + " ; Number of outputs per case\n\n");
        }
        else {
            System.out.println("There are no cases to write.");
        }
    }
    
    
/**
 *
 */
    public void writeTLearnFile()
    throws IOException {
        // Temporary assignment, fix once the code allows use of these
        FileOutputStream fos,fost;
        PrintWriter pw,pwt;
        
        fos = null;
        fost = null;
        pw = pwt = null;
        try {
            fos = new FileOutputStream("crystal.data");
            pw = new PrintWriter(fos);
            fost = new FileOutputStream("crystal.teach");
            pwt = new PrintWriter(fost);
        }
        catch (Exception E) {
            System.out.println("Couldn't Open TLearn Files");
        }
        featuresFull = true;
        targetsFull = true;
        if (featuresFull & targetsFull) {
            pw.println("distributed");
            pw.println(numCases);
            pwt.println("distributed");
            pwt.println(numCases);
            for (int i = 0; i < numCases; i++) {
                for (int j = 0; j < featuresPerCase; j++) {
                    pw.print(featureMatrix[i][j] + " ");
                }
                for (int j = 0;j < outputsPerCase;j++) {
                    pwt.print(targetArray[i][j] + " ");
                }
                pw.println();
                pwt.println();
            }
            System.out.println("Data File Written.");
            pw.close();
            fos.close();
            pwt.close();
            fost.close();
        }
        else {
            System.out.println("There are not enough data points.");
        }
    }
    
    /**
     *
     */
    public void writeFile()
    throws IOException {
        // Temporary assignment, fix once the code allows use of these
        featuresFull = true;
        targetsFull = true;
        if (featuresFull & targetsFull) {
            writeHeader();
            
            for (int i = 0; i < numCases; i++) {
                pw.println("\n; Case " + caseNumList[i]);
                pw.println("; Feature List");
                for (int j = 0; j < featuresPerCase; j++) {
                    pw.print(featureMatrix[i][j] + " ");
                }
                pw.println("\n; Targets");
                for (int j = 0;j < outputsPerCase;j++) {
                    pw.print(targetArray[i][j] + " ");
                }
            }
            System.out.println("Data File Written.");
            pw.close();
            fos.close();
        }
        else {
            System.out.println("There are not enough data points.");
        }
    }
    
    /**
     *
     */
    public void setNumCases(int numCases) {
        this.numCases = numCases;
        if (featuresPerCase != 0) {
            featureMatrix = new int[numCases][featuresPerCase];
        }
        if (outputsPerCase != 0) {
            targetArray = new double[numCases][outputsPerCase];
        }
    }
    
    /**
     *
     */
    public void setFeaturesPerCase(int numFeatures) {
        this.featuresPerCase = numFeatures;
        if (numCases != 0) {
            featureMatrix = new int[numCases][featuresPerCase];
        }
    }
    
    /**
     *
     */
    public void setOutputsPerCase(int numOutputs) {
        this.outputsPerCase = numOutputs;
        if (numCases != 0) {
            targetArray = new double[numCases][outputsPerCase];
        }
    }
    
    /**
     *
     */
    public void setFeatureMatrix(int[][] features) {
        featureMatrix = features;
        featuresFull = true;
    }
    
    public void setFeatureList(int[] features, int caseNum) {
        int[] tempFeatureList;
        
        tempFeatureList = new int[featuresPerCase];
        tempFeatureList = features;
        for (int i = 0;i < featuresPerCase;i++) {
            featureMatrix[caseNum][i] = tempFeatureList[i];
        }
        if (caseNum == (numCases-1)) {
            featuresFull = true;
        }
    }
    
    /**
     *
     */
    public void setTargetArray(double[][] targets) {
        this.targetArray = targets;
        targetsFull = true;
    }
    
    public void setTargetList(double[] targets, int caseNum) {
        
        for (int i = 0;i < outputsPerCase;i++) {
            targetArray[caseNum][i] = targets[i];
        }
        if (caseNum == (numCases-1)) {
            targetsFull = true;
        }
    }
    
    public void setCaseNumList(int[] caseList) {
        caseNumList = new int[numCases];
        caseNumList = caseList;
    }
    
    public void dispose() {
        
        try {
            fos.close();
        }
        catch (Exception e) {
        }
    }
}