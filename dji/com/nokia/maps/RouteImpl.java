package com.nokia.maps;

import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.RoadElement;
import com.here.android.mpa.routing.Maneuver;
import com.here.android.mpa.routing.Route;
import com.here.android.mpa.routing.Route.TrafficPenaltyMode;
import com.here.android.mpa.routing.RouteElements;
import com.here.android.mpa.routing.RoutePlan;
import com.here.android.mpa.routing.RouteTta;
import com.here.android.mpa.routing.RouteWaypoint;
import com.here.android.mpa.routing.TransitRouteSourceAttribution;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Online
public class RouteImpl extends BaseNativeObject {
    private static k<Route, RouteImpl> b = null;
    private static am<Route, RouteImpl> c = null;
    private cq a = new cq(RouteImpl.class.getName());
    private a d;
    private TrafficPenaltyMode e = TrafficPenaltyMode.DISABLED;

    public enum a {
        MOS_ROUTE,
        ENHANCED_TRANSIT_ROUTE,
        URBAN_MOBILITY_ROUTE;

        public static a[] a() {
            return (a[]) d.clone();
        }
    }

    private native void createRouteNative();

    static native RouteImpl deserailize(byte[] bArr) throws Exception;

    private native void destroyRouteNative();

    private native ec[] getAllIntersectionsAfterRoadElement(RoadElementImpl roadElementImpl, int i, int i2);

    private native GeoBoundingBoxImpl getBoundingBoxNative();

    private native RouteTtaImpl getDurationWithTrafficNative(int i, int i2);

    private native int getEtaNative(Date date, Date date2, Date date3, int i);

    private native ec getFirstIntersectionAfterRoadElement(RoadElementImpl roadElementImpl, int i, int i2);

    private native ManeuverImpl[] getManeuversNative();

    private native RouteElementsImpl getRouteElementsFromDuration(long j);

    private native RouteElementsImpl getRouteElementsFromDuration(long j, long j2);

    private native RouteElementsImpl getRouteElementsFromLength(int i);

    private native RouteElementsImpl getRouteElementsFromLength(int i, int i2);

    private native RouteElementsImpl getRouteElementsFromManeuver(ManeuverImpl maneuverImpl);

    private native RouteElementsImpl getRouteElementsNative();

    private native GeoCoordinateImpl[] getRouteGeometryNative();

    private native RoutePlanImpl getRoutePlanNative();

    static native byte[] serialize(RouteImpl routeImpl) throws Exception;

    public native int getLength();

    public native int getSublegCount();

    static {
        ce.a(Route.class);
    }

    public static void a(k<Route, RouteImpl> kVar, am<Route, RouteImpl> amVar) {
        b = kVar;
        c = amVar;
    }

    static RouteImpl a(Route route) {
        return (RouteImpl) b.a(route);
    }

    @OnlineNative
    static Route create(RouteImpl routeImpl) {
        if (routeImpl != null) {
            return (Route) c.a(routeImpl);
        }
        return null;
    }

    private RouteImpl() {
        createRouteNative();
        this.d = a.MOS_ROUTE;
    }

    @OnlineNative
    private RouteImpl(int i) {
        this.nativeptr = i;
        this.d = a.MOS_ROUTE;
    }

    protected RouteImpl(a aVar) {
        boolean z;
        dy.a((Object) aVar, "Route type is null");
        if (aVar != a.MOS_ROUTE) {
            z = true;
        } else {
            z = false;
        }
        dy.a(z, "Route type cannot be MOS_ROUTE");
        this.nativeptr = 0;
        this.d = aVar;
    }

    a a() {
        return this.d;
    }

    protected void finalize() {
        if (this.nativeptr != 0) {
            destroyRouteNative();
        }
    }

    public GeoBoundingBox b() {
        return GeoBoundingBoxImpl.create(getBoundingBoxNative());
    }

    public RoutePlan c() {
        return RoutePlanImpl.a(getRoutePlanNative());
    }

    public List<Maneuver> d() {
        return ManeuverImpl.a(getManeuversNative());
    }

    public List<GeoCoordinate> e() {
        return GeoCoordinateImpl.b(Arrays.asList(getRouteGeometryNative()));
    }

    public Maneuver f() {
        List d = d();
        return d.size() > 0 ? (Maneuver) d.get(0) : null;
    }

    public List<GeoCoordinate> g() {
        List<GeoCoordinate> arrayList = new ArrayList();
        RoutePlanImpl routePlanNative = getRoutePlanNative();
        int waypointCount = routePlanNative.getWaypointCount();
        for (int i = 0; i < waypointCount; i++) {
            arrayList.add(routePlanNative.a(i));
        }
        return arrayList;
    }

    @HybridPlus
    public List<RouteWaypoint> h() {
        List<RouteWaypoint> arrayList = new ArrayList();
        RoutePlanImpl routePlanNative = getRoutePlanNative();
        int waypointCount = routePlanNative.getWaypointCount();
        for (int i = 0; i < waypointCount; i++) {
            arrayList.add(routePlanNative.b(i));
        }
        return arrayList;
    }

    public GeoCoordinate i() {
        RoutePlanImpl routePlanNative = getRoutePlanNative();
        if (routePlanNative.getWaypointCount() > 0) {
            return routePlanNative.a(0);
        }
        return null;
    }

    public RouteElements j() {
        return RouteElementsImpl.a(getRouteElementsNative());
    }

    public RouteElements a(Maneuver maneuver) {
        return RouteElementsImpl.a(getRouteElementsFromManeuver(ManeuverImpl.a(maneuver)));
    }

    public RouteElements a(long j) {
        return RouteElementsImpl.a(getRouteElementsFromDuration(j));
    }

    public RouteElements a(long j, long j2) {
        return RouteElementsImpl.a(getRouteElementsFromDuration(j, j2));
    }

    public RouteElements a(int i) {
        return RouteElementsImpl.a(getRouteElementsFromLength(i));
    }

    public RouteElements a(int i, int i2) {
        return RouteElementsImpl.a(getRouteElementsFromLength(i, i2));
    }

    public GeoCoordinate k() {
        RoutePlanImpl routePlanNative = getRoutePlanNative();
        int waypointCount = routePlanNative.getWaypointCount();
        if (waypointCount >= 2) {
            return routePlanNative.a(waypointCount - 1);
        }
        return null;
    }

    void a(TrafficPenaltyMode trafficPenaltyMode) {
        this.e = trafficPenaltyMode;
    }

    TrafficPenaltyMode l() {
        return this.e;
    }

    public RouteTta a(TrafficPenaltyMode trafficPenaltyMode, int i) {
        boolean z = false;
        dy.a(trafficPenaltyMode != TrafficPenaltyMode.AVOID_CONGESTION, "AVOID_CONGESTION not allowed for Route.getTta(TrafficPenaltyMode, int");
        if (i == Route.WHOLE_ROUTE || (i >= 0 && i < getSublegCount())) {
            z = true;
        }
        dy.a(z, "subleg must be be >= 0 and less than the number of subleg(s)");
        return RouteTtaImpl.a(getDurationWithTrafficNative(trafficPenaltyMode.value(), i));
    }

    public TransitRouteSourceAttribution m() {
        return null;
    }

    public com.here.android.mpa.routing.a a(RoadElement roadElement, int i, int i2) {
        boolean z;
        boolean z2 = true;
        dy.a((Object) roadElement, "Road element provided is null");
        if (i >= 0) {
            z = true;
        } else {
            z = false;
        }
        dy.a(z, "Minimim distance must be >= 0");
        if (i > i2) {
            z2 = false;
        }
        dy.a(z2, "Maximum distance must be >= minimum distance");
        return ec.a(getFirstIntersectionAfterRoadElement(RoadElementImpl.a(roadElement), i, i2));
    }

    public List<com.here.android.mpa.routing.a> b(RoadElement roadElement, int i, int i2) {
        boolean z;
        boolean z2 = true;
        dy.a((Object) roadElement, "Road element provided is null");
        if (i >= 0) {
            z = true;
        } else {
            z = false;
        }
        dy.a(z, "Minimim distance must be >= 0");
        if (i > i2) {
            z2 = false;
        }
        dy.a(z2, "Maximum distance must be >= minimum distance");
        return ec.a(Arrays.asList(getAllIntersectionsAfterRoadElement(RoadElementImpl.a(roadElement), i, i2)));
    }

    public int hashCode() {
        return this.nativeptr + 31;
    }
}
