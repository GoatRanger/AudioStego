package dji.publics.c;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class a extends Animation {
    private float a;
    private float b;
    private float c;
    private float d;
    private float e;
    private float f;
    private float g;
    private float h;
    private float i;
    private float j;
    private float k;
    private float l;
    private float m;
    private float n;
    private Camera o;
    private Matrix p;

    public a(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14) {
        this.g = f;
        this.h = f2;
        this.a = f3;
        this.b = f4;
        this.c = f5;
        this.d = f6;
        this.e = f7;
        this.f = f8;
        this.i = f9;
        this.j = f10;
        this.k = f11;
        this.l = f12;
        this.m = f13;
        this.n = f14;
    }

    public a(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        this.g = f;
        this.h = f2;
        this.a = f3;
        this.b = f4;
        this.c = f5;
        this.d = f6;
        this.e = f7;
        this.f = f8;
    }

    public a(float f, float f2, float f3, float f4) {
        this.g = f;
        this.h = f2;
        this.a = f3;
        this.b = f4;
    }

    public void initialize(int i, int i2, int i3, int i4) {
        super.initialize(i, i2, i3, i4);
        this.o = new Camera();
    }

    protected void applyTransformation(float f, Transformation transformation) {
        float f2 = this.a + ((this.b - this.a) * f);
        float f3 = this.c + ((this.d - this.c) * f);
        float f4 = this.e + ((this.f - this.e) * f);
        float f5 = this.i + ((this.j - this.i) * f);
        float f6 = this.k + ((this.l - this.k) * f);
        float f7 = this.m + ((this.n - this.m) * f);
        this.p = transformation.getMatrix();
        this.o.save();
        this.o.translate(f5, f6, f7);
        this.o.rotateX(f2);
        this.o.rotateY(f3);
        this.o.rotateZ(f4);
        this.o.getMatrix(this.p);
        this.o.restore();
        this.p.preTranslate(-this.g, -this.h);
        this.p.postTranslate(this.g, this.h);
    }
}
