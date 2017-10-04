package com.here.posclient;

public enum MeasurementType {
    MSMTYPE_UNKNOWN(0),
    MSMTYPE_CELLULAR(1),
    MSMTYPE_WLAN(2),
    MSMTYPE_GNSS(4),
    MSMTYPE_BLE(8),
    MSMTYPE_CACHED_WLAN(64),
    MSMTYPE_STATUS(65536),
    MSMTYPE_CELLULAR_STATUS(MSMTYPE_CELLULAR.value | MSMTYPE_STATUS.value),
    MSMTYPE_WLAN_STATUS(MSMTYPE_WLAN.value | MSMTYPE_STATUS.value),
    MSMTYPE_GNSS_STATUS(MSMTYPE_GNSS.value | MSMTYPE_STATUS.value),
    MSMTYPE_BLE_STATUS(MSMTYPE_BLE.value | MSMTYPE_STATUS.value),
    MSMTYPE_ALL(Integer.MAX_VALUE);
    
    public int value;

    private MeasurementType(int i) {
        this.value = i;
    }

    public static MeasurementType fromInt(int i) {
        for (MeasurementType measurementType : values()) {
            if (measurementType.toInt() == i) {
                return measurementType;
            }
        }
        return MSMTYPE_UNKNOWN;
    }

    public int toInt() {
        return this.value;
    }
}
