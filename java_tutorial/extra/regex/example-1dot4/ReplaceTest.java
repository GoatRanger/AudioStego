import java.util.regex.*; 

public final class ReplaceTest {
 
    private static String REGEX = "dog";
    private static String INPUT = "The dog says meow. All dogs say meow.";
    private static String REPLACE = "cat";
 
    public static void main(String[] argv) {
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(INPUT); // get a matcher object
        INPUT = m.replaceAll(REPLACE);
        System.out.println(INPUT);
    }
}

