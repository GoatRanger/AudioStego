package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import java.util.Collection;

public class GoogleAuthorizationCodeFlow extends AuthorizationCodeFlow {
    private final String accessType;
    private final String approvalPrompt;

    public GoogleAuthorizationCodeFlow(HttpTransport httpTransport, JsonFactory jsonFactory, String str, String str2, Collection<String> collection) {
        this(new Builder(httpTransport, jsonFactory, str, str2, collection));
    }

    protected GoogleAuthorizationCodeFlow(Builder builder) {
        super(builder);
        this.accessType = builder.accessType;
        this.approvalPrompt = builder.approvalPrompt;
    }

    public GoogleAuthorizationCodeTokenRequest newTokenRequest(String str) {
        return new GoogleAuthorizationCodeTokenRequest(getTransport(), getJsonFactory(), getTokenServerEncodedUrl(), "", "", str, "").setClientAuthentication(getClientAuthentication()).setRequestInitializer(getRequestInitializer()).setScopes(getScopes());
    }

    public GoogleAuthorizationCodeRequestUrl newAuthorizationUrl() {
        return new GoogleAuthorizationCodeRequestUrl(getAuthorizationServerEncodedUrl(), getClientId(), "", getScopes()).setAccessType(this.accessType).setApprovalPrompt(this.approvalPrompt);
    }

    public final String getApprovalPrompt() {
        return this.approvalPrompt;
    }

    public final String getAccessType() {
        return this.accessType;
    }
}
