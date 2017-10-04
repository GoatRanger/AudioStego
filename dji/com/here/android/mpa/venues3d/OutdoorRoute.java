package com.here.android.mpa.venues3d;

import com.here.android.mpa.routing.Route;
import com.here.android.mpa.venues3d.IRouteSection.RouteSectionType;
import com.nokia.maps.BaseNativeObject;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
public class OutdoorRoute extends BaseNativeObject implements IRouteSection {
    private Route a;

    @HybridPlusNative
    private native Route getRouteNative();

    @HybridPlusNative
    private native void nativeDispose();

    @HybridPlusNative
    private OutdoorRoute(int i) {
        this.nativeptr = i;
    }

    public RouteSectionType getRouteSectionType() {
        return RouteSectionType.OUTDOOR;
    }

    public Route getRoute() {
        if (this.a == null) {
            this.a = getRouteNative();
        }
        return this.a;
    }

    protected void finalize() throws Throwable {
        if (this.nativeptr != 0) {
            nativeDispose();
        }
        super.finalize();
    }
}
