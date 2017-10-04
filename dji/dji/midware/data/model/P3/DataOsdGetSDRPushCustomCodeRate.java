package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataOsdGetSDRPushCustomCodeRate extends n {
    private static DataOsdGetSDRPushCustomCodeRate instance = null;

    public static synchronized DataOsdGetSDRPushCustomCodeRate getInstance() {
        DataOsdGetSDRPushCustomCodeRate dataOsdGetSDRPushCustomCodeRate;
        synchronized (DataOsdGetSDRPushCustomCodeRate.class) {
            if (instance == null) {
                instance = new DataOsdGetSDRPushCustomCodeRate();
            }
            dataOsdGetSDRPushCustomCodeRate = instance;
        }
        return dataOsdGetSDRPushCustomCodeRate;
    }

    protected void doPack() {
    }

    public float getCodeRate() {
        return ((Float) get(0, 4, Float.class)).floatValue();
    }
}
