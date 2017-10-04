package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataDm368GetPushCheckStatus extends n {
    private static DataDm368GetPushCheckStatus instance = null;

    public static synchronized DataDm368GetPushCheckStatus getInstance() {
        DataDm368GetPushCheckStatus dataDm368GetPushCheckStatus;
        synchronized (DataDm368GetPushCheckStatus.class) {
            if (instance == null) {
                instance = new DataDm368GetPushCheckStatus();
                instance.isNeedPushLosed = true;
                instance.isRemoteModel = true;
            }
            dataDm368GetPushCheckStatus = instance;
        }
        return dataDm368GetPushCheckStatus;
    }

    public int getVideoBps() {
        return ((Integer) get(0, 4, Integer.class)).intValue() & 8;
    }

    public boolean get68013ConnectStatus() {
        return (((Integer) get(0, 4, Integer.class)).intValue() >> 0) == 1;
    }

    public boolean getHDMIConnectStatus() {
        return (((Integer) get(0, 4, Integer.class)).intValue() >> 17) == 0;
    }

    public boolean getAppConnectStatus() {
        return (((Integer) get(0, 4, Integer.class)).intValue() >> 18) == 0;
    }

    public boolean getEncryptStatus() {
        return (((Integer) get(0, 4, Integer.class)).intValue() >> 19) == 0;
    }

    protected void doPack() {
    }
}
