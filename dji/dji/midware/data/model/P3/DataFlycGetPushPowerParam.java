package dji.midware.data.model.P3;

import dji.midware.data.model.a.b;

public class DataFlycGetPushPowerParam extends b {
    private static DataFlycGetPushPowerParam instance = null;

    public static synchronized DataFlycGetPushPowerParam getInstance() {
        DataFlycGetPushPowerParam dataFlycGetPushPowerParam;
        synchronized (DataFlycGetPushPowerParam.class) {
            if (instance == null) {
                instance = new DataFlycGetPushPowerParam();
            }
            dataFlycGetPushPowerParam = instance;
        }
        return dataFlycGetPushPowerParam;
    }

    public float getEscAverageSpeed() {
        return ((Float) get(0, 4, Float.class)).floatValue();
    }

    public float getLift() {
        return ((Float) get(4, 4, Float.class)).floatValue();
    }

    protected void doPack() {
    }
}
