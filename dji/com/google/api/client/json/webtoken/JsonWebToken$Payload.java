package com.google.api.client.json.webtoken;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Key;
import java.util.Collections;
import java.util.List;

@Beta
public class JsonWebToken$Payload extends GenericJson {
    @Key("aud")
    private Object audience;
    @Key("exp")
    private Long expirationTimeSeconds;
    @Key("iat")
    private Long issuedAtTimeSeconds;
    @Key("iss")
    private String issuer;
    @Key("jti")
    private String jwtId;
    @Key("nbf")
    private Long notBeforeTimeSeconds;
    @Key("sub")
    private String subject;
    @Key("typ")
    private String type;

    public final Long getExpirationTimeSeconds() {
        return this.expirationTimeSeconds;
    }

    public JsonWebToken$Payload setExpirationTimeSeconds(Long l) {
        this.expirationTimeSeconds = l;
        return this;
    }

    public final Long getNotBeforeTimeSeconds() {
        return this.notBeforeTimeSeconds;
    }

    public JsonWebToken$Payload setNotBeforeTimeSeconds(Long l) {
        this.notBeforeTimeSeconds = l;
        return this;
    }

    public final Long getIssuedAtTimeSeconds() {
        return this.issuedAtTimeSeconds;
    }

    public JsonWebToken$Payload setIssuedAtTimeSeconds(Long l) {
        this.issuedAtTimeSeconds = l;
        return this;
    }

    public final String getIssuer() {
        return this.issuer;
    }

    public JsonWebToken$Payload setIssuer(String str) {
        this.issuer = str;
        return this;
    }

    public final Object getAudience() {
        return this.audience;
    }

    public final List<String> getAudienceAsList() {
        if (this.audience == null) {
            return Collections.emptyList();
        }
        if (this.audience instanceof String) {
            return Collections.singletonList((String) this.audience);
        }
        return (List) this.audience;
    }

    public JsonWebToken$Payload setAudience(Object obj) {
        this.audience = obj;
        return this;
    }

    public final String getJwtId() {
        return this.jwtId;
    }

    public JsonWebToken$Payload setJwtId(String str) {
        this.jwtId = str;
        return this;
    }

    public final String getType() {
        return this.type;
    }

    public JsonWebToken$Payload setType(String str) {
        this.type = str;
        return this;
    }

    public final String getSubject() {
        return this.subject;
    }

    public JsonWebToken$Payload setSubject(String str) {
        this.subject = str;
        return this;
    }

    public JsonWebToken$Payload set(String str, Object obj) {
        return (JsonWebToken$Payload) super.set(str, obj);
    }

    public JsonWebToken$Payload clone() {
        return (JsonWebToken$Payload) super.clone();
    }
}
