package com.google.a.d.c;

final class f implements g {
    f() {
    }

    public int a() {
        return 4;
    }

    public void a(h hVar) {
        CharSequence stringBuilder = new StringBuilder();
        while (hVar.h()) {
            a(hVar.b(), (StringBuilder) stringBuilder);
            hVar.a++;
            if (stringBuilder.length() >= 4) {
                hVar.a(a(stringBuilder, 0));
                stringBuilder.delete(0, 4);
                if (j.a(hVar.a(), hVar.a, a()) != a()) {
                    hVar.b(0);
                    break;
                }
            }
        }
        stringBuilder.append('\u001f');
        a(hVar, stringBuilder);
    }

    private static void a(h hVar, CharSequence charSequence) {
        int i = 1;
        try {
            int length = charSequence.length();
            if (length != 0) {
                int i2;
                if (length == 1) {
                    hVar.k();
                    i2 = hVar.j().i() - hVar.e();
                    if (hVar.i() == 0 && i2 <= 2) {
                        hVar.b(0);
                        return;
                    }
                }
                if (length > 4) {
                    throw new IllegalStateException("Count must not exceed 4");
                }
                i2 = length - 1;
                String a = a(charSequence, 0);
                if ((!hVar.h() ? 1 : 0) == 0 || i2 > 2) {
                    i = 0;
                }
                if (i2 <= 2) {
                    hVar.c(hVar.e() + i2);
                    if (hVar.j().i() - hVar.e() >= 3) {
                        hVar.c(hVar.e() + a.length());
                        i = 0;
                    }
                }
                if (i != 0) {
                    hVar.l();
                    hVar.a -= i2;
                } else {
                    hVar.a(a);
                }
                hVar.b(0);
            }
        } finally {
            hVar.b(0);
        }
    }

    private static void a(char c, StringBuilder stringBuilder) {
        if (c >= ' ' && c <= '?') {
            stringBuilder.append(c);
        } else if (c < '@' || c > '^') {
            j.c(c);
        } else {
            stringBuilder.append((char) (c - 64));
        }
    }

    private static String a(CharSequence charSequence, int i) {
        int i2 = 0;
        int length = charSequence.length() - i;
        if (length == 0) {
            throw new IllegalStateException("StringBuilder must not be empty");
        }
        int charAt;
        char charAt2 = charSequence.charAt(i);
        int charAt3 = length >= 2 ? charSequence.charAt(i + 1) : 0;
        if (length >= 3) {
            charAt = charSequence.charAt(i + 2);
        } else {
            charAt = 0;
        }
        if (length >= 4) {
            i2 = charSequence.charAt(i + 3);
        }
        i2 += (charAt << 6) + ((charAt3 << 12) + (charAt2 << 18));
        char c = (char) ((i2 >> 16) & 255);
        char c2 = (char) ((i2 >> 8) & 255);
        char c3 = (char) (i2 & 255);
        StringBuilder stringBuilder = new StringBuilder(3);
        stringBuilder.append(c);
        if (length >= 2) {
            stringBuilder.append(c2);
        }
        if (length >= 3) {
            stringBuilder.append(c3);
        }
        return stringBuilder.toString();
    }
}
