package com.nokia.maps;

import com.here.android.mpa.mapping.MapProxyObject.Type;
import com.here.android.mpa.mapping.SafetySpotInfo;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
public class SafetySpotObjectImpl extends MapProxyObjectImpl {
    private final native SafetySpotInfoImpl getSafetySpotInfoNative();

    @HybridPlusNative
    private SafetySpotObjectImpl(int i) {
        super(i);
    }

    public Type a() {
        return Type.SAFETY_SPOT;
    }

    public SafetySpotInfo b() {
        return SafetySpotInfoImpl.a(getSafetySpotInfoNative());
    }
}
