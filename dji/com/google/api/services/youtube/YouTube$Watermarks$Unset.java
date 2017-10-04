package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.Watermarks;

public class YouTube$Watermarks$Unset extends YouTubeRequest<Void> {
    private static final String REST_PATH = "watermarks/unset";
    @Key
    private String channelId;
    @Key
    private String onBehalfOfContentOwner;
    final /* synthetic */ Watermarks this$1;

    protected YouTube$Watermarks$Unset(Watermarks watermarks, String str) {
        this.this$1 = watermarks;
        super(watermarks.this$0, HttpMethods.POST, REST_PATH, null, Void.class);
        this.channelId = (String) Preconditions.checkNotNull(str, "Required parameter channelId must be specified.");
    }

    public YouTube$Watermarks$Unset setAlt(String str) {
        return (YouTube$Watermarks$Unset) super.setAlt(str);
    }

    public YouTube$Watermarks$Unset setFields(String str) {
        return (YouTube$Watermarks$Unset) super.setFields(str);
    }

    public YouTube$Watermarks$Unset setKey(String str) {
        return (YouTube$Watermarks$Unset) super.setKey(str);
    }

    public YouTube$Watermarks$Unset setOauthToken(String str) {
        return (YouTube$Watermarks$Unset) super.setOauthToken(str);
    }

    public YouTube$Watermarks$Unset setPrettyPrint(Boolean bool) {
        return (YouTube$Watermarks$Unset) super.setPrettyPrint(bool);
    }

    public YouTube$Watermarks$Unset setQuotaUser(String str) {
        return (YouTube$Watermarks$Unset) super.setQuotaUser(str);
    }

    public YouTube$Watermarks$Unset setUserIp(String str) {
        return (YouTube$Watermarks$Unset) super.setUserIp(str);
    }

    public String getChannelId() {
        return this.channelId;
    }

    public YouTube$Watermarks$Unset setChannelId(String str) {
        this.channelId = str;
        return this;
    }

    public String getOnBehalfOfContentOwner() {
        return this.onBehalfOfContentOwner;
    }

    public YouTube$Watermarks$Unset setOnBehalfOfContentOwner(String str) {
        this.onBehalfOfContentOwner = str;
        return this;
    }

    public YouTube$Watermarks$Unset set(String str, Object obj) {
        return (YouTube$Watermarks$Unset) super.set(str, obj);
    }
}
