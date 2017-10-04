package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataOsdGetPushCommon.TRIPOD_STATUS;

public class DataFlycGetPushDeformStatus extends n {
    private static DataFlycGetPushDeformStatus instance = null;

    public enum DEFORM_MODE {
        Pack(0),
        Protect(1),
        Normal(2),
        OTHER(3);
        
        private int data;

        private DEFORM_MODE(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static DEFORM_MODE find(int i) {
            DEFORM_MODE deform_mode = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return deform_mode;
        }
    }

    public static synchronized DataFlycGetPushDeformStatus getInstance() {
        DataFlycGetPushDeformStatus dataFlycGetPushDeformStatus;
        synchronized (DataFlycGetPushDeformStatus.class) {
            if (instance == null) {
                instance = new DataFlycGetPushDeformStatus();
            }
            dataFlycGetPushDeformStatus = instance;
        }
        return dataFlycGetPushDeformStatus;
    }

    public DataFlycGetPushDeformStatus(boolean z) {
        super(z);
    }

    public DEFORM_MODE getDeformMode() {
        return DEFORM_MODE.find((((Integer) get(0, 1, Integer.class)).intValue() & 48) >>> 4);
    }

    public TRIPOD_STATUS getDeformStatus() {
        return TRIPOD_STATUS.ofValue((byte) ((((Integer) get(0, 1, Integer.class)).intValue() & 14) >>> 1));
    }

    public boolean isDeformProtected() {
        return (((Integer) get(0, 1, Integer.class)).intValue() & 1) != 0;
    }

    protected void doPack() {
    }
}
