package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.PlaylistItems;

public class YouTube$PlaylistItems$Delete extends YouTubeRequest<Void> {
    private static final String REST_PATH = "playlistItems";
    @Key
    private String id;
    final /* synthetic */ PlaylistItems this$1;

    protected YouTube$PlaylistItems$Delete(PlaylistItems playlistItems, String str) {
        this.this$1 = playlistItems;
        super(playlistItems.this$0, HttpMethods.DELETE, REST_PATH, null, Void.class);
        this.id = (String) Preconditions.checkNotNull(str, "Required parameter id must be specified.");
    }

    public YouTube$PlaylistItems$Delete setAlt(String str) {
        return (YouTube$PlaylistItems$Delete) super.setAlt(str);
    }

    public YouTube$PlaylistItems$Delete setFields(String str) {
        return (YouTube$PlaylistItems$Delete) super.setFields(str);
    }

    public YouTube$PlaylistItems$Delete setKey(String str) {
        return (YouTube$PlaylistItems$Delete) super.setKey(str);
    }

    public YouTube$PlaylistItems$Delete setOauthToken(String str) {
        return (YouTube$PlaylistItems$Delete) super.setOauthToken(str);
    }

    public YouTube$PlaylistItems$Delete setPrettyPrint(Boolean bool) {
        return (YouTube$PlaylistItems$Delete) super.setPrettyPrint(bool);
    }

    public YouTube$PlaylistItems$Delete setQuotaUser(String str) {
        return (YouTube$PlaylistItems$Delete) super.setQuotaUser(str);
    }

    public YouTube$PlaylistItems$Delete setUserIp(String str) {
        return (YouTube$PlaylistItems$Delete) super.setUserIp(str);
    }

    public String getId() {
        return this.id;
    }

    public YouTube$PlaylistItems$Delete setId(String str) {
        this.id = str;
        return this;
    }

    public YouTube$PlaylistItems$Delete set(String str, Object obj) {
        return (YouTube$PlaylistItems$Delete) super.set(str, obj);
    }
}
