package com.here.android.mpa.customlocation2;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.annotation.HybridPlus;
import java.util.List;

@HybridPlus
public class CLE2ProximityRequest extends CLE2Request {
    public CLE2ProximityRequest(String str, GeoCoordinate geoCoordinate, int i) {
        super(str, geoCoordinate, i);
    }

    public CLE2ProximityRequest(List<String> list, GeoCoordinate geoCoordinate, int i) {
        super((List) list, geoCoordinate, i);
    }
}
