package com.google.a.g.a.a.a;

import com.google.a.c.a;
import com.google.a.h;
import com.google.a.m;

final class d extends h {
    private static final int b = 8;
    private static final int c = 2;
    private static final int d = 10;

    d(a aVar) {
        super(aVar);
    }

    public String a() throws m, h {
        if (b().a() < 48) {
            throw m.a();
        }
        StringBuilder stringBuilder = new StringBuilder();
        b(stringBuilder, 8);
        int a = c().a(48, 2);
        stringBuilder.append("(393");
        stringBuilder.append(a);
        stringBuilder.append(')');
        a = c().a(50, 10);
        if (a / 100 == 0) {
            stringBuilder.append('0');
        }
        if (a / 10 == 0) {
            stringBuilder.append('0');
        }
        stringBuilder.append(a);
        stringBuilder.append(c().a(60, null).a());
        return stringBuilder.toString();
    }
}
