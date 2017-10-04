package com.here.android.mpa.routing;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.RouteWaypointImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.k;

public final class RouteWaypoint {
    private RouteWaypointImpl a;

    @HybridPlus
    public enum Type {
        STOP_WAYPOINT(0),
        VIA_WAYPOINT(1);
        
        private int a;

        private Type(int i) {
            this.a = i;
        }

        public int value() {
            return this.a;
        }
    }

    @HybridPlus
    public RouteWaypoint(GeoCoordinate geoCoordinate) {
        this.a = new RouteWaypointImpl(geoCoordinate);
    }

    private RouteWaypoint(RouteWaypointImpl routeWaypointImpl) {
        this.a = routeWaypointImpl;
    }

    @HybridPlus
    public RouteWaypoint setWaypointType(Type type) {
        this.a.a(type);
        return this;
    }

    @HybridPlus
    public Type getWaypointType() {
        return this.a.e();
    }

    @HybridPlus
    public RouteWaypoint setOriginalPosition(GeoCoordinate geoCoordinate) {
        this.a.a(geoCoordinate);
        return this;
    }

    @HybridPlus
    public GeoCoordinate getOriginalPosition() {
        return this.a.f();
    }

    @HybridPlus
    public RouteWaypoint setNavigablePosition(GeoCoordinate geoCoordinate) {
        this.a.b(geoCoordinate);
        return this;
    }

    @HybridPlus
    public GeoCoordinate getNavigablePosition() {
        return this.a.g();
    }

    static {
        RouteWaypointImpl.a(new k<RouteWaypoint, RouteWaypointImpl>() {
            public RouteWaypointImpl a(RouteWaypoint routeWaypoint) {
                return routeWaypoint.a;
            }
        }, new am<RouteWaypoint, RouteWaypointImpl>() {
            public RouteWaypoint a(RouteWaypointImpl routeWaypointImpl) {
                return routeWaypointImpl != null ? new RouteWaypoint(routeWaypointImpl) : null;
            }
        });
    }

    public int hashCode() {
        int i = 0;
        GeoCoordinate originalPosition = getOriginalPosition();
        GeoCoordinate navigablePosition = getNavigablePosition();
        int hashCode = ((originalPosition == null ? 0 : originalPosition.hashCode()) + ((getWaypointType().value() + 31) * 31)) * 31;
        if (navigablePosition != null) {
            i = navigablePosition.hashCode();
        }
        return i + hashCode;
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
        RouteWaypoint routeWaypoint = (RouteWaypoint) obj;
        if (getWaypointType() != routeWaypoint.getWaypointType()) {
            return false;
        }
        GeoCoordinate originalPosition = getOriginalPosition();
        if (!originalPosition != null ? routeWaypoint.getOriginalPosition() == null : originalPosition.equals(routeWaypoint.getOriginalPosition())) {
            return false;
        }
        originalPosition = getNavigablePosition();
        if (originalPosition == null) {
            if (routeWaypoint.getNavigablePosition() == null) {
                return true;
            }
        } else if (originalPosition.equals(routeWaypoint.getNavigablePosition())) {
            return true;
        }
        return false;
    }
}
