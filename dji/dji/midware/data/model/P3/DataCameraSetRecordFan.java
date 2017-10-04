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

public class DataCameraSetRecordFan extends n implements e {
    private static DataCameraSetRecordFan d = null;
    private byte a = (byte) 0;
    private int b = 0;
    private int c = 0;

    public static synchronized DataCameraSetRecordFan getInstance() {
        DataCameraSetRecordFan dataCameraSetRecordFan;
        synchronized (DataCameraSetRecordFan.class) {
            if (d == null) {
                d = new DataCameraSetRecordFan();
            }
            dataCameraSetRecordFan = d;
        }
        return dataCameraSetRecordFan;
    }

    public DataCameraSetRecordFan a() {
        this.a = (byte) 0;
        return this;
    }

    public DataCameraSetRecordFan a(boolean z) {
        if (z) {
            this.a = (byte) 1;
        } else {
            this.a = (byte) 0;
        }
        return this;
    }

    public DataCameraSetRecordFan a(int i) {
        this.b = i;
        return this;
    }

    public DataCameraSetRecordFan b(int i) {
        this.c = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[3];
        this._sendData[0] = this.a;
        this._sendData[1] = (byte) this.b;
        this._sendData[2] = (byte) this.c;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.bB.a();
        cVar.p = getSendData();
        cVar.v = 2000;
        start(cVar, dVar);
    }
}
