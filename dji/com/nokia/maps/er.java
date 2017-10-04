package com.nokia.maps;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Identifier;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.TransitType;
import com.here.android.mpa.routing.TransitManeuver.TransitLineStyle;
import com.here.android.mpa.routing.TransitRouteStop;
import com.nokia.maps.IdentifierImpl.a;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.restrouting.Link;
import com.nokia.maps.restrouting.Stop;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@HybridPlus
public class er extends TransitRouteElementImpl {
    private eq a;
    private TransitRouteStop b;
    private TransitRouteStop c;
    private List<GeoCoordinate> d;
    private Date e;
    private Date f;
    private Double g;
    private Double h;
    private Identifier i;
    private int j;
    private int k;

    er(eq eqVar, Stop stop, Stop stop2, Link link, long j, long j2) {
        this.a = eqVar;
        if (stop == null || stop2 == null || link == null) {
            this.d = new ArrayList();
            return;
        }
        this.c = TransitRouteStopImpl.a(new ew(stop));
        this.b = TransitRouteStopImpl.a(new ew(stop2));
        String str = eqVar.getLineName() + stop.b() + stop2.b();
        this.d = ee.b(link.d());
        long longValue = j + ((j2 - link.g().longValue()) * 1000);
        this.j = stop.c().intValue();
        this.k = this.j;
        long j3 = ((long) (this.j * 1000)) + longValue;
        if (eqVar.w() == 0) {
            this.k += eqVar.x();
            j3 += (long) (eqVar.x() * 1000);
        }
        this.e = new Date(longValue);
        this.f = new Date(j3);
        this.g = link.f();
        this.h = link.g();
        this.i = IdentifierImpl.a(new IdentifierImpl(a.STRING, str));
    }

    public String getDestination() {
        return this.a.getTerminusStopName();
    }

    public List<GeoCoordinate> a() {
        return this.d;
    }

    public List<GeoCoordinate> b() {
        return this.d;
    }

    public String getLineName() {
        return this.a.getLineName();
    }

    public boolean hasPrimaryLineColor() {
        return this.a.n();
    }

    public boolean hasSecondaryLineColor() {
        return this.a.o();
    }

    public int c() {
        return this.a.p();
    }

    public int d() {
        return this.a.q();
    }

    public TransitLineStyle e() {
        return this.a.r();
    }

    public String getSystemOfficialName() {
        return this.a.getSystemOfficialName();
    }

    public String getSystemInformalName() {
        return this.a.s();
    }

    public String getSystemShortName() {
        return this.a.getSystemShortName();
    }

    public Image f() {
        return null;
    }

    public Image g() {
        return null;
    }

    public TransitType h() {
        return this.a.getTransitType();
    }

    public String getTransitTypeName() {
        return this.a.getTransitTypeName();
    }

    public final int getVehicleTravelTime() {
        return this.j;
    }

    public int getTotalDuration() {
        return this.k;
    }

    public Date i() {
        return new Date(this.e.getTime());
    }

    public Date j() {
        return new Date(this.f.getTime());
    }

    public TransitRouteStop k() {
        return this.c;
    }

    public TransitRouteStop l() {
        return this.b;
    }

    public final Identifier m() {
        return this.i;
    }

    protected void finalize() {
    }

    Double n() {
        return this.g;
    }

    Double o() {
        return this.h;
    }
}
