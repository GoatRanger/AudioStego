package com.here.odnp.wifi.util;

import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;

public class WifiScanLock {
    private static final String TAG = "odnp.wifi.util.WifiScanLock";
    private static final String WIFILOCK_TAG = "com.here.odnp.wifi.util.WifiScanLock";
    private long mAcquiredAt;
    private WifiLock mWifiLock;

    public WifiScanLock(WifiManager wifiManager) {
        this.mWifiLock = wifiManager.createWifiLock(2, WIFILOCK_TAG);
        this.mWifiLock.setReferenceCounted(false);
    }

    public synchronized void acquire() {
        if (this.mWifiLock != null) {
            if (!this.mWifiLock.isHeld()) {
                this.mWifiLock.acquire();
            }
        }
    }

    public synchronized void release() {
        if (this.mWifiLock != null) {
            if (this.mWifiLock.isHeld()) {
                this.mWifiLock.release();
            }
        }
    }

    public synchronized void close() {
        release();
        this.mWifiLock = null;
    }
}
