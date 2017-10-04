package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataCenterGetPushLog extends n {
    private static DataCenterGetPushLog instance = null;

    public static synchronized DataCenterGetPushLog getInstance() {
        DataCenterGetPushLog dataCenterGetPushLog;
        synchronized (DataCenterGetPushLog.class) {
            if (instance == null) {
                instance = new DataCenterGetPushLog();
            }
            dataCenterGetPushLog = instance;
        }
        return dataCenterGetPushLog;
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
