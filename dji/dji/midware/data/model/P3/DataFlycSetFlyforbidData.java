package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.g;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataFlycSetFlyforbidData extends n implements e {
    private static DataFlycSetFlyforbidData a = null;
    private int b = 0;

    public static synchronized DataFlycSetFlyforbidData getInstance() {
        DataFlycSetFlyforbidData dataFlycSetFlyforbidData;
        synchronized (DataFlycSetFlyforbidData.class) {
            if (a == null) {
                a = new DataFlycSetFlyforbidData();
            }
            dataFlycSetFlyforbidData = a;
        }
        return dataFlycSetFlyforbidData;
    }

    public DataFlycSetFlyforbidData a(int i) {
        this.b = i;
        return this;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.aK.a();
        super.start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[2];
        if (this.b == 1) {
            this._sendData[0] = (byte) 64;
        } else {
            this._sendData[0] = (byte) 63;
        }
    }
}
