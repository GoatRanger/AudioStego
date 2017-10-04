package com.google.api.client.http;

import java.util.concurrent.Callable;

class HttpRequest$1 implements Callable<HttpResponse> {
    final /* synthetic */ HttpRequest this$0;

    HttpRequest$1(HttpRequest httpRequest) {
        this.this$0 = httpRequest;
    }

    public HttpResponse call() throws Exception {
        return this.this$0.execute();
    }
}
