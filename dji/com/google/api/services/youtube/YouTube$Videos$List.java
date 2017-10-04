package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.Videos;
import com.google.api.services.youtube.model.VideoListResponse;
import java.io.IOException;

public class YouTube$Videos$List extends YouTubeRequest<VideoListResponse> {
    private static final String REST_PATH = "videos";
    @Key
    private String chart;
    @Key
    private String id;
    @Key
    private String locale;
    @Key
    private Long maxResults;
    @Key
    private String myRating;
    @Key
    private String onBehalfOfContentOwner;
    @Key
    private String pageToken;
    @Key
    private String part;
    @Key
    private String regionCode;
    final /* synthetic */ Videos this$1;
    @Key
    private String videoCategoryId;

    protected YouTube$Videos$List(Videos videos, String str) {
        this.this$1 = videos;
        super(videos.this$0, HttpMethods.GET, "videos", null, VideoListResponse.class);
        this.part = (String) Preconditions.checkNotNull(str, "Required parameter part must be specified.");
    }

    public HttpResponse executeUsingHead() throws IOException {
        return super.executeUsingHead();
    }

    public HttpRequest buildHttpRequestUsingHead() throws IOException {
        return super.buildHttpRequestUsingHead();
    }

    public YouTube$Videos$List setAlt(String str) {
        return (YouTube$Videos$List) super.setAlt(str);
    }

    public YouTube$Videos$List setFields(String str) {
        return (YouTube$Videos$List) super.setFields(str);
    }

    public YouTube$Videos$List setKey(String str) {
        return (YouTube$Videos$List) super.setKey(str);
    }

    public YouTube$Videos$List setOauthToken(String str) {
        return (YouTube$Videos$List) super.setOauthToken(str);
    }

    public YouTube$Videos$List setPrettyPrint(Boolean bool) {
        return (YouTube$Videos$List) super.setPrettyPrint(bool);
    }

    public YouTube$Videos$List setQuotaUser(String str) {
        return (YouTube$Videos$List) super.setQuotaUser(str);
    }

    public YouTube$Videos$List setUserIp(String str) {
        return (YouTube$Videos$List) super.setUserIp(str);
    }

    public String getPart() {
        return this.part;
    }

    public YouTube$Videos$List setPart(String str) {
        this.part = str;
        return this;
    }

    public String getOnBehalfOfContentOwner() {
        return this.onBehalfOfContentOwner;
    }

    public YouTube$Videos$List setOnBehalfOfContentOwner(String str) {
        this.onBehalfOfContentOwner = str;
        return this;
    }

    public String getRegionCode() {
        return this.regionCode;
    }

    public YouTube$Videos$List setRegionCode(String str) {
        this.regionCode = str;
        return this;
    }

    public String getLocale() {
        return this.locale;
    }

    public YouTube$Videos$List setLocale(String str) {
        this.locale = str;
        return this;
    }

    public String getVideoCategoryId() {
        return this.videoCategoryId;
    }

    public YouTube$Videos$List setVideoCategoryId(String str) {
        this.videoCategoryId = str;
        return this;
    }

    public String getChart() {
        return this.chart;
    }

    public YouTube$Videos$List setChart(String str) {
        this.chart = str;
        return this;
    }

    public Long getMaxResults() {
        return this.maxResults;
    }

    public YouTube$Videos$List setMaxResults(Long l) {
        this.maxResults = l;
        return this;
    }

    public String getPageToken() {
        return this.pageToken;
    }

    public YouTube$Videos$List setPageToken(String str) {
        this.pageToken = str;
        return this;
    }

    public String getMyRating() {
        return this.myRating;
    }

    public YouTube$Videos$List setMyRating(String str) {
        this.myRating = str;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public YouTube$Videos$List setId(String str) {
        this.id = str;
        return this;
    }

    public YouTube$Videos$List set(String str, Object obj) {
        return (YouTube$Videos$List) super.set(str, obj);
    }
}
