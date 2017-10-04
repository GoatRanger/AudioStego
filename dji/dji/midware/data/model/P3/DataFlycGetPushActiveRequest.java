package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataFlycGetPushActiveRequest extends n {
    private static DataFlycGetPushActiveRequest instance = null;

    public static synchronized DataFlycGetPushActiveRequest getInstance() {
        DataFlycGetPushActiveRequest dataFlycGetPushActiveRequest;
        synchronized (DataFlycGetPushActiveRequest.class) {
            if (instance == null) {
                instance = new DataFlycGetPushActiveRequest();
            }
            dataFlycGetPushActiveRequest = instance;
        }
        return dataFlycGetPushActiveRequest;
    }

    public int getAppId() {
        return ((Integer) get(0, 4, Integer.class)).intValue();
    }

    public int getAppLevel() {
        return ((Integer) get(4, 4, Integer.class)).intValue();
    }

    public int getAppVersion() {
        return ((Integer) get(8, 4, Integer.class)).intValue();
    }

    public String getAppBundleId() {
        return getUTF8(12, 32);
    }

    public String getDevSn() {
        return getUTF8(44, 32);
    }

    protected void doPack() {
    }
}
