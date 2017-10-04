package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataCenterGetPushCheckStatus extends n {
    private static DataCenterGetPushCheckStatus instance = null;

    public static synchronized DataCenterGetPushCheckStatus getInstance() {
        DataCenterGetPushCheckStatus dataCenterGetPushCheckStatus;
        synchronized (DataCenterGetPushCheckStatus.class) {
            if (instance == null) {
                instance = new DataCenterGetPushCheckStatus();
            }
            dataCenterGetPushCheckStatus = instance;
        }
        return dataCenterGetPushCheckStatus;
    }

    public boolean isOK() {
        return getBatteryConnectStatus() || getGpsConnectStatus() || getMcConnectStatus();
    }

    public boolean getBatteryConnectStatus() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 1) == 1;
    }

    public boolean getGpsConnectStatus() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 1) & 1) == 1;
    }

    public boolean getMcConnectStatus() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 2) & 1) == 1;
    }

    protected void doPack() {
    }
}
