package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataDoubleGetPushCheckStatus extends n {
    private static DataDoubleGetPushCheckStatus instance = null;

    public static synchronized DataDoubleGetPushCheckStatus getInstance() {
        DataDoubleGetPushCheckStatus dataDoubleGetPushCheckStatus;
        synchronized (DataDoubleGetPushCheckStatus.class) {
            if (instance == null) {
                instance = new DataDoubleGetPushCheckStatus();
                instance.isNeedPushLosed = true;
                instance.isRemoteModel = true;
            }
            dataDoubleGetPushCheckStatus = instance;
        }
        return dataDoubleGetPushCheckStatus;
    }

    protected void doPack() {
    }
}
