package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataRcSimPushParams extends n {
    public static final int MAX_VALUE = 1684;
    public static final int MID_VALUE = 1024;
    public static final int MIN_VALUE = 364;
    private static DataRcSimPushParams instance = null;

    public static synchronized DataRcSimPushParams getInstance() {
        DataRcSimPushParams dataRcSimPushParams;
        synchronized (DataRcSimPushParams.class) {
            if (instance == null) {
                instance = new DataRcSimPushParams();
            }
            dataRcSimPushParams = instance;
        }
        return dataRcSimPushParams;
    }

    public int getAileron() {
        return getData(0, 2);
    }

    public int getElevator() {
        return getData(2, 2);
    }

    public int getThrottle() {
        return getData(4, 2);
    }

    public int getRudder() {
        return getData(6, 2);
    }

    private int getData(int i, int i2) {
        if (this._recData == null || this._recData.length < i + i2) {
            return 1024;
        }
        int intValue = ((Integer) get(i, i2, Integer.class)).intValue();
        if (intValue < 364) {
            return 364;
        }
        if (intValue > 1684) {
            return 1684;
        }
        return intValue;
    }

    protected void doPack() {
    }
}
