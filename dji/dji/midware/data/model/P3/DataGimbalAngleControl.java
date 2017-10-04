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

public class DataGimbalAngleControl extends n implements e {
    private static DataGimbalAngleControl a = null;
    private short b;
    private short c;
    private short d;
    private short e = (short) 300;
    private boolean f;
    private boolean g;
    private boolean h;
    private int i;
    private boolean j = true;
    private boolean k = true;
    private boolean l = true;

    public static synchronized DataGimbalAngleControl getInstance() {
        DataGimbalAngleControl dataGimbalAngleControl;
        synchronized (DataGimbalAngleControl.class) {
            if (a == null) {
                a = new DataGimbalAngleControl();
            }
            dataGimbalAngleControl = a;
        }
        return dataGimbalAngleControl;
    }

    public DataGimbalAngleControl a(short s) {
        this.b = s;
        return this;
    }

    public DataGimbalAngleControl b(short s) {
        this.c = s;
        return this;
    }

    public DataGimbalAngleControl c(short s) {
        this.d = s;
        return this;
    }

    public DataGimbalAngleControl a(boolean z) {
        this.f = z;
        return this;
    }

    public DataGimbalAngleControl b(boolean z) {
        this.g = z;
        return this;
    }

    public DataGimbalAngleControl a(int i) {
        this.i = i;
        return this;
    }

    public DataGimbalAngleControl c(boolean z) {
        this.h = z;
        return this;
    }

    public DataGimbalAngleControl d(boolean z) {
        this.j = z;
        return this;
    }

    public DataGimbalAngleControl e(boolean z) {
        this.k = z;
        return this;
    }

    public DataGimbalAngleControl f(boolean z) {
        this.l = z;
        return this;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.GIMBAL.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.e.a();
        cVar.n = h.a.AngleControl.a();
        super.start(cVar, dVar);
    }

    public void a() {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.GIMBAL.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.e.a();
        cVar.n = h.a.AngleControl.a();
        super.start(cVar);
    }

    protected void doPack() {
        int i;
        int i2 = 1;
        this._sendData = new byte[10];
        System.arraycopy(dji.midware.util.c.b(this.b), 0, this._sendData, 0, 2);
        System.arraycopy(dji.midware.util.c.b(this.c), 0, this._sendData, 2, 2);
        System.arraycopy(dji.midware.util.c.b(this.d), 0, this._sendData, 4, 2);
        System.arraycopy(dji.midware.util.c.b(this.e), 0, this._sendData, 6, 2);
        if (this.f && this.g) {
            i = 3;
        } else {
            i = 0;
        }
        if (!this.f && this.g) {
            i = 1;
        }
        if (this.f && !this.g) {
            i = 2;
        }
        this._sendData[8] = (byte) (((this.h ? 1 : 0) + (i << 6)) << 5);
        byte[] bArr = this._sendData;
        byte b = this._sendData[8];
        if (this.j) {
            i = 1;
        } else {
            i = 0;
        }
        bArr[8] = (byte) ((i + b) << 4);
        bArr = this._sendData;
        b = this._sendData[8];
        if (this.k) {
            i = 1;
        } else {
            i = 0;
        }
        bArr[8] = (byte) ((i + b) << 3);
        byte[] bArr2 = this._sendData;
        byte b2 = this._sendData[8];
        if (!this.l) {
            i2 = 0;
        }
        bArr2[8] = (byte) ((b2 + i2) << 2);
        this._sendData[9] = (byte) this.i;
    }
}
