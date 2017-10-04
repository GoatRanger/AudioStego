//
// Name: CDT Morel
// Section: I2
// Date: 02 Nov 2003
// Description: Selection Homework: Tiger Woods

public class TigerWoods extends eecs.Gui
{
    public static void main(String[] args)
    {
         // Code for the main program
         
    int club = 0;
    int distance = getInt("Enter the distance to the hole in yards.");
    if (distance <= 80 && distance >= 0)
   {
        club = (3);
   }
    if (distance <= 120 && distance >= 81)
    {
        club = (4);
   }
    if (distance <= 145 && distance >= 121)
    {
        club = (5);
    }
   
                           
    int elevation = getInt("Enter elevation in yards.");     
    if (elevation > 0 && elevation > 15)
    {
       club = club + 1;
       }
    if (elevation < 0 && elevation < -15)
    {
       club = club - 1;
    }
    else
    {
       club = club;
     }  
     
    double windSpeed = getDouble("Enter wind speed in MPH.");
    double windFactor = Math.pow((windSpeed/10.0),2) + Math.sqrt(5.0);//MAJ Morel, assistance given to programmer, email, 
    //West Point, NY, 05 November 2003. MAJ Morel provided me with an explanation as to why Math.pow and Math.sqrt had to
    //be used instead of the symbol ^ which I had previously used in my code.
    if (windFactor > 5.0 && windSpeed > 0.0)
        {
            club = club - 1;
            }
        if (windFactor > 5.0 && windSpeed < 0.0)
        {
            club = club + 1;
       }
    if (windFactor < 5.0)
    {
       club = club;
    }
       
    if (club == 1)
     {
       print("FS");
     }    if (club == 2)
    {
        print("LWII");
    }
    if (club == 3)
    {
        print("LW");
    }
    if (club == 4)
    {
       print("SW");
    }
    if (club == 5)
    {
        print("PW");
        }
    if (club == 6)
    {
        print("9I");
        }
    if (club == 7)
    {
        print("8I");
        }
    } //end main
} //end <ClassName>
