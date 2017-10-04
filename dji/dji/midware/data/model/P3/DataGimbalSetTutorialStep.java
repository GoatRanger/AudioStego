package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.h;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataGimbalSetTutorialStep extends n implements e {
    private static DataGimbalSetTutorialStep b = null;
    byte a;

    public static synchronized DataGimbalSetTutorialStep getInstance() {
        DataGimbalSetTutorialStep dataGimbalSetTutorialStep;
        synchronized (DataGimbalSetTutorialStep.class) {
            if (b == null) {
                b = new DataGimbalSetTutorialStep();
            }
            dataGimbalSetTutorialStep = b;
        }
        return dataGimbalSetTutorialStep;
    }

    public DataGimbalSetTutorialStep a(int i) {
        this.a = (byte) i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = this.a;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.GIMBAL.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.e.a();
        cVar.n = h.a.SetTutorialStep.a();
        start(cVar, dVar);
    }
}
