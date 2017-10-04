package com.here.posclient;

public class BleMeasurement {
    public String deviceAddress;
    public long elapsedRealtimeTimestamp;
    public int rssi;
    public long timestamp;

    public static String toMac64(String str) {
        return WifiMeasurement.toMac64(str);
    }
}
