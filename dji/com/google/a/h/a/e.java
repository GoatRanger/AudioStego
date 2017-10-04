package com.google.a.h.a;

import com.google.a.h;
import com.google.a.h.c;
import java.math.BigInteger;
import java.util.Arrays;

final class e {
    private static final int a = 900;
    private static final int b = 901;
    private static final int c = 902;
    private static final int d = 924;
    private static final int e = 928;
    private static final int f = 923;
    private static final int g = 922;
    private static final int h = 913;
    private static final int i = 15;
    private static final int j = 25;
    private static final int k = 27;
    private static final int l = 27;
    private static final int m = 28;
    private static final int n = 28;
    private static final int o = 29;
    private static final int p = 29;
    private static final char[] q = new char[]{';', '<', '>', '@', '[', '\\', '}', '_', '`', '~', '!', '\r', '\t', ',', ':', '\n', '-', '.', '$', '/', '\"', '|', '*', '(', ')', '?', '{', '}', '\''};
    private static final char[] r = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '&', '\r', '\t', ',', ':', '#', '-', '.', '$', '/', '+', '%', '*', '=', '^'};
    private static final BigInteger[] s = new BigInteger[16];
    private static final int t = 2;

    private enum a {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static {
        s[0] = BigInteger.ONE;
        BigInteger valueOf = BigInteger.valueOf(900);
        s[1] = valueOf;
        for (int i = 2; i < s.length; i++) {
            s[i] = s[i - 1].multiply(valueOf);
        }
    }

    private e() {
    }

    static com.google.a.c.e a(int[] iArr, String str) throws h {
        StringBuilder stringBuilder = new StringBuilder(iArr.length * 2);
        int i = 2;
        int i2 = iArr[1];
        Object cVar = new c();
        while (i < iArr[0]) {
            switch (i2) {
                case a /*900*/:
                    i2 = a(iArr, i, stringBuilder);
                    break;
                case b /*901*/:
                case h /*913*/:
                case d /*924*/:
                    i2 = a(i2, iArr, i, stringBuilder);
                    break;
                case c /*902*/:
                    i2 = b(iArr, i, stringBuilder);
                    break;
                case g /*922*/:
                case f /*923*/:
                    throw h.a();
                case 928:
                    i2 = a(iArr, i, (c) cVar);
                    break;
                default:
                    i2 = a(iArr, i - 1, stringBuilder);
                    break;
            }
            if (i2 < iArr.length) {
                i = i2 + 1;
                i2 = iArr[i2];
            } else {
                throw h.a();
            }
        }
        if (stringBuilder.length() == 0) {
            throw h.a();
        }
        com.google.a.c.e eVar = new com.google.a.c.e(null, stringBuilder.toString(), null, str);
        eVar.a(cVar);
        return eVar;
    }

    private static int a(int[] iArr, int i, c cVar) throws h {
        if (i + 2 > iArr[0]) {
            throw h.a();
        }
        int[] iArr2 = new int[2];
        int i2 = 0;
        while (i2 < 2) {
            iArr2[i2] = iArr[i];
            i2++;
            i++;
        }
        cVar.a(Integer.parseInt(a(iArr2, 2)));
        StringBuilder stringBuilder = new StringBuilder();
        int a = a(iArr, i, stringBuilder);
        cVar.a(stringBuilder.toString());
        if (iArr[a] == f) {
            i2 = a + 1;
            int[] iArr3 = new int[(iArr[0] - i2)];
            int i3 = 0;
            a = i2;
            i2 = 0;
            while (a < iArr[0] && r0 == 0) {
                int i4 = a + 1;
                int i5 = iArr[a];
                if (i5 < a) {
                    a = i3 + 1;
                    iArr3[i3] = i5;
                    i3 = a;
                    a = i4;
                } else {
                    switch (i5) {
                        case g /*922*/:
                            cVar.a(true);
                            a = i4 + 1;
                            i2 = true;
                            break;
                        default:
                            throw h.a();
                    }
                }
            }
            cVar.a(Arrays.copyOf(iArr3, i3));
            return a;
        } else if (iArr[a] != g) {
            return a;
        } else {
            cVar.a(true);
            return a + 1;
        }
    }

    private static int a(int[] iArr, int i, StringBuilder stringBuilder) {
        int[] iArr2 = new int[((iArr[0] - i) << 1)];
        int[] iArr3 = new int[((iArr[0] - i) << 1)];
        int i2 = 0;
        int i3 = 0;
        while (i < iArr[0] && r0 == 0) {
            int i4 = i + 1;
            int i5 = iArr[i];
            if (i5 >= a) {
                switch (i5) {
                    case a /*900*/:
                        i5 = i3 + 1;
                        iArr2[i3] = a;
                        i3 = i5;
                        i = i4;
                        break;
                    case b /*901*/:
                    case c /*902*/:
                    case g /*922*/:
                    case f /*923*/:
                    case d /*924*/:
                    case 928:
                        i = i4 - 1;
                        i2 = 1;
                        break;
                    case h /*913*/:
                        iArr2[i3] = h;
                        i = i4 + 1;
                        iArr3[i3] = iArr[i4];
                        i3++;
                        break;
                    default:
                        i = i4;
                        break;
                }
            }
            iArr2[i3] = i5 / 30;
            iArr2[i3 + 1] = i5 % 30;
            i3 += 2;
            i = i4;
        }
        a(iArr2, iArr3, i3, stringBuilder);
        return i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(int[] r8, int[] r9, int r10, java.lang.StringBuilder r11) {
        /*
        r2 = com.google.a.h.a.e.a.ALPHA;
        r1 = com.google.a.h.a.e.a.ALPHA;
        r0 = 0;
        r3 = r0;
    L_0x0006:
        if (r3 >= r10) goto L_0x0146;
    L_0x0008:
        r4 = r8[r3];
        r0 = 0;
        r5 = com.google.a.h.a.e.AnonymousClass1.a;
        r6 = r2.ordinal();
        r5 = r5[r6];
        switch(r5) {
            case 1: goto L_0x001f;
            case 2: goto L_0x0058;
            case 3: goto L_0x0094;
            case 4: goto L_0x00dd;
            case 5: goto L_0x0103;
            case 6: goto L_0x011e;
            default: goto L_0x0016;
        };
    L_0x0016:
        if (r0 == 0) goto L_0x001b;
    L_0x0018:
        r11.append(r0);
    L_0x001b:
        r0 = r3 + 1;
        r3 = r0;
        goto L_0x0006;
    L_0x001f:
        r5 = 26;
        if (r4 >= r5) goto L_0x0027;
    L_0x0023:
        r0 = r4 + 65;
        r0 = (char) r0;
        goto L_0x0016;
    L_0x0027:
        r5 = 26;
        if (r4 != r5) goto L_0x002e;
    L_0x002b:
        r0 = 32;
        goto L_0x0016;
    L_0x002e:
        r5 = 27;
        if (r4 != r5) goto L_0x0035;
    L_0x0032:
        r2 = com.google.a.h.a.e.a.LOWER;
        goto L_0x0016;
    L_0x0035:
        r5 = 28;
        if (r4 != r5) goto L_0x003c;
    L_0x0039:
        r2 = com.google.a.h.a.e.a.MIXED;
        goto L_0x0016;
    L_0x003c:
        r5 = 29;
        if (r4 != r5) goto L_0x0046;
    L_0x0040:
        r1 = com.google.a.h.a.e.a.PUNCT_SHIFT;
        r7 = r2;
        r2 = r1;
        r1 = r7;
        goto L_0x0016;
    L_0x0046:
        r5 = 913; // 0x391 float:1.28E-42 double:4.51E-321;
        if (r4 != r5) goto L_0x0051;
    L_0x004a:
        r4 = r9[r3];
        r4 = (char) r4;
        r11.append(r4);
        goto L_0x0016;
    L_0x0051:
        r5 = 900; // 0x384 float:1.261E-42 double:4.447E-321;
        if (r4 != r5) goto L_0x0016;
    L_0x0055:
        r2 = com.google.a.h.a.e.a.ALPHA;
        goto L_0x0016;
    L_0x0058:
        r5 = 26;
        if (r4 >= r5) goto L_0x0060;
    L_0x005c:
        r0 = r4 + 97;
        r0 = (char) r0;
        goto L_0x0016;
    L_0x0060:
        r5 = 26;
        if (r4 != r5) goto L_0x0067;
    L_0x0064:
        r0 = 32;
        goto L_0x0016;
    L_0x0067:
        r5 = 27;
        if (r4 != r5) goto L_0x0071;
    L_0x006b:
        r1 = com.google.a.h.a.e.a.ALPHA_SHIFT;
        r7 = r2;
        r2 = r1;
        r1 = r7;
        goto L_0x0016;
    L_0x0071:
        r5 = 28;
        if (r4 != r5) goto L_0x0078;
    L_0x0075:
        r2 = com.google.a.h.a.e.a.MIXED;
        goto L_0x0016;
    L_0x0078:
        r5 = 29;
        if (r4 != r5) goto L_0x0082;
    L_0x007c:
        r1 = com.google.a.h.a.e.a.PUNCT_SHIFT;
        r7 = r2;
        r2 = r1;
        r1 = r7;
        goto L_0x0016;
    L_0x0082:
        r5 = 913; // 0x391 float:1.28E-42 double:4.51E-321;
        if (r4 != r5) goto L_0x008d;
    L_0x0086:
        r4 = r9[r3];
        r4 = (char) r4;
        r11.append(r4);
        goto L_0x0016;
    L_0x008d:
        r5 = 900; // 0x384 float:1.261E-42 double:4.447E-321;
        if (r4 != r5) goto L_0x0016;
    L_0x0091:
        r2 = com.google.a.h.a.e.a.ALPHA;
        goto L_0x0016;
    L_0x0094:
        r5 = 25;
        if (r4 >= r5) goto L_0x009e;
    L_0x0098:
        r0 = r;
        r0 = r0[r4];
        goto L_0x0016;
    L_0x009e:
        r5 = 25;
        if (r4 != r5) goto L_0x00a6;
    L_0x00a2:
        r2 = com.google.a.h.a.e.a.PUNCT;
        goto L_0x0016;
    L_0x00a6:
        r5 = 26;
        if (r4 != r5) goto L_0x00ae;
    L_0x00aa:
        r0 = 32;
        goto L_0x0016;
    L_0x00ae:
        r5 = 27;
        if (r4 != r5) goto L_0x00b6;
    L_0x00b2:
        r2 = com.google.a.h.a.e.a.LOWER;
        goto L_0x0016;
    L_0x00b6:
        r5 = 28;
        if (r4 != r5) goto L_0x00be;
    L_0x00ba:
        r2 = com.google.a.h.a.e.a.ALPHA;
        goto L_0x0016;
    L_0x00be:
        r5 = 29;
        if (r4 != r5) goto L_0x00c9;
    L_0x00c2:
        r1 = com.google.a.h.a.e.a.PUNCT_SHIFT;
        r7 = r2;
        r2 = r1;
        r1 = r7;
        goto L_0x0016;
    L_0x00c9:
        r5 = 913; // 0x391 float:1.28E-42 double:4.51E-321;
        if (r4 != r5) goto L_0x00d5;
    L_0x00cd:
        r4 = r9[r3];
        r4 = (char) r4;
        r11.append(r4);
        goto L_0x0016;
    L_0x00d5:
        r5 = 900; // 0x384 float:1.261E-42 double:4.447E-321;
        if (r4 != r5) goto L_0x0016;
    L_0x00d9:
        r2 = com.google.a.h.a.e.a.ALPHA;
        goto L_0x0016;
    L_0x00dd:
        r5 = 29;
        if (r4 >= r5) goto L_0x00e7;
    L_0x00e1:
        r0 = q;
        r0 = r0[r4];
        goto L_0x0016;
    L_0x00e7:
        r5 = 29;
        if (r4 != r5) goto L_0x00ef;
    L_0x00eb:
        r2 = com.google.a.h.a.e.a.ALPHA;
        goto L_0x0016;
    L_0x00ef:
        r5 = 913; // 0x391 float:1.28E-42 double:4.51E-321;
        if (r4 != r5) goto L_0x00fb;
    L_0x00f3:
        r4 = r9[r3];
        r4 = (char) r4;
        r11.append(r4);
        goto L_0x0016;
    L_0x00fb:
        r5 = 900; // 0x384 float:1.261E-42 double:4.447E-321;
        if (r4 != r5) goto L_0x0016;
    L_0x00ff:
        r2 = com.google.a.h.a.e.a.ALPHA;
        goto L_0x0016;
    L_0x0103:
        r2 = 26;
        if (r4 >= r2) goto L_0x010d;
    L_0x0107:
        r0 = r4 + 65;
        r0 = (char) r0;
        r2 = r1;
        goto L_0x0016;
    L_0x010d:
        r2 = 26;
        if (r4 != r2) goto L_0x0116;
    L_0x0111:
        r0 = 32;
        r2 = r1;
        goto L_0x0016;
    L_0x0116:
        r2 = 900; // 0x384 float:1.261E-42 double:4.447E-321;
        if (r4 != r2) goto L_0x0147;
    L_0x011a:
        r2 = com.google.a.h.a.e.a.ALPHA;
        goto L_0x0016;
    L_0x011e:
        r2 = 29;
        if (r4 >= r2) goto L_0x0129;
    L_0x0122:
        r0 = q;
        r0 = r0[r4];
        r2 = r1;
        goto L_0x0016;
    L_0x0129:
        r2 = 29;
        if (r4 != r2) goto L_0x0131;
    L_0x012d:
        r2 = com.google.a.h.a.e.a.ALPHA;
        goto L_0x0016;
    L_0x0131:
        r2 = 913; // 0x391 float:1.28E-42 double:4.51E-321;
        if (r4 != r2) goto L_0x013e;
    L_0x0135:
        r2 = r9[r3];
        r2 = (char) r2;
        r11.append(r2);
        r2 = r1;
        goto L_0x0016;
    L_0x013e:
        r2 = 900; // 0x384 float:1.261E-42 double:4.447E-321;
        if (r4 != r2) goto L_0x0147;
    L_0x0142:
        r2 = com.google.a.h.a.e.a.ALPHA;
        goto L_0x0016;
    L_0x0146:
        return;
    L_0x0147:
        r2 = r1;
        goto L_0x0016;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.a.h.a.e.a(int[], int[], int, java.lang.StringBuilder):void");
    }

    private static int a(int i, int[] iArr, int i2, StringBuilder stringBuilder) {
        int i3;
        long j;
        int i4;
        int i5;
        if (i == b) {
            i3 = 0;
            j = 0;
            char[] cArr = new char[6];
            int[] iArr2 = new int[6];
            Object obj = null;
            i4 = i2 + 1;
            int i6 = iArr[i2];
            int i7 = i4;
            while (i7 < iArr[0] && r1 == null) {
                i4 = i3 + 1;
                iArr2[i3] = i6;
                j = (j * 900) + ((long) i6);
                int i8 = i7 + 1;
                i6 = iArr[i7];
                if (i6 == a || i6 == b || i6 == c || i6 == d || i6 == 928 || i6 == f || i6 == g) {
                    obj = 1;
                    i7 = i8 - 1;
                    i3 = i4;
                } else if (i4 % 5 != 0 || i4 <= 0) {
                    i3 = i4;
                    i7 = i8;
                } else {
                    i3 = 0;
                    while (i3 < 6) {
                        cArr[5 - i3] = (char) ((int) (j % 256));
                        i3++;
                        j >>= 8;
                    }
                    stringBuilder.append(cArr);
                    i3 = 0;
                    i7 = i8;
                }
            }
            if (i7 == iArr[0] && i6 < a) {
                i5 = i3 + 1;
                iArr2[i3] = i6;
                i3 = i5;
            }
            for (i6 = 0; i6 < i3; i6++) {
                stringBuilder.append((char) iArr2[i6]);
            }
            return i7;
        } else if (i != d) {
            return i2;
        } else {
            i5 = 0;
            j = 0;
            Object obj2 = null;
            while (i2 < iArr[0] && r0 == null) {
                i3 = i2 + 1;
                i4 = iArr[i2];
                if (i4 < a) {
                    i5++;
                    j = (j * 900) + ((long) i4);
                    i2 = i3;
                } else if (i4 == a || i4 == b || i4 == c || i4 == d || i4 == 928 || i4 == f || i4 == g) {
                    i2 = i3 - 1;
                    obj2 = 1;
                } else {
                    i2 = i3;
                }
                if (i5 % 5 == 0 && i5 > 0) {
                    char[] cArr2 = new char[6];
                    for (i5 = 0; i5 < 6; i5++) {
                        cArr2[5 - i5] = (char) ((int) (255 & j));
                        j >>= 8;
                    }
                    stringBuilder.append(cArr2);
                    i5 = 0;
                }
            }
            return i2;
        }
    }

    private static int b(int[] iArr, int i, StringBuilder stringBuilder) throws h {
        int[] iArr2 = new int[15];
        int i2 = 0;
        int i3 = 0;
        while (i < iArr[0] && r0 == 0) {
            int i4 = i + 1;
            int i5 = iArr[i];
            if (i4 == iArr[0]) {
                i2 = 1;
            }
            if (i5 < a) {
                iArr2[i3] = i5;
                i3++;
                i = i4;
            } else if (i5 == a || i5 == b || i5 == d || i5 == 928 || i5 == f || i5 == g) {
                i = i4 - 1;
                i2 = 1;
            } else {
                i = i4;
            }
            if (i3 % 15 == 0 || i5 == c || r0 != 0) {
                stringBuilder.append(a(iArr2, i3));
                i3 = 0;
            }
        }
        return i;
    }

    private static String a(int[] iArr, int i) throws h {
        BigInteger bigInteger = BigInteger.ZERO;
        for (int i2 = 0; i2 < i; i2++) {
            bigInteger = bigInteger.add(s[(i - i2) - 1].multiply(BigInteger.valueOf((long) iArr[i2])));
        }
        String bigInteger2 = bigInteger.toString();
        if (bigInteger2.charAt(0) == '1') {
            return bigInteger2.substring(1);
        }
        throw h.a();
    }
}
