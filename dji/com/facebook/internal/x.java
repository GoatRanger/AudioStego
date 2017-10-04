package com.facebook.internal;

import android.util.Log;
import com.facebook.o;
import com.facebook.y;
import java.util.HashMap;
import java.util.Map.Entry;

public class x {
    public static final String a = "FacebookSDK.";
    private static final HashMap<String, String> b = new HashMap();
    private final y c;
    private final String d;
    private StringBuilder e;
    private int f = 3;

    public static synchronized void a(String str, String str2) {
        synchronized (x.class) {
            b.put(str, str2);
        }
    }

    public static synchronized void a(String str) {
        synchronized (x.class) {
            if (!o.c(y.INCLUDE_ACCESS_TOKENS)) {
                a(str, "ACCESS_TOKEN_REMOVED");
            }
        }
    }

    public static void a(y yVar, String str, String str2) {
        a(yVar, 3, str, str2);
    }

    public static void a(y yVar, String str, String str2, Object... objArr) {
        if (o.c(yVar)) {
            a(yVar, 3, str, String.format(str2, objArr));
        }
    }

    public static void a(y yVar, int i, String str, String str2, Object... objArr) {
        if (o.c(yVar)) {
            a(yVar, i, str, String.format(str2, objArr));
        }
    }

    public static void a(y yVar, int i, String str, String str2) {
        if (o.c(yVar)) {
            String d = d(str2);
            if (!str.startsWith(a)) {
                str = a + str;
            }
            Log.println(i, str, d);
            if (yVar == y.DEVELOPER_ERRORS) {
                new Exception().printStackTrace();
            }
        }
    }

    private static synchronized String d(String str) {
        synchronized (x.class) {
            for (Entry entry : b.entrySet()) {
                str = str.replace((CharSequence) entry.getKey(), (CharSequence) entry.getValue());
            }
        }
        return str;
    }

    public x(y yVar, String str) {
        ai.a(str, "tag");
        this.c = yVar;
        this.d = a + str;
        this.e = new StringBuilder();
    }

    public int a() {
        return this.f;
    }

    public void a(int i) {
        ai.a(Integer.valueOf(i), "value", Integer.valueOf(7), Integer.valueOf(3), Integer.valueOf(6), Integer.valueOf(4), Integer.valueOf(2), Integer.valueOf(5));
        this.f = i;
    }

    public String b() {
        return d(this.e.toString());
    }

    public void c() {
        b(this.e.toString());
        this.e = new StringBuilder();
    }

    public void b(String str) {
        a(this.c, this.f, this.d, str);
    }

    public void a(StringBuilder stringBuilder) {
        if (d()) {
            this.e.append(stringBuilder);
        }
    }

    public void c(String str) {
        if (d()) {
            this.e.append(str);
        }
    }

    public void a(String str, Object... objArr) {
        if (d()) {
            this.e.append(String.format(str, objArr));
        }
    }

    public void a(String str, Object obj) {
        a("  %s:\t%s\n", str, obj);
    }

    private boolean d() {
        return o.c(this.c);
    }
}
