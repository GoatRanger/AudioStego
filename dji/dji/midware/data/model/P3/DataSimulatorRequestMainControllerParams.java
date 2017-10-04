package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.l;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataSimulatorRequestMainControllerParams extends n implements e {
    private static DataSimulatorRequestMainControllerParams a;

    public static synchronized DataSimulatorRequestMainControllerParams getInstance() {
        DataSimulatorRequestMainControllerParams dataSimulatorRequestMainControllerParams;
        synchronized (DataSimulatorRequestMainControllerParams.class) {
            if (a == null) {
                a = new DataSimulatorRequestMainControllerParams();
            }
            dataSimulatorRequestMainControllerParams = a;
        }
        return dataSimulatorRequestMainControllerParams;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.b.a();
        cVar.l = b.a.a();
        cVar.m = p.l.a();
        cVar.n = l.a.RequestMainControllerParams.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) 1;
    }
}
