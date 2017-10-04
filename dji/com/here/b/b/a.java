package com.here.b.b;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

public class a {
    private static Boolean a;

    private static boolean a(TelephonyManager telephonyManager) {
        if (telephonyManager != null) {
            return telephonyManager.getPhoneType() == 0;
        } else {
            throw new IllegalArgumentException("TelephonyManager is null");
        }
    }

    public static boolean a(Context context) {
        if (a != null) {
            return a.booleanValue();
        }
        if (a((WifiManager) context.getSystemService("wifi"))) {
            return true;
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (a(telephonyManager)) {
            return false;
        }
        if (((Boolean) com.here.b.a.a.k().c("flushOnRoaming")).booleanValue() || !telephonyManager.isNetworkRoaming()) {
            return true;
        }
        return false;
    }

    private static boolean a(WifiManager wifiManager) {
        if (wifiManager != null) {
            return wifiManager.isWifiEnabled();
        }
        throw new IllegalArgumentException("WifiManager is null");
    }
}
