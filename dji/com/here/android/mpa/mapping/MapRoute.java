package com.here.android.mpa.mapping;

import com.here.android.mpa.routing.Route;
import com.nokia.maps.MapRouteImpl;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import com.nokia.maps.by;
import com.nokia.maps.k;
import java.util.BitSet;

public final class MapRoute extends MapObject {
    private MapRouteImpl a;

    @Online
    public MapRoute() {
        this(new MapRouteImpl());
    }

    @Online
    public MapRoute(Route route) {
        this(by.a(route));
        setRoute(route);
    }

    @OnlineNative
    MapRoute(MapRouteImpl mapRouteImpl) {
        super(mapRouteImpl);
        this.a = mapRouteImpl;
    }

    @Online
    public boolean isManeuverNumberVisible() {
        return this.a.isManeuverNumberVisible();
    }

    @Online
    public MapRoute setManeuverNumberVisible(boolean z) {
        this.a.setManeuverNumberVisible(z);
        return this;
    }

    @Online
    public int getColor() {
        return this.a.getColor();
    }

    @Online
    public MapRoute setColor(int i) {
        this.a.a(i);
        return this;
    }

    @Online
    public Route getRoute() {
        return this.a.a();
    }

    @Online
    public MapRoute setRoute(Route route) {
        this.a.a(route);
        return this;
    }

    @Online
    public MapRoute setRenderType(RenderType renderType) {
        this.a.a(renderType);
        return this;
    }

    @Online
    public RenderType getRenderType() {
        return this.a.b();
    }

    @HybridPlus
    public MapRoute setTrafficEnabled(boolean z) {
        this.a.enableTraffic(z);
        return this;
    }

    @HybridPlus
    public boolean isTrafficEnabled() {
        return this.a.isTrafficEnabled();
    }

    public MapObject setVisibleMask(int i) {
        return this;
    }

    public MapObject unsetVisibleMask(int i) {
        return this;
    }

    public MapObject setVisibleMask(int i, int i2) {
        return this;
    }

    public MapObject resetVisibleMask(boolean z) {
        return this;
    }

    public MapObject unsetVisibleMask(int i, int i2) {
        return this;
    }

    public BitSet getVisibleMask() {
        return null;
    }

    static {
        MapRouteImpl.b(new k<MapRoute, MapRouteImpl>() {
            public MapRouteImpl a(MapRoute mapRoute) {
                return mapRoute.a;
            }
        });
    }
}
