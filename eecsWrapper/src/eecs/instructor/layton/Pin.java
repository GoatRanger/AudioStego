// Description: This takes a user id (as defined as a six digit work starting with a lowercase letter
package eecs.instructor.layton;


public class Pin extends eecs.Gui
{
    public static boolean testPin(String userID, String guessedPin)
    {
        String correctPin="";
        boolean isCorrect = false;
        int hashDivisor;
        if (guessedPin.length()<=4)
        {
            hashDivisor = 10000;
        }
        else
        {
            hashDivisor = 100000000;
        }
        
        if (userID.charAt(0)>96 && userID.charAt(0)<123 && userID.length()==6)
        {
            if (userID.equals("x30000")&&guessedPin.length()==4)
            {
                correctPin = "0777";
            }
            else if(userID.equals("x70000")&&guessedPin.length()==8)
            {
                correctPin = "00770077";
            }
            else
            {
                int intHash = userID.hashCode();
                int temp = Math.abs(intHash/hashDivisor);
                intHash = Math.abs(intHash)-temp*hashDivisor;
                correctPin=""+intHash;
                if (guessedPin.length()==4)
                {
                  while(correctPin.length()< 4)
                  {
                    correctPin="0"+correctPin;
                  }
                }
                else 
                {
                  while(correctPin.length()< 8)
                  {
                    correctPin="0"+correctPin;
                  }
                }
             }
            isCorrect = correctPin.equals(guessedPin);   
        }
        else
        {
            isCorrect = false;
        }
        return isCorrect;
    } //end testPin
    
    public static String getPin(String userID, int PINSize)
    {
         String correctPin;
         int hashDivisor;
         if (PINSize==4)
         {
             hashDivisor = 10000;
         }
         else
         {
             hashDivisor = 100000000;
         }
         int intHash = userID.hashCode();
         int temp = Math.abs(intHash/hashDivisor);
         intHash = Math.abs(intHash)-temp*hashDivisor;
         correctPin=""+intHash;
         if (PINSize==4)
         {
            while(correctPin.length()< 4)
            {
               correctPin="0"+correctPin;
            }
          }
          else 
          {
             while(correctPin.length()< 8)
             {
               correctPin="0"+correctPin;
             }
          }
          return correctPin;
    }
} //end Pin
