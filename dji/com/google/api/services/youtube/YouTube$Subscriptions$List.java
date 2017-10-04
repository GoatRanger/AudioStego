package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.Subscriptions;
import com.google.api.services.youtube.model.SubscriptionListResponse;
import java.io.IOException;

public class YouTube$Subscriptions$List extends YouTubeRequest<SubscriptionListResponse> {
    private static final String REST_PATH = "subscriptions";
    @Key
    private String channelId;
    @Key
    private String forChannelId;
    @Key
    private String id;
    @Key
    private Long maxResults;
    @Key
    private Boolean mine;
    @Key
    private Boolean mySubscribers;
    @Key
    private String onBehalfOfContentOwner;
    @Key
    private String onBehalfOfContentOwnerChannel;
    @Key
    private String order;
    @Key
    private String pageToken;
    @Key
    private String part;
    final /* synthetic */ Subscriptions this$1;

    protected YouTube$Subscriptions$List(Subscriptions subscriptions, String str) {
        this.this$1 = subscriptions;
        super(subscriptions.this$0, HttpMethods.GET, REST_PATH, null, SubscriptionListResponse.class);
        this.part = (String) Preconditions.checkNotNull(str, "Required parameter part must be specified.");
    }

    public HttpResponse executeUsingHead() throws IOException {
        return super.executeUsingHead();
    }

    public HttpRequest buildHttpRequestUsingHead() throws IOException {
        return super.buildHttpRequestUsingHead();
    }

    public YouTube$Subscriptions$List setAlt(String str) {
        return (YouTube$Subscriptions$List) super.setAlt(str);
    }

    public YouTube$Subscriptions$List setFields(String str) {
        return (YouTube$Subscriptions$List) super.setFields(str);
    }

    public YouTube$Subscriptions$List setKey(String str) {
        return (YouTube$Subscriptions$List) super.setKey(str);
    }

    public YouTube$Subscriptions$List setOauthToken(String str) {
        return (YouTube$Subscriptions$List) super.setOauthToken(str);
    }

    public YouTube$Subscriptions$List setPrettyPrint(Boolean bool) {
        return (YouTube$Subscriptions$List) super.setPrettyPrint(bool);
    }

    public YouTube$Subscriptions$List setQuotaUser(String str) {
        return (YouTube$Subscriptions$List) super.setQuotaUser(str);
    }

    public YouTube$Subscriptions$List setUserIp(String str) {
        return (YouTube$Subscriptions$List) super.setUserIp(str);
    }

    public String getPart() {
        return this.part;
    }

    public YouTube$Subscriptions$List setPart(String str) {
        this.part = str;
        return this;
    }

    public String getOnBehalfOfContentOwner() {
        return this.onBehalfOfContentOwner;
    }

    public YouTube$Subscriptions$List setOnBehalfOfContentOwner(String str) {
        this.onBehalfOfContentOwner = str;
        return this;
    }

    public String getOnBehalfOfContentOwnerChannel() {
        return this.onBehalfOfContentOwnerChannel;
    }

    public YouTube$Subscriptions$List setOnBehalfOfContentOwnerChannel(String str) {
        this.onBehalfOfContentOwnerChannel = str;
        return this;
    }

    public String getChannelId() {
        return this.channelId;
    }

    public YouTube$Subscriptions$List setChannelId(String str) {
        this.channelId = str;
        return this;
    }

    public Boolean getMine() {
        return this.mine;
    }

    public YouTube$Subscriptions$List setMine(Boolean bool) {
        this.mine = bool;
        return this;
    }

    public Long getMaxResults() {
        return this.maxResults;
    }

    public YouTube$Subscriptions$List setMaxResults(Long l) {
        this.maxResults = l;
        return this;
    }

    public String getForChannelId() {
        return this.forChannelId;
    }

    public YouTube$Subscriptions$List setForChannelId(String str) {
        this.forChannelId = str;
        return this;
    }

    public String getPageToken() {
        return this.pageToken;
    }

    public YouTube$Subscriptions$List setPageToken(String str) {
        this.pageToken = str;
        return this;
    }

    public Boolean getMySubscribers() {
        return this.mySubscribers;
    }

    public YouTube$Subscriptions$List setMySubscribers(Boolean bool) {
        this.mySubscribers = bool;
        return this;
    }

    public String getOrder() {
        return this.order;
    }

    public YouTube$Subscriptions$List setOrder(String str) {
        this.order = str;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public YouTube$Subscriptions$List setId(String str) {
        this.id = str;
        return this;
    }

    public YouTube$Subscriptions$List set(String str, Object obj) {
        return (YouTube$Subscriptions$List) super.set(str, obj);
    }
}
