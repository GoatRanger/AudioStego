package com.here.android.mpa.routing;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.RoutePlanImpl;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.Online;

@Online
public final class RoutePlan {
    private RoutePlanImpl a;

    public RoutePlan() {
        this.a = new RoutePlanImpl();
    }

    public RoutePlan(RoutePlan routePlan) {
        this.a = new RoutePlanImpl(routePlan);
    }

    private RoutePlan(RoutePlanImpl routePlanImpl) {
        this.a = routePlanImpl;
    }

    @Deprecated
    public RoutePlan addWaypoint(GeoCoordinate geoCoordinate) {
        this.a.a(geoCoordinate);
        return this;
    }

    @HybridPlus
    public RoutePlan addWaypoint(RouteWaypoint routeWaypoint) {
        this.a.a(routeWaypoint);
        return this;
    }

    @Deprecated
    public RoutePlan insertWaypoint(GeoCoordinate geoCoordinate, int i) {
        this.a.a(geoCoordinate, i);
        return this;
    }

    @HybridPlus
    public RoutePlan insertWaypoint(RouteWaypoint routeWaypoint, int i) {
        this.a.insertWaypoint(routeWaypoint, i);
        return this;
    }

    public RoutePlan removeWaypoint(int i) {
        this.a.removeWaypoint(i);
        return this;
    }

    public RoutePlan removeAllWaypoints() {
        this.a.removeAllWaypoints();
        return this;
    }

    @Deprecated
    public GeoCoordinate getWaypointAt(int i) {
        return this.a.a(i);
    }

    @HybridPlus
    public RouteWaypoint getWaypoint(int i) {
        return this.a.b(i);
    }

    public int getWaypointCount() {
        return this.a.getWaypointCount();
    }

    public RoutePlan setRouteOptions(RouteOptions routeOptions) {
        this.a.a(routeOptions);
        return this;
    }

    public RouteOptions getRouteOptions() {
        return this.a.a();
    }

    static {
        RoutePlanImpl.a(new 1(), new 2());
    }

    public int hashCode() {
        return (this.a == null ? 0 : this.a.hashCode()) + 31;
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
        RoutePlan routePlan = (RoutePlan) obj;
        if (this.a == null) {
            if (routePlan.a != null) {
                return false;
            }
            return true;
        } else if (this.a.equals(routePlan.a)) {
            return true;
        } else {
            return false;
        }
    }
}
