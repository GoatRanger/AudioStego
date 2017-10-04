package com.google.a.d.c;

import android.support.v4.media.TransportMediator;

final class a implements g {
    a() {
    }

    public int a() {
        return 0;
    }

    public void a(h hVar) {
        if (j.a(hVar.a(), hVar.a) >= 2) {
            hVar.a(a(hVar.a().charAt(hVar.a), hVar.a().charAt(hVar.a + 1)));
            hVar.a += 2;
            return;
        }
        char b = hVar.b();
        int a = j.a(hVar.a(), hVar.a, a());
        if (a != a()) {
            switch (a) {
                case 1:
                    hVar.a('æ');
                    hVar.b(1);
                    return;
                case 2:
                    hVar.a('ï');
                    hVar.b(2);
                    return;
                case 3:
                    hVar.a('î');
                    hVar.b(3);
                    return;
                case 4:
                    hVar.a('ð');
                    hVar.b(4);
                    return;
                case 5:
                    hVar.a('ç');
                    hVar.b(5);
                    return;
                default:
                    throw new IllegalStateException("Illegal mode: " + a);
            }
        } else if (j.b(b)) {
            hVar.a('ë');
            hVar.a((char) ((b - 128) + 1));
            hVar.a++;
        } else {
            hVar.a((char) (b + 1));
            hVar.a++;
        }
    }

    private static char a(char c, char c2) {
        if (j.a(c) && j.a(c2)) {
            return (char) ((((c - 48) * 10) + (c2 - 48)) + TransportMediator.KEYCODE_MEDIA_RECORD);
        }
        throw new IllegalArgumentException("not digits: " + c + c2);
    }
}
