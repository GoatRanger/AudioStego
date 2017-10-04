package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataOsdGetPushSdrStatusInfo extends n {
    private static DataOsdGetPushSdrStatusInfo instance = null;
    private String[] mTitleName = new String[32];
    private float[] mValue = new float[32];

    public static synchronized DataOsdGetPushSdrStatusInfo getInstance() {
        DataOsdGetPushSdrStatusInfo dataOsdGetPushSdrStatusInfo;
        synchronized (DataOsdGetPushSdrStatusInfo.class) {
            if (instance == null) {
                instance = new DataOsdGetPushSdrStatusInfo();
            }
            dataOsdGetPushSdrStatusInfo = instance;
        }
        return dataOsdGetPushSdrStatusInfo;
    }

    protected boolean isChanged(byte[] bArr) {
        return true;
    }

    public String[] getTitleList() {
        if (this._recData == null) {
            return this.mTitleName;
        }
        for (int i = 0; i < 32; i++) {
            this.mTitleName[i] = getUTF8((i * 12) + 0, 8);
        }
        return this.mTitleName;
    }

    public float[] getValueList() {
        if (this._recData == null) {
            return this.mValue;
        }
        for (int i = 0; i < 32; i++) {
            this.mValue[i] = ((Float) get((i * 12) + 8, 4, Float.class)).floatValue();
        }
        return this.mValue;
    }

    protected void doPack() {
    }
}
