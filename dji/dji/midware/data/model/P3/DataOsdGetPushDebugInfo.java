package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataOsdGetPushDebugInfo extends n {
    private static DataOsdGetPushDebugInfo instance = null;

    public static synchronized DataOsdGetPushDebugInfo getInstance() {
        DataOsdGetPushDebugInfo dataOsdGetPushDebugInfo;
        synchronized (DataOsdGetPushDebugInfo.class) {
            if (instance == null) {
                instance = new DataOsdGetPushDebugInfo();
            }
            dataOsdGetPushDebugInfo = instance;
        }
        return dataOsdGetPushDebugInfo;
    }

    public DataOsdGetPushDebugInfo(boolean z) {
        super(z);
    }

    public DebugType getType() {
        return DebugType.find(((Integer) get(0, 1, Integer.class)).intValue());
    }

    public byte[] getData() {
        if (this._recData == null || this._recData.length < 2) {
            return null;
        }
        int length = this._recData.length - 1;
        byte[] bArr = new byte[length];
        System.arraycopy(this._recData, 1, bArr, 0, length);
        return bArr;
    }

    protected void doPack() {
    }
}
