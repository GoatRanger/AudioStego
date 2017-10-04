package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.LiveBroadcasts;
import com.google.api.services.youtube.model.LiveBroadcast;

public class YouTube$LiveBroadcasts$Bind extends YouTubeRequest<LiveBroadcast> {
    private static final String REST_PATH = "liveBroadcasts/bind";
    @Key
    private String id;
    @Key
    private String onBehalfOfContentOwner;
    @Key
    private String onBehalfOfContentOwnerChannel;
    @Key
    private String part;
    @Key
    private String streamId;
    final /* synthetic */ LiveBroadcasts this$1;

    protected YouTube$LiveBroadcasts$Bind(LiveBroadcasts liveBroadcasts, String str, String str2) {
        this.this$1 = liveBroadcasts;
        super(liveBroadcasts.this$0, HttpMethods.POST, REST_PATH, null, LiveBroadcast.class);
        this.id = (String) Preconditions.checkNotNull(str, "Required parameter id must be specified.");
        this.part = (String) Preconditions.checkNotNull(str2, "Required parameter part must be specified.");
    }

    public YouTube$LiveBroadcasts$Bind setAlt(String str) {
        return (YouTube$LiveBroadcasts$Bind) super.setAlt(str);
    }

    public YouTube$LiveBroadcasts$Bind setFields(String str) {
        return (YouTube$LiveBroadcasts$Bind) super.setFields(str);
    }

    public YouTube$LiveBroadcasts$Bind setKey(String str) {
        return (YouTube$LiveBroadcasts$Bind) super.setKey(str);
    }

    public YouTube$LiveBroadcasts$Bind setOauthToken(String str) {
        return (YouTube$LiveBroadcasts$Bind) super.setOauthToken(str);
    }

    public YouTube$LiveBroadcasts$Bind setPrettyPrint(Boolean bool) {
        return (YouTube$LiveBroadcasts$Bind) super.setPrettyPrint(bool);
    }

    public YouTube$LiveBroadcasts$Bind setQuotaUser(String str) {
        return (YouTube$LiveBroadcasts$Bind) super.setQuotaUser(str);
    }

    public YouTube$LiveBroadcasts$Bind setUserIp(String str) {
        return (YouTube$LiveBroadcasts$Bind) super.setUserIp(str);
    }

    public String getId() {
        return this.id;
    }

    public YouTube$LiveBroadcasts$Bind setId(String str) {
        this.id = str;
        return this;
    }

    public String getPart() {
        return this.part;
    }

    public YouTube$LiveBroadcasts$Bind setPart(String str) {
        this.part = str;
        return this;
    }

    public String getOnBehalfOfContentOwnerChannel() {
        return this.onBehalfOfContentOwnerChannel;
    }

    public YouTube$LiveBroadcasts$Bind setOnBehalfOfContentOwnerChannel(String str) {
        this.onBehalfOfContentOwnerChannel = str;
        return this;
    }

    public String getOnBehalfOfContentOwner() {
        return this.onBehalfOfContentOwner;
    }

    public YouTube$LiveBroadcasts$Bind setOnBehalfOfContentOwner(String str) {
        this.onBehalfOfContentOwner = str;
        return this;
    }

    public String getStreamId() {
        return this.streamId;
    }

    public YouTube$LiveBroadcasts$Bind setStreamId(String str) {
        this.streamId = str;
        return this;
    }

    public YouTube$LiveBroadcasts$Bind set(String str, Object obj) {
        return (YouTube$LiveBroadcasts$Bind) super.set(str, obj);
    }
}
