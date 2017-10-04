package com.google.api.client.extensions.java6.auth.oauth2;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.util.Preconditions;
import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthorizationCodeInstalledApp {
    private static final Logger LOGGER = Logger.getLogger(AuthorizationCodeInstalledApp.class.getName());
    private final AuthorizationCodeFlow flow;
    private final VerificationCodeReceiver receiver;

    public AuthorizationCodeInstalledApp(AuthorizationCodeFlow authorizationCodeFlow, VerificationCodeReceiver verificationCodeReceiver) {
        this.flow = (AuthorizationCodeFlow) Preconditions.checkNotNull(authorizationCodeFlow);
        this.receiver = (VerificationCodeReceiver) Preconditions.checkNotNull(verificationCodeReceiver);
    }

    public Credential authorize(String str) throws IOException {
        try {
            Credential loadCredential = this.flow.loadCredential(str);
            if (loadCredential == null || (loadCredential.getRefreshToken() == null && loadCredential.getExpiresInSeconds().longValue() <= 60)) {
                String redirectUri = this.receiver.getRedirectUri();
                onAuthorization(this.flow.newAuthorizationUrl().setRedirectUri(redirectUri));
                loadCredential = this.flow.createAndStoreCredential(this.flow.newTokenRequest(this.receiver.waitForCode()).setRedirectUri(redirectUri).execute(), str);
                this.receiver.stop();
            } else {
                this.receiver.stop();
            }
            return loadCredential;
        } catch (Throwable th) {
            this.receiver.stop();
        }
    }

    protected void onAuthorization(AuthorizationCodeRequestUrl authorizationCodeRequestUrl) throws IOException {
        browse(authorizationCodeRequestUrl.build());
    }

    public static void browse(String str) {
        Preconditions.checkNotNull(str);
        System.out.println("Please open the following address in your browser:");
        System.out.println("  " + str);
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (desktop.isSupported(Action.BROWSE)) {
                    System.out.println("Attempting to open that address in the default browser now...");
                    desktop.browse(URI.create(str));
                }
            }
        } catch (Throwable e) {
            LOGGER.log(Level.WARNING, "Unable to open browser", e);
        } catch (Throwable e2) {
            LOGGER.log(Level.WARNING, "Unable to open browser", e2);
        }
    }

    public final AuthorizationCodeFlow getFlow() {
        return this.flow;
    }

    public final VerificationCodeReceiver getReceiver() {
        return this.receiver;
    }
}
