package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataFlycGetPushAgpsStatus extends n {
    private static DataFlycGetPushAgpsStatus instance = null;

    public static synchronized DataFlycGetPushAgpsStatus getInstance() {
        DataFlycGetPushAgpsStatus dataFlycGetPushAgpsStatus;
        synchronized (DataFlycGetPushAgpsStatus.class) {
            if (instance == null) {
                instance = new DataFlycGetPushAgpsStatus();
            }
            dataFlycGetPushAgpsStatus = instance;
        }
        return dataFlycGetPushAgpsStatus;
    }

    public int getTimeStamp() {
        return ((Integer) get(0, 4, Integer.class)).intValue();
    }

    public int getDataLength() {
        return ((Integer) get(4, 4, Integer.class)).intValue();
    }

    public Short getCRC16Hash() {
        return (Short) get(8, 2, Short.class);
    }

    protected void doPack() {
    }
}
