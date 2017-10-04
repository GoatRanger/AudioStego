package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.CredentialRefreshListener;
import com.google.api.client.auth.oauth2.TokenRequest;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets.Details;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.webtoken.JsonWebSignature;
import com.google.api.client.json.webtoken.JsonWebSignature.Header;
import com.google.api.client.json.webtoken.JsonWebToken.Payload;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Clock;
import com.google.api.client.util.Joiner;
import com.google.api.client.util.PemReader;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.SecurityUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Collection;
import java.util.Collections;

public class GoogleCredential extends Credential {
    private String serviceAccountId;
    private PrivateKey serviceAccountPrivateKey;
    private Collection<String> serviceAccountScopes;
    private String serviceAccountUser;

    public static class Builder extends com.google.api.client.auth.oauth2.Credential.Builder {
        String serviceAccountId;
        PrivateKey serviceAccountPrivateKey;
        Collection<String> serviceAccountScopes;
        String serviceAccountUser;

        public Builder() {
            super(BearerToken.authorizationHeaderAccessMethod());
            setTokenServerEncodedUrl(GoogleOAuthConstants.TOKEN_SERVER_URL);
        }

        public GoogleCredential build() {
            return new GoogleCredential(this);
        }

        public Builder setTransport(HttpTransport httpTransport) {
            return (Builder) super.setTransport(httpTransport);
        }

        public Builder setJsonFactory(JsonFactory jsonFactory) {
            return (Builder) super.setJsonFactory(jsonFactory);
        }

        public Builder setClock(Clock clock) {
            return (Builder) super.setClock(clock);
        }

        public Builder setClientSecrets(String str, String str2) {
            setClientAuthentication(new ClientParametersAuthentication(str, str2));
            return this;
        }

        public Builder setClientSecrets(GoogleClientSecrets googleClientSecrets) {
            Details details = googleClientSecrets.getDetails();
            setClientAuthentication(new ClientParametersAuthentication(details.getClientId(), details.getClientSecret()));
            return this;
        }

        @Beta
        public final String getServiceAccountId() {
            return this.serviceAccountId;
        }

        @Beta
        public Builder setServiceAccountId(String str) {
            this.serviceAccountId = str;
            return this;
        }

        @Beta
        public final Collection<String> getServiceAccountScopes() {
            return this.serviceAccountScopes;
        }

        @Beta
        public Builder setServiceAccountScopes(Collection<String> collection) {
            this.serviceAccountScopes = collection;
            return this;
        }

        @Beta
        public final PrivateKey getServiceAccountPrivateKey() {
            return this.serviceAccountPrivateKey;
        }

        @Beta
        public Builder setServiceAccountPrivateKey(PrivateKey privateKey) {
            this.serviceAccountPrivateKey = privateKey;
            return this;
        }

        @Beta
        public Builder setServiceAccountPrivateKeyFromP12File(File file) throws GeneralSecurityException, IOException {
            this.serviceAccountPrivateKey = SecurityUtils.loadPrivateKeyFromKeyStore(SecurityUtils.getPkcs12KeyStore(), new FileInputStream(file), "notasecret", "privatekey", "notasecret");
            return this;
        }

        @Beta
        public Builder setServiceAccountPrivateKeyFromPemFile(File file) throws GeneralSecurityException, IOException {
            this.serviceAccountPrivateKey = SecurityUtils.getRsaKeyFactory().generatePrivate(new PKCS8EncodedKeySpec(PemReader.readFirstSectionAndClose(new FileReader(file), "PRIVATE KEY").getBase64DecodedBytes()));
            return this;
        }

        @Beta
        public final String getServiceAccountUser() {
            return this.serviceAccountUser;
        }

        @Beta
        public Builder setServiceAccountUser(String str) {
            this.serviceAccountUser = str;
            return this;
        }

        public Builder setRequestInitializer(HttpRequestInitializer httpRequestInitializer) {
            return (Builder) super.setRequestInitializer(httpRequestInitializer);
        }

        public Builder addRefreshListener(CredentialRefreshListener credentialRefreshListener) {
            return (Builder) super.addRefreshListener(credentialRefreshListener);
        }

        public Builder setRefreshListeners(Collection<CredentialRefreshListener> collection) {
            return (Builder) super.setRefreshListeners(collection);
        }

        public Builder setTokenServerUrl(GenericUrl genericUrl) {
            return (Builder) super.setTokenServerUrl(genericUrl);
        }

        public Builder setTokenServerEncodedUrl(String str) {
            return (Builder) super.setTokenServerEncodedUrl(str);
        }

        public Builder setClientAuthentication(HttpExecuteInterceptor httpExecuteInterceptor) {
            return (Builder) super.setClientAuthentication(httpExecuteInterceptor);
        }
    }

    public GoogleCredential() {
        this(new Builder());
    }

    protected GoogleCredential(Builder builder) {
        super((com.google.api.client.auth.oauth2.Credential.Builder) builder);
        if (builder.serviceAccountPrivateKey == null) {
            boolean z = builder.serviceAccountId == null && builder.serviceAccountScopes == null && builder.serviceAccountUser == null;
            Preconditions.checkArgument(z);
            return;
        }
        this.serviceAccountId = (String) Preconditions.checkNotNull(builder.serviceAccountId);
        this.serviceAccountScopes = Collections.unmodifiableCollection(builder.serviceAccountScopes);
        this.serviceAccountPrivateKey = builder.serviceAccountPrivateKey;
        this.serviceAccountUser = builder.serviceAccountUser;
    }

    public GoogleCredential setAccessToken(String str) {
        return (GoogleCredential) super.setAccessToken(str);
    }

    public GoogleCredential setRefreshToken(String str) {
        if (str != null) {
            boolean z = (getJsonFactory() == null || getTransport() == null || getClientAuthentication() == null) ? false : true;
            Preconditions.checkArgument(z, "Please use the Builder and call setJsonFactory, setTransport and setClientSecrets");
        }
        return (GoogleCredential) super.setRefreshToken(str);
    }

    public GoogleCredential setExpirationTimeMilliseconds(Long l) {
        return (GoogleCredential) super.setExpirationTimeMilliseconds(l);
    }

    public GoogleCredential setExpiresInSeconds(Long l) {
        return (GoogleCredential) super.setExpiresInSeconds(l);
    }

    public GoogleCredential setFromTokenResponse(TokenResponse tokenResponse) {
        return (GoogleCredential) super.setFromTokenResponse(tokenResponse);
    }

    @Beta
    protected TokenResponse executeRefreshToken() throws IOException {
        if (this.serviceAccountPrivateKey == null) {
            return super.executeRefreshToken();
        }
        Header header = new Header();
        header.setAlgorithm("RS256");
        header.setType("JWT");
        Payload payload = new Payload();
        long currentTimeMillis = getClock().currentTimeMillis();
        payload.setIssuer(this.serviceAccountId);
        payload.setAudience(getTokenServerEncodedUrl());
        payload.setIssuedAtTimeSeconds(Long.valueOf(currentTimeMillis / 1000));
        payload.setExpirationTimeSeconds(Long.valueOf((currentTimeMillis / 1000) + 3600));
        payload.setSubject(this.serviceAccountUser);
        payload.put("scope", Joiner.on(' ').join(this.serviceAccountScopes));
        try {
            String signUsingRsaSha256 = JsonWebSignature.signUsingRsaSha256(this.serviceAccountPrivateKey, getJsonFactory(), header, payload);
            TokenRequest tokenRequest = new TokenRequest(getTransport(), getJsonFactory(), new GenericUrl(getTokenServerEncodedUrl()), "urn:ietf:params:oauth:grant-type:jwt-bearer");
            tokenRequest.put("assertion", signUsingRsaSha256);
            return tokenRequest.execute();
        } catch (Throwable e) {
            IOException iOException = new IOException();
            iOException.initCause(e);
            throw iOException;
        }
    }

    @Beta
    public final String getServiceAccountId() {
        return this.serviceAccountId;
    }

    @Beta
    public final Collection<String> getServiceAccountScopes() {
        return this.serviceAccountScopes;
    }

    @Beta
    public final String getServiceAccountScopesAsString() {
        return this.serviceAccountScopes == null ? null : Joiner.on(' ').join(this.serviceAccountScopes);
    }

    @Beta
    public final PrivateKey getServiceAccountPrivateKey() {
        return this.serviceAccountPrivateKey;
    }

    @Beta
    public final String getServiceAccountUser() {
        return this.serviceAccountUser;
    }
}
