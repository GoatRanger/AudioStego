package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataDm368SetParams.DM368CmdId;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataDm385GetParams extends n implements e {
    private static DataDm385GetParams instance = null;

    public static synchronized DataDm385GetParams getInstance() {
        DataDm385GetParams dataDm385GetParams;
        synchronized (DataDm385GetParams.class) {
            if (instance == null) {
                instance = new DataDm385GetParams();
            }
            dataDm385GetParams = instance;
        }
        return dataDm385GetParams;
    }

    public int getTransmissionMode() {
        return ((Integer) get(2, 1, Integer.class)).intValue();
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) DM368CmdId.SetTransmissionMode.a();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.g = 1;
        cVar.h = DeviceType.DM368.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.i.a();
        cVar.n = dji.midware.data.config.P3.e.a.GetParams.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
