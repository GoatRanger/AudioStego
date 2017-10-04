package com.google.api.client.auth.openidconnect;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.webtoken.JsonWebSignature;
import com.google.api.client.json.webtoken.JsonWebSignature.Header;
import com.google.api.client.util.Beta;
import java.io.IOException;
import java.util.Collection;

@Beta
public class IdToken extends JsonWebSignature {
    public IdToken(Header header, Payload payload, byte[] bArr, byte[] bArr2) {
        super(header, payload, bArr, bArr2);
    }

    public Payload getPayload() {
        return (Payload) super.getPayload();
    }

    public final boolean verifyIssuer(String str) {
        return str.equals(getPayload().getIssuer());
    }

    public final boolean verifyAudience(Collection<String> collection) {
        return collection.containsAll(getPayload().getAudienceAsList());
    }

    public final boolean verifyTime(long j, long j2) {
        return verifyExpirationTime(j, j2) && verifyIssuedAtTime(j, j2);
    }

    public final boolean verifyExpirationTime(long j, long j2) {
        return j <= (getPayload().getExpirationTimeSeconds().longValue() + j2) * 1000;
    }

    public final boolean verifyIssuedAtTime(long j, long j2) {
        return j >= (getPayload().getIssuedAtTimeSeconds().longValue() - j2) * 1000;
    }

    public static IdToken parse(JsonFactory jsonFactory, String str) throws IOException {
        JsonWebSignature parse = JsonWebSignature.parser(jsonFactory).setPayloadClass(Payload.class).parse(str);
        return new IdToken(parse.getHeader(), (Payload) parse.getPayload(), parse.getSignatureBytes(), parse.getSignedContentBytes());
    }
}
