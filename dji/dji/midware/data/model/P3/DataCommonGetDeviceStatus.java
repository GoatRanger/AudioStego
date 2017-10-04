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

public class DataCommonGetDeviceStatus extends n implements e {
    private static DataCommonGetDeviceStatus instance = null;
    private int mFirst = 0;
    private boolean mForceSetReceiveId = false;
    private int mReceiveId = 0;
    private DeviceType mReceiveType = DeviceType.RC;
    private int mSecond = 0;

    public static synchronized DataCommonGetDeviceStatus getInstance() {
        DataCommonGetDeviceStatus dataCommonGetDeviceStatus;
        synchronized (DataCommonGetDeviceStatus.class) {
            if (instance == null) {
                instance = new DataCommonGetDeviceStatus();
            }
            dataCommonGetDeviceStatus = instance;
        }
        return dataCommonGetDeviceStatus;
    }

    public DataCommonGetDeviceStatus setReceiveType(DeviceType deviceType) {
        this.mReceiveType = deviceType;
        return this;
    }

    public DataCommonGetDeviceStatus setReceiveId(int i) {
        this.mReceiveId = i;
        return this;
    }

    public DataCommonGetDeviceStatus setReceiveIdForce(int i) {
        this.mReceiveId = i;
        this.mForceSetReceiveId = true;
        return this;
    }

    public DataCommonGetDeviceStatus setVersioin(int i, int i2) {
        this.mFirst = i;
        this.mSecond = i2;
        return this;
    }

    public int getMode() {
        return ((Integer) get(1, 4, Integer.class)).intValue() & 1;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) 0;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = this.mReceiveType.value();
        cVar.g = this.mForceSetReceiveId ? this.mReceiveId : 0;
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.a.a();
        cVar.n = dji.midware.data.config.P3.d.a.h.a();
        cVar.v = 5000;
        cVar.w = 1;
        start(cVar, dVar);
    }
}
