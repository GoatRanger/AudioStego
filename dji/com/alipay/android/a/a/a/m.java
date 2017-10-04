package com.alipay.android.a.a.a;

import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.protocol.HttpContext;

final class m implements ConnectionKeepAliveStrategy {
    final /* synthetic */ k a;

    m(k kVar) {
        this.a = kVar;
    }

    public final long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
        return 180000;
    }
}
