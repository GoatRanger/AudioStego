package eecs.instructor.cobb;
public class seven27Solution extends Seven27
{
        public static void main(String[] args)
	    {
	        boolean playAgain = true;
	        boolean playNewRound= false;
	        boolean playerBust=false;
	        boolean playerTookCard=true;
	        boolean dealerBust=false;
	        boolean dealerTookCard=true;
	        double dealerDifference=0.0;
	        double humanDifference=0.0;
	        printLine("Welcome to the Game of 7-27!");
	        printLine("This game is played as follows:");
	        printLine("Similar to BlackJack or 21, you are trying to be closer to 7 or 27 than the dealer");
	        printLine("without going over.  Face cards are only worth .5 point ");
	        
	        
	      
	        {
	            printLine("");
	            printLine("Let's Play!!!");
	            printLine("-------------");
                Seven27.	
	        //    reset();  //clears marbles/cards off the table..1 by 1
	            //initialize match
	            initializeMatch();
	            while (doBothPlayersHaveMoney()){
	               
	            //New Round();
	            
	            
				initializeNewRound();
				playerBust=false;
				dealerBust=false;
				playerTookCard=true;
				dealerTookCard=true;
	           //			Human Play
				while (playerTookCard & !playerBust){
				   if (getPlayerCardTotal()>27.0){
				         playerBust=true;
				         printLine("PlayerBusted");}
				   else
				      {
				  	 int choice = chooseButton("Do you want another card?", "Yes", "No");
	               	 if (choice != BUTTON1)
	                   playerTookCard=false;
	                 else
	                   givePlayerACard();
	                    }							
				}
	//		Dealer Play();
					 
	                 if (!playerBust){
	                 	flipDownCard();
	                    while (dealerTookCard & !dealerBust){
	                        if (getDealerCardTotal()>27)
	                        	dealerBust=true;
	                    	else{
	                    	  if ((getDealerCardTotal()>7 & getDealerCardTotal()<22) || (getDealerCardTotal()<=5)){
	                    	  
	                    	      giveDealerACard();	
								printLine("Dealer "+ getDealerCardTotal());
	                    	   }
	                    	  else
	                    	     dealerTookCard=false;
	                        }
	                 	
	                   }
	                 }
	                 printLine("finished cards, now figure out winner");
					  if (playerBust){
					     showMessage("Dealer wins the round ");
					     givePotToDealer(); 
					  }
					  else if (dealerBust){
					  	showMessage("You win the round");
					  	givePotToPlayer();
					  }
					  else {
					  	
					  	
					  	if (getDealerCardTotal()>7)
					  		 dealerDifference=27-getDealerCardTotal();
					  	
					  	else 
					  	    dealerDifference=7-getDealerCardTotal();
					  	    
					  	if (getPlayerCardTotal()>7)
					  	     humanDifference=27-getPlayerCardTotal(); 
					  	else
					  	     humanDifference=7-getPlayerCardTotal();  
					  
					    if (humanDifference==dealerDifference){
					        showMessage("We have a Tie"); 
					        splitPot();
					    }
					    else if (humanDifference<dealerDifference){		    
					        showMessage("You win the Round");
					        givePotToPlayer();
					        }
					    else {
					    
					        showMessage("Dealer wins the Round"); 
						   givePotToDealer();}
					  
					  
	             }
	                
	             //   reset();
				updateDisplay();
	                
					  //Update Display();
	             }
						
								//	 int choice = chooseButton("Do you want to play again?", "Yes", "No");
						if (getDealerBank()>0)
						    showMessage("DEALER wins the MATCH!!!!!");
						else
						    showMessage("You win the MATCH");
							//	 if (choice !=BUTTON1) 
								//	 playAgain = false;
										 
	           
	
	        // end of while(playAgain)
	        //showMessage("Game Over!");
	        }
	    }
}
    // end main


