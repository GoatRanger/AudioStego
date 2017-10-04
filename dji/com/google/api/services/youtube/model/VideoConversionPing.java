package com.google.api.services.youtube.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class VideoConversionPing extends GenericJson {
    @Key
    private String context;
    @Key
    private String conversionUrl;

    public String getContext() {
        return this.context;
    }

    public VideoConversionPing setContext(String str) {
        this.context = str;
        return this;
    }

    public String getConversionUrl() {
        return this.conversionUrl;
    }

    public VideoConversionPing setConversionUrl(String str) {
        this.conversionUrl = str;
        return this;
    }

    public VideoConversionPing set(String str, Object obj) {
        return (VideoConversionPing) super.set(str, obj);
    }

    public VideoConversionPing clone() {
        return (VideoConversionPing) super.clone();
    }
}
