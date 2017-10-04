package com.nokia.maps.a;

import com.here.a.a.a.a.f;
import com.here.a.a.a.a.g;
import com.here.android.mpa.urbanmobility.City;
import com.here.android.mpa.urbanmobility.CityCoverageResult;
import com.nokia.maps.am;
import com.nokia.maps.ce;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class j extends m {
    private static am<CityCoverageResult, j> b;
    private List<City> a;

    public j(g gVar) {
        super(gVar);
        List<f> a = gVar.a();
        if (a.isEmpty()) {
            this.a = Collections.emptyList();
            return;
        }
        this.a = new ArrayList(a.size());
        for (f kVar : a) {
            this.a.add(k.a(new k(kVar)));
        }
    }

    public List<City> a() {
        return Collections.unmodifiableList(this.a);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        boolean z = super.equals(obj) && this.a.equals(((j) obj).a);
        return z;
    }

    public int hashCode() {
        return (super.hashCode() * 31) + this.a.hashCode();
    }

    public static void a(am<CityCoverageResult, j> amVar) {
        b = amVar;
    }

    static CityCoverageResult a(j jVar) {
        if (jVar != null) {
            return (CityCoverageResult) b.a(jVar);
        }
        return null;
    }

    static {
        ce.a(CityCoverageResult.class);
    }
}
