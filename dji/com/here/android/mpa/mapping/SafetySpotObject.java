package com.here.android.mpa.mapping;

import com.nokia.maps.SafetySpotObjectImpl;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
public final class SafetySpotObject extends MapProxyObject {
    private SafetySpotObjectImpl b;

    @HybridPlusNative
    private SafetySpotObject(SafetySpotObjectImpl safetySpotObjectImpl) {
        super(safetySpotObjectImpl);
        this.b = safetySpotObjectImpl;
    }

    public SafetySpotInfo getSafetySpotInfo() {
        return this.b.b();
    }
}
