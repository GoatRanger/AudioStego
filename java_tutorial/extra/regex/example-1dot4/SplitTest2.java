import java.util.regex.*;

public final class SplitTest2 {

    private static String REGEX = "\\d";
         private static String INPUT = "one9two4three7four1five";

         public static void main(String[] argv) {
             Pattern p = Pattern.compile(REGEX);
             String[] items = p.split(INPUT);
             for(int i=0;i<items.length;i++) {
                 System.out.println(items[i]);
             }
         }

}


