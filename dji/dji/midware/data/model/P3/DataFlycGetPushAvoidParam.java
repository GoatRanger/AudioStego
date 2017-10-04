package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataFlycGetPushAvoidParam extends n {
    private static DataFlycGetPushAvoidParam instance = null;

    public static synchronized DataFlycGetPushAvoidParam getInstance() {
        DataFlycGetPushAvoidParam dataFlycGetPushAvoidParam;
        synchronized (DataFlycGetPushAvoidParam.class) {
            if (instance == null) {
                instance = new DataFlycGetPushAvoidParam();
            }
            dataFlycGetPushAvoidParam = instance;
        }
        return dataFlycGetPushAvoidParam;
    }

    public boolean isAvoidObstacleEnable() {
        return (((Integer) get(0, 2, Integer.class)).intValue() & 1) != 0;
    }

    public boolean isUserAvoidEnable() {
        return (((Integer) get(0, 2, Integer.class)).intValue() & 2) != 0;
    }

    public boolean getAvoidObstacleWorkFlag() {
        return (((Integer) get(0, 2, Integer.class)).intValue() & 4) != 0;
    }

    public boolean getEmergencyBrakeWorkFlag() {
        return (((Integer) get(0, 2, Integer.class)).intValue() & 8) != 0;
    }

    public boolean gohomeAvoidEnable() {
        return (((Integer) get(0, 2, Integer.class)).intValue() & 16) != 0;
    }

    public boolean avoidGroundForceLanding() {
        return (((Integer) get(0, 2, Integer.class)).intValue() & 32) != 0;
    }

    public boolean isRadiusLimitWorking() {
        return (((Integer) get(0, 2, Integer.class)).intValue() & 64) != 0;
    }

    public boolean isAirportLimitWorking() {
        return (((Integer) get(0, 2, Integer.class)).intValue() & 128) != 0;
    }

    public boolean isAvoidObstacleWorking() {
        return (((Integer) get(0, 2, Integer.class)).intValue() & 256) != 0;
    }

    public boolean horizNearBoundary() {
        return (((Integer) get(0, 2, Integer.class)).intValue() & 512) != 0;
    }

    public boolean isAvoidOvershotAct() {
        return (((Integer) get(0, 2, Integer.class)).intValue() & 1024) != 0;
    }

    public boolean vertLowLimitWorkFlag() {
        return (((Integer) get(0, 2, Integer.class)).intValue() & 2048) != 0;
    }

    protected void doPack() {
    }
}
