package com.here.android.mpa.guidance;

import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public enum NavigationManager$RealisticViewMode {
    OFF(0),
    DAY(1),
    NIGHT(2);
    
    private int a;

    private NavigationManager$RealisticViewMode(int i) {
        this.a = i;
    }

    public int value() {
        return this.a;
    }
}
