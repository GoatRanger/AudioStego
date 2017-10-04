package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataOsdGetPushSdrConfigInfo extends n {
    private static DataOsdGetPushSdrConfigInfo instance = null;

    public static synchronized DataOsdGetPushSdrConfigInfo getInstance() {
        DataOsdGetPushSdrConfigInfo dataOsdGetPushSdrConfigInfo;
        synchronized (DataOsdGetPushSdrConfigInfo.class) {
            if (instance == null) {
                instance = new DataOsdGetPushSdrConfigInfo();
            }
            dataOsdGetPushSdrConfigInfo = instance;
        }
        return dataOsdGetPushSdrConfigInfo;
    }

    public int getChannel() {
        return Math.round(((Float) get(0, 4, Float.class)).floatValue());
    }

    public float getAutoChannelShow() {
        return ((Float) get(0, 4, Float.class)).floatValue();
    }

    public float getAutoMcs() {
        return ((Float) get(4, 4, Float.class)).floatValue();
    }

    public int getMcsType() {
        return ((Integer) get(8, 1, Integer.class)).intValue();
    }

    public int getNF() {
        return ((Integer) get(0, 2, Integer.class)).intValue();
    }

    public int getBand() {
        return ((Integer) get(2, 1, Integer.class)).intValue();
    }

    protected void doPack() {
    }
}
