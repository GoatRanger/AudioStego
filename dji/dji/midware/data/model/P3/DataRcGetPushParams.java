package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataRcGetPushParams extends n {
    public static final int MAX_CHANNEL = 1684;
    public static final int MIDDLE_VALUE = 1024;
    public static final int MIN_CHANNEL = 364;
    private static DataRcGetPushParams instance = null;

    public static synchronized DataRcGetPushParams getInstance() {
        DataRcGetPushParams dataRcGetPushParams;
        synchronized (DataRcGetPushParams.class) {
            if (instance == null) {
                instance = new DataRcGetPushParams();
            }
            dataRcGetPushParams = instance;
        }
        return dataRcGetPushParams;
    }

    public DataRcGetPushParams(boolean z) {
        super(z);
    }

    public int getAileron() {
        return ((Integer) get(0, 2, Integer.class)).intValue();
    }

    public int getElevator() {
        return ((Integer) get(2, 2, Integer.class)).intValue();
    }

    public int getThrottle() {
        return ((Integer) get(4, 2, Integer.class)).intValue();
    }

    public int getRudder() {
        return ((Integer) get(6, 2, Integer.class)).intValue();
    }

    public int getGyroValue() {
        return ((Integer) get(8, 2, Integer.class)).intValue();
    }

    public boolean isWheelChanged() {
        return (((Integer) get(10, 1, Integer.class)).intValue() & 128) != 0;
    }

    public boolean isWheelPositive() {
        return (((Integer) get(10, 1, Integer.class)).intValue() & 64) == 0;
    }

    public int getWheelOffset() {
        return (((Integer) get(10, 1, Integer.class)).intValue() & 62) >> 1;
    }

    public boolean isWheelBtnDown() {
        return (((Integer) get(10, 1, Integer.class)).intValue() & 1) == 1;
    }

    public int getWheelClickStatus() {
        return ((Integer) get(10, 1, Integer.class)).intValue() & 1;
    }

    public boolean getRecordStatus() {
        return ((((Integer) get(12, 1, Integer.class)).intValue() >> 7) & 1) == 1;
    }

    public boolean getShutterStatus() {
        return ((((Integer) get(12, 1, Integer.class)).intValue() >> 6) & 1) == 1;
    }

    public boolean getPlayBackStatus() {
        return ((((Integer) get(12, 1, Integer.class)).intValue() >> 5) & 1) == 1;
    }

    public int getPlayback() {
        return (((Integer) get(12, 1, Integer.class)).intValue() >> 5) & 1;
    }

    public int getCustom2() {
        return (((Integer) get(12, 1, Integer.class)).intValue() >> 3) & 1;
    }

    public int getCustom1() {
        return (((Integer) get(12, 1, Integer.class)).intValue() >> 4) & 1;
    }

    public boolean isGoHomeButtonPressed() {
        return ((((Integer) get(11, 1, Integer.class)).intValue() >> 3) & 1) != 0;
    }

    public boolean getFootStool() {
        return ((((Integer) get(11, 1, Integer.class)).intValue() >> 6) & 3) == 3;
    }

    public int getMode() {
        return (((Integer) get(11, 1, Integer.class)).intValue() >> 4) & 3;
    }

    public int getBandWidth() {
        return ((Integer) get(13, 1, Integer.class)).intValue();
    }

    public byte[] getResource() {
        return this._recData;
    }

    protected void doPack() {
    }
}
