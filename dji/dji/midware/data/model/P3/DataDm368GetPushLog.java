package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataDm368GetPushLog extends n {
    private static DataDm368GetPushLog instance = null;

    public static synchronized DataDm368GetPushLog getInstance() {
        DataDm368GetPushLog dataDm368GetPushLog;
        synchronized (DataDm368GetPushLog.class) {
            if (instance == null) {
                instance = new DataDm368GetPushLog();
            }
            dataDm368GetPushLog = instance;
        }
        return dataDm368GetPushLog;
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
