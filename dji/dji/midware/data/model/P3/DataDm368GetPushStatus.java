package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataDm368GetPushStatus extends n {
    private static DataDm368GetPushStatus instance = null;

    public static synchronized DataDm368GetPushStatus getInstance() {
        DataDm368GetPushStatus dataDm368GetPushStatus;
        synchronized (DataDm368GetPushStatus.class) {
            if (instance == null) {
                instance = new DataDm368GetPushStatus();
            }
            dataDm368GetPushStatus = instance;
        }
        return dataDm368GetPushStatus;
    }

    protected boolean isChanged(byte[] bArr) {
        return true;
    }

    public boolean isDisableLiveview() {
        return ((Integer) get(3, 1, Integer.class)).intValue() == 1;
    }

    public int getEncodeMode() {
        return ((Integer) get(4, 1, Integer.class)).intValue();
    }

    public int getDualEncodeModePercentage() {
        return ((Integer) get(5, 1, Integer.class)).intValue();
    }

    public boolean isDualEncodeModeSupported() {
        if (getRecDataLen() < 6) {
            return false;
        }
        return true;
    }

    protected void doPack() {
    }
}
