package dji.pilot2.utils;

import android.content.Context;
import android.content.res.Configuration;
import java.util.Locale;

public class c {
    private static final String a = "v2_explore_flag_";

    public static String a(String str, Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        String str2 = "";
        if (str != null) {
            return new Locale("", str).getDisplayCountry(configuration.locale);
        }
        return str2;
    }

    public static int a(Context context, String str) {
        if (str == null) {
            return 0;
        }
        return context.getResources().getIdentifier(a + str.toLowerCase(), "drawable", context.getPackageName());
    }
}
