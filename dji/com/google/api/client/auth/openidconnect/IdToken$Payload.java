package com.google.api.client.auth.openidconnect;

import com.google.api.client.json.webtoken.JsonWebToken$Payload;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Key;
import java.util.List;

@Beta
public class IdToken$Payload extends JsonWebToken$Payload {
    @Key("at_hash")
    private String accessTokenHash;
    @Key("auth_time")
    private Long authorizationTimeSeconds;
    @Key("azp")
    private String authorizedParty;
    @Key("acr")
    private String classReference;
    @Key("amr")
    private List<String> methodsReferences;
    @Key
    private String nonce;

    public final Long getAuthorizationTimeSeconds() {
        return this.authorizationTimeSeconds;
    }

    public IdToken$Payload setAuthorizationTimeSeconds(Long l) {
        this.authorizationTimeSeconds = l;
        return this;
    }

    public final String getAuthorizedParty() {
        return this.authorizedParty;
    }

    public IdToken$Payload setAuthorizedParty(String str) {
        this.authorizedParty = str;
        return this;
    }

    public final String getNonce() {
        return this.nonce;
    }

    public IdToken$Payload setNonce(String str) {
        this.nonce = str;
        return this;
    }

    public final String getAccessTokenHash() {
        return this.accessTokenHash;
    }

    public IdToken$Payload setAccessTokenHash(String str) {
        this.accessTokenHash = str;
        return this;
    }

    public final String getClassReference() {
        return this.classReference;
    }

    public IdToken$Payload setClassReference(String str) {
        this.classReference = str;
        return this;
    }

    public final List<String> getMethodsReferences() {
        return this.methodsReferences;
    }

    public IdToken$Payload setMethodsReferences(List<String> list) {
        this.methodsReferences = list;
        return this;
    }

    public IdToken$Payload setExpirationTimeSeconds(Long l) {
        return (IdToken$Payload) super.setExpirationTimeSeconds(l);
    }

    public IdToken$Payload setNotBeforeTimeSeconds(Long l) {
        return (IdToken$Payload) super.setNotBeforeTimeSeconds(l);
    }

    public IdToken$Payload setIssuedAtTimeSeconds(Long l) {
        return (IdToken$Payload) super.setIssuedAtTimeSeconds(l);
    }

    public IdToken$Payload setIssuer(String str) {
        return (IdToken$Payload) super.setIssuer(str);
    }

    public IdToken$Payload setAudience(Object obj) {
        return (IdToken$Payload) super.setAudience(obj);
    }

    public IdToken$Payload setJwtId(String str) {
        return (IdToken$Payload) super.setJwtId(str);
    }

    public IdToken$Payload setType(String str) {
        return (IdToken$Payload) super.setType(str);
    }

    public IdToken$Payload setSubject(String str) {
        return (IdToken$Payload) super.setSubject(str);
    }

    public IdToken$Payload set(String str, Object obj) {
        return (IdToken$Payload) super.set(str, obj);
    }

    public IdToken$Payload clone() {
        return (IdToken$Payload) super.clone();
    }
}
