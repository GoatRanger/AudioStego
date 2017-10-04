package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.Videos;

public class YouTube$Videos$Delete extends YouTubeRequest<Void> {
    private static final String REST_PATH = "videos";
    @Key
    private String id;
    @Key
    private String onBehalfOfContentOwner;
    final /* synthetic */ Videos this$1;

    protected YouTube$Videos$Delete(Videos videos, String str) {
        this.this$1 = videos;
        super(videos.this$0, HttpMethods.DELETE, "videos", null, Void.class);
        this.id = (String) Preconditions.checkNotNull(str, "Required parameter id must be specified.");
    }

    public YouTube$Videos$Delete setAlt(String str) {
        return (YouTube$Videos$Delete) super.setAlt(str);
    }

    public YouTube$Videos$Delete setFields(String str) {
        return (YouTube$Videos$Delete) super.setFields(str);
    }

    public YouTube$Videos$Delete setKey(String str) {
        return (YouTube$Videos$Delete) super.setKey(str);
    }

    public YouTube$Videos$Delete setOauthToken(String str) {
        return (YouTube$Videos$Delete) super.setOauthToken(str);
    }

    public YouTube$Videos$Delete setPrettyPrint(Boolean bool) {
        return (YouTube$Videos$Delete) super.setPrettyPrint(bool);
    }

    public YouTube$Videos$Delete setQuotaUser(String str) {
        return (YouTube$Videos$Delete) super.setQuotaUser(str);
    }

    public YouTube$Videos$Delete setUserIp(String str) {
        return (YouTube$Videos$Delete) super.setUserIp(str);
    }

    public String getId() {
        return this.id;
    }

    public YouTube$Videos$Delete setId(String str) {
        this.id = str;
        return this;
    }

    public String getOnBehalfOfContentOwner() {
        return this.onBehalfOfContentOwner;
    }

    public YouTube$Videos$Delete setOnBehalfOfContentOwner(String str) {
        this.onBehalfOfContentOwner = str;
        return this;
    }

    public YouTube$Videos$Delete set(String str, Object obj) {
        return (YouTube$Videos$Delete) super.set(str, obj);
    }
}
