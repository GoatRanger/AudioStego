package com.google.a.g;

import com.google.a.a;
import com.google.a.c.b;
import com.google.a.g;
import com.google.a.h;
import com.google.a.w;
import java.util.Map;

public final class i extends y {
    private static final int a = 95;

    public b a(String str, a aVar, int i, int i2, Map<g, ?> map) throws w {
        if (aVar == a.EAN_13) {
            return super.a(str, aVar, i, i2, map);
        }
        throw new IllegalArgumentException("Can only encode EAN_13, but got " + aVar);
    }

    public boolean[] a(String str) {
        if (str.length() != 13) {
            throw new IllegalArgumentException("Requested contents should be 13 digits long, but got " + str.length());
        }
        try {
            if (x.a((CharSequence) str)) {
                int i;
                int parseInt;
                int i2 = h.a[Integer.parseInt(str.substring(0, 1))];
                boolean[] zArr = new boolean[95];
                int a = r.a(zArr, 0, x.b, true) + 0;
                for (i = 1; i <= 6; i++) {
                    parseInt = Integer.parseInt(str.substring(i, i + 1));
                    if (((i2 >> (6 - i)) & 1) == 1) {
                        parseInt += 10;
                    }
                    a += r.a(zArr, a, x.g[parseInt], false);
                }
                i = a + r.a(zArr, a, x.e, false);
                for (parseInt = 7; parseInt <= 12; parseInt++) {
                    i += r.a(zArr, i, x.f[Integer.parseInt(str.substring(parseInt, parseInt + 1))], true);
                }
                r.a(zArr, i, x.b, true);
                return zArr;
            }
            throw new IllegalArgumentException("Contents do not pass checksum");
        } catch (h e) {
            throw new IllegalArgumentException("Illegal contents");
        }
    }
}
