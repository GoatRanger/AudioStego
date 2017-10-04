package com.flurry.sdk;

import android.telephony.TelephonyManager;

public class hu {
    private static hu a;
    private static final String b = hu.class.getSimpleName();

    public static synchronized hu a() {
        hu huVar;
        synchronized (hu.class) {
            if (a == null) {
                a = new hu();
            }
            huVar = a;
        }
        return huVar;
    }

    public static void b() {
        a = null;
    }

    private hu() {
    }

    public String c() {
        TelephonyManager telephonyManager = (TelephonyManager) hz.a().c().getSystemService("phone");
        if (telephonyManager == null) {
            return null;
        }
        return telephonyManager.getNetworkOperatorName();
    }

    public String d() {
        TelephonyManager telephonyManager = (TelephonyManager) hz.a().c().getSystemService("phone");
        if (telephonyManager == null) {
            return null;
        }
        return telephonyManager.getNetworkOperator();
    }
}
