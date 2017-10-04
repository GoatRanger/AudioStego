package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataOsdGetPushSDRBarInterference extends n {
    private static DataOsdGetPushSDRBarInterference instance = null;

    public static synchronized DataOsdGetPushSDRBarInterference getInstance() {
        DataOsdGetPushSDRBarInterference dataOsdGetPushSDRBarInterference;
        synchronized (DataOsdGetPushSDRBarInterference.class) {
            if (instance == null) {
                instance = new DataOsdGetPushSDRBarInterference();
            }
            dataOsdGetPushSDRBarInterference = instance;
        }
        return dataOsdGetPushSDRBarInterference;
    }

    public int getBeInterfered() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    protected void doPack() {
    }
}
