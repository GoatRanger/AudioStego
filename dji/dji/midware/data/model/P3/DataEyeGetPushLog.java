package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataEyeGetPushLog extends n {
    private static DataEyeGetPushLog instance = null;

    public static synchronized DataEyeGetPushLog getInstance() {
        DataEyeGetPushLog dataEyeGetPushLog;
        synchronized (DataEyeGetPushLog.class) {
            if (instance == null) {
                instance = new DataEyeGetPushLog();
            }
            dataEyeGetPushLog = instance;
        }
        return dataEyeGetPushLog;
    }

    public String getLog() {
        return get(0, this._recData.length);
    }

    public byte[] getRecvData() {
        return this._recData;
    }

    protected void doPack() {
    }
}
