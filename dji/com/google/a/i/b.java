package com.google.a.i;

import com.google.a.a;
import com.google.a.g;
import com.google.a.i.a.f;
import com.google.a.i.c.c;
import com.google.a.v;
import com.google.a.w;
import java.util.Map;

public final class b implements v {
    private static final int a = 4;

    public com.google.a.c.b a(String str, a aVar, int i, int i2) throws w {
        return a(str, aVar, i, i2, null);
    }

    public com.google.a.c.b a(String str, a aVar, int i, int i2, Map<g, ?> map) throws w {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        } else if (aVar != a.QR_CODE) {
            throw new IllegalArgumentException("Can only encode QR_CODE, but got " + aVar);
        } else if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("Requested dimensions are too small: " + i + 'x' + i2);
        } else {
            int intValue;
            f fVar = f.L;
            if (map != null) {
                f fVar2 = (f) map.get(g.ERROR_CORRECTION);
                if (fVar2 != null) {
                    fVar = fVar2;
                }
                Integer num = (Integer) map.get(g.MARGIN);
                if (num != null) {
                    intValue = num.intValue();
                    return a(c.a(str, fVar, (Map) map), i, i2, intValue);
                }
            }
            intValue = 4;
            return a(c.a(str, fVar, (Map) map), i, i2, intValue);
        }
    }

    private static com.google.a.c.b a(com.google.a.i.c.f fVar, int i, int i2, int i3) {
        com.google.a.i.c.b e = fVar.e();
        if (e == null) {
            throw new IllegalStateException();
        }
        int b = e.b();
        int a = e.a();
        int i4 = (i3 << 1) + b;
        int i5 = (i3 << 1) + a;
        int max = Math.max(i, i4);
        int max2 = Math.max(i2, i5);
        int min = Math.min(max / i4, max2 / i5);
        i5 = (max - (b * min)) / 2;
        i4 = (max2 - (a * min)) / 2;
        com.google.a.c.b bVar = new com.google.a.c.b(max, max2);
        max2 = i4;
        for (int i6 = 0; i6 < a; i6++) {
            max = 0;
            i4 = i5;
            while (max < b) {
                if (e.a(max, i6) == (byte) 1) {
                    bVar.a(i4, max2, min, min);
                }
                max++;
                i4 += min;
            }
            max2 += min;
        }
        return bVar;
    }
}
