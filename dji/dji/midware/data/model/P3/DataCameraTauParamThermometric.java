package dji.midware.data.model.P3;

import com.google.android.gms.common.api.CommonStatusCodes;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.model.P3.DataCameraTauParamer.ParamCmd;
import dji.midware.e.d;
import dji.midware.util.c;

public class DataCameraTauParamThermometric extends DataCameraTauParamer {
    public DataCameraTauParamThermometric() {
        this.b = ParamCmd.REGION_THERMOMETRIC;
    }

    public DataCameraTauParamThermometric a(float f, float f2) {
        this.c = new byte[8];
        System.arraycopy(c.a(f), 0, this.c, 0, 4);
        System.arraycopy(c.a(f2), 0, this.c, 4, 4);
        return this;
    }

    public float a() {
        return ((Float) get(0, 4, Float.class)).floatValue();
    }

    public float b() {
        return ((Float) get(4, 4, Float.class)).floatValue();
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.bU.a();
        cVar.v = CommonStatusCodes.AUTH_API_INVALID_CREDENTIALS;
        cVar.w = 1;
        start(cVar, dVar);
    }
}
