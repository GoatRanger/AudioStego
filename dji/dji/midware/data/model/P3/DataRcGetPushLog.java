package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataRcGetPushLog extends n {
    private static DataRcGetPushLog instance = null;

    public static synchronized DataRcGetPushLog getInstance() {
        DataRcGetPushLog dataRcGetPushLog;
        synchronized (DataRcGetPushLog.class) {
            if (instance == null) {
                instance = new DataRcGetPushLog();
            }
            dataRcGetPushLog = instance;
        }
        return dataRcGetPushLog;
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
