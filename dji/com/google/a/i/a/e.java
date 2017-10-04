package com.google.a.i.a;

import com.google.a.c.b;
import com.google.a.c.b.a;
import com.google.a.c.b.c;
import com.google.a.d;
import com.google.a.h;
import com.google.a.q;
import java.util.Map;

public final class e {
    private final c a = new c(a.e);

    public com.google.a.c.e a(boolean[][] zArr) throws d, h {
        return a(zArr, null);
    }

    public com.google.a.c.e a(boolean[][] zArr, Map<com.google.a.e, ?> map) throws d, h {
        int length = zArr.length;
        b bVar = new b(length);
        for (int i = 0; i < length; i++) {
            for (int i2 = 0; i2 < length; i2++) {
                if (zArr[i][i2]) {
                    bVar.b(i2, i);
                }
            }
        }
        return a(bVar, (Map) map);
    }

    public com.google.a.c.e a(b bVar) throws d, h {
        return a(bVar, null);
    }

    public com.google.a.c.e a(b bVar, Map<com.google.a.e, ?> map) throws h, d {
        com.google.a.c.e a;
        d dVar;
        h e;
        q qVar = null;
        a aVar = new a(bVar);
        try {
            a = a(aVar, (Map) map);
        } catch (h e2) {
            h hVar = e2;
            dVar = qVar;
            try {
                aVar.d();
                aVar.a(true);
                aVar.b();
                aVar.a();
                aVar.e();
                a = a(aVar, (Map) map);
                a.a(new i(true));
                return a;
            } catch (h e3) {
                e = e3;
                if (hVar != null) {
                    throw hVar;
                } else if (dVar == null) {
                    throw dVar;
                } else {
                    throw e;
                }
            } catch (d e4) {
                e = e4;
                if (hVar != null) {
                    throw hVar;
                } else if (dVar == null) {
                    throw e;
                } else {
                    throw dVar;
                }
            }
        } catch (d e5) {
            dVar = e5;
            q qVar2 = qVar;
            aVar.d();
            aVar.a(true);
            aVar.b();
            aVar.a();
            aVar.e();
            a = a(aVar, (Map) map);
            a.a(new i(true));
            return a;
        }
        return a;
    }

    private com.google.a.c.e a(a aVar, Map<com.google.a.e, ?> map) throws h, d {
        j b = aVar.b();
        f a = aVar.a().a();
        b[] a2 = b.a(aVar.c(), b, a);
        int i = 0;
        for (b a3 : a2) {
            i += a3.a();
        }
        byte[] bArr = new byte[i];
        int length = a2.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            b bVar = a2[i2];
            byte[] b2 = bVar.b();
            int a4 = bVar.a();
            a(b2, a4);
            i = i3;
            i3 = 0;
            while (i3 < a4) {
                int i4 = i + 1;
                bArr[i] = b2[i3];
                i3++;
                i = i4;
            }
            i2++;
            i3 = i;
        }
        return d.a(bArr, b, a, (Map) map);
    }

    private void a(byte[] bArr, int i) throws d {
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
            throw d.a();
        }
    }
}
