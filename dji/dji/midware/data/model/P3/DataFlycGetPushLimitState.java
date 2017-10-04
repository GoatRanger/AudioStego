package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataFlycGetPushLimitState extends n {
    private static DataFlycGetPushLimitState instance = null;

    public enum DJILimitsAreaStatus {
        None(0),
        NearLimit(1),
        InnerHeightLimit(2),
        InnerLimit(3),
        OTHER(100);
        
        private int data;

        private DJILimitsAreaStatus(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static DJILimitsAreaStatus find(int i) {
            DJILimitsAreaStatus dJILimitsAreaStatus = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return dJILimitsAreaStatus;
        }
    }

    public static synchronized DataFlycGetPushLimitState getInstance() {
        DataFlycGetPushLimitState dataFlycGetPushLimitState;
        synchronized (DataFlycGetPushLimitState.class) {
            if (instance == null) {
                instance = new DataFlycGetPushLimitState();
            }
            dataFlycGetPushLimitState = instance;
        }
        return dataFlycGetPushLimitState;
    }

    protected boolean isChanged(byte[] bArr) {
        return true;
    }

    public double getLatitude() {
        return ((Double) get(0, 8, Double.class)).doubleValue();
    }

    public double getLongitude() {
        return ((Double) get(8, 8, Double.class)).doubleValue();
    }

    public int getInnerRadius() {
        return ((Integer) get(16, 2, Integer.class)).intValue();
    }

    public int getOuterRadius() {
        return ((Integer) get(18, 2, Integer.class)).intValue();
    }

    public int getType() {
        return ((Integer) get(20, 1, Integer.class)).intValue();
    }

    public int getAreaState() {
        return ((Integer) get(21, 1, Integer.class)).intValue();
    }

    public int getActionState() {
        return ((Integer) get(22, 1, Integer.class)).intValue();
    }

    public boolean isEnable() {
        return ((Integer) get(23, 1, Integer.class)).intValue() == 1;
    }

    protected void doPack() {
    }
}
