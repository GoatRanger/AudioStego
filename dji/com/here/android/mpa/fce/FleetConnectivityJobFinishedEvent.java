package com.here.android.mpa.fce;

import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public class FleetConnectivityJobFinishedEvent extends FleetConnectivityEvent {
    protected boolean dispatch(FleetConnectivityService fleetConnectivityService) {
        this.m_jobId = fleetConnectivityService.getRunningJobId();
        return fleetConnectivityService.b(getContent());
    }
}
