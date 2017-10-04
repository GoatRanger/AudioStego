package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataCameraGetPushCheckStatus extends n {
    private static DataCameraGetPushCheckStatus instance = null;

    public static synchronized DataCameraGetPushCheckStatus getInstance() {
        DataCameraGetPushCheckStatus dataCameraGetPushCheckStatus;
        synchronized (DataCameraGetPushCheckStatus.class) {
            if (instance == null) {
                instance = new DataCameraGetPushCheckStatus();
            }
            dataCameraGetPushCheckStatus = instance;
        }
        return dataCameraGetPushCheckStatus;
    }

    public boolean encryptionStatus() {
        return ((Integer) get(21, 2, Integer.class)).intValue() == 2;
    }

    public boolean hdmiStatus() {
        return ((Integer) get(20, 1, Integer.class)).intValue() == 0;
    }

    public boolean upgradeStatus() {
        return ((Integer) get(14, 2, Integer.class)).intValue() == 0;
    }

    protected void doPack() {
    }
}
