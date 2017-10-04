package com.nokia.maps.a;

import com.here.a.a.a.a.v;
import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.urbanmobility.Maneuver;
import com.here.android.mpa.urbanmobility.Maneuver.Action;
import com.here.android.mpa.urbanmobility.Maneuver.Direction;
import com.nokia.maps.GeoPolylineImpl;
import com.nokia.maps.am;
import com.nokia.maps.ce;
import java.util.List;

public class ab {
    private static am<Maneuver, ab> j;
    GeoBoundingBox a;
    private Action b;
    private Direction c;
    private String d;
    private List<GeoCoordinate> e;
    private String f;
    private String g;
    private long h;
    private int i;

    ab(v vVar) {
        this.b = Action.values()[vVar.a.ordinal()];
        this.c = Direction.values()[vVar.b.ordinal()];
        this.d = vVar.d;
        this.e = u.a(vVar.e);
        this.f = (String) vVar.f.c("");
        this.g = (String) vVar.g.c("");
        GeoPolylineImpl geoPolylineImpl = new GeoPolylineImpl(this.e);
        this.a = geoPolylineImpl.getNumberOfPoints() > 0 ? geoPolylineImpl.a() : new GeoBoundingBox(new GeoCoordinate(0.0d, 0.0d), new GeoCoordinate(0.0d, 0.0d));
        this.h = vVar.c;
        this.i = ((Integer) vVar.h.c(Integer.valueOf(-1))).intValue();
    }

    public Action a() {
        return this.b;
    }

    public Direction b() {
        return this.c;
    }

    public String c() {
        return this.d;
    }

    public List<GeoCoordinate> d() {
        return this.e;
    }

    public String e() {
        return this.g;
    }

    public String f() {
        return this.f;
    }

    public GeoBoundingBox g() {
        return this.a;
    }

    public long h() {
        return this.h;
    }

    public int i() {
        return this.i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ab)) {
            return false;
        }
        ab abVar = (ab) obj;
        if (this.b == abVar.b && this.c == abVar.c && this.d.equals(abVar.d) && this.e.equals(abVar.e) && this.f.equals(abVar.f) && this.g.equals(abVar.g) && this.a.equals(abVar.a) && this.h == abVar.h && this.i == abVar.i) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((this.b.hashCode() * 31) + this.c.hashCode()) * 31) + this.d.hashCode()) * 31) + this.e.hashCode()) * 31) + this.f.hashCode()) * 31) + this.g.hashCode()) * 31) + this.a.hashCode()) * 31) + ((int) (this.h ^ (this.h >>> 32)))) * 31) + this.i;
    }

    public static void a(am<Maneuver, ab> amVar) {
        j = amVar;
    }

    static Maneuver a(ab abVar) {
        if (abVar != null) {
            return (Maneuver) j.a(abVar);
        }
        return null;
    }

    static {
        ce.a(Maneuver.class);
    }
}
