package com.google.a.i.a;

import com.google.a.c.b;
import com.google.a.h;

final class a {
    private final b a;
    private j b;
    private g c;
    private boolean d;

    a(b bVar) throws h {
        int g = bVar.g();
        if (g < 21 || (g & 3) != 1) {
            throw h.a();
        }
        this.a = bVar;
    }

    g a() throws h {
        int i = 0;
        if (this.c != null) {
            return this.c;
        }
        int i2;
        int i3 = 0;
        for (i2 = 0; i2 < 6; i2++) {
            i3 = a(i2, 8, i3);
        }
        i3 = a(8, 7, a(8, 8, a(7, 8, i3)));
        for (i2 = 5; i2 >= 0; i2--) {
            i3 = a(8, i2, i3);
        }
        int g = this.a.g();
        int i4 = g - 7;
        for (i2 = g - 1; i2 >= i4; i2--) {
            i = a(8, i2, i);
        }
        for (i2 = g - 8; i2 < g; i2++) {
            i = a(i2, 8, i);
        }
        this.c = g.b(i3, i);
        if (this.c != null) {
            return this.c;
        }
        throw h.a();
    }

    j b() throws h {
        if (this.b != null) {
            return this.b;
        }
        int g = this.a.g();
        int i = (g - 17) >> 2;
        if (i <= 6) {
            return j.b(i);
        }
        int i2 = g - 11;
        int i3 = 0;
        for (int i4 = 5; i4 >= 0; i4--) {
            for (i = g - 9; i >= i2; i--) {
                i3 = a(i, i4, i3);
            }
        }
        j c = j.c(i3);
        if (c == null || c.d() != g) {
            int i5 = 0;
            for (int i6 = 5; i6 >= 0; i6--) {
                for (i = g - 9; i >= i2; i--) {
                    i5 = a(i6, i, i5);
                }
            }
            c = j.c(i5);
            if (c == null || c.d() != g) {
                throw h.a();
            }
            this.b = c;
            return c;
        }
        this.b = c;
        return c;
    }

    private int a(int i, int i2, int i3) {
        return this.d ? this.a.a(i2, i) : this.a.a(i, i2) ? (i3 << 1) | 1 : i3 << 1;
    }

    byte[] c() throws h {
        g a = a();
        j b = b();
        c a2 = c.a(a.b());
        int g = this.a.g();
        a2.a(this.a, g);
        b e = b.e();
        byte[] bArr = new byte[b.c()];
        int i = g - 1;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 1;
        while (i > 0) {
            if (i == 6) {
                i--;
            }
            for (int i6 = 0; i6 < g; i6++) {
                int i7;
                if (i5 != 0) {
                    i7 = (g - 1) - i6;
                } else {
                    i7 = i6;
                }
                for (int i8 = 0; i8 < 2; i8++) {
                    if (!e.a(i - i8, i7)) {
                        i2++;
                        i3 <<= 1;
                        if (this.a.a(i - i8, i7)) {
                            i3 |= 1;
                        }
                        if (i2 == 8) {
                            i2 = i4 + 1;
                            bArr[i4] = (byte) i3;
                            i3 = 0;
                            i4 = i2;
                            i2 = 0;
                        }
                    }
                }
            }
            i -= 2;
            i5 ^= 1;
        }
        if (i4 == b.c()) {
            return bArr;
        }
        throw h.a();
    }

    void d() {
        if (this.c != null) {
            c.a(this.c.b()).a(this.a, this.a.g());
        }
    }

    void a(boolean z) {
        this.b = null;
        this.c = null;
        this.d = z;
    }

    void e() {
        for (int i = 0; i < this.a.f(); i++) {
            for (int i2 = i + 1; i2 < this.a.g(); i2++) {
                if (this.a.a(i, i2) != this.a.a(i2, i)) {
                    this.a.c(i2, i);
                    this.a.c(i, i2);
                }
            }
        }
    }
}
