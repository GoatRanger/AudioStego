package com.nokia.maps.a;

import com.here.a.a.a.a.f;
import com.here.a.a.a.a.h;
import com.here.android.mpa.urbanmobility.City;
import com.here.android.mpa.urbanmobility.CitySearchResult;
import com.nokia.maps.am;
import com.nokia.maps.ce;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class m {
    private static am<CitySearchResult, m> f;
    private Date a;
    private int b;
    private int c;
    private int d;
    private List<City> e;

    public m(h hVar) {
        this.a = hVar.a;
        this.b = hVar.b;
        this.c = hVar.c;
        this.d = hVar.d;
        List<f> b = hVar.b();
        if (b.isEmpty()) {
            this.e = Collections.emptyList();
            return;
        }
        this.e = new ArrayList(b.size());
        for (f kVar : b) {
            this.e.add(k.a(new k(kVar)));
        }
    }

    public Date b() {
        return new Date(this.a.getTime());
    }

    public int c() {
        return this.b;
    }

    public int d() {
        return this.c;
    }

    public int e() {
        return this.d;
    }

    public List<City> f() {
        return Collections.unmodifiableList(this.e);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        m mVar = (m) obj;
        if (this.b == mVar.b && this.c == mVar.c && this.d == mVar.d && this.a.equals(mVar.a) && this.e.equals(mVar.e)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((this.a.hashCode() * 31) + this.b) * 31) + this.c) * 31) + this.d) * 31) + this.e.hashCode();
    }

    public static void b(am<CitySearchResult, m> amVar) {
        f = amVar;
    }

    static CitySearchResult a(m mVar) {
        if (mVar != null) {
            return (CitySearchResult) f.a(mVar);
        }
        return null;
    }

    static {
        ce.a(CitySearchResult.class);
    }
}
