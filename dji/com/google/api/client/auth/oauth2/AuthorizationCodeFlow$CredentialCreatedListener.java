package com.google.api.client.auth.oauth2;

import java.io.IOException;

public interface AuthorizationCodeFlow$CredentialCreatedListener {
    void onCredentialCreated(Credential credential, TokenResponse tokenResponse) throws IOException;
}
