//
// Name: CDT. Cruz Ordoniez Jorge L.
// Section: A2
// Date: 11-10-03
// Description: Program to get the club that I have to use to have a perfect shot. 
// Notes: Mr. Sandrino Cueva, assistance given to the author. West Point, New York, 08 November 2003.
// Mr. Sandrino Cueva is my cousin, i asked him if he knew what to do when I get errors with ilegal espressions
// he told me to check the line in its simplest form, I did it and I realized that thre where a couple 
// missed  parenthesis.
public class ProjectTwo extends eecs.Gui
{
    public static void main(String[] args)
    {
         // Code for the main program
int distanceHole=getInt("Please enter the distance to the hole");
int elevationDiference=getInt("Please enter the elevation diference");
double speedOfTheWind=getDouble("This is the last step, please enter the wind speed");
double WF=(((speedOfTheWind/10)*(speedOfTheWind/10))+ Math.sqrt(5));

if ((distanceHole > 0 && distanceHole <= 80) && ((Math.abs(elevationDiference)) > 15) && (elevationDiference < -15) && (WF > 5) && (speedOfTheWind < 0))
{
print ("LW");
}
else if ((distanceHole > 0 && distanceHole <= 80) && ((Math.abs(elevationDiference)) > 15) && (elevationDiference < -15) && (WF > 5) && (speedOfTheWind > 0)) 
{
print ("FS");
}
else if ((distanceHole > 0 && distanceHole <= 80) && ((Math.abs(elevationDiference)) > 15) && (elevationDiference < -15) && (WF < 5))
{
print ("LWII");
}
else if ((distanceHole > 0 && distanceHole <= 80) && ((Math.abs(elevationDiference)) > 15) && (elevationDiference > 15) && (WF > 5) && (speedOfTheWind < 0))
{
print ("PW");
}
else if ((distanceHole > 0 && distanceHole <= 80) && ((Math.abs(elevationDiference)) > 15) && (elevationDiference > 15) && (WF > 5) && (speedOfTheWind > 0))
{
print ("LW");
}
else if ((distanceHole > 0 && distanceHole <= 80)&&((Math.abs(elevationDiference)) > 15) && (elevationDiference > 15) && (WF < 5))
{
print ("SW");
}
else if ((distanceHole > 0 && distanceHole <= 80) && ((Math.abs(elevationDiference)) < 15) && (WF > 5) && (speedOfTheWind < 0))
{
print ("SW");
}
else if ((distanceHole > 0 && distanceHole <= 80) && ((Math.abs(elevationDiference)) < 15) && (WF > 5) && (speedOfTheWind > 0))
{
print ("LWII");
}
else if ((distanceHole > 0 && distanceHole <= 80) && ((Math.abs(elevationDiference)) < 15) && (WF < 5))
{
print ("LW");
}
else if ((distanceHole >= 81 && distanceHole <= 120) && ((Math.abs(elevationDiference)) > 15) && (elevationDiference < -15) && (WF > 5) && (speedOfTheWind < 0))
{
print ("SW");
}
else if ((distanceHole >= 81 && distanceHole <= 120) && ((Math.abs(elevationDiference)) > 15) && (elevationDiference < -15) && (WF > 5) && (speedOfTheWind > 0))
{
print ("LWII");
}
else if ((distanceHole >= 81 && distanceHole <= 120) && ((Math.abs(elevationDiference)) > 15) && (elevationDiference < -15) && (WF < 5))
{
print ("LW");
}
else if ((distanceHole >= 81 && distanceHole <= 120) && ((Math.abs(elevationDiference)) > 15) && (elevationDiference > 15) && (WF > 5) && (speedOfTheWind < 0))
{
print ("9I");
}
else if ((distanceHole >= 81 && distanceHole <= 120) && ((Math.abs(elevationDiference)) > 15) && (elevationDiference > 15) && (WF > 5) && (speedOfTheWind > 0))
{
print ("SW");
}
else if ((distanceHole >= 81 && distanceHole <= 120) && ((Math.abs(elevationDiference)) > 15) && (elevationDiference > 15) && (WF < 5))
{
print ("PW");
}
else if ((distanceHole >= 81 && distanceHole <= 120) && ((Math.abs(elevationDiference)) < 15) && (WF > 5) && (speedOfTheWind < 0))
{
print ("PW");
}
else if ((distanceHole >= 81 && distanceHole <= 120) && ((Math.abs(elevationDiference)) < 15) && (WF > 5) && (speedOfTheWind > 0))
{    
print ("LW");
}
else if ((distanceHole >= 81 && distanceHole <= 120) && ((Math.abs(elevationDiference)) < 15) && (WF < 5))
{
print ("SW");
}
else if ((distanceHole > 120) && ((Math.abs(elevationDiference)) > 15) && (elevationDiference < -15) && (WF > 5) && (speedOfTheWind < 0))
{
print ("PW");
}
else if ((distanceHole > 120) && ((Math.abs(elevationDiference)) > 15) && (elevationDiference < -15) && (WF > 5) && (speedOfTheWind > 0))
{
print ("LW");
}
else if ((distanceHole > 120) && ((Math.abs(elevationDiference)) > 15) && (elevationDiference < -15) && (WF < 5))
{
print ("SW");
}
else if ((distanceHole > 120) && ((Math.abs(elevationDiference)) > 15) && (elevationDiference > 15) && (WF > 5) && (speedOfTheWind < 0))
{
print ("8I");
}
else if ((distanceHole > 120) && ((Math.abs(elevationDiference)) > 15) && (elevationDiference > 15) && (WF > 5) && (speedOfTheWind > 0))
{
print ("PW");
}
else if ((distanceHole > 120) && ((Math.abs(elevationDiference)) > 15) && (elevationDiference > 15) && (WF < 5))
{
print ("9I");
}
else if ((distanceHole > 120) && ((Math.abs(elevationDiference)) < 15) && (WF > 5) && (speedOfTheWind < 0))
{
print ("9I"); 
}
else if ((distanceHole > 120) && ((Math.abs(elevationDiference)) > 15) && (elevationDiference < -15) && (WF > 5) && (speedOfTheWind > 0))
{
print ("SW");
}
else if  ((distanceHole > 120) && ((Math.abs(elevationDiference)) > 15) && (elevationDiference < -15) && (WF < 5))
{
print ("PW");
}
else
{
}
} //end main
} //end class ProjectTwo
