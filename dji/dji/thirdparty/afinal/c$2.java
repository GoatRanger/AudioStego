package dji.thirdparty.afinal;

import com.loopj.android.http.AsyncHttpClient;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

class c$2 implements HttpRequestInterceptor {
    final /* synthetic */ c a;

    c$2(c cVar) {
        this.a = cVar;
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        if (!httpRequest.containsHeader(AsyncHttpClient.HEADER_ACCEPT_ENCODING)) {
            httpRequest.addHeader(AsyncHttpClient.HEADER_ACCEPT_ENCODING, AsyncHttpClient.ENCODING_GZIP);
        }
        for (String str : c.a(this.a).keySet()) {
            httpRequest.addHeader(str, (String) c.a(this.a).get(str));
        }
    }
}
