package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.ChannelSections;
import com.google.api.services.youtube.model.ChannelSection;

public class YouTube$ChannelSections$Update extends YouTubeRequest<ChannelSection> {
    private static final String REST_PATH = "channelSections";
    @Key
    private String part;
    final /* synthetic */ ChannelSections this$1;

    protected YouTube$ChannelSections$Update(ChannelSections channelSections, String str, ChannelSection channelSection) {
        this.this$1 = channelSections;
        super(channelSections.this$0, HttpMethods.PUT, REST_PATH, channelSection, ChannelSection.class);
        this.part = (String) Preconditions.checkNotNull(str, "Required parameter part must be specified.");
    }

    public YouTube$ChannelSections$Update setAlt(String str) {
        return (YouTube$ChannelSections$Update) super.setAlt(str);
    }

    public YouTube$ChannelSections$Update setFields(String str) {
        return (YouTube$ChannelSections$Update) super.setFields(str);
    }

    public YouTube$ChannelSections$Update setKey(String str) {
        return (YouTube$ChannelSections$Update) super.setKey(str);
    }

    public YouTube$ChannelSections$Update setOauthToken(String str) {
        return (YouTube$ChannelSections$Update) super.setOauthToken(str);
    }

    public YouTube$ChannelSections$Update setPrettyPrint(Boolean bool) {
        return (YouTube$ChannelSections$Update) super.setPrettyPrint(bool);
    }

    public YouTube$ChannelSections$Update setQuotaUser(String str) {
        return (YouTube$ChannelSections$Update) super.setQuotaUser(str);
    }

    public YouTube$ChannelSections$Update setUserIp(String str) {
        return (YouTube$ChannelSections$Update) super.setUserIp(str);
    }

    public String getPart() {
        return this.part;
    }

    public YouTube$ChannelSections$Update setPart(String str) {
        this.part = str;
        return this;
    }

    public YouTube$ChannelSections$Update set(String str, Object obj) {
        return (YouTube$ChannelSections$Update) super.set(str, obj);
    }
}
