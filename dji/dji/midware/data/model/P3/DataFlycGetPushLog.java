package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataFlycGetPushLog extends n {
    private static DataFlycGetPushLog instance = null;

    public static synchronized DataFlycGetPushLog getInstance() {
        DataFlycGetPushLog dataFlycGetPushLog;
        synchronized (DataFlycGetPushLog.class) {
            if (instance == null) {
                instance = new DataFlycGetPushLog();
            }
            dataFlycGetPushLog = instance;
        }
        return dataFlycGetPushLog;
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
