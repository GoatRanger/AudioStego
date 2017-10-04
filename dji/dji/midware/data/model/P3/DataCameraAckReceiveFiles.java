package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.d;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.manager.P3.n;
import dji.midware.e.b;
import dji.thirdparty.g.b.a.a;

public class DataCameraAckReceiveFiles extends n implements b {
    private static DataCameraAckReceiveFiles instance = null;
    private AckCcode ackCcode;

    public enum AckCcode {
        Success(0),
        UnableReceive(34),
        NoMemory(35),
        NoSupport(a.fw_),
        OTHER(100);
        
        private int data;

        private AckCcode(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static AckCcode find(int i) {
            AckCcode ackCcode = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return ackCcode;
        }
    }

    public static synchronized DataCameraAckReceiveFiles getInstance() {
        DataCameraAckReceiveFiles dataCameraAckReceiveFiles;
        synchronized (DataCameraAckReceiveFiles.class) {
            if (instance == null) {
                instance = new DataCameraAckReceiveFiles();
            }
            dataCameraAckReceiveFiles = instance;
        }
        return dataCameraAckReceiveFiles;
    }

    public int getFileType() {
        return ((Integer) get(8, 1, Integer.class)).intValue();
    }

    public long getFileSize() {
        return ((Long) get(0, 8, Long.class)).longValue();
    }

    public long getCreateTime() {
        return ((Long) get(9, 4, Long.class)).longValue();
    }

    public String getMD5() {
        return get(8, 16);
    }

    public int getIsAllPath() {
        return ((Integer) get(24, 1, Integer.class)).intValue();
    }

    public String getFileName() {
        return get(25, ((this._recData.length - 8) - 16) - 1);
    }

    public DataCameraAckReceiveFiles setAckCcode(AckCcode ackCcode) {
        this.ackCcode = ackCcode;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.ackCcode.value();
    }

    public void start() {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = q.a.b.a();
        cVar.k = q.c.b.a();
        cVar.l = q.b.a.a();
        cVar.m = p.a.a();
        cVar.n = d.a.m.a();
        if (this.pack != null) {
            cVar.i = this.pack.i;
            start(cVar);
        }
    }
}
