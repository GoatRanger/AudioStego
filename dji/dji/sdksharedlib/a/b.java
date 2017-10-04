package dji.sdksharedlib.a;

import dji.sdksharedlib.b.a;
import dji.sdksharedlib.b.a.d;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.b.e;
import dji.sdksharedlib.b.f;
import dji.sdksharedlib.b.g;
import dji.sdksharedlib.b.h;
import dji.sdksharedlib.b.i;

public class b {
    public static c a(String str) {
        return a(h.a, str);
    }

    public static c[] a(String[] strArr) {
        return a(h.a, strArr);
    }

    public static c b(String str) {
        return a(dji.sdksharedlib.b.b.a, str);
    }

    public static c[] b(String[] strArr) {
        return a(dji.sdksharedlib.b.b.a, strArr);
    }

    public static c c(String str) {
        return a(f.a, str);
    }

    public static c[] c(String[] strArr) {
        return a(f.a, strArr);
    }

    public static c d(String str) {
        return a(a.a, str);
    }

    public static c[] d(String[] strArr) {
        return a(a.a, strArr);
    }

    public static c a(int i, String str) {
        return a(a.a, i, str);
    }

    public static c[] a(int i, String[] strArr) {
        return a(a.a, strArr, i);
    }

    public static c e(String str) {
        return a(a.a, str, Integer.MAX_VALUE);
    }

    public static c[] e(String[] strArr) {
        return a(a.a, strArr, Integer.MAX_VALUE);
    }

    public static c f(String str) {
        return a(e.a, str);
    }

    public static c[] f(String[] strArr) {
        return a(e.a, strArr);
    }

    public static c g(String str) {
        return a(dji.sdksharedlib.b.a.a.a, str);
    }

    public static c[] g(String[] strArr) {
        return a(dji.sdksharedlib.b.a.a.a, strArr);
    }

    public static c h(String str) {
        c.a aVar = new c.a();
        aVar.b(dji.sdksharedlib.b.a.a.a).a(Integer.valueOf(0)).c(dji.sdksharedlib.b.a.c.h).b(Integer.valueOf(0));
        return aVar.d(str).a();
    }

    public static c[] h(String[] strArr) {
        int i = 0;
        c[] cVarArr = new c[strArr.length];
        c.a aVar = new c.a();
        aVar.b(dji.sdksharedlib.b.a.a.a).a(Integer.valueOf(0)).c(dji.sdksharedlib.b.a.c.h).b(Integer.valueOf(0));
        while (i < strArr.length) {
            cVarArr[i] = aVar.d(strArr[i]).a();
            i++;
        }
        return cVarArr;
    }

    public static c i(String str) {
        c.a aVar = new c.a();
        aVar.b(dji.sdksharedlib.b.a.a.a).a(Integer.valueOf(0)).c(dji.sdksharedlib.b.a.e.h).b(Integer.valueOf(0));
        return aVar.d(str).a();
    }

    public static c j(String str) {
        return a(g.a, str);
    }

    public static c[] i(String[] strArr) {
        return a(g.a, strArr);
    }

    public static c k(String str) {
        return a(i.a, str);
    }

    public static c[] j(String[] strArr) {
        return a(i.a, strArr);
    }

    public static c l(String str) {
        c.a aVar = new c.a();
        aVar.b(dji.sdksharedlib.b.a.a.a).a(Integer.valueOf(0)).c(d.h).b(Integer.valueOf(0));
        return aVar.d(str).a();
    }

    public static c[] a(String str, String[] strArr) {
        if (strArr == null) {
            return null;
        }
        int length = strArr.length;
        c[] cVarArr = new c[length];
        for (int i = 0; i < length; i++) {
            cVarArr[i] = a(str, strArr[i]);
        }
        return cVarArr;
    }

    public static c a(String str, String str2) {
        return a(str, 0, str2);
    }

    public static c a(String str, String str2, int i) {
        return a(str, i, str2);
    }

    public static c[] a(String str, String[] strArr, int i) {
        if (strArr == null) {
            return null;
        }
        int length = strArr.length;
        c[] cVarArr = new c[length];
        for (int i2 = 0; i2 < length; i2++) {
            cVarArr[i2] = a(str, i, strArr[i2]);
        }
        return cVarArr;
    }

    public static c a(String str, int i, String str2) {
        c d = c.d(str + dji.pilot.usercenter.protocol.d.t + i + dji.pilot.usercenter.protocol.d.t + str2);
        if (d != null) {
            return d;
        }
        c.a aVar = new c.a();
        aVar.b(str);
        aVar.a(Integer.valueOf(i));
        aVar.d(str2);
        return aVar.a();
    }
}
