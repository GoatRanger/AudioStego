package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.a;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataCommonGetPushAppGpsConfig extends n implements e {
    private static DataCommonGetPushAppGpsConfig instance = null;
    private a ResponseCode = a.D;
    private boolean isStart = false;
    private int pushInterval = 0;

    public static DataCommonGetPushAppGpsConfig getInstance() {
        if (instance == null) {
            instance = new DataCommonGetPushAppGpsConfig();
        }
        return instance;
    }

    public boolean isStart() {
        boolean z;
        if (((Integer) get(0, 1, Integer.class)).intValue() == 1) {
            z = true;
        } else {
            z = false;
        }
        this.isStart = z;
        if (!this.isStart) {
            this.pushInterval = 0;
        }
        return this.isStart;
    }

    public int getPushInterval() {
        this.pushInterval = ((Integer) get(1, 4, Integer.class)).intValue();
        return this.pushInterval;
    }

    public void setResponseCode(boolean z) {
        if (!this.isStart) {
            this.ResponseCode = a.b;
        } else if (z) {
            this.ResponseCode = a.b;
        } else {
            this.ResponseCode = a.d;
        }
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OFDM.value();
        cVar.j = q.a.b.a();
        cVar.k = q.c.b.a();
        cVar.l = b.a.a();
        cVar.m = p.a.a();
        cVar.n = dji.midware.data.config.P3.d.a.x.a();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.ResponseCode.a();
    }
}
