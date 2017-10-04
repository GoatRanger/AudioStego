package com.here.a.a.a.a;

import java.util.Date;

public abstract class u {
    public final ad<b> d;
    public final ad<ak> e;
    public final ad<String> f;
    public final ad<Date> g;
    public final ad<af> h;
    public final ad<a> i;

    public u(b bVar, ak akVar, String str, Date date, af afVar, a aVar) {
        if (bVar == null && akVar == null) {
            throw new IllegalArgumentException("Location must have either non-null Address or Station.");
        }
        this.d = ad.b(bVar);
        this.e = ad.b(akVar);
        this.f = ad.b(str);
        this.g = ad.b(date);
        this.h = ad.b(afVar);
        this.i = ad.b(aVar);
    }

    public m a() {
        return this.e.c() ? ((ak) this.e.b()).b.a : ((b) this.d.b()).a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        u uVar = (u) obj;
        if (this.d.equals(uVar.d) && this.e.equals(uVar.e) && this.f.equals(uVar.f) && this.g.equals(uVar.g) && this.h.equals(uVar.h) && this.i.equals(uVar.i)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((this.d.hashCode() * 31) + this.e.hashCode()) * 31) + this.f.hashCode()) * 31) + this.g.hashCode()) * 31) + this.h.hashCode()) * 31) + this.i.hashCode();
    }
}
