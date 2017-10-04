package com.google.api.client.auth.oauth2;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;

public class AuthorizationCodeResponseUrl extends GenericUrl {
    @Key
    private String code;
    @Key
    private String error;
    @Key("error_description")
    private String errorDescription;
    @Key("error_uri")
    private String errorUri;
    @Key
    private String state;

    public AuthorizationCodeResponseUrl(String str) {
        boolean z;
        boolean z2 = true;
        super(str);
        if (this.code == null) {
            z = true;
        } else {
            z = false;
        }
        if (z == (this.error == null)) {
            z2 = false;
        }
        Preconditions.checkArgument(z2);
    }

    public final String getCode() {
        return this.code;
    }

    public AuthorizationCodeResponseUrl setCode(String str) {
        this.code = str;
        return this;
    }

    public final String getState() {
        return this.state;
    }

    public AuthorizationCodeResponseUrl setState(String str) {
        this.state = str;
        return this;
    }

    public final String getError() {
        return this.error;
    }

    public AuthorizationCodeResponseUrl setError(String str) {
        this.error = str;
        return this;
    }

    public final String getErrorDescription() {
        return this.errorDescription;
    }

    public AuthorizationCodeResponseUrl setErrorDescription(String str) {
        this.errorDescription = str;
        return this;
    }

    public final String getErrorUri() {
        return this.errorUri;
    }

    public AuthorizationCodeResponseUrl setErrorUri(String str) {
        this.errorUri = str;
        return this;
    }

    public AuthorizationCodeResponseUrl set(String str, Object obj) {
        return (AuthorizationCodeResponseUrl) super.set(str, obj);
    }

    public AuthorizationCodeResponseUrl clone() {
        return (AuthorizationCodeResponseUrl) super.clone();
    }
}
