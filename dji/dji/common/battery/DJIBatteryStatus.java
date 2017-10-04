package dji.common.battery;

import dji.midware.data.model.P3.DataCenterGetPushBatteryCommon.ConnStatus;

public enum DJIBatteryStatus {
    NORMAL(ConnStatus.NORMAL),
    INVALID(ConnStatus.INVALID),
    EXCEPTION(ConnStatus.EXCEPTION),
    OTHER(ConnStatus.OTHER);
    
    private ConnStatus connStatus;

    private DJIBatteryStatus(ConnStatus connStatus) {
        this.connStatus = connStatus;
    }

    public int value() {
        return this.connStatus.value();
    }

    private boolean belongsTo(ConnStatus connStatus) {
        return this.connStatus == connStatus;
    }

    public static DJIBatteryStatus find(ConnStatus connStatus) {
        for (DJIBatteryStatus dJIBatteryStatus : values()) {
            if (dJIBatteryStatus.belongsTo(connStatus)) {
                return dJIBatteryStatus;
            }
        }
        return OTHER;
    }
}
