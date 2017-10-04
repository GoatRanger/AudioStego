package dji.midware.data.model.P3;

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

public class DataFlycSendGpsInfo extends n implements e {
    private static DataFlycSendGpsInfo instance = null;
    private double latitude;
    private double longitude;
    private short mAltitude = (short) 0;
    private short mHeading = (short) 0;

    public static synchronized DataFlycSendGpsInfo getInstance() {
        DataFlycSendGpsInfo dataFlycSendGpsInfo;
        synchronized (DataFlycSendGpsInfo.class) {
            if (instance == null) {
                instance = new DataFlycSendGpsInfo();
            }
            dataFlycSendGpsInfo = instance;
        }
        return dataFlycSendGpsInfo;
    }

    public DataFlycSendGpsInfo setLatitude(double d) {
        this.latitude = d;
        return this;
    }

    public DataFlycSendGpsInfo setLongitude(double d) {
        this.longitude = d;
        return this;
    }

    public DataFlycSendGpsInfo setAltitude(short s) {
        this.mAltitude = s;
        return this;
    }

    public DataFlycSendGpsInfo setHeading(short s) {
        this.mHeading = s;
        return this;
    }

    public int getResult() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.ax.a();
        super.start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[20];
        System.arraycopy(dji.midware.util.c.a(this.latitude), 0, this._sendData, 0, 8);
        System.arraycopy(dji.midware.util.c.a(this.longitude), 0, this._sendData, 8, 8);
        System.arraycopy(dji.midware.util.c.b(this.mAltitude), 0, this._sendData, 16, 2);
        System.arraycopy(dji.midware.util.c.b(this.mHeading), 0, this._sendData, 18, 2);
    }
}
