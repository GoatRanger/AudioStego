package dji.midware.data.model.P3;

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
import java.util.Calendar;

public class DataFlycSetDate extends n implements e {
    private static DataFlycSetDate a = null;

    public static synchronized DataFlycSetDate getInstance() {
        DataFlycSetDate dataFlycSetDate;
        synchronized (DataFlycSetDate.class) {
            if (a == null) {
                a = new DataFlycSetDate();
            }
            dataFlycSetDate = a;
        }
        return dataFlycSetDate;
    }

    protected void doPack() {
        Calendar instance = Calendar.getInstance();
        this._sendData = new byte[7];
        byte[] b = c.b(instance.get(1));
        this._sendData[0] = (byte) instance.get(11);
        this._sendData[1] = (byte) instance.get(12);
        this._sendData[2] = (byte) instance.get(13);
        this._sendData[3] = b[0];
        this._sendData[4] = b[1];
        this._sendData[5] = (byte) (instance.get(2) + 1);
        this._sendData[6] = (byte) instance.get(5);
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.REQUEST.a();
        cVar.k = q.c.YES.a();
        cVar.l = b.NO.a();
        cVar.m = p.FLYC.a();
        cVar.n = g.a.SetDate.a();
        cVar.p = getSendData();
        cVar.v = 2000;
        cVar.w = 5;
        start(cVar, dVar);
    }
}
