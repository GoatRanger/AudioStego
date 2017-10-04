package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.Playlists;
import com.google.api.services.youtube.model.PlaylistListResponse;
import java.io.IOException;

public class YouTube$Playlists$List extends YouTubeRequest<PlaylistListResponse> {
    private static final String REST_PATH = "playlists";
    @Key
    private String channelId;
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
    final /* synthetic */ Playlists this$1;

    protected YouTube$Playlists$List(Playlists playlists, String str) {
        this.this$1 = playlists;
        super(playlists.this$0, HttpMethods.GET, REST_PATH, null, PlaylistListResponse.class);
        this.part = (String) Preconditions.checkNotNull(str, "Required parameter part must be specified.");
    }

    public HttpResponse executeUsingHead() throws IOException {
        return super.executeUsingHead();
    }

    public HttpRequest buildHttpRequestUsingHead() throws IOException {
        return super.buildHttpRequestUsingHead();
    }

    public YouTube$Playlists$List setAlt(String str) {
        return (YouTube$Playlists$List) super.setAlt(str);
    }

    public YouTube$Playlists$List setFields(String str) {
        return (YouTube$Playlists$List) super.setFields(str);
    }

    public YouTube$Playlists$List setKey(String str) {
        return (YouTube$Playlists$List) super.setKey(str);
    }

    public YouTube$Playlists$List setOauthToken(String str) {
        return (YouTube$Playlists$List) super.setOauthToken(str);
    }

    public YouTube$Playlists$List setPrettyPrint(Boolean bool) {
        return (YouTube$Playlists$List) super.setPrettyPrint(bool);
    }

    public YouTube$Playlists$List setQuotaUser(String str) {
        return (YouTube$Playlists$List) super.setQuotaUser(str);
    }

    public YouTube$Playlists$List setUserIp(String str) {
        return (YouTube$Playlists$List) super.setUserIp(str);
    }

    public String getPart() {
        return this.part;
    }

    public YouTube$Playlists$List setPart(String str) {
        this.part = str;
        return this;
    }

    public String getOnBehalfOfContentOwner() {
        return this.onBehalfOfContentOwner;
    }

    public YouTube$Playlists$List setOnBehalfOfContentOwner(String str) {
        this.onBehalfOfContentOwner = str;
        return this;
    }

    public String getOnBehalfOfContentOwnerChannel() {
        return this.onBehalfOfContentOwnerChannel;
    }

    public YouTube$Playlists$List setOnBehalfOfContentOwnerChannel(String str) {
        this.onBehalfOfContentOwnerChannel = str;
        return this;
    }

    public String getChannelId() {
        return this.channelId;
    }

    public YouTube$Playlists$List setChannelId(String str) {
        this.channelId = str;
        return this;
    }

    public Boolean getMine() {
        return this.mine;
    }

    public YouTube$Playlists$List setMine(Boolean bool) {
        this.mine = bool;
        return this;
    }

    public Long getMaxResults() {
        return this.maxResults;
    }

    public YouTube$Playlists$List setMaxResults(Long l) {
        this.maxResults = l;
        return this;
    }

    public String getPageToken() {
        return this.pageToken;
    }

    public YouTube$Playlists$List setPageToken(String str) {
        this.pageToken = str;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public YouTube$Playlists$List setId(String str) {
        this.id = str;
        return this;
    }

    public YouTube$Playlists$List set(String str, Object obj) {
        return (YouTube$Playlists$List) super.set(str, obj);
    }
}
