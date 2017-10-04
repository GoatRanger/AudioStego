package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.Playlists;

public class YouTube$Playlists$Delete extends YouTubeRequest<Void> {
    private static final String REST_PATH = "playlists";
    @Key
    private String id;
    @Key
    private String onBehalfOfContentOwner;
    final /* synthetic */ Playlists this$1;

    protected YouTube$Playlists$Delete(Playlists playlists, String str) {
        this.this$1 = playlists;
        super(playlists.this$0, HttpMethods.DELETE, REST_PATH, null, Void.class);
        this.id = (String) Preconditions.checkNotNull(str, "Required parameter id must be specified.");
    }

    public YouTube$Playlists$Delete setAlt(String str) {
        return (YouTube$Playlists$Delete) super.setAlt(str);
    }

    public YouTube$Playlists$Delete setFields(String str) {
        return (YouTube$Playlists$Delete) super.setFields(str);
    }

    public YouTube$Playlists$Delete setKey(String str) {
        return (YouTube$Playlists$Delete) super.setKey(str);
    }

    public YouTube$Playlists$Delete setOauthToken(String str) {
        return (YouTube$Playlists$Delete) super.setOauthToken(str);
    }

    public YouTube$Playlists$Delete setPrettyPrint(Boolean bool) {
        return (YouTube$Playlists$Delete) super.setPrettyPrint(bool);
    }

    public YouTube$Playlists$Delete setQuotaUser(String str) {
        return (YouTube$Playlists$Delete) super.setQuotaUser(str);
    }

    public YouTube$Playlists$Delete setUserIp(String str) {
        return (YouTube$Playlists$Delete) super.setUserIp(str);
    }

    public String getId() {
        return this.id;
    }

    public YouTube$Playlists$Delete setId(String str) {
        this.id = str;
        return this;
    }

    public String getOnBehalfOfContentOwner() {
        return this.onBehalfOfContentOwner;
    }

    public YouTube$Playlists$Delete setOnBehalfOfContentOwner(String str) {
        this.onBehalfOfContentOwner = str;
        return this;
    }

    public YouTube$Playlists$Delete set(String str, Object obj) {
        return (YouTube$Playlists$Delete) super.set(str, obj);
    }
}
