package com.nokia.maps.a;

import com.here.a.a.a.a.a;
import com.here.a.a.a.a.m;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.urbanmobility.AccessPoint;
import com.nokia.maps.am;
import com.nokia.maps.ce;

public class d {
    private static am<AccessPoint, d> d;
    private GeoCoordinate a;
    private String b;
    private String c;

    protected d(a aVar) {
        m mVar = aVar.a;
        this.a = new GeoCoordinate(mVar.a, mVar.b);
        this.b = (String) aVar.c.c("");
        this.c = (String) aVar.b.c("");
    }

    public GeoCoordinate a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        d dVar = (d) obj;
        if (this.a.equals(dVar.a) && this.b.equals(dVar.b) && this.c.equals(dVar.c)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
    }

    public static void a(am<AccessPoint, d> amVar) {
        d = amVar;
    }

    static AccessPoint a(d dVar) {
        if (dVar != null) {
            return (AccessPoint) d.a(dVar);
        }
        return null;
    }

    static {
        ce.a(AccessPoint.class);
    }
}
