package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.Playlists;
import com.google.api.services.youtube.model.Playlist;

public class YouTube$Playlists$Update extends YouTubeRequest<Playlist> {
    private static final String REST_PATH = "playlists";
    @Key
    private String onBehalfOfContentOwner;
    @Key
    private String part;
    final /* synthetic */ Playlists this$1;

    protected YouTube$Playlists$Update(Playlists playlists, String str, Playlist playlist) {
        this.this$1 = playlists;
        super(playlists.this$0, HttpMethods.PUT, REST_PATH, playlist, Playlist.class);
        this.part = (String) Preconditions.checkNotNull(str, "Required parameter part must be specified.");
    }

    public YouTube$Playlists$Update setAlt(String str) {
        return (YouTube$Playlists$Update) super.setAlt(str);
    }

    public YouTube$Playlists$Update setFields(String str) {
        return (YouTube$Playlists$Update) super.setFields(str);
    }

    public YouTube$Playlists$Update setKey(String str) {
        return (YouTube$Playlists$Update) super.setKey(str);
    }

    public YouTube$Playlists$Update setOauthToken(String str) {
        return (YouTube$Playlists$Update) super.setOauthToken(str);
    }

    public YouTube$Playlists$Update setPrettyPrint(Boolean bool) {
        return (YouTube$Playlists$Update) super.setPrettyPrint(bool);
    }

    public YouTube$Playlists$Update setQuotaUser(String str) {
        return (YouTube$Playlists$Update) super.setQuotaUser(str);
    }

    public YouTube$Playlists$Update setUserIp(String str) {
        return (YouTube$Playlists$Update) super.setUserIp(str);
    }

    public String getPart() {
        return this.part;
    }

    public YouTube$Playlists$Update setPart(String str) {
        this.part = str;
        return this;
    }

    public String getOnBehalfOfContentOwner() {
        return this.onBehalfOfContentOwner;
    }

    public YouTube$Playlists$Update setOnBehalfOfContentOwner(String str) {
        this.onBehalfOfContentOwner = str;
        return this;
    }

    public YouTube$Playlists$Update set(String str, Object obj) {
        return (YouTube$Playlists$Update) super.set(str, obj);
    }
}
