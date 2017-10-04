package com.here.android.mpa.venues3d;

import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
public abstract class Area extends SpatialObject {
    public native GeoBoundingBox getBoundingBox();

    public native GeoCoordinate getCenter();

    @HybridPlus
    public native String getName();

    @HybridPlusNative
    Area(int i) {
        super(i);
    }
}
