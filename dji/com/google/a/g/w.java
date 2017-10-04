package com.google.a.g;

import com.google.a.c.a;
import com.google.a.m;
import com.google.a.q;
import com.google.a.r;

final class w {
    private static final int[] a = new int[]{1, 1, 2};
    private final u b = new u();
    private final v c = new v();

    w() {
    }

    r a(int i, a aVar, int i2) throws m {
        int[] a = x.a(aVar, i2, false, a);
        try {
            return this.c.a(i, aVar, a);
        } catch (q e) {
            return this.b.a(i, aVar, a);
        }
    }
}
