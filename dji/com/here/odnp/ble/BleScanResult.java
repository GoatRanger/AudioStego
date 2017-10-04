package com.here.odnp.ble;

import com.here.posclient.BleMeasurement;

public class BleScanResult extends BleMeasurement {
    public String deviceName;

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.deviceAddress);
        stringBuilder.append("|");
        stringBuilder.append(this.deviceName);
        stringBuilder.append("|");
        stringBuilder.append(this.rssi);
        stringBuilder.append("|");
        stringBuilder.append(this.timestamp);
        stringBuilder.append("|");
        stringBuilder.append(this.elapsedRealtimeTimestamp);
        return stringBuilder.toString();
    }
}
