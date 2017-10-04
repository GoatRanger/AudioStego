package com.google.api.services.youtube.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class ChannelSection extends GenericJson {
    @Key
    private ChannelSectionContentDetails contentDetails;
    @Key
    private String etag;
    @Key
    private String id;
    @Key
    private String kind;
    @Key
    private ChannelSectionSnippet snippet;

    public ChannelSectionContentDetails getContentDetails() {
        return this.contentDetails;
    }

    public ChannelSection setContentDetails(ChannelSectionContentDetails channelSectionContentDetails) {
        this.contentDetails = channelSectionContentDetails;
        return this;
    }

    public String getEtag() {
        return this.etag;
    }

    public ChannelSection setEtag(String str) {
        this.etag = str;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public ChannelSection setId(String str) {
        this.id = str;
        return this;
    }

    public String getKind() {
        return this.kind;
    }

    public ChannelSection setKind(String str) {
        this.kind = str;
        return this;
    }

    public ChannelSectionSnippet getSnippet() {
        return this.snippet;
    }

    public ChannelSection setSnippet(ChannelSectionSnippet channelSectionSnippet) {
        this.snippet = channelSectionSnippet;
        return this;
    }

    public ChannelSection set(String str, Object obj) {
        return (ChannelSection) super.set(str, obj);
    }

    public ChannelSection clone() {
        return (ChannelSection) super.clone();
    }
}
