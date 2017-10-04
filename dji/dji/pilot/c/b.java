package dji.pilot.c;

import android.content.Context;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class b {
    public static String a = "map_type_key";
    public static String b = null;
    public static final int c = 300;
    public static final String d = "DJI Album/";
    public static final String e = "CACHE_IMAGE/";
    public static final String f = "http://upgrade.dj2006.net/upgrade/inspireinfo";
    public static String[] g = new String[]{"https://upgrade.bgcentre.com/links/links/pilot_v2", "http://upgrade.dj2006.net/redirect/links/GO_Test", "http://upgrade.dj2006.net/redirect/links/GO_Debug"};
    public static String[] h = new String[]{"https://upgrade.bgcentre.com/links/links/pilot_br", "http://upgrade.dj2006.net/redirect/links/pliot_br_DEBUG", "http://upgrade.dj2006.net/redirect/links/pliot_br_DEBUG"};
    public static final String i = "https://upgrade.bgcentre.com/getdayv3";
    public static final int j = 1765;

    public static void a(Context context) {
        String b = b(context);
        if (b != null) {
            g[0] = g[0] + "?os=android&build=" + b;
            h[0] = h[0] + "?os=android&build=" + b;
        }
    }

    public static String b(Context context) {
        if (b != null) {
            return b;
        }
        try {
            String readLine = new BufferedReader(new InputStreamReader(context.getAssets().open("build"))).readLine();
            if (readLine == null) {
                return readLine;
            }
            int length = readLine.length();
            if (length > 0 && readLine.substring(length - 1, length).equals(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)) {
                readLine = readLine.substring(0, length - 1);
            }
            b = readLine;
            return readLine;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
