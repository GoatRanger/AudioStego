package com.tencent.android.tpush.stat;

import android.content.Context;
import com.loopj.android.http.AsyncHttpClient;
import com.tencent.android.tpush.stat.a.e;
import dji.pilot.usercenter.protocol.d;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPOutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

class f {
    private static com.tencent.android.tpush.stat.a.f c = e.b();
    private static volatile f d = null;
    private static Context e = null;
    DefaultHttpClient a = null;
    StringBuilder b = new StringBuilder(4096);
    private long f = 0;

    private f(Context context) {
        try {
            e = context.getApplicationContext();
            this.f = System.currentTimeMillis() / 1000;
            if (c.b()) {
                try {
                    Logger.getLogger("org.apache.http.wire").setLevel(Level.FINER);
                    Logger.getLogger("org.apache.http.headers").setLevel(Level.FINER);
                    System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
                    System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
                    System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire", "debug");
                    System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "debug");
                    System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.headers", "debug");
                } catch (Throwable th) {
                }
            }
            HttpParams basicHttpParams = new BasicHttpParams();
            HttpConnectionParams.setStaleCheckingEnabled(basicHttpParams, false);
            HttpConnectionParams.setConnectionTimeout(basicHttpParams, 10000);
            HttpConnectionParams.setSoTimeout(basicHttpParams, 10000);
            this.a = new DefaultHttpClient(basicHttpParams);
            this.a.setKeepAliveStrategy(new g(this));
        } catch (Throwable th2) {
            c.b(th2);
        }
    }

    static void a(Context context) {
        e = context.getApplicationContext();
    }

    static Context a() {
        return e;
    }

    static f b(Context context) {
        if (d == null) {
            synchronized (f.class) {
                if (d == null) {
                    d = new f(context);
                }
            }
        }
        return d;
    }

    private void a(JSONObject jSONObject) {
        try {
            if (!jSONObject.isNull("cfg")) {
                c.a(e, jSONObject.getJSONObject("cfg"));
            }
            if (!jSONObject.isNull("ncts")) {
                int i = jSONObject.getInt("ncts");
                int currentTimeMillis = (int) (((long) i) - (System.currentTimeMillis() / 1000));
                if (c.b()) {
                    c.b("server time:" + i + ", diff time:" + currentTimeMillis);
                }
                e.k(e);
                e.a(e, currentTimeMillis);
            }
        } catch (Throwable th) {
            c.d(th);
        }
    }

    void a(List list, e eVar) {
        if (list != null && !list.isEmpty()) {
            int size = list.size();
            Throwable th = null;
            try {
                String str;
                this.b.delete(0, this.b.length());
                this.b.append(d.G);
                String str2 = "rc4";
                for (int i = 0; i < size; i++) {
                    this.b.append(list.get(i).toString());
                    if (i != size - 1) {
                        this.b.append(",");
                    }
                }
                this.b.append(d.H);
                String stringBuilder = this.b.toString();
                int length = stringBuilder.length();
                String str3 = c.d() + "/?index=" + this.f;
                this.f++;
                if (c.b()) {
                    c.b(d.G + str3 + "]Send request(eventsize:" + size + "," + length + "bytes), content:" + stringBuilder);
                }
                HttpPost httpPost = new HttpPost(str3);
                httpPost.addHeader(AsyncHttpClient.HEADER_ACCEPT_ENCODING, AsyncHttpClient.ENCODING_GZIP);
                httpPost.setHeader(dji.sdksharedlib.b.d.ck, "Keep-Alive");
                httpPost.removeHeaders("Cache-Control");
                HttpHost a = e.a(e);
                httpPost.addHeader(AsyncHttpClient.HEADER_CONTENT_ENCODING, str2);
                if (a == null) {
                    this.a.getParams().removeParameter("http.route.default-proxy");
                } else {
                    if (c.b()) {
                        c.h("proxy:" + a.toHostString());
                    }
                    httpPost.addHeader("X-Content-Encoding", str2);
                    this.a.getParams().setParameter("http.route.default-proxy", a);
                    httpPost.addHeader("X-Online-Host", c.d);
                    httpPost.addHeader("Accept", "*/*");
                    httpPost.addHeader(AsyncHttpClient.HEADER_CONTENT_TYPE, "json");
                }
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream(length);
                byte[] bytes = stringBuilder.getBytes("UTF-8");
                int length2 = bytes.length;
                if ((length > 512 ? 1 : null) != null) {
                    httpPost.removeHeaders(AsyncHttpClient.HEADER_CONTENT_ENCODING);
                    str = str2 + ",gzip";
                    httpPost.addHeader(AsyncHttpClient.HEADER_CONTENT_ENCODING, str);
                    if (a != null) {
                        httpPost.removeHeaders("X-Content-Encoding");
                        httpPost.addHeader("X-Content-Encoding", str);
                    }
                    byteArrayOutputStream.write(new byte[4]);
                    GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                    gZIPOutputStream.write(bytes);
                    gZIPOutputStream.close();
                    bytes = byteArrayOutputStream.toByteArray();
                    ByteBuffer.wrap(bytes, 0, 4).putInt(length2);
                    if (c.b()) {
                        c.h("before Gzip:" + length2 + " bytes, after Gzip:" + bytes.length + " bytes");
                    }
                }
                httpPost.setEntity(new ByteArrayEntity(com.tencent.android.tpush.stat.a.d.a(bytes)));
                HttpResponse execute = this.a.execute(httpPost);
                HttpEntity entity = execute.getEntity();
                int statusCode = execute.getStatusLine().getStatusCode();
                long contentLength = entity.getContentLength();
                if (c.b()) {
                    c.b("http recv response status code:" + statusCode + ", content length:" + contentLength);
                }
                if (contentLength <= 0) {
                    c.f("Server response no data.");
                    if (eVar != null) {
                        eVar.b();
                    }
                    EntityUtils.toString(entity);
                    return;
                }
                if (contentLength > 0) {
                    InputStream content = entity.getContent();
                    DataInputStream dataInputStream = new DataInputStream(content);
                    bytes = new byte[((int) entity.getContentLength())];
                    dataInputStream.readFully(bytes);
                    content.close();
                    dataInputStream.close();
                    Header firstHeader = execute.getFirstHeader(AsyncHttpClient.HEADER_CONTENT_ENCODING);
                    if (firstHeader != null) {
                        if (firstHeader.getValue().equalsIgnoreCase("gzip,rc4")) {
                            bytes = com.tencent.android.tpush.stat.a.d.b(e.a(bytes));
                        } else if (firstHeader.getValue().equalsIgnoreCase("rc4,gzip")) {
                            bytes = e.a(com.tencent.android.tpush.stat.a.d.b(bytes));
                        } else if (firstHeader.getValue().equalsIgnoreCase(AsyncHttpClient.ENCODING_GZIP)) {
                            bytes = e.a(bytes);
                        } else if (firstHeader.getValue().equalsIgnoreCase("rc4")) {
                            bytes = com.tencent.android.tpush.stat.a.d.b(bytes);
                        }
                    }
                    str = new String(bytes, "UTF-8");
                    if (c.b()) {
                        c.b("http get response data:" + str);
                    }
                    JSONObject jSONObject = new JSONObject(str);
                    if (statusCode == 200) {
                        a(jSONObject);
                        if (eVar != null) {
                            if (jSONObject.optInt("ret") == 0) {
                                eVar.a();
                            } else {
                                c.e("response error data.");
                                eVar.b();
                            }
                        }
                    } else {
                        c.e("Server response error code:" + statusCode + ", error:" + new String(bytes, "UTF-8"));
                        if (eVar != null) {
                            eVar.b();
                        }
                    }
                    content.close();
                } else {
                    EntityUtils.toString(entity);
                }
                byteArrayOutputStream.close();
                if (th != null) {
                    c.a(th);
                    if (eVar != null) {
                        try {
                            eVar.b();
                        } catch (Throwable th2) {
                            c.b(th2);
                        }
                    }
                    if (th instanceof OutOfMemoryError) {
                        System.gc();
                        this.b = null;
                        this.b = new StringBuilder(2048);
                    } else if (!(th instanceof UnknownHostException) && (th instanceof SocketTimeoutException)) {
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    void b(List list, e eVar) {
        a(list, eVar);
    }

    void a(com.tencent.android.tpush.stat.event.d dVar, e eVar) {
        b(Arrays.asList(new String[]{dVar.c()}), eVar);
    }
}
