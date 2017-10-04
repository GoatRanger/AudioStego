package com.google.api.client.http;

import java.io.IOException;

class HttpHeaders$HeaderParsingFakeLevelHttpRequest extends LowLevelHttpRequest {
    private final HttpHeaders$ParseHeaderState state;
    private final HttpHeaders target;

    HttpHeaders$HeaderParsingFakeLevelHttpRequest(HttpHeaders httpHeaders, HttpHeaders$ParseHeaderState httpHeaders$ParseHeaderState) {
        this.target = httpHeaders;
        this.state = httpHeaders$ParseHeaderState;
    }

    public void addHeader(String str, String str2) {
        this.target.parseHeader(str, str2, this.state);
    }

    public LowLevelHttpResponse execute() throws IOException {
        throw new UnsupportedOperationException();
    }
}
