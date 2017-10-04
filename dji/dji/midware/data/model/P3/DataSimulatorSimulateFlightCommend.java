package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.l;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataSimulatorSimulateFlightCommend extends n implements e {
    private static DataSimulatorSimulateFlightCommend a;
    private int b = 128;
    private int c = 64;
    private int d = 32;
    private int e = 16;
    private int f = 8;
    private int g = 4;
    private int h = 2;
    private int i = 1;
    private int j;
    private int k;
    private double l;
    private double m;
    private double n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;

    public static synchronized DataSimulatorSimulateFlightCommend getInstance() {
        DataSimulatorSimulateFlightCommend dataSimulatorSimulateFlightCommend;
        synchronized (DataSimulatorSimulateFlightCommend.class) {
            if (a == null) {
                a = new DataSimulatorSimulateFlightCommend();
            }
            dataSimulatorSimulateFlightCommend = a;
        }
        return dataSimulatorSimulateFlightCommend;
    }

    public DataSimulatorSimulateFlightCommend a() {
        this.k = 0;
        return this;
    }

    public DataSimulatorSimulateFlightCommend b() {
        this.k = 2;
        return this;
    }

    public DataSimulatorSimulateFlightCommend a(boolean z) {
        if (z) {
            this.j |= 1;
        } else {
            this.j |= 0;
        }
        return this;
    }

    public DataSimulatorSimulateFlightCommend b(boolean z) {
        if (z) {
            this.j |= 2;
        } else {
            this.j |= 0;
        }
        return this;
    }

    public DataSimulatorSimulateFlightCommend c(boolean z) {
        if (z) {
            this.j |= 4;
        } else {
            this.j |= 0;
        }
        return this;
    }

    public DataSimulatorSimulateFlightCommend a(double d) {
        this.l = d(d);
        return this;
    }

    public DataSimulatorSimulateFlightCommend b(double d) {
        this.m = d(d);
        return this;
    }

    public DataSimulatorSimulateFlightCommend c(double d) {
        this.n = 1.0d * d;
        return this;
    }

    public DataSimulatorSimulateFlightCommend a(int i) {
        this.o = i;
        return this;
    }

    public DataSimulatorSimulateFlightCommend b(int i) {
        this.p = i;
        return this;
    }

    public int c() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public DataSimulatorSimulateFlightCommend d(boolean z) {
        if (z) {
            this.q |= this.b;
        } else {
            this.q |= this.b - this.b;
        }
        return this;
    }

    public DataSimulatorSimulateFlightCommend e(boolean z) {
        if (z) {
            this.q |= this.c;
        } else {
            this.q |= this.c - this.c;
        }
        return this;
    }

    public DataSimulatorSimulateFlightCommend f(boolean z) {
        if (z) {
            this.q |= this.d;
        } else {
            this.q |= this.d - this.d;
        }
        return this;
    }

    public DataSimulatorSimulateFlightCommend g(boolean z) {
        if (z) {
            this.q |= this.e;
        } else {
            this.q |= this.e - this.e;
        }
        return this;
    }

    public DataSimulatorSimulateFlightCommend h(boolean z) {
        if (z) {
            this.q |= this.f;
        } else {
            this.q |= this.f - this.f;
        }
        return this;
    }

    public DataSimulatorSimulateFlightCommend i(boolean z) {
        if (z) {
            this.q |= this.g;
        } else {
            this.q |= this.g - this.g;
        }
        return this;
    }

    public DataSimulatorSimulateFlightCommend j(boolean z) {
        if (z) {
            this.q |= this.h;
        } else {
            this.q |= this.h - this.h;
        }
        return this;
    }

    public DataSimulatorSimulateFlightCommend k(boolean z) {
        if (z) {
            this.q |= this.i;
        } else {
            this.q |= this.i - this.i;
        }
        return this;
    }

    public DataSimulatorSimulateFlightCommend l(boolean z) {
        if (z) {
            this.r |= this.b;
        } else {
            this.r |= this.b - this.b;
        }
        return this;
    }

    public DataSimulatorSimulateFlightCommend m(boolean z) {
        if (z) {
            this.r |= this.c;
        } else {
            this.r |= this.c - this.c;
        }
        return this;
    }

    public DataSimulatorSimulateFlightCommend n(boolean z) {
        if (z) {
            this.r |= this.d;
        } else {
            this.r |= this.d - this.d;
        }
        return this;
    }

    public DataSimulatorSimulateFlightCommend o(boolean z) {
        if (z) {
            this.r |= this.e;
        } else {
            this.r |= this.e - this.e;
        }
        return this;
    }

    public DataSimulatorSimulateFlightCommend p(boolean z) {
        if (z) {
            this.r |= this.f;
        } else {
            this.r |= this.f - this.f;
        }
        return this;
    }

    public DataSimulatorSimulateFlightCommend q(boolean z) {
        if (z) {
            this.r |= this.g;
        } else {
            this.r |= this.g - this.g;
        }
        return this;
    }

    public DataSimulatorSimulateFlightCommend r(boolean z) {
        if (z) {
            this.r |= this.h;
        } else {
            this.r |= this.h - this.h;
        }
        return this;
    }

    public DataSimulatorSimulateFlightCommend s(boolean z) {
        if (z) {
            this.r |= this.i;
        } else {
            this.r |= this.i - this.i;
        }
        return this;
    }

    public DataSimulatorSimulateFlightCommend t(boolean z) {
        if (z) {
            this.s |= this.b;
        } else {
            this.s |= this.b - this.b;
        }
        return this;
    }

    public DataSimulatorSimulateFlightCommend u(boolean z) {
        if (z) {
            this.s |= this.c;
        } else {
            this.s |= this.c - this.c;
        }
        return this;
    }

    public DataSimulatorSimulateFlightCommend v(boolean z) {
        if (z) {
            this.s |= this.d;
        } else {
            this.s |= this.d - this.d;
        }
        return this;
    }

    public DataSimulatorSimulateFlightCommend w(boolean z) {
        if (z) {
            this.s |= this.e;
        } else {
            this.s |= this.e - this.e;
        }
        return this;
    }

    public DataSimulatorSimulateFlightCommend x(boolean z) {
        if (z) {
            this.s |= this.f;
        } else {
            this.s |= this.f - this.f;
        }
        return this;
    }

    public DataSimulatorSimulateFlightCommend y(boolean z) {
        if (z) {
            this.s |= this.g;
        } else {
            this.s |= this.g - this.g;
        }
        return this;
    }

    public DataSimulatorSimulateFlightCommend z(boolean z) {
        if (z) {
            this.s |= this.h;
        } else {
            this.s |= this.h - this.h;
        }
        return this;
    }

    public DataSimulatorSimulateFlightCommend A(boolean z) {
        if (z) {
            this.s |= this.i;
        } else {
            this.s |= this.i - this.i;
        }
        return this;
    }

    public DataSimulatorSimulateFlightCommend B(boolean z) {
        if (z) {
            this.t |= this.b;
        } else {
            this.t |= this.b - this.b;
        }
        return this;
    }

    public DataSimulatorSimulateFlightCommend C(boolean z) {
        if (z) {
            this.t |= this.c;
        } else {
            this.t |= this.c - this.c;
        }
        return this;
    }

    public DataSimulatorSimulateFlightCommend D(boolean z) {
        if (z) {
            this.t |= this.d;
        } else {
            this.t |= this.d - this.d;
        }
        return this;
    }

    public DataSimulatorSimulateFlightCommend E(boolean z) {
        if (z) {
            this.t |= this.e;
        } else {
            this.t |= this.e - this.e;
        }
        return this;
    }

    public DataSimulatorSimulateFlightCommend F(boolean z) {
        if (z) {
            this.t |= this.f;
        } else {
            this.t |= this.f - this.f;
        }
        return this;
    }

    private double d(double d) {
        return (3.141592653589793d * d) / 180.0d;
    }

    private double e(double d) {
        return (180.0d * d) / 3.141592653589793d;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.b.a();
        cVar.l = b.a.a();
        cVar.m = p.l.a();
        cVar.n = l.a.SimulateFlightCommend.a();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[36];
        this._sendData[0] = (byte) this.k;
        this._sendData[1] = (byte) this.j;
        this._sendData[2] = (byte) this.p;
        this._sendData[3] = (byte) this.o;
        System.arraycopy(dji.midware.util.c.a(this.m), 0, this._sendData, 4, 8);
        System.arraycopy(dji.midware.util.c.a(this.l), 0, this._sendData, 12, 8);
        System.arraycopy(dji.midware.util.c.a(this.n), 0, this._sendData, 20, 8);
        this._sendData[28] = (byte) this.q;
        this._sendData[29] = (byte) this.r;
        this._sendData[30] = (byte) this.s;
        this._sendData[31] = (byte) this.t;
        System.arraycopy(dji.midware.util.c.a(0), 0, this._sendData, 32, 4);
    }
}
