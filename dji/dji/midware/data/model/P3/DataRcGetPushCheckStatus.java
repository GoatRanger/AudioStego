package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataRcGetPushCheckStatus extends n {
    private static DataRcGetPushCheckStatus instance = null;

    public static synchronized DataRcGetPushCheckStatus getInstance() {
        DataRcGetPushCheckStatus dataRcGetPushCheckStatus;
        synchronized (DataRcGetPushCheckStatus.class) {
            if (instance == null) {
                instance = new DataRcGetPushCheckStatus();
            }
            dataRcGetPushCheckStatus = instance;
        }
        return dataRcGetPushCheckStatus;
    }

    public boolean getNeedCalibration() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 0) & 1) == 1;
    }

    public boolean getIsFpgaInitError() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 1) & 1) == 1;
    }

    public boolean getIs5_8ModuleInitError() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 2) & 1) == 1;
    }

    public boolean getIsF330ModuleInitError() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 3) & 1) == 1;
    }

    public boolean getIsGpsInitError() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 4) & 1) == 1;
    }

    public boolean getIsEncryptionError() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 5) & 1) == 1;
    }

    public boolean getIsReseted() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 6) & 1) == 1;
    }

    public boolean getIsRcBatteryTooLow() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 7) & 1) == 1;
    }

    public boolean getIsIdleTooLong() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 8) & 1) == 1;
    }

    public boolean getIsRcOverHeat() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 9) & 1) == 1;
    }

    protected void doPack() {
    }
}
