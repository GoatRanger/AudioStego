package com.here.android.mpa.customlocation;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.annotation.HybridPlus;

public class ProximityRequest extends Request {
    @HybridPlus
    public ProximityRequest(int i, GeoCoordinate geoCoordinate, int i2) {
        super(i, geoCoordinate, i2);
    }
}
