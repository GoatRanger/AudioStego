package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataOfdmGetPushCheckStatus extends n {
    private static DataOfdmGetPushCheckStatus instance = null;

    public static synchronized DataOfdmGetPushCheckStatus getInstance() {
        DataOfdmGetPushCheckStatus dataOfdmGetPushCheckStatus;
        synchronized (DataOfdmGetPushCheckStatus.class) {
            if (instance == null) {
                instance = new DataOfdmGetPushCheckStatus();
            }
            dataOfdmGetPushCheckStatus = instance;
        }
        return dataOfdmGetPushCheckStatus;
    }

    public boolean isOK() {
        return false;
    }

    public boolean isFirmwareNotMatch() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 32) != 0;
    }

    protected void doPack() {
    }
}
