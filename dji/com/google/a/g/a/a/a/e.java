package com.google.a.g.a.a.a;

import com.google.a.c.a;
import com.google.a.m;

final class e extends i {
    private static final int b = 8;
    private static final int c = 20;
    private static final int d = 16;
    private final String e;
    private final String f;

    e(a aVar, String str, String str2) {
        super(aVar);
        this.e = str2;
        this.f = str;
    }

    public String a() throws m {
        if (b().a() != 84) {
            throw m.a();
        }
        StringBuilder stringBuilder = new StringBuilder();
        b(stringBuilder, 8);
        b(stringBuilder, 48, 20);
        c(stringBuilder, 68);
        return stringBuilder.toString();
    }

    private void c(StringBuilder stringBuilder, int i) {
        int a = c().a(i, 16);
        if (a != 38400) {
            stringBuilder.append('(');
            stringBuilder.append(this.e);
            stringBuilder.append(')');
            int i2 = a % 32;
            a /= 32;
            int i3 = (a % 12) + 1;
            a /= 12;
            if (a / 10 == 0) {
                stringBuilder.append('0');
            }
            stringBuilder.append(a);
            if (i3 / 10 == 0) {
                stringBuilder.append('0');
            }
            stringBuilder.append(i3);
            if (i2 / 10 == 0) {
                stringBuilder.append('0');
            }
            stringBuilder.append(i2);
        }
    }

    protected void a(StringBuilder stringBuilder, int i) {
        int i2 = i / 100000;
        stringBuilder.append('(');
        stringBuilder.append(this.f);
        stringBuilder.append(i2);
        stringBuilder.append(')');
    }

    protected int a(int i) {
        return i % 100000;
    }
}
