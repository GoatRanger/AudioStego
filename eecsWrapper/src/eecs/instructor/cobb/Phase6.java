/* Copyright (C) 2003  USMA
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

package eecs.instructor.cobb;

import jago.ImageLoader;

import jago.element.Message;

import java.awt.Image;


/**
* GUI for writing games that play the game of Nim. This game uses
* marbles. The setInitialSize() method sets the initial size of
* the pile of marbles. The pile must be between 1 - 100 marbles.
* Players may remove 1 or no more than half of the marbles on their
* turn.  The person to draw the last marble loses.
*/
public class Phase6 extends eecs.RobotGuiBase {
  

  static {
  	getProperties();
  	if (!runningTests){
    sim = new jago.SimBox();
    sim.setSimTitle("Dice");

    Image bg = ImageLoader.getImage(new Phase6(), "dice.GIF");
    sim.setBackgroundImage(bg);

    frame = sim;
    sim.setVisible(true);
    robot = null;
  	}
  }
  public static Dice[] activeDice=new Dice[5];
 
  
  private static int round = 0;
 
  private static Message roundMsg;
  private static Message scoreMsg;
  
static		 Message round1MSG=new Message("1");
static		 Message round2MSG=new Message("2");
static		 Message round3MSG=new Message("3");
static		 Message round4MSG=new Message("4");
static		 Message round5MSG=new Message("5");
static		 Message round6MSG=new Message("6");
static		 Message totalMSG=new Message("Total");
static		 Message scoreTitleMSG=new Message("SCORE");
static		 Message score1MSG=new Message("0");
static		 Message score2MSG=new Message("0");
static		 Message score3MSG=new Message("0");
static		 Message score4MSG=new Message("0");
static		 Message score5MSG=new Message("0");
static	 Message score6MSG=new Message("0");
static		 Message totalScoreMSG=new Message("0");
  
  
  /**
     * Sets up the entire match.  Currently creates both the dealer and human player objects
     *
     */
  public static void initializeMatch() {
     getProperties();
  	 if (!runningTests){
  	
  	printLine("intializing Match.....");
    
    
    
   
    
    
    printLine("Match initialized");

  createDice();
  createScoreCard();
 }
  }

  /**
     * deals player one card face up, updated card total, and updates the display.
     *
     */
  public static void createScoreCard()
  {
	   roundMsg=new Message("ROUND");
	   
	   sim.add(roundMsg, 20, 300);
	   sim.add(scoreTitleMSG, 100, 300);
	   sim.add(round1MSG, 25,320);
	   sim.add(score1MSG, 110,320);
	   sim.add(round2MSG, 25,340);
	   sim.add(score2MSG, 110,340);
       sim.add(round3MSG, 25,360);
	   sim.add(score3MSG, 110,360);
       sim.add(round4MSG, 25,380);
	   sim.add(score4MSG, 110,380);	 
       sim.add(round5MSG, 25,400);
	   sim.add(score5MSG, 110,400);
 	   sim.add(round6MSG, 25,420);
	   sim.add(score6MSG, 110,420);
  	   sim.add(totalMSG, 25,440);
	   sim.add(totalScoreMSG, 110,440);
	   
  }
  public static int getRoundNumber() {
    return round;
  }

 
 public static int getDie1Value()
 {  
 	getProperties();
 	if (!runningTests){
 	   return activeDice[0].getDieValue();
 	}
 	else{
 		int x=getInt("dummy");

    logger.info("Die 1: " + x);
 		return x;
 	}
 }
  
 public static int getDie2Value()
  {
 	getProperties();
 	if (!runningTests){
	  return activeDice[1].getDieValue();
 	} 
	 else{
 		int x=getInt("dummy");
 		return x;
 	}
  }
 
  public static int getDie3Value()
   {
  	
  	getProperties();
 	if (!runningTests){
	  return activeDice[2].getDieValue();
 	}
	
   else{
 		int x=getInt("dummy");
 		return x;
 	}  
   }
  
   public static int getDie4Value()
	{
   	getProperties();
 	if (!runningTests){
	   return activeDice[3].getDieValue();
 	}
	
	else{
 		int x=getInt("dummy");
 		return x;
 	}   
	}
	public static int getDie5Value()
	 {
		getProperties();
	 	if (!runningTests){
			return activeDice[4].getDieValue();
	 	}
			
		else{
		 		int x=getInt("dummy");
		 		return x;
		 	}	
	 }
  
/*	 public static int getDie6Value()
	  {
		 return activeDice[5].getDieValue();
	  }
*/

  
  
  public static void rollAll()
  {
  	getProperties();
  	if(!runningTests){
	  	 for (int i=0;i<5;i++){
		     sim.remove(activeDice[i]);
		 }
	  	  
		   showDiceRoll();
			try 
			{
			 Thread.sleep(0);
			}
			catch (InterruptedException e) 
					{
	            
				   }
	  	for (int i=0;i<5;i++)
	  	{
	  		activeDice[i].roll();
			sim.add(activeDice[i],20+(50*i),100);
	  	}
	  	showMessage("Results of the current roll");
  	}
  }
   public static void createDice()
   {
   	getProperties();
   	if(!runningTests){
       // Dice activeDice1=new Dice(1);
        for (int i=0;i<5;i++)
        {
        	activeDice[i]=new Dice(6);
			sim.add(activeDice[i],20+(50*i),100);
        }
        
   	}
   }
   public static void rollDice(boolean die1, boolean die2, boolean die3, boolean die4, boolean die5)
   {
   	
    getProperties();
   	if(!runningTests){
		boolean[] dieArray={die1,die2,die3,die4,die5};
	   	for (int i=0;i<5;i++)
			{
			if (dieArray[i])
				sim.remove(activeDice[i]);
			}
	  	  
			   showDiceRoll();
				try 
				{
				 Thread.sleep(0);
				}
				catch (InterruptedException e) 
						{
	            
					   }
			for (int i=0;i<5;i++)
			{
				if (dieArray[i])
				{	
				activeDice[i].roll();
				sim.add(activeDice[i],20+(50*i),100);
				}
			}
		showMessage("Results of the current roll");
   	}
  }
  /**
  * Sets the initial size of the pile of marbles. May only be
  * called once. Calling more than once will cause an
  * IllegalStateException to be thrown.
  * @param size  The initial size of the pile (1 - 100)
  */
   
  public static void showDiceRoll()
  {
    getProperties();
    if(!runningTests){
		Dice diceAnim=new Dice();
		sim.add(diceAnim, 25,50);
		for (int i=0;i<10;i++)
		{		
			
			//this.setAppearance("images/dice"+randomValue+".gif");
			diceAnim.setAppearance("images/dicerule.gif");
			
		  try 
			 {
	  		 	
				Thread.sleep(300);
			  } 
			 catch (InterruptedException e) 
			 {
	            
			}
		}
		sim.remove(diceAnim);
    }
  }
 

  

  
  public static void updateRoundScore(int roundNumber, int score) {
    getProperties();
  	if(!runningTests){

   
	    if (roundNumber==1)
	    {	
	       score1MSG.setAppearance(""+score);
	    }
		else if (roundNumber==2)
		   {	
			  score2MSG.setAppearance(""+score);
		   }
		else if (roundNumber==3)
		   {	
			  score3MSG.setAppearance(""+score);
		   }
		else if (roundNumber==4)
		   {	
			  score4MSG.setAppearance(""+score);
		   }
		else if (roundNumber==5)
		   {	
	          score5MSG.setAppearance(""+score);
		   }
		else if (roundNumber==6)
		   {	
			  score6MSG.setAppearance(""+score);
		   }
  	}
  	else{
  		printLine("URS"+roundNumber+","+score);
  	}
    //sim.add(RoundMsg,300,175);
  }

//  public static void printLine(String m) {
//  	
//  	getProperties();
//  	if (!runningTests) {
//  		super.printLine(m);
//  	}
//  	else {
//  		
//  	}
//  }
  
   public static void updateTotalScore(int totalScore)
   {
   	 //totalScoreMSG.setAppearance(""+totalScore);
    getProperties();
  	if(!runningTests){
		sim.remove(totalScoreMSG);
		totalScoreMSG=new Message(""+totalScore);
		sim.add(totalScoreMSG,110,440);
  	}
  	else{
  		printLine("UTS"+totalScore);
  	}
    
   }
  

  private static int getRandomIndex(int size) {
    int randomVal;

    randomVal = (int) (size * Math.random());

    return randomVal;
  }

  /* private static void createMarbles() {
   if (!marblesCreated) {
     for (int i = 0; i < initSize; i++) {

       if ((i % 2) == 0) {
         marbles[i] = new Marble(Marble.RED);
       } else if ((i % 3) == 0) {
         marbles[i] = new Marble(Marble.BLUE);
       } else {
         marbles[i] = new Marble(Marble.YELLOW);
       }
     }
     marbles[3]=new Marble(Marble.down);
     marblesCreated = true;
   }
  }*/
}
