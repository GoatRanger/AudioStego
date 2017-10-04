
public class Tiger2 extends eecs.Gui 
{
    static int distanceToHole;
    static double windSpeed;
    static int changeInElevation;
    static double windFactor;
    static int baseClub;
    static int ballLie;
     
  public static void main(String[] args) 
	{
    // method calls
    getUserInput ();
    determineBaseClub ();
    determineWindFactor ();
    changeBaseClubByUsingWindFactor ();
    changeBaseClubByUsingChangeInElevation ();
    changeBaseClubByUsingBallLie ();
    outputClubSelection ();
    
    // end method calls
       }
  // end main
  
    // methods
    public static void getUserInput ()
        {
    distanceToHole = getInt("How many yards to pin?");
    changeInElevation = getInt("What is the change in elevation from the ball to the pin (in yards)? (positive values if uphill, negative values if downhill)");
    windSpeed = getDouble("What is the wind speed (in mph)? (negative values if head wind, positive values if tail wind)");
    ballLie = chooseButton("Where does ball lie?", "Rough", "Fairway", "Divot");
        }
    
    public static void determineBaseClub ()
        {
        if (distanceToHole >= 146)
            {
           showError("This program only selects clubs for shots within 145 yards of the pin, genius! Try again there, slick.");
            }
        else
            { 
            if (121 <= distanceToHole && distanceToHole <= 145)
                {
                baseClub = 5;
                }
            else
                {
                if (81 <= distanceToHole && distanceToHole  <= 120)
                    {
                    baseClub = 4;
                    }
                else
                    {
                    if (1 <= distanceToHole && distanceToHole <= 80)
                        {
                        baseClub = 3;
                        }
                    else
                        {
                        if (distanceToHole == 0)
                            {
                            showError("Um, yeah, you're in the hole genius.  Pick whatever club you would like, but remember this program has nothing to do with human stupidity.");
                            }
                        else
                            {
                            showError("So there are negative yards between the ball and the hole? Yeah i didn't think so.  Try again, whiz kid.");
                            }
                        }
                    }
                }
            }
        }
    
    public static void determineWindFactor ()
        {
        windFactor = windSpeed/10;
        windFactor = windFactor*windFactor;
        windFactor = windFactor + (Math.sqrt(5));
        if (windSpeed < 0)
            {
            windFactor = -windFactor;
            }
            else
                {
                //do nothing
                }
        }
    
    public static void changeBaseClubByUsingWindFactor ()
        {
        if (windFactor > 5)
            {
            baseClub = baseClub - 1;
            }
            else
                {
                if (windFactor < -5)
                    {
                    baseClub = baseClub + 1;
                    }
                    else
                        {
                        // do nothing
                        }
                }
        }

    public static void changeBaseClubByUsingChangeInElevation ()
        {
        if (changeInElevation > 15)
            {
            baseClub = baseClub + 1;
            }
            else
                {
                if (changeInElevation < -15)
                    {
                    baseClub = baseClub - 1;
                    }
                    else
                        {
                        // do nothing
                        }
                }   
        }
    
    public static void changeBaseClubByUsingBallLie ()
        {
        if (ballLie == BUTTON1)
            {
            baseClub = baseClub - 1;
            }
            else if (ballLie == BUTTON3)
                {
                baseClub = baseClub + 1;
                }
                else
                {
                // do nothing
                }
        }
    
    public static void outputClubSelection ()
        {
        if (baseClub == 0)
            {
            printLine("P");
            }
        else if (baseClub == 1)
            {
            printLine("FS");
            }
        else if (baseClub == 2)
            {
            printLine("LWII");
            }
        else if (baseClub == 3)
            {
            printLine("LW");
            }
        else if (baseClub == 4)
            {
            printLine("SW");
            }
        else if (baseClub == 5)
            {
            printLine("PW");
            }
        else if (baseClub == 6)
            {
            printLine("9I");
            }
        else if (baseClub == 7)
            {
            printLine("8I");
            }
        else if (baseClub == 8)
            {
            printLine("7I");
            }
        else
            {
            showError("Yeah, um, just try again ok, and get it right please...");
            }
        
        }
    

} // end Tiger
