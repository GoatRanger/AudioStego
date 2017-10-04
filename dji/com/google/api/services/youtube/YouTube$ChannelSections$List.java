package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.ChannelSections;
import com.google.api.services.youtube.model.ChannelSectionListResponse;
import java.io.IOException;

public class YouTube$ChannelSections$List extends YouTubeRequest<ChannelSectionListResponse> {
    private static final String REST_PATH = "channelSections";
    @Key
    private String channelId;
    @Key
    private String id;
    @Key
    private Boolean mine;
    @Key
    private String onBehalfOfContentOwner;
    @Key
    private String part;
    final /* synthetic */ ChannelSections this$1;

    protected YouTube$ChannelSections$List(ChannelSections channelSections, String str) {
        this.this$1 = channelSections;
        super(channelSections.this$0, HttpMethods.GET, REST_PATH, null, ChannelSectionListResponse.class);
        this.part = (String) Preconditions.checkNotNull(str, "Required parameter part must be specified.");
    }

    public HttpResponse executeUsingHead() throws IOException {
        return super.executeUsingHead();
    }

    public HttpRequest buildHttpRequestUsingHead() throws IOException {
        return super.buildHttpRequestUsingHead();
    }

    public YouTube$ChannelSections$List setAlt(String str) {
        return (YouTube$ChannelSections$List) super.setAlt(str);
    }

    public YouTube$ChannelSections$List setFields(String str) {
        return (YouTube$ChannelSections$List) super.setFields(str);
    }

    public YouTube$ChannelSections$List setKey(String str) {
        return (YouTube$ChannelSections$List) super.setKey(str);
    }

    public YouTube$ChannelSections$List setOauthToken(String str) {
        return (YouTube$ChannelSections$List) super.setOauthToken(str);
    }

    public YouTube$ChannelSections$List setPrettyPrint(Boolean bool) {
        return (YouTube$ChannelSections$List) super.setPrettyPrint(bool);
    }

    public YouTube$ChannelSections$List setQuotaUser(String str) {
        return (YouTube$ChannelSections$List) super.setQuotaUser(str);
    }

    public YouTube$ChannelSections$List setUserIp(String str) {
        return (YouTube$ChannelSections$List) super.setUserIp(str);
    }

    public String getPart() {
        return this.part;
    }

    public YouTube$ChannelSections$List setPart(String str) {
        this.part = str;
        return this;
    }

    public String getOnBehalfOfContentOwner() {
        return this.onBehalfOfContentOwner;
    }

    public YouTube$ChannelSections$List setOnBehalfOfContentOwner(String str) {
        this.onBehalfOfContentOwner = str;
        return this;
    }

    public String getChannelId() {
        return this.channelId;
    }

    public YouTube$ChannelSections$List setChannelId(String str) {
        this.channelId = str;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public YouTube$ChannelSections$List setId(String str) {
        this.id = str;
        return this;
    }

    public Boolean getMine() {
        return this.mine;
    }

    public YouTube$ChannelSections$List setMine(Boolean bool) {
        this.mine = bool;
        return this;
    }

    public YouTube$ChannelSections$List set(String str, Object obj) {
        return (YouTube$ChannelSections$List) super.set(str, obj);
    }
}
