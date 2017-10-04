package lecho.lib.hellocharts.b;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import lecho.lib.hellocharts.e.i;
import lecho.lib.hellocharts.e.m;
import lecho.lib.hellocharts.model.Viewport;

public class a {
    protected static final float a = 20.0f;
    protected float b = a;
    protected int c;
    protected int d;
    protected Rect e = new Rect();
    protected Rect f = new Rect();
    protected Rect g = new Rect();
    protected Viewport h = new Viewport();
    protected Viewport i = new Viewport();
    protected float j;
    protected float k;
    protected m l = new i();

    public void a(int i, int i2, int i3, int i4, int i5, int i6) {
        this.c = i;
        this.d = i2;
        this.g.set(i3, i4, i - i5, i2 - i6);
        this.f.set(this.g);
        this.e.set(this.g);
    }

    public void a() {
        this.f.set(this.g);
        this.e.set(this.g);
    }

    public void a(int i, int i2, int i3, int i4) {
        this.f.left += i;
        this.f.top += i2;
        this.f.right -= i3;
        this.f.bottom -= i4;
        b(i, i2, i3, i4);
    }

    public void b(int i, int i2, int i3, int i4) {
        this.e.left += i;
        this.e.top += i2;
        this.e.right -= i3;
        this.e.bottom -= i4;
    }

    public void a(float f, float f2, float f3, float f4) {
        if (f3 - f < this.j) {
            f3 = f + this.j;
            if (f < this.i.a) {
                f = this.i.a;
                f3 = f + this.j;
            } else if (f3 > this.i.c) {
                f3 = this.i.c;
                f = f3 - this.j;
            }
        }
        if (f2 - f4 < this.k) {
            f4 = f2 - this.k;
            if (f2 > this.i.b) {
                f2 = this.i.b;
                f4 = f2 - this.k;
            } else if (f4 < this.i.d) {
                f4 = this.i.d;
                f2 = f4 + this.k;
            }
        }
        this.h.a = Math.max(this.i.a, f);
        this.h.b = Math.min(this.i.b, f2);
        this.h.c = Math.min(this.i.c, f3);
        this.h.d = Math.max(this.i.d, f4);
        this.l.a(this.h);
    }

    public void a(float f, float f2) {
        float c = this.h.c();
        float d = this.h.d();
        float max = Math.max(this.i.a, Math.min(f, this.i.c - c));
        float max2 = Math.max(this.i.d + d, Math.min(f2, this.i.b));
        a(max, max2, c + max, max2 - d);
    }

    public float a(float f) {
        return ((f - this.h.a) * (((float) this.e.width()) / this.h.c())) + ((float) this.e.left);
    }

    public float b(float f) {
        return ((float) this.e.bottom) - ((f - this.h.d) * (((float) this.e.height()) / this.h.d()));
    }

    public float c(float f) {
        return (((float) this.e.width()) / this.h.c()) * f;
    }

    public float d(float f) {
        return (((float) this.e.height()) / this.h.d()) * f;
    }

    public boolean a(float f, float f2, PointF pointF) {
        if (!this.e.contains((int) f, (int) f2)) {
            return false;
        }
        pointF.set(this.h.a + (((f - ((float) this.e.left)) * this.h.c()) / ((float) this.e.width())), this.h.d + (((f2 - ((float) this.e.bottom)) * this.h.d()) / ((float) (-this.e.height()))));
        return true;
    }

    public void a(Point point) {
        point.set((int) ((this.i.c() * ((float) this.e.width())) / this.h.c()), (int) ((this.i.d() * ((float) this.e.height())) / this.h.d()));
    }

    public boolean a(float f, float f2, float f3) {
        if (f < ((float) this.e.left) - f3 || f > ((float) this.e.right) + f3 || f2 > ((float) this.e.bottom) + f3 || f2 < ((float) this.e.top) - f3) {
            return false;
        }
        return true;
    }

    public Rect b() {
        return this.e;
    }

    public Rect c() {
        return this.f;
    }

    public Viewport d() {
        return this.h;
    }

    public void a(Viewport viewport) {
        a(viewport.a, viewport.b, viewport.c, viewport.d);
    }

    public void b(float f, float f2, float f3, float f4) {
        a(f, f2, f3, f4);
    }

    public Viewport e() {
        return this.i;
    }

    public void b(Viewport viewport) {
        c(viewport.a, viewport.b, viewport.c, viewport.d);
    }

    public void c(float f, float f2, float f3, float f4) {
        this.i.a(f, f2, f3, f4);
        l();
    }

    public Viewport f() {
        return this.h;
    }

    public void c(Viewport viewport) {
        a(viewport);
    }

    public float g() {
        return this.j;
    }

    public float h() {
        return this.k;
    }

    public void a(m mVar) {
        if (mVar == null) {
            this.l = new i();
        } else {
            this.l = mVar;
        }
    }

    public int i() {
        return this.c;
    }

    public int j() {
        return this.d;
    }

    public float k() {
        return this.b;
    }

    public void e(float f) {
        if (f < 1.0f) {
            f = 1.0f;
        }
        this.b = f;
        l();
        a(this.h);
    }

    private void l() {
        this.j = this.i.c() / this.b;
        this.k = this.i.d() / this.b;
    }
}
