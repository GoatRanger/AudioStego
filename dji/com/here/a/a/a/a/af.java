package com.here.a.a.a.a;

import com.here.a.a.a.s;
import com.here.android.mpa.search.TransitDeparture;
import java.util.Date;

public class af {
    public final boolean a;
    public final boolean b;
    public final ad<Date> c;
    public final ad<Date> d;
    public final ad<Boolean> e;
    public final ad<String> f;
    public final ad<a> g;

    public enum a {
        OK,
        REDIRECTED,
        REPLACED,
        CANCELLED,
        ADDITIONAL;

        public static a a(String str) {
            if (TransitDeparture.EXCEPTION_EVENT_REDIRECTED.equalsIgnoreCase(str)) {
                return REDIRECTED;
            }
            if (TransitDeparture.EXCEPTION_EVENT_REPLACED.equalsIgnoreCase(str)) {
                return REPLACED;
            }
            if ("cancelled".equalsIgnoreCase(str)) {
                return CANCELLED;
            }
            if (TransitDeparture.EXCEPTION_EVENT_ADDITIONAL.equalsIgnoreCase(str)) {
                return ADDITIONAL;
            }
            return OK;
        }
    }

    public af(boolean z, boolean z2, Date date, Date date2, Boolean bool, String str, a aVar) {
        this.a = z;
        this.b = z2;
        this.c = ad.b(date);
        this.d = ad.b(date2);
        this.e = ad.b(bool);
        this.f = ad.b(str);
        this.g = ad.b(aVar);
    }

    public static af fromJSON(o oVar) {
        boolean z;
        Boolean bool;
        boolean z2 = true;
        boolean z3 = oVar.b("@has_arr") || oVar.j("@has_arr").intValue() == 1;
        if (oVar.b("@has_dep") || oVar.j("@has_dep").intValue() == 1) {
            z = true;
        } else {
            z = false;
        }
        Date a = oVar.b("@arr") ? null : s.a(oVar.i("@arr"));
        Date a2 = oVar.b("@dep") ? null : s.a(oVar.i("@dep"));
        if (oVar.b("@new_stop")) {
            bool = null;
        } else {
            if (oVar.j("@new_stop").intValue() != 1) {
                z2 = false;
            }
            bool = Boolean.valueOf(z2);
        }
        return new af(z3, z, a, a2, bool, oVar.a("@platform", null), a.a(oVar.a("@status", null)));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        af afVar = (af) obj;
        if (this.a == afVar.a && this.b == afVar.b && this.c.equals(afVar.c) && this.d.equals(afVar.d) && this.e.equals(afVar.e) && this.f.equals(afVar.f) && this.g.equals(afVar.g)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 1;
        int i2 = (this.a ? 1 : 0) * 31;
        if (!this.b) {
            i = 0;
        }
        return ((((((((((i2 + i) * 31) + this.c.hashCode()) * 31) + this.d.hashCode()) * 31) + this.e.hashCode()) * 31) + this.f.hashCode()) * 31) + this.g.hashCode();
    }
}
