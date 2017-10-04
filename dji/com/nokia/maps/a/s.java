package com.nokia.maps.a;

import com.here.a.a.a.a.ak;
import com.here.a.a.a.a.l;
import com.here.android.mpa.urbanmobility.ExploredCoverage;
import com.here.android.mpa.urbanmobility.Line;
import com.here.android.mpa.urbanmobility.Station;
import com.nokia.maps.am;
import com.nokia.maps.ce;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class s {
    private static am<ExploredCoverage, s> f;
    private int a;
    private int b;
    private int c;
    private List<Station> d;
    private List<Line> e;

    public s(l lVar) {
        this.a = lVar.a;
        this.b = lVar.b;
        this.c = lVar.c;
        Collection<ak> a = lVar.a();
        if (a.isEmpty()) {
            this.d = Collections.emptyList();
        } else {
            this.d = new ArrayList(a.size());
            for (ak atVar : a) {
                this.d.add(at.a(new at(atVar)));
            }
        }
        Collection<com.here.a.a.a.a.s> b = lVar.b();
        if (b.isEmpty()) {
            this.e = Collections.emptyList();
            return;
        }
        this.e = new ArrayList(b.size());
        for (com.here.a.a.a.a.s xVar : b) {
            this.e.add(x.a(new x(xVar)));
        }
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public Collection<Station> d() {
        return Collections.unmodifiableCollection(this.d);
    }

    public Collection<Line> e() {
        return Collections.unmodifiableCollection(this.e);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        s sVar = (s) obj;
        if (this.a == sVar.a && this.b == sVar.b && this.c == sVar.c && this.d.equals(sVar.d) && this.e.equals(sVar.e)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((this.a * 31) + this.b) * 31) + this.c) * 31) + this.d.hashCode()) * 31) + this.e.hashCode();
    }

    public static void a(am<ExploredCoverage, s> amVar) {
        f = amVar;
    }

    static ExploredCoverage a(s sVar) {
        if (sVar != null) {
            return (ExploredCoverage) f.a(sVar);
        }
        return null;
    }

    static {
        ce.a(ExploredCoverage.class);
    }
}
