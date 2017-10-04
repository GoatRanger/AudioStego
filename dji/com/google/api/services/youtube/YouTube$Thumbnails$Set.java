package com.google.api.services.youtube;

import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.Thumbnails;
import com.google.api.services.youtube.model.ThumbnailSetResponse;

public class YouTube$Thumbnails$Set extends YouTubeRequest<ThumbnailSetResponse> {
    private static final String REST_PATH = "thumbnails/set";
    @Key
    private String onBehalfOfContentOwner;
    final /* synthetic */ Thumbnails this$1;
    @Key
    private String videoId;

    protected YouTube$Thumbnails$Set(Thumbnails thumbnails, String str) {
        this.this$1 = thumbnails;
        super(thumbnails.this$0, HttpMethods.POST, REST_PATH, null, ThumbnailSetResponse.class);
        this.videoId = (String) Preconditions.checkNotNull(str, "Required parameter videoId must be specified.");
    }

    protected YouTube$Thumbnails$Set(Thumbnails thumbnails, String str, AbstractInputStreamContent abstractInputStreamContent) {
        this.this$1 = thumbnails;
        super(thumbnails.this$0, HttpMethods.POST, "/upload/" + thumbnails.this$0.getServicePath() + REST_PATH, null, ThumbnailSetResponse.class);
        this.videoId = (String) Preconditions.checkNotNull(str, "Required parameter videoId must be specified.");
        initializeMediaUpload(abstractInputStreamContent);
    }

    public YouTube$Thumbnails$Set setAlt(String str) {
        return (YouTube$Thumbnails$Set) super.setAlt(str);
    }

    public YouTube$Thumbnails$Set setFields(String str) {
        return (YouTube$Thumbnails$Set) super.setFields(str);
    }

    public YouTube$Thumbnails$Set setKey(String str) {
        return (YouTube$Thumbnails$Set) super.setKey(str);
    }

    public YouTube$Thumbnails$Set setOauthToken(String str) {
        return (YouTube$Thumbnails$Set) super.setOauthToken(str);
    }

    public YouTube$Thumbnails$Set setPrettyPrint(Boolean bool) {
        return (YouTube$Thumbnails$Set) super.setPrettyPrint(bool);
    }

    public YouTube$Thumbnails$Set setQuotaUser(String str) {
        return (YouTube$Thumbnails$Set) super.setQuotaUser(str);
    }

    public YouTube$Thumbnails$Set setUserIp(String str) {
        return (YouTube$Thumbnails$Set) super.setUserIp(str);
    }

    public String getVideoId() {
        return this.videoId;
    }

    public YouTube$Thumbnails$Set setVideoId(String str) {
        this.videoId = str;
        return this;
    }

    public String getOnBehalfOfContentOwner() {
        return this.onBehalfOfContentOwner;
    }

    public YouTube$Thumbnails$Set setOnBehalfOfContentOwner(String str) {
        this.onBehalfOfContentOwner = str;
        return this;
    }

    public YouTube$Thumbnails$Set set(String str, Object obj) {
        return (YouTube$Thumbnails$Set) super.set(str, obj);
    }
}
