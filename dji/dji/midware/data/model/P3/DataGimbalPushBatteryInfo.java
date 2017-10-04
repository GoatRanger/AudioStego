package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataGimbalPushBatteryInfo extends n {
    private static DataGimbalPushBatteryInfo a = null;

    public static synchronized DataGimbalPushBatteryInfo getInstance() {
        DataGimbalPushBatteryInfo dataGimbalPushBatteryInfo;
        synchronized (DataGimbalPushBatteryInfo.class) {
            if (a == null) {
                a = new DataGimbalPushBatteryInfo();
            }
            dataGimbalPushBatteryInfo = a;
        }
        return dataGimbalPushBatteryInfo;
    }

    public int a() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    protected void doPack() {
    }
}
