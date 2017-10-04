package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataCameraSetShutterSpeed.TYPE;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataCameraGetShutterSpeed extends n implements e {
    private static DataCameraGetShutterSpeed instance = null;

    public static synchronized DataCameraGetShutterSpeed getInstance() {
        DataCameraGetShutterSpeed dataCameraGetShutterSpeed;
        synchronized (DataCameraGetShutterSpeed.class) {
            if (instance == null) {
                instance = new DataCameraGetShutterSpeed();
            }
            dataCameraGetShutterSpeed = instance;
        }
        return dataCameraGetShutterSpeed;
    }

    public TYPE getAuto() {
        return TYPE.find(((Integer) get(0, 1, Integer.class)).intValue());
    }

    public boolean isReciprocal() {
        return (((Integer) get(1, 2, Integer.class)).intValue() >> 15) == 1;
    }

    public float getValue() {
        int intValue = -32769 & ((Integer) get(1, 2, Integer.class)).intValue();
        return Float.valueOf(intValue + "." + ((Integer) get(3, 1, Integer.class)).intValue()).floatValue();
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
        cVar.n = dji.midware.data.config.P3.b.a.F.a();
        start(cVar, dVar);
    }
}
