package dji.midware.media.k.a;

import android.os.Build.VERSION;
import dji.midware.media.d;

public class j {
    public static String a = "TranscoderManager";
    public static byte[] b;
    public static byte[] c;
    public static b d;
    private static h e = null;

    private static synchronized h e() {
        h hVar;
        synchronized (j.class) {
            if (e != null) {
                hVar = e;
            } else if (d.a) {
                e = new g();
                hVar = e;
            } else if (VERSION.SDK_INT >= 18) {
                e = new e();
                hVar = e;
            } else if (d.b) {
                e = new g();
                hVar = e;
            } else {
                e = new e();
                hVar = e;
            }
        }
        return hVar;
    }

    public static synchronized void a() {
        synchronized (j.class) {
            if (e != null) {
                e.b();
                e = null;
            }
        }
    }

    public static void a(String str, String str2, i iVar) {
        e().a(str, str2, iVar);
    }

    public static void b() {
        if (e != null) {
            e().a();
        }
    }

    public static boolean a(String str) {
        if (str != null && e != null && str.equals(e.c()) && e.e()) {
            return true;
        }
        return false;
    }

    public static int b(String str) {
        if (a(str)) {
            return e.d();
        }
        return 0;
    }

    public static void c() {
        if (e != null) {
            e.a(null);
        }
    }

    public static boolean d() {
        return e != null && e.e();
    }

    public static void a(i iVar) {
        if (e != null) {
            e.a(iVar);
        }
    }

    public static void a(b bVar) {
        d = bVar;
    }
}
