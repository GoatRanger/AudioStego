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

public class DataOsdOsmoCalibration extends n implements e {
    private static DataOsdOsmoCalibration b = null;
    private byte a;

    public static synchronized DataOsdOsmoCalibration getInstance() {
        DataOsdOsmoCalibration dataOsdOsmoCalibration;
        synchronized (DataOsdOsmoCalibration.class) {
            if (b == null) {
                b = new DataOsdOsmoCalibration();
            }
            dataOsdOsmoCalibration = b;
        }
        return dataOsdOsmoCalibration;
    }

    public void a(int i) {
        this.a = (byte) i;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = this.a;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OFDM.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.j.a();
        cVar.n = i.a.OsmoCalibration.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
