package com.google.api.client.auth.oauth2;

import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import java.io.IOException;

class TokenRequest$1 implements HttpRequestInitializer {
    final /* synthetic */ TokenRequest this$0;

    TokenRequest$1(TokenRequest tokenRequest) {
        this.this$0 = tokenRequest;
    }

    public void initialize(HttpRequest httpRequest) throws IOException {
        if (this.this$0.requestInitializer != null) {
            this.this$0.requestInitializer.initialize(httpRequest);
        }
        final HttpExecuteInterceptor interceptor = httpRequest.getInterceptor();
        httpRequest.setInterceptor(new HttpExecuteInterceptor() {
            public void intercept(HttpRequest httpRequest) throws IOException {
                if (interceptor != null) {
                    interceptor.intercept(httpRequest);
                }
                if (TokenRequest$1.this.this$0.clientAuthentication != null) {
                    TokenRequest$1.this.this$0.clientAuthentication.intercept(httpRequest);
                }
            }
        });
    }
}
