package com.google.a.h.a;

import com.google.a.c.b;
import com.google.a.m;
import com.google.a.t;

final class c {
    private b a;
    private t b;
    private t c;
    private t d;
    private t e;
    private int f;
    private int g;
    private int h;
    private int i;

    c(b bVar, t tVar, t tVar2, t tVar3, t tVar4) throws m {
        if (!(tVar == null && tVar3 == null) && (!(tVar2 == null && tVar4 == null) && ((tVar == null || tVar2 != null) && (tVar3 == null || tVar4 != null)))) {
            a(bVar, tVar, tVar2, tVar3, tVar4);
            return;
        }
        throw m.a();
    }

    c(c cVar) {
        a(cVar.a, cVar.b, cVar.c, cVar.d, cVar.e);
    }

    private void a(b bVar, t tVar, t tVar2, t tVar3, t tVar4) {
        this.a = bVar;
        this.b = tVar;
        this.c = tVar2;
        this.d = tVar3;
        this.e = tVar4;
        i();
    }

    static c a(c cVar, c cVar2) throws m {
        if (cVar == null) {
            return cVar2;
        }
        if (cVar2 == null) {
            return cVar;
        }
        return new c(cVar.a, cVar.b, cVar.c, cVar2.d, cVar2.e);
    }

    c a(int i, int i2, boolean z) throws m {
        int b;
        t tVar;
        t tVar2 = this.b;
        t tVar3 = this.c;
        t tVar4 = this.d;
        t tVar5 = this.e;
        if (i > 0) {
            t tVar6 = z ? this.b : this.d;
            b = ((int) tVar6.b()) - i;
            if (b < 0) {
                b = 0;
            }
            tVar = new t(tVar6.a(), (float) b);
            if (!z) {
                tVar4 = tVar;
                tVar = tVar2;
            }
        } else {
            tVar = tVar2;
        }
        if (i2 > 0) {
            tVar6 = z ? this.c : this.e;
            b = ((int) tVar6.b()) + i2;
            if (b >= this.a.g()) {
                b = this.a.g() - 1;
            }
            tVar2 = new t(tVar6.a(), (float) b);
            if (!z) {
                tVar5 = tVar2;
                tVar2 = tVar3;
            }
        } else {
            tVar2 = tVar3;
        }
        i();
        return new c(this.a, tVar, tVar2, tVar4, tVar5);
    }

    private void i() {
        if (this.b == null) {
            this.b = new t(0.0f, this.d.b());
            this.c = new t(0.0f, this.e.b());
        } else if (this.d == null) {
            this.d = new t((float) (this.a.f() - 1), this.b.b());
            this.e = new t((float) (this.a.f() - 1), this.c.b());
        }
        this.f = (int) Math.min(this.b.a(), this.c.a());
        this.g = (int) Math.max(this.d.a(), this.e.a());
        this.h = (int) Math.min(this.b.b(), this.d.b());
        this.i = (int) Math.max(this.c.b(), this.e.b());
    }

    int a() {
        return this.f;
    }

    int b() {
        return this.g;
    }

    int c() {
        return this.h;
    }

    int d() {
        return this.i;
    }

    t e() {
        return this.b;
    }

    t f() {
        return this.d;
    }

    t g() {
        return this.c;
    }

    t h() {
        return this.e;
    }
}
