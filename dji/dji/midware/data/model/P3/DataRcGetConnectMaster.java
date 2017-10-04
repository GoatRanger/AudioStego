package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.k;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataRcGetSlaveList.RcModel;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataRcGetConnectMaster extends n implements e {
    private static DataRcGetConnectMaster instance = null;

    public static synchronized DataRcGetConnectMaster getInstance() {
        DataRcGetConnectMaster dataRcGetConnectMaster;
        synchronized (DataRcGetConnectMaster.class) {
            if (instance == null) {
                instance = new DataRcGetConnectMaster();
            }
            dataRcGetConnectMaster = instance;
        }
        return dataRcGetConnectMaster;
    }

    public RcModel getMaster() {
        RcModel rcModel = new RcModel();
        rcModel.id = ((Integer) get(0, 4, Integer.class)).intValue();
        rcModel.name = c.f(c.e(this._recData, 4, 6));
        rcModel.password = ((Integer) get(10, 2, Integer.class)).intValue();
        return rcModel;
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OSD.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.g.a();
        cVar.n = k.a.GetConnectMaster.b();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
