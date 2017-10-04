package eecs.instructor.cobb;
public class Phase6Solution extends Phase6
{
	    static int round=0;
	    static int currentRoundScore;
	    static boolean die1,die2,die3,die4,die5,die6;
	    public static void checkRollResults()
	    {
	        currentRoundScore=0;
			if (getDie1Value()==round)
			{
			    die1=false;
			    currentRoundScore+=round;
			}
			else
			{	
			    die1=true;
				
			}	
			
	        if (getDie2Value()==round)
	        {
	         	die2=false;
	         	currentRoundScore+=round;
	        }
	        else
	        {	
		       die2=true;
			  
	       }
	        
		   if (getDie3Value()==round)
			   {
				   die3=false;
				   currentRoundScore+=round;
			   }
			   else
			   {	
				   die3=true;
				   
			   }	
		   if (getDie4Value()==round)
			   {
				   die4=false;
				   currentRoundScore+=round;
			   }
		   else
			   {	
				  die4=true;
				  
			  }
		   if (getDie5Value()==round)
			  {
				  die5=false;
				  currentRoundScore+=round;
			  }
		   else
			  {	
				 die5=true;
				 
			 }
					   
	    }
        public static void main(String[] args)
	    {   int totalPoints=0;
        	initializeMatch(); 
        	while (round<6)
        	{  
        		round++;
        		showMessage("Starting round "+ round);
        		rollAll();
        		int rolls=1;
        		//showMessage("roll1 results");
        		checkRollResults();
        		
        		//cadets will do it this way vs using arrays
        		rollDice(die1,die2,die3,die4,die5);
        		//showMessage("roll2 results");
				checkRollResults();
			
			    rollDice(die1,die2,die3,die4,die5);
        		//now calculate total
			    checkRollResults();
			    totalPoints+=currentRoundScore;
			    updateRoundScore(round,currentRoundScore);
			    updateTotalScore(totalPoints);
			    showMessage("You scored "+ currentRoundScore+" points this round and have a total of "+ totalPoints);
        	}
        	showMessage("You finished with "+ totalPoints);
         }
}   // end main


