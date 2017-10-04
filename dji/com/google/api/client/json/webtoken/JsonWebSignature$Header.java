package com.google.api.client.json.webtoken;

import com.google.api.client.util.Beta;
import com.google.api.client.util.Key;
import java.util.List;

@Beta
public class JsonWebSignature$Header extends JsonWebToken$Header {
    @Key("alg")
    private String algorithm;
    @Key("crit")
    private List<String> critical;
    @Key("jwk")
    private String jwk;
    @Key("jku")
    private String jwkUrl;
    @Key("kid")
    private String keyId;
    @Key("x5c")
    private String x509Certificate;
    @Key("x5t")
    private String x509Thumbprint;
    @Key("x5u")
    private String x509Url;

    public JsonWebSignature$Header setType(String str) {
        super.setType(str);
        return this;
    }

    public final String getAlgorithm() {
        return this.algorithm;
    }

    public JsonWebSignature$Header setAlgorithm(String str) {
        this.algorithm = str;
        return this;
    }

    public final String getJwkUrl() {
        return this.jwkUrl;
    }

    public JsonWebSignature$Header setJwkUrl(String str) {
        this.jwkUrl = str;
        return this;
    }

    public final String getJwk() {
        return this.jwk;
    }

    public JsonWebSignature$Header setJwk(String str) {
        this.jwk = str;
        return this;
    }

    public final String getKeyId() {
        return this.keyId;
    }

    public JsonWebSignature$Header setKeyId(String str) {
        this.keyId = str;
        return this;
    }

    public final String getX509Url() {
        return this.x509Url;
    }

    public JsonWebSignature$Header setX509Url(String str) {
        this.x509Url = str;
        return this;
    }

    public final String getX509Thumbprint() {
        return this.x509Thumbprint;
    }

    public JsonWebSignature$Header setX509Thumbprint(String str) {
        this.x509Thumbprint = str;
        return this;
    }

    public final String getX509Certificate() {
        return this.x509Certificate;
    }

    public JsonWebSignature$Header setX509Certificate(String str) {
        this.x509Certificate = str;
        return this;
    }

    public final List<String> getCritical() {
        return this.critical;
    }

    public JsonWebSignature$Header setCritical(List<String> list) {
        this.critical = list;
        return this;
    }

    public JsonWebSignature$Header set(String str, Object obj) {
        return (JsonWebSignature$Header) super.set(str, obj);
    }

    public JsonWebSignature$Header clone() {
        return (JsonWebSignature$Header) super.clone();
    }
}
