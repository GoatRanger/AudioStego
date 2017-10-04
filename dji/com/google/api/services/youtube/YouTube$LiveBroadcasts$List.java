package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.LiveBroadcasts;
import com.google.api.services.youtube.model.LiveBroadcastListResponse;
import java.io.IOException;

public class YouTube$LiveBroadcasts$List extends YouTubeRequest<LiveBroadcastListResponse> {
    private static final String REST_PATH = "liveBroadcasts";
    @Key
    private String broadcastStatus;
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
    final /* synthetic */ LiveBroadcasts this$1;

    protected YouTube$LiveBroadcasts$List(LiveBroadcasts liveBroadcasts, String str) {
        this.this$1 = liveBroadcasts;
        super(liveBroadcasts.this$0, HttpMethods.GET, REST_PATH, null, LiveBroadcastListResponse.class);
        this.part = (String) Preconditions.checkNotNull(str, "Required parameter part must be specified.");
    }

    public HttpResponse executeUsingHead() throws IOException {
        return super.executeUsingHead();
    }

    public HttpRequest buildHttpRequestUsingHead() throws IOException {
        return super.buildHttpRequestUsingHead();
    }

    public YouTube$LiveBroadcasts$List setAlt(String str) {
        return (YouTube$LiveBroadcasts$List) super.setAlt(str);
    }

    public YouTube$LiveBroadcasts$List setFields(String str) {
        return (YouTube$LiveBroadcasts$List) super.setFields(str);
    }

    public YouTube$LiveBroadcasts$List setKey(String str) {
        return (YouTube$LiveBroadcasts$List) super.setKey(str);
    }

    public YouTube$LiveBroadcasts$List setOauthToken(String str) {
        return (YouTube$LiveBroadcasts$List) super.setOauthToken(str);
    }

    public YouTube$LiveBroadcasts$List setPrettyPrint(Boolean bool) {
        return (YouTube$LiveBroadcasts$List) super.setPrettyPrint(bool);
    }

    public YouTube$LiveBroadcasts$List setQuotaUser(String str) {
        return (YouTube$LiveBroadcasts$List) super.setQuotaUser(str);
    }

    public YouTube$LiveBroadcasts$List setUserIp(String str) {
        return (YouTube$LiveBroadcasts$List) super.setUserIp(str);
    }

    public String getPart() {
        return this.part;
    }

    public YouTube$LiveBroadcasts$List setPart(String str) {
        this.part = str;
        return this;
    }

    public String getBroadcastStatus() {
        return this.broadcastStatus;
    }

    public YouTube$LiveBroadcasts$List setBroadcastStatus(String str) {
        this.broadcastStatus = str;
        return this;
    }

    public String getOnBehalfOfContentOwner() {
        return this.onBehalfOfContentOwner;
    }

    public YouTube$LiveBroadcasts$List setOnBehalfOfContentOwner(String str) {
        this.onBehalfOfContentOwner = str;
        return this;
    }

    public String getOnBehalfOfContentOwnerChannel() {
        return this.onBehalfOfContentOwnerChannel;
    }

    public YouTube$LiveBroadcasts$List setOnBehalfOfContentOwnerChannel(String str) {
        this.onBehalfOfContentOwnerChannel = str;
        return this;
    }

    public Boolean getMine() {
        return this.mine;
    }

    public YouTube$LiveBroadcasts$List setMine(Boolean bool) {
        this.mine = bool;
        return this;
    }

    public Long getMaxResults() {
        return this.maxResults;
    }

    public YouTube$LiveBroadcasts$List setMaxResults(Long l) {
        this.maxResults = l;
        return this;
    }

    public String getPageToken() {
        return this.pageToken;
    }

    public YouTube$LiveBroadcasts$List setPageToken(String str) {
        this.pageToken = str;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public YouTube$LiveBroadcasts$List setId(String str) {
        this.id = str;
        return this;
    }

    public YouTube$LiveBroadcasts$List set(String str, Object obj) {
        return (YouTube$LiveBroadcasts$List) super.set(str, obj);
    }
}
