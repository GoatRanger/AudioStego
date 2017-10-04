package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.g;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataFlycSetHomePoint extends n implements e {
    private static DataFlycSetHomePoint a = null;
    private HOMETYPE b = HOMETYPE.RC;
    private double c = 0.0d;
    private double d = 0.0d;
    private byte e = (byte) 0;

    public enum HOMETYPE {
        AIRCRAFT((byte) 0),
        RC((byte) 1),
        APP((byte) 2),
        FOLLOW((byte) 3);
        
        private byte e;

        private HOMETYPE(byte b) {
            this.e = (byte) 0;
            this.e = b;
        }

        public byte a() {
            return this.e;
        }

        public boolean a(byte b) {
            return this.e == b;
        }

        public static HOMETYPE ofValue(byte b) {
            for (HOMETYPE hometype : values()) {
                if (hometype.a(b)) {
                    return hometype;
                }
            }
            return RC;
        }
    }

    public static synchronized DataFlycSetHomePoint getInstance() {
        DataFlycSetHomePoint dataFlycSetHomePoint;
        synchronized (DataFlycSetHomePoint.class) {
            if (a == null) {
                a = new DataFlycSetHomePoint();
            }
            dataFlycSetHomePoint = a;
        }
        return dataFlycSetHomePoint;
    }

    public DataFlycSetHomePoint a(HOMETYPE hometype) {
        this.b = hometype;
        return this;
    }

    public DataFlycSetHomePoint a(double d, double d2) {
        this.c = d;
        this.d = d2;
        return this;
    }

    public DataFlycSetHomePoint a(byte b) {
        this.e = b;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[18];
        this._sendData[0] = this.b.a();
        System.arraycopy(c.a(this.c), 0, this._sendData, 1, 8);
        System.arraycopy(c.a(this.d), 0, this._sendData, 9, 8);
        this._sendData[17] = this.e;
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.t.a();
        start(cVar, dVar);
    }
}
