package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.Activities;
import com.google.api.services.youtube.model.ActivityListResponse;
import java.io.IOException;

public class YouTube$Activities$List extends YouTubeRequest<ActivityListResponse> {
    private static final String REST_PATH = "activities";
    @Key
    private String channelId;
    @Key
    private Boolean home;
    @Key
    private Long maxResults;
    @Key
    private Boolean mine;
    @Key
    private String pageToken;
    @Key
    private String part;
    @Key
    private DateTime publishedAfter;
    @Key
    private DateTime publishedBefore;
    @Key
    private String regionCode;
    final /* synthetic */ Activities this$1;

    protected YouTube$Activities$List(Activities activities, String str) {
        this.this$1 = activities;
        super(activities.this$0, HttpMethods.GET, REST_PATH, null, ActivityListResponse.class);
        this.part = (String) Preconditions.checkNotNull(str, "Required parameter part must be specified.");
    }

    public HttpResponse executeUsingHead() throws IOException {
        return super.executeUsingHead();
    }

    public HttpRequest buildHttpRequestUsingHead() throws IOException {
        return super.buildHttpRequestUsingHead();
    }

    public YouTube$Activities$List setAlt(String str) {
        return (YouTube$Activities$List) super.setAlt(str);
    }

    public YouTube$Activities$List setFields(String str) {
        return (YouTube$Activities$List) super.setFields(str);
    }

    public YouTube$Activities$List setKey(String str) {
        return (YouTube$Activities$List) super.setKey(str);
    }

    public YouTube$Activities$List setOauthToken(String str) {
        return (YouTube$Activities$List) super.setOauthToken(str);
    }

    public YouTube$Activities$List setPrettyPrint(Boolean bool) {
        return (YouTube$Activities$List) super.setPrettyPrint(bool);
    }

    public YouTube$Activities$List setQuotaUser(String str) {
        return (YouTube$Activities$List) super.setQuotaUser(str);
    }

    public YouTube$Activities$List setUserIp(String str) {
        return (YouTube$Activities$List) super.setUserIp(str);
    }

    public String getPart() {
        return this.part;
    }

    public YouTube$Activities$List setPart(String str) {
        this.part = str;
        return this;
    }

    public String getRegionCode() {
        return this.regionCode;
    }

    public YouTube$Activities$List setRegionCode(String str) {
        this.regionCode = str;
        return this;
    }

    public DateTime getPublishedBefore() {
        return this.publishedBefore;
    }

    public YouTube$Activities$List setPublishedBefore(DateTime dateTime) {
        this.publishedBefore = dateTime;
        return this;
    }

    public String getChannelId() {
        return this.channelId;
    }

    public YouTube$Activities$List setChannelId(String str) {
        this.channelId = str;
        return this;
    }

    public Boolean getMine() {
        return this.mine;
    }

    public YouTube$Activities$List setMine(Boolean bool) {
        this.mine = bool;
        return this;
    }

    public Long getMaxResults() {
        return this.maxResults;
    }

    public YouTube$Activities$List setMaxResults(Long l) {
        this.maxResults = l;
        return this;
    }

    public String getPageToken() {
        return this.pageToken;
    }

    public YouTube$Activities$List setPageToken(String str) {
        this.pageToken = str;
        return this;
    }

    public Boolean getHome() {
        return this.home;
    }

    public YouTube$Activities$List setHome(Boolean bool) {
        this.home = bool;
        return this;
    }

    public DateTime getPublishedAfter() {
        return this.publishedAfter;
    }

    public YouTube$Activities$List setPublishedAfter(DateTime dateTime) {
        this.publishedAfter = dateTime;
        return this;
    }

    public YouTube$Activities$List set(String str, Object obj) {
        return (YouTube$Activities$List) super.set(str, obj);
    }
}
