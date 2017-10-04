package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.LiveBroadcasts;

public class YouTube$LiveBroadcasts$Delete extends YouTubeRequest<Void> {
    private static final String REST_PATH = "liveBroadcasts";
    @Key
    private String id;
    @Key
    private String onBehalfOfContentOwner;
    @Key
    private String onBehalfOfContentOwnerChannel;
    final /* synthetic */ LiveBroadcasts this$1;

    protected YouTube$LiveBroadcasts$Delete(LiveBroadcasts liveBroadcasts, String str) {
        this.this$1 = liveBroadcasts;
        super(liveBroadcasts.this$0, HttpMethods.DELETE, REST_PATH, null, Void.class);
        this.id = (String) Preconditions.checkNotNull(str, "Required parameter id must be specified.");
    }

    public YouTube$LiveBroadcasts$Delete setAlt(String str) {
        return (YouTube$LiveBroadcasts$Delete) super.setAlt(str);
    }

    public YouTube$LiveBroadcasts$Delete setFields(String str) {
        return (YouTube$LiveBroadcasts$Delete) super.setFields(str);
    }

    public YouTube$LiveBroadcasts$Delete setKey(String str) {
        return (YouTube$LiveBroadcasts$Delete) super.setKey(str);
    }

    public YouTube$LiveBroadcasts$Delete setOauthToken(String str) {
        return (YouTube$LiveBroadcasts$Delete) super.setOauthToken(str);
    }

    public YouTube$LiveBroadcasts$Delete setPrettyPrint(Boolean bool) {
        return (YouTube$LiveBroadcasts$Delete) super.setPrettyPrint(bool);
    }

    public YouTube$LiveBroadcasts$Delete setQuotaUser(String str) {
        return (YouTube$LiveBroadcasts$Delete) super.setQuotaUser(str);
    }

    public YouTube$LiveBroadcasts$Delete setUserIp(String str) {
        return (YouTube$LiveBroadcasts$Delete) super.setUserIp(str);
    }

    public String getId() {
        return this.id;
    }

    public YouTube$LiveBroadcasts$Delete setId(String str) {
        this.id = str;
        return this;
    }

    public String getOnBehalfOfContentOwnerChannel() {
        return this.onBehalfOfContentOwnerChannel;
    }

    public YouTube$LiveBroadcasts$Delete setOnBehalfOfContentOwnerChannel(String str) {
        this.onBehalfOfContentOwnerChannel = str;
        return this;
    }

    public String getOnBehalfOfContentOwner() {
        return this.onBehalfOfContentOwner;
    }

    public YouTube$LiveBroadcasts$Delete setOnBehalfOfContentOwner(String str) {
        this.onBehalfOfContentOwner = str;
        return this;
    }

    public YouTube$LiveBroadcasts$Delete set(String str, Object obj) {
        return (YouTube$LiveBroadcasts$Delete) super.set(str, obj);
    }
}
