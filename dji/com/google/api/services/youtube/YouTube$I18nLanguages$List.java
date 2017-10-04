package com.google.api.services.youtube;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.youtube.YouTube.I18nLanguages;
import com.google.api.services.youtube.model.I18nLanguageListResponse;
import java.io.IOException;

public class YouTube$I18nLanguages$List extends YouTubeRequest<I18nLanguageListResponse> {
    private static final String REST_PATH = "i18nLanguages";
    @Key
    private String hl;
    @Key
    private String part;
    final /* synthetic */ I18nLanguages this$1;

    protected YouTube$I18nLanguages$List(I18nLanguages i18nLanguages, String str) {
        this.this$1 = i18nLanguages;
        super(i18nLanguages.this$0, HttpMethods.GET, REST_PATH, null, I18nLanguageListResponse.class);
        this.part = (String) Preconditions.checkNotNull(str, "Required parameter part must be specified.");
    }

    public HttpResponse executeUsingHead() throws IOException {
        return super.executeUsingHead();
    }

    public HttpRequest buildHttpRequestUsingHead() throws IOException {
        return super.buildHttpRequestUsingHead();
    }

    public YouTube$I18nLanguages$List setAlt(String str) {
        return (YouTube$I18nLanguages$List) super.setAlt(str);
    }

    public YouTube$I18nLanguages$List setFields(String str) {
        return (YouTube$I18nLanguages$List) super.setFields(str);
    }

    public YouTube$I18nLanguages$List setKey(String str) {
        return (YouTube$I18nLanguages$List) super.setKey(str);
    }

    public YouTube$I18nLanguages$List setOauthToken(String str) {
        return (YouTube$I18nLanguages$List) super.setOauthToken(str);
    }

    public YouTube$I18nLanguages$List setPrettyPrint(Boolean bool) {
        return (YouTube$I18nLanguages$List) super.setPrettyPrint(bool);
    }

    public YouTube$I18nLanguages$List setQuotaUser(String str) {
        return (YouTube$I18nLanguages$List) super.setQuotaUser(str);
    }

    public YouTube$I18nLanguages$List setUserIp(String str) {
        return (YouTube$I18nLanguages$List) super.setUserIp(str);
    }

    public String getPart() {
        return this.part;
    }

    public YouTube$I18nLanguages$List setPart(String str) {
        this.part = str;
        return this;
    }

    public String getHl() {
        return this.hl;
    }

    public YouTube$I18nLanguages$List setHl(String str) {
        this.hl = str;
        return this;
    }

    public YouTube$I18nLanguages$List set(String str, Object obj) {
        return (YouTube$I18nLanguages$List) super.set(str, obj);
    }
}
