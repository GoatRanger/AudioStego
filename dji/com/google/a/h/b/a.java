package com.google.a.h.b;

import com.google.a.c;
import com.google.a.c.b;
import com.google.a.e;
import com.google.a.m;
import com.google.a.t;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class a {
    private static final int[] a = new int[]{0, 4, 1, 5};
    private static final int[] b = new int[]{6, 2, 7, 3};
    private static final int c = 8;
    private static final int d = 256;
    private static final int e = 107;
    private static final int f = 204;
    private static final int[] g = new int[]{8, 1, 1, 1, 1, 1, 1, 3};
    private static final int[] h = new int[]{7, 1, 1, 3, 1, 1, 1, 2, 1};
    private static final int i = 3;
    private static final int j = 5;
    private static final int k = 25;
    private static final int l = 5;
    private static final int m = 10;

    private a() {
    }

    public static b a(c cVar, Map<e, ?> map, boolean z) throws m {
        b c = cVar.c();
        List a = a(z, c);
        if (a.isEmpty()) {
            c = c.h();
            c.b();
            a = a(z, c);
        }
        return new b(c, a);
    }

    private static List<t[]> a(boolean z, b bVar) {
        List<t[]> arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i3 < bVar.g()) {
            Object a = a(bVar, i3, i2);
            if (a[0] != null || a[3] != null) {
                arrayList.add(a);
                if (!z) {
                    break;
                }
                if (a[2] != null) {
                    i = (int) a[2].a();
                    i2 = (int) a[2].b();
                } else {
                    i = (int) a[4].a();
                    i2 = (int) a[4].b();
                }
                i3 = i2;
                i2 = i;
                i = 1;
            } else if (i == 0) {
                break;
            } else {
                for (t[] tVarArr : arrayList) {
                    if (tVarArr[1] != null) {
                        i3 = (int) Math.max((float) i3, tVarArr[1].b());
                    }
                    if (tVarArr[3] != null) {
                        i3 = Math.max(i3, (int) tVarArr[3].b());
                    }
                }
                i2 = 0;
                i3 += 5;
                i = 0;
            }
        }
        return arrayList;
    }

    private static t[] a(b bVar, int i, int i2) {
        int a;
        int b;
        int g = bVar.g();
        int f = bVar.f();
        t[] tVarArr = new t[8];
        a(tVarArr, a(bVar, g, f, i, i2, g), a);
        if (tVarArr[4] != null) {
            a = (int) tVarArr[4].a();
            b = (int) tVarArr[4].b();
        } else {
            a = i2;
            b = i;
        }
        a(tVarArr, a(bVar, g, f, b, a, h), b);
        return tVarArr;
    }

    private static void a(t[] tVarArr, t[] tVarArr2, int[] iArr) {
        for (int i = 0; i < iArr.length; i++) {
            tVarArr[iArr[i]] = tVarArr2[i];
        }
    }

    private static t[] a(b bVar, int i, int i2, int i3, int i4, int[] iArr) {
        int[] iArr2;
        int i5;
        Object obj;
        int i6;
        int i7;
        t[] tVarArr = new t[4];
        int[] iArr3 = new int[iArr.length];
        int i8 = i3;
        while (i8 < i) {
            int[] a = a(bVar, i4, i8, i2, false, iArr, iArr3);
            int i9;
            if (a != null) {
                iArr2 = a;
                i5 = i8;
                while (i5 > 0) {
                    i8 = i5 - 1;
                    a = a(bVar, i4, i8, i2, false, iArr, iArr3);
                    if (a == null) {
                        i5 = i8 + 1;
                        break;
                    }
                    iArr2 = a;
                    i5 = i8;
                }
                tVarArr[0] = new t((float) iArr2[0], (float) i5);
                tVarArr[1] = new t((float) iArr2[1], (float) i5);
                obj = 1;
                i6 = i5;
                i5 = i6 + 1;
                if (obj != null) {
                    iArr2 = new int[]{(int) tVarArr[0].a(), (int) tVarArr[1].a()};
                    i7 = 0;
                    i8 = i5;
                    while (i8 < i) {
                        a = a(bVar, iArr2[0], i8, i2, false, iArr, iArr3);
                        if (a == null && Math.abs(iArr2[0] - a[0]) < 5 && Math.abs(iArr2[1] - a[1]) < 5) {
                            i9 = 0;
                        } else if (i7 > 25) {
                            break;
                        } else {
                            i9 = i7 + 1;
                            a = iArr2;
                        }
                        i8++;
                        iArr2 = a;
                        i7 = i9;
                    }
                    i5 = i8 - (i7 + 1);
                    tVarArr[2] = new t((float) iArr2[0], (float) i5);
                    tVarArr[3] = new t((float) iArr2[1], (float) i5);
                }
                if (i5 - i6 < 10) {
                    for (i5 = 0; i5 < tVarArr.length; i5++) {
                        tVarArr[i5] = null;
                    }
                }
                return tVarArr;
            }
            i8 += 5;
        }
        obj = null;
        i6 = i8;
        i5 = i6 + 1;
        if (obj != null) {
            iArr2 = new int[]{(int) tVarArr[0].a(), (int) tVarArr[1].a()};
            i7 = 0;
            i8 = i5;
            while (i8 < i) {
                a = a(bVar, iArr2[0], i8, i2, false, iArr, iArr3);
                if (a == null) {
                }
                if (i7 > 25) {
                    break;
                }
                i9 = i7 + 1;
                a = iArr2;
                i8++;
                iArr2 = a;
                i7 = i9;
            }
            i5 = i8 - (i7 + 1);
            tVarArr[2] = new t((float) iArr2[0], (float) i5);
            tVarArr[3] = new t((float) iArr2[1], (float) i5);
        }
        if (i5 - i6 < 10) {
            for (i5 = 0; i5 < tVarArr.length; i5++) {
                tVarArr[i5] = null;
            }
        }
        return tVarArr;
    }

    private static int[] a(b bVar, int i, int i2, int i3, boolean z, int[] iArr, int[] iArr2) {
        int i4;
        Arrays.fill(iArr2, 0, iArr2.length, 0);
        int length = iArr.length;
        int i5 = 0;
        while (bVar.a(i, i2) && i > 0) {
            i4 = i5 + 1;
            if (i5 >= 3) {
                break;
            }
            i--;
            i5 = i4;
        }
        i5 = 0;
        i4 = i;
        int i6 = z;
        while (i < i3) {
            if ((bVar.a(i, i2) ^ i6) != 0) {
                iArr2[i5] = iArr2[i5] + 1;
            } else {
                if (i5 != length - 1) {
                    i5++;
                } else if (a(iArr2, iArr, 204) < 107) {
                    return new int[]{i4, i};
                } else {
                    i4 += iArr2[0] + iArr2[1];
                    System.arraycopy(iArr2, 2, iArr2, 0, length - 2);
                    iArr2[length - 2] = 0;
                    iArr2[length - 1] = 0;
                    i5--;
                }
                iArr2[i5] = 1;
                i6 = i6 == 0 ? 1 : 0;
            }
            i++;
        }
        if (i5 != length - 1 || a(iArr2, iArr, 204) >= 107) {
            return null;
        }
        return new int[]{i4, i - 1};
    }

    private static int a(int[] iArr, int[] iArr2, int i) {
        int i2;
        int length = iArr.length;
        int i3 = 0;
        int i4 = 0;
        for (i2 = 0; i2 < length; i2++) {
            i4 += iArr[i2];
            i3 += iArr2[i2];
        }
        if (i4 < i3) {
            return Integer.MAX_VALUE;
        }
        int i5 = (i4 << 8) / i3;
        int i6 = (i * i5) >> 8;
        i3 = 0;
        for (i2 = 0; i2 < length; i2++) {
            int i7 = iArr[i2] << 8;
            int i8 = iArr2[i2] * i5;
            i7 = i7 > i8 ? i7 - i8 : i8 - i7;
            if (i7 > i6) {
                return Integer.MAX_VALUE;
            }
            i3 += i7;
        }
        return i3 / i4;
    }
}
