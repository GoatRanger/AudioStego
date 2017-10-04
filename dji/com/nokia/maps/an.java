package com.nokia.maps;

import android.graphics.PointF;

public class an {
    public PointF[] a = new PointF[200];
    private float b;
    private float c;
    private float d;
    private float e;
    private float f;
    private float g;
    private PointF h;

    public an(PointF pointF, PointF pointF2, PointF pointF3, PointF pointF4) {
        this.d = (pointF2.x - pointF.x) * 3.0f;
        this.c = ((pointF3.x - pointF2.x) * 3.0f) - this.d;
        this.b = ((pointF4.x - pointF.x) - this.d) - this.c;
        this.g = (pointF2.y - pointF.y) * 3.0f;
        this.f = ((pointF3.y - pointF2.y) * 3.0f) - this.g;
        this.e = ((pointF4.y - pointF.y) - this.g) - this.f;
        this.h = pointF;
        float f = 0.0f;
        for (int i = 0; i < 200; i++) {
            this.a[i] = a(f);
            f += 0.005f;
        }
    }

    public PointF a(float f) {
        PointF pointF = new PointF();
        float f2 = f * f;
        float f3 = f2 * f;
        pointF.x = (((this.b * f3) + (this.c * f2)) + (this.d * f)) + this.h.x;
        pointF.y = (((f2 * this.f) + (f3 * this.e)) + (this.g * f)) + this.h.y;
        return pointF;
    }
}
