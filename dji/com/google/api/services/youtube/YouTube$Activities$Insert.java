package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.Activities;
import com.google.api.services.youtube.model.Activity;

public class YouTube$Activities$Insert extends YouTubeRequest<Activity> {
    private static final String REST_PATH = "activities";
    @Key
    private String part;
    final /* synthetic */ Activities this$1;

    protected YouTube$Activities$Insert(Activities activities, String str, Activity activity) {
        this.this$1 = activities;
        super(activities.this$0, HttpMethods.POST, REST_PATH, activity, Activity.class);
        this.part = (String) Preconditions.checkNotNull(str, "Required parameter part must be specified.");
    }

    public YouTube$Activities$Insert setAlt(String str) {
        return (YouTube$Activities$Insert) super.setAlt(str);
    }

    public YouTube$Activities$Insert setFields(String str) {
        return (YouTube$Activities$Insert) super.setFields(str);
    }

    public YouTube$Activities$Insert setKey(String str) {
        return (YouTube$Activities$Insert) super.setKey(str);
    }

    public YouTube$Activities$Insert setOauthToken(String str) {
        return (YouTube$Activities$Insert) super.setOauthToken(str);
    }

    public YouTube$Activities$Insert setPrettyPrint(Boolean bool) {
        return (YouTube$Activities$Insert) super.setPrettyPrint(bool);
    }

    public YouTube$Activities$Insert setQuotaUser(String str) {
        return (YouTube$Activities$Insert) super.setQuotaUser(str);
    }

    public YouTube$Activities$Insert setUserIp(String str) {
        return (YouTube$Activities$Insert) super.setUserIp(str);
    }

    public String getPart() {
        return this.part;
    }

    public YouTube$Activities$Insert setPart(String str) {
        this.part = str;
        return this;
    }

    public YouTube$Activities$Insert set(String str, Object obj) {
        return (YouTube$Activities$Insert) super.set(str, obj);
    }
}
