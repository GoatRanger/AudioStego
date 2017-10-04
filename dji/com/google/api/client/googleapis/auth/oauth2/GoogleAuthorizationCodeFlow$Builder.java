package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow.Builder;
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow.CredentialCreatedListener;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.Credential.AccessMethod;
import com.google.api.client.auth.oauth2.CredentialRefreshListener;
import com.google.api.client.auth.oauth2.CredentialStore;
import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Clock;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.DataStoreFactory;
import java.io.IOException;
import java.util.Collection;

public class GoogleAuthorizationCodeFlow$Builder extends Builder {
    String accessType;
    String approvalPrompt;

    public GoogleAuthorizationCodeFlow$Builder(HttpTransport httpTransport, JsonFactory jsonFactory, String str, String str2, Collection<String> collection) {
        super(BearerToken.authorizationHeaderAccessMethod(), httpTransport, jsonFactory, new GenericUrl(GoogleOAuthConstants.TOKEN_SERVER_URL), new ClientParametersAuthentication(str, str2), str, GoogleOAuthConstants.AUTHORIZATION_SERVER_URL);
        setScopes((Collection) collection);
    }

    public GoogleAuthorizationCodeFlow$Builder(HttpTransport httpTransport, JsonFactory jsonFactory, GoogleClientSecrets googleClientSecrets, Collection<String> collection) {
        super(BearerToken.authorizationHeaderAccessMethod(), httpTransport, jsonFactory, new GenericUrl(GoogleOAuthConstants.TOKEN_SERVER_URL), new ClientParametersAuthentication(googleClientSecrets.getDetails().getClientId(), googleClientSecrets.getDetails().getClientSecret()), googleClientSecrets.getDetails().getClientId(), GoogleOAuthConstants.AUTHORIZATION_SERVER_URL);
        setScopes((Collection) collection);
    }

    public GoogleAuthorizationCodeFlow build() {
        return new GoogleAuthorizationCodeFlow(this);
    }

    public GoogleAuthorizationCodeFlow$Builder setDataStoreFactory(DataStoreFactory dataStoreFactory) throws IOException {
        return (GoogleAuthorizationCodeFlow$Builder) super.setDataStoreFactory(dataStoreFactory);
    }

    public GoogleAuthorizationCodeFlow$Builder setCredentialDataStore(DataStore<StoredCredential> dataStore) {
        return (GoogleAuthorizationCodeFlow$Builder) super.setCredentialDataStore(dataStore);
    }

    public GoogleAuthorizationCodeFlow$Builder setCredentialCreatedListener(CredentialCreatedListener credentialCreatedListener) {
        return (GoogleAuthorizationCodeFlow$Builder) super.setCredentialCreatedListener(credentialCreatedListener);
    }

    @Beta
    @Deprecated
    public GoogleAuthorizationCodeFlow$Builder setCredentialStore(CredentialStore credentialStore) {
        return (GoogleAuthorizationCodeFlow$Builder) super.setCredentialStore(credentialStore);
    }

    public GoogleAuthorizationCodeFlow$Builder setRequestInitializer(HttpRequestInitializer httpRequestInitializer) {
        return (GoogleAuthorizationCodeFlow$Builder) super.setRequestInitializer(httpRequestInitializer);
    }

    public GoogleAuthorizationCodeFlow$Builder setScopes(Collection<String> collection) {
        Preconditions.checkState(!collection.isEmpty());
        return (GoogleAuthorizationCodeFlow$Builder) super.setScopes(collection);
    }

    public GoogleAuthorizationCodeFlow$Builder setMethod(AccessMethod accessMethod) {
        return (GoogleAuthorizationCodeFlow$Builder) super.setMethod(accessMethod);
    }

    public GoogleAuthorizationCodeFlow$Builder setTransport(HttpTransport httpTransport) {
        return (GoogleAuthorizationCodeFlow$Builder) super.setTransport(httpTransport);
    }

    public GoogleAuthorizationCodeFlow$Builder setJsonFactory(JsonFactory jsonFactory) {
        return (GoogleAuthorizationCodeFlow$Builder) super.setJsonFactory(jsonFactory);
    }

    public GoogleAuthorizationCodeFlow$Builder setTokenServerUrl(GenericUrl genericUrl) {
        return (GoogleAuthorizationCodeFlow$Builder) super.setTokenServerUrl(genericUrl);
    }

    public GoogleAuthorizationCodeFlow$Builder setClientAuthentication(HttpExecuteInterceptor httpExecuteInterceptor) {
        return (GoogleAuthorizationCodeFlow$Builder) super.setClientAuthentication(httpExecuteInterceptor);
    }

    public GoogleAuthorizationCodeFlow$Builder setClientId(String str) {
        return (GoogleAuthorizationCodeFlow$Builder) super.setClientId(str);
    }

    public GoogleAuthorizationCodeFlow$Builder setAuthorizationServerEncodedUrl(String str) {
        return (GoogleAuthorizationCodeFlow$Builder) super.setAuthorizationServerEncodedUrl(str);
    }

    public GoogleAuthorizationCodeFlow$Builder setClock(Clock clock) {
        return (GoogleAuthorizationCodeFlow$Builder) super.setClock(clock);
    }

    public GoogleAuthorizationCodeFlow$Builder addRefreshListener(CredentialRefreshListener credentialRefreshListener) {
        return (GoogleAuthorizationCodeFlow$Builder) super.addRefreshListener(credentialRefreshListener);
    }

    public GoogleAuthorizationCodeFlow$Builder setRefreshListeners(Collection<CredentialRefreshListener> collection) {
        return (GoogleAuthorizationCodeFlow$Builder) super.setRefreshListeners(collection);
    }

    public GoogleAuthorizationCodeFlow$Builder setApprovalPrompt(String str) {
        this.approvalPrompt = str;
        return this;
    }

    public final String getApprovalPrompt() {
        return this.approvalPrompt;
    }

    public GoogleAuthorizationCodeFlow$Builder setAccessType(String str) {
        this.accessType = str;
        return this;
    }

    public final String getAccessType() {
        return this.accessType;
    }
}
