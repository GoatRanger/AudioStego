package com.here.android.mpa.fce;

import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
public class FleetConnectivityJobStartedEvent extends FleetConnectivityEvent {
    @HybridPlusNative
    private long m_etaThreshold;

    public void setJobId(String str) {
        this.m_jobId = str;
    }

    public long getEtaThreshold() {
        return this.m_etaThreshold;
    }

    public void setEtaThreshold(long j) {
        this.m_etaThreshold = j;
    }

    protected boolean dispatch(FleetConnectivityService fleetConnectivityService) {
        return fleetConnectivityService.a(getJobId(), getEtaThreshold(), getContent());
    }
}
