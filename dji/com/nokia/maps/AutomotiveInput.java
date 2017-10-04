package com.nokia.maps;

import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
public class AutomotiveInput extends BaseNativeObject {
    private native void createAutomotiveInputNative();

    private native void destroyAutomotiveInputNative();

    public native void setCourseStandardDeviation(float f);

    public native void setElevationStandardDeviation(float f);

    public native void setHorizontalLargeStandardDeviation(float f);

    public native void setHorizontalSmallStandardDeviation(float f);

    public native void setPosition(int i, double d, double d2, double d3, double d4, float f, float f2, float f3, long j);

    public native void setSpeedStandardDeviation(float f);

    public AutomotiveInput() {
        createAutomotiveInputNative();
    }

    @HybridPlusNative
    private AutomotiveInput(int i) {
        this.nativeptr = i;
    }

    protected void finalize() {
        if (this.nativeptr != 0) {
            destroyAutomotiveInputNative();
        }
    }
}
