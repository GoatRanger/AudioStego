package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.ChannelSections;
import com.google.api.services.youtube.model.ChannelSection;

public class YouTube$ChannelSections$Insert extends YouTubeRequest<ChannelSection> {
    private static final String REST_PATH = "channelSections";
    @Key
    private String onBehalfOfContentOwner;
    @Key
    private String onBehalfOfContentOwnerChannel;
    @Key
    private String part;
    final /* synthetic */ ChannelSections this$1;

    protected YouTube$ChannelSections$Insert(ChannelSections channelSections, String str, ChannelSection channelSection) {
        this.this$1 = channelSections;
        super(channelSections.this$0, HttpMethods.POST, REST_PATH, channelSection, ChannelSection.class);
        this.part = (String) Preconditions.checkNotNull(str, "Required parameter part must be specified.");
    }

    public YouTube$ChannelSections$Insert setAlt(String str) {
        return (YouTube$ChannelSections$Insert) super.setAlt(str);
    }

    public YouTube$ChannelSections$Insert setFields(String str) {
        return (YouTube$ChannelSections$Insert) super.setFields(str);
    }

    public YouTube$ChannelSections$Insert setKey(String str) {
        return (YouTube$ChannelSections$Insert) super.setKey(str);
    }

    public YouTube$ChannelSections$Insert setOauthToken(String str) {
        return (YouTube$ChannelSections$Insert) super.setOauthToken(str);
    }

    public YouTube$ChannelSections$Insert setPrettyPrint(Boolean bool) {
        return (YouTube$ChannelSections$Insert) super.setPrettyPrint(bool);
    }

    public YouTube$ChannelSections$Insert setQuotaUser(String str) {
        return (YouTube$ChannelSections$Insert) super.setQuotaUser(str);
    }

    public YouTube$ChannelSections$Insert setUserIp(String str) {
        return (YouTube$ChannelSections$Insert) super.setUserIp(str);
    }

    public String getPart() {
        return this.part;
    }

    public YouTube$ChannelSections$Insert setPart(String str) {
        this.part = str;
        return this;
    }

    public String getOnBehalfOfContentOwnerChannel() {
        return this.onBehalfOfContentOwnerChannel;
    }

    public YouTube$ChannelSections$Insert setOnBehalfOfContentOwnerChannel(String str) {
        this.onBehalfOfContentOwnerChannel = str;
        return this;
    }

    public String getOnBehalfOfContentOwner() {
        return this.onBehalfOfContentOwner;
    }

    public YouTube$ChannelSections$Insert setOnBehalfOfContentOwner(String str) {
        this.onBehalfOfContentOwner = str;
        return this;
    }

    public YouTube$ChannelSections$Insert set(String str, Object obj) {
        return (YouTube$ChannelSections$Insert) super.set(str, obj);
    }
}
