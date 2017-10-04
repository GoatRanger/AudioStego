package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.Videos;

public class YouTube$Videos$Rate extends YouTubeRequest<Void> {
    private static final String REST_PATH = "videos/rate";
    @Key
    private String id;
    @Key
    private String onBehalfOfContentOwner;
    @Key
    private String rating;
    final /* synthetic */ Videos this$1;

    protected YouTube$Videos$Rate(Videos videos, String str, String str2) {
        this.this$1 = videos;
        super(videos.this$0, HttpMethods.POST, REST_PATH, null, Void.class);
        this.id = (String) Preconditions.checkNotNull(str, "Required parameter id must be specified.");
        this.rating = (String) Preconditions.checkNotNull(str2, "Required parameter rating must be specified.");
    }

    public YouTube$Videos$Rate setAlt(String str) {
        return (YouTube$Videos$Rate) super.setAlt(str);
    }

    public YouTube$Videos$Rate setFields(String str) {
        return (YouTube$Videos$Rate) super.setFields(str);
    }

    public YouTube$Videos$Rate setKey(String str) {
        return (YouTube$Videos$Rate) super.setKey(str);
    }

    public YouTube$Videos$Rate setOauthToken(String str) {
        return (YouTube$Videos$Rate) super.setOauthToken(str);
    }

    public YouTube$Videos$Rate setPrettyPrint(Boolean bool) {
        return (YouTube$Videos$Rate) super.setPrettyPrint(bool);
    }

    public YouTube$Videos$Rate setQuotaUser(String str) {
        return (YouTube$Videos$Rate) super.setQuotaUser(str);
    }

    public YouTube$Videos$Rate setUserIp(String str) {
        return (YouTube$Videos$Rate) super.setUserIp(str);
    }

    public String getId() {
        return this.id;
    }

    public YouTube$Videos$Rate setId(String str) {
        this.id = str;
        return this;
    }

    public String getRating() {
        return this.rating;
    }

    public YouTube$Videos$Rate setRating(String str) {
        this.rating = str;
        return this;
    }

    public String getOnBehalfOfContentOwner() {
        return this.onBehalfOfContentOwner;
    }

    public YouTube$Videos$Rate setOnBehalfOfContentOwner(String str) {
        this.onBehalfOfContentOwner = str;
        return this;
    }

    public YouTube$Videos$Rate set(String str, Object obj) {
        return (YouTube$Videos$Rate) super.set(str, obj);
    }
}
