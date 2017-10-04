package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataRcGetPushConnectStatus extends n {
    private static DataRcGetPushConnectStatus instance = null;

    public static synchronized DataRcGetPushConnectStatus getInstance() {
        DataRcGetPushConnectStatus dataRcGetPushConnectStatus;
        synchronized (DataRcGetPushConnectStatus.class) {
            if (instance == null) {
                instance = new DataRcGetPushConnectStatus();
            }
            dataRcGetPushConnectStatus = instance;
        }
        return dataRcGetPushConnectStatus;
    }

    protected boolean isChanged(byte[] bArr) {
        return true;
    }

    public int getIP() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public int getSignalMaster() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }

    public int getSignalSlave(int i) {
        return ((Integer) get(i + 2, 1, Integer.class)).intValue();
    }

    public boolean isSlaveConnected() {
        for (int i = 0; i < 6; i++) {
            if (getSignalSlave(i) > 0) {
                return true;
            }
        }
        return false;
    }

    protected void doPack() {
    }
}
