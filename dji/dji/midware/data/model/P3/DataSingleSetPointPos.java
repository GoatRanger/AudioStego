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

public class DataSingleSetPointPos extends n implements e {
    private float a = 0.0f;
    private float b = 0.0f;
    private short c = (short) 0;
    private TapMode d = TapMode.POSITIVE_FLY;

    public enum TapMode {
        POSITIVE_FLY(0),
        REVERSE_FLY(1),
        HEADLESS_CIRCLE(2),
        HEADLESS_PARALLEL(3),
        OTHER(100);
        
        private final int f;

        private TapMode(int i) {
            this.f = i;
        }

        public int a() {
            return this.f;
        }

        public boolean a(int i) {
            return this.f == i;
        }

        public static TapMode find(int i) {
            TapMode tapMode = POSITIVE_FLY;
            for (TapMode tapMode2 : values()) {
                if (tapMode2.a(i)) {
                    return tapMode2;
                }
            }
            return tapMode;
        }
    }

    public DataSingleSetPointPos a(float f, float f2) {
        this.a = f;
        this.b = f2;
        return this;
    }

    public DataSingleSetPointPos a(short s) {
        this.c = s;
        return this;
    }

    public DataSingleSetPointPos a(TapMode tapMode) {
        this.d = tapMode;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[11];
        System.arraycopy(c.a(this.a), 0, this._sendData, 0, 4);
        System.arraycopy(c.a(this.b), 0, this._sendData, 4, 4);
        System.arraycopy(c.b(this.c), 0, this._sendData, 8, 2);
        this._sendData[10] = (byte) this.d.a();
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
        cVar.n = f.a.SetPointPos.a();
        cVar.v = CommonStatusCodes.AUTH_API_INVALID_CREDENTIALS;
        cVar.w = 1;
        start(cVar, dVar);
    }
}
