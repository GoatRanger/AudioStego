package com.nokia.maps;

import com.here.android.mpa.common.ViewObject.Type;
import com.here.android.mpa.routing.Route;
import com.here.android.mpa.streetlevel.StreetLevelObject;
import com.nokia.maps.RouteImpl.a;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
public class PanoramaRoute extends cx {
    private native void createNative();

    private native void createNative2(RouteImpl routeImpl);

    private native void destroyNative();

    private native RouteImpl getRouteNative();

    private native void setRouteNative(RouteImpl routeImpl);

    public native int getColor();

    public native void setColor(int i);

    @HybridPlusNative
    private PanoramaRoute(int i) {
        super(i);
    }

    public PanoramaRoute() {
        createNative();
    }

    public Type k() {
        return Type.USER_OBJECT;
    }

    public StreetLevelObject.Type a() {
        return StreetLevelObject.Type.ROUTE_OBJECT;
    }

    public Route b() {
        return RouteImpl.create(getRouteNative());
    }

    public void a(Route route) {
        RouteImpl a = RouteImpl.a(route);
        if (a.a() == a.ENHANCED_TRANSIT_ROUTE) {
            throw new IllegalArgumentException("Public transport timetable routes are not supported");
        } else if (a.a() == a.URBAN_MOBILITY_ROUTE) {
            throw new IllegalArgumentException("Urban Mobility routes are not supported");
        } else {
            setRouteNative(a);
        }
    }
}
