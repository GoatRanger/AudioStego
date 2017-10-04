package com.here.a.a.a.a;

import com.here.a.a.a.s;
import java.util.Date;

public class d {
    public final ad<Date> a;
    public final ad<af> b;
    public final ad<s> c;

    public d(Date date, af afVar, s sVar) {
        this.a = ad.b(date);
        this.b = ad.b(afVar);
        this.c = ad.b(sVar);
    }

    public static d fromJSON(o oVar) {
        s sVar = null;
        Date a = oVar.b("@time") ? null : s.a(oVar.i("@time"));
        af fromJSON = oVar.f("RT") == null ? null : af.fromJSON(oVar.c("RT"));
        if (!oVar.b("Line")) {
            sVar = s.fromJSON(oVar.c("Line"));
        }
        return new d(a, fromJSON, sVar);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        d dVar = (d) obj;
        if (this.a.equals(dVar.a) && this.b.equals(dVar.b) && this.c.equals(dVar.c)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
    }
}
