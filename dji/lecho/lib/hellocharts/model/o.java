package lecho.lib.hellocharts.model;

import dji.pilot.usercenter.protocol.d;
import java.util.Arrays;
import lecho.lib.hellocharts.h.b;

public class o {
    private static final int a = 2;
    @Deprecated
    private int b = 2;
    private float c;
    private float d;
    private float e;
    private int f = b.a;
    private int g = b.b;
    private char[] h;

    public o() {
        b(0.0f);
    }

    public o(float f) {
        b(f);
    }

    public o(float f, int i) {
        b(f);
        a(i);
    }

    public o(float f, int i, int i2) {
        b(f);
        a(i);
        this.b = i2;
    }

    public o(o oVar) {
        b(oVar.c);
        a(oVar.f);
        this.b = oVar.b;
        this.h = oVar.h;
    }

    public void a(float f) {
        this.c = this.d + (this.e * f);
    }

    public void a() {
        b(this.d + this.e);
    }

    public float b() {
        return this.c;
    }

    public o b(float f) {
        this.c = f;
        this.d = f;
        this.e = 0.0f;
        return this;
    }

    public o c(float f) {
        b(this.c);
        this.e = f - this.d;
        return this;
    }

    public int c() {
        return this.f;
    }

    public o a(int i) {
        this.f = i;
        this.g = b.a(i);
        return this;
    }

    public int d() {
        return this.g;
    }

    @Deprecated
    public int e() {
        return this.b;
    }

    @Deprecated
    public o b(int i) {
        this.b = i;
        return this;
    }

    @Deprecated
    public char[] f() {
        return this.h;
    }

    @Deprecated
    public o a(char[] cArr) {
        this.h = cArr;
        return this;
    }

    public o a(String str) {
        this.h = str.toCharArray();
        return this;
    }

    public char[] g() {
        return this.h;
    }

    public String toString() {
        return "SliceValue [value=" + this.c + d.H;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        o oVar = (o) obj;
        if (this.f != oVar.f) {
            return false;
        }
        if (this.g != oVar.g) {
            return false;
        }
        if (Float.compare(oVar.e, this.e) != 0) {
            return false;
        }
        if (Float.compare(oVar.d, this.d) != 0) {
            return false;
        }
        if (this.b != oVar.b) {
            return false;
        }
        if (Float.compare(oVar.c, this.c) != 0) {
            return false;
        }
        if (Arrays.equals(this.h, oVar.h)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int floatToIntBits;
        int i = 0;
        int floatToIntBits2 = (this.c != 0.0f ? Float.floatToIntBits(this.c) : 0) * 31;
        if (this.d != 0.0f) {
            floatToIntBits = Float.floatToIntBits(this.d);
        } else {
            floatToIntBits = 0;
        }
        floatToIntBits2 = (floatToIntBits + floatToIntBits2) * 31;
        if (this.e != 0.0f) {
            floatToIntBits = Float.floatToIntBits(this.e);
        } else {
            floatToIntBits = 0;
        }
        floatToIntBits = (((((((floatToIntBits + floatToIntBits2) * 31) + this.f) * 31) + this.g) * 31) + this.b) * 31;
        if (this.h != null) {
            i = Arrays.hashCode(this.h);
        }
        return floatToIntBits + i;
    }
}
