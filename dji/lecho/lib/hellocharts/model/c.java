package lecho.lib.hellocharts.model;

import java.util.Arrays;

public class c {
    private float a;
    private char[] b;

    public c(float f) {
        a(f);
    }

    @Deprecated
    public c(float f, char[] cArr) {
        this.a = f;
        this.b = cArr;
    }

    public c(c cVar) {
        this.a = cVar.a;
        this.b = cVar.b;
    }

    public float a() {
        return this.a;
    }

    public c a(float f) {
        this.a = f;
        return this;
    }

    @Deprecated
    public char[] b() {
        return this.b;
    }

    public c a(String str) {
        this.b = str.toCharArray();
        return this;
    }

    public char[] c() {
        return this.b;
    }

    @Deprecated
    public c a(char[] cArr) {
        this.b = cArr;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        c cVar = (c) obj;
        if (Float.compare(cVar.a, this.a) != 0) {
            return false;
        }
        if (Arrays.equals(this.b, cVar.b)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int floatToIntBits;
        int i = 0;
        if (this.a != 0.0f) {
            floatToIntBits = Float.floatToIntBits(this.a);
        } else {
            floatToIntBits = 0;
        }
        floatToIntBits *= 31;
        if (this.b != null) {
            i = Arrays.hashCode(this.b);
        }
        return floatToIntBits + i;
    }
}
