package com.google.a.d.a;

import com.google.a.c.b;
import com.google.a.c.b.a;
import com.google.a.c.b.c;
import com.google.a.c.e;
import com.google.a.h;

public final class d {
    private final c a = new c(a.f);

    public e a(boolean[][] zArr) throws h, com.google.a.d {
        int length = zArr.length;
        b bVar = new b(length);
        for (int i = 0; i < length; i++) {
            for (int i2 = 0; i2 < length; i2++) {
                if (zArr[i][i2]) {
                    bVar.b(i2, i);
                }
            }
        }
        return a(bVar);
    }

    public e a(b bVar) throws h, com.google.a.d {
        int i;
        a aVar = new a(bVar);
        b[] a = b.a(aVar.b(), aVar.a());
        int length = a.length;
        int i2 = 0;
        for (b a2 : a) {
            i2 += a2.a();
        }
        byte[] bArr = new byte[i2];
        for (i = 0; i < length; i++) {
            b bVar2 = a[i];
            byte[] b = bVar2.b();
            int a3 = bVar2.a();
            a(b, a3);
            for (i2 = 0; i2 < a3; i2++) {
                bArr[(i2 * length) + i] = b[i2];
            }
        }
        return c.a(bArr);
    }

    private void a(byte[] bArr, int i) throws com.google.a.d {
        int i2 = 0;
        int length = bArr.length;
        int[] iArr = new int[length];
        for (int i3 = 0; i3 < length; i3++) {
            iArr[i3] = bArr[i3] & 255;
        }
        try {
            this.a.a(iArr, bArr.length - i);
            while (i2 < i) {
                bArr[i2] = (byte) iArr[i2];
                i2++;
            }
        } catch (com.google.a.c.b.e e) {
            throw com.google.a.d.a();
        }
    }
}
