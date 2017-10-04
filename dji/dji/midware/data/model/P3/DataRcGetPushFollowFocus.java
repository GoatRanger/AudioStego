package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataRcGetPushFollowFocus extends n {
    private static DataRcGetPushFollowFocus instance = null;

    public enum CtrlDirection {
        CW(0),
        CCW(1),
        OTHER(10);
        
        private int data;

        private CtrlDirection(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static CtrlDirection find(int i) {
            CtrlDirection ctrlDirection = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return ctrlDirection;
        }
    }

    public enum CtrlType {
        APERTURE(0),
        FOCAL_LENGTH(1),
        OTHER(10);
        
        private int data;

        private CtrlType(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static CtrlType find(int i) {
            CtrlType ctrlType = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return ctrlType;
        }
    }

    public static synchronized DataRcGetPushFollowFocus getInstance() {
        DataRcGetPushFollowFocus dataRcGetPushFollowFocus;
        synchronized (DataRcGetPushFollowFocus.class) {
            if (instance == null) {
                instance = new DataRcGetPushFollowFocus();
            }
            dataRcGetPushFollowFocus = instance;
        }
        return dataRcGetPushFollowFocus;
    }

    protected void setPushRecData(byte[] bArr) {
        setRecData(bArr);
        post();
    }

    public int getCurCtrlStatus() {
        return ((Integer) get(0, 1, Integer.class)).intValue() & 1;
    }

    public CtrlType getCtrlType() {
        return CtrlType.find((((Integer) get(0, 1, Integer.class)).intValue() & 14) >>> 1);
    }

    public CtrlDirection getCtrlDirection() {
        return CtrlDirection.find((((Integer) get(0, 1, Integer.class)).intValue() & 16) >>> 4);
    }

    public int getCurrentValue() {
        return ((Integer) get(1, 2, Integer.class)).intValue();
    }

    protected void doPack() {
    }
}
