package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.PlaylistItems;
import com.google.api.services.youtube.model.PlaylistItem;

public class YouTube$PlaylistItems$Insert extends YouTubeRequest<PlaylistItem> {
    private static final String REST_PATH = "playlistItems";
    @Key
    private String onBehalfOfContentOwner;
    @Key
    private String part;
    final /* synthetic */ PlaylistItems this$1;

    protected YouTube$PlaylistItems$Insert(PlaylistItems playlistItems, String str, PlaylistItem playlistItem) {
        this.this$1 = playlistItems;
        super(playlistItems.this$0, HttpMethods.POST, REST_PATH, playlistItem, PlaylistItem.class);
        this.part = (String) Preconditions.checkNotNull(str, "Required parameter part must be specified.");
    }

    public YouTube$PlaylistItems$Insert setAlt(String str) {
        return (YouTube$PlaylistItems$Insert) super.setAlt(str);
    }

    public YouTube$PlaylistItems$Insert setFields(String str) {
        return (YouTube$PlaylistItems$Insert) super.setFields(str);
    }

    public YouTube$PlaylistItems$Insert setKey(String str) {
        return (YouTube$PlaylistItems$Insert) super.setKey(str);
    }

    public YouTube$PlaylistItems$Insert setOauthToken(String str) {
        return (YouTube$PlaylistItems$Insert) super.setOauthToken(str);
    }

    public YouTube$PlaylistItems$Insert setPrettyPrint(Boolean bool) {
        return (YouTube$PlaylistItems$Insert) super.setPrettyPrint(bool);
    }

    public YouTube$PlaylistItems$Insert setQuotaUser(String str) {
        return (YouTube$PlaylistItems$Insert) super.setQuotaUser(str);
    }

    public YouTube$PlaylistItems$Insert setUserIp(String str) {
        return (YouTube$PlaylistItems$Insert) super.setUserIp(str);
    }

    public String getPart() {
        return this.part;
    }

    public YouTube$PlaylistItems$Insert setPart(String str) {
        this.part = str;
        return this;
    }

    public String getOnBehalfOfContentOwner() {
        return this.onBehalfOfContentOwner;
    }

    public YouTube$PlaylistItems$Insert setOnBehalfOfContentOwner(String str) {
        this.onBehalfOfContentOwner = str;
        return this;
    }

    public YouTube$PlaylistItems$Insert set(String str, Object obj) {
        return (YouTube$PlaylistItems$Insert) super.set(str, obj);
    }
}
