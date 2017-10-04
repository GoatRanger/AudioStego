package com.here.android.mpa.customlocation;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.routing.Route;
import com.nokia.maps.annotation.HybridPlus;
import java.util.List;

public class CorridorRequest extends Request {
    @HybridPlus
    public CorridorRequest(int i, List<GeoCoordinate> list, int i2) {
        super(i, (List) list, i2);
    }

    @HybridPlus
    public CorridorRequest(int i, Route route, int i2) {
        super(i, route, i2);
    }
}
