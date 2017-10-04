package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataCameraSaveParams extends n implements e {
    private static DataCameraSaveParams instance = null;
    private USER user = USER.DEFAULT;

    public enum USER {
        DEFAULT(0),
        USER1(1),
        USER2(2),
        USER3(3),
        USER4(4),
        OTHER(6);
        
        private int data;

        private USER(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static USER find(int i) {
            USER user = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return user;
        }
    }

    public static synchronized DataCameraSaveParams getInstance() {
        DataCameraSaveParams dataCameraSaveParams;
        synchronized (DataCameraSaveParams.class) {
            if (instance == null) {
                instance = new DataCameraSaveParams();
            }
            dataCameraSaveParams = instance;
        }
        return dataCameraSaveParams;
    }

    public DataCameraSaveParams setMode(USER user) {
        this.user = user;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.user.value();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.aG.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
