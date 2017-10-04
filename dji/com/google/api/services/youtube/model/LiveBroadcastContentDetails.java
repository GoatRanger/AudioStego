package com.google.api.services.youtube.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class LiveBroadcastContentDetails extends GenericJson {
    @Key
    private String boundStreamId;
    @Key
    private Boolean enableClosedCaptions;
    @Key
    private Boolean enableContentEncryption;
    @Key
    private Boolean enableDvr;
    @Key
    private Boolean enableEmbed;
    @Key
    private MonitorStreamInfo monitorStream;
    @Key
    private Boolean recordFromStart;
    @Key
    private Boolean startWithSlate;

    public String getBoundStreamId() {
        return this.boundStreamId;
    }

    public LiveBroadcastContentDetails setBoundStreamId(String str) {
        this.boundStreamId = str;
        return this;
    }

    public Boolean getEnableClosedCaptions() {
        return this.enableClosedCaptions;
    }

    public LiveBroadcastContentDetails setEnableClosedCaptions(Boolean bool) {
        this.enableClosedCaptions = bool;
        return this;
    }

    public Boolean getEnableContentEncryption() {
        return this.enableContentEncryption;
    }

    public LiveBroadcastContentDetails setEnableContentEncryption(Boolean bool) {
        this.enableContentEncryption = bool;
        return this;
    }

    public Boolean getEnableDvr() {
        return this.enableDvr;
    }

    public LiveBroadcastContentDetails setEnableDvr(Boolean bool) {
        this.enableDvr = bool;
        return this;
    }

    public Boolean getEnableEmbed() {
        return this.enableEmbed;
    }

    public LiveBroadcastContentDetails setEnableEmbed(Boolean bool) {
        this.enableEmbed = bool;
        return this;
    }

    public MonitorStreamInfo getMonitorStream() {
        return this.monitorStream;
    }

    public LiveBroadcastContentDetails setMonitorStream(MonitorStreamInfo monitorStreamInfo) {
        this.monitorStream = monitorStreamInfo;
        return this;
    }

    public Boolean getRecordFromStart() {
        return this.recordFromStart;
    }

    public LiveBroadcastContentDetails setRecordFromStart(Boolean bool) {
        this.recordFromStart = bool;
        return this;
    }

    public Boolean getStartWithSlate() {
        return this.startWithSlate;
    }

    public LiveBroadcastContentDetails setStartWithSlate(Boolean bool) {
        this.startWithSlate = bool;
        return this;
    }

    public LiveBroadcastContentDetails set(String str, Object obj) {
        return (LiveBroadcastContentDetails) super.set(str, obj);
    }

    public LiveBroadcastContentDetails clone() {
        return (LiveBroadcastContentDetails) super.clone();
    }
}
