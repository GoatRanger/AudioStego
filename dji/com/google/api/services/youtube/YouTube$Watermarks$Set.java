package com.google.api.services.youtube;

import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.Watermarks;
import com.google.api.services.youtube.model.InvideoBranding;

public class YouTube$Watermarks$Set extends YouTubeRequest<Void> {
    private static final String REST_PATH = "watermarks/set";
    @Key
    private String channelId;
    @Key
    private String onBehalfOfContentOwner;
    final /* synthetic */ Watermarks this$1;

    protected YouTube$Watermarks$Set(Watermarks watermarks, String str, InvideoBranding invideoBranding) {
        this.this$1 = watermarks;
        super(watermarks.this$0, HttpMethods.POST, REST_PATH, invideoBranding, Void.class);
        this.channelId = (String) Preconditions.checkNotNull(str, "Required parameter channelId must be specified.");
    }

    protected YouTube$Watermarks$Set(Watermarks watermarks, String str, InvideoBranding invideoBranding, AbstractInputStreamContent abstractInputStreamContent) {
        this.this$1 = watermarks;
        super(watermarks.this$0, HttpMethods.POST, "/upload/" + watermarks.this$0.getServicePath() + REST_PATH, invideoBranding, Void.class);
        this.channelId = (String) Preconditions.checkNotNull(str, "Required parameter channelId must be specified.");
        initializeMediaUpload(abstractInputStreamContent);
    }

    public YouTube$Watermarks$Set setAlt(String str) {
        return (YouTube$Watermarks$Set) super.setAlt(str);
    }

    public YouTube$Watermarks$Set setFields(String str) {
        return (YouTube$Watermarks$Set) super.setFields(str);
    }

    public YouTube$Watermarks$Set setKey(String str) {
        return (YouTube$Watermarks$Set) super.setKey(str);
    }

    public YouTube$Watermarks$Set setOauthToken(String str) {
        return (YouTube$Watermarks$Set) super.setOauthToken(str);
    }

    public YouTube$Watermarks$Set setPrettyPrint(Boolean bool) {
        return (YouTube$Watermarks$Set) super.setPrettyPrint(bool);
    }

    public YouTube$Watermarks$Set setQuotaUser(String str) {
        return (YouTube$Watermarks$Set) super.setQuotaUser(str);
    }

    public YouTube$Watermarks$Set setUserIp(String str) {
        return (YouTube$Watermarks$Set) super.setUserIp(str);
    }

    public String getChannelId() {
        return this.channelId;
    }

    public YouTube$Watermarks$Set setChannelId(String str) {
        this.channelId = str;
        return this;
    }

    public String getOnBehalfOfContentOwner() {
        return this.onBehalfOfContentOwner;
    }

    public YouTube$Watermarks$Set setOnBehalfOfContentOwner(String str) {
        this.onBehalfOfContentOwner = str;
        return this;
    }

    public YouTube$Watermarks$Set set(String str, Object obj) {
        return (YouTube$Watermarks$Set) super.set(str, obj);
    }
}
