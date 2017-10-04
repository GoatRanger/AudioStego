package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.LiveStreams;
import com.google.api.services.youtube.model.LiveStream;

public class YouTube$LiveStreams$Insert extends YouTubeRequest<LiveStream> {
    private static final String REST_PATH = "liveStreams";
    @Key
    private String onBehalfOfContentOwner;
    @Key
    private String onBehalfOfContentOwnerChannel;
    @Key
    private String part;
    final /* synthetic */ LiveStreams this$1;

    protected YouTube$LiveStreams$Insert(LiveStreams liveStreams, String str, LiveStream liveStream) {
        this.this$1 = liveStreams;
        super(liveStreams.this$0, HttpMethods.POST, REST_PATH, liveStream, LiveStream.class);
        this.part = (String) Preconditions.checkNotNull(str, "Required parameter part must be specified.");
    }

    public YouTube$LiveStreams$Insert setAlt(String str) {
        return (YouTube$LiveStreams$Insert) super.setAlt(str);
    }

    public YouTube$LiveStreams$Insert setFields(String str) {
        return (YouTube$LiveStreams$Insert) super.setFields(str);
    }

    public YouTube$LiveStreams$Insert setKey(String str) {
        return (YouTube$LiveStreams$Insert) super.setKey(str);
    }

    public YouTube$LiveStreams$Insert setOauthToken(String str) {
        return (YouTube$LiveStreams$Insert) super.setOauthToken(str);
    }

    public YouTube$LiveStreams$Insert setPrettyPrint(Boolean bool) {
        return (YouTube$LiveStreams$Insert) super.setPrettyPrint(bool);
    }

    public YouTube$LiveStreams$Insert setQuotaUser(String str) {
        return (YouTube$LiveStreams$Insert) super.setQuotaUser(str);
    }

    public YouTube$LiveStreams$Insert setUserIp(String str) {
        return (YouTube$LiveStreams$Insert) super.setUserIp(str);
    }

    public String getPart() {
        return this.part;
    }

    public YouTube$LiveStreams$Insert setPart(String str) {
        this.part = str;
        return this;
    }

    public String getOnBehalfOfContentOwnerChannel() {
        return this.onBehalfOfContentOwnerChannel;
    }

    public YouTube$LiveStreams$Insert setOnBehalfOfContentOwnerChannel(String str) {
        this.onBehalfOfContentOwnerChannel = str;
        return this;
    }

    public String getOnBehalfOfContentOwner() {
        return this.onBehalfOfContentOwner;
    }

    public YouTube$LiveStreams$Insert setOnBehalfOfContentOwner(String str) {
        this.onBehalfOfContentOwner = str;
        return this;
    }

    public YouTube$LiveStreams$Insert set(String str, Object obj) {
        return (YouTube$LiveStreams$Insert) super.set(str, obj);
    }
}
