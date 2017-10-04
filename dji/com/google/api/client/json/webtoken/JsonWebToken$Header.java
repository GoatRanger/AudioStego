package com.google.api.client.json.webtoken;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Key;

@Beta
public class JsonWebToken$Header extends GenericJson {
    @Key("cty")
    private String contentType;
    @Key("typ")
    private String type;

    public final String getType() {
        return this.type;
    }

    public JsonWebToken$Header setType(String str) {
        this.type = str;
        return this;
    }

    public final String getContentType() {
        return this.contentType;
    }

    public JsonWebToken$Header setContentType(String str) {
        this.contentType = str;
        return this;
    }

    public JsonWebToken$Header set(String str, Object obj) {
        return (JsonWebToken$Header) super.set(str, obj);
    }

    public JsonWebToken$Header clone() {
        return (JsonWebToken$Header) super.clone();
    }
}
