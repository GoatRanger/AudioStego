package com.google.api.services.youtube.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class LiveStreamContentDetails extends GenericJson {
    @Key
    private String closedCaptionsIngestionUrl;

    public String getClosedCaptionsIngestionUrl() {
        return this.closedCaptionsIngestionUrl;
    }

    public LiveStreamContentDetails setClosedCaptionsIngestionUrl(String str) {
        this.closedCaptionsIngestionUrl = str;
        return this;
    }

    public LiveStreamContentDetails set(String str, Object obj) {
        return (LiveStreamContentDetails) super.set(str, obj);
    }

    public LiveStreamContentDetails clone() {
        return (LiveStreamContentDetails) super.clone();
    }
}
