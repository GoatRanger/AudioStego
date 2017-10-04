package com.google.a.c;

import com.google.a.b;
import com.google.a.j;
import com.google.a.m;

public class h extends b {
    private static final int a = 5;
    private static final int b = 3;
    private static final int c = 32;
    private static final byte[] d = new byte[0];
    private byte[] e = d;
    private final int[] f = new int[32];

    public h(j jVar) {
        super(jVar);
    }

    public a a(int i, a aVar) throws m {
        int i2;
        int i3;
        int i4 = 1;
        j a = a();
        int g = a.g();
        if (aVar == null || aVar.a() < g) {
            aVar = new a(g);
        } else {
            aVar.c();
        }
        a(g);
        byte[] a2 = a.a(i, this.e);
        int[] iArr = this.f;
        for (i2 = 0; i2 < g; i2++) {
            i3 = (a2[i2] & 255) >> 3;
            iArr[i3] = iArr[i3] + 1;
        }
        i3 = a(iArr);
        i2 = a2[1] & 255;
        int i5 = a2[0] & 255;
        while (i4 < g - 1) {
            int i6 = a2[i4 + 1] & 255;
            if (((((i2 << 2) - i5) - i6) >> 1) < i3) {
                aVar.b(i4);
            }
            i4++;
            i5 = i2;
            i2 = i6;
        }
        return aVar;
    }

    public b b() throws m {
        int i;
        int i2;
        j a = a();
        int g = a.g();
        int h = a.h();
        b bVar = new b(g, h);
        a(g);
        int[] iArr = this.f;
        for (i = 1; i < 5; i++) {
            byte[] a2 = a.a((h * i) / 5, this.e);
            int i3 = (g << 2) / 5;
            for (i2 = g / 5; i2 < i3; i2++) {
                int i4 = (a2[i2] & 255) >> 3;
                iArr[i4] = iArr[i4] + 1;
            }
        }
        int a3 = a(iArr);
        byte[] a4 = a.a();
        for (i = 0; i < h; i++) {
            int i5 = i * g;
            for (i2 = 0; i2 < g; i2++) {
                if ((a4[i5 + i2] & 255) < a3) {
                    bVar.b(i2, i);
                }
            }
        }
        return bVar;
    }

    public b a(j jVar) {
        return new h(jVar);
    }

    private void a(int i) {
        if (this.e.length < i) {
            this.e = new byte[i];
        }
        for (int i2 = 0; i2 < 32; i2++) {
            this.f[i2] = 0;
        }
    }

    private static int a(int[] iArr) throws m {
        int i;
        int i2;
        int i3 = 0;
        int length = iArr.length;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        for (i = 0; i < length; i++) {
            if (iArr[i] > i4) {
                i4 = iArr[i];
                i5 = i;
            }
            if (iArr[i] > i6) {
                i6 = iArr[i];
            }
        }
        i = 0;
        int i7 = 0;
        while (i3 < length) {
            i4 = i3 - i5;
            i4 *= iArr[i3] * i4;
            if (i4 > i) {
                i = i3;
            } else {
                i4 = i;
                i = i7;
            }
            i3++;
            i7 = i;
            i = i4;
        }
        if (i5 > i7) {
            i2 = i7;
            i7 = i5;
        } else {
            i2 = i5;
        }
        if (i7 - i2 <= (length >> 4)) {
            throw m.a();
        }
        i3 = i7 - 1;
        i5 = -1;
        i = i7 - 1;
        while (i > i2) {
            i4 = i - i2;
            i4 = ((i4 * i4) * (i7 - i)) * (i6 - iArr[i]);
            if (i4 > i5) {
                i5 = i;
            } else {
                i4 = i5;
                i5 = i3;
            }
            i--;
            i3 = i5;
            i5 = i4;
        }
        return i3 << 3;
    }
}
