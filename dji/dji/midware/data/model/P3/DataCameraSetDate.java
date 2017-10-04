package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;
import java.util.Calendar;

public class DataCameraSetDate extends n implements e {
    private static DataCameraSetDate a = null;

    public static synchronized DataCameraSetDate getInstance() {
        DataCameraSetDate dataCameraSetDate;
        synchronized (DataCameraSetDate.class) {
            if (a == null) {
                a = new DataCameraSetDate();
            }
            dataCameraSetDate = a;
        }
        return dataCameraSetDate;
    }

    protected void doPack() {
        Calendar instance = Calendar.getInstance();
        this._sendData = new byte[7];
        byte[] b = c.b(instance.get(1));
        this._sendData[0] = b[0];
        this._sendData[1] = b[1];
        this._sendData[2] = (byte) (instance.get(2) + 1);
        this._sendData[3] = (byte) instance.get(5);
        this._sendData[4] = (byte) instance.get(11);
        this._sendData[5] = (byte) instance.get(12);
        this._sendData[6] = (byte) instance.get(13);
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.al.a();
        cVar.w = 3;
        start(cVar, dVar);
    }
}
