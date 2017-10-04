package com.here.android.mpa.venues3d;

import android.graphics.PointF;
import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.BaseNativeObject;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
public final class VenueController extends BaseNativeObject {
    private Venue a;

    private native BaseLocation getLocationNative(float f, float f2, boolean z);

    private native Venue getVenueNative();

    private native void nativeDispose();

    public native void deselectSpace();

    public native Level getGroundLevel();

    public native int getModelScale();

    public native GeoCoordinate getNormalGeoCoordinate(GeoCoordinate geoCoordinate);

    public native GeoCoordinate getScaledGeoCoordinate(GeoCoordinate geoCoordinate);

    public native Level getSelectedLevel();

    public native Space getSelectedSpace();

    public native void selectLevel(Level level);

    public native void selectSpace(Space space);

    public native boolean useVenueZoom(boolean z);

    @HybridPlusNative
    private VenueController(int i) {
        this.nativeptr = i;
    }

    public BaseLocation getLocation(PointF pointF, boolean z) {
        return getLocationNative(pointF.x, pointF.y, z);
    }

    public Venue getVenue() {
        if (this.a == null) {
            this.a = getVenueNative();
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
