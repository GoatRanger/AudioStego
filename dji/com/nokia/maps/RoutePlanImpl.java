package com.nokia.maps;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.routing.RouteOptions;
import com.here.android.mpa.routing.RoutePlan;
import com.here.android.mpa.routing.RouteWaypoint;
import com.here.android.mpa.routing.UMRouteOptions;
import com.nokia.maps.a.an;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;

@Online
public class RoutePlanImpl extends BaseNativeObject {
    private static k<RoutePlan, RoutePlanImpl> b = null;
    private static am<RoutePlan, RoutePlanImpl> c = null;
    private cq a = new cq(RoutePlanImpl.class.getName());
    private an d = null;

    private native void addWaypoint(GeoCoordinateImpl geoCoordinateImpl);

    @HybridPlusNative
    private native void addWaypoint(RouteWaypointImpl routeWaypointImpl);

    private native void createRoutePlanNative();

    private native void destroyRoutePlanNative();

    private native RouteOptionsImpl getRouteOptionsNative();

    private native GeoCoordinateImpl getWaypointAtNative(int i);

    @HybridPlusNative
    private native RouteWaypointImpl getWaypointNative(int i);

    private native void insertWaypoint(GeoCoordinateImpl geoCoordinateImpl, int i);

    @HybridPlusNative
    private native void insertWaypoint(RouteWaypointImpl routeWaypointImpl, int i);

    public native int getWaypointCount();

    public native void removeAllWaypoints();

    public native void removeWaypoint(int i);

    public native void setRouteOptionsNative(RouteOptionsImpl routeOptionsImpl);

    static {
        ce.a(RoutePlan.class);
    }

    public static void a(k<RoutePlan, RoutePlanImpl> kVar, am<RoutePlan, RoutePlanImpl> amVar) {
        b = kVar;
        c = amVar;
    }

    public static RoutePlanImpl a(RoutePlan routePlan) {
        return (RoutePlanImpl) b.a(routePlan);
    }

    public static RoutePlan a(RoutePlanImpl routePlanImpl) {
        if (routePlanImpl != null) {
            return (RoutePlan) c.a(routePlanImpl);
        }
        return null;
    }

    public RoutePlanImpl() {
        createRoutePlanNative();
    }

    @OnlineNative
    private RoutePlanImpl(int i) {
        this.nativeptr = i;
    }

    public RoutePlanImpl(RoutePlan routePlan) {
        synchronized (routePlan) {
            createRoutePlanNative();
            RoutePlanImpl a = a(routePlan);
            RouteOptions a2 = a.a();
            if (a2 instanceof UMRouteOptions) {
                a(new UMRouteOptions(a.b()));
            } else {
                a(new RouteOptions(a2));
            }
            for (int i = 0; i < a.getWaypointCount(); i++) {
                a(a.b(i));
            }
        }
    }

    protected void finalize() {
        if (this.nativeptr != 0) {
            destroyRoutePlanNative();
        }
    }

    public void a(GeoCoordinate geoCoordinate) {
        dy.a(getWaypointCount() < 32, String.format("The maximum number(%d) of Waypoints allowed for route calculation has been reached.", new Object[]{Integer.valueOf(32)}));
        dy.a(geoCoordinate.isValid(), "GeoCoordinate is invalid.");
        addWaypoint(GeoCoordinateImpl.get(geoCoordinate));
    }

    @HybridPlus
    public void a(RouteWaypoint routeWaypoint) {
        dy.a(getWaypointCount() < 32, String.format("The maximum number(%d) of Waypoints allowed for route calculation has been reached.", new Object[]{Integer.valueOf(32)}));
        dy.a(routeWaypoint.getNavigablePosition().isValid(), "GeoCoordinate is invalid.");
        addWaypoint(RouteWaypointImpl.a(routeWaypoint));
    }

    public void a(GeoCoordinate geoCoordinate, int i) {
        boolean z = true;
        dy.a(getWaypointCount() < 32, String.format("The maximum number(%d) of Waypoints allowed for route calculation has been reached.", new Object[]{Integer.valueOf(32)}));
        dy.a(geoCoordinate.isValid(), "GeoCoordinate is invalid.");
        if (i < 0 || i > getWaypointCount()) {
            z = false;
        }
        dy.a(z, "Index is out of bounds.");
        insertWaypoint(GeoCoordinateImpl.get(geoCoordinate), i);
    }

    @HybridPlusNative
    public void insertWaypoint(RouteWaypoint routeWaypoint, int i) {
        boolean z = true;
        dy.a(getWaypointCount() < 32, String.format("The maximum number(%d) of Waypoints allowed for route calculation has been reached.", new Object[]{Integer.valueOf(32)}));
        dy.a(routeWaypoint.getNavigablePosition().isValid(), "GeoCoordinate is invalid.");
        if (i < 0 || i > getWaypointCount()) {
            z = false;
        }
        dy.a(z, "Index is out of bounds.");
        insertWaypoint(RouteWaypointImpl.a(routeWaypoint), i);
    }

    public GeoCoordinate a(int i) {
        return GeoCoordinateImpl.create(getWaypointAtNative(i));
    }

    @HybridPlus
    public RouteWaypoint b(int i) {
        return RouteWaypointImpl.a(getWaypointNative(i));
    }

    public void a(RouteOptions routeOptions) {
        synchronized (routeOptions) {
            if (routeOptions instanceof UMRouteOptions) {
                this.d = new an((UMRouteOptions) routeOptions);
            } else {
                this.d = null;
            }
            setRouteOptionsNative(RouteOptionsImpl.get(routeOptions));
        }
    }

    public RouteOptions a() {
        RouteOptionsImpl routeOptionsImpl;
        if (this.d != null) {
            routeOptionsImpl = this.d;
        } else {
            u();
            routeOptionsImpl = getRouteOptionsNative();
        }
        return RouteOptionsImpl.create(routeOptionsImpl);
    }

    public UMRouteOptions b() {
        an anVar;
        if (this.d != null) {
            anVar = this.d;
        } else {
            u();
            anVar = new an(RouteOptionsImpl.create(getRouteOptionsNative()));
        }
        return an.a(anVar);
    }

    public int hashCode() {
        int i;
        if (this.d == null) {
            i = 0;
        } else {
            i = this.d.hashCode();
        }
        int waypointCount = ((i + 31) * 31) + getWaypointCount();
        for (i = 0; i < getWaypointCount(); i++) {
            waypointCount = (a(i) == null ? 0 : a(i).hashCode()) + (waypointCount * 31);
        }
        return waypointCount;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (obj instanceof RoutePlan) {
            obj = a((RoutePlan) obj);
        } else {
            RoutePlanImpl routePlanImpl = (RoutePlanImpl) obj;
        }
        if (this.d == null) {
            if (obj.d != null) {
                return false;
            }
        } else if (!this.d.equals(obj.d)) {
            return false;
        }
        if (getWaypointCount() != obj.getWaypointCount()) {
            return false;
        }
        for (int i = 0; i < getWaypointCount(); i++) {
            if (!b(i).equals(obj.b(i))) {
                return false;
            }
        }
        return true;
    }
}
