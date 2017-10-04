package com.here.android.mpa.customlocation2;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.CLE2GeometryImpl;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.util.List;

@HybridPlus
public final class CLE2MultiPointGeometry extends CLE2Geometry {
    List<GeoCoordinate> b;

    @HybridPlusNative
    public CLE2MultiPointGeometry(CLE2GeometryImpl cLE2GeometryImpl) {
        super(cLE2GeometryImpl);
    }

    public List<GeoCoordinate> getMultiPoint() {
        return this.b;
    }

    public void setMultiPoint(List<GeoCoordinate> list) {
        this.b = list;
    }
}
