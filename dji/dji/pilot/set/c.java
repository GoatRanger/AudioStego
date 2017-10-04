package dji.pilot.set;

import java.util.regex.Pattern;

public class c {
    private static long a;

    public static boolean a() {
        long currentTimeMillis = System.currentTimeMillis();
        long j = currentTimeMillis - a;
        if (0 < j && j < 800) {
            return true;
        }
        a = currentTimeMillis;
        return false;
    }

    public static boolean a(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        return false;
    }

    public static boolean b(String str) {
        String str2 = "[A-Z0-9a-z-_ ]{1,32}";
        return Pattern.compile("[A-Z0-9a-z-_ ]{1,32}").matcher(str).matches();
    }

    public static boolean c(String str) {
        String str2 = "[A-Z0-9a-z]{8,63}";
        return Pattern.compile("[A-Z0-9a-z]{8,63}").matcher(str).matches();
    }
}
