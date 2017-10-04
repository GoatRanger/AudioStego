package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.LiveBroadcasts;
import com.google.api.services.youtube.model.LiveBroadcast;

public class YouTube$LiveBroadcasts$Insert extends YouTubeRequest<LiveBroadcast> {
    private static final String REST_PATH = "liveBroadcasts";
    @Key
    private String onBehalfOfContentOwner;
    @Key
    private String onBehalfOfContentOwnerChannel;
    @Key
    private String part;
    final /* synthetic */ LiveBroadcasts this$1;

    protected YouTube$LiveBroadcasts$Insert(LiveBroadcasts liveBroadcasts, String str, LiveBroadcast liveBroadcast) {
        this.this$1 = liveBroadcasts;
        super(liveBroadcasts.this$0, HttpMethods.POST, REST_PATH, liveBroadcast, LiveBroadcast.class);
        this.part = (String) Preconditions.checkNotNull(str, "Required parameter part must be specified.");
    }

    public YouTube$LiveBroadcasts$Insert setAlt(String str) {
        return (YouTube$LiveBroadcasts$Insert) super.setAlt(str);
    }

    public YouTube$LiveBroadcasts$Insert setFields(String str) {
        return (YouTube$LiveBroadcasts$Insert) super.setFields(str);
    }

    public YouTube$LiveBroadcasts$Insert setKey(String str) {
        return (YouTube$LiveBroadcasts$Insert) super.setKey(str);
    }

    public YouTube$LiveBroadcasts$Insert setOauthToken(String str) {
        return (YouTube$LiveBroadcasts$Insert) super.setOauthToken(str);
    }

    public YouTube$LiveBroadcasts$Insert setPrettyPrint(Boolean bool) {
        return (YouTube$LiveBroadcasts$Insert) super.setPrettyPrint(bool);
    }

    public YouTube$LiveBroadcasts$Insert setQuotaUser(String str) {
        return (YouTube$LiveBroadcasts$Insert) super.setQuotaUser(str);
    }

    public YouTube$LiveBroadcasts$Insert setUserIp(String str) {
        return (YouTube$LiveBroadcasts$Insert) super.setUserIp(str);
    }

    public String getPart() {
        return this.part;
    }

    public YouTube$LiveBroadcasts$Insert setPart(String str) {
        this.part = str;
        return this;
    }

    public String getOnBehalfOfContentOwnerChannel() {
        return this.onBehalfOfContentOwnerChannel;
    }

    public YouTube$LiveBroadcasts$Insert setOnBehalfOfContentOwnerChannel(String str) {
        this.onBehalfOfContentOwnerChannel = str;
        return this;
    }

    public String getOnBehalfOfContentOwner() {
        return this.onBehalfOfContentOwner;
    }

    public YouTube$LiveBroadcasts$Insert setOnBehalfOfContentOwner(String str) {
        this.onBehalfOfContentOwner = str;
        return this;
    }

    public YouTube$LiveBroadcasts$Insert set(String str, Object obj) {
        return (YouTube$LiveBroadcasts$Insert) super.set(str, obj);
    }
}
