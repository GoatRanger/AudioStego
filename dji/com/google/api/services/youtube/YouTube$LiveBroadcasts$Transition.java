package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.LiveBroadcasts;
import com.google.api.services.youtube.model.LiveBroadcast;

public class YouTube$LiveBroadcasts$Transition extends YouTubeRequest<LiveBroadcast> {
    private static final String REST_PATH = "liveBroadcasts/transition";
    @Key
    private String broadcastStatus;
    @Key
    private String id;
    @Key
    private String onBehalfOfContentOwner;
    @Key
    private String onBehalfOfContentOwnerChannel;
    @Key
    private String part;
    final /* synthetic */ LiveBroadcasts this$1;

    protected YouTube$LiveBroadcasts$Transition(LiveBroadcasts liveBroadcasts, String str, String str2, String str3) {
        this.this$1 = liveBroadcasts;
        super(liveBroadcasts.this$0, HttpMethods.POST, REST_PATH, null, LiveBroadcast.class);
        this.broadcastStatus = (String) Preconditions.checkNotNull(str, "Required parameter broadcastStatus must be specified.");
        this.id = (String) Preconditions.checkNotNull(str2, "Required parameter id must be specified.");
        this.part = (String) Preconditions.checkNotNull(str3, "Required parameter part must be specified.");
    }

    public YouTube$LiveBroadcasts$Transition setAlt(String str) {
        return (YouTube$LiveBroadcasts$Transition) super.setAlt(str);
    }

    public YouTube$LiveBroadcasts$Transition setFields(String str) {
        return (YouTube$LiveBroadcasts$Transition) super.setFields(str);
    }

    public YouTube$LiveBroadcasts$Transition setKey(String str) {
        return (YouTube$LiveBroadcasts$Transition) super.setKey(str);
    }

    public YouTube$LiveBroadcasts$Transition setOauthToken(String str) {
        return (YouTube$LiveBroadcasts$Transition) super.setOauthToken(str);
    }

    public YouTube$LiveBroadcasts$Transition setPrettyPrint(Boolean bool) {
        return (YouTube$LiveBroadcasts$Transition) super.setPrettyPrint(bool);
    }

    public YouTube$LiveBroadcasts$Transition setQuotaUser(String str) {
        return (YouTube$LiveBroadcasts$Transition) super.setQuotaUser(str);
    }

    public YouTube$LiveBroadcasts$Transition setUserIp(String str) {
        return (YouTube$LiveBroadcasts$Transition) super.setUserIp(str);
    }

    public String getBroadcastStatus() {
        return this.broadcastStatus;
    }

    public YouTube$LiveBroadcasts$Transition setBroadcastStatus(String str) {
        this.broadcastStatus = str;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public YouTube$LiveBroadcasts$Transition setId(String str) {
        this.id = str;
        return this;
    }

    public String getPart() {
        return this.part;
    }

    public YouTube$LiveBroadcasts$Transition setPart(String str) {
        this.part = str;
        return this;
    }

    public String getOnBehalfOfContentOwnerChannel() {
        return this.onBehalfOfContentOwnerChannel;
    }

    public YouTube$LiveBroadcasts$Transition setOnBehalfOfContentOwnerChannel(String str) {
        this.onBehalfOfContentOwnerChannel = str;
        return this;
    }

    public String getOnBehalfOfContentOwner() {
        return this.onBehalfOfContentOwner;
    }

    public YouTube$LiveBroadcasts$Transition setOnBehalfOfContentOwner(String str) {
        this.onBehalfOfContentOwner = str;
        return this;
    }

    public YouTube$LiveBroadcasts$Transition set(String str, Object obj) {
        return (YouTube$LiveBroadcasts$Transition) super.set(str, obj);
    }
}
