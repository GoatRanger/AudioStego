package dji.midware.data.model.P3;

import android.support.v4.media.TransportMediator;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.g;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataFlycSendAgpsData extends n implements e {
    private static DataFlycSendAgpsData a = null;
    private byte[] b = new byte[3];
    private byte[] c;

    public static synchronized DataFlycSendAgpsData getInstance() {
        DataFlycSendAgpsData dataFlycSendAgpsData;
        synchronized (DataFlycSendAgpsData.class) {
            if (a == null) {
                a = new DataFlycSendAgpsData();
            }
            dataFlycSendAgpsData = a;
        }
        return dataFlycSendAgpsData;
    }

    public void a(byte[] bArr) {
        this.c = bArr;
    }

    public void a(short s, byte b, byte b2) {
        System.arraycopy(c.b(s), 0, this.b, 0, 2);
        this.b[2] = (byte) ((b2 << 7) | (b & TransportMediator.KEYCODE_MEDIA_PAUSE));
    }

    protected void doPack() {
        int length = this.b.length + this.c.length;
        if (this._sendData == null || this._sendData.length != length) {
            this._sendData = new byte[length];
        }
        System.arraycopy(this.b, 0, this._sendData, 0, this.b.length);
        System.arraycopy(this.c, 0, this._sendData, this.b.length, this.c.length);
    }

    public Short a() {
        return (Short) get(0, 2, Short.class);
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.aA.a();
        cVar.w = 5;
        cVar.v = 5000;
        start(cVar, dVar);
    }
}
