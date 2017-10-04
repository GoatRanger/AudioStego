package com.here.android.mpa.streetlevel;

import com.here.android.mpa.routing.Route;
import com.nokia.maps.PanoramaRoute;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
public final class StreetLevelRoute extends StreetLevelObject {
    PanoramaRoute a;

    public StreetLevelRoute() {
        this(new PanoramaRoute());
    }

    public StreetLevelRoute(Route route) {
        this(new PanoramaRoute());
        setRoute(route);
    }

    @HybridPlusNative
    private StreetLevelRoute(PanoramaRoute panoramaRoute) {
        super(panoramaRoute);
        this.a = panoramaRoute;
    }

    public Route getRoute() {
        return this.a.b();
    }

    public StreetLevelRoute setRoute(Route route) {
        this.a.a(route);
        return this;
    }

    public int getColor() {
        return this.a.getColor();
    }

    public StreetLevelRoute setColor(int i) {
        this.a.setColor(i);
        return this;
    }
}
