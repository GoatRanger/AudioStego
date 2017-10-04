package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;
import dji.midware.util.c;

public class DataWifiGetPushFirstAppMac extends n {
    private static DataWifiGetPushFirstAppMac mInstance = null;

    public static synchronized DataWifiGetPushFirstAppMac getInstance() {
        DataWifiGetPushFirstAppMac dataWifiGetPushFirstAppMac;
        synchronized (DataWifiGetPushFirstAppMac.class) {
            if (mInstance == null) {
                mInstance = new DataWifiGetPushFirstAppMac();
            }
            dataWifiGetPushFirstAppMac = mInstance;
        }
        return dataWifiGetPushFirstAppMac;
    }

    public String getMac() {
        if (this._recData == null || this._recData.length == 0) {
            return null;
        }
        String c = c.c(c.c(((Integer) get(0, 1, Integer.class)).intValue()));
        String c2 = c.c(c.c(((Integer) get(1, 1, Integer.class)).intValue()));
        String c3 = c.c(c.c(((Integer) get(2, 1, Integer.class)).intValue()));
        String c4 = c.c(c.c(((Integer) get(3, 1, Integer.class)).intValue()));
        String c5 = c.c(c.c(((Integer) get(4, 1, Integer.class)).intValue()));
        return c + ":" + c2 + ":" + c3 + ":" + c4 + ":" + c5 + ":" + c.c(c.c(((Integer) get(5, 1, Integer.class)).intValue()));
    }

    protected void doPack() {
    }
}
