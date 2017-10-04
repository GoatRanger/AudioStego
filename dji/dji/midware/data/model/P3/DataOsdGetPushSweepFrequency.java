package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataOsdGetPushSweepFrequency extends n {
    private static DataOsdGetPushSweepFrequency instance = null;

    public static synchronized DataOsdGetPushSweepFrequency getInstance() {
        DataOsdGetPushSweepFrequency dataOsdGetPushSweepFrequency;
        synchronized (DataOsdGetPushSweepFrequency.class) {
            if (instance == null) {
                instance = new DataOsdGetPushSweepFrequency();
            }
            dataOsdGetPushSweepFrequency = instance;
        }
        return dataOsdGetPushSweepFrequency;
    }

    public int[] getSignalList() {
        int i = 0;
        if (this._recData == null) {
            return new int[0];
        }
        int length = this._recData.length;
        int[] iArr = new int[length];
        while (i < length) {
            iArr[i] = this._recData[i];
            i++;
        }
        return iArr;
    }

    protected void doPack() {
    }
}
