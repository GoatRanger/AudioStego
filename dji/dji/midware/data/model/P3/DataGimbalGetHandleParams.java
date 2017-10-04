package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.h;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataGimbalGetHandleParams extends n implements e {
    private static final String TAG = "DataGimbalGetHandleParams";

    public int getDualChannelEnable() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public int getTiltDirection() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }

    public int getPanDirection() {
        return ((Integer) get(2, 1, Integer.class)).intValue();
    }

    public int getProfile() {
        return ((Integer) get(3, 1, Integer.class)).intValue();
    }

    public int getPitchFree() {
        return ((Integer) get(4, 1, Integer.class)).intValue();
    }

    public boolean getZoom2SpeedEnable() {
        return (((Integer) get(5, 1, Integer.class)).intValue() & 1) == 1;
    }

    public boolean getRotationFocusEnable() {
        return (((Integer) get(6, 1, Integer.class)).intValue() & 1) == 1;
    }

    public boolean getCellphoneSensorDisable() {
        return (((Integer) get(7, 1, Integer.class)).intValue() & 1) == 1;
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.GIMBAL.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.e.a();
        cVar.n = h.a.GetHandleParams.a();
        start(cVar, dVar);
    }
}
