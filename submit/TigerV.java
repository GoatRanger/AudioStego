
public class TigerV extends eecs.Gui 
{
  public static void main(String[] args) 
 {

 //Beginning of variable definitions and calculations.
  double D = getDouble("Enter the distance from the ball to the pin:");
  double E = getDouble("Enter the current elevation difference:");
  double W = getDouble("Enter the current wind speed and direction:");
  
  double eabs = Math.abs(E);
  double WF = (Math.pow((W / 10), 2) + Math.sqrt(5));
 //End of variable definitions and calculations.
  
   { //Beginning of distance group #1 (121-145 yards).
  if (D >= 121 && D <= 145)
   {
   if (eabs > 15)// eab > 15
    {
    if (E > 0)
     {
     if (WF > 5 && W > 0)
      {
      printLine("PW");
      }
     if (WF > 5 && W <= 0)
      {
      printLine("8I");
      }
     if (WF <= 5)
       {
       printLine("9I");
       }
      
     }
    if (E <= 0)
    {        // E < 0 
     if (WF > 5 && W > 0)
       {
       printLine("LW");
       }
       if (WF > 5 && W <= 0)
        {
        printLine("PW");
        }
       if (WF <= 5)
        {
        printLine("SW");
        }
    }
    }
         //eabs < 15
    if (eabs <= 15)
     {
     if (WF > 5 && W > 0)
      {
      printLine("SW");
      }
      if (WF > 5 && W <= 0)
       {
       printLine("9I");
       }
      if (WF <= 5)
       {
       printLine("PW");
       }
       
     }
   }  
  }  //End of distance group #1 (121-145 yards).
  
  
  {//Beginning of distance group #2 (81-120 yards).
     if (D >= 81 && D < 121)
   {
   if (eabs > 15)//eabs > 15
    {
    if (E > 0)
     {
     if (WF > 5 && W > 0)
      {
      printLine("SW");
      }
     if (WF > 5 && W <= 0)
      {
      printLine("9I");
      }
     if (WF <= 5)
       {
       printLine("PW");
       }
      
     }
    if (E <= 0)
    {        // E < 0 
     if (WF > 5 && W > 0)
       {
       printLine("LWII");
       }
       if (WF > 5 && W <= 0)
        {
        printLine("SW");
        }
       if (WF <= 5)
        {
        printLine("LW");
        }
    }
    }
         //eabs < 15
    if (eabs <= 15)
     {
     if (WF > 5 && W > 0)
      {
      printLine("LW");
      }
      if (WF > 5 && W < 0)
       {
       printLine("PW");
       }
      if (WF <= 5)
       {
       printLine("SW");
       }
       
     }
   }  
  }  //End of distance group #2 (81-120 yards).
  
  
   {//Beginning of distance group #3 (0-80 yards).
     if (D >= 0 && D < 81)
   {
   if (eabs > 15)//eabs > 15
    {
    if (E > 0)
     {
     if (WF > 5 && W > 0)
      {
      printLine("LW");
      }
     if (WF > 5 && W <= 0)
      {
      printLine("PW");
      }
     if (WF <= 5)
       {
       printLine("SW");
       }
      
     } // E < 0 
    if (E <= 0)
    {       
     if (WF > 5 && W > 0)
       {
       printLine("FS");
       }
       if (WF > 5 && W <= 0)
        {
        printLine("LW");
        }
       if (WF <= 5)
        {
        printLine("LWII");
        }
    }
    }
         //eabs < 15
    if (eabs <= 15)
     {
     if (WF > 5 && W > 0)
      {
      printLine("LWII");
      }
      if (WF > 5 && W < 0)
       {
       printLine("SW");
       }
      if (WF <= 5)
       {
       printLine("LW");
       }
       
     }
   }  
  }  //End of distance group #3 (0-80 yards).
  
  if (D < 0 || D > 145)
  {
  showError("Invalid input!");
  } 
    
 } // end main

} // end Tiger
