package com.google.api.services.youtube.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class VideoCategory extends GenericJson {
    @Key
    private String etag;
    @Key
    private String id;
    @Key
    private String kind;
    @Key
    private VideoCategorySnippet snippet;

    public String getEtag() {
        return this.etag;
    }

    public VideoCategory setEtag(String str) {
        this.etag = str;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public VideoCategory setId(String str) {
        this.id = str;
        return this;
    }

    public String getKind() {
        return this.kind;
    }

    public VideoCategory setKind(String str) {
        this.kind = str;
        return this;
    }

    public VideoCategorySnippet getSnippet() {
        return this.snippet;
    }

    public VideoCategory setSnippet(VideoCategorySnippet videoCategorySnippet) {
        this.snippet = videoCategorySnippet;
        return this;
    }

    public VideoCategory set(String str, Object obj) {
        return (VideoCategory) super.set(str, obj);
    }

    public VideoCategory clone() {
        return (VideoCategory) super.clone();
    }
}
