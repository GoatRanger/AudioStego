package com.google.api.services.youtube;

import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Key;
import com.google.api.services.youtube.YouTube.ChannelBanners;
import com.google.api.services.youtube.model.ChannelBannerResource;

public class YouTube$ChannelBanners$Insert extends YouTubeRequest<ChannelBannerResource> {
    private static final String REST_PATH = "channelBanners/insert";
    @Key
    private String onBehalfOfContentOwner;
    final /* synthetic */ ChannelBanners this$1;

    protected YouTube$ChannelBanners$Insert(ChannelBanners channelBanners, ChannelBannerResource channelBannerResource) {
        this.this$1 = channelBanners;
        super(channelBanners.this$0, HttpMethods.POST, REST_PATH, channelBannerResource, ChannelBannerResource.class);
    }

    protected YouTube$ChannelBanners$Insert(ChannelBanners channelBanners, ChannelBannerResource channelBannerResource, AbstractInputStreamContent abstractInputStreamContent) {
        this.this$1 = channelBanners;
        super(channelBanners.this$0, HttpMethods.POST, "/upload/" + channelBanners.this$0.getServicePath() + REST_PATH, channelBannerResource, ChannelBannerResource.class);
        initializeMediaUpload(abstractInputStreamContent);
    }

    public YouTube$ChannelBanners$Insert setAlt(String str) {
        return (YouTube$ChannelBanners$Insert) super.setAlt(str);
    }

    public YouTube$ChannelBanners$Insert setFields(String str) {
        return (YouTube$ChannelBanners$Insert) super.setFields(str);
    }

    public YouTube$ChannelBanners$Insert setKey(String str) {
        return (YouTube$ChannelBanners$Insert) super.setKey(str);
    }

    public YouTube$ChannelBanners$Insert setOauthToken(String str) {
        return (YouTube$ChannelBanners$Insert) super.setOauthToken(str);
    }

    public YouTube$ChannelBanners$Insert setPrettyPrint(Boolean bool) {
        return (YouTube$ChannelBanners$Insert) super.setPrettyPrint(bool);
    }

    public YouTube$ChannelBanners$Insert setQuotaUser(String str) {
        return (YouTube$ChannelBanners$Insert) super.setQuotaUser(str);
    }

    public YouTube$ChannelBanners$Insert setUserIp(String str) {
        return (YouTube$ChannelBanners$Insert) super.setUserIp(str);
    }

    public String getOnBehalfOfContentOwner() {
        return this.onBehalfOfContentOwner;
    }

    public YouTube$ChannelBanners$Insert setOnBehalfOfContentOwner(String str) {
        this.onBehalfOfContentOwner = str;
        return this;
    }

    public YouTube$ChannelBanners$Insert set(String str, Object obj) {
        return (YouTube$ChannelBanners$Insert) super.set(str, obj);
    }
}
