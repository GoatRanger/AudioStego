package com.here.android.mpa.venues3d;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.venues3d.BaseLocation.LocationType;
import com.nokia.maps.GeoCoordinateImpl;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
public class LevelLocation extends BaseLocation {
    @HybridPlusNative
    private native void createNative(Level level, GeoCoordinateImpl geoCoordinateImpl, VenueController venueController);

    @HybridPlusNative
    private LevelLocation(int i) {
        this.a = LocationType.LEVEL;
        this.nativeptr = i;
    }

    public LevelLocation(Level level, GeoCoordinate geoCoordinate, VenueController venueController) {
        this.a = LocationType.LEVEL;
        createNative(level, new GeoCoordinateImpl(geoCoordinate.getLatitude(), geoCoordinate.getLongitude(), geoCoordinate.getAltitude()), venueController);
    }
}
