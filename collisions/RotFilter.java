// Copyright (C) 1998 Michael Fowler under Gnu Public License
// http://www.gnu.org/copyleft/gpl.html
/*
 * This filter rotates images 90 degrees, counterclockwise by default, unless
 * you call setCounterClockwise(false).  It is a gradual hack from an ImageFilter
 * example in The Java Class Libraries.  I'm not sure the awt won't somehow automatically
 * do a rotation for you if you make a filter that just sets the dimensions, but I
 * can't figure it out.  This works.
 * Drew Dolgert 11 August 1998 ajd2m@virginia.edu
 */
 
import java.awt.*;
import java.awt.image.*;

class RotFilter extends ImageFilter {
    int width, height;
    boolean bCounterClockwise = true;
    
    public void setDimensions(int w, int h) {
        super.setDimensions(width=h,height=w);
        //System.out.println("new width: "+width+" new height: "+height);
    }

    public void setCounterClockwise(boolean on) {
        bCounterClockwise = on;
    }

    public void setHints(int h) {
        h |= ImageConsumer.RANDOMPIXELORDER;
        h &= ~ImageConsumer.TOPDOWNLEFTRIGHT;
        super.setHints(h);
    }

    public void setColorModel(ColorModel model) {
        super.setColorModel(model);
    }

    // RotFilter byte
    public void setPixels(int srcX, int srcY, int srcW, int srcH,
        ColorModel model, byte pixels[], int srcOff, int srcScan) {
        int s = srcOff;
        byte[] tempBuff = new byte[srcW*srcH];
        int xOff, yOff;

        // bytelocation = (x + y*scanSize+offset).
        // For a 90 degree rotation, x' = y, y' = height'-x-1 = width-x-1
        System.out.println("x "+srcX+" y "+srcY+" w "+srcW+" h "+srcH+" scan "+srcScan);
        if (bCounterClockwise) {
            xOff = srcY;
            yOff = height-(srcX+srcW);
            // Can run from 0 to this rectangle's width b/c we can turn this section
            // of the picture in place and the offsets just above put it in the
            // correct part of the whole image.
            for (int y=0; y<srcH; y++) {
                for (int x=0; x<srcW; x++) {
                    int d;
                    d = (srcW-x-1)*srcH+y+srcOff;
                    tempBuff[d] = pixels[s];
                    s++;
                }
            }
        } else {
            // for a -90 rotation, x'=width'-y-1, y'=x
            xOff = width-(srcY+srcH);
            yOff = srcX;
            for (int y=0; y<srcH; y++) {
                for (int x=0; x<srcW; x++) {
                    int d;
                    d = (x+1)*srcH-y-1+srcOff;
                    tempBuff[d] = pixels[s];
                    s++;
                }
            }
        }
        //System.out.println(" ");
        System.out.println("x "+xOff+" y "+yOff+" w "+srcH+" h "+srcW+" scan "+srcH);

        super.setPixels(xOff, yOff, srcH, srcW, model, tempBuff, 0, srcH);
    }

    // RotFilter int
    public void setPixels(int srcX, int srcY, int srcW, int srcH,
        ColorModel model, int pixels[], int srcOff, int srcScan) {
        int s = srcOff;
        int[] tempBuff = new int[srcW*srcH];
        int xOff, yOff;
        
        if (bCounterClockwise) {
            xOff = srcY;
            yOff = height-(srcX+srcW);
            for (int y=0; y<srcH; y++) {
                for (int x=0; x<srcW; x++) {
                    int d;
                    d = (srcW-x-1)*srcH+y+srcOff;
                    tempBuff[d] = pixels[s];
                    s++;
                }
            }
        } else {
            xOff = width-(srcY+srcH);
            yOff = srcX;
            for (int y=0; y<srcH; y++) {
                for (int x=0; x<srcW; x++) {
                    int d;
                    d = (x+1)*srcH-y-1+srcOff;
                    tempBuff[d] = pixels[s];
                    s++;
                }
            }
        }
        //System.out.println(" ");
        //System.out.println("x "+xOff+" y "+yOff+" w "+srcH+" h "+srcW+" scan "+srcH);

        super.setPixels(xOff, yOff, srcH, srcW, model, tempBuff, 0, srcH);
    }

    public synchronized void imageComplete(int status) {
        super.imageComplete(status);
    }
}

