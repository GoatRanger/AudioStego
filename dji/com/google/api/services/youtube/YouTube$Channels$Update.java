package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.Channels;
import com.google.api.services.youtube.model.Channel;

public class YouTube$Channels$Update extends YouTubeRequest<Channel> {
    private static final String REST_PATH = "channels";
    @Key
    private String onBehalfOfContentOwner;
    @Key
    private String part;
    final /* synthetic */ Channels this$1;

    protected YouTube$Channels$Update(Channels channels, String str, Channel channel) {
        this.this$1 = channels;
        super(channels.this$0, HttpMethods.PUT, REST_PATH, channel, Channel.class);
        this.part = (String) Preconditions.checkNotNull(str, "Required parameter part must be specified.");
    }

    public YouTube$Channels$Update setAlt(String str) {
        return (YouTube$Channels$Update) super.setAlt(str);
    }

    public YouTube$Channels$Update setFields(String str) {
        return (YouTube$Channels$Update) super.setFields(str);
    }

    public YouTube$Channels$Update setKey(String str) {
        return (YouTube$Channels$Update) super.setKey(str);
    }

    public YouTube$Channels$Update setOauthToken(String str) {
        return (YouTube$Channels$Update) super.setOauthToken(str);
    }

    public YouTube$Channels$Update setPrettyPrint(Boolean bool) {
        return (YouTube$Channels$Update) super.setPrettyPrint(bool);
    }

    public YouTube$Channels$Update setQuotaUser(String str) {
        return (YouTube$Channels$Update) super.setQuotaUser(str);
    }

    public YouTube$Channels$Update setUserIp(String str) {
        return (YouTube$Channels$Update) super.setUserIp(str);
    }

    public String getPart() {
        return this.part;
    }

    public YouTube$Channels$Update setPart(String str) {
        this.part = str;
        return this;
    }

    public String getOnBehalfOfContentOwner() {
        return this.onBehalfOfContentOwner;
    }

    public YouTube$Channels$Update setOnBehalfOfContentOwner(String str) {
        this.onBehalfOfContentOwner = str;
        return this;
    }

    public YouTube$Channels$Update set(String str, Object obj) {
        return (YouTube$Channels$Update) super.set(str, obj);
    }
}
