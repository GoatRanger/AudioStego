package com.google.a.a.c;

import com.google.a.c.a;

final class b extends g {
    private final short b;
    private final short c;

    b(g gVar, int i, int i2) {
        super(gVar);
        this.b = (short) i;
        this.c = (short) i2;
    }

    public void a(a aVar, byte[] bArr) {
        short s = (short) 0;
        while (s < this.c) {
            if (s == (short) 0 || (s == (short) 31 && this.c <= (short) 62)) {
                aVar.c(31, 5);
                if (this.c > (short) 62) {
                    aVar.c(this.c - 31, 16);
                } else if (s == (short) 0) {
                    aVar.c(Math.min(this.c, 31), 5);
                } else {
                    aVar.c(this.c - 31, 5);
                }
            }
            aVar.c(bArr[this.b + s], 8);
            s++;
        }
    }

    public String toString() {
        return "<" + this.b + "::" + ((this.b + this.c) - 1) + '>';
    }
}
