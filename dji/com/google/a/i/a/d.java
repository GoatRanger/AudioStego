package com.google.a.i.a;

import android.support.v4.media.TransportMediator;
import com.google.a.c.c;
import com.google.a.c.e;
import com.google.a.c.l;
import com.google.a.h;
import dji.thirdparty.g.b.a.a;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

final class d {
    private static final char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '$', '%', '*', '+', '-', '.', '/', ':'};
    private static final int b = 1;

    private d() {
    }

    static e a(byte[] bArr, j jVar, f fVar, Map<com.google.a.e, ?> map) throws h {
        int i;
        int i2;
        List list;
        String str;
        c cVar = new c(bArr);
        StringBuilder stringBuilder = new StringBuilder(50);
        List arrayList = new ArrayList(1);
        com.google.a.c.d dVar = null;
        int i3 = -1;
        int i4 = -1;
        boolean z = false;
        while (true) {
            h hVar;
            boolean z2;
            if (cVar.c() < 4) {
                hVar = h.TERMINATOR;
            } else {
                hVar = h.forBits(cVar.a(4));
            }
            if (hVar == h.TERMINATOR) {
                z2 = z;
                i = i4;
                i2 = i3;
            } else if (hVar == h.FNC1_FIRST_POSITION || hVar == h.FNC1_SECOND_POSITION) {
                z2 = true;
                i = i4;
                i2 = i3;
            } else if (hVar == h.STRUCTURED_APPEND) {
                if (cVar.c() < 16) {
                    throw h.a();
                }
                try {
                    z2 = z;
                    i = cVar.a(8);
                    i2 = cVar.a(8);
                } catch (IllegalArgumentException e) {
                    throw h.a();
                }
            } else if (hVar == h.ECI) {
                dVar = com.google.a.c.d.getCharacterSetECIByValue(a(cVar));
                if (dVar == null) {
                    throw h.a();
                }
                z2 = z;
                i = i4;
                i2 = i3;
            } else if (hVar == h.HANZI) {
                r2 = cVar.a(4);
                i = cVar.a(hVar.a(jVar));
                if (r2 == 1) {
                    a(cVar, stringBuilder, i);
                }
                z2 = z;
                i = i4;
                i2 = i3;
            } else {
                r2 = cVar.a(hVar.a(jVar));
                if (hVar == h.NUMERIC) {
                    c(cVar, stringBuilder, r2);
                    z2 = z;
                    i = i4;
                    i2 = i3;
                } else if (hVar == h.ALPHANUMERIC) {
                    a(cVar, stringBuilder, r2, z);
                    z2 = z;
                    i = i4;
                    i2 = i3;
                } else if (hVar == h.BYTE) {
                    a(cVar, stringBuilder, r2, dVar, arrayList, map);
                    z2 = z;
                    i = i4;
                    i2 = i3;
                } else if (hVar == h.KANJI) {
                    b(cVar, stringBuilder, r2);
                    z2 = z;
                    i = i4;
                    i2 = i3;
                } else {
                    throw h.a();
                }
            }
            if (hVar == h.TERMINATOR) {
                break;
            }
            i3 = i2;
            i4 = i;
            z = z2;
        }
        String stringBuilder2 = stringBuilder.toString();
        if (arrayList.isEmpty()) {
            list = null;
        } else {
            list = arrayList;
        }
        if (fVar == null) {
            str = null;
        } else {
            str = fVar.toString();
        }
        return new e(bArr, stringBuilder2, list, str, i, i2);
    }

    private static void a(c cVar, StringBuilder stringBuilder, int i) throws h {
        if (i * 13 > cVar.c()) {
            throw h.a();
        }
        byte[] bArr = new byte[(i * 2)];
        int i2 = 0;
        while (i > 0) {
            int a = cVar.a(13);
            a = (a % 96) | ((a / 96) << 8);
            if (a < 959) {
                a += 41377;
            } else {
                a += 42657;
            }
            bArr[i2] = (byte) ((a >> 8) & 255);
            bArr[i2 + 1] = (byte) (a & 255);
            i--;
            i2 += 2;
        }
        try {
            stringBuilder.append(new String(bArr, l.b));
        } catch (UnsupportedEncodingException e) {
            throw h.a();
        }
    }

    private static void b(c cVar, StringBuilder stringBuilder, int i) throws h {
        if (i * 13 > cVar.c()) {
            throw h.a();
        }
        byte[] bArr = new byte[(i * 2)];
        int i2 = 0;
        while (i > 0) {
            int a = cVar.a(13);
            a = (a % 192) | ((a / 192) << 8);
            if (a < 7936) {
                a += 33088;
            } else {
                a += 49472;
            }
            bArr[i2] = (byte) (a >> 8);
            bArr[i2 + 1] = (byte) a;
            i--;
            i2 += 2;
        }
        try {
            stringBuilder.append(new String(bArr, l.a));
        } catch (UnsupportedEncodingException e) {
            throw h.a();
        }
    }

    private static void a(c cVar, StringBuilder stringBuilder, int i, com.google.a.c.d dVar, Collection<byte[]> collection, Map<com.google.a.e, ?> map) throws h {
        if ((i << 3) > cVar.c()) {
            throw h.a();
        }
        String a;
        Object obj = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            obj[i2] = (byte) cVar.a(8);
        }
        if (dVar == null) {
            a = l.a(obj, map);
        } else {
            a = dVar.name();
        }
        try {
            stringBuilder.append(new String(obj, a));
            collection.add(obj);
        } catch (UnsupportedEncodingException e) {
            throw h.a();
        }
    }

    private static char a(int i) throws h {
        if (i < a.length) {
            return a[i];
        }
        throw h.a();
    }

    private static void a(c cVar, StringBuilder stringBuilder, int i, boolean z) throws h {
        int length = stringBuilder.length();
        while (i > 1) {
            if (cVar.c() < 11) {
                throw h.a();
            }
            int a = cVar.a(11);
            stringBuilder.append(a(a / 45));
            stringBuilder.append(a(a % 45));
            i -= 2;
        }
        if (i == 1) {
            if (cVar.c() < 6) {
                throw h.a();
            }
            stringBuilder.append(a(cVar.a(6)));
        }
        if (z) {
            while (length < stringBuilder.length()) {
                if (stringBuilder.charAt(length) == '%') {
                    if (length >= stringBuilder.length() - 1 || stringBuilder.charAt(length + 1) != '%') {
                        stringBuilder.setCharAt(length, '\u001d');
                    } else {
                        stringBuilder.deleteCharAt(length + 1);
                    }
                }
                length++;
            }
        }
    }

    private static void c(c cVar, StringBuilder stringBuilder, int i) throws h {
        while (i >= 3) {
            if (cVar.c() < 10) {
                throw h.a();
            }
            int a = cVar.a(10);
            if (a >= 1000) {
                throw h.a();
            }
            stringBuilder.append(a(a / 100));
            stringBuilder.append(a((a / 10) % 10));
            stringBuilder.append(a(a % 10));
            i -= 3;
        }
        if (i == 2) {
            if (cVar.c() < 7) {
                throw h.a();
            }
            a = cVar.a(7);
            if (a >= 100) {
                throw h.a();
            }
            stringBuilder.append(a(a / 10));
            stringBuilder.append(a(a % 10));
        } else if (i != 1) {
        } else {
            if (cVar.c() < 4) {
                throw h.a();
            }
            a = cVar.a(4);
            if (a >= 10) {
                throw h.a();
            }
            stringBuilder.append(a(a));
        }
    }

    private static int a(c cVar) throws h {
        int a = cVar.a(8);
        if ((a & 128) == 0) {
            return a & TransportMediator.KEYCODE_MEDIA_PAUSE;
        }
        if ((a & 192) == 128) {
            return ((a & 63) << 8) | cVar.a(8);
        } else if ((a & a.fw_) == 192) {
            return ((a & 31) << 16) | cVar.a(16);
        } else {
            throw h.a();
        }
    }
}
