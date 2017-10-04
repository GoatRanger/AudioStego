//
// Name: Meyer, Robert J
// Section: I2
// Date: 10 November 2003
// Description: Tiger Woods Caddie Problem

public class TigerWoodsA extends eecs.Gui
{
    public static void main(String[] args)
    
         // Code for the main program 
    {
    int yards=chooseButton("Distance to Hole?","121-145 Yards","81-120 Yards",
    "Less than 80 Yards");
    int headWind=chooseButton("Is it a head wind or tail wind?","Head","Tail");
    double windSpeed=getDouble("What is the wind speed?");
    double elevation=getDouble("What is the elevation difference (hole to ball)?");
    double windFactor=((Math.pow((windSpeed/10),2.0))+Math.sqrt(5.0));
    if (yards==BUTTON1 && windFactor>5 && headWind==BUTTON1 && elevation>15)
    {
        printLine("Use a Pitching Wedge");
    }
    else if (yards==BUTTON1 && windFactor>5 && headWind==BUTTON1 && 
    0 <= elevation && elevation<=15)
    {
        printLine("Use a Sand Wedge");
    }
    else if (yards==BUTTON1 && windFactor>5 && headWind==BUTTON1 && elevation<-15)
    {
        printLine("Use a Lob Wedge");
    }
    else if (yards==BUTTON1 && windFactor>5 && headWind==BUTTON1 && 
    -15 <= elevation && elevation<=0)
    {
        printLine("Use a Sand Wedge");
    }
    else if (yards==BUTTON1 && windFactor<=5 && headWind==BUTTON1 && elevation>15)
    {
        printLine("Use a 9 Iron");
    }
    else if (yards==BUTTON1 && windFactor<=5 && headWind==BUTTON1 && 
     0 <= elevation && elevation <= 15)
    {
        printLine("Use a Pitching Wedge");
    }
    else if (yards==BUTTON1 && windFactor<=5 && headWind==BUTTON1 && elevation<-15)
    {
        printLine("Use a Sand Wedge");
    }
    else if (yards==BUTTON1 && windFactor<=5 && headWind==BUTTON1 &&
     -15 <= elevation && elevation<=0)
    {
        printLine("Use a Pitching Wedge");
    }
    else if (yards==BUTTON1 && windFactor>5 && headWind==BUTTON2 && elevation>15)
    {
        printLine("Use an 8 Iron");
    }
     else if (yards==BUTTON1 && windFactor>5 && headWind==BUTTON2 && 
      0 <= elevation && elevation <=15)
    {
        printLine("Use a 9 Iron");
    }
     else if (yards==BUTTON1 && windFactor>5 && headWind==BUTTON2 &&  elevation<-15)
    {
        printLine("Use a Pitching Wedge");
    }
     else if (yards==BUTTON1 && windFactor<=5 && headWind==BUTTON2 && 
      -15 <= elevation && elevation<=0)
    {
        printLine("Use a 9 Iron");
    }
     else if (yards==BUTTON1 && windFactor<=5 && headWind==BUTTON2 &&  elevation>15)
    {
        printLine("Use a 9 Iron");
    }
     else if (yards==BUTTON1 && windFactor<=5 && headWind==BUTTON2 && 
      0 <= elevation && elevation<=15)
    {
        printLine("Use a Pitching Wedge");
    }
    else if (yards==BUTTON1 && windFactor<=5 && headWind==BUTTON2 && elevation<-15)
    {
        printLine("Use a Sand Wedge");
    }
     else if (yards==BUTTON1 && windFactor<=5 && headWind==BUTTON2 && 
      -15 <= elevation && elevation <= 0)
    {
        printLine("Use a Pitching Wedge");
    }  // end BUTTON1
    else if (yards==BUTTON2 && windFactor>5 && headWind==BUTTON1 && elevation>15)
    {
        printLine("Use a Sand Wedge");
    }
    else if (yards==BUTTON2 && windFactor>5 && headWind==BUTTON1 &&
     0 <= elevation && elevation<=15)
    {
        printLine("Use a Lob Wedge");
    }
    else if (yards==BUTTON2 && windFactor>5 && headWind==BUTTON1 && elevation<-15)
    {
        printLine("Use a Lob Wedge 2");
    }
    else if (yards==BUTTON2 && windFactor>5 && headWind==BUTTON1 &&
     -15 <= elevation && elevation<=0)
    {
        printLine("Use a Lob Wedge");
    }
    else if (yards==BUTTON2 && windFactor<=5 && headWind==BUTTON1 && elevation>15)
    {
        printLine("Use a Pitching Wedge");
    }
    else if (yards==BUTTON2 && windFactor<=5 && headWind==BUTTON1 && 
     0 <= elevation && elevation <= 15)
    {
        printLine("Use a Sand Wedge");
    }
    else if (yards==BUTTON2 && windFactor<=5 && headWind==BUTTON1 && elevation<-15)
    {
        printLine("Use a Lob Wedge");
    }
    else if (yards==BUTTON2 && windFactor<=5 && headWind==BUTTON1 &&
     -15 <= elevation && elevation<=0)
    {
        printLine("Use a Sand Wedge");
    }
    else if (yards==BUTTON2 && windFactor>5 && headWind==BUTTON2 && elevation>15)
    {
        printLine("Use a 9 Iron");
    }
     else if (yards==BUTTON2 && windFactor>5 && headWind==BUTTON2 && 
      0 <= elevation && elevation <=15)
    {
        printLine("Use a Pitching Wedge");
    }
     else if (yards==BUTTON2 && windFactor>5 && headWind==BUTTON2 &&  elevation<-15)
    {
        printLine("Use a Sand Wedge");
    }
     else if (yards==BUTTON2 && windFactor<=5 && headWind==BUTTON2 && 
      -15 <= elevation && elevation<=0)
    {
        printLine("Use a Pitching Wedge");
    }
     else if (yards==BUTTON2 && windFactor<=5 && headWind==BUTTON2 &&  elevation>15)
    {
        printLine("Use a Pitching Wedge");
    }
     else if (yards==BUTTON2 && windFactor<=5 && headWind==BUTTON2 && 
      0 <= elevation && elevation<=15)
    {
        printLine("Use a Sand Wedge");
    }
    else if (yards==BUTTON2 && windFactor<=5 && headWind==BUTTON2 && elevation<-15)
    {
        printLine("Use a Lob Wedge");
    }
     else if (yards==BUTTON2 && windFactor<=5 && headWind==BUTTON2 && 
      -15 <= elevation && elevation <= 0)
    {
        printLine("Use a Sand Wedge");
    } //end Button 2
    else if (yards==BUTTON3 && windFactor>5 && headWind==BUTTON1 && elevation>15)
    {
        printLine("Use a Lob Wedge");
    }
    else if (yards==BUTTON1 && windFactor>5 && headWind==BUTTON1 &&
     0 <= elevation && elevation<=15)
    {
        printLine("Use a Lob Wedge 2");
    }
    else if (yards==BUTTON3 && windFactor>5 && headWind==BUTTON1 && elevation<-15)
    {
        printLine("Use a Flop Shot");
    }
    else if (yards==BUTTON3 && windFactor>5 && headWind==BUTTON1 &&
     -15 <= elevation && elevation<=0)
    {
        printLine("Use a Lob Wedge 2");
    }
    else if (yards==BUTTON3 && windFactor<=5 && headWind==BUTTON1 && elevation>15)
    {
        printLine("Use a Sand Wedge");
    }
    else if (yards==BUTTON3 && windFactor<=5 && headWind==BUTTON1 && 
    0 <= elevation && elevation <= 15)
    {
        printLine("Use a Lob Wedge");
    }
    else if (yards==BUTTON3 && windFactor<=5 && headWind==BUTTON1 && elevation<-15)
    {
        printLine("Use a Lob Wedge 2");
    }
    else if (yards==BUTTON3 && windFactor<=5 && headWind==BUTTON1 &&
     -15 <= elevation && elevation<=0)
    {
        printLine("Use a Lob Wedge");
    }
    else if (yards==BUTTON3 && windFactor>5 && headWind==BUTTON2 && elevation>15)
    {
        printLine("Use a Pitching Iron");
    }
     else if (yards==BUTTON3 && windFactor>5 && headWind==BUTTON2 && 
      0 <= elevation && elevation <=15)
    {
        printLine("Use a Sand Wedge");
    }
     else if (yards==BUTTON3 && windFactor>5 && headWind==BUTTON2 &&  elevation<-15)
    {
        printLine("Use a Lob Wedge");
    }
     else if (yards==BUTTON3 && windFactor<=5 && headWind==BUTTON2 && 
      -15 <= elevation && elevation<=0)
    {
        printLine("Use a Sand Wedge");
    }
     else if (yards==BUTTON3 && windFactor<=5 && headWind==BUTTON2 &&  elevation>15)
    {
        printLine("Use a Sand Wedge");
    }
     else if (yards==BUTTON1 && windFactor<=5 && headWind==BUTTON2 &&
      0 <= elevation && elevation<=15)
    {
        printLine("Use a Lob Wedge");
    }
    else if (yards==BUTTON1 && windFactor<=5 && headWind==BUTTON2 && elevation<-15)
    {
        printLine("Use a Lob Wedge 2");
    }
     else if (yards==BUTTON1 && windFactor<=5 && headWind==BUTTON2 && 
      -15 <= elevation && elevation <= 0)
    {
        printLine("Use a Lob Wedge");
    {
  } // end main
}
}} // end Tiger
   