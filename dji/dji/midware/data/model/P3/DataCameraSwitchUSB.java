package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataCameraSwitchUSB extends n implements e {
    private static DataCameraSwitchUSB a = null;
    private int b;

    public static synchronized DataCameraSwitchUSB getInstance() {
        DataCameraSwitchUSB dataCameraSwitchUSB;
        synchronized (DataCameraSwitchUSB.class) {
            if (a == null) {
                a = new DataCameraSwitchUSB();
            }
            dataCameraSwitchUSB = a;
        }
        return dataCameraSwitchUSB;
    }

    public DataCameraSwitchUSB a(int i) {
        this.b = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[2];
        this._sendData[0] = (byte) 0;
        this._sendData[1] = (byte) this.b;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.e.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
