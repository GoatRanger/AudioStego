import java.util.regex.*;

public final class MatcherTest {

    private static final String REGEX = "\\bdog\\b";
    private static final String INPUT = "dog dog dog doggie dogg";

    public static void main(String[] argv) {
       Pattern p = Pattern.compile(REGEX);
       Matcher m = p.matcher(INPUT); // get a matcher object
       int count = 0;
       while(m.find()) {
           count++;
           System.out.println("Match number "+count);
           System.out.println("start(): "+m.start());
           System.out.println("end(): "+m.end());
       }
    }
}

