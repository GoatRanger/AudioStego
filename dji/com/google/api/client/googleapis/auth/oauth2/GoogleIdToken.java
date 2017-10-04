package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.auth.openidconnect.IdToken;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.webtoken.JsonWebSignature;
import com.google.api.client.json.webtoken.JsonWebSignature.Header;
import com.google.api.client.util.Beta;
import java.io.IOException;
import java.security.GeneralSecurityException;

@Beta
public class GoogleIdToken extends IdToken {
    public static GoogleIdToken parse(JsonFactory jsonFactory, String str) throws IOException {
        JsonWebSignature parse = JsonWebSignature.parser(jsonFactory).setPayloadClass(Payload.class).parse(str);
        return new GoogleIdToken(parse.getHeader(), (Payload) parse.getPayload(), parse.getSignatureBytes(), parse.getSignedContentBytes());
    }

    public GoogleIdToken(Header header, Payload payload, byte[] bArr, byte[] bArr2) {
        super(header, payload, bArr, bArr2);
    }

    public boolean verify(GoogleIdTokenVerifier googleIdTokenVerifier) throws GeneralSecurityException, IOException {
        return googleIdTokenVerifier.verify(this);
    }

    public Payload getPayload() {
        return (Payload) super.getPayload();
    }
}
