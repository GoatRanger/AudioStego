package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataOsdOsmoPushCalibration extends n {
    private static DataOsdOsmoPushCalibration a = null;

    public static synchronized DataOsdOsmoPushCalibration getInstance() {
        DataOsdOsmoPushCalibration dataOsdOsmoPushCalibration;
        synchronized (DataOsdOsmoPushCalibration.class) {
            if (a == null) {
                a = new DataOsdOsmoPushCalibration();
            }
            dataOsdOsmoPushCalibration = a;
        }
        return dataOsdOsmoPushCalibration;
    }

    protected void doPack() {
    }

    public int a() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public int b() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }
}
