package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataWifiGetPushSweepFrequency extends n {
    private static DataWifiGetPushSweepFrequency instance = null;

    public static synchronized DataWifiGetPushSweepFrequency getInstance() {
        DataWifiGetPushSweepFrequency dataWifiGetPushSweepFrequency;
        synchronized (DataWifiGetPushSweepFrequency.class) {
            if (instance == null) {
                instance = new DataWifiGetPushSweepFrequency();
            }
            dataWifiGetPushSweepFrequency = instance;
        }
        return dataWifiGetPushSweepFrequency;
    }

    protected void doPack() {
    }

    public int[] get24GRssiList() {
        int intValue = ((Integer) get(8, 2, Integer.class)).intValue();
        int[] iArr = new int[intValue];
        for (int i = 0; i < intValue; i++) {
            iArr[i] = ((Integer) get(((i * 8) + 10) + 4, 4, Integer.class)).intValue();
        }
        return iArr;
    }

    public int[] get5GRssiList() {
        int intValue = ((Integer) get(8, 2, Integer.class)).intValue();
        int intValue2 = ((Integer) get(((intValue * 8) + 10) + 4, 2, Integer.class)).intValue();
        int[] iArr = new int[intValue2];
        for (int i = 0; i < intValue2; i++) {
            iArr[i] = ((Integer) get(((((intValue * 8) + 10) + 6) + (i * 8)) + 4, 4, Integer.class)).intValue();
        }
        return iArr;
    }

    public int getTotal() {
        return ((Integer) get(0, 4, Integer.class)).intValue();
    }
}
