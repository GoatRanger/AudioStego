
public class TigerH extends eecs.Gui 
{
  public static void main(String[] args) 
	{
    // type your code here
   
   /*8i = 7
    9I = 6
    PW = 5
    SW = 4
    LW = 3
    LWII = 2
    FS = 1*/
    
//Base Club decision
        int club;
        int distanceToHole = getInt("Enter the distance to the pin in yards:");
       
        if (distanceToHole >= 121 && distanceToHole <= 146)
        {
            club = 5;
        }
        else if (distanceToHole > 81 && distanceToHole < 120)
        {
            club = 4;   
        }
        else
        {
            club = 3;
        }   

//Elevation Decision       
        int elevationDifference = getInt("Enter the difference of elevation from the hole to the ball:");
        
        if (elevationDifference >= -15 && elevationDifference <= 15)//club = 5,4,3 //Club stays the same as base club;
            {
            }
        else if(elevationDifference > 15)//If postive shot = uphill
         {
            club = club + 1; //club is 91, PW, SW
         }
         else
         {
            club = club - 1; //club is SW, LW, LWII
         }   

//Calculate WindSpeed by putting windspeed into equation
        double windSpeed = getDouble("Enter the windspeed in miles-per-hour:");
        double windStatus;
        windStatus = (windSpeed / 10) * (windSpeed / 10);
        double windFactor;
        windFactor = windStatus + Math.sqrt(5);

//WindSpeed Decision
        if (windFactor > 5) //Check whether windspeed is positive  
        {
                
        if (windSpeed > 0) //positive windspeed (tailwind) (PW,SW,LW)(SW,LW,LWII)(LW,LWII,FS)
            {
                club = club - 1; 
            }
            
            else  //negative windspeed (headwind) (8I,9I,PW)(9I,PW,SW)(PW,SW,LW)
            {        
                club = club + 1;  
            }
        }    
        else if (windFactor < 5)
        {
        }


// Determine the final club
        if (club == 1)
                {
                print("FS");
                }
            else if(club == 2)
                {           
                print("LWII");
                }
            else if(club == 3)
                {
                print("LW");
                }
            else if(club == 4)
                {           
                print("SW");
                }
            else if(club == 5)
                {
                print("PW");
                }
            else if(club == 6)
                {
                print("9I");
                }  
            else if(club == 7)
                {
                print("8I");
                } 
            
  } // end main

} // end Tiger
