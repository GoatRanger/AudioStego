package com.alipay.android.a.a.a;

import com.google.api.client.http.HttpStatusCodes;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultRedirectHandler;
import org.apache.http.protocol.HttpContext;

final class l extends DefaultRedirectHandler {
    int a;
    final /* synthetic */ k b;

    l(k kVar) {
        this.b = kVar;
    }

    public final boolean isRedirectRequested(HttpResponse httpResponse, HttpContext httpContext) {
        this.a++;
        boolean isRedirectRequested = super.isRedirectRequested(httpResponse, httpContext);
        if (isRedirectRequested || this.a >= 5) {
            return isRedirectRequested;
        }
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        return (statusCode == HttpStatusCodes.STATUS_CODE_MOVED_PERMANENTLY || statusCode == HttpStatusCodes.STATUS_CODE_FOUND) ? true : isRedirectRequested;
    }
}
