package com.google.a.g;

import com.google.a.c.a;
import com.google.a.h;
import com.google.a.m;
import com.google.a.r;
import com.google.a.t;
import com.google.api.client.http.HttpStatusCodes;
import com.here.posclient.analytics.TrackerEvent;
import dji.pilot.publics.control.rc.b;
import dji.pilot.usercenter.protocol.d;
import java.util.Arrays;
import java.util.Map;

public final class e extends q {
    static final String a = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%";
    static final int[] b = new int[]{52, 289, 97, 352, 49, HttpStatusCodes.STATUS_CODE_NOT_MODIFIED, d.k, 37, 292, 100, 265, 73, 328, 25, 280, 88, 13, 268, 76, 28, 259, 67, 322, 19, 274, 82, 7, b.j, 70, 22, 385, 193, 448, 145, 400, 208, TrackerEvent.PositioningOfflinePrivateIndoor, 388, 196, 148, 168, 162, 138, 42};
    private static final char[] e = a.toCharArray();
    private static final int f = b[39];
    private final boolean g;
    private final boolean h;
    private final StringBuilder i;
    private final int[] j;

    public e() {
        this(false);
    }

    public e(boolean z) {
        this(z, false);
    }

    public e(boolean z, boolean z2) {
        this.g = z;
        this.h = z2;
        this.i = new StringBuilder(20);
        this.j = new int[9];
    }

    public r a(int i, a aVar, Map<com.google.a.e, ?> map) throws m, com.google.a.d, h {
        int a;
        int i2;
        int[] iArr = this.j;
        Arrays.fill(iArr, 0);
        CharSequence charSequence = this.i;
        charSequence.setLength(0);
        int d = aVar.d(a(aVar, iArr)[1]);
        int a2 = aVar.a();
        while (true) {
            q.a(aVar, d, iArr);
            a = a(iArr);
            if (a < 0) {
                throw m.a();
            }
            char a3 = a(a);
            charSequence.append(a3);
            i2 = d;
            for (int i3 : iArr) {
                i2 += i3;
            }
            a = aVar.d(i2);
            if (a3 == '*') {
                break;
            }
            d = a;
        }
        charSequence.setLength(charSequence.length() - 1);
        int i4 = 0;
        for (int i32 : iArr) {
            i4 += i32;
        }
        i2 = (a - d) - i4;
        if (a == a2 || (i2 >> 1) >= i4) {
            if (this.g) {
                int length = charSequence.length() - 1;
                i2 = 0;
                for (a = 0; a < length; a++) {
                    i2 += a.indexOf(this.i.charAt(a));
                }
                if (charSequence.charAt(length) != e[i2 % 43]) {
                    throw com.google.a.d.a();
                }
                charSequence.setLength(length);
            }
            if (charSequence.length() == 0) {
                throw m.a();
            }
            String a4;
            if (this.h) {
                a4 = a(charSequence);
            } else {
                a4 = charSequence.toString();
            }
            float f = ((float) d) + (((float) i4) / 2.0f);
            return new r(a4, null, new t[]{new t(((float) (r6[1] + r6[0])) / 2.0f, (float) i), new t(f, (float) i)}, com.google.a.a.CODE_39);
        }
        throw m.a();
    }

    private static int[] a(a aVar, int[] iArr) throws m {
        int a = aVar.a();
        int d = aVar.d(0);
        int length = iArr.length;
        int i = d;
        int i2 = 0;
        int i3 = 0;
        while (i < a) {
            if ((aVar.a(i) ^ i2) != 0) {
                iArr[i3] = iArr[i3] + 1;
            } else {
                if (i3 != length - 1) {
                    i3++;
                } else if (a(iArr) == f && aVar.a(Math.max(0, d - ((i - d) >> 1)), d, false)) {
                    return new int[]{d, i};
                } else {
                    d += iArr[0] + iArr[1];
                    System.arraycopy(iArr, 2, iArr, 0, length - 2);
                    iArr[length - 2] = 0;
                    iArr[length - 1] = 0;
                    i3--;
                }
                iArr[i3] = 1;
                if (i2 == 0) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
            }
            i++;
        }
        throw m.a();
    }

    private static int a(int[] iArr) {
        int i;
        int length = iArr.length;
        int i2 = 0;
        while (true) {
            int i3;
            int i4 = Integer.MAX_VALUE;
            for (int i5 : iArr) {
                if (i5 < i4 && i5 > r0) {
                    i4 = i5;
                }
            }
            i2 = 0;
            int i52 = 0;
            i = 0;
            for (i3 = 0; i3 < length; i3++) {
                int i6 = iArr[i3];
                if (i6 > i4) {
                    i2 |= 1 << ((length - 1) - i3);
                    i++;
                    i52 += i6;
                }
            }
            if (i == 3) {
                break;
            } else if (i <= 3) {
                return -1;
            } else {
                i2 = i4;
            }
        }
        int i7 = i;
        for (i = 0; i < length && i7 > 0; i++) {
            i3 = iArr[i];
            if (i3 > i4) {
                i7--;
                if ((i3 << 1) >= i52) {
                    return -1;
                }
            }
        }
        return i2;
    }

    private static char a(int i) throws m {
        for (int i2 = 0; i2 < b.length; i2++) {
            if (b[i2] == i) {
                return e[i2];
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
            if (charAt == '+' || charAt == '$' || charAt == '%' || charAt == '/') {
                char charAt2 = charSequence.charAt(i + 1);
                switch (charAt) {
                    case '$':
                        if (charAt2 >= 'A' && charAt2 <= 'Z') {
                            charAt = (char) (charAt2 - 64);
                            break;
                        }
                        throw h.a();
                        break;
                    case '%':
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
                    case '+':
                        if (charAt2 >= 'A' && charAt2 <= 'Z') {
                            charAt = (char) (charAt2 + 32);
                            break;
                        }
                        throw h.a();
                        break;
                    case '/':
                        if (charAt2 >= 'A' && charAt2 <= 'O') {
                            charAt = (char) (charAt2 - 32);
                            break;
                        } else if (charAt2 == 'Z') {
                            charAt = ':';
                            break;
                        } else {
                            throw h.a();
                        }
                    default:
                        charAt = '\u0000';
                        break;
                }
                stringBuilder.append(charAt);
                i2 = i + 1;
            } else {
                stringBuilder.append(charAt);
                i2 = i;
            }
            i = i2 + 1;
        }
        return stringBuilder.toString();
    }
}
