package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.LiveStreams;
import com.google.api.services.youtube.model.LiveStreamListResponse;
import java.io.IOException;

public class YouTube$LiveStreams$List extends YouTubeRequest<LiveStreamListResponse> {
    private static final String REST_PATH = "liveStreams";
    @Key
    private String id;
    @Key
    private Long maxResults;
    @Key
    private Boolean mine;
    @Key
    private String onBehalfOfContentOwner;
    @Key
    private String onBehalfOfContentOwnerChannel;
    @Key
    private String pageToken;
    @Key
    private String part;
    final /* synthetic */ LiveStreams this$1;

    protected YouTube$LiveStreams$List(LiveStreams liveStreams, String str) {
        this.this$1 = liveStreams;
        super(liveStreams.this$0, HttpMethods.GET, REST_PATH, null, LiveStreamListResponse.class);
        this.part = (String) Preconditions.checkNotNull(str, "Required parameter part must be specified.");
    }

    public HttpResponse executeUsingHead() throws IOException {
        return super.executeUsingHead();
    }

    public HttpRequest buildHttpRequestUsingHead() throws IOException {
        return super.buildHttpRequestUsingHead();
    }

    public YouTube$LiveStreams$List setAlt(String str) {
        return (YouTube$LiveStreams$List) super.setAlt(str);
    }

    public YouTube$LiveStreams$List setFields(String str) {
        return (YouTube$LiveStreams$List) super.setFields(str);
    }

    public YouTube$LiveStreams$List setKey(String str) {
        return (YouTube$LiveStreams$List) super.setKey(str);
    }

    public YouTube$LiveStreams$List setOauthToken(String str) {
        return (YouTube$LiveStreams$List) super.setOauthToken(str);
    }

    public YouTube$LiveStreams$List setPrettyPrint(Boolean bool) {
        return (YouTube$LiveStreams$List) super.setPrettyPrint(bool);
    }

    public YouTube$LiveStreams$List setQuotaUser(String str) {
        return (YouTube$LiveStreams$List) super.setQuotaUser(str);
    }

    public YouTube$LiveStreams$List setUserIp(String str) {
        return (YouTube$LiveStreams$List) super.setUserIp(str);
    }

    public String getPart() {
        return this.part;
    }

    public YouTube$LiveStreams$List setPart(String str) {
        this.part = str;
        return this;
    }

    public String getOnBehalfOfContentOwner() {
        return this.onBehalfOfContentOwner;
    }

    public YouTube$LiveStreams$List setOnBehalfOfContentOwner(String str) {
        this.onBehalfOfContentOwner = str;
        return this;
    }

    public String getOnBehalfOfContentOwnerChannel() {
        return this.onBehalfOfContentOwnerChannel;
    }

    public YouTube$LiveStreams$List setOnBehalfOfContentOwnerChannel(String str) {
        this.onBehalfOfContentOwnerChannel = str;
        return this;
    }

    public Boolean getMine() {
        return this.mine;
    }

    public YouTube$LiveStreams$List setMine(Boolean bool) {
        this.mine = bool;
        return this;
    }

    public Long getMaxResults() {
        return this.maxResults;
    }

    public YouTube$LiveStreams$List setMaxResults(Long l) {
        this.maxResults = l;
        return this;
    }

    public String getPageToken() {
        return this.pageToken;
    }

    public YouTube$LiveStreams$List setPageToken(String str) {
        this.pageToken = str;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public YouTube$LiveStreams$List setId(String str) {
        this.id = str;
        return this;
    }

    public YouTube$LiveStreams$List set(String str, Object obj) {
        return (YouTube$LiveStreams$List) super.set(str, obj);
    }
}
