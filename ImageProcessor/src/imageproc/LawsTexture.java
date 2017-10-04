/*
 * LawsTexture.java
 *
 * Created on September 14, 2000, 6:58 PM
 */

package imageproc;

import Jama.Matrix;
/**
 *
 * @author  Karl A. Gossett
 * @version 
 */

public class LawsTexture extends Object {

    /** Creates new LawsTexture */
    public LawsTexture() {
        double[][] L5vals = {{1., 4., 6., 4., 1.}};
        double[][] E5vals = {{-1., -2., 0., 2., 1.}};
        double[][] S5vals = {{-1., 0., 2., 0., -1.}};
        double[][] W5vals = {{-1., 2., 0., -2., 1.}};
        double[][] R5vals = {{1., -4., 6., -4., 1.}};
        double[][] L3vals = {{1., 2., 1.}};
        double[][] E3vals = {{-1., 0., 1.}};
        double[][] S3vals = {{-1., 2., -1.}};
        
        Matrix L5 = new Matrix(L5vals);
        Matrix E5 = new Matrix(E5vals);
        Matrix S5 = new Matrix(S5vals);
        Matrix W5 = new Matrix(W5vals);
        Matrix R5 = new Matrix(R5vals);
        Matrix L3 = new Matrix(L3vals);
        Matrix E3 = new Matrix(E3vals);
        Matrix S3 = new Matrix(S3vals);
        
        Matrix L5E5 = L5.times(E5.transpose());
        Matrix E5L5 = E5.times(L5.transpose());
        Matrix S5E5 = S5.times(E5.transpose());
        Matrix E5S5 = E5.times(S5.transpose());
        Matrix W5E5 = W5.times(E5.transpose());
        Matrix E5W5 = E5.times(W5.transpose());
    }

}