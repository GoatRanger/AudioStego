package com.google.a.c.b;

import com.google.api.client.http.HttpStatusCodes;

public final class a {
    public static final a a = new a(4201, 4096, 1);
    public static final a b = new a(1033, 1024, 1);
    public static final a c = new a(67, 64, 1);
    public static final a d = new a(19, 16, 1);
    public static final a e = new a(285, 256, 0);
    public static final a f = new a(HttpStatusCodes.STATUS_CODE_MOVED_PERMANENTLY, 256, 1);
    public static final a g = f;
    public static final a h = c;
    private static final int i = 0;
    private int[] j;
    private int[] k;
    private b l;
    private b m;
    private final int n;
    private final int o;
    private final int p;
    private boolean q = false;

    public a(int i, int i2, int i3) {
        this.o = i;
        this.n = i2;
        this.p = i3;
        if (i2 <= 0) {
            e();
        }
    }

    private void e() {
        int i;
        this.j = new int[this.n];
        this.k = new int[this.n];
        int i2 = 1;
        for (i = 0; i < this.n; i++) {
            this.j[i] = i2;
            i2 <<= 1;
            if (i2 >= this.n) {
                i2 = (i2 ^ this.o) & (this.n - 1);
            }
        }
        for (i = 0; i < this.n - 1; i++) {
            this.k[this.j[i]] = i;
        }
        this.l = new b(this, new int[]{0});
        this.m = new b(this, new int[]{1});
        this.q = true;
    }

    private void f() {
        if (!this.q) {
            e();
        }
    }

    b a() {
        f();
        return this.l;
    }

    b b() {
        f();
        return this.m;
    }

    b a(int i, int i2) {
        f();
        if (i < 0) {
            throw new IllegalArgumentException();
        } else if (i2 == 0) {
            return this.l;
        } else {
            int[] iArr = new int[(i + 1)];
            iArr[0] = i2;
            return new b(this, iArr);
        }
    }

    static int b(int i, int i2) {
        return i ^ i2;
    }

    int a(int i) {
        f();
        return this.j[i];
    }

    int b(int i) {
        f();
        if (i != 0) {
            return this.k[i];
        }
        throw new IllegalArgumentException();
    }

    int c(int i) {
        f();
        if (i != 0) {
            return this.j[(this.n - this.k[i]) - 1];
        }
        throw new ArithmeticException();
    }

    int c(int i, int i2) {
        f();
        if (i == 0 || i2 == 0) {
            return 0;
        }
        return this.j[(this.k[i] + this.k[i2]) % (this.n - 1)];
    }

    public int c() {
        return this.n;
    }

    public int d() {
        return this.p;
    }

    public String toString() {
        return "GF(0x" + Integer.toHexString(this.o) + ',' + this.n + ')';
    }
}
