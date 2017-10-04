package com.here.android.mpa.venues3d;

import com.here.android.mpa.routing.RouteOptions;
import com.here.android.mpa.venues3d.IRouteSection.RouteSectionType;
import com.nokia.maps.BaseNativeObject;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
public class VenueRouteOptions extends BaseNativeObject {
    @HybridPlusNative
    private native void createNative();

    @HybridPlusNative
    private native void nativeDispose();

    @HybridPlusNative
    public native boolean areCorridorsPreferred();

    @HybridPlusNative
    public native boolean areElevatorsAllowed();

    @HybridPlusNative
    public native boolean areEscalatorsAllowed();

    @HybridPlusNative
    public native boolean areGroundEntrancesPreferred();

    @HybridPlusNative
    public native boolean areRampsAllowed();

    @HybridPlusNative
    public native boolean areStairsAllowed();

    @HybridPlusNative
    public native int getColor(RouteSectionType routeSectionType);

    @HybridPlusNative
    public native int getConnectorColor();

    @HybridPlusNative
    public native boolean getFlagsVisible();

    @HybridPlusNative
    public native boolean getIconsVisible();

    @HybridPlusNative
    public native double getIndoorRouteWidth();

    @HybridPlusNative
    public native int getOutdoorRouteWidth();

    @HybridPlusNative
    public native RouteOptions getRouteOptions();

    @HybridPlusNative
    public native boolean getRouteVisible(RouteSectionType routeSectionType);

    @HybridPlusNative
    public native boolean isAutoParkingEnabled();

    @HybridPlusNative
    public native void setAutoParkingEnabled(boolean z);

    @HybridPlusNative
    public native void setColor(RouteSectionType routeSectionType, int i, int i2, int i3, int i4);

    @HybridPlusNative
    public native void setConnectorColor(int i, int i2, int i3, int i4);

    @HybridPlusNative
    public native void setCorridorsPreferred(boolean z);

    @HybridPlusNative
    public native void setElevatorsAllowed(boolean z);

    @HybridPlusNative
    public native void setEscalatorsAllowed(boolean z);

    @HybridPlusNative
    public native void setFlagsVisible(boolean z);

    @HybridPlusNative
    public native void setGroundEntrancesPreferred(boolean z);

    @HybridPlusNative
    public native void setIconsVisible(boolean z);

    @HybridPlusNative
    public native void setIndoorRouteWidth(double d);

    @HybridPlusNative
    public native void setOutdoorRouteWidth(int i);

    @HybridPlusNative
    public native void setRampsAllowed(boolean z);

    @HybridPlusNative
    public native void setRouteOptions(RouteOptions routeOptions);

    @HybridPlusNative
    public native void setRouteVisible(RouteSectionType routeSectionType, boolean z);

    @HybridPlusNative
    public native void setStairsAllowed(boolean z);

    public VenueRouteOptions() {
        createNative();
    }

    @HybridPlusNative
    public VenueRouteOptions(int i) {
        this.nativeptr = i;
    }

    protected void finalize() throws Throwable {
        if (this.nativeptr != 0) {
            nativeDispose();
        }
        super.finalize();
    }
}
