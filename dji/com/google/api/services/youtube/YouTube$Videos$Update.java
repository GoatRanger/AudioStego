package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.Videos;
import com.google.api.services.youtube.model.Video;

public class YouTube$Videos$Update extends YouTubeRequest<Video> {
    private static final String REST_PATH = "videos";
    @Key
    private String onBehalfOfContentOwner;
    @Key
    private String part;
    final /* synthetic */ Videos this$1;

    protected YouTube$Videos$Update(Videos videos, String str, Video video) {
        this.this$1 = videos;
        super(videos.this$0, HttpMethods.PUT, "videos", video, Video.class);
        this.part = (String) Preconditions.checkNotNull(str, "Required parameter part must be specified.");
        checkRequiredParameter(video, "content");
        checkRequiredParameter(video.getId(), "Video.getId()");
    }

    public YouTube$Videos$Update setAlt(String str) {
        return (YouTube$Videos$Update) super.setAlt(str);
    }

    public YouTube$Videos$Update setFields(String str) {
        return (YouTube$Videos$Update) super.setFields(str);
    }

    public YouTube$Videos$Update setKey(String str) {
        return (YouTube$Videos$Update) super.setKey(str);
    }

    public YouTube$Videos$Update setOauthToken(String str) {
        return (YouTube$Videos$Update) super.setOauthToken(str);
    }

    public YouTube$Videos$Update setPrettyPrint(Boolean bool) {
        return (YouTube$Videos$Update) super.setPrettyPrint(bool);
    }

    public YouTube$Videos$Update setQuotaUser(String str) {
        return (YouTube$Videos$Update) super.setQuotaUser(str);
    }

    public YouTube$Videos$Update setUserIp(String str) {
        return (YouTube$Videos$Update) super.setUserIp(str);
    }

    public String getPart() {
        return this.part;
    }

    public YouTube$Videos$Update setPart(String str) {
        this.part = str;
        return this;
    }

    public String getOnBehalfOfContentOwner() {
        return this.onBehalfOfContentOwner;
    }

    public YouTube$Videos$Update setOnBehalfOfContentOwner(String str) {
        this.onBehalfOfContentOwner = str;
        return this;
    }

    public YouTube$Videos$Update set(String str, Object obj) {
        return (YouTube$Videos$Update) super.set(str, obj);
    }
}
