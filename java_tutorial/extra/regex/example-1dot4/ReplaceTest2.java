import java.util.regex.*;
 
public final class ReplaceTest2 {
 
    private static String REGEX = "a*b";
    private static String INPUT = "aabfooaabfooabfoob";
    private static String REPLACE = "-";
 
    public static void main(String[] argv) {
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(INPUT); // get a matcher object
        INPUT = m.replaceAll(REPLACE);
        System.out.println(INPUT);
    }
}

