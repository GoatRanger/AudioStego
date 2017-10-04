package com.here.android.mpa.customlocation;

import com.here.android.mpa.common.GeoBoundingBox;
import com.nokia.maps.annotation.HybridPlus;

public class AreaRequest extends Request {
    @HybridPlus
    public AreaRequest(int i, GeoBoundingBox geoBoundingBox) {
        super(i, geoBoundingBox, false);
    }
}
