package dji.thirdparty.afinal;

import com.alipay.sdk.b.b;
import com.loopj.android.http.AsyncHttpClient;
import dji.thirdparty.afinal.f.a;
import dji.thirdparty.afinal.f.f;
import dji.thirdparty.afinal.f.g;
import java.io.File;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpVersion;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.SyncBasicHttpContext;

public class c {
    private static final int a = 8192;
    private static final String b = "Accept-Encoding";
    private static final String c = "gzip";
    private static int d = 10;
    private static int e = 30000;
    private static int f = 4000;
    private static int g = 2000;
    private static int h = 3;
    private static int i = 6;
    private static final ThreadFactory n = new 1();
    private static final Executor o = Executors.newFixedThreadPool(i, n);
    private final DefaultHttpClient j;
    private final HttpContext k;
    private String l = "utf-8";
    private final Map<String, String> m;

    public c() {
        SocketFactory cVar;
        HttpParams basicHttpParams = new BasicHttpParams();
        basicHttpParams.setParameter("http.protocol.allow-circular-redirects", Boolean.valueOf(true));
        ConnManagerParams.setTimeout(basicHttpParams, (long) g);
        ConnManagerParams.setMaxConnectionsPerRoute(basicHttpParams, new ConnPerRouteBean(d));
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, 10);
        HttpConnectionParams.setSoTimeout(basicHttpParams, e);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, f);
        HttpConnectionParams.setTcpNoDelay(basicHttpParams, true);
        HttpConnectionParams.setSocketBufferSize(basicHttpParams, 8192);
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        try {
            KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
            instance.load(null, null);
            cVar = new c(instance);
        } catch (Exception e) {
            cVar = SSLSocketFactory.getSocketFactory();
        }
        cVar.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
        schemeRegistry.register(new Scheme(b.a, cVar, 443));
        ClientConnectionManager threadSafeClientConnManager = new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry);
        this.k = new SyncBasicHttpContext(new BasicHttpContext());
        this.j = new DefaultHttpClient(threadSafeClientConnManager, basicHttpParams);
        this.j.addRequestInterceptor(new 2(this));
        this.j.addResponseInterceptor(new 3(this));
        this.j.setRedirectHandler(new d(null));
        this.j.setHttpRequestRetryHandler(new f(h));
        this.m = new HashMap();
    }

    public HttpClient a() {
        return this.j;
    }

    public HttpContext b() {
        return this.k;
    }

    public void a(String str) {
        if (str != null && str.trim().length() != 0) {
            this.l = str;
        }
    }

    public void a(CookieStore cookieStore) {
        this.k.setAttribute("http.cookie-store", cookieStore);
    }

    public void b(String str) {
        HttpProtocolParams.setUserAgent(this.j.getParams(), str);
    }

    public void a(int i) {
        HttpParams params = this.j.getParams();
        ConnManagerParams.setTimeout(params, (long) i);
        HttpConnectionParams.setSoTimeout(params, i);
        HttpConnectionParams.setConnectionTimeout(params, i);
    }

    public void a(SSLSocketFactory sSLSocketFactory) {
        this.j.getConnectionManager().getSchemeRegistry().register(new Scheme(b.a, sSLSocketFactory, 443));
    }

    public void b(int i) {
        this.j.setHttpRequestRetryHandler(new f(i));
    }

    public void a(String str, String str2) {
        this.m.put(str, str2);
    }

    public void a(String str, a<? extends Object> aVar) {
        a(str, null, (a) aVar);
    }

    public void a(String str, dji.thirdparty.afinal.f.b bVar, a<? extends Object> aVar) {
        a(this.j, this.k, new HttpGet(d(str, bVar)), null, (a) aVar);
    }

    public void a(String str, Header[] headerArr, dji.thirdparty.afinal.f.b bVar, a<? extends Object> aVar) {
        HttpUriRequest httpGet = new HttpGet(d(str, bVar));
        if (headerArr != null) {
            httpGet.setHeaders(headerArr);
        }
        a(this.j, this.k, httpGet, null, (a) aVar);
    }

    public Object c(String str) {
        return a(str, null);
    }

    public Object a(String str, dji.thirdparty.afinal.f.b bVar) {
        return a(this.j, this.k, new HttpGet(d(str, bVar)), null);
    }

    public Object a(String str, Header[] headerArr, dji.thirdparty.afinal.f.b bVar) {
        HttpUriRequest httpGet = new HttpGet(d(str, bVar));
        if (headerArr != null) {
            httpGet.setHeaders(headerArr);
        }
        return a(this.j, this.k, httpGet, null);
    }

    public void b(String str, a<? extends Object> aVar) {
        b(str, null, (a) aVar);
    }

    public void b(String str, dji.thirdparty.afinal.f.b bVar, a<? extends Object> aVar) {
        a(str, a(bVar), null, (a) aVar);
    }

    public void a(String str, HttpEntity httpEntity, String str2, a<? extends Object> aVar) {
        a(this.j, this.k, a(new HttpPost(str), httpEntity), str2, (a) aVar);
    }

    public <T> void a(String str, Header[] headerArr, dji.thirdparty.afinal.f.b bVar, String str2, a<T> aVar) {
        HttpUriRequest httpPost = new HttpPost(str);
        if (bVar != null) {
            httpPost.setEntity(a(bVar));
        }
        if (headerArr != null) {
            httpPost.setHeaders(headerArr);
        }
        a(this.j, this.k, httpPost, str2, (a) aVar);
    }

    public void a(String str, Header[] headerArr, HttpEntity httpEntity, String str2, a<? extends Object> aVar) {
        HttpUriRequest a = a(new HttpPost(str), httpEntity);
        if (headerArr != null) {
            a.setHeaders(headerArr);
        }
        a(this.j, this.k, a, str2, (a) aVar);
    }

    public Object d(String str) {
        return b(str, null);
    }

    public Object b(String str, dji.thirdparty.afinal.f.b bVar) {
        return a(str, a(bVar), null);
    }

    public Object a(String str, HttpEntity httpEntity, String str2) {
        return a(this.j, this.k, a(new HttpPost(str), httpEntity), str2);
    }

    public Object a(String str, Header[] headerArr, dji.thirdparty.afinal.f.b bVar, String str2) {
        HttpUriRequest httpPost = new HttpPost(str);
        if (bVar != null) {
            httpPost.setEntity(a(bVar));
        }
        if (headerArr != null) {
            httpPost.setHeaders(headerArr);
        }
        return a(this.j, this.k, httpPost, str2);
    }

    public Object a(String str, Header[] headerArr, HttpEntity httpEntity, String str2) {
        HttpUriRequest a = a(new HttpPost(str), httpEntity);
        if (headerArr != null) {
            a.setHeaders(headerArr);
        }
        return a(this.j, this.k, a, str2);
    }

    public void c(String str, a<? extends Object> aVar) {
        c(str, null, aVar);
    }

    public void c(String str, dji.thirdparty.afinal.f.b bVar, a<? extends Object> aVar) {
        b(str, a(bVar), null, (a) aVar);
    }

    public void b(String str, HttpEntity httpEntity, String str2, a<? extends Object> aVar) {
        a(this.j, this.k, a(new HttpPut(str), httpEntity), str2, (a) aVar);
    }

    public void b(String str, Header[] headerArr, HttpEntity httpEntity, String str2, a<? extends Object> aVar) {
        HttpUriRequest a = a(new HttpPut(str), httpEntity);
        if (headerArr != null) {
            a.setHeaders(headerArr);
        }
        a(this.j, this.k, a, str2, (a) aVar);
    }

    public Object e(String str) {
        return c(str, null);
    }

    public Object c(String str, dji.thirdparty.afinal.f.b bVar) {
        return b(str, a(bVar), null);
    }

    public Object b(String str, HttpEntity httpEntity, String str2) {
        return b(str, null, httpEntity, str2);
    }

    public Object b(String str, Header[] headerArr, HttpEntity httpEntity, String str2) {
        HttpUriRequest a = a(new HttpPut(str), httpEntity);
        if (headerArr != null) {
            a.setHeaders(headerArr);
        }
        return a(this.j, this.k, a, str2);
    }

    public void d(String str, a<? extends Object> aVar) {
        a(this.j, this.k, new HttpDelete(str), null, (a) aVar);
    }

    public void d(String str, dji.thirdparty.afinal.f.b bVar, a<? extends Object> aVar) {
        a(this.j, this.k, new HttpDelete(d(str, bVar)), null, (a) aVar);
    }

    public void a(String str, Header[] headerArr, a<? extends Object> aVar) {
        HttpUriRequest httpDelete = new HttpDelete(str);
        if (headerArr != null) {
            httpDelete.setHeaders(headerArr);
        }
        a(this.j, this.k, httpDelete, null, (a) aVar);
    }

    public Object f(String str) {
        return a(str, null);
    }

    public Object a(String str, Header[] headerArr) {
        HttpUriRequest httpDelete = new HttpDelete(str);
        if (headerArr != null) {
            httpDelete.setHeaders(headerArr);
        }
        return a(this.j, this.k, httpDelete, null);
    }

    public dji.thirdparty.afinal.f.c<File> a(String str, String str2, a<File> aVar) {
        return a(str, null, str2, false, true, (a) aVar);
    }

    public dji.thirdparty.afinal.f.c<File> a(String str, String str2, boolean z, boolean z2, a<File> aVar) {
        return a(str, null, str2, z, z2, (a) aVar);
    }

    public dji.thirdparty.afinal.f.c<File> a(String str, String str2, boolean z, a<File> aVar) {
        return a(str, null, str2, z, true, (a) aVar);
    }

    public dji.thirdparty.afinal.f.c<File> a(String str, dji.thirdparty.afinal.f.b bVar, String str2, a<File> aVar) {
        return a(str, bVar, str2, false, true, (a) aVar);
    }

    public dji.thirdparty.afinal.f.c<File> a(String str, dji.thirdparty.afinal.f.b bVar, String str2, boolean z, boolean z2, a<File> aVar) {
        HttpGet httpGet = new HttpGet(d(str.trim(), bVar));
        dji.thirdparty.afinal.f.c<File> cVar = new dji.thirdparty.afinal.f.c(this.j, this.k, aVar, this.l);
        cVar.a(o, new Object[]{httpGet, str2, Boolean.valueOf(z), Boolean.valueOf(z2)});
        return cVar;
    }

    public dji.thirdparty.afinal.f.c<File> a(String str, String str2, HttpEntity httpEntity, String str3, boolean z, a<File> aVar) {
        return a(str, str2, httpEntity, str3, z, true, aVar);
    }

    public dji.thirdparty.afinal.f.c<File> a(String str, String str2, HttpEntity httpEntity, String str3, boolean z, boolean z2, a<File> aVar) {
        dji.thirdparty.afinal.f.c<File> cVar = new dji.thirdparty.afinal.f.c(this.j, this.k, aVar, this.l);
        HttpUriRequest a = a(new HttpPost(str), httpEntity);
        if (str3 != null) {
            a.addHeader(AsyncHttpClient.HEADER_CONTENT_TYPE, str3);
        }
        cVar.a(o, new Object[]{a, str2, Boolean.valueOf(z), Boolean.valueOf(z2)});
        return cVar;
    }

    protected <T> void a(DefaultHttpClient defaultHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, String str, a<T> aVar) {
        if (str != null) {
            httpUriRequest.addHeader(AsyncHttpClient.HEADER_CONTENT_TYPE, str);
        }
        new dji.thirdparty.afinal.f.c(defaultHttpClient, httpContext, aVar, this.l).a(o, new Object[]{httpUriRequest});
    }

    protected Object a(DefaultHttpClient defaultHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, String str) {
        if (str != null) {
            httpUriRequest.addHeader(AsyncHttpClient.HEADER_CONTENT_TYPE, str);
        }
        return new g(defaultHttpClient, httpContext, this.l).a(new HttpUriRequest[]{httpUriRequest});
    }

    public static String d(String str, dji.thirdparty.afinal.f.b bVar) {
        if (bVar == null) {
            return str;
        }
        return str + "?" + bVar.c();
    }

    private HttpEntity a(dji.thirdparty.afinal.f.b bVar) {
        if (bVar != null) {
            return bVar.a();
        }
        return null;
    }

    private HttpEntityEnclosingRequestBase a(HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase, HttpEntity httpEntity) {
        if (httpEntity != null) {
            httpEntityEnclosingRequestBase.setEntity(httpEntity);
        }
        return httpEntityEnclosingRequestBase;
    }
}
