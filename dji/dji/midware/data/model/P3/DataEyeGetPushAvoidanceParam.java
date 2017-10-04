package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataEyeGetPushAvoidanceParam extends n {
    private static DataEyeGetPushAvoidanceParam instance = null;

    public static synchronized DataEyeGetPushAvoidanceParam getInstance() {
        DataEyeGetPushAvoidanceParam dataEyeGetPushAvoidanceParam;
        synchronized (DataEyeGetPushAvoidanceParam.class) {
            if (instance == null) {
                instance = new DataEyeGetPushAvoidanceParam();
            }
            dataEyeGetPushAvoidanceParam = instance;
        }
        return dataEyeGetPushAvoidanceParam;
    }

    public boolean isBraking() {
        return (((Integer) get(0, 1, Integer.class)).intValue() & 1) != 0;
    }

    public boolean isVisualSensorWorking() {
        return (((Integer) get(0, 1, Integer.class)).intValue() & 2) != 0;
    }

    public boolean isAvoidOpen() {
        return (((Integer) get(0, 1, Integer.class)).intValue() & 4) != 0;
    }

    public boolean beShuttleMode() {
        return (((Integer) get(0, 1, Integer.class)).intValue() & 8) != 0;
    }

    public boolean isAvoidFrontWork() {
        return (((Integer) get(0, 1, Integer.class)).intValue() & 16) != 0;
    }

    public boolean isAvoidRightWork() {
        return (((Integer) get(0, 1, Integer.class)).intValue() & 32) != 0;
    }

    public boolean isAvoidBehindWork() {
        return (((Integer) get(0, 1, Integer.class)).intValue() & 64) != 0;
    }

    public boolean isAvoidLeftWork() {
        return (((Integer) get(0, 1, Integer.class)).intValue() & 128) != 0;
    }

    public int getAvoidFrontDistanceLevel() {
        return ((Integer) get(1, 1, Integer.class)).intValue() & 15;
    }

    public int getAvoidFrontAlertLevel() {
        return (((Integer) get(1, 1, Integer.class)).intValue() & 240) >>> 4;
    }

    public int getAvoidRightDistanceLevel() {
        return ((Integer) get(2, 1, Integer.class)).intValue() & 15;
    }

    public int getAvoidRightAlertLevel() {
        return (((Integer) get(2, 1, Integer.class)).intValue() & 240) >>> 4;
    }

    public int getAvoidBehindDistanceLevel() {
        return ((Integer) get(3, 1, Integer.class)).intValue() & 15;
    }

    public int getAvoidBehindAlertLevel() {
        return (((Integer) get(3, 1, Integer.class)).intValue() & 240) >>> 4;
    }

    public int getAvoidLeftDistanceLevel() {
        return ((Integer) get(4, 1, Integer.class)).intValue() & 15;
    }

    public int getAvoidLeftAlertLevel() {
        return (((Integer) get(4, 1, Integer.class)).intValue() & 240) >>> 4;
    }

    public boolean allowFront() {
        DataEyeGetPushFunctionList instance = DataEyeGetPushFunctionList.getInstance();
        if (instance.isGetted() && instance.sensorStatusSource()) {
            return (((Integer) get(5, 1, Integer.class)).intValue() & 1) != 0;
        } else {
            return true;
        }
    }

    public boolean allowRight() {
        DataEyeGetPushFunctionList instance = DataEyeGetPushFunctionList.getInstance();
        if (instance.isGetted() && instance.sensorStatusSource()) {
            return (((Integer) get(5, 1, Integer.class)).intValue() & 2) != 0;
        } else {
            return true;
        }
    }

    public boolean allowBack() {
        DataEyeGetPushFunctionList instance = DataEyeGetPushFunctionList.getInstance();
        if (instance.isGetted() && instance.sensorStatusSource()) {
            return (((Integer) get(5, 1, Integer.class)).intValue() & 4) != 0;
        } else {
            return true;
        }
    }

    public boolean allowLeft() {
        DataEyeGetPushFunctionList instance = DataEyeGetPushFunctionList.getInstance();
        if (instance.isGetted() && instance.sensorStatusSource()) {
            return (((Integer) get(5, 1, Integer.class)).intValue() & 8) != 0;
        } else {
            return true;
        }
    }

    public int getIndex() {
        return ((Integer) get(6, 1, Integer.class)).intValue();
    }

    protected void setPushRecData(byte[] bArr) {
        setRecData(bArr);
        if (isWantPush()) {
            post();
        }
    }

    protected void doPack() {
    }
}
