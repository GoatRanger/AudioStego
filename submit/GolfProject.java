//
// Name: Randall B. Chasten Jr.
// Section: A2
// Date: 09 November 2003
// Description: Golf Project

public class GolfProject extends eecs.Gui
/* 
Cadet Shobe,A-2,'07,oral discussion, West Point, NY, 8 November 2003
informed me that the best way to 
do the Java part was to imulate it after the PoolICE methods.
*/
{         static int distanceYds=0;
          static int elevation=0;
          static double windFactor=0;
          static int windSpeed=0;
          static int numberOfClub=0;
          public static void main(String[] args)
    {
         int distanceYds= getInt("Enter Distance:");
         if (distanceYds <=80)
         {
         numberOfClub=2;
         }
         else if (distanceYds >=81&& distanceYds <=120)
         {
         numberOfClub=3;
         }
         else if (distanceYds >=121&& distanceYds <=145)
         {
         numberOfClub=4;
         }
         
         calcWind();
         calcElevation();
         asignClubNames();
         }
    public static void calcWind()
    {
        int windSpeed= getInt("Enter Wind Speed Value:");
        double windFactor=((windSpeed/10)*(windSpeed/10)+ Math.sqrt(5)); 
        /*
         Cadet Shobe,A-2,'07,oral discussion, West Point, NY, 8 November 2003
        informed me of separating the (WindSpeed)^2 into multiplication.
        */
        if (windFactor>5&& windSpeed>0)
         {   
         numberOfClub= (numberOfClub -1);
         } 
         else if (windFactor>5&& windSpeed<0)
         {
         numberOfClub=(numberOfClub +1);
         }
    }     
    public static void calcElevation()
    {
         int elevation= getInt("Enter Elevation Value:");
         if (elevation>15)
         {
         numberOfClub= (numberOfClub +1);
         }
         else if (elevation<-15)
         {
         numberOfClub= (numberOfClub -1);  
         }
    }
    
    public static void asignClubNames()
    /*
    Cadet Shobe,A-2,'07,oral discussion, West Point, NY, 8 November 2003
     also informed me that I needed to name my clubs at the end using "if then"
     statments, this was giving me a hard time for a while.
    */
    {
        if (numberOfClub ==0)
        {
        printLine("FS");
        }
        if (numberOfClub ==1)
        {
        printLine("LWII");
        } 
        if (numberOfClub ==2)
        {
        printLine("LW");
        }            
        if (numberOfClub ==3)
        {
        printLine("SW");
        }   
        if (numberOfClub ==4)
        {
        printLine("PW");
        }            
        if (numberOfClub ==5)
        {
        printLine("9I");
        }            
        if (numberOfClub ==6)
        {
        printLine("8I");
        }        
      } 
} //end <ClassName>
