package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;
import dji.midware.util.c;

public class DataEyeGetPushPointLog extends n {
    private static DataEyeGetPushPointLog instance = null;

    public static synchronized DataEyeGetPushPointLog getInstance() {
        DataEyeGetPushPointLog dataEyeGetPushPointLog;
        synchronized (DataEyeGetPushPointLog.class) {
            if (instance == null) {
                instance = new DataEyeGetPushPointLog();
            }
            dataEyeGetPushPointLog = instance;
        }
        return dataEyeGetPushPointLog;
    }

    public String getLog() {
        return c.g(this._recData);
    }

    protected void doPack() {
    }
}
