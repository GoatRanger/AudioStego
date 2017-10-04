/*
 * Created on Sep 19, 2004
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
import java.awt.Frame;

import java.applet.Applet;

import java.awt.*;

import java.awt.event.*;

import com.sun.j3d.utils.applet.MainFrame;

import com.sun.j3d.utils.geometry.*;

import com.sun.j3d.utils.universe.*;

import javax.media.j3d.*;

import javax.vecmath.*;

import com.sun.j3d.utils.image.TextureLoader;


public class Lesson05 extends Applet {
    SimpleUniverse simpleU;
    static boolean application = false;


    public BranchGroup createSceneGraph() {      

   	BranchGroup objRoot = new BranchGroup();

	Appearance polygon1Appearance = new Appearance();

        QuadArray polygon1 = new QuadArray (4,QuadArray.COORDINATES | GeometryArray.TEXTURE_COORDINATE_2);
    	polygon1.setCoordinate (0, new Point3f (0f, 0f, 0f));
    	polygon1.setCoordinate (1, new Point3f (2f, 0f, 0f));
    	polygon1.setCoordinate (2, new Point3f (2f, 3f, 0f));
    	polygon1.setCoordinate (3, new Point3f (0f, 3f, 0f));

	polygon1.setTextureCoordinate (0, new Point2f(0.0f,0.0f));
	polygon1.setTextureCoordinate (1, new Point2f(1.0f,0.0f)); 
    	polygon1.setTextureCoordinate (2, new Point2f(1.0f,1.0f));
    	polygon1.setTextureCoordinate (3, new Point2f(0.0f,1.0f));


	if (application == true){
     	   	Texture texImage = new TextureLoader("c:/projects/jago3D/bin/jago/images/track.JPG", this).getTexture();

	   	polygon1Appearance.setTexture (texImage);

        }
	else {
	    	try
    		{
      			java.net.URL texImage = new java.net.URL (getCodeBase (), "brick.jpg");
	      		Texture brick= new TextureLoader (texImage, this).getTexture ();
      			polygon1Appearance.setTexture (brick);

	    	}
    		catch (java.net.MalformedURLException ex){}

	}

    	objRoot.addChild (new Shape3D (polygon1, polygon1Appearance));
    	return objRoot;


    }



    public Lesson05 (){

    }



    public void init() {



        setLayout(new BorderLayout());

    	Canvas3D c = new Canvas3D(SimpleUniverse.getPreferredConfiguration());

    	add("Center", c);

    

    	BranchGroup scene = createSceneGraph();

	simpleU = new SimpleUniverse(c);

        TransformGroup tg = simpleU.getViewingPlatform().getViewPlatformTransform();

	Transform3D t3d = new Transform3D();
	t3d.setTranslation(new Vector3f(0f,0f,10f));
	tg.setTransform(t3d);

        scene.compile();

        simpleU.addBranchGraph(scene);

    }

    public void destroy(){
	simpleU.removeAllLocales();
    }  



    public static void main(String[] args) {
	application = true;

     	Frame frame = new MainFrame(new Lesson05(), 500, 500);

    }

}



