
public class TigerO extends eecs.Gui 
{
    static int elevationTotal;
    static int distanceToPin;
    static int windSpeed;
    static int clubPart1;
    static int clubPart2;
    static int clubPart3;
    static int clubTotal;
    static double wind1;
    static double wind2;
    static double wind3;
    static double windFactor;
   
  public static void main(String[] args) 
	{
    input ();
    determineWindFactor();
    determineTheClubPart1();
    determineTheClubPart2();
    determineTheClubPart3();
    combineAnswers();
    outputClub();
    
    } // end main
  
  public static void input()
  {
  elevationTotal = getInt("What is the total elevation?");
  distanceToPin = getInt("What is the distance to the pin?");
  windSpeed = getInt("Positive=head, Negative= Tail");
  }
  
  public static void determineTheClubPart1()
  {
    if(distanceToPin <= 80)
    {
      clubPart1 = 3;
    }
    else if (distanceToPin >= 121)
    {
      clubPart1 = 5;
    }
    else
    {
        clubPart1 = 4;
    }
   }
  public static void determineWindFactor()
    {
    wind1 = (windSpeed / 10);
    wind2 = Math.pow(wind1,2);
    wind3 = Math.sqrt(5);
    windFactor = wind2 + wind3;
    }
  public static void determineTheClubPart2()
  {
  if (windFactor > 5 && windSpeed > 0)
    {
        clubPart2 = 1;
  }
  else if(windFactor > 5 && windSpeed < 0)
  {
    clubPart2 = -1;
  }
  else
  {
    clubPart2 = 0;
  }
 }
  public static void determineTheClubPart3()
  {
  if(elevationTotal > 15)
  {
    clubPart3 = 1;
  }
  else if(elevationTotal < -15)
  {
    clubPart3 = -1;
  }
  else
  {
    clubPart3 = 0;
  }
  }
  public static void combineAnswers()
  {
    clubTotal = clubPart1 + clubPart2 + clubPart3;
  }
  public static void outputClub()
  {
  if(clubTotal == 7)
  {
    printLine("FS");
  }
  else if(clubTotal == 6)
  {
    printLine("LWII");
  }
  else if(clubTotal == 5)
  {
    printLine("LW");
  }
  else if(clubTotal == 4)
  {
    printLine("SW");
  }
  else if(clubTotal == 3)
  {
    printLine("PW");
  }
  else if(clubTotal == 2)
  {
    printLine("9I");
  }
  else if(clubTotal == 1)
  {
    printLine("8I");
  }
 }

} // end Tiger