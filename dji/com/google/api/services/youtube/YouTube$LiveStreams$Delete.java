package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.LiveStreams;

public class YouTube$LiveStreams$Delete extends YouTubeRequest<Void> {
    private static final String REST_PATH = "liveStreams";
    @Key
    private String id;
    @Key
    private String onBehalfOfContentOwner;
    @Key
    private String onBehalfOfContentOwnerChannel;
    final /* synthetic */ LiveStreams this$1;

    protected YouTube$LiveStreams$Delete(LiveStreams liveStreams, String str) {
        this.this$1 = liveStreams;
        super(liveStreams.this$0, HttpMethods.DELETE, REST_PATH, null, Void.class);
        this.id = (String) Preconditions.checkNotNull(str, "Required parameter id must be specified.");
    }

    public YouTube$LiveStreams$Delete setAlt(String str) {
        return (YouTube$LiveStreams$Delete) super.setAlt(str);
    }

    public YouTube$LiveStreams$Delete setFields(String str) {
        return (YouTube$LiveStreams$Delete) super.setFields(str);
    }

    public YouTube$LiveStreams$Delete setKey(String str) {
        return (YouTube$LiveStreams$Delete) super.setKey(str);
    }

    public YouTube$LiveStreams$Delete setOauthToken(String str) {
        return (YouTube$LiveStreams$Delete) super.setOauthToken(str);
    }

    public YouTube$LiveStreams$Delete setPrettyPrint(Boolean bool) {
        return (YouTube$LiveStreams$Delete) super.setPrettyPrint(bool);
    }

    public YouTube$LiveStreams$Delete setQuotaUser(String str) {
        return (YouTube$LiveStreams$Delete) super.setQuotaUser(str);
    }

    public YouTube$LiveStreams$Delete setUserIp(String str) {
        return (YouTube$LiveStreams$Delete) super.setUserIp(str);
    }

    public String getId() {
        return this.id;
    }

    public YouTube$LiveStreams$Delete setId(String str) {
        this.id = str;
        return this;
    }

    public String getOnBehalfOfContentOwnerChannel() {
        return this.onBehalfOfContentOwnerChannel;
    }

    public YouTube$LiveStreams$Delete setOnBehalfOfContentOwnerChannel(String str) {
        this.onBehalfOfContentOwnerChannel = str;
        return this;
    }

    public String getOnBehalfOfContentOwner() {
        return this.onBehalfOfContentOwner;
    }

    public YouTube$LiveStreams$Delete setOnBehalfOfContentOwner(String str) {
        this.onBehalfOfContentOwner = str;
        return this;
    }

    public YouTube$LiveStreams$Delete set(String str, Object obj) {
        return (YouTube$LiveStreams$Delete) super.set(str, obj);
    }
}
