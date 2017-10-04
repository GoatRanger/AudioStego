package com.here.a.a.a.a;

public final class aa {
    public final String a;
    public final boolean b;
    public final int c;
    public final int d;
    public final int e;
    public final ad<i> f;
    public final ad<l> g;
    public final ad<f> h;

    public aa(String str, boolean z, int i, int i2, int i3, i iVar, l lVar, f fVar) {
        if (str == null) {
            throw new IllegalArgumentException("Geo Ref and type can't be null.");
        }
        this.a = str;
        this.b = z;
        this.c = i;
        this.d = i2;
        this.e = i3;
        this.f = ad.b(iVar);
        this.g = ad.b(lVar);
        this.h = ad.b(fVar);
    }

    public static aa fromJSON(o oVar) {
        f fromJSON;
        String i;
        int intValue;
        int intValue2;
        int intValue3;
        i a;
        boolean z = true;
        l lVar = null;
        o c = oVar.c("LocalCoverage");
        o c2 = c.c("NearbyCoverage");
        if (!c.b("City")) {
            p d = c.d("City");
            if (d.a() > 0) {
                fromJSON = f.fromJSON(d.a(0));
                i = c.i("@georef");
                if (c2.j("@covered").intValue() != 1) {
                    z = false;
                }
                intValue = c2.j("@lines").intValue();
                intValue2 = c2.j("@radius").intValue();
                intValue3 = c2.j("@stops").intValue();
                if (c2.b("@type")) {
                    a = i.a(c2.i("@type"));
                } else {
                    a = null;
                }
                if (!c.b("ExploredCoverage")) {
                    lVar = l.fromJSON(c.c("ExploredCoverage"));
                }
                return new aa(i, z, intValue, intValue2, intValue3, a, lVar, fromJSON);
            }
        }
        fromJSON = null;
        i = c.i("@georef");
        if (c2.j("@covered").intValue() != 1) {
            z = false;
        }
        intValue = c2.j("@lines").intValue();
        intValue2 = c2.j("@radius").intValue();
        intValue3 = c2.j("@stops").intValue();
        if (c2.b("@type")) {
            a = i.a(c2.i("@type"));
        } else {
            a = null;
        }
        if (c.b("ExploredCoverage")) {
            lVar = l.fromJSON(c.c("ExploredCoverage"));
        }
        return new aa(i, z, intValue, intValue2, intValue3, a, lVar, fromJSON);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        aa aaVar = (aa) obj;
        if (this.a.equals(aaVar.a) && this.b == aaVar.b && this.c == aaVar.c && this.d == aaVar.d && this.e == aaVar.e && this.f.equals(aaVar.f) && this.g.equals(aaVar.g) && this.h.equals(aaVar.h)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((this.b ? 1 : 0) + (this.a.hashCode() * 31)) * 31) + this.c) * 31) + this.d) * 31) + this.e) * 31) + this.f.hashCode()) * 31) + this.g.hashCode()) * 31) + this.h.hashCode();
    }
}
