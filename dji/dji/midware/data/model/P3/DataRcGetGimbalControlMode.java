package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.k;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataRcSetGimbalControlMode.MODE;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataRcGetGimbalControlMode extends n implements e {
    private static DataRcGetGimbalControlMode instance = null;
    private MODE mode;

    public static synchronized DataRcGetGimbalControlMode getInstance() {
        DataRcGetGimbalControlMode dataRcGetGimbalControlMode;
        synchronized (DataRcGetGimbalControlMode.class) {
            if (instance == null) {
                instance = new DataRcGetGimbalControlMode();
            }
            dataRcGetGimbalControlMode = instance;
        }
        return dataRcGetGimbalControlMode;
    }

    public void setRecData(byte[] bArr) {
        super.setRecData(bArr);
        this.mode = MODE.find(((Integer) get(0, 1, Integer.class)).intValue());
    }

    public void setMode(MODE mode) {
        this.mode = mode;
    }

    public MODE getMode() {
        return this.mode;
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OSD.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.g.a();
        cVar.n = k.a.GetGimbalControlMode.b();
        start(cVar, dVar);
    }
}
