package dji.pilot.publics.objects;

import android.content.Context;
import android.content.SharedPreferences;

public class g {
    public static SharedPreferences a(Context context) {
        return context.getSharedPreferences(context.getPackageName(), 0);
    }

    public static boolean a(Context context, String str, int i) {
        return a(context).edit().putInt(str, i).commit();
    }

    public static boolean a(Context context, String str, long j) {
        return a(context).edit().putLong(str, j).commit();
    }

    public static boolean a(Context context, String str, float f) {
        return a(context).edit().putFloat(str, f).commit();
    }

    public static boolean a(Context context, String str, boolean z) {
        return a(context).edit().putBoolean(str, z).commit();
    }

    public static boolean a(Context context, String str, String str2) {
        return a(context).edit().putString(str, str2).commit();
    }

    public static int b(Context context, String str, int i) {
        return a(context).getInt(str, i);
    }

    public static long b(Context context, String str, long j) {
        return a(context).getLong(str, j);
    }

    public static float b(Context context, String str, float f) {
        return a(context).getFloat(str, f);
    }

    public static boolean b(Context context, String str, boolean z) {
        return a(context).getBoolean(str, z);
    }

    public static String b(Context context, String str, String str2) {
        return a(context).getString(str, str2);
    }

    public static boolean a(Context context, String str) {
        return a(context).edit().remove(str).commit();
    }
}
