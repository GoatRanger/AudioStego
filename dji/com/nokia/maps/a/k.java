package com.nokia.maps.a;

import com.here.a.a.a.a.ac;
import com.here.a.a.a.a.ae;
import com.here.a.a.a.a.f;
import com.here.a.a.a.a.y;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.urbanmobility.City;
import com.here.android.mpa.urbanmobility.MissingCoverage;
import com.here.android.mpa.urbanmobility.Operator;
import com.here.android.mpa.urbanmobility.Provider;
import com.nokia.maps.am;
import com.nokia.maps.ce;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class k {
    private static am<City, k> q;
    private String a;
    private String b;
    private Date c;
    private Date d;
    private GeoCoordinate e;
    private String f;
    private int g;
    private double h;
    private String i;
    private int j;
    private int k;
    private float l;
    private int m;
    private MissingCoverage n;
    private List<Operator> o;
    private List<Provider> p;

    public k(f fVar) {
        this.a = fVar.a;
        this.b = fVar.b;
        this.c = fVar.c;
        this.d = fVar.d;
        this.e = u.a(fVar.e);
        this.f = (String) fVar.f.c("");
        this.g = ((Integer) fVar.g.c(Integer.valueOf(-1))).intValue();
        this.h = ((Double) fVar.h.c(Double.valueOf(Map.MOVE_PRESERVE_ZOOM_LEVEL))).doubleValue();
        this.i = (String) fVar.i.c("");
        this.j = ((Integer) fVar.j.c(Integer.valueOf(-1))).intValue();
        this.k = ((Integer) fVar.k.c(Integer.valueOf(-1))).intValue();
        this.l = ((Float) fVar.l.c(Float.valueOf(-1.0f))).floatValue();
        this.m = ((Integer) fVar.m.c(Integer.valueOf(-1))).intValue();
        this.n = fVar.n.c() ? ad.a(new ad((y) fVar.n.b())) : null;
        Collection<ac> a = fVar.a();
        if (a.isEmpty()) {
            this.o = Collections.emptyList();
        } else {
            this.o = new ArrayList(a.size());
            for (ac aiVar : a) {
                this.o.add(ai.a(new ai(aiVar)));
            }
        }
        Collection<ae> b = fVar.b();
        if (b.isEmpty()) {
            this.p = Collections.emptyList();
            return;
        }
        this.p = new ArrayList(b.size());
        for (ae ajVar : b) {
            this.p.add(aj.a(new aj(ajVar)));
        }
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public Date c() {
        return new Date(this.c.getTime());
    }

    public Date d() {
        return new Date(this.d.getTime());
    }

    public GeoCoordinate e() {
        return this.e;
    }

    public String f() {
        return this.f;
    }

    public int g() {
        return this.g;
    }

    public double h() {
        return this.h;
    }

    public String i() {
        return this.i;
    }

    public int j() {
        return this.j;
    }

    public int k() {
        return this.k;
    }

    public float l() {
        return this.l;
    }

    public int m() {
        return this.m;
    }

    public MissingCoverage n() {
        return this.n;
    }

    public Collection<Operator> o() {
        return Collections.unmodifiableCollection(this.o);
    }

    public Collection<Provider> p() {
        return Collections.unmodifiableCollection(this.p);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        k kVar = (k) obj;
        if (this.g == kVar.g && Double.compare(kVar.h, this.h) == 0 && this.k == kVar.k && Float.compare(kVar.l, this.l) == 0 && this.m == kVar.m && this.a.equals(kVar.a) && this.b.equals(kVar.b) && this.c.equals(kVar.c) && this.d.equals(kVar.d) && this.e.equals(kVar.e) && this.f.equals(kVar.f) && this.i.equals(kVar.i) && this.j == kVar.j && this.n != null) {
            return this.n.equals(kVar.n);
        }
        if (kVar.n == null && this.o.equals(kVar.o) && this.p.equals(kVar.p)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((((((((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode()) * 31) + this.e.hashCode()) * 31) + this.f.hashCode()) * 31) + this.g;
        long doubleToLongBits = Double.doubleToLongBits(this.h);
        hashCode = (((((((hashCode * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31) + this.i.hashCode()) * 31) + this.j) * 31) + this.k;
        doubleToLongBits = (long) Float.floatToIntBits(this.l);
        return (((((this.n != null ? this.n.hashCode() : 0) + (((((hashCode * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31) + this.m) * 31)) * 31) + this.o.hashCode()) * 31) + this.p.hashCode();
    }

    public static void a(am<City, k> amVar) {
        q = amVar;
    }

    static City a(k kVar) {
        if (kVar != null) {
            return (City) q.a(kVar);
        }
        return null;
    }

    static {
        ce.a(City.class);
    }
}
