import java.util.regex.*;

public final class SplitTest {

    private static String REGEX = ":";
    private static String INPUT = "one:two:three:four:five";
    
    public static void main(String[] argv) {
        Pattern p = Pattern.compile(REGEX);
        String[] items = p.split(INPUT);
        for(int i=0; i<items.length;i++) {
            System.out.println(items[i]);
        }
    }
}
