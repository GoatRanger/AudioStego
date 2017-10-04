package com.here.android.mpa.venues3d;

import com.here.android.mpa.common.GeoBoundingBox;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
public final class VenueInfo extends SpatialObject {
    public native GeoBoundingBox getBoundingBox();

    public native String getName();

    @HybridPlusNative
    private VenueInfo(int i) {
        super(i);
    }
}
