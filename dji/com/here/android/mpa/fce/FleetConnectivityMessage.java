package com.here.android.mpa.fce;

import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.util.Map;

@HybridPlus
public abstract class FleetConnectivityMessage {
    @HybridPlusNative
    private String m_assetId;
    @HybridPlusNative
    private Map<String, String> m_content;
    @HybridPlusNative
    private long m_creationTime;
    @HybridPlusNative
    private String m_dispatcherId;
    @HybridPlusNative
    private String m_jobId;
    @HybridPlusNative
    private String m_message;

    public String getJobId() {
        return this.m_jobId;
    }

    public String getDispatcherId() {
        return this.m_dispatcherId;
    }

    public String getAssetId() {
        return this.m_assetId;
    }

    public String getMessage() {
        return this.m_message;
    }

    public long getCreationTimeMilliseconds() {
        return this.m_creationTime;
    }

    public Map<String, String> getContent() {
        return this.m_content;
    }
}
