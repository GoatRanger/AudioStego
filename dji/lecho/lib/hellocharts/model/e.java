package lecho.lib.hellocharts.model;

import dji.pilot.usercenter.protocol.d;
import java.util.Arrays;
import lecho.lib.hellocharts.h.b;

public class e {
    private float a;
    private float b;
    private float c;
    private float d;
    private float e;
    private float f;
    private float g;
    private float h;
    private float i;
    private int j = b.a;
    private int k = b.b;
    private q l = q.a;
    private char[] m;

    public e() {
        a(0.0f, 0.0f, 0.0f);
    }

    public e(float f, float f2, float f3) {
        a(f, f2, f3);
    }

    public e(float f, float f2, float f3, int i) {
        a(f, f2, f3);
        a(i);
    }

    public e(e eVar) {
        a(eVar.a, eVar.b, eVar.c);
        a(eVar.j);
        this.m = eVar.m;
    }

    public void a(float f) {
        this.a = this.d + (this.g * f);
        this.b = this.e + (this.h * f);
        this.c = this.f + (this.i * f);
    }

    public void a() {
        a(this.d + this.g, this.e + this.h, this.f + this.i);
    }

    public e a(float f, float f2, float f3) {
        this.a = f;
        this.b = f2;
        this.c = f3;
        this.d = f;
        this.e = f2;
        this.f = f3;
        this.g = 0.0f;
        this.h = 0.0f;
        this.i = 0.0f;
        return this;
    }

    public e b(float f, float f2, float f3) {
        a(this.a, this.b, this.c);
        this.g = f - this.d;
        this.h = f2 - this.e;
        this.i = f3 - this.f;
        return this;
    }

    public float b() {
        return this.a;
    }

    public float c() {
        return this.b;
    }

    public float d() {
        return this.c;
    }

    public int e() {
        return this.j;
    }

    public e a(int i) {
        this.j = i;
        this.k = b.a(i);
        return this;
    }

    public int f() {
        return this.k;
    }

    public q g() {
        return this.l;
    }

    public e a(q qVar) {
        this.l = qVar;
        return this;
    }

    @Deprecated
    public char[] h() {
        return this.m;
    }

    public e a(String str) {
        this.m = str.toCharArray();
        return this;
    }

    public char[] i() {
        return this.m;
    }

    @Deprecated
    public e a(char[] cArr) {
        this.m = cArr;
        return this;
    }

    public String toString() {
        return "BubbleValue [x=" + this.a + ", y=" + this.b + ", z=" + this.c + d.H;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        e eVar = (e) obj;
        if (this.j != eVar.j) {
            return false;
        }
        if (this.k != eVar.k) {
            return false;
        }
        if (Float.compare(eVar.g, this.g) != 0) {
            return false;
        }
        if (Float.compare(eVar.h, this.h) != 0) {
            return false;
        }
        if (Float.compare(eVar.i, this.i) != 0) {
            return false;
        }
        if (Float.compare(eVar.d, this.d) != 0) {
            return false;
        }
        if (Float.compare(eVar.e, this.e) != 0) {
            return false;
        }
        if (Float.compare(eVar.f, this.f) != 0) {
            return false;
        }
        if (Float.compare(eVar.a, this.a) != 0) {
            return false;
        }
        if (Float.compare(eVar.b, this.b) != 0) {
            return false;
        }
        if (Float.compare(eVar.c, this.c) != 0) {
            return false;
        }
        if (!Arrays.equals(this.m, eVar.m)) {
            return false;
        }
        if (this.l != eVar.l) {
            return false;
        }
        return true;
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
        floatToIntBits2 = (floatToIntBits + floatToIntBits2) * 31;
        if (this.g != 0.0f) {
            floatToIntBits = Float.floatToIntBits(this.g);
        } else {
            floatToIntBits = 0;
        }
        floatToIntBits2 = (floatToIntBits + floatToIntBits2) * 31;
        if (this.h != 0.0f) {
            floatToIntBits = Float.floatToIntBits(this.h);
        } else {
            floatToIntBits = 0;
        }
        floatToIntBits2 = (floatToIntBits + floatToIntBits2) * 31;
        if (this.i != 0.0f) {
            floatToIntBits = Float.floatToIntBits(this.i);
        } else {
            floatToIntBits = 0;
        }
        floatToIntBits2 = (((((floatToIntBits + floatToIntBits2) * 31) + this.j) * 31) + this.k) * 31;
        if (this.l != null) {
            floatToIntBits = this.l.hashCode();
        } else {
            floatToIntBits = 0;
        }
        floatToIntBits = (floatToIntBits + floatToIntBits2) * 31;
        if (this.m != null) {
            i = Arrays.hashCode(this.m);
        }
        return floatToIntBits + i;
    }
}
