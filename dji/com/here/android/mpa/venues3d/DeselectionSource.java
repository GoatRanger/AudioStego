package com.here.android.mpa.venues3d;

import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public enum DeselectionSource {
    MOVE_OUT,
    ZOOM_OUT,
    MAP_TAPPED,
    LAYER_SWITCHED_OFF,
    SELECT_OTHER_VENUE,
    MANUALLY
}
