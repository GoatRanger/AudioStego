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

public class DataRcSetSimulation extends n implements e {
    private static DataRcSetSimulation a = null;
    private boolean b = false;

    public static synchronized DataRcSetSimulation getInstance() {
        DataRcSetSimulation dataRcSetSimulation;
        synchronized (DataRcSetSimulation.class) {
            if (a == null) {
                a = new DataRcSetSimulation();
            }
            dataRcSetSimulation = a;
        }
        return dataRcSetSimulation;
    }

    public DataRcSetSimulation a() {
        this.b = true;
        return this;
    }

    public DataRcSetSimulation b() {
        this.b = false;
        return this;
    }

    protected void doPack() {
        byte b = (byte) 1;
        this._sendData = new byte[1];
        byte[] bArr = this._sendData;
        if (!this.b) {
            b = (byte) 2;
        }
        bArr[0] = b;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OSD.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.g.a();
        cVar.n = k.a.SetSimulation.b();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
