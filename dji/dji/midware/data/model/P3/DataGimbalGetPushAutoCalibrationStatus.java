package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataGimbalGetPushAutoCalibrationStatus extends n {
    private static DataGimbalGetPushAutoCalibrationStatus instance = null;

    public static synchronized DataGimbalGetPushAutoCalibrationStatus getInstance() {
        DataGimbalGetPushAutoCalibrationStatus dataGimbalGetPushAutoCalibrationStatus;
        synchronized (DataGimbalGetPushAutoCalibrationStatus.class) {
            if (instance == null) {
                instance = new DataGimbalGetPushAutoCalibrationStatus();
            }
            dataGimbalGetPushAutoCalibrationStatus = instance;
        }
        return dataGimbalGetPushAutoCalibrationStatus;
    }

    protected boolean isChanged(byte[] bArr) {
        return true;
    }

    protected void doPack() {
    }

    public int getProgress() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public int getStatus() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }
}
