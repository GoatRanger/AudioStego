package com.here.android.mpa.venues3d;

import com.here.android.mpa.venues3d.BaseLocation.LocationType;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
public class SpaceLocation extends BaseLocation {
    @HybridPlusNative
    private native void createNative(Space space, VenueController venueController);

    @HybridPlusNative
    private SpaceLocation(int i) {
        this.a = LocationType.SPACE;
        this.nativeptr = i;
    }

    public SpaceLocation(Space space, VenueController venueController) {
        this.a = LocationType.SPACE;
        createNative(space, venueController);
    }
}
