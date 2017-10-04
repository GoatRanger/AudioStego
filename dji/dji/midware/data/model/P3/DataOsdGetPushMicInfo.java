package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataOsdGetPushMicInfo extends n {
    private static DataOsdGetPushMicInfo instance = null;

    public enum MIC_TYPE {
        IN(0),
        OUT(1),
        OTHER(2);
        
        private int data;

        private MIC_TYPE(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static MIC_TYPE find(int i) {
            MIC_TYPE mic_type = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return mic_type;
        }
    }

    public static synchronized DataOsdGetPushMicInfo getInstance() {
        DataOsdGetPushMicInfo dataOsdGetPushMicInfo;
        synchronized (DataOsdGetPushMicInfo.class) {
            if (instance == null) {
                instance = new DataOsdGetPushMicInfo();
            }
            dataOsdGetPushMicInfo = instance;
        }
        return dataOsdGetPushMicInfo;
    }

    protected void doPack() {
    }

    public int getMicVolume() {
        return ((Short) get(0, 1, Short.class)).shortValue() >> 1;
    }

    public MIC_TYPE getMicType() {
        return MIC_TYPE.find(((Short) get(0, 1, Short.class)).shortValue() & 1);
    }
}
