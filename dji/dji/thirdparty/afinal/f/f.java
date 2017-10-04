package dji.thirdparty.afinal.f;

import android.os.SystemClock;
import com.google.api.client.http.HttpMethods;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashSet;
import javax.net.ssl.SSLHandshakeException;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;

public class f implements HttpRequestRetryHandler {
    private static final int a = 1000;
    private static HashSet<Class<?>> b = new HashSet();
    private static HashSet<Class<?>> c = new HashSet();
    private final int d;

    static {
        b.add(NoHttpResponseException.class);
        b.add(UnknownHostException.class);
        b.add(SocketException.class);
        c.add(InterruptedIOException.class);
        c.add(SSLHandshakeException.class);
    }

    public f(int i) {
        this.d = i;
    }

    public boolean retryRequest(IOException iOException, int i, HttpContext httpContext) {
        boolean z = true;
        Boolean bool = (Boolean) httpContext.getAttribute("http.request_sent");
        boolean z2 = bool != null && bool.booleanValue();
        if (i > this.d) {
            z2 = false;
        } else if (c.contains(iOException.getClass())) {
            z2 = false;
        } else if (b.contains(iOException.getClass())) {
            z2 = true;
        } else if (z2) {
            z2 = true;
        } else {
            z2 = true;
        }
        if (z2) {
            HttpUriRequest httpUriRequest = (HttpUriRequest) httpContext.getAttribute("http.request");
            if (httpUriRequest == null || HttpMethods.POST.equals(httpUriRequest.getMethod())) {
                z = false;
            }
        } else {
            z = z2;
        }
        if (z) {
            SystemClock.sleep(1000);
        } else {
            iOException.printStackTrace();
        }
        return z;
    }
}
