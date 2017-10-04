package com.nokia.maps.a;

import com.here.a.a.a.a.ac;
import com.here.a.a.a.a.af;
import com.here.a.a.a.a.ak;
import com.here.a.a.a.a.d;
import com.here.a.a.a.a.j;
import com.here.a.a.a.a.k;
import com.here.a.a.a.a.s;
import com.here.android.mpa.urbanmobility.AlternativeDeparture;
import com.here.android.mpa.urbanmobility.Departure;
import com.here.android.mpa.urbanmobility.DepartureFrequency;
import com.here.android.mpa.urbanmobility.Station;
import com.nokia.maps.am;
import com.nokia.maps.ce;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class r extends a {
    private static am<Departure, r> m;
    private String i;
    private DepartureFrequency j;
    private List<AlternativeDeparture> k;
    private Station l;

    protected r(k kVar) {
        if (kVar.c.c()) {
            s sVar = (s) kVar.c.b();
            this.a = x.a(new x(sVar));
            this.b = (String) sVar.c.c("");
            if (sVar.d.c()) {
                this.c = ai.a(new ai((ac) sVar.d.b()));
            }
            this.g = ((Boolean) sVar.f.c(Boolean.valueOf(false))).booleanValue();
            this.h = ((Boolean) sVar.g.c(Boolean.valueOf(false))).booleanValue();
        } else {
            this.b = "";
            this.g = false;
            this.h = false;
        }
        if (kVar.g.c()) {
            this.d = (Date) kVar.g.b();
        }
        this.i = kVar.f.c() ? (String) kVar.f.b() : "";
        if (kVar.h.c()) {
            this.f = true;
            this.e = ak.a(new ak((af) kVar.h.b()));
        } else {
            this.f = false;
        }
        if (kVar.e.c()) {
            this.l = at.a(new at((ak) kVar.e.b()));
        }
        if (kVar.b.c()) {
            this.j = q.a(new q((j) kVar.b.b()));
            List<d> a = ((j) kVar.b.b()).a();
            if (a.isEmpty()) {
                this.k = Collections.emptyList();
                return;
            }
            this.k = new ArrayList(a.size());
            for (d gVar : a) {
                this.k.add(g.a(new g(gVar)));
            }
            return;
        }
        this.k = Collections.emptyList();
    }

    public String j() {
        return this.i;
    }

    public DepartureFrequency k() {
        return this.j;
    }

    public List<AlternativeDeparture> l() {
        return Collections.unmodifiableList(this.k);
    }

    public Station m() {
        return this.l;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        r rVar = (r) obj;
        if (this.i.equals(rVar.i)) {
            if (this.j != null) {
                if (this.j.equals(rVar.j)) {
                    return true;
                }
            } else if (rVar.j == null && this.k.equals(rVar.k)) {
                if (this.l != null) {
                    if (this.l.equals(rVar.l)) {
                        return true;
                    }
                } else if (rVar.l == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int hashCode2 = ((super.hashCode() * 31) + this.i.hashCode()) * 31;
        if (this.j != null) {
            hashCode = this.j.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (((hashCode + hashCode2) * 31) + this.k.hashCode()) * 31;
        if (this.l != null) {
            i = this.l.hashCode();
        }
        return hashCode + i;
    }

    public static void a(am<Departure, r> amVar) {
        m = amVar;
    }

    static Departure a(r rVar) {
        if (rVar != null) {
            return (Departure) m.a(rVar);
        }
        return null;
    }

    static {
        ce.a(Departure.class);
    }
}
