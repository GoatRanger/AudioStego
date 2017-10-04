package com.google.a.c;

import com.google.a.m;
import dji.pilot.visual.a.d;

public final class f extends i {
    public b a(b bVar, int i, int i2, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16) throws m {
        return a(bVar, i, i2, k.a(f, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16));
    }

    public b a(b bVar, int i, int i2, k kVar) throws m {
        if (i <= 0 || i2 <= 0) {
            throw m.a();
        }
        b bVar2 = new b(i, i2);
        float[] fArr = new float[(i << 1)];
        for (int i3 = 0; i3 < i2; i3++) {
            int i4;
            int length = fArr.length;
            float f = ((float) i3) + d.c;
            for (i4 = 0; i4 < length; i4 += 2) {
                fArr[i4] = ((float) (i4 >> 1)) + d.c;
                fArr[i4 + 1] = f;
            }
            kVar.a(fArr);
            i.a(bVar, fArr);
            i4 = 0;
            while (i4 < length) {
                try {
                    if (bVar.a((int) fArr[i4], (int) fArr[i4 + 1])) {
                        bVar2.b(i4 >> 1, i3);
                    }
                    i4 += 2;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw m.a();
                }
            }
        }
        return bVar2;
    }
}
