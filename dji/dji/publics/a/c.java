package dji.publics.a;

import android.graphics.Camera;
import android.graphics.Matrix;

public class c {
    private Camera a = new Camera();
    private Matrix b = new Matrix();
    private float c;
    private float d;
    private float e;
    private float f;
    private float g;
    private float h;
    private boolean i = false;
    private boolean j = false;
    private boolean k = false;
    private boolean l = false;
    private float m;
    private float n;
    private float o;
    private float p;

    public void a(float f, float f2, float f3) {
        this.c = f;
        this.d = f2;
        this.e = f3;
        this.j = true;
    }

    public void b(float f, float f2, float f3) {
        this.f = f;
        this.g = f2;
        this.h = f3;
        this.i = true;
    }

    public void a(float f, float f2) {
        this.m = f;
        this.n = f2;
        this.k = true;
    }

    public void b(float f, float f2) {
        this.o = f;
        this.p = f2;
        this.l = true;
    }

    public Matrix a() {
        this.a.save();
        if (this.j) {
            c();
        }
        if (this.i) {
            d();
        }
        this.a.getMatrix(this.b);
        this.a.restore();
        if (this.l) {
            this.b.preTranslate(-this.o, -this.p);
            this.b.postTranslate(this.o, this.p);
        }
        if (this.k) {
            this.b.preScale(this.m, this.n);
        }
        return this.b;
    }

    public void b() {
        this.c = 0.0f;
        this.d = 0.0f;
        this.e = 0.0f;
        this.j = false;
        this.f = 0.0f;
        this.g = 0.0f;
        this.h = 0.0f;
        this.i = false;
        this.o = 0.0f;
        this.p = 0.0f;
        this.l = false;
        this.m = 0.0f;
        this.n = 0.0f;
        this.k = false;
    }

    private void c() {
        this.a.translate(this.c, this.d, this.e);
    }

    private void d() {
        this.a.rotateX(this.f);
        this.a.rotateY(this.g);
        this.a.rotateY(this.h);
    }
}
