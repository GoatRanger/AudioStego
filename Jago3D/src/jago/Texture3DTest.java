/*
 * Created on Sep 20, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package jago;

/**
 * @author Karl
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
/*
 *      @(#)Texture3DTest.java 1.1 99/01/05 16:14:11
 *
 * Copyright (c) 1996-1998 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Sun grants you ("Licensee") a non-exclusive, royalty free, license to use,
 * modify and redistribute this software in source and binary code form,
 * provided that i) this copyright notice and license appear on all copies of
 * the software; and ii) Licensee does not utilize the software in a manner
 * which is disparaging to Sun.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes.
 */


/**
 * Draws two squares using a 3D texture map.  The first square uses explicit
 * texture coordinates.  The second uses texture coordinate generation to 
 * set up texture coordinates which line up with the first square.
 */


import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.event.*;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.Box;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.awt.image.BufferedImage;
import java.awt.image.ComponentColorModel;
import java.awt.image.WritableRaster;
import java.awt.image.DataBufferByte;
import java.awt.color.ColorSpace;
import java.awt.Transparency;


public class Texture3DTest extends Applet {

    public BranchGroup createSceneGraph() {

        // Create the root of the branch graph
        BranchGroup objRoot = new BranchGroup();

        // Create a transform group to center the object
        TransformGroup objOrient = new TransformGroup();
        Transform3D orient = new Transform3D();
        orient.set(new Vector3d(-0.25, -0.0, -0.0), 0.5);
        objOrient.setTransform(orient);
        objRoot.addChild(objOrient);

        // Create a transform group node and initialize it to the identity.
        // Enable the TRANSFORM_WRITE capability so that our behavior code
        // can modify it at runtime.  Add it to the root of the subgraph.
        //
        TransformGroup objTrans = new TransformGroup();

        objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objOrient.addChild(objTrans);

        //
        // Create a 3D texture
        //
        int width = 16;
        int height = 16;
        int depth = 16;

        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_sRGB); 
        int[] nBits = {8, 8, 8}; 
        ComponentColorModel colorModel = new ComponentColorModel(cs, nBits, 
                false, false, Transparency.OPAQUE, 0); 
        WritableRaster raster = 
          colorModel.createCompatibleWritableRaster(width, height); 
 
        BufferedImage bImage =                                   
            new BufferedImage(colorModel, raster, false, null); 
 
        byte[] byteData = ((DataBufferByte)raster.getDataBuffer()).getData(); 
 
        ImageComponent3D pArray = new ImageComponent3D(
                ImageComponent.FORMAT_RGB, width, height, depth);

        // set up a volume with the color intensities corresponding to the 
        // s,t,r values: s = red, t = green, r == blue
        for (int k = 0; k < depth; k++) {
            for (int j = 0;j < height;j++){
                for (int i = 0;i < width;i++){ 

                    double s = (double) i / width;
                    double t = (double) j / height;
                    double r = (double) k / depth;

                    // Note: Java3D flips the s coordinate to match the 2D 
                    // image sematics, which put s=0 at the "top" of the image.
                    // Since most 3D data puts the origin at the lower left 
                    // corner, we flip the "s" coordinate
                    s = 1.0 - s;
                    
                    int index = ((j * width) + i) * 3; 
                    byteData[index] =   (byte)(255 * s);
                    byteData[index+1] = (byte)(255 * t);
                    byteData[index+2] = (byte)(255 * r);
                } 
            } 
            pArray.set(k, bImage);
        }
 

        Texture3D tex = new Texture3D(Texture.BASE_LEVEL,
                                           Texture.RGB, width, height, depth);
        tex.setImage(0, pArray);
        tex.setEnable(true);
        tex.setMinFilter(Texture.BASE_LEVEL_LINEAR);
        tex.setMagFilter(Texture.BASE_LEVEL_LINEAR);
        tex.setBoundaryModeS(Texture.CLAMP);
        tex.setBoundaryModeT(Texture.CLAMP);
        tex.setBoundaryModeR(Texture.CLAMP);

        // turn off face culling and lighting so we an see just the texture
        PolygonAttributes p = new PolygonAttributes();
        p.setCullFace(PolygonAttributes.CULL_NONE);
        Material m = new Material();
        m.setLightingEnable(false);

        // Create two squares, one with texture coordinates, and the 
        // other with generated texture coordinates

        Point3f[]       coords = new Point3f[4];
        coords[0] = new Point3f(0.0f, 0.0f, 0.0f);
        coords[1] = new Point3f(1.0f, 1.0f, 0.0f);
        coords[2] = new Point3f(1.0f, 1.0f, 1.0f);
        coords[3] = new Point3f(0.0f, 0.0f, 1.0f);

        // Note that the texture coordinates match the coords: s=x, t=y, r=z
        Point3f[]       texCoords = new Point3f[4];
        texCoords[0] = coords[0];
        texCoords[1] = coords[1];
        texCoords[2] = coords[2];
        texCoords[3] = coords[3];

        QuadArray coordsSquare = new QuadArray(4, 
                QuadArray.COORDINATES | QuadArray.TEXTURE_COORDINATE_3 );
        coordsSquare.setCoordinates(0, coords);
        coordsSquare.setTextureCoordinates(0, texCoords);

        // create an appearance with the texture but no tex coord gen
        Appearance coordsAppearance = new Appearance();
        coordsAppearance.setTexture(tex);
        coordsAppearance.setMaterial(m);
        coordsAppearance.setPolygonAttributes(p);

        Shape3D coordsShape = new Shape3D(coordsSquare, coordsAppearance); 

        objTrans.addChild(coordsShape);

        // Now the square with generated tex coords.  This crosses the first
        // square, but it has texture coordinates which match up with the
        // the first quad

        // First the shape...
        Point3f[]       genCoords = new Point3f[4];
        genCoords[0] = new Point3f(1.0f, 0.0f, 0.0f);
        genCoords[1] = new Point3f(1.0f, 0.0f, 1.0f);
        genCoords[2] = new Point3f(0.0f, 1.0f, 1.0f);
        genCoords[3] = new Point3f(0.0f, 1.0f, 0.0f);

        QuadArray genSquare = new QuadArray(4, QuadArray.COORDINATES);
        genSquare.setCoordinates(0, genCoords);

        // setup the tex coord gen.  This is just s = x, t = y, r = z
        TexCoordGeneration tg = new TexCoordGeneration();
        tg.setFormat(TexCoordGeneration.TEXTURE_COORDINATE_3);
        tg.setPlaneS(new Vector4f(1.0f, 0.0f, 0.0f, 0.0f));
        tg.setPlaneT(new Vector4f(0.0f, 1.0f, 0.0f, 0.0f));
        tg.setPlaneR(new Vector4f(0.0f, 0.0f, 1.0f, 0.0f));

        // create an appearance with the texture and tex coord gen
        Appearance genAppearance = new Appearance();
        genAppearance.setTexture(tex);
        genAppearance.setTexCoordGeneration(tg);
        genAppearance.setMaterial(m);
        genAppearance.setPolygonAttributes(p);

        Shape3D genShape = new Shape3D(genSquare, genAppearance); 

        objTrans.addChild(genShape);

        BoundingSphere bounds =
            new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);

        // Create a new Behavior object that will perform the desired
        // operation on the specified transform object and add it into the
        // scene graph.
        //
        Transform3D yAxis = new Transform3D();
        Alpha rotorAlpha = new Alpha(-1, Alpha.INCREASING_ENABLE,
                                     0, 0,
                                     4000, 0, 0,
                                     0, 0, 0);
        RotationInterpolator rotator =
            new RotationInterpolator(rotorAlpha,
                                     objTrans,
                                     yAxis,
                                     0.0f, (float) Math.PI*2.0f);
        rotator.setSchedulingBounds(bounds);
        objTrans.addChild(rotator);

        // Have Java 3D perform optimizations on this scene graph.
        objRoot.compile();

        return objRoot;
    }

    public Texture3DTest() {
        setLayout(new BorderLayout());
        Canvas3D c = new Canvas3D(null);
        add("Center", c);

        // Create a simple scene and attach it to the virtual universe
        BranchGroup scene = createSceneGraph();
        SimpleUniverse u = new SimpleUniverse(c);

        // This will move the ViewPlatform back a bit so the
        // objects in the scene can be viewed.
        u.getViewingPlatform().setNominalViewingTransform();

        u.addBranchGraph(scene);
    }

    //
    // The following allows Texture3DTest to be run as an application
    // as well as an applet
    //
    public static void main(String[] args) {
        new MainFrame(new Texture3DTest(), 256, 256);
    }

}

