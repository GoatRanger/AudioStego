package dji.midware.data.model.P3;

import android.support.v4.media.TransportMediator;
import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataCameraGetQuickPlayBack extends n implements e {
    private static final byte FLAG_ENABLE = Byte.MIN_VALUE;
    private static DataCameraGetQuickPlayBack instance = null;

    public static synchronized DataCameraGetQuickPlayBack getInstance() {
        DataCameraGetQuickPlayBack dataCameraGetQuickPlayBack;
        synchronized (DataCameraGetQuickPlayBack.class) {
            if (instance == null) {
                instance = new DataCameraGetQuickPlayBack();
            }
            dataCameraGetQuickPlayBack = instance;
        }
        return dataCameraGetQuickPlayBack;
    }

    public byte getTime() {
        int intValue = ((Integer) get(0, 1, Integer.class)).intValue();
        if (((intValue & -128) != 0 ? 1 : 0) != 0) {
            return (byte) (intValue & TransportMediator.KEYCODE_MEDIA_PAUSE);
        }
        return (byte) 0;
    }

    public boolean isEnable() {
        if ((((Integer) get(0, 1, Integer.class)).intValue() & -128) != 0) {
            return true;
        }
        return false;
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.ak.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
