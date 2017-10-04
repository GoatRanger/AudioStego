package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.Playlists;
import com.google.api.services.youtube.model.Playlist;

public class YouTube$Playlists$Insert extends YouTubeRequest<Playlist> {
    private static final String REST_PATH = "playlists";
    @Key
    private String onBehalfOfContentOwner;
    @Key
    private String onBehalfOfContentOwnerChannel;
    @Key
    private String part;
    final /* synthetic */ Playlists this$1;

    protected YouTube$Playlists$Insert(Playlists playlists, String str, Playlist playlist) {
        this.this$1 = playlists;
        super(playlists.this$0, HttpMethods.POST, REST_PATH, playlist, Playlist.class);
        this.part = (String) Preconditions.checkNotNull(str, "Required parameter part must be specified.");
    }

    public YouTube$Playlists$Insert setAlt(String str) {
        return (YouTube$Playlists$Insert) super.setAlt(str);
    }

    public YouTube$Playlists$Insert setFields(String str) {
        return (YouTube$Playlists$Insert) super.setFields(str);
    }

    public YouTube$Playlists$Insert setKey(String str) {
        return (YouTube$Playlists$Insert) super.setKey(str);
    }

    public YouTube$Playlists$Insert setOauthToken(String str) {
        return (YouTube$Playlists$Insert) super.setOauthToken(str);
    }

    public YouTube$Playlists$Insert setPrettyPrint(Boolean bool) {
        return (YouTube$Playlists$Insert) super.setPrettyPrint(bool);
    }

    public YouTube$Playlists$Insert setQuotaUser(String str) {
        return (YouTube$Playlists$Insert) super.setQuotaUser(str);
    }

    public YouTube$Playlists$Insert setUserIp(String str) {
        return (YouTube$Playlists$Insert) super.setUserIp(str);
    }

    public String getPart() {
        return this.part;
    }

    public YouTube$Playlists$Insert setPart(String str) {
        this.part = str;
        return this;
    }

    public String getOnBehalfOfContentOwnerChannel() {
        return this.onBehalfOfContentOwnerChannel;
    }

    public YouTube$Playlists$Insert setOnBehalfOfContentOwnerChannel(String str) {
        this.onBehalfOfContentOwnerChannel = str;
        return this;
    }

    public String getOnBehalfOfContentOwner() {
        return this.onBehalfOfContentOwner;
    }

    public YouTube$Playlists$Insert setOnBehalfOfContentOwner(String str) {
        this.onBehalfOfContentOwner = str;
        return this;
    }

    public YouTube$Playlists$Insert set(String str, Object obj) {
        return (YouTube$Playlists$Insert) super.set(str, obj);
    }
}
