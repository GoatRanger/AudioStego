package com.google.a.g.a;

import com.google.a.g.q;
import com.google.a.m;

public abstract class a extends q {
    private static final int a = 51;
    private static final int b = 115;
    private static final float e = 0.7916667f;
    private static final float f = 0.89285713f;
    private final int[] g = new int[4];
    private final int[] h = new int[8];
    private final float[] i = new float[4];
    private final float[] j = new float[4];
    private final int[] k = new int[(this.h.length / 2)];
    private final int[] l = new int[(this.h.length / 2)];

    protected a() {
    }

    protected final int[] b() {
        return this.g;
    }

    protected final int[] c() {
        return this.h;
    }

    protected final float[] d() {
        return this.i;
    }

    protected final float[] e() {
        return this.j;
    }

    protected final int[] f() {
        return this.k;
    }

    protected final int[] g() {
        return this.l;
    }

    protected static int a(int[] iArr, int[][] iArr2) throws m {
        for (int i = 0; i < iArr2.length; i++) {
            if (q.a(iArr, iArr2[i], (int) b) < 51) {
                return i;
            }
        }
        throw m.a();
    }

    protected static int a(int[] iArr) {
        int i = 0;
        int i2 = 0;
        while (i < iArr.length) {
            i2 += iArr[i];
            i++;
        }
        return i2;
    }

    protected static void a(int[] iArr, float[] fArr) {
        int i = 0;
        float f = fArr[0];
        for (int i2 = 1; i2 < iArr.length; i2++) {
            if (fArr[i2] > f) {
                f = fArr[i2];
                i = i2;
            }
        }
        iArr[i] = iArr[i] + 1;
    }

    protected static void b(int[] iArr, float[] fArr) {
        int i = 0;
        float f = fArr[0];
        for (int i2 = 1; i2 < iArr.length; i2++) {
            if (fArr[i2] < f) {
                f = fArr[i2];
                i = i2;
            }
        }
        iArr[i] = iArr[i] - 1;
    }

    protected static boolean b(int[] iArr) {
        int i = iArr[0] + iArr[1];
        float f = ((float) i) / ((float) ((iArr[2] + i) + iArr[3]));
        if (f < e || f > f) {
            return false;
        }
        int i2 = Integer.MAX_VALUE;
        int i3 = Integer.MIN_VALUE;
        int length = iArr.length;
        int i4 = 0;
        while (i4 < length) {
            i = iArr[i4];
            if (i > i3) {
                i3 = i;
            }
            if (i >= i2) {
                i = i2;
            }
            i4++;
            i2 = i;
        }
        if (i3 < i2 * 10) {
            return true;
        }
        return false;
    }
}
