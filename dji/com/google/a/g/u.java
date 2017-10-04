package com.google.a.g;

import com.google.a.c.a;
import com.google.a.m;
import com.google.a.r;
import com.google.a.s;
import com.google.a.t;
import java.util.EnumMap;
import java.util.Map;

final class u {
    private final int[] a = new int[4];
    private final StringBuilder b = new StringBuilder();

    u() {
    }

    r a(int i, a aVar, int[] iArr) throws m {
        StringBuilder stringBuilder = this.b;
        stringBuilder.setLength(0);
        int a = a(aVar, iArr, stringBuilder);
        String stringBuilder2 = stringBuilder.toString();
        Map a2 = a(stringBuilder2);
        r rVar = new r(stringBuilder2, null, new t[]{new t(((float) (iArr[0] + iArr[1])) / 2.0f, (float) i), new t((float) a, (float) i)}, com.google.a.a.UPC_EAN_EXTENSION);
        if (a2 != null) {
            rVar.a(a2);
        }
        return rVar;
    }

    int a(a aVar, int[] iArr, StringBuilder stringBuilder) throws m {
        int[] iArr2 = this.a;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int a = aVar.a();
        int i = iArr[1];
        int i2 = 0;
        for (int i3 = 0; i3 < 2 && i < a; i3++) {
            int a2 = x.a(aVar, iArr2, i, x.g);
            stringBuilder.append((char) ((a2 % 10) + 48));
            int i4 = 0;
            while (i4 < iArr2.length) {
                int i5 = iArr2[i4] + i;
                i4++;
                i = i5;
            }
            if (a2 >= 10) {
                i2 |= 1 << (1 - i3);
            }
            if (i3 != 1) {
                i = aVar.e(aVar.d(i));
            }
        }
        if (stringBuilder.length() != 2) {
            throw m.a();
        } else if (Integer.parseInt(stringBuilder.toString()) % 4 == i2) {
            return i;
        } else {
            throw m.a();
        }
    }

    private static Map<s, Object> a(String str) {
        if (str.length() != 2) {
            return null;
        }
        Map<s, Object> enumMap = new EnumMap(s.class);
        enumMap.put(s.ISSUE_NUMBER, Integer.valueOf(str));
        return enumMap;
    }
}
