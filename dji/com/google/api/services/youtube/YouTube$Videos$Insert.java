package com.google.api.services.youtube;

import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Data;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.Videos;
import com.google.api.services.youtube.model.Video;

public class YouTube$Videos$Insert extends YouTubeRequest<Video> {
    private static final String REST_PATH = "videos";
    @Key
    private Boolean autoLevels;
    @Key
    private Boolean notifySubscribers;
    @Key
    private String onBehalfOfContentOwner;
    @Key
    private String onBehalfOfContentOwnerChannel;
    @Key
    private String part;
    @Key
    private Boolean stabilize;
    final /* synthetic */ Videos this$1;

    protected YouTube$Videos$Insert(Videos videos, String str, Video video) {
        this.this$1 = videos;
        super(videos.this$0, HttpMethods.POST, "videos", video, Video.class);
        this.part = (String) Preconditions.checkNotNull(str, "Required parameter part must be specified.");
    }

    protected YouTube$Videos$Insert(Videos videos, String str, Video video, AbstractInputStreamContent abstractInputStreamContent) {
        this.this$1 = videos;
        super(videos.this$0, HttpMethods.POST, "/upload/" + videos.this$0.getServicePath() + "videos", video, Video.class);
        this.part = (String) Preconditions.checkNotNull(str, "Required parameter part must be specified.");
        initializeMediaUpload(abstractInputStreamContent);
    }

    public YouTube$Videos$Insert setAlt(String str) {
        return (YouTube$Videos$Insert) super.setAlt(str);
    }

    public YouTube$Videos$Insert setFields(String str) {
        return (YouTube$Videos$Insert) super.setFields(str);
    }

    public YouTube$Videos$Insert setKey(String str) {
        return (YouTube$Videos$Insert) super.setKey(str);
    }

    public YouTube$Videos$Insert setOauthToken(String str) {
        return (YouTube$Videos$Insert) super.setOauthToken(str);
    }

    public YouTube$Videos$Insert setPrettyPrint(Boolean bool) {
        return (YouTube$Videos$Insert) super.setPrettyPrint(bool);
    }

    public YouTube$Videos$Insert setQuotaUser(String str) {
        return (YouTube$Videos$Insert) super.setQuotaUser(str);
    }

    public YouTube$Videos$Insert setUserIp(String str) {
        return (YouTube$Videos$Insert) super.setUserIp(str);
    }

    public String getPart() {
        return this.part;
    }

    public YouTube$Videos$Insert setPart(String str) {
        this.part = str;
        return this;
    }

    public String getOnBehalfOfContentOwner() {
        return this.onBehalfOfContentOwner;
    }

    public YouTube$Videos$Insert setOnBehalfOfContentOwner(String str) {
        this.onBehalfOfContentOwner = str;
        return this;
    }

    public Boolean getStabilize() {
        return this.stabilize;
    }

    public YouTube$Videos$Insert setStabilize(Boolean bool) {
        this.stabilize = bool;
        return this;
    }

    public String getOnBehalfOfContentOwnerChannel() {
        return this.onBehalfOfContentOwnerChannel;
    }

    public YouTube$Videos$Insert setOnBehalfOfContentOwnerChannel(String str) {
        this.onBehalfOfContentOwnerChannel = str;
        return this;
    }

    public Boolean getNotifySubscribers() {
        return this.notifySubscribers;
    }

    public YouTube$Videos$Insert setNotifySubscribers(Boolean bool) {
        this.notifySubscribers = bool;
        return this;
    }

    public boolean isNotifySubscribers() {
        if (this.notifySubscribers == null || this.notifySubscribers == Data.NULL_BOOLEAN) {
            return true;
        }
        return this.notifySubscribers.booleanValue();
    }

    public Boolean getAutoLevels() {
        return this.autoLevels;
    }

    public YouTube$Videos$Insert setAutoLevels(Boolean bool) {
        this.autoLevels = bool;
        return this;
    }

    public YouTube$Videos$Insert set(String str, Object obj) {
        return (YouTube$Videos$Insert) super.set(str, obj);
    }
}
