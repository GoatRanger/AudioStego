package com.google.api.client.json.webtoken;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.webtoken.JsonWebToken.Payload;
import com.google.api.client.util.Base64;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.SecurityUtils;
import com.google.api.client.util.StringUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.PublicKey;

@Beta
public class JsonWebSignature extends JsonWebToken {
    private final byte[] signatureBytes;
    private final byte[] signedContentBytes;

    @Beta
    public static final class Parser {
        private Class<? extends Header> headerClass = Header.class;
        private final JsonFactory jsonFactory;
        private Class<? extends Payload> payloadClass = Payload.class;

        public Parser(JsonFactory jsonFactory) {
            this.jsonFactory = (JsonFactory) Preconditions.checkNotNull(jsonFactory);
        }

        public Class<? extends Header> getHeaderClass() {
            return this.headerClass;
        }

        public Parser setHeaderClass(Class<? extends Header> cls) {
            this.headerClass = cls;
            return this;
        }

        public Class<? extends Payload> getPayloadClass() {
            return this.payloadClass;
        }

        public Parser setPayloadClass(Class<? extends Payload> cls) {
            this.payloadClass = cls;
            return this;
        }

        public JsonFactory getJsonFactory() {
            return this.jsonFactory;
        }

        public JsonWebSignature parse(String str) throws IOException {
            boolean z;
            boolean z2 = true;
            int indexOf = str.indexOf(46);
            Preconditions.checkArgument(indexOf != -1);
            byte[] decodeBase64 = Base64.decodeBase64(str.substring(0, indexOf));
            int indexOf2 = str.indexOf(46, indexOf + 1);
            if (indexOf2 != -1) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z);
            if (str.indexOf(46, indexOf2 + 1) == -1) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z);
            byte[] decodeBase642 = Base64.decodeBase64(str.substring(indexOf + 1, indexOf2));
            byte[] decodeBase643 = Base64.decodeBase64(str.substring(indexOf2 + 1));
            byte[] bytesUtf8 = StringUtils.getBytesUtf8(str.substring(0, indexOf2));
            Header header = (Header) this.jsonFactory.fromInputStream(new ByteArrayInputStream(decodeBase64), this.headerClass);
            if (header.getAlgorithm() == null) {
                z2 = false;
            }
            Preconditions.checkArgument(z2);
            return new JsonWebSignature(header, (Payload) this.jsonFactory.fromInputStream(new ByteArrayInputStream(decodeBase642), this.payloadClass), decodeBase643, bytesUtf8);
        }
    }

    public JsonWebSignature(Header header, Payload payload, byte[] bArr, byte[] bArr2) {
        super(header, payload);
        this.signatureBytes = (byte[]) Preconditions.checkNotNull(bArr);
        this.signedContentBytes = (byte[]) Preconditions.checkNotNull(bArr2);
    }

    public Header getHeader() {
        return (Header) super.getHeader();
    }

    public final boolean verifySignature(PublicKey publicKey) throws GeneralSecurityException {
        if ("RS256".equals(getHeader().getAlgorithm())) {
            return SecurityUtils.verify(SecurityUtils.getSha256WithRsaSignatureAlgorithm(), publicKey, this.signatureBytes, this.signedContentBytes);
        }
        return false;
    }

    public final byte[] getSignatureBytes() {
        return this.signatureBytes;
    }

    public final byte[] getSignedContentBytes() {
        return this.signedContentBytes;
    }

    public static JsonWebSignature parse(JsonFactory jsonFactory, String str) throws IOException {
        return parser(jsonFactory).parse(str);
    }

    public static Parser parser(JsonFactory jsonFactory) {
        return new Parser(jsonFactory);
    }

    public static String signUsingRsaSha256(PrivateKey privateKey, JsonFactory jsonFactory, Header header, Payload payload) throws GeneralSecurityException, IOException {
        String str = Base64.encodeBase64URLSafeString(jsonFactory.toByteArray(header)) + "." + Base64.encodeBase64URLSafeString(jsonFactory.toByteArray(payload));
        return str + "." + Base64.encodeBase64URLSafeString(SecurityUtils.sign(SecurityUtils.getSha256WithRsaSignatureAlgorithm(), privateKey, StringUtils.getBytesUtf8(str)));
    }
}
