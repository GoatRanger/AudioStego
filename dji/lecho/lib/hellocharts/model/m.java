package lecho.lib.hellocharts.model;

import dji.pilot.usercenter.protocol.d;
import java.util.Arrays;

public class m {
    private float a;
    private float b;
    private float c;
    private float d;
    private float e;
    private float f;
    private char[] g;

    public m() {
        a(0.0f, 0.0f);
    }

    public m(float f, float f2) {
        a(f, f2);
    }

    public m(m mVar) {
        a(mVar.a, mVar.b);
        this.g = mVar.g;
    }

    public void a(float f) {
        this.a = this.c + (this.e * f);
        this.b = this.d + (this.f * f);
    }

    public void a() {
        a(this.c + this.e, this.d + this.f);
    }

    public m a(float f, float f2) {
        this.a = f;
        this.b = f2;
        this.c = f;
        this.d = f2;
        this.e = 0.0f;
        this.f = 0.0f;
        return this;
    }

    public m b(float f, float f2) {
        a(this.a, this.b);
        this.e = f - this.c;
        this.f = f2 - this.d;
        return this;
    }

    public float b() {
        return this.a;
    }

    public float c() {
        return this.b;
    }

    @Deprecated
    public char[] d() {
        return this.g;
    }

    public m a(String str) {
        this.g = str.toCharArray();
        return this;
    }

    public char[] e() {
        return this.g;
    }

    @Deprecated
    public m a(char[] cArr) {
        this.g = cArr;
        return this;
    }

    public String toString() {
        return "PointValue [x=" + this.a + ", y=" + this.b + d.H;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        m mVar = (m) obj;
        if (Float.compare(mVar.e, this.e) != 0) {
            return false;
        }
        if (Float.compare(mVar.f, this.f) != 0) {
            return false;
        }
        if (Float.compare(mVar.c, this.c) != 0) {
            return false;
        }
        if (Float.compare(mVar.d, this.d) != 0) {
            return false;
        }
        if (Float.compare(mVar.a, this.a) != 0) {
            return false;
        }
        if (Float.compare(mVar.b, this.b) != 0) {
            return false;
        }
        if (Arrays.equals(this.g, mVar.g)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int floatToIntBits;
        int i = 0;
        int floatToIntBits2 = (this.a != 0.0f ? Float.floatToIntBits(this.a) : 0) * 31;
        if (this.b != 0.0f) {
            floatToIntBits = Float.floatToIntBits(this.b);
        } else {
            floatToIntBits = 0;
        }
        floatToIntBits2 = (floatToIntBits + floatToIntBits2) * 31;
        if (this.c != 0.0f) {
            floatToIntBits = Float.floatToIntBits(this.c);
        } else {
            floatToIntBits = 0;
        }
        floatToIntBits2 = (floatToIntBits + floatToIntBits2) * 31;
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
        floatToIntBits2 = (floatToIntBits + floatToIntBits2) * 31;
        if (this.f != 0.0f) {
            floatToIntBits = Float.floatToIntBits(this.f);
        } else {
            floatToIntBits = 0;
        }
        floatToIntBits = (floatToIntBits + floatToIntBits2) * 31;
        if (this.g != null) {
            i = Arrays.hashCode(this.g);
        }
        return floatToIntBits + i;
    }
}
