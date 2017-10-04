package com.flurry.sdk;

import java.util.Locale;
import java.util.TimeZone;

public class hq {
    private static hq a;

    public static synchronized hq a() {
        hq hqVar;
        synchronized (hq.class) {
            if (a == null) {
                a = new hq();
            }
            hqVar = a;
        }
        return hqVar;
    }

    public static void b() {
        a = null;
    }

    private hq() {
    }

    public String c() {
        return Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
    }

    public String d() {
        return TimeZone.getDefault().getID();
    }
}
