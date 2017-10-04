package com.nokia.maps.a;

import com.here.a.a.a.a.ab;
import com.here.a.a.a.a.ac;
import com.here.a.a.a.a.k;
import com.here.a.a.a.a.s;
import com.here.a.a.a.a.t;
import com.here.android.mpa.urbanmobility.Departure;
import com.here.android.mpa.urbanmobility.DepartureBoard;
import com.here.android.mpa.urbanmobility.Line;
import com.here.android.mpa.urbanmobility.Link;
import com.here.android.mpa.urbanmobility.Operator;
import com.nokia.maps.am;
import com.nokia.maps.ce;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class o {
    private static am<DepartureBoard, o> e;
    private List<Departure> a;
    private Collection<Line> b;
    private Collection<Operator> c;
    private Collection<Link> d;

    protected o(ab abVar) {
        List<k> b = abVar.b();
        if (b.isEmpty()) {
            this.a = Collections.emptyList();
        } else {
            this.a = new ArrayList(b.size());
            for (k rVar : b) {
                this.a.add(r.a(new r(rVar)));
            }
        }
        Collection<s> c = abVar.c();
        if (c.isEmpty()) {
            this.b = Collections.emptyList();
        } else {
            this.b = new ArrayList(c.size());
            for (s xVar : c) {
                this.b.add(x.a(new x(xVar)));
            }
        }
        Collection<ac> a = abVar.a();
        if (a.isEmpty()) {
            this.c = Collections.emptyList();
        } else {
            this.c = new ArrayList(a.size());
            for (ac aiVar : a) {
                this.c.add(ai.a(new ai(aiVar)));
            }
        }
        Collection<t> d = abVar.d();
        if (d.isEmpty()) {
            this.d = Collections.emptyList();
            return;
        }
        this.d = new ArrayList(d.size());
        for (t zVar : d) {
            this.d.add(z.a(new z(zVar)));
        }
    }

    public final List<Departure> a() {
        return Collections.unmodifiableList(this.a);
    }

    public Collection<Line> b() {
        return Collections.unmodifiableCollection(this.b);
    }

    public Collection<Operator> c() {
        return Collections.unmodifiableCollection(this.c);
    }

    public Collection<Link> d() {
        return Collections.unmodifiableCollection(this.d);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        o oVar = (o) obj;
        if (this.a.equals(oVar.a) && this.b.equals(oVar.b) && this.c.equals(oVar.c) && this.d.equals(oVar.d)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode();
    }

    public static void a(am<DepartureBoard, o> amVar) {
        e = amVar;
    }

    static DepartureBoard a(o oVar) {
        if (oVar != null) {
            return (DepartureBoard) e.a(oVar);
        }
        return null;
    }

    static {
        ce.a(DepartureBoard.class);
    }
}
