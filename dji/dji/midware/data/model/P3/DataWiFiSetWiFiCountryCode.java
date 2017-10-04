package dji.midware.data.model.P3;

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

public class DataWiFiSetWiFiCountryCode extends n implements e {
    private static DataWiFiSetWiFiCountryCode a;
    private String b;
    private String c;
    private boolean d;

    public static synchronized DataWiFiSetWiFiCountryCode getInstance() {
        DataWiFiSetWiFiCountryCode dataWiFiSetWiFiCountryCode;
        synchronized (DataWiFiSetWiFiCountryCode.class) {
            if (a == null) {
                a = new DataWiFiSetWiFiCountryCode();
            }
            dataWiFiSetWiFiCountryCode = a;
        }
        return dataWiFiSetWiFiCountryCode;
    }

    public DataWiFiSetWiFiCountryCode a(String str) {
        this.b = str;
        return this;
    }

    public DataWiFiSetWiFiCountryCode b(String str) {
        this.c = str;
        return this;
    }

    public DataWiFiSetWiFiCountryCode a(boolean z) {
        this.d = z;
        return this;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.m = p.h.a();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        ProductType c = i.getInstance().c();
        if (c.equals(ProductType.KumquatX) || c.equals(ProductType.KumquatS)) {
            cVar.h = DeviceType.OFDM.value();
        } else if (c.equals(ProductType.Orange2)) {
            cVar.h = DeviceType.OSD.value();
        } else if (c.equals(ProductType.Pomato)) {
            cVar.h = DeviceType.OSD.value();
        }
        cVar.n = o.a.SetWiFiCountryCode.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }

    protected void doPack() {
        int i = 1;
        this._sendData = new byte[10];
        if (this.b == null || this.b.length() < 2) {
            this._sendData[0] = (byte) 0;
            this._sendData[1] = (byte) 0;
        } else {
            this._sendData[0] = (byte) this.b.charAt(0);
            this._sendData[1] = (byte) this.b.charAt(1);
        }
        this._sendData[2] = (byte) 0;
        this._sendData[3] = (byte) 0;
        if (this.c == null || this.c.length() < 2) {
            this._sendData[4] = (byte) 0;
            this._sendData[5] = (byte) 0;
        } else {
            this._sendData[4] = (byte) this.c.charAt(0);
            this._sendData[5] = (byte) this.c.charAt(1);
        }
        this._sendData[6] = (byte) 0;
        this._sendData[7] = (byte) 0;
        byte[] bArr = this._sendData;
        if (!this.d) {
            i = 0;
        }
        bArr[8] = (byte) i;
    }

    public boolean a() {
        return this._recData != null && this._recData.length >= 2 && this._recData[1] == (byte) 1;
    }
}
