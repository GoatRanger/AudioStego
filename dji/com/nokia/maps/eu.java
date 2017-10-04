package com.nokia.maps;

import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.RoadElement;
import com.here.android.mpa.routing.Maneuver;
import com.here.android.mpa.routing.Route.TrafficPenaltyMode;
import com.here.android.mpa.routing.RouteElement;
import com.here.android.mpa.routing.RouteElements;
import com.here.android.mpa.routing.RouteOptions.TransportMode;
import com.here.android.mpa.routing.RoutePlan;
import com.here.android.mpa.routing.RouteTta;
import com.here.android.mpa.routing.RouteTta.Detail;
import com.here.android.mpa.routing.RouteWaypoint;
import com.here.android.mpa.routing.TransitRouteSourceAttribution;
import com.nokia.maps.RouteImpl.a;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.restrouting.Leg;
import com.nokia.maps.restrouting.Link;
import com.nokia.maps.restrouting.PublicTransportLine;
import com.nokia.maps.restrouting.Route;
import com.nokia.maps.restrouting.SourceAttribution;
import com.nokia.maps.restrouting.SummaryByCountry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;

@HybridPlus
public class eu extends RouteImpl {
    private RoutePlan a;
    private GeoBoundingBox b;
    private int c;
    private List<GeoCoordinate> d = new ArrayList();
    private List<Maneuver> e = new ArrayList();
    private RouteElements f;
    private List<GeoCoordinate> g;
    private Double h = Double.valueOf(0.0d);
    private Double i;
    private Double j;
    private int k;
    private TransitRouteSourceAttribution l;

    protected eu(RoutePlan routePlan, Route route, SourceAttribution sourceAttribution) {
        super(a.ENHANCED_TRANSIT_ROUTE);
        this.a = RoutePlanImpl.a(new RoutePlanImpl(routePlan));
        this.g = new ArrayList();
        this.k = route.b().size();
        for (int i = 0; i < this.a.getWaypointCount(); i++) {
            this.g.add(this.a.getWaypointAt(i));
        }
        this.b = ee.a(route.a());
        this.c = route.e().a().intValue();
        for (Leg leg : route.b()) {
            for (Link d : leg.b()) {
                this.d.addAll(ee.b(d.d()));
            }
            this.h = Double.valueOf(leg.c().doubleValue() + this.h.doubleValue());
        }
        this.i = route.e().b();
        this.j = route.e().a();
        a(route);
        if (sourceAttribution != null) {
            this.l = ev.a(new ev(sourceAttribution));
        }
    }

    public GeoBoundingBox b() {
        return this.b;
    }

    public int getLength() {
        return this.c;
    }

    public List<Maneuver> d() {
        return this.e;
    }

    public List<GeoCoordinate> e() {
        return this.d;
    }

    public Maneuver f() {
        if (this.e.size() > 0) {
            return (Maneuver) this.e.get(0);
        }
        return null;
    }

    public List<GeoCoordinate> g() {
        return this.g;
    }

    @HybridPlus
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
        if (this.g.size() > 0) {
            return (GeoCoordinate) this.g.get(0);
        }
        return null;
    }

    public GeoCoordinate k() {
        int size = this.g.size();
        if (size > 1) {
            return this.a.getWaypointAt(size - 1);
        }
        return null;
    }

    public RouteElements j() {
        return this.f;
    }

    public RouteElements a(Maneuver maneuver) {
        return RouteElementsImpl.a(new es(maneuver));
    }

    public RouteElements a(long j) {
        return a(0, j);
    }

    public RouteElements a(long j, long j2) {
        if (j < 0) {
            j = 0;
        }
        if (j2 < 0) {
            j2 = 0;
        }
        double doubleValue = this.h.doubleValue() - ((double) j);
        double d = doubleValue - ((double) j2);
        List arrayList = new ArrayList();
        for (RouteElement a : this.f.getElements()) {
            RouteElementImpl routeElementImpl = (eb) RouteElementImpl.a(a);
            double doubleValue2 = routeElementImpl.g().doubleValue();
            if (d <= doubleValue2 && doubleValue2 <= doubleValue) {
                arrayList.add(RouteElementImpl.a(routeElementImpl));
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return RouteElementsImpl.a(new es(arrayList));
    }

    public RouteElements a(int i) {
        return a(0, i);
    }

    public RouteElements a(int i, int i2) {
        if (i < 0) {
            i = 0;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        double doubleValue = this.j.doubleValue() - ((double) i);
        double d = doubleValue - ((double) i2);
        List arrayList = new ArrayList();
        for (RouteElement a : this.f.getElements()) {
            RouteElementImpl routeElementImpl = (eb) RouteElementImpl.a(a);
            double doubleValue2 = routeElementImpl.f().doubleValue();
            if (d <= doubleValue2 && doubleValue2 <= doubleValue) {
                arrayList.add(RouteElementImpl.a(routeElementImpl));
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return RouteElementsImpl.a(new es(arrayList));
    }

    public RouteTta a(TrafficPenaltyMode trafficPenaltyMode, int i) {
        boolean z = trafficPenaltyMode == TrafficPenaltyMode.OPTIMAL || trafficPenaltyMode == TrafficPenaltyMode.AVOID_CONGESTION;
        return a(z, i);
    }

    public int getSublegCount() {
        return this.k;
    }

    private void a(Route route) {
        Double valueOf = Double.valueOf(0.0d);
        Double valueOf2 = Double.valueOf(0.0d);
        List<Leg> b = route.b();
        List<SummaryByCountry> f = route.f();
        int i = 0;
        Double d = valueOf;
        valueOf = valueOf2;
        Object obj = null;
        for (Leg leg : b) {
            List<Link> b2 = leg.b();
            Link link = null;
            Object obj2 = obj;
            Double d2 = valueOf;
            Double d3 = d;
            for (com.nokia.maps.restrouting.Maneuver maneuver : leg.a()) {
                if (ee.h(maneuver.b())) {
                    obj2 = 1;
                } else {
                    Link link2;
                    PublicTransportLine publicTransportLine;
                    List arrayList = new ArrayList();
                    for (Link link22 : b2) {
                        if (maneuver.i().equals(link22.h())) {
                            arrayList.add(link22);
                        }
                    }
                    b2.removeAll(arrayList);
                    String r = ee.g(maneuver.b()) ? maneuver.r() : maneuver.q();
                    if (r.isEmpty() && arrayList.size() > 0 && !((Link) arrayList.get(0)).m().isEmpty()) {
                        r = ((Link) arrayList.get(0)).m();
                    }
                    if (!r.isEmpty()) {
                        for (PublicTransportLine publicTransportLine2 : route.c()) {
                            if (publicTransportLine2.i().equals(r)) {
                                publicTransportLine = publicTransportLine2;
                                break;
                            }
                        }
                    }
                    publicTransportLine = null;
                    String str = "";
                    for (SummaryByCountry summaryByCountry : f) {
                        if (d3.doubleValue() < summaryByCountry.b().doubleValue()) {
                            str = summaryByCountry.a();
                            break;
                        }
                    }
                    TransitManeuverImpl eqVar = new eq(i, maneuver, publicTransportLine, arrayList, link, d3.intValue(), d2.intValue(), str);
                    if (obj2 != null && eqVar.e() == TransportMode.PEDESTRIAN) {
                        eqVar.B();
                    }
                    this.e.add(TransitManeuverImpl.a(eqVar));
                    d3 = Double.valueOf(d3.doubleValue() + maneuver.f().doubleValue());
                    d2 = maneuver.f();
                    if (arrayList.size() > 0) {
                        link22 = (Link) arrayList.get(arrayList.size() - 1);
                    } else {
                        link22 = link;
                    }
                    obj2 = null;
                    link = link22;
                }
            }
            i++;
            obj = obj2;
            valueOf = d2;
            d = d3;
        }
        List arrayList2 = new ArrayList();
        for (Maneuver routeElements : this.e) {
            Collection routeElements2 = routeElements.getRouteElements();
            if (routeElements2.size() > 0) {
                arrayList2.addAll(routeElements2);
            }
        }
        this.f = RouteElementsImpl.a(new es(arrayList2));
    }

    private RouteTta a(boolean z, int i) {
        int i2 = 0;
        boolean z2 = i == com.here.android.mpa.routing.Route.WHOLE_ROUTE || i <= this.k;
        dy.a(z2, "Sub-leg is out-of-range. Use Route.WHOLE_ROUTE to calculate duration for the whole route");
        EnumSet noneOf = EnumSet.noneOf(Detail.class);
        eq eqVar;
        Collection a;
        if (i == com.here.android.mpa.routing.Route.WHOLE_ROUTE) {
            if (z) {
                i2 = this.i.intValue();
            } else {
                i2 = this.h.intValue();
            }
            for (Maneuver a2 : this.e) {
                eqVar = (eq) ManeuverImpl.a(a2);
                if (noneOf.size() >= 2) {
                    break;
                }
                a = ee.a(eqVar.A());
                if (!a.isEmpty()) {
                    noneOf.addAll(a);
                }
            }
        } else {
            for (Maneuver a22 : this.e) {
                eqVar = (eq) ManeuverImpl.a(a22);
                if (eqVar.y() > i) {
                    break;
                } else if (eqVar.y() >= i) {
                    int transitTravelTime;
                    if (z) {
                        transitTravelTime = eqVar.getTransitTravelTime();
                    } else {
                        transitTravelTime = eqVar.z();
                    }
                    i2 += transitTravelTime;
                    if (noneOf.size() < 2) {
                        a = ee.a(eqVar.A());
                        if (!a.isEmpty()) {
                            noneOf.addAll(a);
                        }
                    }
                }
            }
        }
        return RouteTtaImpl.a(new ef(noneOf, i2));
    }

    public TransitRouteSourceAttribution m() {
        return this.l;
    }

    public com.here.android.mpa.routing.a a(RoadElement roadElement, int i, int i2) {
        dy.a(false, "getFirstIntersectionAfter operation is not supported for timetabled transit route.");
        return null;
    }

    public List<com.here.android.mpa.routing.a> b(RoadElement roadElement, int i, int i2) {
        dy.a(false, "getAllIntersectionsAfter operation is not supported for timetabled transit route.");
        return new ArrayList();
    }
}
