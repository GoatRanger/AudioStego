package com.here.android.mpa.guidance;

import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public enum NavigationManager$RoadView$Orientation {
    DYNAMIC(0),
    NORTH_UP(1);
    
    private final int a;

    private NavigationManager$RoadView$Orientation(int i) {
        this.a = i;
    }

    public int value() {
        return this.a;
    }
}
