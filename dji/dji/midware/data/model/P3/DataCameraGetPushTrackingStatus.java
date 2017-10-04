package dji.midware.data.model.P3;

import dji.midware.data.model.a.a;

public class DataCameraGetPushTrackingStatus extends a {
    private static DataCameraGetPushTrackingStatus instance = null;

    public static synchronized DataCameraGetPushTrackingStatus getInstance() {
        DataCameraGetPushTrackingStatus dataCameraGetPushTrackingStatus;
        synchronized (DataCameraGetPushTrackingStatus.class) {
            if (instance == null) {
                instance = new DataCameraGetPushTrackingStatus();
            }
            dataCameraGetPushTrackingStatus = instance;
        }
        return dataCameraGetPushTrackingStatus;
    }

    public boolean getStatus() {
        return (((Integer) get(0, 1, Integer.class)).intValue() & 1) == 1;
    }

    public int getXCoord() {
        return ((Integer) get(1, 2, Integer.class)).intValue();
    }

    public int getYCoord() {
        return ((Integer) get(3, 2, Integer.class)).intValue();
    }

    protected void doPack() {
    }
}
