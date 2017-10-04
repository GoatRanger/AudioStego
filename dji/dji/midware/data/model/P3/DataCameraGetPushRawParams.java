package dji.midware.data.model.P3;

import dji.midware.data.model.a.a;

public class DataCameraGetPushRawParams extends a {
    private static DataCameraGetPushRawParams instance = null;

    public enum DiskStatus {
        NOTCONNECTED(-1),
        NA(0),
        WAITING(1),
        STORING(2),
        LOW_FORMATING(3),
        FAST_FORMATING(4),
        INITIALIZING(5),
        DEVICE_ERROR(6),
        VERIFY_ERROR(7),
        FULL(8),
        OTHER(9);
        
        private int data;

        private DiskStatus(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static DiskStatus find(int i) {
            DiskStatus diskStatus = WAITING;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return diskStatus;
        }
    }

    public static synchronized DataCameraGetPushRawParams getInstance() {
        DataCameraGetPushRawParams dataCameraGetPushRawParams;
        synchronized (DataCameraGetPushRawParams.class) {
            if (instance == null) {
                instance = new DataCameraGetPushRawParams();
            }
            dataCameraGetPushRawParams = instance;
        }
        return dataCameraGetPushRawParams;
    }

    public DiskStatus getDiskStatus() {
        return DiskStatus.find(((Integer) get(0, 1, Integer.class)).intValue() & 15);
    }

    public int getDiskStatusValue() {
        return ((Integer) get(0, 1, Integer.class)).intValue() & 15;
    }

    public boolean isDiskConnected() {
        return (((Integer) get(0, 1, Integer.class)).intValue() & 16) != 0;
    }

    public int getDiskCapacity() {
        return (((Integer) get(0, 2, Integer.class)).intValue() & 96) >> 5;
    }

    public int getDiskAvailableTime() {
        return ((Integer) get(1, 2, Integer.class)).intValue();
    }

    public long getAvailableCapacity() {
        return (long) ((Integer) get(3, 4, Integer.class)).intValue();
    }

    public int getResolution() {
        return ((Integer) get(7, 1, Integer.class)).intValue() & 255;
    }

    public int getFps() {
        return ((Integer) get(8, 1, Integer.class)).intValue() & 255;
    }

    public int getAHCIStatus() {
        if (this._recData.length > 9) {
            return ((Integer) get(9, 1, Integer.class)).intValue();
        }
        return -1;
    }

    protected void doPack() {
    }
}
