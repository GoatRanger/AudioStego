package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.I18nRegions;
import com.google.api.services.youtube.model.I18nRegionListResponse;
import java.io.IOException;

public class YouTube$I18nRegions$List extends YouTubeRequest<I18nRegionListResponse> {
    private static final String REST_PATH = "i18nRegions";
    @Key
    private String hl;
    @Key
    private String part;
    final /* synthetic */ I18nRegions this$1;

    protected YouTube$I18nRegions$List(I18nRegions i18nRegions, String str) {
        this.this$1 = i18nRegions;
        super(i18nRegions.this$0, HttpMethods.GET, REST_PATH, null, I18nRegionListResponse.class);
        this.part = (String) Preconditions.checkNotNull(str, "Required parameter part must be specified.");
    }

    public HttpResponse executeUsingHead() throws IOException {
        return super.executeUsingHead();
    }

    public HttpRequest buildHttpRequestUsingHead() throws IOException {
        return super.buildHttpRequestUsingHead();
    }

    public YouTube$I18nRegions$List setAlt(String str) {
        return (YouTube$I18nRegions$List) super.setAlt(str);
    }

    public YouTube$I18nRegions$List setFields(String str) {
        return (YouTube$I18nRegions$List) super.setFields(str);
    }

    public YouTube$I18nRegions$List setKey(String str) {
        return (YouTube$I18nRegions$List) super.setKey(str);
    }

    public YouTube$I18nRegions$List setOauthToken(String str) {
        return (YouTube$I18nRegions$List) super.setOauthToken(str);
    }

    public YouTube$I18nRegions$List setPrettyPrint(Boolean bool) {
        return (YouTube$I18nRegions$List) super.setPrettyPrint(bool);
    }

    public YouTube$I18nRegions$List setQuotaUser(String str) {
        return (YouTube$I18nRegions$List) super.setQuotaUser(str);
    }

    public YouTube$I18nRegions$List setUserIp(String str) {
        return (YouTube$I18nRegions$List) super.setUserIp(str);
    }

    public String getPart() {
        return this.part;
    }

    public YouTube$I18nRegions$List setPart(String str) {
        this.part = str;
        return this;
    }

    public String getHl() {
        return this.hl;
    }

    public YouTube$I18nRegions$List setHl(String str) {
        this.hl = str;
        return this;
    }

    public YouTube$I18nRegions$List set(String str, Object obj) {
        return (YouTube$I18nRegions$List) super.set(str, obj);
    }
}
