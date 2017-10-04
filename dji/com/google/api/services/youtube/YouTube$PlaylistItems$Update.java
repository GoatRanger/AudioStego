package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.PlaylistItems;
import com.google.api.services.youtube.model.PlaylistItem;

public class YouTube$PlaylistItems$Update extends YouTubeRequest<PlaylistItem> {
    private static final String REST_PATH = "playlistItems";
    @Key
    private String part;
    final /* synthetic */ PlaylistItems this$1;

    protected YouTube$PlaylistItems$Update(PlaylistItems playlistItems, String str, PlaylistItem playlistItem) {
        this.this$1 = playlistItems;
        super(playlistItems.this$0, HttpMethods.PUT, REST_PATH, playlistItem, PlaylistItem.class);
        this.part = (String) Preconditions.checkNotNull(str, "Required parameter part must be specified.");
    }

    public YouTube$PlaylistItems$Update setAlt(String str) {
        return (YouTube$PlaylistItems$Update) super.setAlt(str);
    }

    public YouTube$PlaylistItems$Update setFields(String str) {
        return (YouTube$PlaylistItems$Update) super.setFields(str);
    }

    public YouTube$PlaylistItems$Update setKey(String str) {
        return (YouTube$PlaylistItems$Update) super.setKey(str);
    }

    public YouTube$PlaylistItems$Update setOauthToken(String str) {
        return (YouTube$PlaylistItems$Update) super.setOauthToken(str);
    }

    public YouTube$PlaylistItems$Update setPrettyPrint(Boolean bool) {
        return (YouTube$PlaylistItems$Update) super.setPrettyPrint(bool);
    }

    public YouTube$PlaylistItems$Update setQuotaUser(String str) {
        return (YouTube$PlaylistItems$Update) super.setQuotaUser(str);
    }

    public YouTube$PlaylistItems$Update setUserIp(String str) {
        return (YouTube$PlaylistItems$Update) super.setUserIp(str);
    }

    public String getPart() {
        return this.part;
    }

    public YouTube$PlaylistItems$Update setPart(String str) {
        this.part = str;
        return this;
    }

    public YouTube$PlaylistItems$Update set(String str, Object obj) {
        return (YouTube$PlaylistItems$Update) super.set(str, obj);
    }
}
