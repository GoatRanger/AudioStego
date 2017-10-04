package com.here.android.mpa.customlocation;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.annotation.HybridPlus;

public class PointGeometryRequest extends Request {
    @HybridPlus
    public PointGeometryRequest(int i, GeoCoordinate geoCoordinate) {
        super(i, geoCoordinate);
    }
}
