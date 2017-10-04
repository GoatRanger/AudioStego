package com.here.android.mpa.venues3d;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.venues3d.BaseLocation.LocationType;
import com.nokia.maps.GeoCoordinateImpl;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
public class OutdoorLocation extends BaseLocation {
    @HybridPlusNative
    private native void createNative(GeoCoordinateImpl geoCoordinateImpl);

    @HybridPlusNative
    private OutdoorLocation(int i) {
        this.a = LocationType.OUTDOOR;
        this.nativeptr = i;
    }

    public OutdoorLocation(GeoCoordinate geoCoordinate) {
        this.a = LocationType.OUTDOOR;
        createNative(new GeoCoordinateImpl(geoCoordinate.getLatitude(), geoCoordinate.getLongitude(), geoCoordinate.getAltitude()));
    }
}
