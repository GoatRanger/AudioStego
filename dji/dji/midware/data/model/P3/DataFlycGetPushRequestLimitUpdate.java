package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataFlycGetPushRequestLimitUpdate extends n {
    private static DataFlycGetPushRequestLimitUpdate instance = null;

    public static synchronized DataFlycGetPushRequestLimitUpdate getInstance() {
        DataFlycGetPushRequestLimitUpdate dataFlycGetPushRequestLimitUpdate;
        synchronized (DataFlycGetPushRequestLimitUpdate.class) {
            if (instance == null) {
                instance = new DataFlycGetPushRequestLimitUpdate();
            }
            dataFlycGetPushRequestLimitUpdate = instance;
        }
        return dataFlycGetPushRequestLimitUpdate;
    }

    protected boolean isChanged(byte[] bArr) {
        return true;
    }

    protected void doPack() {
    }
}
