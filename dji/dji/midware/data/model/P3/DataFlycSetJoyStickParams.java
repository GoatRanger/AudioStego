package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataFlycSetJoyStickParams extends n implements e {
    private static DataFlycSetJoyStickParams a = null;
    private boolean b = false;
    private boolean c = false;
    private boolean d = false;
    private int e = 1024;
    private int f = 1024;
    private int g = 1024;
    private int h = 1024;
    private int i = 1024;
    private int j = 1024;
    private int k = 1024;
    private int l = 0;
    private byte m = (byte) 0;
    private boolean n = false;
    private int o = 0;
    private int p = 0;
    private int q = 0;
    private int r = 0;
    private int s = 1;
    private int t = 0;
    private int u = 0;
    private int v = 0;

    public enum FlycMode {
        a(0),
        P(1),
        F(2),
        OTHER(100);
        
        private int e;

        private FlycMode(int i) {
            this.e = i;
        }

        public int a() {
            return this.e;
        }

        public boolean a(int i) {
            return this.e == i;
        }

        public static FlycMode find(int i) {
            FlycMode flycMode = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return flycMode;
        }
    }

    public static synchronized DataFlycSetJoyStickParams getInstance() {
        DataFlycSetJoyStickParams dataFlycSetJoyStickParams;
        synchronized (DataFlycSetJoyStickParams.class) {
            if (a == null) {
                a = new DataFlycSetJoyStickParams();
            }
            dataFlycSetJoyStickParams = a;
        }
        return dataFlycSetJoyStickParams;
    }

    public DataFlycSetJoyStickParams a(int i, int i2, int i3, int i4) {
        this.e = i3;
        this.f = i2;
        this.g = i;
        this.h = i4;
        return this;
    }

    public DataFlycSetJoyStickParams a(FlycMode flycMode) {
        this.t = flycMode.a();
        return this;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OFDM.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.b.a();
        cVar.n = dji.midware.data.config.P3.n.a.JoySitckSetParams.a();
        start(cVar, dVar);
    }

    public void a() {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OFDM.value();
        cVar.j = a.a.a();
        cVar.k = q.c.b.a();
        cVar.l = b.a.a();
        cVar.m = p.b.a();
        cVar.n = dji.midware.data.config.P3.n.a.JoySitckSetParams.a();
        super.start(cVar);
    }

    protected void doPack() {
        byte[] bArr;
        this._sendData = new byte[13];
        this._sendData[0] = (byte) 0;
        if (this.b) {
            bArr = this._sendData;
            bArr[0] = (byte) (bArr[0] | 1);
        }
        if (this.c) {
            bArr = this._sendData;
            bArr[0] = (byte) (bArr[0] | 2);
        }
        if (this.d) {
            bArr = this._sendData;
            bArr[0] = (byte) (bArr[0] | 4);
        }
        dji.midware.util.c.a(b(), this._sendData, 1);
        this._sendData[11] = (byte) 0;
        if (this.n) {
            bArr = this._sendData;
            bArr[11] = (byte) (bArr[11] | 1);
        }
        bArr = this._sendData;
        bArr[11] = (byte) (bArr[11] | (this.o << 1));
        bArr = this._sendData;
        bArr[11] = (byte) (bArr[11] | (this.p << 6));
        bArr = this._sendData;
        bArr[11] = (byte) (bArr[11] | (this.q << 7));
        this._sendData[12] = (byte) 0;
        bArr = this._sendData;
        bArr[12] = (byte) (bArr[12] | this.r);
        bArr = this._sendData;
        bArr[12] = (byte) (bArr[12] | (this.s << 1));
        bArr = this._sendData;
        bArr[12] = (byte) (bArr[12] | (this.t << 2));
        bArr = this._sendData;
        bArr[12] = (byte) (bArr[12] | (this.u << 4));
        bArr = this._sendData;
        bArr[12] = (byte) (bArr[12] | (this.v << 6));
    }

    private byte[] b() {
        r0 = new byte[10];
        byte[] a = dji.midware.util.c.a(this.e << 0);
        r0[0] = (byte) (r0[0] | a[0]);
        r0[1] = a[1];
        a = dji.midware.util.c.a(this.f << 3);
        r0[1] = (byte) (r0[1] | a[0]);
        r0[2] = a[1];
        a = dji.midware.util.c.a(this.g << 6);
        r0[2] = (byte) (r0[2] | a[0]);
        r0[3] = a[1];
        r0[4] = a[2];
        a = dji.midware.util.c.a(this.h << 1);
        r0[4] = (byte) (r0[4] | a[0]);
        r0[5] = a[1];
        a = dji.midware.util.c.a(this.i << 4);
        r0[5] = (byte) (r0[5] | a[0]);
        r0[6] = a[1];
        a = dji.midware.util.c.a(this.j << 7);
        r0[6] = (byte) (r0[6] | a[0]);
        r0[7] = a[1];
        r0[8] = a[2];
        a = dji.midware.util.c.a(this.k << 2);
        r0[8] = (byte) (r0[8] | a[0]);
        r0[9] = a[1];
        r0[9] = (byte) (r0[9] | (this.l << 5));
        r0[9] = (byte) (r0[9] | (this.m << 7));
        return r0;
    }
}
