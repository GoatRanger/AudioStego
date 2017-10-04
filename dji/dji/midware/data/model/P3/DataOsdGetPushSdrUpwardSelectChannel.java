package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataOsdGetPushSdrUpwardSelectChannel extends n {
    private static DataOsdGetPushSdrUpwardSelectChannel instance = null;
    private int mSelectChannelCnt = 0;

    public static synchronized DataOsdGetPushSdrUpwardSelectChannel getInstance() {
        DataOsdGetPushSdrUpwardSelectChannel dataOsdGetPushSdrUpwardSelectChannel;
        synchronized (DataOsdGetPushSdrUpwardSelectChannel.class) {
            if (instance == null) {
                instance = new DataOsdGetPushSdrUpwardSelectChannel();
            }
            dataOsdGetPushSdrUpwardSelectChannel = instance;
        }
        return dataOsdGetPushSdrUpwardSelectChannel;
    }

    public float getSelectChannelType() {
        return ((Float) get(0, 4, Float.class)).floatValue();
    }

    public int getSelectChannelCount() {
        this.mSelectChannelCnt = ((Integer) get(4, 4, Integer.class)).intValue();
        return this.mSelectChannelCnt;
    }

    public float[] getSelectChannelList() {
        if (this._recData == null) {
            return null;
        }
        if (this.mSelectChannelCnt == 0) {
            this.mSelectChannelCnt = ((Integer) get(4, 4, Integer.class)).intValue();
        }
        if (this.mSelectChannelCnt == 0) {
            return null;
        }
        float[] fArr = new float[this.mSelectChannelCnt];
        for (int i = 0; i < this.mSelectChannelCnt; i++) {
            fArr[i] = ((Float) get((i * 4) + 8, 4, Float.class)).floatValue();
        }
        return fArr;
    }

    protected void doPack() {
    }
}
