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

public class DataCameraGetAudio extends n implements e {
    private static DataCameraGetAudio instance = null;
    byte isEnable = (byte) 0;
    int src = 0;
    byte toFirstDataRate = (byte) 0;
    byte toSecondDataRate = (byte) 0;
    int type = 0;

    public static synchronized DataCameraGetAudio getInstance() {
        DataCameraGetAudio dataCameraGetAudio;
        synchronized (DataCameraGetAudio.class) {
            if (instance == null) {
                instance = new DataCameraGetAudio();
            }
            dataCameraGetAudio = instance;
        }
        return dataCameraGetAudio;
    }

    public boolean isEnable() {
        if (this._recData == null || (this._recData[0] & 1) == 0) {
            return false;
        }
        return true;
    }

    public boolean toSecondDataRate() {
        if (this._recData == null || (this._recData[0] & 1) == 0) {
            return false;
        }
        return true;
    }

    public boolean toFirstDataRate() {
        if (this._recData == null || (this._recData[0] & 1) == 0) {
            return false;
        }
        return true;
    }

    public int getType() {
        if (this._recData != null) {
            return this._recData[1] & 3;
        }
        return 0;
    }

    public int getSrc() {
        if (this._recData != null) {
            return this._recData[1] & 3;
        }
        return 0;
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.bg.a();
        cVar.p = getSendData();
        cVar.w = 3;
        start(cVar, dVar);
    }
}
