package com.google.a.i.c;

import com.google.a.c.a;
import com.google.a.c.d;
import com.google.a.g;
import com.google.a.i.a.f;
import com.google.a.i.a.h;
import com.google.a.i.a.j;
import com.google.a.i.a.j.b;
import com.google.a.w;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public final class c {
    static final String a = "ISO-8859-1";
    private static final int[] b = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};

    private c() {
    }

    private static int a(b bVar) {
        return ((d.a(bVar) + d.b(bVar)) + d.c(bVar)) + d.d(bVar);
    }

    public static f a(String str, f fVar) throws w {
        return a(str, fVar, null);
    }

    public static f a(String str, f fVar, Map<g, ?> map) throws w {
        String str2;
        if (map == null) {
            str2 = null;
        } else {
            str2 = (String) map.get(g.b);
        }
        if (str2 == null) {
            str2 = a;
        }
        h a = a(str, str2);
        a aVar = new a();
        if (a == h.BYTE && !a.equals(str2)) {
            d characterSetECIByName = d.getCharacterSetECIByName(str2);
            if (characterSetECIByName != null) {
                a(characterSetECIByName, aVar);
            }
        }
        a(a, aVar);
        a aVar2 = new a();
        a(str, a, aVar2, str2);
        j a2 = a((a.a(a((aVar.a() + a.a(j.b(1))) + aVar2.a(), fVar)) + aVar.a()) + aVar2.a(), fVar);
        a aVar3 = new a();
        aVar3.a(aVar);
        a(a == h.BYTE ? aVar2.b() : str.length(), a2, a, aVar3);
        aVar3.a(aVar2);
        b a3 = a2.a(fVar);
        int c = a2.c() - a3.c();
        a(c, aVar3);
        a a4 = a(aVar3, a2.c(), c, a3.b());
        f fVar2 = new f();
        fVar2.a(fVar);
        fVar2.a(a);
        fVar2.a(a2);
        int d = a2.d();
        b bVar = new b(d, d);
        d = a(a4, fVar, a2, bVar);
        fVar2.a(d);
        e.a(a4, fVar, a2, d, bVar);
        fVar2.a(bVar);
        return fVar2;
    }

    static int a(int i) {
        if (i < b.length) {
            return b[i];
        }
        return -1;
    }

    public static h a(String str) {
        return a(str, null);
    }

    private static h a(String str, String str2) {
        int i = 0;
        if (!"Shift_JIS".equals(str2)) {
            int i2 = 0;
            int i3 = 0;
            while (i < str.length()) {
                int charAt = str.charAt(i);
                if (charAt >= '0' && charAt <= '9') {
                    i3 = 1;
                } else if (a(charAt) == -1) {
                    return h.BYTE;
                } else {
                    i2 = 1;
                }
                i++;
            }
            if (i2 != 0) {
                return h.ALPHANUMERIC;
            }
            if (i3 != 0) {
                return h.NUMERIC;
            }
            return h.BYTE;
        } else if (b(str)) {
            return h.KANJI;
        } else {
            return h.BYTE;
        }
    }

    private static boolean b(String str) {
        try {
            byte[] bytes = str.getBytes("Shift_JIS");
            int length = bytes.length;
            if (length % 2 != 0) {
                return false;
            }
            for (int i = 0; i < length; i += 2) {
                int i2 = bytes[i] & 255;
                if ((i2 < 129 || i2 > 159) && (i2 < dji.thirdparty.g.b.a.a.fw_ || i2 > 235)) {
                    return false;
                }
            }
            return true;
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }

    private static int a(a aVar, f fVar, j jVar, b bVar) throws w {
        int i = Integer.MAX_VALUE;
        int i2 = -1;
        int i3 = 0;
        while (i3 < 8) {
            e.a(aVar, fVar, jVar, i3, bVar);
            int a = a(bVar);
            if (a < i) {
                i2 = i3;
            } else {
                a = i;
            }
            i3++;
            i = a;
        }
        return i2;
    }

    private static j a(int i, f fVar) throws w {
        for (int i2 = 1; i2 <= 40; i2++) {
            j b = j.b(i2);
            if (b.c() - b.a(fVar).c() >= (i + 7) / 8) {
                return b;
            }
        }
        throw new w("Data too big");
    }

    static void a(int i, a aVar) throws w {
        int i2 = i << 3;
        if (aVar.a() > i2) {
            throw new w("data bits cannot fit in the QR Code" + aVar.a() + " > " + i2);
        }
        int i3;
        for (i3 = 0; i3 < 4 && aVar.a() < i2; i3++) {
            aVar.a(false);
        }
        i3 = aVar.a() & 7;
        if (i3 > 0) {
            while (i3 < 8) {
                aVar.a(false);
                i3++;
            }
        }
        int b = i - aVar.b();
        for (i3 = 0; i3 < b; i3++) {
            aVar.c((i3 & 1) == 0 ? 236 : 17, 8);
        }
        if (aVar.a() != i2) {
            throw new w("Bits size does not equal capacity");
        }
    }

    static void a(int i, int i2, int i3, int i4, int[] iArr, int[] iArr2) throws w {
        if (i4 >= i3) {
            throw new w("Block ID too large");
        }
        int i5 = i % i3;
        int i6 = i3 - i5;
        int i7 = i / i3;
        int i8 = i7 + 1;
        int i9 = i2 / i3;
        int i10 = i9 + 1;
        i7 -= i9;
        i8 -= i10;
        if (i7 != i8) {
            throw new w("EC bytes mismatch");
        } else if (i3 != i6 + i5) {
            throw new w("RS blocks mismatch");
        } else {
            if (i != (i5 * (i10 + i8)) + ((i9 + i7) * i6)) {
                throw new w("Total bytes mismatch");
            } else if (i4 < i6) {
                iArr[0] = i9;
                iArr2[0] = i7;
            } else {
                iArr[0] = i10;
                iArr2[0] = i8;
            }
        }
    }

    static a a(a aVar, int i, int i2, int i3) throws w {
        if (aVar.b() != i2) {
            throw new w("Number of bits and data bytes does not match");
        }
        Collection<a> arrayList = new ArrayList(i3);
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i4 < i3) {
            int[] iArr = new int[1];
            int[] iArr2 = new int[1];
            a(i, i2, i3, i4, iArr, iArr2);
            int i8 = iArr[0];
            byte[] bArr = new byte[i8];
            aVar.a(i7 * 8, bArr, 0, i8);
            byte[] a = a(bArr, iArr2[0]);
            arrayList.add(new a(bArr, a));
            int max = Math.max(i6, i8);
            i4++;
            i5 = Math.max(i5, a.length);
            i6 = max;
            i7 = iArr[0] + i7;
        }
        if (i2 != i7) {
            throw new w("Data bytes does not match offset");
        }
        a aVar2 = new a();
        for (max = 0; max < i6; max++) {
            for (a a2 : arrayList) {
                byte[] a3 = a2.a();
                if (max < a3.length) {
                    aVar2.c(a3[max], 8);
                }
            }
        }
        for (max = 0; max < i5; max++) {
            for (a a22 : arrayList) {
                a3 = a22.b();
                if (max < a3.length) {
                    aVar2.c(a3[max], 8);
                }
            }
        }
        if (i == aVar2.b()) {
            return aVar2;
        }
        throw new w("Interleaving error: " + i + " and " + aVar2.b() + " differ.");
    }

    static byte[] a(byte[] bArr, int i) {
        int i2 = 0;
        int length = bArr.length;
        int[] iArr = new int[(length + i)];
        for (int i3 = 0; i3 < length; i3++) {
            iArr[i3] = bArr[i3] & 255;
        }
        new com.google.a.c.b.d(com.google.a.c.b.a.e).a(iArr, i);
        byte[] bArr2 = new byte[i];
        while (i2 < i) {
            bArr2[i2] = (byte) iArr[length + i2];
            i2++;
        }
        return bArr2;
    }

    static void a(h hVar, a aVar) {
        aVar.c(hVar.a(), 4);
    }

    static void a(int i, j jVar, h hVar, a aVar) throws w {
        int a = hVar.a(jVar);
        if (i >= (1 << a)) {
            throw new w(i + " is bigger than " + ((1 << a) - 1));
        }
        aVar.c(i, a);
    }

    static void a(String str, h hVar, a aVar, String str2) throws w {
        switch (hVar) {
            case NUMERIC:
                a((CharSequence) str, aVar);
                return;
            case ALPHANUMERIC:
                b(str, aVar);
                return;
            case BYTE:
                a(str, aVar, str2);
                return;
            case KANJI:
                a(str, aVar);
                return;
            default:
                throw new w("Invalid mode: " + hVar);
        }
    }

    static void a(CharSequence charSequence, a aVar) {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            int charAt = charSequence.charAt(i) - 48;
            if (i + 2 < length) {
                aVar.c(((charAt * 100) + ((charSequence.charAt(i + 1) - 48) * 10)) + (charSequence.charAt(i + 2) - 48), 10);
                i += 3;
            } else if (i + 1 < length) {
                aVar.c((charAt * 10) + (charSequence.charAt(i + 1) - 48), 7);
                i += 2;
            } else {
                aVar.c(charAt, 4);
                i++;
            }
        }
    }

    static void b(CharSequence charSequence, a aVar) throws w {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            int a = a(charSequence.charAt(i));
            if (a == -1) {
                throw new w();
            } else if (i + 1 < length) {
                int a2 = a(charSequence.charAt(i + 1));
                if (a2 == -1) {
                    throw new w();
                }
                aVar.c((a * 45) + a2, 11);
                i += 2;
            } else {
                aVar.c(a, 6);
                i++;
            }
        }
    }

    static void a(String str, a aVar, String str2) throws w {
        try {
            for (byte c : str.getBytes(str2)) {
                aVar.c(c, 8);
            }
        } catch (Throwable e) {
            throw new w(e);
        }
    }

    static void a(String str, a aVar) throws w {
        try {
            byte[] bytes = str.getBytes("Shift_JIS");
            int length = bytes.length;
            for (int i = 0; i < length; i += 2) {
                int i2 = ((bytes[i] & 255) << 8) | (bytes[i + 1] & 255);
                if (i2 >= 33088 && i2 <= 40956) {
                    i2 -= 33088;
                } else if (i2 < 57408 || i2 > 60351) {
                    i2 = -1;
                } else {
                    i2 -= 49472;
                }
                if (i2 == -1) {
                    throw new w("Invalid byte sequence");
                }
                aVar.c((i2 & 255) + ((i2 >> 8) * 192), 13);
            }
        } catch (Throwable e) {
            throw new w(e);
        }
    }

    private static void a(d dVar, a aVar) {
        aVar.c(h.ECI.a(), 4);
        aVar.c(dVar.a(), 8);
    }
}
