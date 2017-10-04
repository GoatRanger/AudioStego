package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataGimbalGetPushLog extends n {
    private static DataGimbalGetPushLog instance = null;

    public static synchronized DataGimbalGetPushLog getInstance() {
        DataGimbalGetPushLog dataGimbalGetPushLog;
        synchronized (DataGimbalGetPushLog.class) {
            if (instance == null) {
                instance = new DataGimbalGetPushLog();
            }
            dataGimbalGetPushLog = instance;
        }
        return dataGimbalGetPushLog;
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
