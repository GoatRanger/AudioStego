package com.google.a.g;

import android.support.v4.media.TransportMediator;
import com.google.a.e;
import com.google.a.h;
import com.google.a.m;
import com.google.a.r;
import com.google.a.t;
import com.google.api.client.http.HttpStatusCodes;
import dji.pilot.flyunlimit.a;
import dji.pilot.usercenter.protocol.d;
import dji.thirdparty.b.a.b.q;
import it.sauronsoftware.ftp4j.FTPCodes;
import java.util.Arrays;
import java.util.Map;

public final class g extends q {
    private static final String a = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*";
    private static final char[] b = a.toCharArray();
    private static final int[] e = new int[]{276, 328, 324, 322, 296, 292, 290, 336, 274, 266, 424, 420, 418, 404, a.y, 394, 360, 356, 354, q.b, 282, 344, FTPCodes.NEED_ACCOUNT, 326, 300, 278, 436, 434, 428, 422, a.B, a.w, 364, 358, 310, 314, HttpStatusCodes.STATUS_CODE_FOUND, 468, 466, 458, 366, 374, 430, 294, 474, 470, d.f, FTPCodes.PENDING_FURTHER_INFORMATION};
    private static final int f = e[47];
    private final StringBuilder g = new StringBuilder(20);
    private final int[] h = new int[6];

    public r a(int i, com.google.a.c.a aVar, Map<e, ?> map) throws m, com.google.a.d, h {
        int d = aVar.d(a(aVar)[1]);
        int a = aVar.a();
        int[] iArr = this.h;
        Arrays.fill(iArr, 0);
        CharSequence charSequence = this.g;
        charSequence.setLength(0);
        while (true) {
            q.a(aVar, d, iArr);
            int a2 = a(iArr);
            if (a2 < 0) {
                throw m.a();
            }
            char a3 = a(a2);
            charSequence.append(a3);
            int i2 = d;
            for (int i3 : iArr) {
                i2 += i3;
            }
            a2 = aVar.d(i2);
            if (a3 == '*') {
                break;
            }
            d = a2;
        }
        charSequence.deleteCharAt(charSequence.length() - 1);
        int i4 = 0;
        for (int i32 : iArr) {
            i4 += i32;
        }
        if (a2 == a || !aVar.a(a2)) {
            throw m.a();
        } else if (charSequence.length() < 2) {
            throw m.a();
        } else {
            b(charSequence);
            charSequence.setLength(charSequence.length() - 2);
            float f = ((float) d) + (((float) i4) / 2.0f);
            return new r(a(charSequence), null, new t[]{new t(((float) (r4[1] + r4[0])) / 2.0f, (float) i), new t(f, (float) i)}, com.google.a.a.CODE_93);
        }
    }

    private int[] a(com.google.a.c.a aVar) throws m {
        int a = aVar.a();
        int d = aVar.d(0);
        Arrays.fill(this.h, 0);
        int[] iArr = this.h;
        int length = iArr.length;
        int i = 0;
        int i2 = d;
        d = 0;
        for (int i3 = d; i3 < a; i3++) {
            if ((aVar.a(i3) ^ i) != 0) {
                iArr[d] = iArr[d] + 1;
            } else {
                if (d != length - 1) {
                    d++;
                } else if (a(iArr) == f) {
                    return new int[]{i2, i3};
                } else {
                    i2 += iArr[0] + iArr[1];
                    System.arraycopy(iArr, 2, iArr, 0, length - 2);
                    iArr[length - 2] = 0;
                    iArr[length - 1] = 0;
                    d--;
                }
                iArr[d] = 1;
                if (i == 0) {
                    i = 1;
                } else {
                    i = 0;
                }
            }
        }
        throw m.a();
    }

    private static int a(int[] iArr) {
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        while (i < iArr.length) {
            i++;
            i2 = iArr[i] + i2;
        }
        i = 0;
        for (int i3 = 0; i3 < length; i3++) {
            int i4;
            int i5 = ((iArr[i3] << 8) * 9) / i2;
            int i6 = i5 >> 8;
            if ((i5 & 255) > TransportMediator.KEYCODE_MEDIA_PAUSE) {
                i4 = i6 + 1;
            } else {
                i4 = i6;
            }
            if (i4 < 1 || i4 > 4) {
                return -1;
            }
            if ((i3 & 1) == 0) {
                i6 = 0;
                while (i6 < i4) {
                    i6++;
                    i = (i << 1) | 1;
                }
            } else {
                i <<= i4;
            }
        }
        return i;
    }

    private static char a(int i) throws m {
        for (int i2 = 0; i2 < e.length; i2++) {
            if (e[i2] == i) {
                return b[i2];
            }
        }
        throw m.a();
    }

    private static String a(CharSequence charSequence) throws h {
        int length = charSequence.length();
        StringBuilder stringBuilder = new StringBuilder(length);
        int i = 0;
        while (i < length) {
            int i2;
            char charAt = charSequence.charAt(i);
            if (charAt < 'a' || charAt > 'd') {
                stringBuilder.append(charAt);
                i2 = i;
            } else if (i >= length - 1) {
                throw h.a();
            } else {
                char charAt2 = charSequence.charAt(i + 1);
                switch (charAt) {
                    case 'a':
                        if (charAt2 >= 'A' && charAt2 <= 'Z') {
                            charAt = (char) (charAt2 - 64);
                            break;
                        }
                        throw h.a();
                        break;
                    case 'b':
                        if (charAt2 < 'A' || charAt2 > 'E') {
                            if (charAt2 >= 'F' && charAt2 <= 'W') {
                                charAt = (char) (charAt2 - 11);
                                break;
                            }
                            throw h.a();
                        }
                        charAt = (char) (charAt2 - 38);
                        break;
                        break;
                    case 'c':
                        if (charAt2 >= 'A' && charAt2 <= 'O') {
                            charAt = (char) (charAt2 - 32);
                            break;
                        } else if (charAt2 == 'Z') {
                            charAt = ':';
                            break;
                        } else {
                            throw h.a();
                        }
                    case 'd':
                        if (charAt2 >= 'A' && charAt2 <= 'Z') {
                            charAt = (char) (charAt2 + 32);
                            break;
                        }
                        throw h.a();
                        break;
                    default:
                        charAt = '\u0000';
                        break;
                }
                stringBuilder.append(charAt);
                i2 = i + 1;
            }
            i = i2 + 1;
        }
        return stringBuilder.toString();
    }

    private static void b(CharSequence charSequence) throws com.google.a.d {
        int length = charSequence.length();
        a(charSequence, length - 2, 20);
        a(charSequence, length - 1, 15);
    }

    private static void a(CharSequence charSequence, int i, int i2) throws com.google.a.d {
        int i3 = 1;
        int i4 = i - 1;
        int i5 = 0;
        while (i4 >= 0) {
            int indexOf = (a.indexOf(charSequence.charAt(i4)) * i3) + i5;
            i5 = i3 + 1;
            if (i5 > i2) {
                i5 = 1;
            }
            i4--;
            i3 = i5;
            i5 = indexOf;
        }
        if (charSequence.charAt(i) != b[i5 % 47]) {
            throw com.google.a.d.a();
        }
    }
}
