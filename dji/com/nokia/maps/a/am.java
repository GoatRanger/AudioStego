package com.nokia.maps.a;

import com.here.a.a.a.a.ah;
import com.here.a.a.a.a.aj;
import com.here.a.a.a.a.ao;
import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.routing.Maneuver;
import com.here.android.mpa.routing.Route;
import com.here.android.mpa.routing.Route.TrafficPenaltyMode;
import com.here.android.mpa.routing.RoutePlan;
import com.here.android.mpa.routing.RouteTta;
import com.here.android.mpa.routing.RouteTta.Detail;
import com.here.android.mpa.routing.RouteWaypoint;
import com.here.android.mpa.routing.UMRoute;
import com.here.android.mpa.urbanmobility.Location;
import com.here.android.mpa.urbanmobility.RouteSection;
import com.here.android.mpa.urbanmobility.TicketCollection;
import com.nokia.maps.GeoPolylineImpl;
import com.nokia.maps.RouteImpl;
import com.nokia.maps.RouteImpl.a;
import com.nokia.maps.RoutePlanImpl;
import com.nokia.maps.RouteTtaImpl;
import com.nokia.maps.ce;
import com.nokia.maps.dy;
import com.nokia.maps.ef;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

public class am extends RouteImpl {
    private static com.nokia.maps.am<UMRoute, am> p = null;
    private final RoutePlan a;
    private GeoBoundingBox b;
    private int c;
    private List<GeoCoordinate> d;
    private final GeoCoordinate e;
    private final GeoCoordinate f;
    private final List<GeoCoordinate> g = Arrays.asList(new GeoCoordinate[]{this.e, this.f});
    private final int h;
    private final List<RouteSection> i;
    private final String j;
    private final int k;
    private final long l;
    private final Location m;
    private final Location n;
    private final List<TicketCollection> o;

    public am(RoutePlan routePlan, ah ahVar) {
        super(a.URBAN_MOBILITY_ROUTE);
        this.a = RoutePlanImpl.a(new RoutePlanImpl(routePlan));
        this.h = ahVar.b().size();
        this.e = u.a(ahVar.d.a());
        this.f = u.a(ahVar.e.a());
        this.j = ahVar.a;
        this.k = ahVar.b;
        this.l = ahVar.c;
        this.m = aa.a(new aa(ahVar.d));
        this.n = aa.a(new aa(ahVar.e));
        List<aj> b = ahVar.b();
        if (b.isEmpty()) {
            this.i = Collections.emptyList();
        } else {
            this.i = new ArrayList(b.size());
            for (aj arVar : b) {
                this.i.add(ar.a(new ar(arVar)));
            }
        }
        List<ao> a = ahVar.a();
        if (a.isEmpty()) {
            this.o = Collections.emptyList();
        } else {
            this.o = new ArrayList(a.size());
            for (ao ayVar : a) {
                this.o.add(ay.a(new ay(ayVar)));
            }
        }
        w();
    }

    public String n() {
        return this.j;
    }

    public int o() {
        return this.k;
    }

    public long p() {
        return this.l;
    }

    public Location q() {
        return this.m;
    }

    public Location r() {
        return this.n;
    }

    public List<RouteSection> s() {
        return Collections.unmodifiableList(this.i);
    }

    public List<TicketCollection> v() {
        return Collections.unmodifiableList(this.o);
    }

    public GeoBoundingBox b() {
        return this.b;
    }

    public int getLength() {
        return this.c;
    }

    public List<Maneuver> d() {
        return Collections.emptyList();
    }

    public List<GeoCoordinate> e() {
        return this.d;
    }

    public List<GeoCoordinate> g() {
        return this.g;
    }

    public List<RouteWaypoint> h() {
        List<RouteWaypoint> arrayList = new ArrayList();
        int waypointCount = this.a.getWaypointCount();
        for (int i = 0; i < waypointCount; i++) {
            arrayList.add(this.a.getWaypoint(i));
        }
        return arrayList;
    }

    public RoutePlan c() {
        return this.a;
    }

    public GeoCoordinate i() {
        return this.e;
    }

    public GeoCoordinate k() {
        return this.f;
    }

    public RouteTta a(TrafficPenaltyMode trafficPenaltyMode, int i) {
        long j;
        boolean z = i == Route.WHOLE_ROUTE || i <= this.h || i < 0;
        dy.a(z, "Sub-leg is out-of-range. Use Route. WHOLE_ROUTE to calculate duration for the whole route");
        if (i == Route.WHOLE_ROUTE) {
            j = this.l;
        } else {
            j = ((RouteSection) this.i.get(i)).getDuration();
        }
        return RouteTtaImpl.a(new ef(EnumSet.noneOf(Detail.class), (int) j));
    }

    public int getSublegCount() {
        return this.h;
    }

    private void w() {
        this.c = 0;
        this.d = new ArrayList();
        this.d.add(this.e);
        for (RouteSection routeSection : this.i) {
            this.c += routeSection.getDistance();
            if (routeSection.getDepartureLocation().getAddress() != null) {
                a(routeSection.getDepartureLocation().getAddress().getCoordinate());
            }
            if (routeSection.getDepartureLocation().getStation() != null) {
                a(routeSection.getDepartureLocation().getStation().getCoordinate());
            }
            if (routeSection.getDepartureLocation().getAccessPoint() != null) {
                a(routeSection.getDepartureLocation().getAccessPoint().getCoordinate());
            }
            if (routeSection.getGeometry().size() > 1) {
                this.d.addAll(routeSection.getGeometry());
            }
            if (routeSection.getArrivalLocation().getAccessPoint() != null) {
                a(routeSection.getArrivalLocation().getAccessPoint().getCoordinate());
            }
            if (routeSection.getArrivalLocation().getStation() != null) {
                a(routeSection.getArrivalLocation().getStation().getCoordinate());
            }
            if (routeSection.getArrivalLocation().getAddress() != null) {
                a(routeSection.getArrivalLocation().getAddress().getCoordinate());
            }
        }
        a(this.f);
        GeoPolylineImpl geoPolylineImpl = new GeoPolylineImpl(this.d);
        this.b = geoPolylineImpl.getNumberOfPoints() > 0 ? geoPolylineImpl.a() : new GeoBoundingBox(new GeoCoordinate(0.0d, 0.0d), new GeoCoordinate(0.0d, 0.0d));
    }

    private void a(GeoCoordinate geoCoordinate) {
        if (u.a((GeoCoordinate) this.d.get(this.d.size() - 1), geoCoordinate)) {
            this.d.add(geoCoordinate);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof am)) {
            return false;
        }
        am amVar = (am) obj;
        boolean z = super.equals(obj) && this.c == amVar.c && this.h == amVar.h && this.k == amVar.k && this.l == amVar.l && this.a.equals(amVar.a) && this.b.equals(amVar.b) && this.d.equals(amVar.d) && this.e.equals(amVar.e) && this.f.equals(amVar.f) && this.g.equals(amVar.g) && this.i.equals(amVar.i) && this.j.equals(amVar.j) && this.m.equals(amVar.m) && this.n.equals(amVar.n) && this.o.equals(amVar.o);
        return z;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((super.hashCode() * 31) + this.a.hashCode()) * 31) + this.b.hashCode()) * 31) + this.c) * 31) + this.d.hashCode()) * 31) + this.e.hashCode()) * 31) + this.f.hashCode()) * 31) + this.g.hashCode()) * 31) + this.h) * 31) + this.i.hashCode()) * 31) + this.j.hashCode()) * 31) + this.k) * 31) + ((int) (this.l ^ (this.l >>> 32)))) * 31) + this.m.hashCode()) * 31) + this.n.hashCode()) * 31) + this.o.hashCode();
    }

    static {
        ce.a(UMRoute.class);
    }

    public static void a(com.nokia.maps.am<UMRoute, am> amVar) {
        p = amVar;
    }

    public static UMRoute a(am amVar) {
        if (amVar != null) {
            return (UMRoute) p.a(amVar);
        }
        return null;
    }
}
