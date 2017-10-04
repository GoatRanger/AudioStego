package com.nokia.maps;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.routing.RouteWaypoint;
import com.here.android.mpa.routing.RouteWaypoint.Type;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
public class RouteWaypointImpl extends LocationImpl {
    private static k<RouteWaypoint, RouteWaypointImpl> b = null;
    private static am<RouteWaypoint, RouteWaypointImpl> c = null;
    private cq a = new cq(RouteWaypointImpl.class.getName());

    private native void createRouteWaypointNative(GeoCoordinateImpl geoCoordinateImpl);

    private native void destroyRouteWaypointNative();

    private native GeoCoordinateImpl getNavigablePositionNative();

    private native GeoCoordinateImpl getOriginalPositionNative();

    private native int getWaypointTypeNative();

    private native void setNavigablePositionNative(GeoCoordinateImpl geoCoordinateImpl);

    private native void setOriginalPositionNative(GeoCoordinateImpl geoCoordinateImpl);

    private native void setWaypointTypeNative(int i);

    static {
        ce.a(RouteWaypoint.class);
    }

    public static void a(k<RouteWaypoint, RouteWaypointImpl> kVar, am<RouteWaypoint, RouteWaypointImpl> amVar) {
        b = kVar;
        c = amVar;
    }

    static RouteWaypointImpl a(RouteWaypoint routeWaypoint) {
        return (RouteWaypointImpl) b.a(routeWaypoint);
    }

    static RouteWaypoint a(RouteWaypointImpl routeWaypointImpl) {
        if (routeWaypointImpl != null) {
            return (RouteWaypoint) c.a(routeWaypointImpl);
        }
        return null;
    }

    public RouteWaypointImpl(GeoCoordinate geoCoordinate) {
        createRouteWaypointNative(GeoCoordinateImpl.get(geoCoordinate));
    }

    @HybridPlusNative
    private RouteWaypointImpl(int i) {
        super(i);
    }

    protected void a() {
        destroyRouteWaypointNative();
    }

    public void a(Type type) {
        setWaypointTypeNative(type.ordinal());
    }

    public Type e() {
        switch (getWaypointTypeNative()) {
            case 1:
                return Type.VIA_WAYPOINT;
            default:
                return Type.STOP_WAYPOINT;
        }
    }

    public void a(GeoCoordinate geoCoordinate) {
        dy.a((Object) geoCoordinate, "Geo-coordinates provided cannot be null");
        setOriginalPositionNative(GeoCoordinateImpl.get(geoCoordinate));
    }

    public GeoCoordinate f() {
        return GeoCoordinateImpl.create(getOriginalPositionNative());
    }

    public void b(GeoCoordinate geoCoordinate) {
        dy.a((Object) geoCoordinate, "Geo-coordinates provided cannot be null");
        setNavigablePositionNative(GeoCoordinateImpl.get(geoCoordinate));
    }

    public GeoCoordinate g() {
        return GeoCoordinateImpl.create(getNavigablePositionNative());
    }
}
