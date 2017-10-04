package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;
import dji.midware.util.c;

public class DataEyeGetPushTrackLog extends n {
    private static DataEyeGetPushTrackLog instance = null;

    public static synchronized DataEyeGetPushTrackLog getInstance() {
        DataEyeGetPushTrackLog dataEyeGetPushTrackLog;
        synchronized (DataEyeGetPushTrackLog.class) {
            if (instance == null) {
                instance = new DataEyeGetPushTrackLog();
            }
            dataEyeGetPushTrackLog = instance;
        }
        return dataEyeGetPushTrackLog;
    }

    public String getLog() {
        return c.g(this._recData);
    }

    protected void doPack() {
    }
}
