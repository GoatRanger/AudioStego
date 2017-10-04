package com.nokia.maps.a;

import com.here.a.a.a.a.aj;
import com.here.a.a.a.a.an;
import com.here.a.a.a.a.c;
import com.here.a.a.a.a.m;
import com.here.a.a.a.a.n;
import com.here.a.a.a.a.q;
import com.here.a.a.a.a.r;
import com.here.a.a.a.a.s;
import com.here.a.a.a.a.t;
import com.here.a.a.a.a.v;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.urbanmobility.Alert;
import com.here.android.mpa.urbanmobility.IntermediateStop;
import com.here.android.mpa.urbanmobility.Line;
import com.here.android.mpa.urbanmobility.Link;
import com.here.android.mpa.urbanmobility.Location;
import com.here.android.mpa.urbanmobility.Maneuver;
import com.here.android.mpa.urbanmobility.RouteSection;
import com.here.android.mpa.urbanmobility.Ticket;
import com.here.android.mpa.urbanmobility.TransportType;
import com.nokia.maps.am;
import com.nokia.maps.ce;
import com.nokia.maps.k;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ar {
    private static am<RouteSection, ar> m;
    private static k<RouteSection, ar> n;
    private Line a;
    private Location b;
    private Location c;
    private List<GeoCoordinate> d;
    private Collection<Ticket> e;
    private TransportType f;
    private List<IntermediateStop> g;
    private int h;
    private long i;
    private List<Maneuver> j;
    private Collection<Link> k;
    private Collection<Alert> l;

    protected ar(aj ajVar) {
        TransportType transportType = null;
        this.b = aa.a(new aa(ajVar.c));
        this.c = aa.a(new aa(ajVar.b));
        this.a = ajVar.b.c.c() ? x.a(new x((s) ajVar.b.c.b())) : null;
        if (ajVar.i.c()) {
            List<m> a = ((n) ajVar.i.b()).a();
            this.d = new ArrayList(a.size());
            for (m mVar : a) {
                this.d.add(new GeoCoordinate(mVar.a, mVar.b));
            }
        } else {
            this.d = Collections.emptyList();
        }
        List<an> e = ajVar.e();
        if (e.isEmpty()) {
            this.e = Collections.emptyList();
        } else {
            this.e = new ArrayList(e.size());
            for (an azVar : e) {
                this.e.add(az.a(new az(azVar)));
            }
        }
        if (!ajVar.f()) {
            transportType = ba.a(ajVar.g());
        }
        this.f = transportType;
        if (ajVar.f()) {
            this.g = Collections.emptyList();
        } else {
            List<r> a2 = ((q) ajVar).a();
            if (a2.isEmpty()) {
                this.g = Collections.emptyList();
            } else {
                this.g = new ArrayList(a2.size());
                for (r vVar : a2) {
                    this.g.add(v.a(new v(vVar)));
                }
            }
        }
        this.h = ajVar.d;
        this.i = ajVar.e;
        List<v> b = ajVar.b();
        if (b.isEmpty()) {
            this.j = Collections.emptyList();
        } else {
            this.j = new ArrayList(b.size());
            for (v abVar : b) {
                this.j.add(ab.a(new ab(abVar)));
            }
        }
        Collection<t> c = ajVar.c();
        if (c.isEmpty()) {
            this.k = Collections.emptyList();
        } else {
            this.k = new ArrayList(c.size());
            for (t zVar : c) {
                this.k.add(z.a(new z(zVar)));
            }
        }
        Collection<c> d = ajVar.d();
        if (d.isEmpty()) {
            this.l = Collections.emptyList();
            return;
        }
        this.l = new ArrayList(d.size());
        for (c fVar : d) {
            this.l.add(f.a(new f(fVar)));
        }
    }

    public Line a() {
        return this.a;
    }

    public Location b() {
        return this.b;
    }

    public Location c() {
        return this.c;
    }

    public List<GeoCoordinate> d() {
        return Collections.unmodifiableList(this.d);
    }

    public Collection<Ticket> e() {
        return Collections.unmodifiableCollection(this.e);
    }

    public TransportType f() {
        return this.f;
    }

    public List<IntermediateStop> g() {
        return Collections.unmodifiableList(this.g);
    }

    public int h() {
        return this.h;
    }

    public long i() {
        return this.i;
    }

    public List<Maneuver> j() {
        return Collections.unmodifiableList(this.j);
    }

    public Collection<Link> k() {
        return Collections.unmodifiableCollection(this.k);
    }

    public Collection<Alert> l() {
        return Collections.unmodifiableCollection(this.l);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ar arVar = (ar) obj;
        if (this.h == arVar.h && this.i == arVar.i && this.b.equals(arVar.b) && this.c.equals(arVar.c) && this.d.equals(arVar.d) && this.e.equals(arVar.e) && this.f == arVar.f && this.g.equals(arVar.g) && this.j.equals(arVar.j) && this.k.equals(arVar.k) && this.l.equals(arVar.l)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((this.f != null ? this.f.hashCode() : 0) + (((((((this.b.hashCode() * 31) + this.c.hashCode()) * 31) + this.d.hashCode()) * 31) + this.e.hashCode()) * 31)) * 31) + this.g.hashCode()) * 31) + this.h) * 31) + ((int) (this.i ^ (this.i >>> 32)))) * 31) + this.j.hashCode()) * 31) + this.k.hashCode()) * 31) + this.l.hashCode();
    }

    public static void a(k<RouteSection, ar> kVar, am<RouteSection, ar> amVar) {
        n = kVar;
        m = amVar;
    }

    static RouteSection a(ar arVar) {
        if (arVar != null) {
            return (RouteSection) m.a(arVar);
        }
        return null;
    }

    static {
        ce.a(RouteSection.class);
    }
}
