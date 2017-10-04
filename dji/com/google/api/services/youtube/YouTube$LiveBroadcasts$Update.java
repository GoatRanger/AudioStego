package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.LiveBroadcasts;
import com.google.api.services.youtube.model.LiveBroadcast;

public class YouTube$LiveBroadcasts$Update extends YouTubeRequest<LiveBroadcast> {
    private static final String REST_PATH = "liveBroadcasts";
    @Key
    private String onBehalfOfContentOwner;
    @Key
    private String onBehalfOfContentOwnerChannel;
    @Key
    private String part;
    final /* synthetic */ LiveBroadcasts this$1;

    protected YouTube$LiveBroadcasts$Update(LiveBroadcasts liveBroadcasts, String str, LiveBroadcast liveBroadcast) {
        this.this$1 = liveBroadcasts;
        super(liveBroadcasts.this$0, HttpMethods.PUT, REST_PATH, liveBroadcast, LiveBroadcast.class);
        this.part = (String) Preconditions.checkNotNull(str, "Required parameter part must be specified.");
        checkRequiredParameter(liveBroadcast, "content");
        checkRequiredParameter(liveBroadcast.getId(), "LiveBroadcast.getId()");
    }

    public YouTube$LiveBroadcasts$Update setAlt(String str) {
        return (YouTube$LiveBroadcasts$Update) super.setAlt(str);
    }

    public YouTube$LiveBroadcasts$Update setFields(String str) {
        return (YouTube$LiveBroadcasts$Update) super.setFields(str);
    }

    public YouTube$LiveBroadcasts$Update setKey(String str) {
        return (YouTube$LiveBroadcasts$Update) super.setKey(str);
    }

    public YouTube$LiveBroadcasts$Update setOauthToken(String str) {
        return (YouTube$LiveBroadcasts$Update) super.setOauthToken(str);
    }

    public YouTube$LiveBroadcasts$Update setPrettyPrint(Boolean bool) {
        return (YouTube$LiveBroadcasts$Update) super.setPrettyPrint(bool);
    }

    public YouTube$LiveBroadcasts$Update setQuotaUser(String str) {
        return (YouTube$LiveBroadcasts$Update) super.setQuotaUser(str);
    }

    public YouTube$LiveBroadcasts$Update setUserIp(String str) {
        return (YouTube$LiveBroadcasts$Update) super.setUserIp(str);
    }

    public String getPart() {
        return this.part;
    }

    public YouTube$LiveBroadcasts$Update setPart(String str) {
        this.part = str;
        return this;
    }

    public String getOnBehalfOfContentOwnerChannel() {
        return this.onBehalfOfContentOwnerChannel;
    }

    public YouTube$LiveBroadcasts$Update setOnBehalfOfContentOwnerChannel(String str) {
        this.onBehalfOfContentOwnerChannel = str;
        return this;
    }

    public String getOnBehalfOfContentOwner() {
        return this.onBehalfOfContentOwner;
    }

    public YouTube$LiveBroadcasts$Update setOnBehalfOfContentOwner(String str) {
        this.onBehalfOfContentOwner = str;
        return this;
    }

    public YouTube$LiveBroadcasts$Update set(String str, Object obj) {
        return (YouTube$LiveBroadcasts$Update) super.set(str, obj);
    }
}
