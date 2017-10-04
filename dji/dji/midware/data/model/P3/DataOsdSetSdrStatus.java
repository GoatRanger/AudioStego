package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.i;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataOsdSetSdrStatus extends n implements e {
    private static DataOsdSetSdrStatus a = null;
    private int b = 0;
    private int c = 0;

    public static synchronized DataOsdSetSdrStatus getInstance() {
        DataOsdSetSdrStatus dataOsdSetSdrStatus;
        synchronized (DataOsdSetSdrStatus.class) {
            if (a == null) {
                a = new DataOsdSetSdrStatus();
            }
            dataOsdSetSdrStatus = a;
        }
        return dataOsdSetSdrStatus;
    }

    public DataOsdSetSdrStatus a(boolean z) {
        this.b = z ? 1 : 0;
        return this;
    }

    public DataOsdSetSdrStatus a(int i) {
        this.c = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.b;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        if (this.c == 0) {
            cVar.h = DeviceType.OFDM.value();
        } else {
            cVar.h = DeviceType.OSD.value();
        }
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.j.a();
        cVar.n = i.a.SetSdrStatus.a();
        start(cVar, dVar);
    }
}
