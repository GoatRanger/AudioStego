package com.here.android.mpa.fce;

import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public class FleetConnectivityJobRejectedEvent extends FleetConnectivityEvent {
    public void setJobId(String str) {
        this.m_jobId = str;
    }

    protected boolean dispatch(FleetConnectivityService fleetConnectivityService) {
        return fleetConnectivityService.a(getJobId(), getContent());
    }
}
