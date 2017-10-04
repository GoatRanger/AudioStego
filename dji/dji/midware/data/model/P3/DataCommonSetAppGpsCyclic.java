package dji.midware.data.model.P3;

import com.here.android.mpa.mapping.Map;
import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataCommonSetAppGpsCyclic extends n implements e {
    private int a = -1;
    private double b = Map.MOVE_PRESERVE_ZOOM_LEVEL;
    private double c = Map.MOVE_PRESERVE_ZOOM_LEVEL;
    private float d = -1.0f;

    public DataCommonSetAppGpsCyclic a(int i, double d, double d2, float f) {
        this.a = i;
        this.b = d;
        this.c = d2;
        this.d = f;
        return this;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OFDM.value();
        cVar.j = a.a.a();
        cVar.k = q.c.b.a();
        cVar.l = b.a.a();
        cVar.m = p.a.a();
        cVar.n = dji.midware.data.config.P3.d.a.y.a();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[21];
        this._sendData[0] = (byte) this.a;
        System.arraycopy(dji.midware.util.c.a((this.c / 180.0d) * 3.141592653589793d), 0, this._sendData, 1, 8);
        System.arraycopy(dji.midware.util.c.a((this.b / 180.0d) * 3.141592653589793d), 0, this._sendData, 9, 8);
        System.arraycopy(dji.midware.util.c.a(this.d), 0, this._sendData, 17, 4);
    }
}
