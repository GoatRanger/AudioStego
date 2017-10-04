package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataFlycGetPushCheckStatus extends n {
    private static DataFlycGetPushCheckStatus instance = null;

    public static synchronized DataFlycGetPushCheckStatus getInstance() {
        DataFlycGetPushCheckStatus dataFlycGetPushCheckStatus;
        synchronized (DataFlycGetPushCheckStatus.class) {
            if (instance == null) {
                instance = new DataFlycGetPushCheckStatus();
            }
            dataFlycGetPushCheckStatus = instance;
        }
        return dataFlycGetPushCheckStatus;
    }

    public boolean isOK() {
        return getIMUAdvanceCaliStatus() || getIMUBasicCaliStatus() || getIMUHorizontalCaliStatus() || getVersionStatus() || getIMUDirectionStatus() || getIMUInitStatus() || getPressInitStatus() || getAccDataStatus() || getGyroscopeStatus() || getPressDataStatus() || getAircraftAttiStatus() || getIMUDataStatus() || getDataLoggerStatus() || getLastIMUAdvanceCaliStatus() || getLastIMUBasicCaliStatus();
    }

    public boolean getIMUAdvanceCaliStatus() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 0) & 1) == 1;
    }

    public boolean getIMUBasicCaliStatus() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 1) & 1) == 1;
    }

    public boolean getIMUHorizontalCaliStatus() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 2) & 1) == 1;
    }

    public boolean getVersionStatus() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 3) & 1) == 1;
    }

    public boolean getIMUDirectionStatus() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 4) & 1) == 1;
    }

    public boolean getIMUInitStatus() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 5) & 1) == 1;
    }

    public boolean getPressInitStatus() {
        if (DataOsdGetPushCommon.getInstance().getFlycVersion() < 4) {
            return false;
        }
        if (((((Integer) get(0, 4, Integer.class)).intValue() >> 6) & 1) == 1) {
            return true;
        }
        return false;
    }

    public boolean getAccDataStatus() {
        if (DataOsdGetPushCommon.getInstance().getFlycVersion() < 4) {
            return false;
        }
        if (((((Integer) get(0, 4, Integer.class)).intValue() >> 7) & 1) == 1) {
            return true;
        }
        return false;
    }

    public boolean getGyroscopeStatus() {
        if (DataOsdGetPushCommon.getInstance().getFlycVersion() < 4) {
            return false;
        }
        if (((((Integer) get(0, 4, Integer.class)).intValue() >> 8) & 1) == 1) {
            return true;
        }
        return false;
    }

    public boolean getPressDataStatus() {
        if (DataOsdGetPushCommon.getInstance().getFlycVersion() < 4) {
            return false;
        }
        if (((((Integer) get(0, 4, Integer.class)).intValue() >> 9) & 1) == 1) {
            return true;
        }
        return false;
    }

    public boolean getAircraftAttiStatus() {
        if (DataOsdGetPushCommon.getInstance().getFlycVersion() < 4) {
            return false;
        }
        if (((((Integer) get(0, 4, Integer.class)).intValue() >> 10) & 1) == 1) {
            return true;
        }
        return false;
    }

    public boolean getIMUDataStatus() {
        if (DataOsdGetPushCommon.getInstance().getFlycVersion() < 4) {
            return false;
        }
        if (((((Integer) get(0, 4, Integer.class)).intValue() >> 11) & 1) == 1) {
            return true;
        }
        return false;
    }

    public boolean getDataLoggerStatus() {
        if (DataOsdGetPushCommon.getInstance().getFlycVersion() < 4) {
            return false;
        }
        if (((((Integer) get(0, 4, Integer.class)).intValue() >> 12) & 1) == 1) {
            return true;
        }
        return false;
    }

    public boolean getLastIMUAdvanceCaliStatus() {
        if (DataOsdGetPushCommon.getInstance().getFlycVersion() < 5) {
            return false;
        }
        if (((((Integer) get(0, 4, Integer.class)).intValue() >> 13) & 1) == 1) {
            return true;
        }
        return false;
    }

    public boolean getLastIMUBasicCaliStatus() {
        if (DataOsdGetPushCommon.getInstance().getFlycVersion() < 5) {
            return false;
        }
        if (((((Integer) get(0, 4, Integer.class)).intValue() >> 14) & 1) == 1) {
            return true;
        }
        return false;
    }

    protected void doPack() {
    }
}
