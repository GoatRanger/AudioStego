package dji.pilot.set;

import android.content.Context;
import dji.thirdparty.a.c;

public class a {

    public static class a {
        public String a;

        public a(String str) {
            this.a = str;
        }
    }

    public static boolean a(Context context, String str, int i) {
        boolean a = h.a(context, str, i);
        if (a) {
            c.a().e(new a(str));
            a(str);
        }
        return a;
    }

    public static boolean a(Context context, String str, boolean z) {
        boolean a = h.a(context, str, z);
        if (a) {
            c.a().e(new a(str));
            a(str);
        }
        return a;
    }

    public static boolean a(Context context, String str, String str2) {
        return h.a(context, str, str2);
    }

    public static String b(Context context, String str, String str2) {
        return h.b(context, str, str2);
    }

    public static int a(Context context, String str) {
        return h.a(context, str);
    }

    public static int b(Context context, String str, int i) {
        return h.b(context, str, i);
    }

    public static boolean b(Context context, String str) {
        return h.b(context, str);
    }

    public static boolean b(Context context, String str, boolean z) {
        return h.b(context, str, z);
    }

    public static boolean a(Context context) {
        return b(context, g.r);
    }

    public static boolean b(Context context) {
        return b(context, g.q, true);
    }

    public static boolean c(Context context) {
        return b(context, g.u);
    }

    public static boolean d(Context context) {
        return b(context, g.t);
    }

    public static boolean e(Context context) {
        return b(context, g.v);
    }

    public static int f(Context context) {
        return h.a(context, g.a);
    }

    public static int g(Context context) {
        return h.a(context, g.i);
    }

    private static void a(String str) {
        try {
            Class cls = Class.forName("dji.pilot.reflect.SetReflect");
            cls.getMethod("notifyMainProjectChange", new Class[]{String.class}).invoke(cls, new Object[]{str});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
