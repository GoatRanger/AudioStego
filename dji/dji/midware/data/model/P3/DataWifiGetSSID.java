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
import java.nio.charset.Charset;

public class DataWifiGetSSID extends n implements e {
    private static DataWifiGetSSID mInstance = null;
    private boolean isFromLongan = false;

    public static synchronized DataWifiGetSSID getInstance() {
        DataWifiGetSSID dataWifiGetSSID;
        synchronized (DataWifiGetSSID.class) {
            if (mInstance == null) {
                mInstance = new DataWifiGetSSID();
            }
            dataWifiGetSSID = mInstance;
        }
        return dataWifiGetSSID;
    }

    public String getSSID() {
        String str = "";
        if (this._recData == null || this._recData.length <= 1) {
            return str;
        }
        return new String(this._recData, 1, this._recData[0], Charset.forName("UTF-8"));
    }

    protected void doPack() {
    }

    public DataWifiGetSSID setFromLongan(boolean z) {
        this.isFromLongan = z;
        return this;
    }

    public void setRecData(byte[] bArr) {
        Log.v("Get", "change");
        this.isFromLongan = false;
        super.setRecData(bArr);
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        if (this.isFromLongan) {
            cVar.h = DeviceType.WIFI.value();
        } else {
            cVar.h = DeviceType.WIFI_G.value();
        }
        ProductType c = i.getInstance().c();
        if (c == ProductType.KumquatX || c == ProductType.KumquatS) {
            cVar.h = DeviceType.WIFI.value();
        }
        cVar.j = a.REQUEST.a();
        cVar.k = q.c.YES.a();
        cVar.l = b.NO.a();
        cVar.m = p.WIFI.a();
        cVar.n = o.a.a.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
