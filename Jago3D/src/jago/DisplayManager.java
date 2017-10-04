/* Copyright (C) 2002  USMA
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package jago;

import jago.element.*;

import java.applet.Applet;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.net.MalformedURLException;
import java.net.URL;

import javax.media.j3d.Alpha;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Geometry;
import javax.media.j3d.Material;
import javax.media.j3d.PositionInterpolator;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;


/**
 * This class is responsible for updating the display of all elements,
 * including the background image and any elements that are
 * <CODE>Visible</CODE>.
 *
 * <P>
 * As of Version 6.1, any element that does not implement the
 * <CODE>Mobile</CODE> interface are added to the background, and are not
 * redrawn during every update.
 * </p>
 */
public class DisplayManager
    extends JPanel {

    private Canvas3D canvas3d;
    /** Indicates whether debugging information is desired (not used) */
    protected boolean debugging = false;

    /** The {@link eecs.jago.SimEngine} that requires display services. */
    protected SimEngine owner;
    private Image background;
    private BufferedImage backgroundBuffer;
    private Graphics2D bg2;

    /** The height of the parent frame, used for scaling */
    private int frameHeight;

    /** The width of the parent frame, used for scaling */
    private int frameWidth;
    private BufferedImage offScreenBuffer;
    private Graphics2D osbg2;
    private AffineTransform scaleTransform = null;

    private BranchGroup scene;
    /** Flags an update of the background for the next paint cycle */
    private boolean updateNeeded = false;

    /**
     * Creates a new DisplayManager, within the specified SimEngine
     *
     * @param theOwner The {@link eecs.jago.SimEngine} that requires service.
     */
    public DisplayManager(SimEngine theOwner) {
    	owner = theOwner;
        GraphicsConfiguration config =
            SimpleUniverse.getPreferredConfiguration();

 	   canvas3d = new Canvas3D(config);
 	   canvas3d.setPreferredSize(owner.getPreferredSize());
 	   owner.add(canvas3d);
 	   SimpleUniverse u = new SimpleUniverse(canvas3d);
      	u.getViewingPlatform( ).setNominalViewingTransform( );

		BranchGroup scene = createSceneGraph( );
		BranchGroup robot = createRobot();
		scene.addChild(robot);
		scene.compile( );
		u.addBranchGraph( scene );
		
        frameWidth = (int)owner.getPreferredSize()
                               .getWidth();
        frameHeight = (int)owner.getPreferredSize()
                                .getHeight();
        setVisible(true);
    }
    
    private BranchGroup createRobot() {
    	BranchGroup branch = new BranchGroup();
    	TransformGroup trans = new TransformGroup();
    	trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        branch.addChild(trans);
    	Box box = new Box(0.5f,0.5f,0.5f,new Appearance());
    	
    	trans.addChild(box);
    	
    	Appearance sphereAppearance = new Appearance();
    	Texture texImage = null;
    	
			texImage = new TextureLoader("c:/projects/jago3D/bin/jago/images/track.JPG",this).getTexture();
			texImage.setEnable(true);
		   	sphereAppearance.setTexture (texImage);	
		   	TextureAttributes textAttr = new TextureAttributes();
			textAttr.setTextureMode(TextureAttributes.MODULATE);
			sphereAppearance.setTextureAttributes(textAttr);
			sphereAppearance.setMaterial(new Material());
        box.setAppearance(sphereAppearance);
    	Box track = new Box (0.2f, 0.2f, 0.8f, sphereAppearance);
	    
    	Transform3D polyTrans = new Transform3D();
    	polyTrans.setTranslation(new Vector3f(-0.3f,0.3f,0.0f));
    	TransformGroup transTrack = new TransformGroup();
    	transTrack.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	transTrack.setTransform(polyTrans);
        transTrack.addChild(track);
        branch.addChild(transTrack);
        /* switch on the light! ambient and directlight */
		AmbientLight lightAmbient1 = new AmbientLight(new Color3f(.7f, .7f, .7f));
		BoundingSphere bounds2 = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
		lightAmbient1.setInfluencingBounds(bounds2);
		branch.addChild(lightAmbient1);
		DirectionalLight lightDirec1 = new DirectionalLight(new Color3f(.4f, .4f, .4f), new Vector3f(1.0f,1.0f,-1.0f));
		lightDirec1.setInfluencingBounds(bounds2);
		branch.addChild(lightDirec1);

    	return branch;
    }
	private BranchGroup createSceneGraph( )
	{
		// Make a scene graph branch
		BranchGroup branch = new BranchGroup( );

		// Make a changeable 3D transform
		TransformGroup trans = new TransformGroup( );
		trans.setCapability( TransformGroup.ALLOW_TRANSFORM_WRITE );
		branch.addChild( trans );

		// Make a shape
		ColorCube demo = new ColorCube( 0.4 );
		trans.addChild( demo );
		Alpha posAlpha = new Alpha(10,0);
		PositionInterpolator pi = new PositionInterpolator(posAlpha, trans);
		pi.setSchedulingBounds(new BoundingSphere(new Point3d(),3));
		trans.addChild( pi );
		// Make a behavor to spin the shape
		Alpha spinAlpha = new Alpha( -1, 4000 );
		RotationInterpolator spinner =
			new RotationInterpolator( spinAlpha, trans );
//		spinner.setSchedulingBounds(
//			new BoundingSphere( new Point3d( ), 1000.0 ) );
		trans.addChild( spinner );

		return branch;
	}
/* (non-Javadoc)
 * @see javax.swing.JComponent#setPreferredSize(java.awt.Dimension)
 */
public void setPreferredSize(Dimension arg0) {
	// TODO Auto-generated method stub
	super.setPreferredSize(arg0);
	canvas3d.setPreferredSize(arg0);
}
    /**
     * Set the background image to the specified image.
     *
     * @param theImage An <CODE>Image</CODE> to use as the background.
     */
    public void setBackground(Image theImage) {
        background = theImage;

        int imageWidth = theImage.getWidth(this);
        int imageHeight = theImage.getHeight(this);
        this.setPreferredSize(new Dimension(imageWidth, imageHeight));
        revalidate();
        frameWidth = (int)owner.getPreferredSize()
                               .getWidth();
        frameHeight = (int)owner.getPreferredSize()
                                .getHeight();

        // Create the graphics buffer and Graphics2D context
        offScreenBuffer = (BufferedImage)owner.createImage(frameWidth,
                                                           frameHeight);
        backgroundBuffer = (BufferedImage)owner.createImage(frameWidth,
                                                            frameHeight);
        osbg2 = offScreenBuffer.createGraphics();
        bg2 = backgroundBuffer.createGraphics();
        updateNeeded = true;
    }

    /**
     * Tile the background with the specifed tile image.
     *
     * @param theImage An <CODE>Image</CODE> to use to create a tiled
     *        background.
     */
    public void setBackgroundTile(Image theImage) {

        Image tiles = TiledImage.createTiledImage(theImage,
                                                  owner.getPreferredSize().width,
                                                  owner.getPreferredSize().height,
                                                  owner);
        setBackground(tiles);
    }

    /**
     * Turns on debugging.  For the DisplayManager, debugging will draw all
     * bounding rectangles for everything that implements the {@link
     * eecs.jago.element.Visible} interface.
     *
     * @param debug As of Version 6.0, this method is not used. (Kept for
     *        future use)
     */
    public void setDebugGraphics(boolean debug) {
        debugging = debug;
    }

    /**
     * Returns the current drawing buffer.  Useful for SimElements that use
     * knowledge of the visual appearance of the simulation (such as sensors)
     *
     * @return The current drawing buffer, as a <CODE>BufferedImage</CODE>
     */
    public BufferedImage getImageBuffer() {

        if (bg2 == null) {

            return null;
        }

        return backgroundBuffer;
    }

    /**
     * Repaint the screen. Draws all static elements before mobile ones to
     * ensure that mobile elements are always displayed on top, and that
     * sensors will detect events from the background.  May need to be
     * updated eventually to update sensors last so they will also be able to
     * respond accurately to mobile elements.  In this version, there is no
     * guarantee that sensor results will be consistent when detecting
     * against mobile elements in the simulation.
     *
     * @param g The<CODE>Graphics2D</CODE> context.
     */
    public void paintComponent2d(Graphics g) {

        long a = System.currentTimeMillis();
        super.paintComponent(g);

        if (osbg2 == null) {

            return;
        }

        if (owner.elementManager.staticChanged || updateNeeded) {
            updateBackground();
            updateNeeded = false;
            owner.elementManager.staticChanged = false;
        }

        // Draw the background
        osbg2.drawImage(backgroundBuffer, null, 0, 0);

        Object[] elements = owner.elementManager.mobileElements.toArray();

        // Now draw the mobile elements.  Added this to ensure that all static
        // elements are drawn first, so sensors will work (more) correctly.
        // May need to eventually ensure that sensors are updated last.
        for (int elem = 0; elem < elements.length; elem++) {

            if (elements[elem] instanceof Visible) {
                ((Visible)elements[elem]).draw(osbg2);

                if (debugging == true) {

                    if (elements[elem] instanceof SimElement) {
                        osbg2.setColor(Color.blue);

                        Area elemArea = ((SimElement)elements[elem]).getArea();
                        osbg2.draw(elemArea);
                        osbg2.setColor(Color.black);
                        osbg2.draw(elemArea.getBounds2D());
                        osbg2.drawRect(
                                (int)((SimElement)elements[elem]).getPosition()
                                                               .getX(),
                                (int)((SimElement)elements[elem]).getPosition()
                                                               .getY(), 2, 2);
                    }
                }
            }
        }

        Graphics2D g2 = (Graphics2D)g;

        /* The following will rescale the image so that it always completely
         * fits inside the simulation frame.  Unfortunately, a significant
         * amount of processing is required, so the simulation runs significantly
         * slower.  Maybe JDK1.4 VolatileImage model will help...
         */

        //if (frameWidth != owner.getWidth() || frameHeight != owner.getHeight()) {
        //    scaleTransform = new AffineTransform();
        //    double hScale = (double)background.getWidth(this)
        //            / (double)owner.getWidth();
        //    double vScale = (double)background.getHeight(this)
        //            / (double)owner.getHeight();
        //    scaleTransform.scale(1.0/hScale,1.0/vScale);
        //}
        //g2.drawImage(offScreenBuffer,scaleTransform,this);
        g2.drawImage(offScreenBuffer, null, 0, 0);
        g2.dispose();
    }

    /**
     * The background consists of any background image and all static elements
     * in the simulation.  If the DisplayManager notices that any of these
     * components have changed, it will update.  Otherwise, the background
     * "image" is prerendered (with all static elements added) to increase
     * speed.
     */
    void updateBackground() {
        bg2.drawImage(background, 0, 0, null);

        // Draw the static elements
        Object[] statics = owner.elementManager.staticElements.toArray();

        for (int i = 0; i < statics.length; i++) {

            if (statics[i] instanceof Visible) {
                ((Visible)statics[i]).draw(bg2);
            }
        }
    }
}