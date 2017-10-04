package com.dji.a.f;

import android.content.Context;
import android.content.SharedPreferences;
import com.dji.a.a;

public class i {
    private static i a;
    private final SharedPreferences b;

    private i(Context context) {
        this.b = context.getSharedPreferences("com.dji.analytics.sharedpreinfo", 0);
    }

    public static synchronized void a(Context context) {
        synchronized (i.class) {
            if (a == null) {
                a = new i(context);
                if (a.b) {
                    a.c.a(a.a, i.class.getSimpleName() + " init success.");
                }
            }
        }
    }

    public static synchronized i a() {
        i iVar;
        synchronized (i.class) {
            if (a == null) {
                throw new IllegalStateException(i.class.getSimpleName() + " is not initialized, call initializeInstance(..) method first.");
            }
            iVar = a;
        }
        return iVar;
    }

    public void a(String str, boolean z) {
        this.b.edit().putBoolean(str, z).apply();
    }

    public boolean a(String str) {
        return this.b.getBoolean(str, false);
    }

    public void a(String str, String str2) {
        this.b.edit().putString(str, str2).apply();
    }

    public String b(String str) {
        return this.b.getString(str, null);
    }

    public void c(String str) {
        a("com.dji.analytics.uuid", str);
    }

    public String b() {
        return b("com.dji.analytics.uuid");
    }

    public void a(boolean z) {
        a("com.dji.analytics.baseinfosentsign", z);
    }

    public boolean c() {
        return a("com.dji.analytics.baseinfosentsign");
    }

    public void b(boolean z) {
        a("com.dji.analytics.canreport", z);
    }

    public boolean d() {
        return a("com.dji.analytics.canreport");
    }
}
