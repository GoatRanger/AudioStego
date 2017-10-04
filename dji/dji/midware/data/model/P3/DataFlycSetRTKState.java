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

public class DataFlycSetRTKState extends n implements e {
    private static DataFlycSetRTKState a = null;
    private boolean b = false;

    public static synchronized DataFlycSetRTKState getInstance() {
        DataFlycSetRTKState dataFlycSetRTKState;
        synchronized (DataFlycSetRTKState.class) {
            if (a == null) {
                a = new DataFlycSetRTKState();
            }
            dataFlycSetRTKState = a;
        }
        return dataFlycSetRTKState;
    }

    public DataFlycSetRTKState a(boolean z) {
        this.b = z;
        return this;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.b.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.W.a();
        start(cVar, dVar);
    }

    protected void doPack() {
        int i = 0;
        this._sendData = new byte[2];
        this._sendData[0] = (byte) 3;
        byte[] bArr = this._sendData;
        if (this.b) {
            i = 1;
        }
        bArr[1] = (byte) i;
    }
}
