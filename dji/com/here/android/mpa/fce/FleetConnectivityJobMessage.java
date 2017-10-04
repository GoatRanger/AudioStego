package com.here.android.mpa.fce;

import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
public class FleetConnectivityJobMessage extends FleetConnectivityMessage {
    @HybridPlusNative
    private long m_etaThreshold;

    public long getEtaThreshold() {
        return this.m_etaThreshold;
    }
}
