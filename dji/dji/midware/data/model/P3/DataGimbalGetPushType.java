package dji.midware.data.model.P3;

import dji.midware.data.a.a.a;
import dji.midware.data.manager.P3.n;

public class DataGimbalGetPushType extends n {
    private static DataGimbalGetPushType instance = null;

    public enum DJIGimbalType {
        TIMEOUT(0),
        FAULT(1),
        FC550(2),
        FC300SX(3),
        FC260(4),
        FC350(5),
        FC350Z(6),
        Z15(7),
        P4(8),
        D5(11),
        GH4(12),
        A7(13),
        BMPCC(14),
        WM220(20),
        Ronin(10),
        OTHER(100);
        
        private int data;

        private DJIGimbalType(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static DJIGimbalType find(int i) {
            DJIGimbalType dJIGimbalType = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return dJIGimbalType;
        }
    }

    public static synchronized DataGimbalGetPushType getInstance() {
        DataGimbalGetPushType dataGimbalGetPushType;
        synchronized (DataGimbalGetPushType.class) {
            if (instance == null) {
                instance = new DataGimbalGetPushType();
                instance.isNeedPushLosed = true;
                instance.isRemoteModel = true;
            }
            dataGimbalGetPushType = instance;
        }
        return dataGimbalGetPushType;
    }

    public DataGimbalGetPushType(boolean z) {
        super(z);
    }

    public DJIGimbalType getType() {
        return DJIGimbalType.find(((Short) get(0, 1, Short.class)).shortValue());
    }

    protected void doPack() {
    }

    protected void setPushRecPack(a aVar) {
        if (this.isNeedPushLosed) {
            this.isPushLosed = false;
            handler.removeMessages(0, this);
            handler.sendMessageDelayed(handler.obtainMessage(0, this), 8000);
        }
        this.pack = aVar;
        setPushRecData(aVar.p);
    }
}
