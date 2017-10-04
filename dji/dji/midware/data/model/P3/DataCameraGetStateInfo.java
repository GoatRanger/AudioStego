package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataCameraGetStateInfo extends n implements e {
    private static DataCameraGetStateInfo instance = null;

    public enum FirmErrorType {
        NO(0),
        Nomatch(1),
        UpgradeError(2),
        OTHER(6);
        
        private int data;

        private FirmErrorType(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static FirmErrorType find(int i) {
            FirmErrorType firmErrorType = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return firmErrorType;
        }
    }

    public enum PhotoState {
        NO(0),
        Single(1),
        Multiple(2),
        Hdr(3),
        FullView(4),
        OTHER(6);
        
        private int data;

        private PhotoState(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static PhotoState find(int i) {
            PhotoState photoState = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return photoState;
        }
    }

    public enum SDCardState {
        Normal(0),
        None(1),
        Invalid(2),
        WriteProtection(3),
        Unformat(4),
        Formating(5),
        Illegal(6),
        Busy(7),
        Full(8),
        Slow(9),
        Unknow(10),
        IndexMax(11),
        Initialzing(12),
        ToFormat(13),
        TryToRecoverFile(14),
        BecomeSlow(15),
        USBConnected(99),
        OTHER(100);
        
        private int data;

        private SDCardState(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static SDCardState find(int i) {
            SDCardState sDCardState = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return sDCardState;
        }
    }

    public static synchronized DataCameraGetStateInfo getInstance() {
        DataCameraGetStateInfo dataCameraGetStateInfo;
        synchronized (DataCameraGetStateInfo.class) {
            if (instance == null) {
                instance = new DataCameraGetStateInfo();
            }
            dataCameraGetStateInfo = instance;
        }
        return dataCameraGetStateInfo;
    }

    public boolean getConnectState() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 1) == 1;
    }

    public boolean getUsbState() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 2) == 1;
    }

    public boolean getTimeSyncState() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 4) == 1;
    }

    public PhotoState getPhotoState() {
        return PhotoState.find(((Integer) get(0, 4, Integer.class)).intValue() & 56);
    }

    public boolean getRecordState() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 64) == 1;
    }

    public boolean getSensorState() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 128) == 1;
    }

    public boolean getSDCardInsertState() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 256) == 1;
    }

    public SDCardState getSDCardState() {
        return SDCardState.find(((Integer) get(0, 4, Integer.class)).intValue() & 7680);
    }

    public boolean getFirmUpgradeState() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 8192) == 1;
    }

    public FirmErrorType getFirmUpgradeErrorState() {
        return FirmErrorType.find(((Integer) get(0, 4, Integer.class)).intValue() & 49152);
    }

    public boolean getHotState() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 65536) == 1;
    }

    public MODE getMode() {
        return MODE.find(((Integer) get(4, 1, Integer.class)).intValue());
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.aD.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
