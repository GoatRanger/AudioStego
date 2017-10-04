package com.google.a.d.c;

import com.google.a.f;
import com.google.api.client.http.HttpStatusCodes;
import dji.pilot.usercenter.protocol.d;
import dji.thirdparty.g.b.a.a;

public class k {
    static final k[] a = new k[]{new k(false, 3, 5, 8, 8, 1), new k(false, 5, 7, 10, 10, 1), new k(true, 5, 7, 16, 6, 1), new k(false, 8, 10, 12, 12, 1), new k(true, 10, 11, 14, 6, 2), new k(false, 12, 12, 14, 14, 1), new k(true, 16, 14, 24, 10, 1), new k(false, 18, 14, 16, 16, 1), new k(false, 22, 18, 18, 18, 1), new k(true, 22, 18, 16, 10, 2), new k(false, 30, 20, 20, 20, 1), new k(true, 32, 24, 16, 14, 2), new k(false, 36, 24, 22, 22, 1), new k(false, 44, 28, 24, 24, 1), new k(true, 49, 28, 22, 14, 2), new k(false, 62, 36, 14, 14, 4), new k(false, 86, 42, 16, 16, 4), new k(false, 114, 48, 18, 18, 4), new k(false, 144, 56, 20, 20, 4), new k(false, 174, 68, 22, 22, 4), new k(false, HttpStatusCodes.STATUS_CODE_NO_CONTENT, 84, 24, 24, 4, 102, 42), new k(false, 280, d.k, 14, 14, 16, 140, 56), new k(false, 368, 144, 16, 16, 16, 92, 36), new k(false, 456, 192, 18, 18, 16, 114, 48), new k(false, 576, a.fw_, 20, 20, 16, 144, 56), new k(false, 696, 272, 22, 22, 16, 174, 68), new k(false, 816, 336, 24, 24, 16, 136, 56), new k(false, 1050, 408, 18, 18, 36, 175, 68), new k(false, 1304, 496, 20, 20, 36, 163, 62), new d()};
    private static k[] d = a;
    public final int b;
    public final int c;
    private final boolean e;
    private final int f;
    private final int g;
    private final int h;
    private final int i;
    private final int j;

    public static void a(k[] kVarArr) {
        d = kVarArr;
    }

    public k(boolean z, int i, int i2, int i3, int i4, int i5) {
        this(z, i, i2, i3, i4, i5, i, i2);
    }

    k(boolean z, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.e = z;
        this.f = i;
        this.g = i2;
        this.b = i3;
        this.c = i4;
        this.h = i5;
        this.i = i6;
        this.j = i7;
    }

    public static k b(int i) {
        return a(i, l.FORCE_NONE, true);
    }

    public static k a(int i, l lVar) {
        return a(i, lVar, true);
    }

    public static k a(int i, boolean z, boolean z2) {
        return a(i, z ? l.FORCE_NONE : l.FORCE_SQUARE, z2);
    }

    private static k a(int i, l lVar, boolean z) {
        return a(i, lVar, null, null, z);
    }

    public static k a(int i, l lVar, f fVar, f fVar2, boolean z) {
        for (k kVar : d) {
            if ((lVar != l.FORCE_SQUARE || !kVar.e) && ((lVar != l.FORCE_RECTANGLE || kVar.e) && ((fVar == null || (kVar.f() >= fVar.a() && kVar.g() >= fVar.b())) && ((fVar2 == null || (kVar.f() <= fVar2.a() && kVar.g() <= fVar2.b())) && i <= kVar.f)))) {
                return kVar;
            }
        }
        if (!z) {
            return null;
        }
        throw new IllegalArgumentException("Can't find a symbol arrangement that matches the message. Data codewords: " + i);
    }

    final int b() {
        switch (this.h) {
            case 1:
                return 1;
            case 2:
            case 4:
                return 2;
            case 16:
                return 4;
            case 36:
                return 6;
            default:
                throw new IllegalStateException("Cannot handle this number of data regions");
        }
    }

    final int c() {
        switch (this.h) {
            case 1:
            case 2:
                return 1;
            case 4:
                return 2;
            case 16:
                return 4;
            case 36:
                return 6;
            default:
                throw new IllegalStateException("Cannot handle this number of data regions");
        }
    }

    public final int d() {
        return b() * this.b;
    }

    public final int e() {
        return c() * this.c;
    }

    public final int f() {
        return d() + (b() * 2);
    }

    public final int g() {
        return e() + (c() * 2);
    }

    public int h() {
        return this.f + this.g;
    }

    public int a() {
        return this.f / this.i;
    }

    public final int i() {
        return this.f;
    }

    public final int j() {
        return this.g;
    }

    public int a(int i) {
        return this.i;
    }

    public final int c(int i) {
        return this.j;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.e ? "Rectangular Symbol:" : "Square Symbol:");
        stringBuilder.append(" data region ").append(this.b).append('x').append(this.c);
        stringBuilder.append(", symbol size ").append(f()).append('x').append(g());
        stringBuilder.append(", symbol data size ").append(d()).append('x').append(e());
        stringBuilder.append(", codewords ").append(this.f).append('+').append(this.g);
        return stringBuilder.toString();
    }
}
