package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.k;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataRcSetRcUnitNLang extends n implements e {
    private static DataRcSetRcUnitNLang a = null;
    private int b = 0;
    private int c = 2;

    public static synchronized DataRcSetRcUnitNLang getInstance() {
        DataRcSetRcUnitNLang dataRcSetRcUnitNLang;
        synchronized (DataRcSetRcUnitNLang.class) {
            if (a == null) {
                a = new DataRcSetRcUnitNLang();
            }
            dataRcSetRcUnitNLang = a;
        }
        return dataRcSetRcUnitNLang;
    }

    public DataRcSetRcUnitNLang a(int i) {
        this.b = i;
        return this;
    }

    public DataRcSetRcUnitNLang b(int i) {
        this.c = i;
        return this;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OSD.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.g.a();
        cVar.n = k.a.SetRcUnitNLang.b();
        start(cVar, dVar);
    }

    protected void doPack() {
        int i = ((1 << this.c) + 0) + (1 << (this.b + 6));
        this._sendData = new byte[1];
        this._sendData[0] = (byte) i;
    }
}
