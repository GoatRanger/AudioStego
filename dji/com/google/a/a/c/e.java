package com.google.a.a.c;

import com.google.a.c.a;

final class e extends g {
    private final short b;
    private final short c;

    e(g gVar, int i, int i2) {
        super(gVar);
        this.b = (short) i;
        this.c = (short) i2;
    }

    void a(a aVar, byte[] bArr) {
        aVar.c(this.b, this.c);
    }

    public String toString() {
        return '<' + Integer.toBinaryString(((this.b & ((1 << this.c) - 1)) | (1 << this.c)) | (1 << this.c)).substring(1) + '>';
    }
}
