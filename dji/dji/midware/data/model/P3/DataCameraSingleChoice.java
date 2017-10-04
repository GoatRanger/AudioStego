package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataCameraSingleChoice extends n implements e {
    private static DataCameraSingleChoice instance = null;
    private int index;

    public static synchronized DataCameraSingleChoice getInstance() {
        DataCameraSingleChoice dataCameraSingleChoice;
        synchronized (DataCameraSingleChoice.class) {
            if (instance == null) {
                instance = new DataCameraSingleChoice();
            }
            dataCameraSingleChoice = instance;
        }
        return dataCameraSingleChoice;
    }

    public DataCameraSingleChoice setKey(int i) {
        this.index = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[4];
        c.a(c.a(this.index), this._sendData, 0);
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.aK.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
