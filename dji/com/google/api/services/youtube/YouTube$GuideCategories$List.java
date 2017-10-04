package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.GuideCategories;
import com.google.api.services.youtube.model.GuideCategoryListResponse;
import java.io.IOException;

public class YouTube$GuideCategories$List extends YouTubeRequest<GuideCategoryListResponse> {
    private static final String REST_PATH = "guideCategories";
    @Key
    private String hl;
    @Key
    private String id;
    @Key
    private String part;
    @Key
    private String regionCode;
    final /* synthetic */ GuideCategories this$1;

    protected YouTube$GuideCategories$List(GuideCategories guideCategories, String str) {
        this.this$1 = guideCategories;
        super(guideCategories.this$0, HttpMethods.GET, REST_PATH, null, GuideCategoryListResponse.class);
        this.part = (String) Preconditions.checkNotNull(str, "Required parameter part must be specified.");
    }

    public HttpResponse executeUsingHead() throws IOException {
        return super.executeUsingHead();
    }

    public HttpRequest buildHttpRequestUsingHead() throws IOException {
        return super.buildHttpRequestUsingHead();
    }

    public YouTube$GuideCategories$List setAlt(String str) {
        return (YouTube$GuideCategories$List) super.setAlt(str);
    }

    public YouTube$GuideCategories$List setFields(String str) {
        return (YouTube$GuideCategories$List) super.setFields(str);
    }

    public YouTube$GuideCategories$List setKey(String str) {
        return (YouTube$GuideCategories$List) super.setKey(str);
    }

    public YouTube$GuideCategories$List setOauthToken(String str) {
        return (YouTube$GuideCategories$List) super.setOauthToken(str);
    }

    public YouTube$GuideCategories$List setPrettyPrint(Boolean bool) {
        return (YouTube$GuideCategories$List) super.setPrettyPrint(bool);
    }

    public YouTube$GuideCategories$List setQuotaUser(String str) {
        return (YouTube$GuideCategories$List) super.setQuotaUser(str);
    }

    public YouTube$GuideCategories$List setUserIp(String str) {
        return (YouTube$GuideCategories$List) super.setUserIp(str);
    }

    public String getPart() {
        return this.part;
    }

    public YouTube$GuideCategories$List setPart(String str) {
        this.part = str;
        return this;
    }

    public String getRegionCode() {
        return this.regionCode;
    }

    public YouTube$GuideCategories$List setRegionCode(String str) {
        this.regionCode = str;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public YouTube$GuideCategories$List setId(String str) {
        this.id = str;
        return this;
    }

    public String getHl() {
        return this.hl;
    }

    public YouTube$GuideCategories$List setHl(String str) {
        this.hl = str;
        return this;
    }

    public YouTube$GuideCategories$List set(String str, Object obj) {
        return (YouTube$GuideCategories$List) super.set(str, obj);
    }
}
