package com.nokia.maps.a;

import com.here.a.a.a.a.ak;
import com.here.a.a.a.a.s;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.urbanmobility.Address;
import com.here.android.mpa.urbanmobility.Line;
import com.here.android.mpa.urbanmobility.LineCategory;
import com.here.android.mpa.urbanmobility.Station;
import com.nokia.maps.am;
import com.nokia.maps.ce;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class at {
    private static am<Station, at> n;
    private String a;
    private String b;
    private String c;
    private Address d;
    private GeoCoordinate e;
    private Collection<Line> f;
    private Set<LineCategory> g;
    private boolean h;
    private boolean i;
    private boolean j;
    private boolean k;
    private int l;
    private long m;

    protected at(ak akVar) {
        this.a = akVar.a;
        this.b = (String) akVar.d.c("");
        this.c = (String) akVar.j.c("");
        this.d = e.a(new e(akVar.b));
        this.e = u.a(akVar.b.a);
        this.h = ((Boolean) akVar.g.c(Boolean.valueOf(false))).booleanValue();
        this.i = ((Boolean) akVar.h.c(Boolean.valueOf(false))).booleanValue();
        this.j = ((Boolean) akVar.i.c(Boolean.valueOf(false))).booleanValue();
        this.k = akVar.c;
        this.l = ((Integer) akVar.e.c(Integer.valueOf(-1))).intValue();
        this.m = ((Long) akVar.f.c(Long.valueOf(-1))).longValue();
        Collection<s> a = akVar.a();
        if (a.isEmpty()) {
            this.f = Collections.emptyList();
            this.g = Collections.emptySet();
            return;
        }
        this.f = new ArrayList(a.size());
        this.g = new HashSet();
        for (s sVar : a) {
            this.f.add(x.a(new x(sVar)));
            this.g.add(w.a(new w(sVar)));
        }
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public boolean c() {
        return this.h;
    }

    public boolean d() {
        return this.i;
    }

    public boolean e() {
        return this.j;
    }

    public boolean f() {
        return this.k;
    }

    public String g() {
        return this.c;
    }

    public Address h() {
        return this.d;
    }

    public GeoCoordinate i() {
        return this.e;
    }

    public Collection<Line> j() {
        return Collections.unmodifiableCollection(this.f);
    }

    public Set<LineCategory> k() {
        return Collections.unmodifiableSet(this.g);
    }

    public int l() {
        return this.l;
    }

    public long m() {
        return this.m;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        at atVar = (at) obj;
        if (this.a.equals(atVar.a) && this.b.equals(atVar.b) && this.e.equals(atVar.e) && this.f.equals(atVar.f) && this.g.equals(atVar.g)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.e.hashCode()) * 31) + this.f.hashCode()) * 31) + this.g.hashCode();
    }

    public static void a(am<Station, at> amVar) {
        n = amVar;
    }

    static Station a(at atVar) {
        if (atVar != null) {
            return (Station) n.a(atVar);
        }
        return null;
    }

    static {
        ce.a(Station.class);
    }
}
