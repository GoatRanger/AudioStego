package com.here.android.mpa.routing;

import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.RoadElement;
import com.nokia.maps.RouteImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.Internal;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;
import java.util.List;

public class Route {
    @Online
    public static final int WHOLE_ROUTE = 268435455;
    private final RouteImpl a;

    @HybridPlus
    public enum EtaValidity {
        INVALID(0),
        VALID(1),
        DTA_VALID(2),
        DTA_LATE(3),
        DTA_IN_PAST(4);
        
        int a;

        private EtaValidity(int i) {
            this.a = i;
        }

        public int value() {
            return this.a;
        }
    }

    @Online
    public GeoBoundingBox getBoundingBox() {
        return this.a.b();
    }

    @Online
    public int getLength() {
        return this.a.getLength();
    }

    @Online
    public List<Maneuver> getManeuvers() {
        return this.a.d();
    }

    @Online
    public List<GeoCoordinate> getRouteGeometry() {
        return this.a.e();
    }

    @Online
    public Maneuver getFirstManeuver() {
        return this.a.f();
    }

    @Online
    public List<GeoCoordinate> getWaypoints() {
        return this.a.g();
    }

    @HybridPlus
    public List<RouteWaypoint> getRouteWaypoints() {
        return this.a.h();
    }

    @Online
    public RoutePlan getRoutePlan() {
        return this.a.c();
    }

    @Online
    public GeoCoordinate getStart() {
        return this.a.i();
    }

    @Online
    public GeoCoordinate getDestination() {
        return this.a.k();
    }

    @HybridPlus
    public RouteElements getRouteElements() {
        return this.a.j();
    }

    @HybridPlus
    public RouteElements getRouteElements(Maneuver maneuver) {
        return this.a.a(maneuver);
    }

    @HybridPlus
    public RouteElements getRouteElementsFromDuration(long j) {
        return this.a.a(j);
    }

    @HybridPlus
    public RouteElements getRouteElementsFromDuration(long j, long j2) {
        return this.a.a(j, j2);
    }

    @HybridPlus
    public RouteElements getRouteElementsFromLength(int i) {
        return this.a.a(i);
    }

    @HybridPlus
    public RouteElements getRouteElementsFromLength(int i, int i2) {
        return this.a.a(i, i2);
    }

    @Online
    public RouteTta getTta(TrafficPenaltyMode trafficPenaltyMode, int i) {
        return this.a.a(trafficPenaltyMode, i);
    }

    @Online
    public int getSublegCount() {
        return this.a.getSublegCount();
    }

    Route(RouteImpl routeImpl) {
        this.a = routeImpl;
    }

    @HybridPlus
    public TransitRouteSourceAttribution getTransitRouteSourceAttribution() {
        return this.a.m();
    }

    @Internal
    public a getFirstIntersectionAfter(RoadElement roadElement, int i, int i2) {
        return this.a.a(roadElement, i, i2);
    }

    @Internal
    public List<a> getAllIntersectionsAfter(RoadElement roadElement, int i, int i2) {
        return this.a.b(roadElement, i, i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (((Route) obj).hashCode() != hashCode()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    static {
        RouteImpl.a(new k<Route, RouteImpl>() {
            public RouteImpl a(Route route) {
                return route.a;
            }
        }, new am<Route, RouteImpl>() {
            public Route a(RouteImpl routeImpl) {
                return routeImpl != null ? new Route(routeImpl) : null;
            }
        });
    }
}
