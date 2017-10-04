package com.here.android.mpa.venues3d;

import com.nokia.maps.BaseNativeObject;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
public abstract class SpatialObject extends BaseNativeObject {
    public native String getId();

    @HybridPlusNative
    protected SpatialObject(int i) {
        this.nativeptr = i;
    }
}
