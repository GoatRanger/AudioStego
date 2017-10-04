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

public class DataFlycNoeMissionPauseOrResume extends n implements e {
    private static DataFlycNoeMissionPauseOrResume a;
    private boolean b;

    public DataFlycNoeMissionPauseOrResume a() {
        this.b = true;
        return this;
    }

    public DataFlycNoeMissionPauseOrResume b() {
        this.b = false;
        return this;
    }

    public static synchronized DataFlycNoeMissionPauseOrResume getInstance() {
        DataFlycNoeMissionPauseOrResume dataFlycNoeMissionPauseOrResume;
        synchronized (DataFlycNoeMissionPauseOrResume.class) {
            if (a == null) {
                a = new DataFlycNoeMissionPauseOrResume();
            }
            dataFlycNoeMissionPauseOrResume = a;
        }
        return dataFlycNoeMissionPauseOrResume;
    }

    public int c() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.ag.a();
        super.start(cVar, dVar);
    }

    protected void doPack() {
        byte b = (byte) 1;
        this._sendData = new byte[1];
        byte[] bArr = this._sendData;
        if (this.b) {
            b = (byte) 0;
        }
        bArr[0] = b;
    }
}
