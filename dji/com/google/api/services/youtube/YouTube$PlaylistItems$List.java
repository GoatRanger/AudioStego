package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.PlaylistItems;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import java.io.IOException;

public class YouTube$PlaylistItems$List extends YouTubeRequest<PlaylistItemListResponse> {
    private static final String REST_PATH = "playlistItems";
    @Key
    private String id;
    @Key
    private Long maxResults;
    @Key
    private String onBehalfOfContentOwner;
    @Key
    private String pageToken;
    @Key
    private String part;
    @Key
    private String playlistId;
    final /* synthetic */ PlaylistItems this$1;
    @Key
    private String videoId;

    protected YouTube$PlaylistItems$List(PlaylistItems playlistItems, String str) {
        this.this$1 = playlistItems;
        super(playlistItems.this$0, HttpMethods.GET, REST_PATH, null, PlaylistItemListResponse.class);
        this.part = (String) Preconditions.checkNotNull(str, "Required parameter part must be specified.");
    }

    public HttpResponse executeUsingHead() throws IOException {
        return super.executeUsingHead();
    }

    public HttpRequest buildHttpRequestUsingHead() throws IOException {
        return super.buildHttpRequestUsingHead();
    }

    public YouTube$PlaylistItems$List setAlt(String str) {
        return (YouTube$PlaylistItems$List) super.setAlt(str);
    }

    public YouTube$PlaylistItems$List setFields(String str) {
        return (YouTube$PlaylistItems$List) super.setFields(str);
    }

    public YouTube$PlaylistItems$List setKey(String str) {
        return (YouTube$PlaylistItems$List) super.setKey(str);
    }

    public YouTube$PlaylistItems$List setOauthToken(String str) {
        return (YouTube$PlaylistItems$List) super.setOauthToken(str);
    }

    public YouTube$PlaylistItems$List setPrettyPrint(Boolean bool) {
        return (YouTube$PlaylistItems$List) super.setPrettyPrint(bool);
    }

    public YouTube$PlaylistItems$List setQuotaUser(String str) {
        return (YouTube$PlaylistItems$List) super.setQuotaUser(str);
    }

    public YouTube$PlaylistItems$List setUserIp(String str) {
        return (YouTube$PlaylistItems$List) super.setUserIp(str);
    }

    public String getPart() {
        return this.part;
    }

    public YouTube$PlaylistItems$List setPart(String str) {
        this.part = str;
        return this;
    }

    public String getOnBehalfOfContentOwner() {
        return this.onBehalfOfContentOwner;
    }

    public YouTube$PlaylistItems$List setOnBehalfOfContentOwner(String str) {
        this.onBehalfOfContentOwner = str;
        return this;
    }

    public String getPlaylistId() {
        return this.playlistId;
    }

    public YouTube$PlaylistItems$List setPlaylistId(String str) {
        this.playlistId = str;
        return this;
    }

    public String getVideoId() {
        return this.videoId;
    }

    public YouTube$PlaylistItems$List setVideoId(String str) {
        this.videoId = str;
        return this;
    }

    public Long getMaxResults() {
        return this.maxResults;
    }

    public YouTube$PlaylistItems$List setMaxResults(Long l) {
        this.maxResults = l;
        return this;
    }

    public String getPageToken() {
        return this.pageToken;
    }

    public YouTube$PlaylistItems$List setPageToken(String str) {
        this.pageToken = str;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public YouTube$PlaylistItems$List setId(String str) {
        this.id = str;
        return this;
    }

    public YouTube$PlaylistItems$List set(String str, Object obj) {
        return (YouTube$PlaylistItems$List) super.set(str, obj);
    }
}
