package dji.midware.data.model.P3;

import com.google.android.gms.common.api.CommonStatusCodes;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.f;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataSingleSetTrackSelect extends n implements e {
    private int a = 0;
    private float b = 0.0f;
    private float c = 0.0f;
    private float d = 0.0f;
    private float e = 0.0f;
    private int f = 0;
    private short g = (short) 0;

    public DataSingleSetTrackSelect a(int i) {
        this.a = i;
        return this;
    }

    public DataSingleSetTrackSelect a(float f, float f2, float f3, float f4) {
        this.b = f;
        this.c = f2;
        this.d = f3;
        this.e = f4;
        return this;
    }

    public DataSingleSetTrackSelect a(short s) {
        this.g = s;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[21];
        this._sendData[0] = (byte) this.a;
        System.arraycopy(c.a(this.b), 0, this._sendData, 1, 4);
        System.arraycopy(c.a(this.c), 0, this._sendData, 5, 4);
        System.arraycopy(c.a(this.d), 0, this._sendData, 9, 4);
        System.arraycopy(c.a(this.e), 0, this._sendData, 13, 4);
        this._sendData[17] = (byte) this.f;
        System.arraycopy(c.b(this.g), 0, this._sendData, 18, 2);
        this._sendData[20] = (byte) 0;
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.SINGLE.value();
        cVar.g = 7;
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.k.a();
        cVar.n = f.a.SetTrackSelect.a();
        cVar.v = CommonStatusCodes.AUTH_API_INVALID_CREDENTIALS;
        cVar.w = 1;
        start(cVar, dVar);
    }
}
