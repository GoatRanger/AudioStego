package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataWifiGetPushFirstConnectedMac extends n {
    private static DataWifiGetPushFirstConnectedMac mInstance = null;

    public static synchronized DataWifiGetPushFirstConnectedMac getInstance() {
        DataWifiGetPushFirstConnectedMac dataWifiGetPushFirstConnectedMac;
        synchronized (DataWifiGetPushFirstConnectedMac.class) {
            if (mInstance == null) {
                mInstance = new DataWifiGetPushFirstConnectedMac();
            }
            dataWifiGetPushFirstConnectedMac = mInstance;
        }
        return dataWifiGetPushFirstConnectedMac;
    }

    protected void doPack() {
    }
}
