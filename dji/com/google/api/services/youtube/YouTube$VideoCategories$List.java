package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.VideoCategories;
import com.google.api.services.youtube.model.VideoCategoryListResponse;
import java.io.IOException;

public class YouTube$VideoCategories$List extends YouTubeRequest<VideoCategoryListResponse> {
    private static final String REST_PATH = "videoCategories";
    @Key
    private String hl;
    @Key
    private String id;
    @Key
    private String part;
    @Key
    private String regionCode;
    final /* synthetic */ VideoCategories this$1;

    protected YouTube$VideoCategories$List(VideoCategories videoCategories, String str) {
        this.this$1 = videoCategories;
        super(videoCategories.this$0, HttpMethods.GET, REST_PATH, null, VideoCategoryListResponse.class);
        this.part = (String) Preconditions.checkNotNull(str, "Required parameter part must be specified.");
    }

    public HttpResponse executeUsingHead() throws IOException {
        return super.executeUsingHead();
    }

    public HttpRequest buildHttpRequestUsingHead() throws IOException {
        return super.buildHttpRequestUsingHead();
    }

    public YouTube$VideoCategories$List setAlt(String str) {
        return (YouTube$VideoCategories$List) super.setAlt(str);
    }

    public YouTube$VideoCategories$List setFields(String str) {
        return (YouTube$VideoCategories$List) super.setFields(str);
    }

    public YouTube$VideoCategories$List setKey(String str) {
        return (YouTube$VideoCategories$List) super.setKey(str);
    }

    public YouTube$VideoCategories$List setOauthToken(String str) {
        return (YouTube$VideoCategories$List) super.setOauthToken(str);
    }

    public YouTube$VideoCategories$List setPrettyPrint(Boolean bool) {
        return (YouTube$VideoCategories$List) super.setPrettyPrint(bool);
    }

    public YouTube$VideoCategories$List setQuotaUser(String str) {
        return (YouTube$VideoCategories$List) super.setQuotaUser(str);
    }

    public YouTube$VideoCategories$List setUserIp(String str) {
        return (YouTube$VideoCategories$List) super.setUserIp(str);
    }

    public String getPart() {
        return this.part;
    }

    public YouTube$VideoCategories$List setPart(String str) {
        this.part = str;
        return this;
    }

    public String getRegionCode() {
        return this.regionCode;
    }

    public YouTube$VideoCategories$List setRegionCode(String str) {
        this.regionCode = str;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public YouTube$VideoCategories$List setId(String str) {
        this.id = str;
        return this;
    }

    public String getHl() {
        return this.hl;
    }

    public YouTube$VideoCategories$List setHl(String str) {
        this.hl = str;
        return this;
    }

    public YouTube$VideoCategories$List set(String str, Object obj) {
        return (YouTube$VideoCategories$List) super.set(str, obj);
    }
}
