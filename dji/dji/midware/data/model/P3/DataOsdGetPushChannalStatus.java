package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataOsdGetPushChannalStatus extends n {
    private static DataOsdGetPushChannalStatus instance = null;

    public enum CHANNEL_STATUS {
        Excellent(0),
        Good(1),
        Medium(2),
        Poor(3),
        OTHER(100);
        
        private int data;

        private CHANNEL_STATUS(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static CHANNEL_STATUS find(int i) {
            CHANNEL_STATUS channel_status = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return channel_status;
        }
    }

    public static synchronized DataOsdGetPushChannalStatus getInstance() {
        DataOsdGetPushChannalStatus dataOsdGetPushChannalStatus;
        synchronized (DataOsdGetPushChannalStatus.class) {
            if (instance == null) {
                instance = new DataOsdGetPushChannalStatus();
            }
            dataOsdGetPushChannalStatus = instance;
        }
        return dataOsdGetPushChannalStatus;
    }

    protected void setPushRecData(byte[] bArr) {
        super.setPushRecData(bArr);
    }

    public CHANNEL_STATUS getChannelStatus() {
        if (this._recData == null || this._recData.length == 0) {
            return CHANNEL_STATUS.OTHER;
        }
        return CHANNEL_STATUS.find(((Integer) get(0, 1, Integer.class)).intValue());
    }

    protected void doPack() {
    }
}
