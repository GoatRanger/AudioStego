package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.Channels;
import com.google.api.services.youtube.model.ChannelListResponse;
import java.io.IOException;

public class YouTube$Channels$List extends YouTubeRequest<ChannelListResponse> {
    private static final String REST_PATH = "channels";
    @Key
    private String categoryId;
    @Key
    private String forUsername;
    @Key
    private String id;
    @Key
    private Boolean managedByMe;
    @Key
    private Long maxResults;
    @Key
    private Boolean mine;
    @Key
    private Boolean mySubscribers;
    @Key
    private String onBehalfOfContentOwner;
    @Key
    private String pageToken;
    @Key
    private String part;
    final /* synthetic */ Channels this$1;

    protected YouTube$Channels$List(Channels channels, String str) {
        this.this$1 = channels;
        super(channels.this$0, HttpMethods.GET, REST_PATH, null, ChannelListResponse.class);
        this.part = (String) Preconditions.checkNotNull(str, "Required parameter part must be specified.");
    }

    public HttpResponse executeUsingHead() throws IOException {
        return super.executeUsingHead();
    }

    public HttpRequest buildHttpRequestUsingHead() throws IOException {
        return super.buildHttpRequestUsingHead();
    }

    public YouTube$Channels$List setAlt(String str) {
        return (YouTube$Channels$List) super.setAlt(str);
    }

    public YouTube$Channels$List setFields(String str) {
        return (YouTube$Channels$List) super.setFields(str);
    }

    public YouTube$Channels$List setKey(String str) {
        return (YouTube$Channels$List) super.setKey(str);
    }

    public YouTube$Channels$List setOauthToken(String str) {
        return (YouTube$Channels$List) super.setOauthToken(str);
    }

    public YouTube$Channels$List setPrettyPrint(Boolean bool) {
        return (YouTube$Channels$List) super.setPrettyPrint(bool);
    }

    public YouTube$Channels$List setQuotaUser(String str) {
        return (YouTube$Channels$List) super.setQuotaUser(str);
    }

    public YouTube$Channels$List setUserIp(String str) {
        return (YouTube$Channels$List) super.setUserIp(str);
    }

    public String getPart() {
        return this.part;
    }

    public YouTube$Channels$List setPart(String str) {
        this.part = str;
        return this;
    }

    public Boolean getManagedByMe() {
        return this.managedByMe;
    }

    public YouTube$Channels$List setManagedByMe(Boolean bool) {
        this.managedByMe = bool;
        return this;
    }

    public String getOnBehalfOfContentOwner() {
        return this.onBehalfOfContentOwner;
    }

    public YouTube$Channels$List setOnBehalfOfContentOwner(String str) {
        this.onBehalfOfContentOwner = str;
        return this;
    }

    public String getForUsername() {
        return this.forUsername;
    }

    public YouTube$Channels$List setForUsername(String str) {
        this.forUsername = str;
        return this;
    }

    public Boolean getMine() {
        return this.mine;
    }

    public YouTube$Channels$List setMine(Boolean bool) {
        this.mine = bool;
        return this;
    }

    public Long getMaxResults() {
        return this.maxResults;
    }

    public YouTube$Channels$List setMaxResults(Long l) {
        this.maxResults = l;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public YouTube$Channels$List setId(String str) {
        this.id = str;
        return this;
    }

    public String getPageToken() {
        return this.pageToken;
    }

    public YouTube$Channels$List setPageToken(String str) {
        this.pageToken = str;
        return this;
    }

    public Boolean getMySubscribers() {
        return this.mySubscribers;
    }

    public YouTube$Channels$List setMySubscribers(Boolean bool) {
        this.mySubscribers = bool;
        return this;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public YouTube$Channels$List setCategoryId(String str) {
        this.categoryId = str;
        return this;
    }

    public YouTube$Channels$List set(String str, Object obj) {
        return (YouTube$Channels$List) super.set(str, obj);
    }
}
