package com.dji.a;

import android.content.Context;
import com.dji.a.c.b;
import com.dji.a.d.e;
import com.dji.a.f.i;
import java.io.IOException;
import java.util.HashMap;

public class a {
    public static final String a = a.class.getSimpleName();
    public static boolean b = false;
    public static b c = new com.dji.a.c.a();
    public static Context d;
    private static boolean e = false;
    private static boolean f = false;
    private static final Object g = new Object();
    private static b h = new b();

    public static b a() {
        return h;
    }

    private a() {
    }

    @Deprecated
    public static void a(boolean z) {
        if (z) {
            b();
        }
    }

    public static void b() {
        b = true;
        try {
            com.dji.a.f.b.a().a(d);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public static void b(boolean z) {
        c(z);
    }

    public static void c(boolean z) {
        e = z;
        i.a().b(z);
        if (z) {
            a("DJIA_user_opened_log_report");
        }
    }

    public static void d(boolean z) {
        h.a = z;
    }

    public static void e(boolean z) {
        f = z;
    }

    public static void a(String str, String str2) {
        h.a(str, str2);
    }

    @Deprecated
    public static boolean c() {
        return d();
    }

    public static boolean d() {
        return e;
    }

    public static String e() {
        if (f()) {
            return com.dji.a.d.a.a().e();
        }
        return null;
    }

    public static void a(Context context, String str, String str2) {
        a(context, str, str2, new b());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.content.Context r4, java.lang.String r5, java.lang.String r6, com.dji.a.b r7) {
        /*
        r1 = g;
        monitor-enter(r1);
        r0 = d;	 Catch:{ all -> 0x0029 }
        if (r0 == 0) goto L_0x0012;
    L_0x0007:
        r0 = c;	 Catch:{ all -> 0x0029 }
        r2 = a;	 Catch:{ all -> 0x0029 }
        r3 = "Initialization can not be repeated";
        r0.c(r2, r3);	 Catch:{ all -> 0x0029 }
        monitor-exit(r1);	 Catch:{ all -> 0x0029 }
    L_0x0011:
        return;
    L_0x0012:
        monitor-exit(r1);	 Catch:{ all -> 0x0029 }
        if (r4 == 0) goto L_0x001f;
    L_0x0015:
        if (r5 == 0) goto L_0x001f;
    L_0x0017:
        r0 = r5.length();
        if (r0 == 0) goto L_0x001f;
    L_0x001d:
        if (r7 != 0) goto L_0x002c;
    L_0x001f:
        r0 = c;
        r1 = a;
        r2 = "init data is empty.";
        r0.c(r1, r2);
        goto L_0x0011;
    L_0x0029:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0029 }
        throw r0;
    L_0x002c:
        h = r7;
        r0 = h;
        r0.b(r5);
        r0 = h;
        r0.a(r6);
        r0 = r4.getApplicationContext();
        d = r0;
        com.dji.a.f.i.a(r4);
        r0 = com.dji.a.f.i.a();
        r0 = r0.d();
        e = r0;
        r0 = com.dji.a.b.a.a();
        r0.a(r4);
        r0 = com.dji.a.d.a.a();
        r0.a(r4, r5);
        r0 = com.dji.a.d.e.a();
        r0.a(r7);
        com.dji.a.d.c.a(r4);
        com.dji.a.d.c.b(r4);
        r0 = com.dji.a.d.a.a();
        r0.c();
        r0 = c;
        r1 = a;
        r2 = "DJIA initialised.";
        r0.a(r1, r2);
        goto L_0x0011;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dji.a.a.a(android.content.Context, java.lang.String, java.lang.String, com.dji.a.b):void");
    }

    public static void a(String str) {
        a(str, null, false);
    }

    public static void a(String str, HashMap<String, String> hashMap) {
        a(str, (HashMap) hashMap, false);
    }

    public static void a(String str, HashMap<String, String> hashMap, boolean z) {
        c.a(a, "DJIA logEvent <" + str + ">");
        if (f) {
            c.a(a, "DJIA is in develop mode. :)");
        } else if (e || z) {
            if (d == null) {
                c.c(a, "DJIA is not initialized, call init(..) method first.");
                return;
            }
            com.dji.a.a.a aVar = new com.dji.a.a.a(str, hashMap, z);
            if (b) {
                c.a(a, "DJIA logEvent <" + str + ">");
                com.dji.a.f.b.a().a(aVar.toString() + "\n");
            }
            e.a().a(aVar);
        } else if (b) {
            c.a(a, "DJIA can not logEvent ");
        }
    }

    public static boolean f() {
        return h != null;
    }
}
