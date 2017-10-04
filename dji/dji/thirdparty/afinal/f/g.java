package dji.thirdparty.afinal.f;

import dji.thirdparty.afinal.f.a.c;
import java.io.IOException;
import java.net.UnknownHostException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.protocol.HttpContext;

public class g {
    private final AbstractHttpClient a;
    private final HttpContext b;
    private final c c = new c();
    private int d = 0;
    private String e;

    public g(AbstractHttpClient abstractHttpClient, HttpContext httpContext, String str) {
        this.a = abstractHttpClient;
        this.b = httpContext;
        this.e = str;
    }

    private Object a(HttpUriRequest httpUriRequest) throws IOException {
        int i;
        IOException iOException;
        int i2;
        IOException iOException2;
        IOException iOException3 = null;
        boolean z = true;
        HttpRequestRetryHandler httpRequestRetryHandler = this.a.getHttpRequestRetryHandler();
        while (z) {
            try {
                return this.c.a(this.a.execute(httpUriRequest, this.b).getEntity(), null, this.e);
            } catch (UnknownHostException e) {
                iOException3 = e;
                i = this.d + 1;
                this.d = i;
                z = httpRequestRetryHandler.retryRequest(iOException3, i, this.b);
            } catch (IOException e2) {
                iOException3 = e2;
                i = this.d + 1;
                this.d = i;
                z = httpRequestRetryHandler.retryRequest(iOException3, i, this.b);
            } catch (NullPointerException e3) {
                iOException = new IOException("NPE in HttpClient" + e3.getMessage());
                i2 = this.d + 1;
                this.d = i2;
                iOException2 = iOException;
                z = httpRequestRetryHandler.retryRequest(iOException, i2, this.b);
                iOException3 = iOException2;
            } catch (Exception e4) {
                iOException = new IOException("Exception" + e4.getMessage());
                i2 = this.d + 1;
                this.d = i2;
                iOException2 = iOException;
                z = httpRequestRetryHandler.retryRequest(iOException, i2, this.b);
                iOException3 = iOException2;
            }
        }
        if (iOException3 != null) {
            throw iOException3;
        }
        throw new IOException("未知网络错误");
    }

    public Object a(HttpUriRequest... httpUriRequestArr) {
        try {
            return a(httpUriRequestArr[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
