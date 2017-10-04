package com.here.posclient;

import android.annotation.TargetApi;
import android.net.wifi.ScanResult;
import android.os.Build.VERSION;
import com.here.odnp.util.TimeManager;

public class WifiMeasurement {
    private static final String CAPABILITY_ADHOC = "[IBSS]";
    private static final int MAC_48_STRING_LENGTH = 17;
    private static final int MAC_64_STRING_LENGTH = 16;
    public static final byte NETWORK_MODE_ADHOC = (byte) 2;
    public static final byte NETWORK_MODE_INFRA = (byte) 1;
    public static final byte NETWORK_MODE_UNKNOWN = (byte) 0;
    public static final int RSSI_NOT_SET = Integer.MAX_VALUE;
    public static final byte WLAN_BAND_2400MHZ = (byte) 0;
    public static final byte WLAN_BAND_5000MHZ = (byte) 1;
    public static final byte WLAN_BAND_UNKNOWN = (byte) -1;
    public byte band;
    public String bssid;
    public int distance;
    public int distanceUncertainty;
    public long elapsedRealtimeTimeStamp;
    public byte mode;
    public int rssi;
    public int rxLevel;
    public String ssid;
    public long timeStamp;
    public long tsf;
    public int txLevel;

    public WifiMeasurement() {
        this.band = (byte) -1;
        this.rxLevel = Integer.MAX_VALUE;
        this.txLevel = Integer.MAX_VALUE;
        this.mode = (byte) 0;
        this.tsf = TimeManager.currentTimeMillis();
        this.timeStamp = TimeManager.currentTimeMillis() / 1000;
        this.elapsedRealtimeTimeStamp = TimeManager.timeSinceBoot();
    }

    @TargetApi(17)
    public WifiMeasurement(ScanResult scanResult) {
        this.band = (byte) -1;
        this.rxLevel = Integer.MAX_VALUE;
        this.txLevel = Integer.MAX_VALUE;
        this.mode = (byte) 0;
        this.bssid = toMac64(scanResult.BSSID);
        this.ssid = scanResult.SSID;
        this.rxLevel = scanResult.level;
        this.timeStamp = TimeManager.currentTimeMillis() / 1000;
        this.elapsedRealtimeTimeStamp = TimeManager.timeSinceBoot();
        if (VERSION.SDK_INT >= 17) {
            this.tsf = scanResult.timestamp;
        }
        if (scanResult.capabilities.contains(CAPABILITY_ADHOC)) {
            this.mode = (byte) 2;
        } else {
            this.mode = (byte) 1;
        }
        setFrequency(scanResult.frequency);
    }

    public static String toMac64(String str) {
        if (str == null) {
            throw new IllegalArgumentException("bssid is null");
        }
        if (str.length() == 17) {
            str = str.substring(0, 9) + "FF:FF:" + str.substring(9);
        } else if (str.length() != 16) {
            throw new IllegalArgumentException("incorrect bssid length: '" + str + "'");
        }
        return str.replace(":", "");
    }

    public WifiMeasurement copyOf() {
        WifiMeasurement wifiMeasurement = new WifiMeasurement();
        wifiMeasurement.band = this.band;
        wifiMeasurement.timeStamp = this.timeStamp;
        wifiMeasurement.elapsedRealtimeTimeStamp = this.elapsedRealtimeTimeStamp;
        wifiMeasurement.bssid = new String(this.bssid);
        if (this.ssid != null) {
            wifiMeasurement.ssid = new String(this.ssid);
        }
        wifiMeasurement.rxLevel = this.rxLevel;
        wifiMeasurement.txLevel = this.txLevel;
        wifiMeasurement.distance = this.distance;
        wifiMeasurement.distanceUncertainty = this.distanceUncertainty;
        wifiMeasurement.rssi = this.rssi;
        wifiMeasurement.mode = this.mode;
        return wifiMeasurement;
    }

    public void setFrequency(int i) {
        if (i >= 2400 && i < 2500) {
            this.band = (byte) 0;
        } else if (i < 4915 || i >= 5825) {
            this.band = (byte) -1;
        } else {
            this.band = (byte) 1;
        }
    }
}
