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

public class DataOsdSetSweepFrequency extends n implements e {
    private static DataOsdSetSweepFrequency a = null;
    private int b;
    private int c = 0;
    private int d = 0;

    public static synchronized DataOsdSetSweepFrequency getInstance() {
        DataOsdSetSweepFrequency dataOsdSetSweepFrequency;
        synchronized (DataOsdSetSweepFrequency.class) {
            if (a == null) {
                a = new DataOsdSetSweepFrequency();
            }
            dataOsdSetSweepFrequency = a;
        }
        return dataOsdSetSweepFrequency;
    }

    public DataOsdSetSweepFrequency a(boolean z) {
        this.b = z ? 1 : 0;
        return this;
    }

    public DataOsdSetSweepFrequency a(int i) {
        this.d = i;
        return this;
    }

    public DataOsdSetSweepFrequency b(int i) {
        this.c = i;
        return this;
    }

    public DataOsdSetSweepFrequency b(boolean z) {
        this.b = z ? 0 : 1;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[2];
        this._sendData[0] = (byte) this.b;
        this._sendData[1] = (byte) this.d;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        if (this.c == 1) {
            cVar.h = DeviceType.OFDM.value();
        } else {
            cVar.h = DeviceType.OSD.value();
        }
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.j.a();
        cVar.n = i.a.SetSweepFrequency.a();
        start(cVar, dVar);
    }
}
