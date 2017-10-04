package com.google.a.g;

import com.google.a.c.a;
import com.google.a.e;
import com.google.a.h;
import com.google.a.r;
import com.google.a.t;
import java.util.Map;

public final class m extends q {
    static final int[][] a = new int[][]{new int[]{1, 1, 3, 3, 1}, new int[]{3, 1, 1, 1, 3}, new int[]{1, 3, 1, 1, 3}, new int[]{3, 3, 1, 1, 1}, new int[]{1, 1, 3, 1, 3}, new int[]{3, 1, 3, 1, 1}, new int[]{1, 3, 3, 1, 1}, new int[]{1, 1, 1, 3, 3}, new int[]{3, 1, 1, 3, 1}, new int[]{1, 3, 1, 3, 1}};
    private static final int b = 107;
    private static final int e = 199;
    private static final int f = 3;
    private static final int g = 1;
    private static final int[] h = new int[]{6, 8, 10, 12, 14};
    private static final int[] j = new int[]{1, 1, 1, 1};
    private static final int[] k = new int[]{1, 1, 3};
    private int i = -1;

    public r a(int i, a aVar, Map<e, ?> map) throws h, com.google.a.m {
        int[] iArr;
        int i2;
        int[] a = a(aVar);
        int[] b = b(aVar);
        StringBuilder stringBuilder = new StringBuilder(20);
        a(aVar, a[1], b[0], stringBuilder);
        String stringBuilder2 = stringBuilder.toString();
        if (map != null) {
            iArr = (int[]) map.get(e.ALLOWED_LENGTHS);
        } else {
            iArr = null;
        }
        if (iArr == null) {
            iArr = h;
        }
        int length = stringBuilder2.length();
        int length2 = iArr.length;
        int i3 = 0;
        int i4 = 0;
        while (i3 < length2) {
            int i5 = iArr[i3];
            if (length == i5) {
                i2 = 1;
                break;
            }
            if (i5 <= i4) {
                i5 = i4;
            }
            i3++;
            i4 = i5;
        }
        i2 = 0;
        if (i2 == 0 && length > i4) {
            i2 = 1;
        }
        if (i2 == 0) {
            throw h.a();
        }
        return new r(stringBuilder2, null, new t[]{new t((float) a[1], (float) i), new t((float) b[0], (float) i)}, com.google.a.a.ITF);
    }

    private static void a(a aVar, int i, int i2, StringBuilder stringBuilder) throws com.google.a.m {
        int[] iArr = new int[10];
        int[] iArr2 = new int[5];
        int[] iArr3 = new int[5];
        int i3 = i;
        while (i3 < i2) {
            int i4;
            q.a(aVar, i3, iArr);
            for (i4 = 0; i4 < 5; i4++) {
                int i5 = i4 << 1;
                iArr2[i4] = iArr[i5];
                iArr3[i4] = iArr[i5 + 1];
            }
            stringBuilder.append((char) (a(iArr2) + 48));
            stringBuilder.append((char) (a(iArr3) + 48));
            i4 = i3;
            for (int i6 : iArr) {
                i4 += i6;
            }
            i3 = i4;
        }
    }

    int[] a(a aVar) throws com.google.a.m {
        int[] c = c(aVar, c(aVar), j);
        this.i = (c[1] - c[0]) >> 2;
        a(aVar, c[0]);
        return c;
    }

    private void a(a aVar, int i) throws com.google.a.m {
        int i2 = this.i * 10;
        if (i2 >= i) {
            i2 = i;
        }
        int i3 = i2;
        i2 = i - 1;
        while (i3 > 0 && i2 >= 0 && !aVar.a(i2)) {
            i3--;
            i2--;
        }
        if (i3 != 0) {
            throw com.google.a.m.a();
        }
    }

    private static int c(a aVar) throws com.google.a.m {
        int a = aVar.a();
        int d = aVar.d(0);
        if (d != a) {
            return d;
        }
        throw com.google.a.m.a();
    }

    int[] b(a aVar) throws com.google.a.m {
        aVar.e();
        try {
            int[] c = c(aVar, c(aVar), k);
            a(aVar, c[0]);
            int i = c[0];
            c[0] = aVar.a() - c[1];
            c[1] = aVar.a() - i;
            return c;
        } finally {
            aVar.e();
        }
    }

    private static int[] c(a aVar, int i, int[] iArr) throws com.google.a.m {
        int length = iArr.length;
        Object obj = new int[length];
        int a = aVar.a();
        int i2 = i;
        int i3 = 0;
        int i4 = 0;
        while (i < a) {
            if ((aVar.a(i) ^ i4) != 0) {
                obj[i3] = obj[i3] + 1;
            } else {
                if (i3 != length - 1) {
                    i3++;
                } else if (q.a((int[]) obj, iArr, (int) e) < 107) {
                    return new int[]{i2, i};
                } else {
                    i2 += obj[0] + obj[1];
                    System.arraycopy(obj, 2, obj, 0, length - 2);
                    obj[length - 2] = null;
                    obj[length - 1] = null;
                    i3--;
                }
                obj[i3] = 1;
                if (i4 == 0) {
                    i4 = 1;
                } else {
                    i4 = 0;
                }
            }
            i++;
        }
        throw com.google.a.m.a();
    }

    private static int a(int[] iArr) throws com.google.a.m {
        int i = 107;
        int i2 = -1;
        int length = a.length;
        int i3 = 0;
        while (i3 < length) {
            int a = q.a(iArr, a[i3], (int) e);
            if (a < i) {
                i2 = i3;
            } else {
                a = i;
            }
            i3++;
            i = a;
        }
        if (i2 >= 0) {
            return i2;
        }
        throw com.google.a.m.a();
    }
}
