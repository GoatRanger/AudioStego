package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataWifiGetPushSignal extends n {
    private static DataWifiGetPushSignal mInstance = null;

    public static synchronized DataWifiGetPushSignal getInstance() {
        DataWifiGetPushSignal dataWifiGetPushSignal;
        synchronized (DataWifiGetPushSignal.class) {
            if (mInstance == null) {
                mInstance = new DataWifiGetPushSignal();
            }
            dataWifiGetPushSignal = mInstance;
        }
        return dataWifiGetPushSignal;
    }

    public int getSignal() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    protected void doPack() {
    }
}
