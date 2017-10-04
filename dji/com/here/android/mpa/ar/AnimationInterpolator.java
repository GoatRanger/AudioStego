package com.here.android.mpa.ar;

import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public enum AnimationInterpolator {
    LINEAR,
    ACCELERATE,
    DECELERATE,
    ACCELERATE_DECELERATE,
    OVERSHOOT,
    ANTICIPATE,
    ANTICIPATE_OVERSHOOT,
    BOUNCE,
    CYCLE
}
