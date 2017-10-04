package com.google.a.a.a;

import com.alipay.sdk.j.i;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.a.c.b;
import com.google.a.c.e;
import com.google.a.h;
import com.google.android.gms.location.places.Place;
import com.here.odnp.debug.DebugFile;
import com.sina.weibo.sdk.component.WidgetRequestParam;
import dji.pilot.usercenter.protocol.d;
import dji.thirdparty.g.b.b.a.c;
import java.util.Arrays;

public final class a {
    private static final String[] a = new String[]{"CTRL_PS", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, c.ik_, "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", c.il_, "W", "X", "Y", "Z", "CTRL_LL", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private static final String[] b = new String[]{"CTRL_PS", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", WidgetRequestParam.REQ_PARAM_COMMENT_TOPIC, "r", "s", "t", "u", "v", "w", "x", "y", "z", "CTRL_US", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private static final String[] c = new String[]{"CTRL_PS", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "\u0001", "\u0002", "\u0003", "\u0004", "\u0005", "\u0006", "\u0007", "\b", "\t", "\n", "\u000b", "\f", "\r", "\u001b", "\u001c", "\u001d", "\u001e", "\u001f", "@", "\\", "^", "_", "`", "|", "~", "", "CTRL_LL", "CTRL_UL", "CTRL_PL", "CTRL_BS"};
    private static final String[] d = new String[]{"", "\r", DebugFile.EOL, ". ", ", ", ": ", "!", "\"", "#", "$", "%", com.alipay.sdk.h.a.b, "'", "(", ")", "*", "+", ",", "-", ".", d.t, ":", i.b, "<", "=", ">", "?", d.G, d.H, "{", i.d, "CTRL_UL"};
    private static final String[] e = new String[]{"CTRL_PS", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ",", ".", "CTRL_UL", "CTRL_US"};
    private com.google.a.a.a f;

    private enum a {
        UPPER,
        LOWER,
        MIXED,
        DIGIT,
        PUNCT,
        BINARY
    }

    public e a(com.google.a.a.a aVar) throws h {
        this.f = aVar;
        return new e(null, b(c(a(aVar.d()))), null, null);
    }

    public static String a(boolean[] zArr) {
        return b(zArr);
    }

    private static String b(boolean[] zArr) {
        int length = zArr.length;
        a aVar = a.UPPER;
        a aVar2 = a.UPPER;
        StringBuilder stringBuilder = new StringBuilder(20);
        int i = 0;
        a aVar3 = aVar2;
        aVar2 = aVar;
        while (i < length) {
            int i2;
            if (aVar3 != a.BINARY) {
                i2 = aVar3 == a.DIGIT ? 4 : 5;
                if (length - i < i2) {
                    break;
                }
                int a = a(zArr, i, i2);
                i += i2;
                String a2 = a(aVar3, a);
                if (a2.startsWith("CTRL_")) {
                    aVar = a(a2.charAt(5));
                    if (a2.charAt(6) == 'L') {
                        aVar2 = aVar;
                    }
                } else {
                    stringBuilder.append(a2);
                    aVar = aVar2;
                }
                aVar3 = aVar;
            } else if (length - i < 5) {
                break;
            } else {
                i2 = a(zArr, i, 5);
                i += 5;
                if (i2 == 0) {
                    if (length - i < 11) {
                        break;
                    }
                    i2 = a(zArr, i, 11) + 31;
                    i += 11;
                }
                int i3 = 0;
                while (i3 < i2) {
                    if (length - i < 8) {
                        i2 = length;
                        break;
                    }
                    stringBuilder.append((char) a(zArr, i, 8));
                    i3++;
                    i += 8;
                }
                i2 = i;
                i = i2;
                aVar3 = aVar2;
            }
        }
        return stringBuilder.toString();
    }

    private static a a(char c) {
        switch (c) {
            case Place.TYPE_MUSEUM /*66*/:
                return a.BINARY;
            case Place.TYPE_PAINTER /*68*/:
                return a.DIGIT;
            case 'L':
                return a.LOWER;
            case 'M':
                return a.MIXED;
            case 'P':
                return a.PUNCT;
            default:
                return a.UPPER;
        }
    }

    private static String a(a aVar, int i) {
        switch (aVar) {
            case UPPER:
                return a[i];
            case LOWER:
                return b[i];
            case MIXED:
                return c[i];
            case PUNCT:
                return d[i];
            case DIGIT:
                return e[i];
            default:
                throw new IllegalStateException("Bad table");
        }
    }

    private boolean[] c(boolean[] zArr) throws h {
        com.google.a.c.b.a aVar;
        int i = 8;
        if (this.f.a() <= 2) {
            i = 6;
            aVar = com.google.a.c.b.a.c;
        } else if (this.f.a() <= 8) {
            aVar = com.google.a.c.b.a.g;
        } else if (this.f.a() <= 22) {
            i = 10;
            aVar = com.google.a.c.b.a.b;
        } else {
            i = 12;
            aVar = com.google.a.c.b.a.a;
        }
        int b = this.f.b();
        int length = zArr.length / i;
        int i2 = length - b;
        int[] iArr = new int[length];
        int length2 = zArr.length % i;
        int i3 = 0;
        while (i3 < length) {
            iArr[i3] = a(zArr, length2, i);
            i3++;
            length2 += i;
        }
        try {
            new com.google.a.c.b.c(aVar).a(iArr, i2);
            int i4 = (1 << i) - 1;
            int i5 = 0;
            for (i3 = 0; i3 < b; i3++) {
                length2 = iArr[i3];
                if (length2 == 0 || length2 == i4) {
                    throw h.a();
                }
                if (length2 == 1 || length2 == i4 - 1) {
                    i5++;
                }
            }
            boolean[] zArr2 = new boolean[((b * i) - i5)];
            i2 = 0;
            i3 = 0;
            while (i2 < b) {
                int i6 = iArr[i2];
                boolean z;
                if (i6 == 1 || i6 == i4 - 1) {
                    length2 = (i3 + i) - 1;
                    if (i6 > 1) {
                        z = true;
                    } else {
                        z = false;
                    }
                    Arrays.fill(zArr2, i3, length2, z);
                    i5 = (i - 1) + i3;
                } else {
                    length2 = i - 1;
                    while (length2 >= 0) {
                        length = i3 + 1;
                        if (((1 << length2) & i6) != 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        zArr2[i3] = z;
                        length2--;
                        i3 = length;
                    }
                    i5 = i3;
                }
                i2++;
                i3 = i5;
            }
            return zArr2;
        } catch (com.google.a.c.b.e e) {
            throw h.a();
        }
    }

    boolean[] a(b bVar) {
        int i;
        int i2;
        int i3;
        int i4;
        boolean c = this.f.c();
        int a = this.f.a();
        int i5 = c ? (a * 4) + 11 : (a * 4) + 14;
        int[] iArr = new int[i5];
        boolean[] zArr = new boolean[a(a, c)];
        if (c) {
            for (i = 0; i < iArr.length; i++) {
                iArr[i] = i;
            }
        } else {
            i2 = i5 / 2;
            i3 = ((i5 + 1) + ((((i5 / 2) - 1) / 15) * 2)) / 2;
            for (i = 0; i < i2; i++) {
                i4 = (i / 15) + i;
                iArr[(i2 - i) - 1] = (i3 - i4) - 1;
                iArr[i2 + i] = (i4 + i3) + 1;
            }
        }
        i4 = 0;
        for (int i6 = 0; i6 < a; i6++) {
            i = c ? ((a - i6) * 4) + 9 : ((a - i6) * 4) + 12;
            int i7 = i6 * 2;
            int i8 = (i5 - 1) - i7;
            for (i3 = 0; i3 < i; i3++) {
                int i9 = i3 * 2;
                for (i2 = 0; i2 < 2; i2++) {
                    zArr[(i4 + i9) + i2] = bVar.a(iArr[i7 + i2], iArr[i7 + i3]);
                    zArr[(((i * 2) + i4) + i9) + i2] = bVar.a(iArr[i7 + i3], iArr[i8 - i2]);
                    zArr[(((i * 4) + i4) + i9) + i2] = bVar.a(iArr[i8 - i2], iArr[i8 - i3]);
                    zArr[(((i * 6) + i4) + i9) + i2] = bVar.a(iArr[i8 - i3], iArr[i7 + i2]);
                }
            }
            i4 = (i * 8) + i4;
        }
        return zArr;
    }

    private static int a(boolean[] zArr, int i, int i2) {
        int i3 = 0;
        for (int i4 = i; i4 < i + i2; i4++) {
            i3 <<= 1;
            if (zArr[i4]) {
                i3++;
            }
        }
        return i3;
    }

    private static int a(int i, boolean z) {
        return ((z ? 88 : d.k) + (i * 16)) * i;
    }
}
