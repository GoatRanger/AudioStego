package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataCameraGetPushLog extends n {
    private static DataCameraGetPushLog instance = null;

    public static synchronized DataCameraGetPushLog getInstance() {
        DataCameraGetPushLog dataCameraGetPushLog;
        synchronized (DataCameraGetPushLog.class) {
            if (instance == null) {
                instance = new DataCameraGetPushLog();
            }
            dataCameraGetPushLog = instance;
        }
        return dataCameraGetPushLog;
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
