package com.google.api.client.json.webtoken;

import com.google.api.client.util.Beta;
import com.google.api.client.util.Objects;
import com.google.api.client.util.Preconditions;

@Beta
public class JsonWebToken {
    private final Header header;
    private final Payload payload;

    public JsonWebToken(Header header, Payload payload) {
        this.header = (Header) Preconditions.checkNotNull(header);
        this.payload = (Payload) Preconditions.checkNotNull(payload);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("header", this.header).add("payload", this.payload).toString();
    }

    public Header getHeader() {
        return this.header;
    }

    public Payload getPayload() {
        return this.payload;
    }
}
