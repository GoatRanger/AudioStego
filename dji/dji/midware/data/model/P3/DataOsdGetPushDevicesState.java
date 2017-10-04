package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.manager.P3.n;

public class DataOsdGetPushDevicesState extends n {
    private static DataOsdGetPushDevicesState instance = null;

    public static synchronized DataOsdGetPushDevicesState getInstance() {
        DataOsdGetPushDevicesState dataOsdGetPushDevicesState;
        synchronized (DataOsdGetPushDevicesState.class) {
            if (instance == null) {
                instance = new DataOsdGetPushDevicesState();
            }
            dataOsdGetPushDevicesState = instance;
        }
        return dataOsdGetPushDevicesState;
    }

    public boolean getSignalQuality(DeviceType deviceType) {
        if (this._recData == null) {
            return false;
        }
        boolean z;
        int length = this._recData.length / 5;
        for (int i = 0; i < length; i++) {
            if (deviceType._equals(((Integer) get(i * 5, 1, Integer.class)).intValue())) {
                if (((Integer) get((i * 5) + 1, 4, Integer.class)).intValue() != 0) {
                    z = true;
                } else {
                    z = false;
                }
                return z;
            }
        }
        z = false;
        return z;
    }

    protected void doPack() {
    }
}
