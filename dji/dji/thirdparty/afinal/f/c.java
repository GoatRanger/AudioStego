package dji.thirdparty.afinal.f;

import android.os.SystemClock;
import dji.thirdparty.afinal.c.d;
import dji.thirdparty.afinal.f.a.a;
import dji.thirdparty.afinal.f.a.b;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.protocol.HttpContext;

public class c<T> extends d<Object, Object, Object> implements a {
    private static final int n = 1;
    private static final int o = 2;
    private static final int p = 3;
    private static final int q = 4;
    private final AbstractHttpClient a;
    private final HttpContext b;
    private final dji.thirdparty.afinal.f.a.c c = new dji.thirdparty.afinal.f.a.c();
    private final b d = new b();
    private final a<T> e;
    private int f = 0;
    private String j = null;
    private boolean k = false;
    private String l;
    private boolean m = false;
    private long r;

    public c(AbstractHttpClient abstractHttpClient, HttpContext httpContext, a<T> aVar, String str) {
        this.a = abstractHttpClient;
        this.b = httpContext;
        this.e = aVar;
        this.l = str;
    }

    private void a(HttpUriRequest httpUriRequest) throws IOException {
        IOException iOException;
        int i;
        IOException iOException2;
        if (this.k && this.j != null) {
            long length;
            File file = new File(this.j);
            if (file.isFile() && file.exists()) {
                length = file.length();
            } else {
                length = 0;
            }
            if (length > 0) {
                httpUriRequest.setHeader("Range", "bytes=" + length + "-");
            }
        }
        IOException iOException3 = null;
        HttpRequestRetryHandler httpRequestRetryHandler = this.a.getHttpRequestRetryHandler();
        boolean z = true;
        while (z) {
            try {
                if (!e()) {
                    HttpResponse execute = this.a.execute(httpUriRequest, this.b);
                    if (!e()) {
                        a(execute);
                        return;
                    }
                    return;
                }
                return;
            } catch (UnknownHostException e) {
                e(Integer.valueOf(3), e, Integer.valueOf(0), "unknownHostException：can't resolve host");
                return;
            } catch (IOException e2) {
                iOException3 = e2;
                int i2 = this.f + 1;
                this.f = i2;
                z = httpRequestRetryHandler.retryRequest(iOException3, i2, this.b);
            } catch (NullPointerException e3) {
                iOException = new IOException("NPE in HttpClient" + e3.getMessage());
                i = this.f + 1;
                this.f = i;
                iOException2 = iOException;
                z = httpRequestRetryHandler.retryRequest(iOException, i, this.b);
                iOException3 = iOException2;
            } catch (Exception e4) {
                iOException = new IOException("Exception" + e4.getMessage());
                i = this.f + 1;
                this.f = i;
                iOException2 = iOException;
                z = httpRequestRetryHandler.retryRequest(iOException, i, this.b);
                iOException3 = iOException2;
            }
        }
        if (iOException3 != null) {
            throw iOException3;
        }
        throw new IOException("未知网络错误");
    }

    protected Object b(Object... objArr) {
        if (objArr != null && objArr.length == 4) {
            this.j = String.valueOf(objArr[1]);
            this.k = ((Boolean) objArr[2]).booleanValue();
            this.m = ((Boolean) objArr[3]).booleanValue();
        }
        try {
            e(Integer.valueOf(1));
            a((HttpUriRequest) objArr[0]);
        } catch (IOException e) {
            e(Integer.valueOf(3), e, Integer.valueOf(0), e.getMessage());
        }
        return null;
    }

    protected void c(Object... objArr) {
        switch (Integer.valueOf(String.valueOf(objArr[0])).intValue()) {
            case 1:
                if (this.e != null) {
                    this.e.a(this.k);
                    break;
                }
                break;
            case 2:
                if (this.e != null) {
                    this.e.a(Long.valueOf(String.valueOf(objArr[1])).longValue(), Long.valueOf(String.valueOf(objArr[2])).longValue());
                    break;
                }
                break;
            case 3:
                if (this.e != null) {
                    this.e.a((Throwable) objArr[1], ((Integer) objArr[2]).intValue(), (String) objArr[3]);
                    break;
                }
                break;
            case 4:
                if (this.e != null) {
                    this.e.a(objArr[1]);
                    break;
                }
                break;
        }
        super.c(objArr);
    }

    public boolean g() {
        return this.d.a();
    }

    public void h() {
        this.d.a(true);
    }

    private void a(HttpResponse httpResponse) {
        StatusLine statusLine = httpResponse.getStatusLine();
        if (statusLine.getStatusCode() >= 300) {
            String str = "response status error code:" + statusLine.getStatusCode();
            if (statusLine.getStatusCode() == 416 && this.k) {
                str = str + " \n maybe you have download complete.";
            }
            e(Integer.valueOf(3), new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase()), Integer.valueOf(statusLine.getStatusCode()), str);
            return;
        }
        try {
            HttpEntity entity = httpResponse.getEntity();
            Object obj = null;
            if (entity != null) {
                this.r = SystemClock.uptimeMillis();
                if (this.j != null) {
                    obj = this.d.a(entity, this, this.j, this.k, this.m);
                    if (obj instanceof String) {
                        e(Integer.valueOf(3), null, Integer.valueOf(0), (String) obj);
                        return;
                    }
                }
                obj = this.c.a(entity, this, this.l);
            }
            e(Integer.valueOf(4), obj);
        } catch (IOException e) {
            e(Integer.valueOf(3), e, Integer.valueOf(0), e.getMessage());
        }
    }

    public void a(long j, long j2, boolean z) {
        if (this.e != null && this.e.e()) {
            if (z) {
                e(Integer.valueOf(2), Long.valueOf(j), Long.valueOf(j2));
                return;
            }
            long uptimeMillis = SystemClock.uptimeMillis();
            if (uptimeMillis - this.r >= ((long) this.e.f())) {
                this.r = uptimeMillis;
                e(Integer.valueOf(2), Long.valueOf(j), Long.valueOf(j2));
            }
        }
    }
}
