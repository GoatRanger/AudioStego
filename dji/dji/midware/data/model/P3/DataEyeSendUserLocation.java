package dji.midware.data.model.P3;

import com.google.android.gms.common.api.CommonStatusCodes;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.f;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataEyeSendUserLocation extends n implements e {
    private static DataEyeSendUserLocation a = null;
    private double b = 0.0d;
    private double c = 0.0d;
    private float d = 0.0f;
    private float e = 0.0f;
    private short f = (short) 0;

    public static synchronized DataEyeSendUserLocation getInstance() {
        DataEyeSendUserLocation dataEyeSendUserLocation;
        synchronized (DataEyeSendUserLocation.class) {
            if (a == null) {
                a = new DataEyeSendUserLocation();
            }
            dataEyeSendUserLocation = a;
        }
        return dataEyeSendUserLocation;
    }

    public DataEyeSendUserLocation a(double d, double d2) {
        this.b = d;
        this.c = d2;
        return this;
    }

    public DataEyeSendUserLocation a(float f, float f2) {
        this.d = f;
        this.e = f2;
        return this;
    }

    public DataEyeSendUserLocation a(short s) {
        this.f = s;
        return this;
    }

    protected void doPack() {
        if (this._sendData == null) {
            this._sendData = new byte[26];
        }
        System.arraycopy(c.a(this.b), 0, this._sendData, 0, 8);
        System.arraycopy(c.a(this.c), 0, this._sendData, 8, 8);
        System.arraycopy(c.a(this.d), 0, this._sendData, 16, 4);
        System.arraycopy(c.a(this.e), 0, this._sendData, 20, 4);
        System.arraycopy(c.b(this.f), 0, this._sendData, 24, 2);
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.SINGLE.value();
        cVar.g = 7;
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.k.a();
        cVar.n = f.a.SendTrackingUserLocation.a();
        cVar.v = CommonStatusCodes.AUTH_API_INVALID_CREDENTIALS;
        cVar.w = 1;
        start(cVar, dVar);
    }
}
