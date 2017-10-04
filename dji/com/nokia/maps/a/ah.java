package com.nokia.maps.a;

import com.here.a.a.a.a.aa;
import com.here.a.a.a.a.f;
import com.here.a.a.a.a.i;
import com.here.a.a.a.a.l;
import com.here.android.mpa.urbanmobility.City;
import com.here.android.mpa.urbanmobility.CoverageType;
import com.here.android.mpa.urbanmobility.ExploredCoverage;
import com.here.android.mpa.urbanmobility.NearbyCoverageResult;
import com.nokia.maps.am;
import com.nokia.maps.ce;

public class ah {
    private static am<NearbyCoverageResult, ah> i;
    private String a;
    private boolean b;
    private int c;
    private int d;
    private int e;
    private CoverageType f;
    private ExploredCoverage g;
    private City h;

    public ah(aa aaVar) {
        ExploredCoverage a;
        City city = null;
        this.a = aaVar.a;
        this.b = aaVar.b;
        this.c = aaVar.c;
        this.d = aaVar.d;
        this.e = aaVar.e;
        this.f = aaVar.f.c() ? n.a((i) aaVar.f.b()) : CoverageType.UNKNOWN;
        if (aaVar.g.c()) {
            a = s.a(new s((l) aaVar.g.b()));
        } else {
            a = null;
        }
        this.g = a;
        if (aaVar.h.c()) {
            city = k.a(new k((f) aaVar.h.b()));
        }
        this.h = city;
    }

    public String a() {
        return this.a;
    }

    public boolean b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public int e() {
        return this.e;
    }

    public CoverageType f() {
        return this.f;
    }

    public ExploredCoverage g() {
        return this.g;
    }

    public City h() {
        return this.h;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ah ahVar = (ah) obj;
        if (this.b == ahVar.b && this.c == ahVar.c && this.d == ahVar.d && this.e == ahVar.e && this.a.equals(ahVar.a) && this.f == ahVar.f && this.g != null) {
            return this.g.equals(ahVar.g);
        }
        if (ahVar.g == null && this.h != null) {
            return this.h.equals(ahVar.h);
        }
        if (ahVar.h != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int hashCode2 = ((((((((((this.b ? 1 : 0) + (this.a.hashCode() * 31)) * 31) + this.c) * 31) + this.d) * 31) + this.e) * 31) + this.f.hashCode()) * 31;
        if (this.g != null) {
            hashCode = this.g.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + hashCode2) * 31;
        if (this.h != null) {
            i = this.h.hashCode();
        }
        return hashCode + i;
    }

    public static void a(am<NearbyCoverageResult, ah> amVar) {
        i = amVar;
    }

    static NearbyCoverageResult a(ah ahVar) {
        if (ahVar != null) {
            return (NearbyCoverageResult) i.a(ahVar);
        }
        return null;
    }

    static {
        ce.a(NearbyCoverageResult.class);
    }
}
