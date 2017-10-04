package com.google.a.a.c;

import com.google.a.c.a;
import java.util.Deque;
import java.util.LinkedList;

final class f {
    static final f a = new f(g.a, 0, 0, 0);
    private final int b;
    private final g c;
    private final int d;
    private final int e;

    private f(g gVar, int i, int i2, int i3) {
        this.c = gVar;
        this.b = i;
        this.d = i2;
        this.e = i3;
    }

    int a() {
        return this.b;
    }

    g b() {
        return this.c;
    }

    int c() {
        return this.d;
    }

    int d() {
        return this.e;
    }

    f a(int i, int i2) {
        int i3;
        g a;
        int i4 = this.e;
        g gVar = this.c;
        if (i != this.b) {
            i3 = d.g[this.b][i];
            i3 = i4 + (i3 >> 16);
            a = gVar.a(65535 & i3, i3 >> 16);
        } else {
            i3 = i4;
            a = gVar;
        }
        int i5 = i == 2 ? 4 : 5;
        return new f(a.a(i2, i5), i, 0, i5 + i3);
    }

    f b(int i, int i2) {
        int i3;
        g gVar = this.c;
        if (this.b == 2) {
            i3 = 4;
        } else {
            i3 = 5;
        }
        return new f(gVar.a(d.h[this.b][i], i3).a(i2, 5), this.b, 0, (i3 + this.e) + 5);
    }

    f a(int i) {
        g a;
        g gVar = this.c;
        int i2 = this.b;
        int i3 = this.e;
        if (this.b == 4 || this.b == 2) {
            int i4 = d.g[i2][0];
            i3 += i4 >> 16;
            a = gVar.a(65535 & i4, i4 >> 16);
            i2 = 0;
        } else {
            a = gVar;
        }
        int i5 = (this.d == 0 || this.d == 31) ? 18 : this.d == 62 ? 9 : 8;
        f fVar = new f(a, i2, this.d + 1, i3 + i5);
        if (fVar.d == 2078) {
            return fVar.b(i + 1);
        }
        return fVar;
    }

    f b(int i) {
        return this.d == 0 ? this : new f(this.c.b(i - this.d, this.d), this.b, 0, this.e);
    }

    boolean a(f fVar) {
        int i = this.e + (d.g[this.b][fVar.b] >> 16);
        if (fVar.d > 0 && (this.d == 0 || this.d > fVar.d)) {
            i += 10;
        }
        return i <= fVar.e;
    }

    a a(byte[] bArr) {
        Deque<g> linkedList = new LinkedList();
        for (g gVar = b(bArr.length).c; gVar != null; gVar = gVar.a()) {
            linkedList.addFirst(gVar);
        }
        a aVar = new a();
        for (g gVar2 : linkedList) {
            gVar2.a(aVar, bArr);
        }
        return aVar;
    }

    public String toString() {
        return String.format("%s bits=%d bytes=%d", new Object[]{d.a[this.b], Integer.valueOf(this.e), Integer.valueOf(this.d)});
    }
}
