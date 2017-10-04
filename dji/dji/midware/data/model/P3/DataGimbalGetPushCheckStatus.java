package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataGimbalGetPushType.DJIGimbalType;

public class DataGimbalGetPushCheckStatus extends n {
    private static DataGimbalGetPushCheckStatus instance = null;

    public static synchronized DataGimbalGetPushCheckStatus getInstance() {
        DataGimbalGetPushCheckStatus dataGimbalGetPushCheckStatus;
        synchronized (DataGimbalGetPushCheckStatus.class) {
            if (instance == null) {
                instance = new DataGimbalGetPushCheckStatus();
            }
            dataGimbalGetPushCheckStatus = instance;
        }
        return dataGimbalGetPushCheckStatus;
    }

    public boolean hasException() {
        boolean z = false;
        boolean z2 = getGyroscopeStatus() || getPitchStatus() || getRollStatus() || getYawStatus() || getDataReceiveStatus();
        DataGimbalGetPushType instance = DataGimbalGetPushType.getInstance();
        if (!instance.isGetted() || instance.getType() != DJIGimbalType.WM220) {
            return z2;
        }
        if (z2 || getLimitStatus() != 0 || getVibrateStatus()) {
            z = true;
        }
        return z;
    }

    public boolean getGyroscopeStatus() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 0) & 1) == 1;
    }

    public boolean getPitchStatus() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 1) & 1) == 1;
    }

    public boolean getRollStatus() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 2) & 1) == 1;
    }

    public boolean getYawStatus() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 3) & 1) == 1;
    }

    public boolean getDataReceiveStatus() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 4) & 1) == 1;
    }

    public boolean getIMUCalibrateMatchStatus() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 7) & 1) == 1;
    }

    public int getLimitStatus() {
        return (((Integer) get(0, 4, Integer.class)).intValue() >> 8) & 3;
    }

    public boolean getVibrateStatus() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 10) & 1) == 1;
    }

    protected void doPack() {
    }
}
