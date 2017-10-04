package dji.midware.data.model.P3;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.here.posclient.analytics.TrackerEvent;
import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.g;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataFlycUploadUnlimitAreas extends n implements e {
    private static DataFlycUploadUnlimitAreas a = null;
    private int b = 0;
    private byte[] c = null;

    public static synchronized DataFlycUploadUnlimitAreas getInstance() {
        DataFlycUploadUnlimitAreas dataFlycUploadUnlimitAreas;
        synchronized (DataFlycUploadUnlimitAreas.class) {
            if (a == null) {
                a = new DataFlycUploadUnlimitAreas();
            }
            dataFlycUploadUnlimitAreas = a;
        }
        return dataFlycUploadUnlimitAreas;
    }

    public int a() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public DataFlycUploadUnlimitAreas a(int i) {
        this.b = i;
        return this;
    }

    public DataFlycUploadUnlimitAreas a(byte[] bArr) {
        this.c = bArr;
        return this;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.E.a();
        cVar.v = CommonStatusCodes.AUTH_API_INVALID_CREDENTIALS;
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[TrackerEvent.PositioningOfflinePrivateIndoor];
        if (this.c == null) {
            this._sendData[0] = (byte) 0;
        } else {
            this._sendData[0] = (byte) this.c.length;
            int i = 0;
            while (i < this.c.length && i < 128) {
                if (i < this.c.length) {
                    this._sendData[i + 5] = this.c[i];
                } else {
                    this._sendData[i + 5] = (byte) 0;
                }
                i++;
            }
        }
        this._sendData[1] = (byte) this.b;
    }
}
