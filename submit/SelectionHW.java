//
// Name: CDT PVT Cheval
// Section: A
// Date: 10 NOV 2003 (Due date)
// Description: Selection Homework

public class SelectionHW extends eecs.Gui
{
    
    public static void main(String[] args)
    {   
    int distance = getInt ("Enter distance, between 0 and 146 yards");
    int elevation = getInt ("Enter the elevation from the hole to the ball, giving a negative value if the slop is downhill");
    double windspeed = getDouble ("Enter wind speed for calculation of wind factor, giving a negative windspeed for a head wind.");
    double windfactor = ((windspeed/10)*(windspeed/10)+Math.sqrt(5));
    if (distance<=80 && elevation > -15 && elevation < 15 && windfactor>5)
    {
        print ("LWII");
    }
    if (distance<=80 && elevation > -15 && elevation < 15 && windfactor<-5)
    {
        print ("SW");
    }
    if (distance<=80 && elevation > 15 && windfactor>-5 && windfactor <5)
    {
        print ("SW");
    }
    if (distance<=80 && elevation <-15 && windfactor>-5 && windfactor <5)
    {
        print ("LWII");
    }
    if (distance<=80 && elevation > -15 && elevation < 15 && windfactor>-5 && windfactor <5)
    {
        print ("LW");
    }
    if (distance<0 || distance>146)
    {
        print ("Program is not designed for such a distance");
    }
    if (distance<=80&&elevation <-15&&windfactor<-5)
    {
        print ("LW");
    }
    if (distance<=80&&elevation>15&&windfactor>5)
    {
        print (" LW ");
    }
    if (distance<=80&&elevation>15&&windfactor<-5)  
    {
        print ("PW");
    }
    if (81 <= distance && distance <= 120&&elevation<-15&&windfactor>5)
    {
        print ("SW");
    }
    if (81 <= distance && distance <= 120 && elevation<-15 && windfactor<-5)
    {
        print ("LWII");
    }
    if (81<=distance && distance <=120 && elevation>15 && windfactor>5)
    {
        print ("SW");
    }
    if (81<=distance  && distance <=120 && elevation>15 && windfactor<-5)
    {
        print (" 9I ");
    }
    if (81 <= distance && distance <= 120 && elevation > -15 && elevation < 15 && windfactor>5)
    {
        print ("LW");
    }
    if (81 <= distance && distance <= 120 && elevation > -15 && elevation < 15 && windfactor<-5)
    {
        print ("PW");
    }
    if (81 <= distance && distance <= 120 && elevation > 15 && windfactor>-5 && windfactor <5)
    {   
        print ("PW");
    }
    if (81 <= distance && distance <= 120 && elevation <-15 && windfactor>-5 && windfactor <5)
    {
        print ("LW");
    }   
    if (81 <= distance && distance <= 120 && elevation > -15 && elevation < 15 && windfactor>-5 && windfactor <5)
    {
        print ("SW");
    }    
    if (121<=distance && distance <=145 && elevation<-15 && windfactor<-5)
    {
        print ("PW");
    }
    if (121<=distance && distance <=145 && elevation<-15 && windfactor>5)
    {
        print ("LW");
    }
    if (121<=distance && distance <=145 && elevation>15 && windfactor<-5)
    {
        print ("8I");
    }
    if (121<=distance && distance <=145 && elevation>15 && windfactor>5)
    {
        print ("PW");
    }
    if (121<=distance && distance <=145 && elevation > -15 && elevation < 15 && windfactor>5)
    {
        print ("SW");
    }
    if (121<=distance && distance <=145 && elevation > -15 && elevation < 15 && windfactor<-5)
    {
        print ("9I");
    }
    if (121<=distance && distance <=145 && elevation > 15 && windfactor>-5 && windfactor <5)
    {
        print ("9I");
    }
    if (121<=distance && distance <=145 && elevation <-15 && windfactor>-5 && windfactor <5)
    {
        print ("SW");    
    }
    if (121<=distance && distance <=145 && elevation > -15 && elevation < 15 && windfactor>-5 && windfactor <5)
    {
        print ("PW");
    }
    }
    }
   
     //endMain
//end SelectionHW