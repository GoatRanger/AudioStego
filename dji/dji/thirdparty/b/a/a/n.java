package dji.thirdparty.b.a.a;

import java.util.Arrays;

public final class n {
    static final int a = 65536;
    static final int b = 1;
    static final int c = 1;
    static final int d = 2;
    static final int e = 1;
    static final int f = 1;
    static final int g = 2;
    static final int h = 2;
    static final int i = 3;
    static final int j = 4;
    static final int k = 5;
    static final int l = 5;
    static final int m = 6;
    static final int n = 6;
    static final int o = 7;
    static final int p = 8;
    static final int q = 10;
    static final int r = 10;
    static final int s = 1;
    private int t;
    private int u;
    private int v;
    private final int[] w = new int[10];

    void a() {
        this.v = 0;
        this.u = 0;
        this.t = 0;
        Arrays.fill(this.w, 0);
    }

    n a(int i, int i2, int i3) {
        if (i < this.w.length) {
            int i4 = 1 << i;
            this.t |= i4;
            if ((i2 & 1) != 0) {
                this.u |= i4;
            } else {
                this.u &= i4 ^ -1;
            }
            if ((i2 & 2) != 0) {
                this.v = i4 | this.v;
            } else {
                this.v = (i4 ^ -1) & this.v;
            }
            this.w[i] = i3;
        }
        return this;
    }

    boolean a(int i) {
        if (((1 << i) & this.t) != 0) {
            return true;
        }
        return false;
    }

    int b(int i) {
        return this.w[i];
    }

    int c(int i) {
        int i2 = 0;
        if (o(i)) {
            i2 = 2;
        }
        if (n(i)) {
            return i2 | 1;
        }
        return i2;
    }

    int b() {
        return Integer.bitCount(this.t);
    }

    int d(int i) {
        return (2 & this.t) != 0 ? this.w[1] : i;
    }

    int c() {
        return (2 & this.t) != 0 ? this.w[1] : -1;
    }

    int e(int i) {
        return (4 & this.t) != 0 ? this.w[2] : i;
    }

    boolean a(boolean z) {
        int i;
        if ((4 & this.t) != 0) {
            i = this.w[2];
        } else if (z) {
            boolean z2 = true;
        } else {
            i = 0;
        }
        if (i == 1) {
            return true;
        }
        return false;
    }

    int f(int i) {
        return (8 & this.t) != 0 ? this.w[3] : i;
    }

    int g(int i) {
        return (16 & this.t) != 0 ? this.w[4] : i;
    }

    int h(int i) {
        return (32 & this.t) != 0 ? this.w[5] : i;
    }

    int i(int i) {
        return (32 & this.t) != 0 ? this.w[5] : i;
    }

    int j(int i) {
        return (64 & this.t) != 0 ? this.w[6] : i;
    }

    int k(int i) {
        return (64 & this.t) != 0 ? this.w[6] : i;
    }

    int l(int i) {
        return (128 & this.t) != 0 ? this.w[7] : i;
    }

    int m(int i) {
        return (256 & this.t) != 0 ? this.w[8] : i;
    }

    boolean d() {
        int i;
        if ((1024 & this.t) != 0) {
            i = this.w[10];
        } else {
            i = 0;
        }
        if ((i & 1) != 0) {
            return true;
        }
        return false;
    }

    boolean n(int i) {
        if (((1 << i) & this.u) != 0) {
            return true;
        }
        return false;
    }

    boolean o(int i) {
        if (((1 << i) & this.v) != 0) {
            return true;
        }
        return false;
    }

    void a(n nVar) {
        for (int i = 0; i < 10; i++) {
            if (nVar.a(i)) {
                a(i, nVar.c(i), nVar.b(i));
            }
        }
    }
}
