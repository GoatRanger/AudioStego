package eecs.jago.instructor.flowers;

import jago.element.SolidElement;

/**
* Represents a small colored marble.
*/
public class Marble extends SolidElement {

    public static final int RED = 1;
    public static final int YELLOW = 2;
    public static final int BLUE = 3;

    public Marble(int color){
        if(color == RED){
            setAppearance("images/red_marble.gif");
        }else if (color == YELLOW){
            setAppearance("images/yellow_marble.gif");
        }else if (color == BLUE){
            setAppearance("images/blue_marble.gif");
        }else {
            throw new IllegalArgumentException("Illegal color: Must use " +
            "Marble.RED, Marble.BLUE, or Marble.YELLOW");
        }
    }
}