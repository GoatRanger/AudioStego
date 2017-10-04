package com.here.a.a.a.a;

public class b {
    public final m a;
    public final ad<String> b;
    public final ad<String> c;
    public final ad<String> d;
    public final ad<String> e;
    public final ad<String> f;
    public final ad<String> g;
    public final ad<String> h;
    public final ad<String> i;
    public final ad<String> j;

    public b(m mVar, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        if (mVar == null) {
            throw new NullPointerException("Address location (GeoPoint) can't be null.");
        }
        this.a = mVar;
        this.b = ad.b(str);
        this.c = ad.b(str2);
        this.d = ad.b(str3);
        this.e = ad.b(str4);
        this.f = ad.b(str5);
        this.g = ad.b(str6);
        this.h = ad.b(str7);
        this.i = ad.b(str8);
        this.j = ad.b(str9);
    }

    public static b fromJSON(o oVar) {
        return new b(new m(oVar.h("@y").doubleValue(), oVar.h("@x").doubleValue()), oVar.a("@name", null), oVar.a("@country", null), oVar.a("@ccode", null), oVar.a("@state", null), oVar.a("@postal", null), oVar.a("@city", null), oVar.a("@district", null), oVar.a("@street", null), oVar.a("@number", null));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        if (this.a.equals(bVar.a) && this.b.equals(bVar.b) && this.c.equals(bVar.c) && this.d.equals(bVar.d) && this.e.equals(bVar.e) && this.f.equals(bVar.f) && this.g.equals(bVar.g) && this.h.equals(bVar.h) && this.i.equals(bVar.i) && this.j.equals(bVar.j)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode()) * 31) + this.e.hashCode()) * 31) + this.f.hashCode()) * 31) + this.g.hashCode()) * 31) + this.h.hashCode()) * 31) + this.i.hashCode()) * 31) + this.j.hashCode();
    }
}
