package com.here.a.a.a.a;

public class a {
    public final m a;
    public final ad<String> b;
    public final ad<String> c;

    public a(m mVar, String str, String str2) {
        if (mVar == null) {
            throw new NullPointerException("AccessPoint location (GeoPoint) can't be null.");
        }
        this.a = mVar;
        this.b = ad.b(str);
        this.c = ad.b(str2);
    }

    public static a fromJSON(o oVar) {
        return new a(new m(oVar.h("@y").doubleValue(), oVar.h("@x").doubleValue()), oVar.a("@id", null), oVar.a("@name", null));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        if (this.a.equals(aVar.a) && this.b.equals(aVar.b) && this.c.equals(aVar.c)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
    }
}
