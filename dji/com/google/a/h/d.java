package com.google.a.h;

import com.google.a.a;
import com.google.a.c.b;
import com.google.a.g;
import com.google.a.h.c.c;
import com.google.a.h.c.e;
import com.google.a.v;
import com.google.a.w;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Map;

public final class d implements v {
    static final int a = 30;

    public b a(String str, a aVar, int i, int i2, Map<g, ?> map) throws w {
        if (aVar != a.PDF_417) {
            throw new IllegalArgumentException("Can only encode PDF_417, but got " + aVar);
        }
        e eVar = new e();
        int i3 = 30;
        if (map != null) {
            if (map.containsKey(g.PDF417_COMPACT)) {
                eVar.a(((Boolean) map.get(g.PDF417_COMPACT)).booleanValue());
            }
            if (map.containsKey(g.PDF417_COMPACTION)) {
                eVar.a((c) map.get(g.PDF417_COMPACTION));
            }
            if (map.containsKey(g.PDF417_DIMENSIONS)) {
                com.google.a.h.c.d dVar = (com.google.a.h.c.d) map.get(g.PDF417_DIMENSIONS);
                eVar.a(dVar.b(), dVar.a(), dVar.d(), dVar.c());
            }
            if (map.containsKey(g.MARGIN)) {
                i3 = ((Number) map.get(g.MARGIN)).intValue();
            }
            if (map.containsKey(g.b)) {
                eVar.a(Charset.forName((String) map.get(g.b)));
            }
        }
        return a(eVar, str, i, i2, i3);
    }

    public b a(String str, a aVar, int i, int i2) throws w {
        return a(str, aVar, i, i2, null);
    }

    private static b a(e eVar, String str, int i, int i2, int i3) throws w {
        int i4;
        eVar.a(str, 2);
        byte[][] a = eVar.a().a(2, 8);
        int i5 = i2 > i ? 1 : 0;
        if (a[0].length < a.length) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        if ((i5 ^ i4) != 0) {
            a = a(a);
            i5 = 1;
        } else {
            i5 = 0;
        }
        int length = i / a[0].length;
        i4 = i2 / a.length;
        if (length >= i4) {
            length = i4;
        }
        if (length <= 1) {
            return a(a, i3);
        }
        byte[][] a2;
        byte[][] a3 = eVar.a().a(length * 2, (length * 4) * 2);
        if (i5 != 0) {
            a2 = a(a3);
        } else {
            a2 = a3;
        }
        return a(a2, i3);
    }

    private static b a(byte[][] bArr, int i) {
        b bVar = new b(bArr[0].length + (i * 2), bArr.length + (i * 2));
        bVar.a();
        int g = (bVar.g() - i) - 1;
        int i2 = 0;
        while (i2 < bArr.length) {
            for (int i3 = 0; i3 < bArr[0].length; i3++) {
                if (bArr[i2][i3] == (byte) 1) {
                    bVar.b(i3 + i, g);
                }
            }
            i2++;
            g--;
        }
        return bVar;
    }

    private static byte[][] a(byte[][] bArr) {
        byte[][] bArr2 = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{bArr[0].length, bArr.length});
        for (int i = 0; i < bArr.length; i++) {
            int length = (bArr.length - i) - 1;
            for (int i2 = 0; i2 < bArr[0].length; i2++) {
                bArr2[i2][length] = bArr[i][i2];
            }
        }
        return bArr2;
    }
}
