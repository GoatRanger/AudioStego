package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.Search;
import com.google.api.services.youtube.model.SearchListResponse;
import java.io.IOException;

public class YouTube$Search$List extends YouTubeRequest<SearchListResponse> {
    private static final String REST_PATH = "search";
    @Key
    private String channelId;
    @Key
    private String channelType;
    @Key
    private String eventType;
    @Key
    private Boolean forContentOwner;
    @Key
    private Boolean forMine;
    @Key
    private String location;
    @Key
    private String locationRadius;
    @Key
    private Long maxResults;
    @Key
    private String onBehalfOfContentOwner;
    @Key
    private String order;
    @Key
    private String pageToken;
    @Key
    private String part;
    @Key
    private DateTime publishedAfter;
    @Key
    private DateTime publishedBefore;
    @Key
    private String q;
    @Key
    private String regionCode;
    @Key
    private String relatedToVideoId;
    @Key
    private String safeSearch;
    final /* synthetic */ Search this$1;
    @Key
    private String topicId;
    @Key
    private String type;
    @Key
    private String videoCaption;
    @Key
    private String videoCategoryId;
    @Key
    private String videoDefinition;
    @Key
    private String videoDimension;
    @Key
    private String videoDuration;
    @Key
    private String videoEmbeddable;
    @Key
    private String videoLicense;
    @Key
    private String videoSyndicated;
    @Key
    private String videoType;

    protected YouTube$Search$List(Search search, String str) {
        this.this$1 = search;
        super(search.this$0, HttpMethods.GET, REST_PATH, null, SearchListResponse.class);
        this.part = (String) Preconditions.checkNotNull(str, "Required parameter part must be specified.");
    }

    public HttpResponse executeUsingHead() throws IOException {
        return super.executeUsingHead();
    }

    public HttpRequest buildHttpRequestUsingHead() throws IOException {
        return super.buildHttpRequestUsingHead();
    }

    public YouTube$Search$List setAlt(String str) {
        return (YouTube$Search$List) super.setAlt(str);
    }

    public YouTube$Search$List setFields(String str) {
        return (YouTube$Search$List) super.setFields(str);
    }

    public YouTube$Search$List setKey(String str) {
        return (YouTube$Search$List) super.setKey(str);
    }

    public YouTube$Search$List setOauthToken(String str) {
        return (YouTube$Search$List) super.setOauthToken(str);
    }

    public YouTube$Search$List setPrettyPrint(Boolean bool) {
        return (YouTube$Search$List) super.setPrettyPrint(bool);
    }

    public YouTube$Search$List setQuotaUser(String str) {
        return (YouTube$Search$List) super.setQuotaUser(str);
    }

    public YouTube$Search$List setUserIp(String str) {
        return (YouTube$Search$List) super.setUserIp(str);
    }

    public String getPart() {
        return this.part;
    }

    public YouTube$Search$List setPart(String str) {
        this.part = str;
        return this;
    }

    public String getEventType() {
        return this.eventType;
    }

    public YouTube$Search$List setEventType(String str) {
        this.eventType = str;
        return this;
    }

    public String getChannelId() {
        return this.channelId;
    }

    public YouTube$Search$List setChannelId(String str) {
        this.channelId = str;
        return this;
    }

    public String getVideoSyndicated() {
        return this.videoSyndicated;
    }

    public YouTube$Search$List setVideoSyndicated(String str) {
        this.videoSyndicated = str;
        return this;
    }

    public String getChannelType() {
        return this.channelType;
    }

    public YouTube$Search$List setChannelType(String str) {
        this.channelType = str;
        return this;
    }

    public String getVideoCaption() {
        return this.videoCaption;
    }

    public YouTube$Search$List setVideoCaption(String str) {
        this.videoCaption = str;
        return this;
    }

    public DateTime getPublishedAfter() {
        return this.publishedAfter;
    }

    public YouTube$Search$List setPublishedAfter(DateTime dateTime) {
        this.publishedAfter = dateTime;
        return this;
    }

    public String getOnBehalfOfContentOwner() {
        return this.onBehalfOfContentOwner;
    }

    public YouTube$Search$List setOnBehalfOfContentOwner(String str) {
        this.onBehalfOfContentOwner = str;
        return this;
    }

    public String getPageToken() {
        return this.pageToken;
    }

    public YouTube$Search$List setPageToken(String str) {
        this.pageToken = str;
        return this;
    }

    public Boolean getForContentOwner() {
        return this.forContentOwner;
    }

    public YouTube$Search$List setForContentOwner(Boolean bool) {
        this.forContentOwner = bool;
        return this;
    }

    public String getRegionCode() {
        return this.regionCode;
    }

    public YouTube$Search$List setRegionCode(String str) {
        this.regionCode = str;
        return this;
    }

    public String getLocation() {
        return this.location;
    }

    public YouTube$Search$List setLocation(String str) {
        this.location = str;
        return this;
    }

    public String getLocationRadius() {
        return this.locationRadius;
    }

    public YouTube$Search$List setLocationRadius(String str) {
        this.locationRadius = str;
        return this;
    }

    public String getVideoType() {
        return this.videoType;
    }

    public YouTube$Search$List setVideoType(String str) {
        this.videoType = str;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public YouTube$Search$List setType(String str) {
        this.type = str;
        return this;
    }

    public String getTopicId() {
        return this.topicId;
    }

    public YouTube$Search$List setTopicId(String str) {
        this.topicId = str;
        return this;
    }

    public DateTime getPublishedBefore() {
        return this.publishedBefore;
    }

    public YouTube$Search$List setPublishedBefore(DateTime dateTime) {
        this.publishedBefore = dateTime;
        return this;
    }

    public String getVideoDimension() {
        return this.videoDimension;
    }

    public YouTube$Search$List setVideoDimension(String str) {
        this.videoDimension = str;
        return this;
    }

    public String getVideoLicense() {
        return this.videoLicense;
    }

    public YouTube$Search$List setVideoLicense(String str) {
        this.videoLicense = str;
        return this;
    }

    public Long getMaxResults() {
        return this.maxResults;
    }

    public YouTube$Search$List setMaxResults(Long l) {
        this.maxResults = l;
        return this;
    }

    public String getRelatedToVideoId() {
        return this.relatedToVideoId;
    }

    public YouTube$Search$List setRelatedToVideoId(String str) {
        this.relatedToVideoId = str;
        return this;
    }

    public String getVideoDefinition() {
        return this.videoDefinition;
    }

    public YouTube$Search$List setVideoDefinition(String str) {
        this.videoDefinition = str;
        return this;
    }

    public String getVideoDuration() {
        return this.videoDuration;
    }

    public YouTube$Search$List setVideoDuration(String str) {
        this.videoDuration = str;
        return this;
    }

    public Boolean getForMine() {
        return this.forMine;
    }

    public YouTube$Search$List setForMine(Boolean bool) {
        this.forMine = bool;
        return this;
    }

    public String getQ() {
        return this.q;
    }

    public YouTube$Search$List setQ(String str) {
        this.q = str;
        return this;
    }

    public String getSafeSearch() {
        return this.safeSearch;
    }

    public YouTube$Search$List setSafeSearch(String str) {
        this.safeSearch = str;
        return this;
    }

    public String getVideoEmbeddable() {
        return this.videoEmbeddable;
    }

    public YouTube$Search$List setVideoEmbeddable(String str) {
        this.videoEmbeddable = str;
        return this;
    }

    public String getVideoCategoryId() {
        return this.videoCategoryId;
    }

    public YouTube$Search$List setVideoCategoryId(String str) {
        this.videoCategoryId = str;
        return this;
    }

    public String getOrder() {
        return this.order;
    }

    public YouTube$Search$List setOrder(String str) {
        this.order = str;
        return this;
    }

    public YouTube$Search$List set(String str, Object obj) {
        return (YouTube$Search$List) super.set(str, obj);
    }
}
