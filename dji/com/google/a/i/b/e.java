package com.google.a.i.b;

import com.google.a.m;
import com.google.a.t;
import com.google.a.u;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class e {
    protected static final int a = 3;
    protected static final int b = 57;
    private static final int c = 2;
    private static final int d = 8;
    private final com.google.a.c.b e;
    private final List<d> f;
    private boolean g;
    private final int[] h;
    private final u i;

    private static final class a implements Serializable, Comparator<d> {
        private final float a;

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((d) obj, (d) obj2);
        }

        private a(float f) {
            this.a = f;
        }

        public int a(d dVar, d dVar2) {
            if (dVar2.d() != dVar.d()) {
                return dVar2.d() - dVar.d();
            }
            float abs = Math.abs(dVar2.c() - this.a);
            float abs2 = Math.abs(dVar.c() - this.a);
            if (abs < abs2) {
                return 1;
            }
            return abs == abs2 ? 0 : -1;
        }
    }

    private static final class b implements Serializable, Comparator<d> {
        private final float a;

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((d) obj, (d) obj2);
        }

        private b(float f) {
            this.a = f;
        }

        public int a(d dVar, d dVar2) {
            float abs = Math.abs(dVar2.c() - this.a);
            float abs2 = Math.abs(dVar.c() - this.a);
            if (abs < abs2) {
                return -1;
            }
            return abs == abs2 ? 0 : 1;
        }
    }

    public e(com.google.a.c.b bVar) {
        this(bVar, null);
    }

    public e(com.google.a.c.b bVar, u uVar) {
        this.e = bVar;
        this.f = new ArrayList();
        this.h = new int[5];
        this.i = uVar;
    }

    protected final com.google.a.c.b a() {
        return this.e;
    }

    protected final List<d> b() {
        return this.f;
    }

    final f b(Map<com.google.a.e, ?> map) throws m {
        Object obj = (map == null || !map.containsKey(com.google.a.e.TRY_HARDER)) ? null : 1;
        boolean z = map != null && map.containsKey(com.google.a.e.PURE_BARCODE);
        int g = this.e.g();
        int f = this.e.f();
        int i = (g * 3) / 228;
        if (i < 3 || obj != null) {
            i = 3;
        }
        boolean z2 = false;
        int[] iArr = new int[5];
        int i2 = i - 1;
        int i3 = i;
        while (i2 < g && !r4) {
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            iArr[3] = 0;
            iArr[4] = 0;
            i = 0;
            int i4 = 0;
            while (i4 < f) {
                if (this.e.a(i4, i2)) {
                    if ((i & 1) == 1) {
                        i++;
                    }
                    iArr[i] = iArr[i] + 1;
                } else if ((i & 1) != 0) {
                    iArr[i] = iArr[i] + 1;
                } else if (i != 4) {
                    i++;
                    iArr[i] = iArr[i] + 1;
                } else if (!a(iArr)) {
                    iArr[0] = iArr[2];
                    iArr[1] = iArr[3];
                    iArr[2] = iArr[4];
                    iArr[3] = 1;
                    iArr[4] = 0;
                    i = 3;
                } else if (a(iArr, i2, i4, z)) {
                    boolean e;
                    i3 = 2;
                    if (this.g) {
                        e = e();
                    } else {
                        i = d();
                        if (i > iArr[2]) {
                            i4 = i2 + ((i - iArr[2]) - 2);
                            i = f - 1;
                        } else {
                            i = i4;
                            i4 = i2;
                        }
                        i2 = i4;
                        i4 = i;
                        e = z2;
                    }
                    iArr[0] = 0;
                    iArr[1] = 0;
                    iArr[2] = 0;
                    iArr[3] = 0;
                    iArr[4] = 0;
                    z2 = e;
                    i = 0;
                } else {
                    iArr[0] = iArr[2];
                    iArr[1] = iArr[3];
                    iArr[2] = iArr[4];
                    iArr[3] = 1;
                    iArr[4] = 0;
                    i = 3;
                }
                i4++;
            }
            if (a(iArr) && a(iArr, i2, f, z)) {
                i3 = iArr[0];
                if (this.g) {
                    z2 = e();
                }
            }
            i2 += i3;
        }
        t[] f2 = f();
        t.a(f2);
        return new f(f2);
    }

    private static float a(int[] iArr, int i) {
        return ((float) ((i - iArr[4]) - iArr[3])) - (((float) iArr[2]) / 2.0f);
    }

    protected static boolean a(int[] iArr) {
        int i;
        boolean z = true;
        int i2 = 0;
        for (i = 0; i < 5; i++) {
            int i3 = iArr[i];
            if (i3 == 0) {
                return false;
            }
            i2 += i3;
        }
        if (i2 < 7) {
            return false;
        }
        i = (i2 << 8) / 7;
        i2 = i / 2;
        if (Math.abs(i - (iArr[0] << 8)) >= i2 || Math.abs(i - (iArr[1] << 8)) >= i2 || Math.abs((i * 3) - (iArr[2] << 8)) >= i2 * 3 || Math.abs(i - (iArr[3] << 8)) >= i2 || Math.abs(i - (iArr[4] << 8)) >= i2) {
            z = false;
        }
        return z;
    }

    private int[] c() {
        this.h[0] = 0;
        this.h[1] = 0;
        this.h[2] = 0;
        this.h[3] = 0;
        this.h[4] = 0;
        return this.h;
    }

    private boolean a(int i, int i2, int i3, int i4) {
        int g = this.e.g();
        int f = this.e.f();
        int[] c = c();
        int i5 = 0;
        while (i - i5 >= 0 && this.e.a(i2 - i5, i - i5)) {
            c[2] = c[2] + 1;
            i5++;
        }
        if (i - i5 < 0 || i2 - i5 < 0) {
            return false;
        }
        while (i - i5 >= 0 && i2 - i5 >= 0 && !this.e.a(i2 - i5, i - i5) && c[1] <= i3) {
            c[1] = c[1] + 1;
            i5++;
        }
        if (i - i5 < 0 || i2 - i5 < 0 || c[1] > i3) {
            return false;
        }
        while (i - i5 >= 0 && i2 - i5 >= 0 && this.e.a(i2 - i5, i - i5) && c[0] <= i3) {
            c[0] = c[0] + 1;
            i5++;
        }
        if (c[0] > i3) {
            return false;
        }
        i5 = 1;
        while (i + i5 < g && i2 + i5 < f && this.e.a(i2 + i5, i + i5)) {
            c[2] = c[2] + 1;
            i5++;
        }
        if (i + i5 >= g || i2 + i5 >= f) {
            return false;
        }
        while (i + i5 < g && i2 + i5 < f && !this.e.a(i2 + i5, i + i5) && c[3] < i3) {
            c[3] = c[3] + 1;
            i5++;
        }
        if (i + i5 >= g || i2 + i5 >= f || c[3] >= i3) {
            return false;
        }
        while (i + i5 < g && i2 + i5 < f && this.e.a(i2 + i5, i + i5) && c[4] < i3) {
            c[4] = c[4] + 1;
            i5++;
        }
        if (c[4] >= i3) {
            return false;
        }
        return Math.abs(((((c[0] + c[1]) + c[2]) + c[3]) + c[4]) - i4) < i4 * 2 && a(c);
    }

    private float b(int i, int i2, int i3, int i4) {
        com.google.a.c.b bVar = this.e;
        int g = bVar.g();
        int[] c = c();
        int i5 = i;
        while (i5 >= 0 && bVar.a(i2, i5)) {
            c[2] = c[2] + 1;
            i5--;
        }
        if (i5 < 0) {
            return Float.NaN;
        }
        while (i5 >= 0 && !bVar.a(i2, i5) && c[1] <= i3) {
            c[1] = c[1] + 1;
            i5--;
        }
        if (i5 < 0 || c[1] > i3) {
            return Float.NaN;
        }
        while (i5 >= 0 && bVar.a(i2, i5) && c[0] <= i3) {
            c[0] = c[0] + 1;
            i5--;
        }
        if (c[0] > i3) {
            return Float.NaN;
        }
        i5 = i + 1;
        while (i5 < g && bVar.a(i2, i5)) {
            c[2] = c[2] + 1;
            i5++;
        }
        if (i5 == g) {
            return Float.NaN;
        }
        while (i5 < g && !bVar.a(i2, i5) && c[3] < i3) {
            c[3] = c[3] + 1;
            i5++;
        }
        if (i5 == g || c[3] >= i3) {
            return Float.NaN;
        }
        while (i5 < g && bVar.a(i2, i5) && c[4] < i3) {
            c[4] = c[4] + 1;
            i5++;
        }
        if (c[4] >= i3 || Math.abs(((((c[0] + c[1]) + c[2]) + c[3]) + c[4]) - i4) * 5 >= i4 * 2 || !a(c)) {
            return Float.NaN;
        }
        return a(c, i5);
    }

    private float c(int i, int i2, int i3, int i4) {
        com.google.a.c.b bVar = this.e;
        int f = bVar.f();
        int[] c = c();
        int i5 = i;
        while (i5 >= 0 && bVar.a(i5, i2)) {
            c[2] = c[2] + 1;
            i5--;
        }
        if (i5 < 0) {
            return Float.NaN;
        }
        while (i5 >= 0 && !bVar.a(i5, i2) && c[1] <= i3) {
            c[1] = c[1] + 1;
            i5--;
        }
        if (i5 < 0 || c[1] > i3) {
            return Float.NaN;
        }
        while (i5 >= 0 && bVar.a(i5, i2) && c[0] <= i3) {
            c[0] = c[0] + 1;
            i5--;
        }
        if (c[0] > i3) {
            return Float.NaN;
        }
        i5 = i + 1;
        while (i5 < f && bVar.a(i5, i2)) {
            c[2] = c[2] + 1;
            i5++;
        }
        if (i5 == f) {
            return Float.NaN;
        }
        while (i5 < f && !bVar.a(i5, i2) && c[3] < i3) {
            c[3] = c[3] + 1;
            i5++;
        }
        if (i5 == f || c[3] >= i3) {
            return Float.NaN;
        }
        while (i5 < f && bVar.a(i5, i2) && c[4] < i3) {
            c[4] = c[4] + 1;
            i5++;
        }
        if (c[4] >= i3 || Math.abs(((((c[0] + c[1]) + c[2]) + c[3]) + c[4]) - i4) * 5 >= i4 || !a(c)) {
            return Float.NaN;
        }
        return a(c, i5);
    }

    protected final boolean a(int[] iArr, int i, int i2, boolean z) {
        boolean z2 = false;
        int i3 = (((iArr[0] + iArr[1]) + iArr[2]) + iArr[3]) + iArr[4];
        float a = a(iArr, i2);
        float b = b(i, (int) a, iArr[2], i3);
        if (Float.isNaN(b)) {
            return false;
        }
        float c = c((int) a, (int) b, iArr[2], i3);
        if (Float.isNaN(c)) {
            return false;
        }
        if (z && !a((int) b, (int) c, iArr[2], i3)) {
            return false;
        }
        float f = ((float) i3) / 7.0f;
        for (int i4 = 0; i4 < this.f.size(); i4++) {
            d dVar = (d) this.f.get(i4);
            if (dVar.a(f, b, c)) {
                this.f.set(i4, dVar.b(b, c, f));
                z2 = true;
                break;
            }
        }
        if (!z2) {
            t dVar2 = new d(c, b, f);
            this.f.add(dVar2);
            if (this.i != null) {
                this.i.a(dVar2);
            }
        }
        return true;
    }

    private int d() {
        if (this.f.size() <= 1) {
            return 0;
        }
        t tVar = null;
        for (t tVar2 : this.f) {
            t tVar22;
            if (tVar22.d() < 2) {
                tVar22 = tVar;
            } else if (tVar != null) {
                this.g = true;
                return ((int) (Math.abs(tVar.a() - tVar22.a()) - Math.abs(tVar.b() - tVar22.b()))) / 2;
            }
            tVar = tVar22;
        }
        return 0;
    }

    private boolean e() {
        float f = 0.0f;
        int size = this.f.size();
        float f2 = 0.0f;
        int i = 0;
        for (d dVar : this.f) {
            float c;
            int i2;
            if (dVar.d() >= 2) {
                c = dVar.c() + f2;
                i2 = i + 1;
            } else {
                c = f2;
                i2 = i;
            }
            i = i2;
            f2 = c;
        }
        if (i < 3) {
            return false;
        }
        float f3 = f2 / ((float) size);
        for (d dVar2 : this.f) {
            f += Math.abs(dVar2.c() - f3);
        }
        if (f <= 0.05f * f2) {
            return true;
        }
        return false;
    }

    private d[] f() throws m {
        float f = 0.0f;
        int size = this.f.size();
        if (size < 3) {
            throw m.a();
        }
        if (size > 3) {
            float c;
            float f2 = 0.0f;
            float f3 = 0.0f;
            for (d c2 : this.f) {
                c = c2.c();
                f3 += c;
                f2 = (c * c) + f2;
            }
            f3 /= (float) size;
            c = (float) Math.sqrt((double) ((f2 / ((float) size)) - (f3 * f3)));
            Collections.sort(this.f, new b(f3));
            float max = Math.max(0.2f * f3, c);
            int i = 0;
            while (i < this.f.size() && this.f.size() > 3) {
                if (Math.abs(((d) this.f.get(i)).c() - f3) > max) {
                    this.f.remove(i);
                    i--;
                }
                i++;
            }
        }
        if (this.f.size() > 3) {
            for (d c22 : this.f) {
                f += c22.c();
            }
            Collections.sort(this.f, new a(f / ((float) this.f.size())));
            this.f.subList(3, this.f.size()).clear();
        }
        return new d[]{(d) this.f.get(0), (d) this.f.get(1), (d) this.f.get(2)};
    }
}
