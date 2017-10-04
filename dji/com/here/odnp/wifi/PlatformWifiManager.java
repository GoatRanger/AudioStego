package com.here.odnp.wifi;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.provider.Settings.Global;
import android.provider.Settings.System;
import com.here.odnp.wifi.IWifiManager.IWifiListener;
import com.here.odnp.wifi.IWifiManager.WifiScanResultContainer;
import com.here.odnp.wifi.util.WifiScanLock;
import com.here.odnp.wifi.util.WifiUtils;
import com.here.posclient.WifiMeasurement;
import com.here.posclient.WifiStatus;
import java.util.List;

public class PlatformWifiManager implements IWifiManager {
    private static final String TAG = "odnp.wifi.PlatformWifiManager";
    private final Context mContext;
    private volatile IWifiListener mListener;
    private final PackageManager mPackageManager;
    private final WifiManager mWifiManager;
    private final BroadcastReceiver mWifiReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if ("android.net.wifi.SCAN_RESULTS".equals(intent.getAction())) {
                PlatformWifiManager.this.handleWifiScanResultsAvailable();
            } else if ("android.net.wifi.WIFI_STATE_CHANGED".equals(intent.getAction())) {
                PlatformWifiManager.this.pushWifiState();
            } else if ("android.net.wifi.STATE_CHANGE".equals(intent.getAction())) {
                PlatformWifiManager.this.pushWifiState();
            } else if ("android.intent.action.AIRPLANE_MODE".equals(intent.getAction())) {
                PlatformWifiManager.this.pushWifiState();
            }
        }
    };
    private final WifiScanLock mWifiScanLock;
    private WifiStatus mWifiStatus;

    public PlatformWifiManager(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context is null");
        }
        this.mContext = context;
        this.mWifiManager = (WifiManager) this.mContext.getSystemService("wifi");
        this.mPackageManager = this.mContext.getPackageManager();
        this.mWifiScanLock = new WifiScanLock(this.mWifiManager);
    }

    public synchronized void open(IWifiListener iWifiListener) {
        close();
        if (iWifiListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        this.mWifiStatus = null;
        this.mListener = iWifiListener;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        intentFilter.addAction("android.intent.action.AIRPLANE_MODE");
        this.mContext.registerReceiver(this.mWifiReceiver, intentFilter);
        pushWifiState();
    }

    public synchronized void close() {
        if (this.mListener != null) {
            try {
                IWifiListener iWifiListener = this.mContext;
                iWifiListener.unregisterReceiver(this.mWifiReceiver);
                this.mListener = iWifiListener;
                releaseWifiLock();
            } finally {
                this.mListener = null;
                releaseWifiLock();
            }
        }
    }

    public List<WifiMeasurement> getLastScanResult() {
        return WifiUtils.toWifiMeasurements(this.mWifiManager.getScanResults());
    }

    @TargetApi(18)
    public boolean startWifiScan() {
        boolean isWifiEnabled = this.mWifiManager.isWifiEnabled();
        boolean isFlightModeOn = isFlightModeOn();
        boolean isScanAlwaysAvailable = isScanAlwaysAvailable();
        if (isWifiEnabled || (!isFlightModeOn && isScanAlwaysAvailable)) {
            acquireWifiLock();
            if (this.mWifiManager.startScan()) {
                return true;
            }
            releaseWifiLock();
        }
        return false;
    }

    public void cancelWifiScan() {
        releaseWifiLock();
    }

    public boolean isWifiSupported() {
        return this.mPackageManager.hasSystemFeature("android.hardware.wifi");
    }

    private void acquireWifiLock() {
        this.mWifiScanLock.acquire();
    }

    private void releaseWifiLock() {
        this.mWifiScanLock.release();
    }

    private synchronized void handleWifiScanResultsAvailable() {
        releaseWifiLock();
        if (this.mListener != null) {
            this.mListener.onScanResultsAvailable(new WifiScanResultContainer(WifiUtils.toWifiMeasurements(this.mWifiManager.getScanResults())));
        }
    }

    private synchronized void pushWifiState() {
        WifiStatus wifiStatus = WifiStatus.Unknown;
        if (this.mWifiManager.isWifiEnabled()) {
            NetworkInfo networkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getNetworkInfo(1);
            wifiStatus = (networkInfo == null || !networkInfo.isConnected()) ? WifiStatus.Disconnected : WifiStatus.Connected;
        } else {
            wifiStatus = WifiStatus.Disabled;
            if (isScanAlwaysAvailable() && !isFlightModeOn()) {
                wifiStatus = WifiStatus.Disconnected;
            }
        }
        if ((this.mWifiStatus == null || this.mWifiStatus != wifiStatus) && this.mListener != null) {
            this.mListener.onWifiStateChanged(wifiStatus);
            this.mWifiStatus = wifiStatus;
        }
    }

    @TargetApi(18)
    private boolean isScanAlwaysAvailable() {
        if (VERSION.SDK_INT >= 18) {
            return this.mWifiManager.isScanAlwaysAvailable();
        }
        return false;
    }

    @TargetApi(17)
    private boolean isFlightModeOn() {
        if (VERSION.SDK_INT < 17) {
            if (System.getInt(this.mContext.getContentResolver(), "airplane_mode_on", 0) != 0) {
                return true;
            }
            return false;
        } else if (Global.getInt(this.mContext.getContentResolver(), "airplane_mode_on", 0) == 0) {
            return false;
        } else {
            return true;
        }
    }
}
