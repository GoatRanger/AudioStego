package com.here.android.mpa.fce;

import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.util.HashMap;
import java.util.Map;

@HybridPlus
public abstract class FleetConnectivityEvent {
    @HybridPlusNative
    private Map<String, String> m_content = new HashMap();
    @HybridPlusNative
    protected String m_jobId;

    protected abstract boolean dispatch(FleetConnectivityService fleetConnectivityService);

    public String getJobId() {
        return this.m_jobId;
    }

    public Map<String, String> getContent() {
        return this.m_content;
    }

    public void setContent(Map<String, String> map) {
        if (map == null) {
            throw new IllegalArgumentException("Content cannot be null!");
        }
        this.m_content = map;
    }
}
