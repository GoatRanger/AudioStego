package com.amap.api.mapcore.util;

import com.autonavi.amap.mapcore.DPoint;

public class cy {
    public final double a;
    public final double b;
    public final double c;
    public final double d;
    public final double e;
    public final double f;

    public cy(double d, double d2, double d3, double d4) {
        this.a = d;
        this.b = d3;
        this.c = d2;
        this.d = d4;
        this.e = (d + d2) / 2.0d;
        this.f = (d3 + d4) / 2.0d;
    }

    public boolean a(double d, double d2) {
        return this.a <= d && d <= this.c && this.b <= d2 && d2 <= this.d;
    }

    public boolean a(DPoint dPoint) {
        return a(dPoint.x, dPoint.y);
    }

    public boolean a(double d, double d2, double d3, double d4) {
        return d < this.c && this.a < d2 && d3 < this.d && this.b < d4;
    }

    public boolean a(cy cyVar) {
        return a(cyVar.a, cyVar.c, cyVar.b, cyVar.d);
    }

    public boolean b(cy cyVar) {
        return cyVar.a >= this.a && cyVar.c <= this.c && cyVar.b >= this.b && cyVar.d <= this.d;
    }
}
