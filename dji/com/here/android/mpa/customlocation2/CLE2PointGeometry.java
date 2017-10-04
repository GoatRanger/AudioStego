package com.here.android.mpa.customlocation2;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.CLE2GeometryImpl;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
public final class CLE2PointGeometry extends CLE2Geometry {
    GeoCoordinate b;

    @HybridPlusNative
    public CLE2PointGeometry(CLE2GeometryImpl cLE2GeometryImpl) {
        super(cLE2GeometryImpl);
    }

    public GeoCoordinate getPoint() {
        return this.b;
    }

    public void setPoint(GeoCoordinate geoCoordinate) {
        this.b = geoCoordinate;
    }
}
