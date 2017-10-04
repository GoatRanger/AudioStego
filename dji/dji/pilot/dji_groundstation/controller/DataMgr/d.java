package dji.pilot.dji_groundstation.controller.DataMgr;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCommonGetVersion;

public class d extends a {
    private static final String b = "POIDataMgr";
    private static d c = null;
    private double d = 0.0d;
    private double e = 0.0d;
    private float f = 0.0f;
    private float g = 1.0f;
    private double h = 0.0d;
    private DataCommonGetVersion i = new DataCommonGetVersion().setDeviceType(DeviceType.FLYC);
    private boolean j = false;
    private int k = 0;
    private boolean l = false;

    public static synchronized d getInstance() {
        d dVar;
        synchronized (d.class) {
            if (c == null) {
                c = new d();
            }
            dVar = c;
        }
        return dVar;
    }

    public double i() {
        return this.d;
    }

    public void a(double d) {
        this.d = d;
    }

    public double j() {
        return this.e;
    }

    public void b(double d) {
        this.e = d;
    }

    public float k() {
        return this.f;
    }

    public void a(float f) {
        this.f = f;
        this.g = (float) (((((double) f) * this.h) * 3.141592653589793d) / 180.0d);
    }

    public float l() {
        return this.g;
    }

    public void b(float f) {
        this.g = f;
        this.f = (float) ((((double) (180.0f * f)) / 3.141592653589793d) / this.h);
    }

    public double m() {
        return this.h;
    }

    public boolean c(double d) {
        this.h = d;
        this.f = (float) ((((double) (this.g * 180.0f)) / 3.141592653589793d) / d);
        if (d >= 5.0d && d <= 500.0d) {
            return true;
        }
        return false;
    }

    public boolean n() {
        if (this.h >= 5.0d && this.h <= 500.0d) {
            return true;
        }
        return false;
    }

    public boolean o() {
        return this.l;
    }

    public void a(boolean z) {
        this.l = z;
    }

    private d() {
    }

    public void p() {
        this.i.start(new dji.midware.e.d(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.j = true;
            }

            public void onFailure(a aVar) {
                this.a.k = this.a.k + 1;
                if (this.a.k < 2) {
                    this.a.i.start(this);
                } else {
                    this.a.j = false;
                }
            }
        });
    }

    public double q() {
        double d = 3.5d;
        if (!this.j) {
            return 0.0d;
        }
        if (3 <= this.i.getFirmByte(1)) {
            double d2 = (((this.h - 5.0d) / 25.0d) * 1.5d) + 2.0d;
            if (d2 <= 3.5d) {
                d = d2;
            }
            d = Math.sqrt(d * this.h);
        } else {
            d = (10.0d * 10.0d) / this.h > 3.47d ? Math.sqrt(this.h * 3.47d) : 10.0d;
        }
        if (d > 10.0d) {
            return 10.0d;
        }
        return d;
    }

    public void e() {
        super.e();
        c = null;
    }
}
