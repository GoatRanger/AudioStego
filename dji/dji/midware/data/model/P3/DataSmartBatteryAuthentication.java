package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.m;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataSmartBatteryAuthentication extends n implements e {
    private static DataSmartBatteryAuthentication a = null;
    private int b = 0;
    private boolean c = false;
    private byte[] d;

    public static DataSmartBatteryAuthentication getInstance() {
        if (a == null) {
            a = new DataSmartBatteryAuthentication();
        }
        return a;
    }

    public DataSmartBatteryAuthentication a(int i) {
        this.b = i;
        return this;
    }

    public DataSmartBatteryAuthentication a(boolean z) {
        this.c = z;
        return this;
    }

    public DataSmartBatteryAuthentication a(byte[] bArr) {
        this.d = bArr;
        return this;
    }

    public int a() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public int b() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }

    public boolean c() {
        return ((Integer) get(2, 1, Integer.class)).intValue() == 0;
    }

    public byte[] d() {
        if (this._sendData == null || this._sendData.length != 23) {
            return null;
        }
        Object obj = new byte[20];
        System.arraycopy(this._sendData, 2, obj, 0, 20);
        return obj;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.BATTERY.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.n.a();
        cVar.n = m.a.Authentication.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }

    protected void doPack() {
        int i;
        this._sendData = new byte[22];
        this._sendData[0] = (byte) this.b;
        byte[] bArr = this._sendData;
        if (this.c) {
            i = 0;
        } else {
            i = 1;
        }
        bArr[1] = (byte) i;
        if (this.d != null && this.d.length <= 20) {
            System.arraycopy(this.d, 0, this._sendData, 2, this.d.length);
        }
    }
}
