package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.Subscriptions;

public class YouTube$Subscriptions$Delete extends YouTubeRequest<Void> {
    private static final String REST_PATH = "subscriptions";
    @Key
    private String id;
    final /* synthetic */ Subscriptions this$1;

    protected YouTube$Subscriptions$Delete(Subscriptions subscriptions, String str) {
        this.this$1 = subscriptions;
        super(subscriptions.this$0, HttpMethods.DELETE, REST_PATH, null, Void.class);
        this.id = (String) Preconditions.checkNotNull(str, "Required parameter id must be specified.");
    }

    public YouTube$Subscriptions$Delete setAlt(String str) {
        return (YouTube$Subscriptions$Delete) super.setAlt(str);
    }

    public YouTube$Subscriptions$Delete setFields(String str) {
        return (YouTube$Subscriptions$Delete) super.setFields(str);
    }

    public YouTube$Subscriptions$Delete setKey(String str) {
        return (YouTube$Subscriptions$Delete) super.setKey(str);
    }

    public YouTube$Subscriptions$Delete setOauthToken(String str) {
        return (YouTube$Subscriptions$Delete) super.setOauthToken(str);
    }

    public YouTube$Subscriptions$Delete setPrettyPrint(Boolean bool) {
        return (YouTube$Subscriptions$Delete) super.setPrettyPrint(bool);
    }

    public YouTube$Subscriptions$Delete setQuotaUser(String str) {
        return (YouTube$Subscriptions$Delete) super.setQuotaUser(str);
    }

    public YouTube$Subscriptions$Delete setUserIp(String str) {
        return (YouTube$Subscriptions$Delete) super.setUserIp(str);
    }

    public String getId() {
        return this.id;
    }

    public YouTube$Subscriptions$Delete setId(String str) {
        this.id = str;
        return this;
    }

    public YouTube$Subscriptions$Delete set(String str, Object obj) {
        return (YouTube$Subscriptions$Delete) super.set(str, obj);
    }
}
