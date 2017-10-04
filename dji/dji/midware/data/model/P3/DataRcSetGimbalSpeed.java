package dji.midware.data.model.P3;

import android.util.Log;
import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.k;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataRcSetGimbalSpeed extends n implements e {
    private static DataRcSetGimbalSpeed a = null;
    private int b;
    private int c;
    private int d;

    public static synchronized DataRcSetGimbalSpeed getInstance() {
        DataRcSetGimbalSpeed dataRcSetGimbalSpeed;
        synchronized (DataRcSetGimbalSpeed.class) {
            if (a == null) {
                a = new DataRcSetGimbalSpeed();
            }
            dataRcSetGimbalSpeed = a;
        }
        return dataRcSetGimbalSpeed;
    }

    public DataRcSetGimbalSpeed a(int i) {
        this.b = i;
        return this;
    }

    public DataRcSetGimbalSpeed b(int i) {
        this.c = i;
        return this;
    }

    public DataRcSetGimbalSpeed c(int i) {
        this.d = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[3];
        this._sendData[0] = (byte) this.b;
        this._sendData[1] = (byte) this.c;
        this._sendData[2] = (byte) this.d;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OSD.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.g.a();
        cVar.n = k.a.SetGimbalSpeed.b();
        cVar.p = getSendData();
        start(cVar, dVar);
    }

    protected void LogPack(String str) {
        Log.d("", "gimbal " + str);
    }
}
