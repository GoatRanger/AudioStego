package com.here.a.a.a.a;

import com.here.a.a.a.s;
import java.util.Date;

public class r {
    public final ak a;
    public final ad<Date> b;
    public final ad<Date> c;
    public final ad<af> d;

    public r(ak akVar, Date date, Date date2, af afVar) {
        if (akVar == null) {
            throw new NullPointerException("JourneyStop Station can't be null.");
        }
        this.a = akVar;
        this.b = ad.b(date);
        this.c = ad.b(date2);
        this.d = ad.b(afVar);
    }

    public static r fromJSON(o oVar) {
        af afVar = null;
        ak fromJSON = ak.fromJSON(oVar.c("Stn"));
        Date a = oVar.b("@arr") ? null : s.a(oVar.i("@arr"));
        Date a2 = oVar.b("@dep") ? null : s.a(oVar.i("@dep"));
        if (oVar.f("RT") != null) {
            afVar = af.fromJSON(oVar.c("RT"));
        }
        return new r(fromJSON, a, a2, afVar);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        r rVar = (r) obj;
        if (this.a.equals(rVar.a) && this.b.equals(rVar.b) && this.c.equals(rVar.c) && this.d.equals(rVar.d)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode();
    }
}
