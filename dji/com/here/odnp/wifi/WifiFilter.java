package com.here.odnp.wifi;

import android.os.Build.VERSION;

public class WifiFilter {
    public static IWifiFilter create() {
        if (VERSION.SDK_INT >= 17) {
            return new WifiFilterTimestamp();
        }
        return new WifiFilterRx();
    }
}
