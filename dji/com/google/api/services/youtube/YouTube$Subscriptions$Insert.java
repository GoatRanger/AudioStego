package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.Subscriptions;
import com.google.api.services.youtube.model.Subscription;

public class YouTube$Subscriptions$Insert extends YouTubeRequest<Subscription> {
    private static final String REST_PATH = "subscriptions";
    @Key
    private String part;
    final /* synthetic */ Subscriptions this$1;

    protected YouTube$Subscriptions$Insert(Subscriptions subscriptions, String str, Subscription subscription) {
        this.this$1 = subscriptions;
        super(subscriptions.this$0, HttpMethods.POST, REST_PATH, subscription, Subscription.class);
        this.part = (String) Preconditions.checkNotNull(str, "Required parameter part must be specified.");
    }

    public YouTube$Subscriptions$Insert setAlt(String str) {
        return (YouTube$Subscriptions$Insert) super.setAlt(str);
    }

    public YouTube$Subscriptions$Insert setFields(String str) {
        return (YouTube$Subscriptions$Insert) super.setFields(str);
    }

    public YouTube$Subscriptions$Insert setKey(String str) {
        return (YouTube$Subscriptions$Insert) super.setKey(str);
    }

    public YouTube$Subscriptions$Insert setOauthToken(String str) {
        return (YouTube$Subscriptions$Insert) super.setOauthToken(str);
    }

    public YouTube$Subscriptions$Insert setPrettyPrint(Boolean bool) {
        return (YouTube$Subscriptions$Insert) super.setPrettyPrint(bool);
    }

    public YouTube$Subscriptions$Insert setQuotaUser(String str) {
        return (YouTube$Subscriptions$Insert) super.setQuotaUser(str);
    }

    public YouTube$Subscriptions$Insert setUserIp(String str) {
        return (YouTube$Subscriptions$Insert) super.setUserIp(str);
    }

    public String getPart() {
        return this.part;
    }

    public YouTube$Subscriptions$Insert setPart(String str) {
        this.part = str;
        return this;
    }

    public YouTube$Subscriptions$Insert set(String str, Object obj) {
        return (YouTube$Subscriptions$Insert) super.set(str, obj);
    }
}
