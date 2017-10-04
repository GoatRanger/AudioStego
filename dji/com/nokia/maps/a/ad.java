package com.nokia.maps.a;

import com.here.a.a.a.a.ac;
import com.here.a.a.a.a.i;
import com.here.a.a.a.a.s;
import com.here.a.a.a.a.y;
import com.here.android.mpa.urbanmobility.CoverageType;
import com.here.android.mpa.urbanmobility.Line;
import com.here.android.mpa.urbanmobility.MissingCoverage;
import com.here.android.mpa.urbanmobility.Operator;
import com.nokia.maps.am;
import com.nokia.maps.ce;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ad {
    private static am<MissingCoverage, ad> d;
    private CoverageType a;
    private List<Operator> b;
    private List<Line> c;

    public ad(y yVar) {
        this.a = yVar.a.c() ? n.a((i) yVar.a.b()) : CoverageType.UNKNOWN;
        Collection<ac> b = yVar.b();
        if (b.isEmpty()) {
            this.b = Collections.emptyList();
        } else {
            this.b = new ArrayList(b.size());
            for (ac aiVar : b) {
                this.b.add(ai.a(new ai(aiVar)));
            }
        }
        Collection<s> a = yVar.a();
        if (a.isEmpty()) {
            this.c = Collections.emptyList();
            return;
        }
        this.c = new ArrayList(a.size());
        for (s xVar : a) {
            this.c.add(x.a(new x(xVar)));
        }
    }

    public CoverageType a() {
        return this.a;
    }

    public Collection<Operator> b() {
        return Collections.unmodifiableCollection(this.b);
    }

    public Collection<Line> c() {
        return Collections.unmodifiableCollection(this.c);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ad adVar = (ad) obj;
        if (this.a == adVar.a && this.b.equals(adVar.b) && this.c.equals(adVar.c)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
    }

    public static void a(am<MissingCoverage, ad> amVar) {
        d = amVar;
    }

    static MissingCoverage a(ad adVar) {
        if (adVar != null) {
            return (MissingCoverage) d.a(adVar);
        }
        return null;
    }

    static {
        ce.a(MissingCoverage.class);
    }
}
