package com.google.a.a.b;

import android.support.v4.media.TransportMediator;
import com.google.a.c.b;
import com.google.a.c.b.c;
import com.google.a.c.b.e;
import com.google.a.c.i;
import com.google.a.m;
import com.google.a.t;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.pilot.visual.a.d;

public final class a {
    private static final int[] g = new int[]{3808, 476, 2107, 1799};
    private final b a;
    private boolean b;
    private int c;
    private int d;
    private int e;
    private int f;

    static final class a {
        private final int a;
        private final int b;

        t a() {
            return new t((float) b(), (float) c());
        }

        a(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        int b() {
            return this.a;
        }

        int c() {
            return this.b;
        }

        public String toString() {
            return "<" + this.a + ' ' + this.b + '>';
        }
    }

    public a(b bVar) {
        this.a = bVar;
    }

    public com.google.a.a.a a() throws m {
        return a(false);
    }

    public com.google.a.a.a a(boolean z) throws m {
        t[] a = a(b());
        if (z) {
            t tVar = a[0];
            a[0] = a[2];
            a[2] = tVar;
        }
        a(a);
        return new com.google.a.a.a(a(this.a, a[this.f % 4], a[(this.f + 1) % 4], a[(this.f + 2) % 4], a[(this.f + 3) % 4]), b(a), this.b, this.d, this.c);
    }

    private void a(t[] tVarArr) throws m {
        if (a(tVarArr[0]) && a(tVarArr[1]) && a(tVarArr[2]) && a(tVarArr[3])) {
            int[] iArr = new int[]{a(tVarArr[0], tVarArr[1], r1), a(tVarArr[1], tVarArr[2], r1), a(tVarArr[2], tVarArr[3], r1), a(tVarArr[3], tVarArr[0], this.e * 2)};
            this.f = a(iArr, this.e * 2);
            long j = 0;
            for (int i = 0; i < 4; i++) {
                int i2 = iArr[(this.f + i) % 4];
                if (this.b) {
                    j = (j << 7) + ((long) ((i2 >> 1) & TransportMediator.KEYCODE_MEDIA_PAUSE));
                } else {
                    j = (j << 10) + ((long) (((i2 >> 1) & 31) + ((i2 >> 2) & 992)));
                }
            }
            int a = a(j, this.b);
            if (this.b) {
                this.c = (a >> 6) + 1;
                this.d = (a & 63) + 1;
                return;
            }
            this.c = (a >> 11) + 1;
            this.d = (a & 2047) + 1;
            return;
        }
        throw m.a();
    }

    private static int a(int[] iArr, int i) throws m {
        int i2 = 0;
        int i3 = 0;
        for (int i4 : iArr) {
            i3 += (i4 & 1) + ((i4 >> (i - 2)) << 1);
        }
        int i5 = ((i3 & 1) << 11) + (i3 >> 1);
        while (i2 < 4) {
            if (Integer.bitCount(g[i2] ^ i5) <= 2) {
                return i2;
            }
            i2++;
        }
        throw m.a();
    }

    private static int a(long j, boolean z) throws m {
        int i;
        int i2;
        int i3 = 0;
        if (z) {
            i = 7;
            i2 = 2;
        } else {
            i = 10;
            i2 = 4;
        }
        int i4 = i - i2;
        int[] iArr = new int[i];
        for (i--; i >= 0; i--) {
            iArr[i] = ((int) j) & 15;
            j >>= 4;
        }
        try {
            new c(com.google.a.c.b.a.d).a(iArr, i4);
            for (int i5 = 0; i5 < i2; i5++) {
                i3 = iArr[i5] + (i3 << 4);
            }
            return i3;
        } catch (e e) {
            throw m.a();
        }
    }

    private t[] a(a aVar) throws m {
        boolean z = true;
        this.e = 1;
        a aVar2 = aVar;
        a aVar3 = aVar;
        a aVar4 = aVar;
        while (this.e < 9) {
            a a = a(aVar4, z, 1, -1);
            a a2 = a(aVar3, z, 1, 1);
            a a3 = a(aVar2, z, -1, 1);
            a a4 = a(aVar, z, -1, -1);
            if (this.e > 2) {
                float b = (b(a4, a) * ((float) this.e)) / (b(aVar, aVar4) * ((float) (this.e + 2)));
                if (((double) b) >= 0.75d) {
                    if (((double) b) <= 1.25d) {
                        if (!a(a, a2, a3, a4)) {
                            break;
                        }
                    }
                    break;
                }
                break;
            }
            if (z) {
                z = false;
            } else {
                z = true;
            }
            this.e++;
            aVar = a4;
            aVar2 = a3;
            aVar3 = a2;
            aVar4 = a;
        }
        if (this.e == 5 || this.e == 7) {
            this.b = this.e == 5;
            t tVar = new t(((float) aVar4.b()) + d.c, ((float) aVar4.c()) - d.c);
            t tVar2 = new t(((float) aVar3.b()) + d.c, ((float) aVar3.c()) + d.c);
            t tVar3 = new t(((float) aVar2.b()) - d.c, ((float) aVar2.c()) + d.c);
            t tVar4 = new t(((float) aVar.b()) - d.c, ((float) aVar.c()) - d.c);
            return a(new t[]{tVar, tVar2, tVar3, tVar4}, (float) ((this.e * 2) - 3), (float) (this.e * 2));
        }
        throw m.a();
    }

    private a b() {
        t tVar;
        t tVar2;
        t tVar3;
        t tVar4;
        int f;
        int g;
        try {
            t[] a = new com.google.a.c.a.c(this.a).a();
            tVar = a[0];
            tVar2 = a[1];
            tVar3 = a[2];
            tVar4 = a[3];
        } catch (m e) {
            f = this.a.f() / 2;
            g = this.a.g() / 2;
            tVar = a(new a(f + 7, g - 7), false, 1, -1).a();
            tVar2 = a(new a(f + 7, g + 7), false, 1, 1).a();
            tVar3 = a(new a(f - 7, g + 7), false, -1, 1).a();
            tVar4 = a(new a(f - 7, g - 7), false, -1, -1).a();
        }
        g = com.google.a.c.a.a.a((((tVar.a() + tVar4.a()) + tVar2.a()) + tVar3.a()) / DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity);
        f = com.google.a.c.a.a.a((((tVar4.b() + tVar.b()) + tVar2.b()) + tVar3.b()) / DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity);
        try {
            t[] a2 = new com.google.a.c.a.c(this.a, 15, g, f).a();
            tVar = a2[0];
            tVar2 = a2[1];
            tVar3 = a2[2];
            tVar4 = a2[3];
        } catch (m e2) {
            tVar = a(new a(g + 7, f - 7), false, 1, -1).a();
            tVar2 = a(new a(g + 7, f + 7), false, 1, 1).a();
            tVar3 = a(new a(g - 7, f + 7), false, -1, 1).a();
            tVar4 = a(new a(g - 7, f - 7), false, -1, -1).a();
        }
        return new a(com.google.a.c.a.a.a((((tVar.a() + tVar4.a()) + tVar2.a()) + tVar3.a()) / DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity), com.google.a.c.a.a.a((((tVar4.b() + tVar.b()) + tVar2.b()) + tVar3.b()) / DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity));
    }

    private t[] b(t[] tVarArr) {
        return a(tVarArr, (float) (this.e * 2), (float) c());
    }

    private b a(b bVar, t tVar, t tVar2, t tVar3, t tVar4) throws m {
        i instance = i.getInstance();
        int c = c();
        float f = (((float) c) / 2.0f) - ((float) this.e);
        float f2 = (((float) c) / 2.0f) + ((float) this.e);
        return instance.a(bVar, c, c, f, f, f2, f, f2, f2, f, f2, tVar.a(), tVar.b(), tVar2.a(), tVar2.b(), tVar3.a(), tVar3.b(), tVar4.a(), tVar4.b());
    }

    private int a(t tVar, t tVar2, int i) {
        int i2 = 0;
        float a = a(tVar, tVar2);
        float f = a / ((float) i);
        float a2 = tVar.a();
        float b = tVar.b();
        float a3 = ((tVar2.a() - tVar.a()) * f) / a;
        f = (f * (tVar2.b() - tVar.b())) / a;
        for (int i3 = 0; i3 < i; i3++) {
            if (this.a.a(com.google.a.c.a.a.a((((float) i3) * a3) + a2), com.google.a.c.a.a.a((((float) i3) * f) + b))) {
                i2 |= 1 << ((i - i3) - 1);
            }
        }
        return i2;
    }

    private boolean a(a aVar, a aVar2, a aVar3, a aVar4) {
        a aVar5 = new a(aVar.b() - 3, aVar.c() + 3);
        a aVar6 = new a(aVar2.b() - 3, aVar2.c() - 3);
        a aVar7 = new a(aVar3.b() + 3, aVar3.c() - 3);
        a aVar8 = new a(aVar4.b() + 3, 3 + aVar4.c());
        int a = a(aVar8, aVar5);
        if (a != 0 && a(aVar5, aVar6) == a && a(aVar6, aVar7) == a && a(aVar7, aVar8) == a) {
            return true;
        }
        return false;
    }

    private int a(a aVar, a aVar2) {
        float b = b(aVar, aVar2);
        float b2 = ((float) (aVar2.b() - aVar.b())) / b;
        float c = ((float) (aVar2.c() - aVar.c())) / b;
        float b3 = (float) aVar.b();
        float c2 = (float) aVar.c();
        boolean a = this.a.a(aVar.b(), aVar.c());
        int i = 0;
        float f = b3;
        b3 = c2;
        for (int i2 = 0; ((float) i2) < b; i2++) {
            f += b2;
            b3 += c;
            if (this.a.a(com.google.a.c.a.a.a(f), com.google.a.c.a.a.a(b3)) != a) {
                i++;
            }
        }
        float f2 = ((float) i) / b;
        if (f2 > 0.1f && f2 < 0.9f) {
            return 0;
        }
        return ((f2 > 0.1f ? 1 : (f2 == 0.1f ? 0 : -1)) <= 0) == a ? 1 : -1;
    }

    private a a(a aVar, boolean z, int i, int i2) {
        int b = aVar.b() + i;
        int c = aVar.c() + i2;
        while (a(b, c) && this.a.a(b, c) == z) {
            b += i;
            c += i2;
        }
        int i3 = c - i2;
        c = b - i;
        while (a(c, i3) && this.a.a(c, i3) == z) {
            c += i;
        }
        b = c - i;
        c = i3;
        while (a(b, c) && this.a.a(b, c) == z) {
            c += i2;
        }
        return new a(b, c - i2);
    }

    private static t[] a(t[] tVarArr, float f, float f2) {
        float f3 = f2 / (2.0f * f);
        float a = tVarArr[0].a() - tVarArr[2].a();
        float b = tVarArr[0].b() - tVarArr[2].b();
        float a2 = (tVarArr[0].a() + tVarArr[2].a()) / 2.0f;
        float b2 = (tVarArr[0].b() + tVarArr[2].b()) / 2.0f;
        t tVar = new t((f3 * a) + a2, (f3 * b) + b2);
        t tVar2 = new t(a2 - (a * f3), b2 - (b * f3));
        a = tVarArr[1].a() - tVarArr[3].a();
        b = tVarArr[1].b() - tVarArr[3].b();
        a2 = (tVarArr[1].a() + tVarArr[3].a()) / 2.0f;
        b2 = (tVarArr[1].b() + tVarArr[3].b()) / 2.0f;
        t tVar3 = new t((f3 * a) + a2, (f3 * b) + b2);
        t tVar4 = new t(a2 - (a * f3), b2 - (f3 * b));
        return new t[]{tVar, tVar3, tVar2, tVar4};
    }

    private boolean a(int i, int i2) {
        return i >= 0 && i < this.a.f() && i2 > 0 && i2 < this.a.g();
    }

    private boolean a(t tVar) {
        return a(com.google.a.c.a.a.a(tVar.a()), com.google.a.c.a.a.a(tVar.b()));
    }

    private static float b(a aVar, a aVar2) {
        return com.google.a.c.a.a.a(aVar.b(), aVar.c(), aVar2.b(), aVar2.c());
    }

    private static float a(t tVar, t tVar2) {
        return com.google.a.c.a.a.a(tVar.a(), tVar.b(), tVar2.a(), tVar2.b());
    }

    private int c() {
        if (this.b) {
            return (this.c * 4) + 11;
        }
        if (this.c <= 4) {
            return (this.c * 4) + 15;
        }
        return ((this.c * 4) + ((((this.c - 4) / 8) + 1) * 2)) + 15;
    }
}
