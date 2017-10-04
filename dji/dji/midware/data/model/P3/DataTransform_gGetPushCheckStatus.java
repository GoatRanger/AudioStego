package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataTransform_gGetPushCheckStatus extends n {
    private static DataTransform_gGetPushCheckStatus instance = null;

    public static synchronized DataTransform_gGetPushCheckStatus getInstance() {
        DataTransform_gGetPushCheckStatus dataTransform_gGetPushCheckStatus;
        synchronized (DataTransform_gGetPushCheckStatus.class) {
            if (instance == null) {
                instance = new DataTransform_gGetPushCheckStatus();
            }
            dataTransform_gGetPushCheckStatus = instance;
        }
        return dataTransform_gGetPushCheckStatus;
    }

    public boolean isOK() {
        return false;
    }

    protected void doPack() {
    }
}
