package com.here.android.mpa.venues3d;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.venues3d.IRouteSection.RouteSectionType;
import com.nokia.maps.BaseNativeObject;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
public class LinkingRoute extends BaseNativeObject implements IRouteSection {
    @HybridPlusNative
    private native void nativeDispose();

    @HybridPlusNative
    public native GeoCoordinate getFrom();

    @HybridPlusNative
    public native GeoCoordinate getTo();

    @HybridPlusNative
    private LinkingRoute(int i) {
        this.nativeptr = i;
    }

    public RouteSectionType getRouteSectionType() {
        return RouteSectionType.LINK;
    }

    protected void finalize() throws Throwable {
        if (this.nativeptr != 0) {
            nativeDispose();
        }
        super.finalize();
    }
}
