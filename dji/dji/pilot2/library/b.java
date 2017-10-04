package dji.pilot2.library;

import android.content.Context;
import android.os.Environment;
import dji.pilot.usercenter.protocol.d;
import dji.pilot2.main.a.a;
import java.util.Locale;

public class b {
    private static final String a = "DJI Album";
    private static final String b = "DJI_RECORD";
    private static final String c = "RECORD_VOICE";
    private static final String d = "VideoEditor/segmentLibrary";
    private static final String e = "VideoEditor/segmentLibrary_hd";
    private static final String f = "CACHE_IMAGE";
    private static final String g = "DJI Album";
    private static final String h = "/DJI/Camera";

    public static String a(Context context) {
        return Environment.getExternalStorageDirectory() + "/DJI/" + context.getPackageName() + d.t + e;
    }

    public static String b(Context context) {
        return com.dji.frame.c.d.a(context, "DJI Album");
    }

    public static String c(Context context) {
        return com.dji.frame.c.d.a(context, b);
    }

    public static String d(Context context) {
        return com.dji.frame.c.d.a(context, d);
    }

    public static String e(Context context) {
        return com.dji.frame.c.d.a(context, c);
    }

    public static String f(Context context) {
        return com.dji.frame.c.d.a(context, f);
    }

    public static String g(Context context) {
        return com.dji.frame.c.d.a(context, "DJI Album");
    }

    public static String h(Context context) {
        return Environment.getExternalStorageDirectory() + "/DJI/Camera";
    }

    public static String i(Context context) {
        return Environment.getExternalStorageDirectory() + "/DJI/Camera";
    }

    public static boolean a(String str) {
        if (str.toLowerCase(Locale.US).endsWith(".mov") || str.toLowerCase(Locale.US).endsWith(".mp4")) {
            return true;
        }
        return false;
    }

    public static boolean b(String str) {
        if (str.endsWith(a.n) || str.endsWith(".dng")) {
            return true;
        }
        return false;
    }

    public static boolean c(String str) {
        if (str != null && str.toLowerCase(Locale.US).endsWith(".mov")) {
            return true;
        }
        return false;
    }

    public static boolean d(String str) {
        if (str != null && str.toLowerCase(Locale.US).endsWith(".mp4")) {
            return true;
        }
        return false;
    }

    public static boolean e(String str) {
        if (str == null || str.contains(b)) {
            return false;
        }
        return true;
    }
}
