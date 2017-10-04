package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataFlycGetPushRTKLocationData extends n {
    private static DataFlycGetPushRTKLocationData instance = null;

    public static synchronized DataFlycGetPushRTKLocationData getInstance() {
        DataFlycGetPushRTKLocationData dataFlycGetPushRTKLocationData;
        synchronized (DataFlycGetPushRTKLocationData.class) {
            if (instance == null) {
                instance = new DataFlycGetPushRTKLocationData();
            }
            dataFlycGetPushRTKLocationData = instance;
        }
        return dataFlycGetPushRTKLocationData;
    }

    public double getLongitude() {
        return (((Double) get(0, 8, Double.class)).doubleValue() * 180.0d) / 3.141592653589793d;
    }

    public double getLatitude() {
        return (((Double) get(8, 8, Double.class)).doubleValue() * 180.0d) / 3.141592653589793d;
    }

    public float getHeight() {
        return ((Float) get(16, 4, Float.class)).floatValue();
    }

    public int getHeading() {
        return ((Integer) get(20, 2, Integer.class)).intValue();
    }

    public boolean isRTKConnected() {
        return ((Integer) get(22, 1, Integer.class)).intValue() == 1;
    }

    public boolean isRTKCanbeUsed() {
        return ((Integer) get(23, 1, Integer.class)).intValue() == 1;
    }

    protected void doPack() {
    }
}
