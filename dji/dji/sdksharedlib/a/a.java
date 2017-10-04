package dji.sdksharedlib.a;

import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.c.c;
import dji.sdksharedlib.c.d;
import dji.sdksharedlib.c.h;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class a {
    public static void a(String str, c cVar) {
        a(b.k(str), cVar);
    }

    public static void b(String str, c cVar) {
        a(b.j(str), cVar);
    }

    public static void c(String str, c cVar) {
        a(b.c(str), cVar);
    }

    public static void a(dji.sdksharedlib.b.c cVar, c cVar2) {
        DJISDKCache.getInstance().getValue(cVar, cVar2);
    }

    public static void d(String str, c cVar) {
        a(b.f(str), cVar);
    }

    public static void e(String str, c cVar) {
        a(b.b(str), cVar);
    }

    public static void f(String str, c cVar) {
        a(b.h(str), cVar);
    }

    public static void g(String str, c cVar) {
        a(b.i(str), cVar);
    }

    public static <T> T a(String str) {
        return a(b.a(str));
    }

    public static <T> T b(String str) {
        return a(b.b(str));
    }

    public static <T> T c(String str) {
        return a(b.h(str));
    }

    public static <T> T d(String str) {
        return a(b.d(str));
    }

    public static <T> T a(String str, boolean z) {
        if (z) {
            return a(b.e(str));
        }
        return a(b.d(str));
    }

    public static <T> T a(String str, int i) {
        if (i == Integer.MAX_VALUE) {
            return a(str, true);
        }
        return a(b.a(i, str));
    }

    public static <T> T e(String str) {
        return a(b.f(str));
    }

    public static <T> T f(String str) {
        return a(b.g(str));
    }

    public static <T> T g(String str) {
        return a(b.c(str));
    }

    public static <T> T h(String str) {
        return a(b.k(str));
    }

    public static <T> T a(dji.sdksharedlib.b.c cVar) {
        dji.sdksharedlib.d.a availableValue = DJISDKCache.getInstance().getAvailableValue(cVar);
        if (availableValue == null || availableValue.e() == null) {
            return null;
        }
        return availableValue.e();
    }

    public static int a(Object obj) {
        if (obj == null || !(obj instanceof Integer)) {
            return 0;
        }
        return ((Integer) obj).intValue();
    }

    public static boolean b(Object obj) {
        if (obj == null || !(obj instanceof Boolean)) {
            return false;
        }
        return ((Boolean) obj).booleanValue();
    }

    public static boolean a(Object obj, boolean z) {
        if (obj == null || !(obj instanceof Boolean)) {
            return z;
        }
        return ((Boolean) obj).booleanValue();
    }

    public static float c(Object obj) {
        if (obj == null || !(obj instanceof Float)) {
            return 0.0f;
        }
        return ((Float) obj).floatValue();
    }

    public static double d(Object obj) {
        if (obj == null || !(obj instanceof Double)) {
            return 0.0d;
        }
        return ((Double) obj).doubleValue();
    }

    public static long e(Object obj) {
        if (obj == null || !(obj instanceof Long)) {
            return 0;
        }
        return ((Long) obj).longValue();
    }

    public static short f(Object obj) {
        if (obj == null || !(obj instanceof Short)) {
            return (short) 0;
        }
        return ((Short) obj).shortValue();
    }

    public static void a(String str, Object obj, h hVar) {
        a(b.b(str), obj, hVar);
    }

    public static void b(String str, Object obj, h hVar) {
        a(b.d(str), obj, hVar);
    }

    public static void c(String str, Object obj, h hVar) {
        a(b.f(str), obj, hVar);
    }

    public static void d(String str, Object obj, h hVar) {
        a(b.k(str), obj, hVar);
    }

    public static void e(String str, Object obj, h hVar) {
        a(b.c(str), obj, hVar);
    }

    public static void a(dji.sdksharedlib.b.c cVar, Object obj, h hVar) {
        DJISDKCache.getInstance().setValue(cVar, obj, hVar);
    }

    public static void a(d dVar, String... strArr) {
        a(dVar, b.a(strArr));
    }

    public static void a(d dVar, String str) {
        a(dVar, b.a(str));
    }

    public static void b(d dVar, String... strArr) {
        a(dVar, b.b(strArr));
    }

    public static void c(d dVar, String... strArr) {
        a(dVar, b.c(strArr));
    }

    public static void d(d dVar, String... strArr) {
        a(dVar, b.d(strArr));
    }

    public static void a(d dVar, int i, String... strArr) {
        a(dVar, b.a(i, strArr));
    }

    public static void e(d dVar, String... strArr) {
        a(dVar, b.e(strArr));
    }

    public static void f(d dVar, String... strArr) {
        a(dVar, b.j(strArr));
    }

    public static void g(d dVar, String... strArr) {
        a(dVar, b.f(strArr));
    }

    public static void h(d dVar, String... strArr) {
        a(dVar, b.g(strArr));
    }

    public static void i(d dVar, String... strArr) {
        a(dVar, b.h(strArr));
    }

    public static void f(String str, Object obj, h hVar) {
        a(b.d(str), obj, hVar);
    }

    public static void g(String str, Object obj, h hVar) {
        a(b.f(str), obj, hVar);
    }

    public static void a(d dVar, dji.sdksharedlib.b.c cVar) {
        DJISDKCache.getInstance().startListeningForUpdates(cVar, dVar, true);
    }

    public static void a(d dVar) {
        DJISDKCache.getInstance().stopListening(dVar);
    }

    public static void a(d dVar, dji.sdksharedlib.b.c... cVarArr) {
        a(dVar, true, cVarArr);
    }

    public static void a(d dVar, boolean z, dji.sdksharedlib.b.c... cVarArr) {
        for (dji.sdksharedlib.b.c startListeningForUpdates : cVarArr) {
            DJISDKCache.getInstance().startListeningForUpdates(startListeningForUpdates, dVar, z);
        }
    }

    public static dji.sdksharedlib.d.a a(dji.sdksharedlib.b.c cVar, int i) {
        a aVar = new a(new CountDownLatch(1));
        DJISDKCache.getInstance().getValue(cVar, aVar);
        if (i > 0) {
            aVar.a(i, TimeUnit.MILLISECONDS);
        } else {
            aVar.a();
        }
        if (aVar.c) {
            return aVar.b;
        }
        return null;
    }
}
