package com.google.a.g.a.a.a;

import com.google.a.h;

final class p extends q {
    static final int a = 10;
    private final int b;
    private final int c;

    p(int i, int i2, int i3) throws h {
        super(i);
        if (i2 < 0 || i2 > 10 || i3 < 0 || i3 > 10) {
            throw h.a();
        }
        this.b = i2;
        this.c = i3;
    }

    int a() {
        return this.b;
    }

    int b() {
        return this.c;
    }

    int c() {
        return (this.b * 10) + this.c;
    }

    boolean d() {
        return this.b == 10;
    }

    boolean e() {
        return this.c == 10;
    }

    boolean f() {
        return this.b == 10 || this.c == 10;
    }
}
