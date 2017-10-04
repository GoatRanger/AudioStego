package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.Videos;
import com.google.api.services.youtube.model.VideoGetRatingResponse;
import java.io.IOException;

public class YouTube$Videos$GetRating extends YouTubeRequest<VideoGetRatingResponse> {
    private static final String REST_PATH = "videos/getRating";
    @Key
    private String id;
    @Key
    private String onBehalfOfContentOwner;
    final /* synthetic */ Videos this$1;

    protected YouTube$Videos$GetRating(Videos videos, String str) {
        this.this$1 = videos;
        super(videos.this$0, HttpMethods.GET, REST_PATH, null, VideoGetRatingResponse.class);
        this.id = (String) Preconditions.checkNotNull(str, "Required parameter id must be specified.");
    }

    public HttpResponse executeUsingHead() throws IOException {
        return super.executeUsingHead();
    }

    public HttpRequest buildHttpRequestUsingHead() throws IOException {
        return super.buildHttpRequestUsingHead();
    }

    public YouTube$Videos$GetRating setAlt(String str) {
        return (YouTube$Videos$GetRating) super.setAlt(str);
    }

    public YouTube$Videos$GetRating setFields(String str) {
        return (YouTube$Videos$GetRating) super.setFields(str);
    }

    public YouTube$Videos$GetRating setKey(String str) {
        return (YouTube$Videos$GetRating) super.setKey(str);
    }

    public YouTube$Videos$GetRating setOauthToken(String str) {
        return (YouTube$Videos$GetRating) super.setOauthToken(str);
    }

    public YouTube$Videos$GetRating setPrettyPrint(Boolean bool) {
        return (YouTube$Videos$GetRating) super.setPrettyPrint(bool);
    }

    public YouTube$Videos$GetRating setQuotaUser(String str) {
        return (YouTube$Videos$GetRating) super.setQuotaUser(str);
    }

    public YouTube$Videos$GetRating setUserIp(String str) {
        return (YouTube$Videos$GetRating) super.setUserIp(str);
    }

    public String getId() {
        return this.id;
    }

    public YouTube$Videos$GetRating setId(String str) {
        this.id = str;
        return this;
    }

    public String getOnBehalfOfContentOwner() {
        return this.onBehalfOfContentOwner;
    }

    public YouTube$Videos$GetRating setOnBehalfOfContentOwner(String str) {
        this.onBehalfOfContentOwner = str;
        return this;
    }

    public YouTube$Videos$GetRating set(String str, Object obj) {
        return (YouTube$Videos$GetRating) super.set(str, obj);
    }
}
