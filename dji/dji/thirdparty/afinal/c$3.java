package dji.thirdparty.afinal;

import com.loopj.android.http.AsyncHttpClient;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.HttpContext;

class c$3 implements HttpResponseInterceptor {
    final /* synthetic */ c a;

    c$3(c cVar) {
        this.a = cVar;
    }

    public void process(HttpResponse httpResponse, HttpContext httpContext) {
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null) {
            Header contentEncoding = entity.getContentEncoding();
            if (contentEncoding != null) {
                for (HeaderElement name : contentEncoding.getElements()) {
                    if (name.getName().equalsIgnoreCase(AsyncHttpClient.ENCODING_GZIP)) {
                        httpResponse.setEntity(new c$b(httpResponse.getEntity()));
                        return;
                    }
                }
            }
        }
    }
}
