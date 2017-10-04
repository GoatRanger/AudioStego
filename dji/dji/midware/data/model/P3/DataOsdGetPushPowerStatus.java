package dji.midware.data.model.P3;

import dji.midware.data.model.a.c;

public class DataOsdGetPushPowerStatus extends c {
    private static DataOsdGetPushPowerStatus instance = null;

    public static synchronized DataOsdGetPushPowerStatus getInstance() {
        DataOsdGetPushPowerStatus dataOsdGetPushPowerStatus;
        synchronized (DataOsdGetPushPowerStatus.class) {
            if (instance == null) {
                instance = new DataOsdGetPushPowerStatus();
            }
            dataOsdGetPushPowerStatus = instance;
        }
        return dataOsdGetPushPowerStatus;
    }

    public int getPowerStatus() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public boolean getIsPowerOff() {
        return ((Integer) get(1, 1, Integer.class)).intValue() == 1;
    }

    protected void doPack() {
    }
}
