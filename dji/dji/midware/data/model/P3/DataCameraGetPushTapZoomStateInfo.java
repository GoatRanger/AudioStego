package dji.midware.data.model.P3;

import dji.midware.data.model.a.a;

public class DataCameraGetPushTapZoomStateInfo extends a {
    private static DataCameraGetPushTapZoomStateInfo instance = null;

    public enum WorkingState {
        IDLE(0),
        ZOOM_IN(1),
        ZOOM_OUT(2),
        Unknown(255);
        
        private int value;

        private WorkingState(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static WorkingState find(int i) {
            WorkingState workingState = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return workingState;
        }
    }

    public static synchronized DataCameraGetPushTapZoomStateInfo getInstance() {
        DataCameraGetPushTapZoomStateInfo dataCameraGetPushTapZoomStateInfo;
        synchronized (DataCameraGetPushTapZoomStateInfo.class) {
            if (instance == null) {
                instance = new DataCameraGetPushTapZoomStateInfo();
                instance.isNeedPushLosed = true;
            }
            dataCameraGetPushTapZoomStateInfo = instance;
        }
        return dataCameraGetPushTapZoomStateInfo;
    }

    public WorkingState getWorkingState() {
        return WorkingState.find(((Integer) get(0, 1, Integer.class)).intValue());
    }

    public int getGimbalState() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }

    public int getMultiplier() {
        return ((Integer) get(2, 1, Integer.class)).intValue();
    }

    protected void setPushRecData(byte[] bArr) {
        super.setPushRecData(bArr);
    }

    protected void doPack() {
    }
}
