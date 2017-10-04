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

public class DataRcDeleteMaster extends n implements e {
    private static DataRcDeleteMaster instance = null;
    private int ID;

    public static synchronized DataRcDeleteMaster getInstance() {
        DataRcDeleteMaster dataRcDeleteMaster;
        synchronized (DataRcDeleteMaster.class) {
            if (instance == null) {
                instance = new DataRcDeleteMaster();
            }
            dataRcDeleteMaster = instance;
        }
        return dataRcDeleteMaster;
    }

    public DataRcDeleteMaster setID(int i) {
        this.ID = i;
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
        cVar.n = k.a.DeleteMaster.b();
        cVar.p = getSendData();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[4];
        System.arraycopy(dji.midware.util.c.a(this.ID), 0, this._sendData, 0, 4);
    }
}
