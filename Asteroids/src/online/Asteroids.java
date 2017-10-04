/* 
 * Asteroids.java
 * Code: Jan Humble
 */
package online;

import java.applet.*; 
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class Asteroids extends Applet 
    implements Runnable, MouseListener, ActionListener{ 
    
    
    /**
   * 
   */
  private static final long serialVersionUID = 2377533374953396029L;
    ActionCanvas actionCanvas;
    int actionCanvasY = 400;
    int dY; 
    int dX; 
    int initNumCircles = 2;
    int numCircles; 
    Vector drawShapes; 
    Thread drawThread;
    double deltaT=100;
    
    /*--------------------------------------------------*/ 
    
    public void init(){ 
    Math.random(); 
    // Get the drawing area of the applet 
    dY=getSize().height; 
    dX=getSize().width; 
        
    
    // Create a random number of circles up to 2 
    numCircles = initNumCircles; 

    // Create a drawShapes array to point to 
    // any shape derived from objectToDraw class.
    drawShapes=new Vector(numCircles); 
    
    // Now create the circle objects 
    makeCircles(); 
        

    // set the layout
    
    setLayout(new BorderLayout());
    
    Label title = new Label("ASTEROIDS by Jan Humble", Label.CENTER);
    title.setFont(new Font("Helvetica", Font.BOLD, 14));
    title.setBackground(Color.white);

    actionCanvas = new ActionCanvas(this);
    actionCanvas.addMouseListener(this);
    
    actionCanvas.setSize(dX, actionCanvasY);
    
    Panel controlPanel = new Panel ();
        controlPanel.setBackground(Color.white);
    Button restartButton = new Button ("Restart");
        restartButton.addActionListener (this); 
    restartButton.setCursor (new Cursor (Cursor.HAND_CURSOR));
        controlPanel.add (restartButton);
    
    
    add("North", title);
    add("Center", actionCanvas);
    add("South", controlPanel);
    
    } 

    
    public void actionPerformed(ActionEvent ev) {
    
    if (ev.getActionCommand().equals("Restart")) {
  
        makeCircles();
        
        
    }
    }

    /*--------------------------------------------------*/ 
    public void start() { 
    if (drawThread == null){ 
        drawThread = new Thread(this);    
        drawThread.start();
    }
    }
    /*--------------------------------------------------*/ 
    public void stop() { 
    drawThread = null;
    }
    /*--------------------------------------------------*/ 
    public void run() { 
    
    while (drawThread != null) { 
        // Use sleep to pause between movements
        try { 
        Thread.sleep((int)(100)); 
        } catch (InterruptedException e)
        {} 
        for( int i=0; i< numCircles; i++){ 
        ((Circle)drawShapes.elementAt(i)).move(deltaT); 
        
    
        }
        actionCanvas.repaint();
    }
    }


    // Check if on mouse click any object has been hit
    
    void checkHit(int x, int y) { 
    
    int i = 0;
    while (i < numCircles &&
           (! ((Circle)drawShapes.elementAt(i)).checkHit(x, y))) 
        i++;
    
    // object hit
    
    
    if (i < numCircles) {
        if ( ((Circle)drawShapes.elementAt(i)).generation < 3) {
        
        // make 2 copies 
        Circle copy1 = 
            (Circle) ((Circle)drawShapes.elementAt(i)).clone();
        Circle copy2 = 
            (Circle) ((Circle)drawShapes.elementAt(i)).clone();
        drawShapes.addElement(copy1);
        drawShapes.addElement(copy2);
        drawShapes.removeElementAt(i);
        numCircles++;
        }
    
        // last generation, just remove object
        else  {
        drawShapes.removeElementAt(i);
        numCircles--;
        }
    }
    
    }
    

    /*--------------------------------------------------*/ 
    void makeCircles(){ 
    int radius=1,x=0,y=0; 
    
    // Fix circle diameters at 10% of horizontal axis 
    radius = (int)(0.1 * dX); 
    
    drawShapes.removeAllElements();
    // Now create the circle instances 
    for (int i=0; i < initNumCircles; i++){ 
        // choose random center location 
        x=(int)( (dX) * Math.random()); 
        y=(int)( (dY) * Math.random()); 
        // Add the circles to the drawShapes array 
        drawShapes.addElement (new 
        Circle(x,y,radius,dX, actionCanvasY)); 
    }
    numCircles = initNumCircles;
    }  
     

    
    // Override all mouse events

    public void mousePressed(MouseEvent e) { 
    checkHit(e.getX(), e.getY());
    
    } 

    public void mouseReleased(MouseEvent e) { 
    
    } 
    
    public void mouseEntered(MouseEvent e) { 
    actionCanvas.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR) );
    } 
    
    public void mouseExited(MouseEvent e) { 
    actionCanvas.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR) );
    } 
    
    public void mouseClicked(MouseEvent e) { 
   
    } 
   
    
    
} 


class Circle implements Cloneable { 

    int centerX,centerY; // Center of drawn object 
    int drawX,drawY;     // Origin of object drawing area, 
                         //  e.g. top left corner of the 
                         // bounding  rectangle. 
    int dx,dy;           // Width of drawing area. 
    int areaX,areaY;     // Dimensions of the applet drawing area. 
    Color color; 
    int radius; 
    double Vtot=0.2;
    double Vx,Vy;
    double toggleSign=1.0;

    int generation = 1;
    
    /*---------------------------------------------------*/
    Circle(int centerX, int centerY, int radius,
       int dX, int dY){
    this.centerX  = centerX; 
    this.centerY  = centerY; 
    this.dx = radius*2; 
    this.dy = radius*2; 
    this.radius = radius;
    drawX = this.centerX - (dx/2); 
    drawY = this.centerY - (dy/2); 
    
    areaX=dX; 
    areaY=dY; 
    
    color = Color.blue;
    
    // Initialize the velocity of the object.

    initVel();
    
    } 
    
    void initVel () {
    Vx = Vtot * Math.random() + 0.01;
    if (Vx >= Vtot) Vx = Vtot - 0.2;
    if (Vx <= 0.05) Vx = 0.1;
    Vy = Math.sqrt( Vtot * Vtot - Vx*Vx);
    }
    
    
    // override cloning to make new circles
    // with different characteristics, according to generation
    
    public Object clone() { 
     
     try {
         
         Circle copy =  (Circle)super.clone();
         copy.generation = generation + 1;
         copy.radius = (int) (radius*0.8);
         copy.dx = copy.dy = copy.radius*2;
         copy.initVel();
         
         if (copy.generation == 2)
         copy.color = Color.red;
         else if (copy.generation == 3)
         copy.color = Color.green;
         
         return copy;
         
     } catch (CloneNotSupportedException e ) { 
         throw new Error("This should never happen!"); 
     } 
     } 
    
    public boolean checkHit (int x, int y) {
    
    // Hit inside circle ?

    if ( Math.sqrt((x - centerX)*(x - centerX) + 
               (y - centerY)*(y - centerY)) 
         <= radius)
        return true;
    return false;
    
    }
    
    /*---------------------------------------------------*/
    // Step the objects in X,Y. 
 
    public void move(double deltaT){
    
    if( drawX <= 0){
        Vx=Math.abs(Vx);
        if(Vx < 0.0) Vx=-Vx;
    }
    if( drawY <= 0){
        Vy=Math.abs(Vy);
        if(Vy < 0.0) Vy=-Vy;
    }
    if( drawX >= (areaX - 2*radius) ){ 
        Vx=-Math.abs(Vx);
        if(Vx > 0.0) Vx=-Vx;
    }
    if( drawY >= (areaY - 2*radius) ) {
        Vy=-Math.abs(Vy);
        if(Vy > 0.0) Vy=-Vy;
    }
    drawX = (int)(deltaT*Vx+drawX);
    drawY = (int)(deltaT*Vy+drawY);
    centerX = drawX + radius;
    centerY = drawY + radius;
    
    }

    public void draw(Graphics g) { 
    g.setColor(color); 
    // Move away from edges. 
    if( drawX < 0) drawX = 0; 
    if( drawY < 0) drawY = 0; 
    if( drawX > (areaX - 2*radius) ) 
        drawX = areaX - 2*radius; 
    if( drawY > (areaY - 2*radius) ) 
        drawY = areaY - 2*radius; 
    
    // Now draw a solid color circle. 
    g.fillOval(drawX,drawY, dx,dy); 
    }
    
} 



class ActionCanvas extends Canvas {
    
    /**
   * 
   */
  private static final long serialVersionUID = -4222368846728508500L;

    Image offscreen;      // Background graphics buffer
    
    int dX, dY;
    
    Asteroids applet;
       
 
    ActionCanvas (Asteroids a) {
    
    this.applet = a;
    this.setSize(a.dX, a.actionCanvasY);
    this.setBackground(Color.black);
    

    }
    
    public void update (Graphics g){
    
    Dimension d = this.getSize();
    
    // Update using double buffering
    
    
    if (offscreen == null)
        offscreen = this.createImage(d.width, d.height);
    
    // Draw to background buffer
    g = offscreen.getGraphics();
    paint(g);
    g.dispose();
    
    // draw from background buffer to screen buffer
    g = this.getGraphics();
    g.drawImage(offscreen, 0, 0, this);
    
    
    }

    
    public void paint(Graphics g) {
    
    dX = getSize().width;
    dY = getSize().height;

    // Clear screen
 
    g.setColor(getBackground());
    g.fillRect(0,0, dX, dY);
    
    // Now we can draw all possible shapes just by
    // looping through the one drawShapes array.
    for( int i=0; i< applet.numCircles; i++){ 
        ((Circle) applet.drawShapes.elementAt(i)).draw(g); 
    } 
    } 
}


