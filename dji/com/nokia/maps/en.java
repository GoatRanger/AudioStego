package com.nokia.maps;

import android.graphics.PointF;

public class en extends PointF {
    public int a;

    public en(float f, float f2, int i) {
        this.x = f;
        this.y = f2;
        this.a = i;
    }

    public void a(float f, float f2, int i) {
        this.x = f;
        this.y = f2;
        this.a = i;
    }

    public boolean a() {
        return (this.x == -1.0f || this.y == -1.0f) ? false : true;
    }

    public int b() {
        return this.a;
    }

    public double a(en enVar) {
        return Math.atan2((double) (this.y - enVar.y), (double) (enVar.x - this.x));
    }

    public double b(en enVar) {
        return Math.toDegrees(a(enVar));
    }

    public double c(en enVar) {
        return Math.sqrt((double) (((enVar.x - this.x) * (enVar.x - this.x)) + ((enVar.y - this.y) * (enVar.y - this.y))));
    }

    public float d(en enVar) {
        return enVar.y - this.y;
    }

    public float e(en enVar) {
        return enVar.x - this.x;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof en)) {
            return false;
        }
        en enVar = (en) obj;
        if (this.x == enVar.x && this.y == enVar.y) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((Float.floatToIntBits(this.x) + 259) * 37) + Float.floatToIntBits(this.y);
    }

    public static double a(en enVar, en enVar2, en enVar3, en enVar4) {
        double b = enVar3.b(enVar4) - enVar.b(enVar2);
        while (b > 180.0d) {
            b -= 360.0d;
        }
        while (b <= -180.0d) {
            b += 360.0d;
        }
        return (b * (3.141592653589793d * enVar3.c(enVar4))) / 360.0d;
    }

    public String toString() {
        return "TouchPoint x: " + this.x + " y:" + this.y;
    }
}
