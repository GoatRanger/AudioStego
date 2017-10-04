package eecs.instructor.cobb;

import jago.element.SolidElement;


/*
 * Created on Oct 16, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

/**
 * @author dm0266
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Dice extends SolidElement {
  int value;
  
  public Dice()
  {
  	this(1);
  }
  public Dice(int dieNumber) {
    switch (dieNumber) {
   

    case 1: //Ace
      setAppearance("images/dice1.GIF");
      this.value = 1;

      break;

    case 2:
      setAppearance("images/dice2.GIF");
      this.value = 2;

      break;

    case 3:
      setAppearance("images/dice3.GIF");
      this.value = 3;

      break;

    case 4:
      setAppearance("images/dice4.GIF");
      this.value = 4;

      break;

    case 5:
      setAppearance("images/dice5.GIF");
      this.value = 5;

      break;

    case 6:
      setAppearance("images/dice6.GIF");
      this.value = 6;

      break;

    

    }
  }
  public int getDieValue()
  {
  	return value;
  }
  public void roll()
  {
  
	int randomValue=0;
  	
  	
  		
  		 randomValue=(int)Math.round(5*Math.random()+1);
  		
  	 
  		this.setAppearance("images/dice"+randomValue+".GIF");
  		this.value=randomValue;
  	
  }
}
