package com.google.a.g;

import com.google.a.a;
import com.google.a.c.b;
import com.google.a.g;
import com.google.a.v;
import com.google.a.w;
import java.util.Map;

public final class t implements v {
    private final i a = new i();

    public b a(String str, a aVar, int i, int i2) throws w {
        return a(str, aVar, i, i2, null);
    }

    public b a(String str, a aVar, int i, int i2, Map<g, ?> map) throws w {
        if (aVar == a.UPC_A) {
            return this.a.a(a(str), a.EAN_13, i, i2, map);
        }
        throw new IllegalArgumentException("Can only encode UPC-A, but got " + aVar);
    }

    private static String a(String str) {
        int length = str.length();
        if (length == 11) {
            int i = 0;
            for (length = 0; length < 11; length++) {
                i += (length % 2 == 0 ? 3 : 1) * (str.charAt(length) - 48);
            }
            str = str + ((1000 - i) % 10);
        } else if (length != 12) {
            throw new IllegalArgumentException("Requested contents should be 11 or 12 digits long, but got " + str.length());
        }
        return '0' + str;
    }
}
