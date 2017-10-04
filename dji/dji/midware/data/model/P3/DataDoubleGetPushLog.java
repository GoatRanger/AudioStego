package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataDoubleGetPushLog extends n {
    private static DataDoubleGetPushLog instance = null;

    public static synchronized DataDoubleGetPushLog getInstance() {
        DataDoubleGetPushLog dataDoubleGetPushLog;
        synchronized (DataDoubleGetPushLog.class) {
            if (instance == null) {
                instance = new DataDoubleGetPushLog();
            }
            dataDoubleGetPushLog = instance;
        }
        return dataDoubleGetPushLog;
    }

    protected void doPack() {
    }
}
