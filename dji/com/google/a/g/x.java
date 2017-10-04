package com.google.a.g;

import com.google.a.c.a;
import com.google.a.d;
import com.google.a.e;
import com.google.a.h;
import com.google.a.m;
import com.google.a.q;
import com.google.a.r;
import com.google.a.s;
import com.google.a.t;
import com.google.a.u;
import java.util.Arrays;
import java.util.Map;

public abstract class x extends q {
    private static final int a = 122;
    static final int[] b = new int[]{1, 1, 1};
    static final int[] e = new int[]{1, 1, 1, 1, 1};
    static final int[][] f = new int[][]{new int[]{3, 2, 1, 1}, new int[]{2, 2, 2, 1}, new int[]{2, 1, 2, 2}, new int[]{1, 4, 1, 1}, new int[]{1, 1, 3, 2}, new int[]{1, 2, 3, 1}, new int[]{1, 1, 1, 4}, new int[]{1, 3, 1, 2}, new int[]{1, 2, 1, 3}, new int[]{3, 1, 1, 2}};
    static final int[][] g = new int[20][];
    private static final int h = 179;
    private final StringBuilder i = new StringBuilder(20);
    private final w j = new w();
    private final l k = new l();

    protected abstract int a(a aVar, int[] iArr, StringBuilder stringBuilder) throws m;

    abstract com.google.a.a b();

    static {
        System.arraycopy(f, 0, g, 0, 10);
        for (int i = 10; i < 20; i++) {
            int[] iArr = f[i - 10];
            int[] iArr2 = new int[iArr.length];
            for (int i2 = 0; i2 < iArr.length; i2++) {
                iArr2[i2] = iArr[(iArr.length - i2) - 1];
            }
            g[i] = iArr2;
        }
    }

    protected x() {
    }

    static int[] a(a aVar) throws m {
        int[] iArr = new int[b.length];
        int i = 0;
        int[] iArr2 = null;
        boolean z = false;
        while (!z) {
            Arrays.fill(iArr, 0, b.length, 0);
            iArr2 = a(aVar, i, false, b, iArr);
            int i2 = iArr2[0];
            i = iArr2[1];
            int i3 = i2 - (i - i2);
            if (i3 >= 0) {
                z = aVar.a(i3, i2, false);
            }
        }
        return iArr2;
    }

    public r a(int i, a aVar, Map<e, ?> map) throws m, d, h {
        return a(i, aVar, a(aVar), (Map) map);
    }

    public r a(int i, a aVar, int[] iArr, Map<e, ?> map) throws m, d, h {
        u uVar;
        if (map == null) {
            uVar = null;
        } else {
            uVar = (u) map.get(e.NEED_RESULT_POINT_CALLBACK);
        }
        if (uVar != null) {
            uVar.a(new t(((float) (iArr[0] + iArr[1])) / 2.0f, (float) i));
        }
        StringBuilder stringBuilder = this.i;
        stringBuilder.setLength(0);
        int a = a(aVar, iArr, stringBuilder);
        if (uVar != null) {
            uVar.a(new t((float) a, (float) i));
        }
        int[] a2 = a(aVar, a);
        if (uVar != null) {
            uVar.a(new t(((float) (a2[0] + a2[1])) / 2.0f, (float) i));
        }
        int i2 = a2[1];
        int i3 = (i2 - a2[0]) + i2;
        if (i3 >= aVar.a() || !aVar.a(i2, i3, false)) {
            throw m.a();
        }
        String stringBuilder2 = stringBuilder.toString();
        if (stringBuilder2.length() < 8) {
            throw h.a();
        } else if (a(stringBuilder2)) {
            float f = ((float) (iArr[1] + iArr[0])) / 2.0f;
            float f2 = ((float) (a2[1] + a2[0])) / 2.0f;
            com.google.a.a b = b();
            r rVar = new r(stringBuilder2, null, new t[]{new t(f, (float) i), new t(f2, (float) i)}, b);
            i2 = 0;
            try {
                r a3 = this.j.a(i, aVar, a2[1]);
                rVar.a(s.UPC_EAN_EXTENSION, a3.a());
                rVar.a(a3.e());
                rVar.a(a3.c());
                i3 = a3.a().length();
            } catch (q e) {
                i3 = i2;
            }
            a2 = map == null ? null : (int[]) map.get(e.ALLOWED_EAN_EXTENSIONS);
            if (a2 != null) {
                Object obj = null;
                for (int i4 : a2) {
                    if (i3 == i4) {
                        obj = 1;
                        break;
                    }
                }
                if (obj == null) {
                    throw m.a();
                }
            }
            if (b == com.google.a.a.EAN_13 || b == com.google.a.a.UPC_A) {
                String a4 = this.k.a(stringBuilder2);
                if (a4 != null) {
                    rVar.a(s.POSSIBLE_COUNTRY, a4);
                }
            }
            return rVar;
        } else {
            throw d.a();
        }
    }

    boolean a(String str) throws d, h {
        return a((CharSequence) str);
    }

    static boolean a(CharSequence charSequence) throws h {
        int length = charSequence.length();
        if (length == 0) {
            return false;
        }
        int i;
        int i2 = 0;
        for (i = length - 2; i >= 0; i -= 2) {
            int charAt = charSequence.charAt(i) - 48;
            if (charAt < 0 || charAt > 9) {
                throw h.a();
            }
            i2 += charAt;
        }
        i2 *= 3;
        for (i = length - 1; i >= 0; i -= 2) {
            length = charSequence.charAt(i) - 48;
            if (length < 0 || length > 9) {
                throw h.a();
            }
            i2 += length;
        }
        if (i2 % 10 == 0) {
            return true;
        }
        return false;
    }

    int[] a(a aVar, int i) throws m {
        return a(aVar, i, false, b);
    }

    static int[] a(a aVar, int i, boolean z, int[] iArr) throws m {
        return a(aVar, i, z, iArr, new int[iArr.length]);
    }

    private static int[] a(a aVar, int i, boolean z, int[] iArr, int[] iArr2) throws m {
        int length = iArr.length;
        int a = aVar.a();
        int e = z ? aVar.e(i) : aVar.d(i);
        int i2 = 0;
        int i3 = z;
        for (int i4 = e; i4 < a; i4++) {
            if ((aVar.a(i4) ^ i3) != 0) {
                iArr2[i2] = iArr2[i2] + 1;
            } else {
                if (i2 != length - 1) {
                    i2++;
                } else if (q.a(iArr2, iArr, (int) h) < a) {
                    return new int[]{e, i4};
                } else {
                    e += iArr2[0] + iArr2[1];
                    System.arraycopy(iArr2, 2, iArr2, 0, length - 2);
                    iArr2[length - 2] = 0;
                    iArr2[length - 1] = 0;
                    i2--;
                }
                iArr2[i2] = 1;
                if (i3 == 0) {
                    i3 = 1;
                } else {
                    i3 = 0;
                }
            }
        }
        throw m.a();
    }

    static int a(a aVar, int[] iArr, int i, int[][] iArr2) throws m {
        q.a(aVar, i, iArr);
        int i2 = a;
        int i3 = -1;
        int length = iArr2.length;
        int i4 = 0;
        while (i4 < length) {
            int a = q.a(iArr, iArr2[i4], (int) h);
            if (a < i2) {
                i3 = i4;
            } else {
                a = i2;
            }
            i4++;
            i2 = a;
        }
        if (i3 >= 0) {
            return i3;
        }
        throw m.a();
    }
}
