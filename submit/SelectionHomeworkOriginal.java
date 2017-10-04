//
// Name: Russell T. Cowley 
// Section: A 02
// Date: 8 November 2003
// Description: SelectionHomework

public class SelectionHomeworkOriginal extends eecs.Gui
{
     static int distance;
      static int elevation;
      static double windSpeed;
     static double windFactor; 
    static int initialClub;
    static int clubAfterWind;
    static int clubAfterElevation;
   static String finalClub;
   public static void main(String[] args)
       //Code for the main program   
    {
     getUserInput();
     getInitialClub();
     getWindFactor();
     getClubAfterWind();
     getClubAfterElevation();
     getFinalClub(); 
     outputDisplay();
     }
    public static void getUserInput()
    {
     distance = getInt ("Enter the distance of the ball from the hole:");
     windSpeed = getDouble ("Enter the wind speed");     
     elevation = getInt ("Enter the elevation of the ball from the hole:");
     }
    public static void getInitialClub()
    {
     
     if (distance <= 145 && distance >= 121)
     {
        initialClub = (5);
     }
     else if (distance <=120 && distance >= 81)
     {
        initialClub = (4);
     }
     else if (distance <= 80 && distance > 0)
     {
         initialClub = (3);
     }
     else if (distance > 145 || distance <= 0) 
     {
         printLine ("ERROR!!  This program only works for distances between 0 and 145 yards.  Please try again.");
     }
     } 
   public static void getWindFactor()
    {
    if (windSpeed > 0)
    {
        windFactor = ((windSpeed / 10) * (windSpeed / 10) + Math.sqrt(5));
     }
     else if (windSpeed < -5)
     {
        windFactor = ((windSpeed / -10) * (windSpeed / 10) + Math.sqrt(5));
     } 
     }  
   public static void getClubAfterWind()
   {
   if (windFactor > 5)
    {
        clubAfterWind = (initialClub - 1);
    }
   else if(windFactor < -5)
   {
        clubAfterWind = (initialClub + 1);
   }
   else if (windFactor >= -5 && windFactor <= 5)
   {
        clubAfterWind = (initialClub);
   }
   }
   public static void getClubAfterElevation()
   {
   if (elevation > 15)
   {
        clubAfterElevation = (clubAfterWind + 1);
   }
   else if (elevation < -15)
   {
        clubAfterElevation = (clubAfterWind - 1);
   }
   else if (elevation > -15 && elevation < 15)
   {
        clubAfterElevation = (clubAfterWind);
   }
   }
    public static void getFinalClub()
   {
   if (clubAfterElevation == 1)
   {
        finalClub = ("FS");
   }
   else if (clubAfterElevation == 2)
   {
        finalClub = ("LWII");
   }
   else if (clubAfterElevation == 3)
   {
        finalClub = ("LW");
   }
   else if (clubAfterElevation == 4)
   {
        finalClub = ("SW");
   }
   else if (clubAfterElevation == 5)
   {
        finalClub = ("PW");
   }
   else if (clubAfterElevation == 6)
   {
        finalClub = ("9I");
   }
   else if (clubAfterElevation == 7)
   {
        finalClub = ("8I");
   }
   }
      public static void outputDisplay()
   {
  printLine (finalClub);
  }
   }
     //end main
 //end SelectionHomeworkOriginal
