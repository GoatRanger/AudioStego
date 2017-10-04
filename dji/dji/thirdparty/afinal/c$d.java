package dji.thirdparty.afinal;

import dji.pilot.usercenter.mode.n;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.impl.client.DefaultRedirectHandler;
import org.apache.http.protocol.HttpContext;

final class c$d extends DefaultRedirectHandler {
    private c$d() {
    }

    public boolean isRedirectRequested(HttpResponse httpResponse, HttpContext httpContext) {
        return super.isRedirectRequested(httpResponse, httpContext);
    }

    public URI getLocationURI(HttpResponse httpResponse, HttpContext httpContext) throws ProtocolException {
        return a(httpResponse.getFirstHeader(n.C).getValue());
    }

    private URI a(String str) throws ProtocolException {
        try {
            URL url = new URL(URLDecoder.decode(str, "UTF-8"));
            return new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
        } catch (Throwable e) {
            throw new ProtocolException(e.getMessage(), e);
        }
    }
}
