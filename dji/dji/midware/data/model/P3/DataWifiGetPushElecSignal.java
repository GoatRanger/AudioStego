package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataWifiGetPushElecSignal extends n {
    private static DataWifiGetPushElecSignal mInstance = null;

    public enum SIGNAL_STATUS {
        Good(0),
        Medium(1),
        Poor(2),
        OTHER(100);
        
        private int data;

        private SIGNAL_STATUS(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static SIGNAL_STATUS find(int i) {
            SIGNAL_STATUS signal_status = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return signal_status;
        }
    }

    public static synchronized DataWifiGetPushElecSignal getInstance() {
        DataWifiGetPushElecSignal dataWifiGetPushElecSignal;
        synchronized (DataWifiGetPushElecSignal.class) {
            if (mInstance == null) {
                mInstance = new DataWifiGetPushElecSignal();
            }
            dataWifiGetPushElecSignal = mInstance;
        }
        return dataWifiGetPushElecSignal;
    }

    protected void setPushRecData(byte[] bArr) {
        super.setPushRecData(bArr);
    }

    public SIGNAL_STATUS getSignalStatus() {
        if (this._recData == null || this._recData.length == 0) {
            return SIGNAL_STATUS.OTHER;
        }
        return SIGNAL_STATUS.find(((Integer) get(0, 1, Integer.class)).intValue());
    }

    protected void doPack() {
    }
}
