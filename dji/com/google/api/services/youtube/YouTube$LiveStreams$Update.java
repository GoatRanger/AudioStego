package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.LiveStreams;
import com.google.api.services.youtube.model.LiveStream;

public class YouTube$LiveStreams$Update extends YouTubeRequest<LiveStream> {
    private static final String REST_PATH = "liveStreams";
    @Key
    private String onBehalfOfContentOwner;
    @Key
    private String onBehalfOfContentOwnerChannel;
    @Key
    private String part;
    final /* synthetic */ LiveStreams this$1;

    protected YouTube$LiveStreams$Update(LiveStreams liveStreams, String str, LiveStream liveStream) {
        this.this$1 = liveStreams;
        super(liveStreams.this$0, HttpMethods.PUT, REST_PATH, liveStream, LiveStream.class);
        this.part = (String) Preconditions.checkNotNull(str, "Required parameter part must be specified.");
        checkRequiredParameter(liveStream, "content");
        checkRequiredParameter(liveStream.getId(), "LiveStream.getId()");
    }

    public YouTube$LiveStreams$Update setAlt(String str) {
        return (YouTube$LiveStreams$Update) super.setAlt(str);
    }

    public YouTube$LiveStreams$Update setFields(String str) {
        return (YouTube$LiveStreams$Update) super.setFields(str);
    }

    public YouTube$LiveStreams$Update setKey(String str) {
        return (YouTube$LiveStreams$Update) super.setKey(str);
    }

    public YouTube$LiveStreams$Update setOauthToken(String str) {
        return (YouTube$LiveStreams$Update) super.setOauthToken(str);
    }

    public YouTube$LiveStreams$Update setPrettyPrint(Boolean bool) {
        return (YouTube$LiveStreams$Update) super.setPrettyPrint(bool);
    }

    public YouTube$LiveStreams$Update setQuotaUser(String str) {
        return (YouTube$LiveStreams$Update) super.setQuotaUser(str);
    }

    public YouTube$LiveStreams$Update setUserIp(String str) {
        return (YouTube$LiveStreams$Update) super.setUserIp(str);
    }

    public String getPart() {
        return this.part;
    }

    public YouTube$LiveStreams$Update setPart(String str) {
        this.part = str;
        return this;
    }

    public String getOnBehalfOfContentOwnerChannel() {
        return this.onBehalfOfContentOwnerChannel;
    }

    public YouTube$LiveStreams$Update setOnBehalfOfContentOwnerChannel(String str) {
        this.onBehalfOfContentOwnerChannel = str;
        return this;
    }

    public String getOnBehalfOfContentOwner() {
        return this.onBehalfOfContentOwner;
    }

    public YouTube$LiveStreams$Update setOnBehalfOfContentOwner(String str) {
        this.onBehalfOfContentOwner = str;
        return this;
    }

    public YouTube$LiveStreams$Update set(String str, Object obj) {
        return (YouTube$LiveStreams$Update) super.set(str, obj);
    }
}
