package dji.common.airlink;

import java.util.regex.Pattern;

public class AirLinkUtils {
    public static boolean verifySSID(String str) {
        if (str == null || str.length() > 30 || !Pattern.compile("[A-Za-z0-9-_]{1,32}").matcher(str).matches()) {
            return true;
        }
        return false;
    }

    public static int transformRadioSignal(int i) {
        int i2 = 0;
        if (i != 0) {
            i2 = 101 - ((int) Math.sqrt(Math.pow(10.0d, (double) (((float) (Math.abs(i) - 53)) / 10.0f))));
            if (i2 > 100) {
                return 100;
            }
            if (i2 < 5) {
                return 5;
            }
        }
        return i2;
    }
}
