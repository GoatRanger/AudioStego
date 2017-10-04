package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.auth.openidconnect.IdToken$Payload;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Key;
import java.util.List;

@Beta
public class GoogleIdToken$Payload extends IdToken$Payload {
    @Key("email")
    private String email;
    @Key("email_verified")
    private Object emailVerified;
    @Key("hd")
    private String hostedDomain;

    @Deprecated
    public String getUserId() {
        return getSubject();
    }

    @Deprecated
    public GoogleIdToken$Payload setUserId(String str) {
        return setSubject(str);
    }

    @Deprecated
    public String getIssuee() {
        return getAuthorizedParty();
    }

    @Deprecated
    public GoogleIdToken$Payload setIssuee(String str) {
        return setAuthorizedParty(str);
    }

    public String getHostedDomain() {
        return this.hostedDomain;
    }

    public GoogleIdToken$Payload setHostedDomain(String str) {
        this.hostedDomain = str;
        return this;
    }

    public String getEmail() {
        return this.email;
    }

    public GoogleIdToken$Payload setEmail(String str) {
        this.email = str;
        return this;
    }

    public Boolean getEmailVerified() {
        if (this.emailVerified == null) {
            return null;
        }
        if (this.emailVerified instanceof Boolean) {
            return (Boolean) this.emailVerified;
        }
        return Boolean.valueOf((String) this.emailVerified);
    }

    public GoogleIdToken$Payload setEmailVerified(Boolean bool) {
        this.emailVerified = bool;
        return this;
    }

    public GoogleIdToken$Payload setAuthorizationTimeSeconds(Long l) {
        return (GoogleIdToken$Payload) super.setAuthorizationTimeSeconds(l);
    }

    public GoogleIdToken$Payload setAuthorizedParty(String str) {
        return (GoogleIdToken$Payload) super.setAuthorizedParty(str);
    }

    public GoogleIdToken$Payload setNonce(String str) {
        return (GoogleIdToken$Payload) super.setNonce(str);
    }

    public GoogleIdToken$Payload setAccessTokenHash(String str) {
        return (GoogleIdToken$Payload) super.setAccessTokenHash(str);
    }

    public GoogleIdToken$Payload setClassReference(String str) {
        return (GoogleIdToken$Payload) super.setClassReference(str);
    }

    public GoogleIdToken$Payload setMethodsReferences(List<String> list) {
        return (GoogleIdToken$Payload) super.setMethodsReferences(list);
    }

    public GoogleIdToken$Payload setExpirationTimeSeconds(Long l) {
        return (GoogleIdToken$Payload) super.setExpirationTimeSeconds(l);
    }

    public GoogleIdToken$Payload setNotBeforeTimeSeconds(Long l) {
        return (GoogleIdToken$Payload) super.setNotBeforeTimeSeconds(l);
    }

    public GoogleIdToken$Payload setIssuedAtTimeSeconds(Long l) {
        return (GoogleIdToken$Payload) super.setIssuedAtTimeSeconds(l);
    }

    public GoogleIdToken$Payload setIssuer(String str) {
        return (GoogleIdToken$Payload) super.setIssuer(str);
    }

    public GoogleIdToken$Payload setAudience(Object obj) {
        return (GoogleIdToken$Payload) super.setAudience(obj);
    }

    public GoogleIdToken$Payload setJwtId(String str) {
        return (GoogleIdToken$Payload) super.setJwtId(str);
    }

    public GoogleIdToken$Payload setType(String str) {
        return (GoogleIdToken$Payload) super.setType(str);
    }

    public GoogleIdToken$Payload setSubject(String str) {
        return (GoogleIdToken$Payload) super.setSubject(str);
    }

    public GoogleIdToken$Payload set(String str, Object obj) {
        return (GoogleIdToken$Payload) super.set(str, obj);
    }

    public GoogleIdToken$Payload clone() {
        return (GoogleIdToken$Payload) super.clone();
    }
}
