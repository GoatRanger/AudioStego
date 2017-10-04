package com.here.android.mpa.guidance;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public interface NavigationManager$RoadView$Listener {
    void onPositionChanged(GeoCoordinate geoCoordinate);
}
