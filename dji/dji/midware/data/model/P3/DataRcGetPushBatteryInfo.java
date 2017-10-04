package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataRcGetPushBatteryInfo extends n {
    private static DataRcGetPushBatteryInfo instance = null;

    public static synchronized DataRcGetPushBatteryInfo getInstance() {
        DataRcGetPushBatteryInfo dataRcGetPushBatteryInfo;
        synchronized (DataRcGetPushBatteryInfo.class) {
            if (instance == null) {
                instance = new DataRcGetPushBatteryInfo();
            }
            dataRcGetPushBatteryInfo = instance;
        }
        return dataRcGetPushBatteryInfo;
    }

    public int getBatteryVolume() {
        return ((Integer) get(0, 4, Integer.class)).intValue();
    }

    public int getBattery() {
        return ((Integer) get(4, 1, Integer.class)).intValue();
    }

    protected void doPack() {
    }
}
