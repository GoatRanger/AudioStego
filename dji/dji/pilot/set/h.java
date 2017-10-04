package dji.pilot.set;

import android.content.Context;
import android.content.SharedPreferences;

public class h {
    private static SharedPreferences a(Context context) {
        return context.getSharedPreferences(context.getPackageName(), 0);
    }

    public static boolean a(Context context, String str, int i) {
        return a(context).edit().putInt(str, i).commit();
    }

    public static int a(Context context, String str) {
        return a(context).getInt(str, 0);
    }

    public static int b(Context context, String str, int i) {
        return a(context).getInt(str, i);
    }

    public static boolean a(Context context, String str, String str2) {
        return a(context).edit().putString(str, str2).commit();
    }

    public static boolean a(Context context, String str, boolean z) {
        return a(context).edit().putBoolean(str, z).commit();
    }

    public static boolean b(Context context, String str) {
        return a(context).getBoolean(str, false);
    }

    public static boolean b(Context context, String str, boolean z) {
        return a(context).getBoolean(str, z);
    }

    public static String b(Context context, String str, String str2) {
        return a(context).getString(str, str2);
    }
}
