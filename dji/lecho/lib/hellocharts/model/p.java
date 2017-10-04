package lecho.lib.hellocharts.model;

import dji.pilot.usercenter.protocol.d;
import java.util.Arrays;
import lecho.lib.hellocharts.h.b;

public class p {
    private float a;
    private float b;
    private float c;
    private int d = b.a;
    private int e = b.b;
    private char[] f;

    public p() {
        b(0.0f);
    }

    public p(float f) {
        b(f);
    }

    public p(float f, int i) {
        b(f);
        a(i);
    }

    public p(p pVar) {
        b(pVar.a);
        a(pVar.d);
        this.f = pVar.f;
    }

    public void a(float f) {
        this.a = this.b + (this.c * f);
    }

    public void a() {
        b(this.b + this.c);
    }

    public float b() {
        return this.a;
    }

    public p b(float f) {
        this.a = f;
        this.b = f;
        this.c = 0.0f;
        return this;
    }

    public p c(float f) {
        b(this.a);
        this.c = f - this.b;
        return this;
    }

    public int c() {
        return this.d;
    }

    public p a(int i) {
        this.d = i;
        this.e = b.a(i);
        return this;
    }

    public int d() {
        return this.e;
    }

    @Deprecated
    public char[] e() {
        return this.f;
    }

    public p a(String str) {
        this.f = str.toCharArray();
        return this;
    }

    public char[] f() {
        return this.f;
    }

    @Deprecated
    public p a(char[] cArr) {
        this.f = cArr;
        return this;
    }

    public String toString() {
        return "ColumnValue [value=" + this.a + d.H;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        p pVar = (p) obj;
        if (this.d != pVar.d) {
            return false;
        }
        if (this.e != pVar.e) {
            return false;
        }
        if (Float.compare(pVar.c, this.c) != 0) {
            return false;
        }
        if (Float.compare(pVar.b, this.b) != 0) {
            return false;
        }
        if (Float.compare(pVar.a, this.a) != 0) {
            return false;
        }
        if (Arrays.equals(this.f, pVar.f)) {
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
        floatToIntBits = (((((floatToIntBits + floatToIntBits2) * 31) + this.d) * 31) + this.e) * 31;
        if (this.f != null) {
            i = Arrays.hashCode(this.f);
        }
        return floatToIntBits + i;
    }
}
