package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataOsdGetPushLog extends n {
    private static DataOsdGetPushLog instance = null;

    public static synchronized DataOsdGetPushLog getInstance() {
        DataOsdGetPushLog dataOsdGetPushLog;
        synchronized (DataOsdGetPushLog.class) {
            if (instance == null) {
                instance = new DataOsdGetPushLog();
            }
            dataOsdGetPushLog = instance;
        }
        return dataOsdGetPushLog;
    }

    public int getType() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public String getLog() {
        return get(1, this._recData.length - 1);
    }

    protected void doPack() {
    }
}
