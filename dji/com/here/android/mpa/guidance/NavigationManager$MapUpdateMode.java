package com.here.android.mpa.guidance;

import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public enum NavigationManager$MapUpdateMode {
    NONE(0),
    POSITION(1),
    POSITION_ANIMATION(2),
    ROADVIEW(3),
    ROADVIEW_NOZOOM(4);
    
    private int a;

    private NavigationManager$MapUpdateMode(int i) {
        this.a = i;
    }

    public int value() {
        return this.a;
    }
}
