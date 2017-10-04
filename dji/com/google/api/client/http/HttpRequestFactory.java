package com.google.api.client.http;

import java.io.IOException;

public final class HttpRequestFactory {
    private final HttpRequestInitializer initializer;
    private final HttpTransport transport;

    HttpRequestFactory(HttpTransport httpTransport, HttpRequestInitializer httpRequestInitializer) {
        this.transport = httpTransport;
        this.initializer = httpRequestInitializer;
    }

    public HttpTransport getTransport() {
        return this.transport;
    }

    public HttpRequestInitializer getInitializer() {
        return this.initializer;
    }

    public HttpRequest buildRequest(String str, GenericUrl genericUrl, HttpContent httpContent) throws IOException {
        HttpRequest buildRequest = this.transport.buildRequest();
        if (this.initializer != null) {
            this.initializer.initialize(buildRequest);
        }
        buildRequest.setRequestMethod(str);
        if (genericUrl != null) {
            buildRequest.setUrl(genericUrl);
        }
        if (httpContent != null) {
            buildRequest.setContent(httpContent);
        }
        return buildRequest;
    }

    public HttpRequest buildDeleteRequest(GenericUrl genericUrl) throws IOException {
        return buildRequest(HttpMethods.DELETE, genericUrl, null);
    }

    public HttpRequest buildGetRequest(GenericUrl genericUrl) throws IOException {
        return buildRequest(HttpMethods.GET, genericUrl, null);
    }

    public HttpRequest buildPostRequest(GenericUrl genericUrl, HttpContent httpContent) throws IOException {
        return buildRequest(HttpMethods.POST, genericUrl, httpContent);
    }

    public HttpRequest buildPutRequest(GenericUrl genericUrl, HttpContent httpContent) throws IOException {
        return buildRequest(HttpMethods.PUT, genericUrl, httpContent);
    }

    public HttpRequest buildPatchRequest(GenericUrl genericUrl, HttpContent httpContent) throws IOException {
        return buildRequest("PATCH", genericUrl, httpContent);
    }

    public HttpRequest buildHeadRequest(GenericUrl genericUrl) throws IOException {
        return buildRequest(HttpMethods.HEAD, genericUrl, null);
    }
}
