package dji.midware.data.model.P3;

import dji.midware.data.model.a.a;

public class DataCameraGetPushPrepareOpenFan extends a {
    private static DataCameraGetPushPrepareOpenFan instance = null;

    public static synchronized DataCameraGetPushPrepareOpenFan getInstance() {
        DataCameraGetPushPrepareOpenFan dataCameraGetPushPrepareOpenFan;
        synchronized (DataCameraGetPushPrepareOpenFan.class) {
            if (instance == null) {
                instance = new DataCameraGetPushPrepareOpenFan();
            }
            dataCameraGetPushPrepareOpenFan = instance;
        }
        return dataCameraGetPushPrepareOpenFan;
    }

    public int getLeftSeconds() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    protected void doPack() {
    }
}
