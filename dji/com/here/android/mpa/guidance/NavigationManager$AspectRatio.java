package com.here.android.mpa.guidance;

import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public enum NavigationManager$AspectRatio {
    AR_16x9(0),
    AR_3x5(1),
    AR_5x3(2),
    AR_4x3(3);
    
    private int a;

    private NavigationManager$AspectRatio(int i) {
        this.a = i;
    }

    public int value() {
        return this.a;
    }
}
