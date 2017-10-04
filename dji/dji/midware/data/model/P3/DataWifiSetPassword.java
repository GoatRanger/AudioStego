package dji.midware.data.model.P3;

import android.util.Log;
import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.config.P3.o;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataWifiSetPassword extends n implements e {
    private static DataWifiSetPassword a = null;
    private boolean b = false;
    private byte[] c = null;

    public static synchronized DataWifiSetPassword getInstance() {
        DataWifiSetPassword dataWifiSetPassword;
        synchronized (DataWifiSetPassword.class) {
            if (a == null) {
                a = new DataWifiSetPassword();
            }
            dataWifiSetPassword = a;
        }
        return dataWifiSetPassword;
    }

    public DataWifiSetPassword a(byte[] bArr) {
        this.c = bArr;
        return this;
    }

    public DataWifiSetPassword a(boolean z) {
        this.b = z;
        return this;
    }

    public void setRecData(byte[] bArr) {
        Log.v("Get", "change");
        this.b = false;
        super.setRecData(bArr);
    }

    protected void doPack() {
        this._sendData = new byte[(this.c.length + 1)];
        this._sendData[0] = (byte) this.c.length;
        System.arraycopy(this.c, 0, this._sendData, 1, this.c.length);
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        if (this.b) {
            cVar.h = DeviceType.WIFI.value();
        } else {
            cVar.h = DeviceType.WIFI_G.value();
        }
        ProductType c = i.getInstance().c();
        if (c == ProductType.KumquatX || c == ProductType.KumquatS) {
            cVar.h = DeviceType.WIFI.value();
        }
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.h.a();
        cVar.n = o.a.SetPassword.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
