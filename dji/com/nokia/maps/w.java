package com.nokia.maps;

import android.graphics.PointF;
import android.util.Pair;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import java.util.ArrayList;
import java.util.List;
import lecho.lib.hellocharts.d.c;

public abstract class w {
    private List<Pair<Float, Float>> a = new ArrayList();
    private List<Float> b = new ArrayList();
    private long c;
    private List<Float> d = new ArrayList();
    protected float e;
    protected float f;
    protected float g = 0.0f;
    protected en h;
    protected long i;
    protected boolean j = false;
    protected float k;
    protected short l;
    protected float m = 1.0f;
    protected short n;
    protected long o = 200;
    protected an p;
    private long q;
    private boolean r = false;
    private boolean s = false;
    private boolean t = false;
    private int u = 0;
    private long v = 0;

    protected abstract void a(en enVar, en enVar2);

    protected synchronized void a_() {
        if (this.a.size() != 0) {
            float f = 0.0f;
            float f2 = 0.0f;
            for (Pair pair : this.a) {
                f = ((Float) pair.second).floatValue() + f;
                f2 = ((Float) pair.first).floatValue() + f2;
            }
            int size = this.a.size();
            this.e = f2 / ((float) size);
            this.f = f / ((float) size);
            this.a.clear();
            this.g = (float) Math.sqrt((double) ((this.e * this.e) + (this.f * this.f)));
            if (this.g < 0.01f) {
                e();
            } else {
                if (this.g > 2.5f) {
                    float f3 = 2.5f / this.g;
                    this.e *= f3;
                    this.f = f3 * this.f;
                    this.g = 2.5f;
                }
                this.j = true;
            }
        }
    }

    public synchronized PointF a(long j, boolean z) {
        PointF pointF;
        Object obj = 1;
        synchronized (this) {
            pointF = new PointF();
            if (this.g >= 0.01f) {
                if (this.h != null && this.j) {
                    long j2 = j - this.i;
                    float f = this.h.x + (((float) j2) * this.e);
                    float f2 = this.h.y + (((float) j2) * this.f);
                    en enVar = new en(f, f2, this.h.b());
                    float f3 = this.g - (((float) j2) * 0.0023f);
                    if (!(Float.isNaN(f) || Float.isNaN(f2))) {
                        obj = null;
                    }
                    if (f3 < 0.01f || r1 != null) {
                        e();
                    } else {
                        float f4 = f3 / this.g;
                        this.e *= f4;
                        this.f = f4 * this.f;
                        this.g = f3;
                        this.i = j;
                        if (z) {
                            a(this.h, enVar);
                        }
                        pointF.x = enVar.x - this.h.x;
                        pointF.y = enVar.y - this.h.y;
                        this.h = enVar;
                        this.s = true;
                    }
                }
            }
        }
        return pointF;
    }

    protected synchronized void e() {
        this.a.clear();
        this.g = 0.0f;
        this.e = 0.0f;
        this.f = 0.0f;
        this.j = false;
    }

    protected synchronized void a(float f, float f2) {
        this.a.add(new Pair(Float.valueOf(f), Float.valueOf(f2)));
        if (this.a.size() > 5) {
            this.a.remove(0);
        }
    }

    protected synchronized boolean f() {
        boolean z;
        if (this.b.size() == 0) {
            z = false;
        } else if (System.currentTimeMillis() - this.c > 100) {
            g();
            z = false;
        } else {
            float f = 0.0f;
            for (Float floatValue : this.b) {
                f = floatValue.floatValue() + f;
            }
            int size = this.b.size();
            this.l = (short) (f > 0.0f ? 1 : -1);
            this.k = Math.abs(f / ((float) size));
            this.b.clear();
            if (this.k < 0.005f) {
                g();
                z = false;
            } else {
                if (this.k > 20.0f) {
                    this.k = 20.0f;
                }
                z = true;
            }
        }
        return z;
    }

    protected synchronized float a(long j) {
        float f = 0.0f;
        synchronized (this) {
            if (this.k >= 0.005f) {
                float f2 = this.k - ((((float) (j - this.c)) / DJIFlightControllerDataType.DJIVirtualStickRollPitchControlMaxVelocity) * c.a);
                if (f2 < 0.005f) {
                    g();
                    h();
                } else {
                    this.k = f2;
                    this.c = j;
                    this.s = true;
                    f = this.k * ((float) this.l);
                }
            }
        }
        return f;
    }

    protected synchronized void g() {
        this.b.clear();
        this.k = 0.0f;
        this.c = 0;
    }

    protected synchronized void a(float f) {
        if (this.c == 0) {
            this.c = System.currentTimeMillis();
        } else {
            int size = this.d.size();
            if (size > 0) {
                double floatValue = (double) ((Float) this.d.get(size - 1)).floatValue();
                if ((floatValue > 0.0d && f < 0.0f) || (floatValue < 0.0d && f > 0.0f)) {
                    this.d.clear();
                    size = 0;
                }
            }
            long currentTimeMillis = (System.currentTimeMillis() - this.c) / 15;
            if (currentTimeMillis == 0) {
                currentTimeMillis = 1;
            }
            this.b.add(Float.valueOf(f / ((float) currentTimeMillis)));
            if (size > 20) {
                this.b.remove(0);
            }
            this.c = System.currentTimeMillis();
        }
    }

    protected void h() {
    }

    protected synchronized boolean i() {
        boolean z;
        if (this.d.size() == 0) {
            z = false;
        } else if (System.currentTimeMillis() - this.q > 100) {
            j();
            z = false;
        } else {
            float f = 0.0f;
            for (Float floatValue : this.d) {
                f = floatValue.floatValue() + f;
            }
            f /= (float) this.d.size();
            this.n = (short) (f > 1.0f ? 1 : -1);
            this.m = ((f / 50.0f) * ((float) this.n)) + 1.0f;
            this.d.clear();
            if (this.m <= 0.985f || this.m >= 1.015f) {
                this.r = true;
                z = true;
            } else {
                j();
                z = false;
            }
        }
        return z;
    }

    protected synchronized float b(long j) {
        float f = 1.0f;
        synchronized (this) {
            if (this.r) {
                this.m -= ((((float) (j - this.q)) / DJIFlightControllerDataType.DJIVirtualStickRollPitchControlMaxVelocity) * 0.002f) * ((float) this.n);
                if (this.m < 1.0f && this.n == (short) 1) {
                    j();
                    k();
                } else if (this.m <= 1.0f || this.n != (short) -1) {
                    this.q = j;
                    this.s = true;
                    f = this.m;
                } else {
                    j();
                    k();
                }
            }
        }
        return f;
    }

    protected synchronized void j() {
        this.d.clear();
        this.m = 1.0f;
        this.q = 0;
        this.r = false;
    }

    protected synchronized void a(double d) {
        if (this.q == 0) {
            this.q = System.currentTimeMillis();
        } else {
            int size = this.d.size();
            if (size > 0) {
                double floatValue = (double) ((Float) this.d.get(size - 1)).floatValue();
                if ((floatValue > 1.0d && d < 1.0d) || (floatValue < 1.0d && d > 1.0d)) {
                    this.d.clear();
                    size = 0;
                }
            }
            long currentTimeMillis = (System.currentTimeMillis() - this.q) / 15;
            if (currentTimeMillis == 0) {
                currentTimeMillis = 1;
            }
            this.d.add(Float.valueOf(((float) d) / ((float) currentTimeMillis)));
            if (size > 20) {
                this.d.remove(0);
            }
            this.q = System.currentTimeMillis();
        }
    }

    protected void k() {
    }

    public synchronized void l() {
        long currentTimeMillis = System.currentTimeMillis();
        a(currentTimeMillis, true);
        this.i = currentTimeMillis;
    }

    public synchronized void m() {
        long currentTimeMillis = System.currentTimeMillis();
        this.s = false;
        PointF a = a(currentTimeMillis, false);
        float a2 = a(currentTimeMillis);
        float b = b(currentTimeMillis);
        if (this.s) {
            a(a.x, a.y, b, a2);
        }
        this.i = currentTimeMillis;
        c(currentTimeMillis);
    }

    protected void a(float f, float f2, float f3, float f4) {
    }

    public void n() {
        g();
        j();
        e();
        a();
    }

    private void a() {
        this.u = 0;
        this.t = false;
        this.v = 0;
    }

    protected boolean c(long j) {
        float f = 1.0f;
        if (!this.t) {
            return this.t;
        }
        long j2 = j - this.v;
        if (j2 > this.o) {
            a();
            b(1.0f);
            o();
            return this.t;
        }
        float f2 = ((float) j2) / ((float) this.o);
        while (this.u < 200 && f2 > this.p.a[this.u].x) {
            this.u++;
        }
        if (this.u != 200) {
            f = this.p.a[this.u].y;
        }
        b(f);
        return this.t;
    }

    protected void b(float f) {
    }

    protected void o() {
    }

    protected void p() {
        this.v = System.currentTimeMillis();
        this.u = 0;
        this.t = true;
    }

    protected synchronized float q() {
        return this.g;
    }

    protected synchronized float r() {
        return this.e;
    }
}
