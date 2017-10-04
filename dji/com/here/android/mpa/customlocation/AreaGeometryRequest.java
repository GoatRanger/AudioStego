package com.here.android.mpa.customlocation;

import com.here.android.mpa.common.GeoBoundingBox;
import com.nokia.maps.annotation.HybridPlus;

public class AreaGeometryRequest extends Request {
    @HybridPlus
    public AreaGeometryRequest(int i, GeoBoundingBox geoBoundingBox) {
        super(i, geoBoundingBox, true);
    }
}
