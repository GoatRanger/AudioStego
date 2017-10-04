package com.here.network;

import android.net.SSLCertificateSocketFactory;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.util.Log;
import com.facebook.internal.a;
import com.google.api.client.http.HttpMethods;
import com.loopj.android.http.AsyncHttpClient;
import dji.sdksharedlib.b.d;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;

public class NetworkProtocol {
    private final String LOGTAG = "NetworkProtocol";
    private int m_clientId = ((int) (System.currentTimeMillis() % 10000));
    private ExecutorService m_executor;

    private class GetTask extends AsyncTask<Request, Void, Void> {
        private GetTask() {
        }

        public synchronized boolean cancelTask(boolean z) {
            return cancel(z);
        }

        protected Void doInBackground(Request... requestArr) {
            int i;
            Throwable th;
            Exception exception;
            HttpURLConnection httpURLConnection;
            for (int i2 = 0; i2 < requestArr.length; i2++) {
                HttpURLConnection httpURLConnection2;
                try {
                    URLConnection openConnection;
                    int i3;
                    String responseMessage;
                    Request request = requestArr[i2];
                    URL url = new URL(request.url());
                    if (!request.hasProxy()) {
                        openConnection = url.openConnection();
                    } else if (request.noProxy()) {
                        openConnection = url.openConnection(Proxy.NO_PROXY);
                    } else {
                        openConnection = url.openConnection(new Proxy(Type.HTTP, new InetSocketAddress(request.proxyServer(), request.proxyPort())));
                    }
                    if (openConnection instanceof HttpsURLConnection) {
                        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) openConnection;
                        SSLContext sSLContext = null;
                        if (!request.certificatePath().isEmpty()) {
                            sSLContext = NetworkSSLContextFactory.getInstance().getSSLContext(request.certificatePath());
                            if (sSLContext == null) {
                                Log.e("NetworkProtocol", "NetworkProtocol::GetTask::run failed to create ssl context, certificate path is set to ? " + request.certificatePath());
                            }
                        }
                        if (sSLContext != null) {
                            httpsURLConnection.setSSLSocketFactory(sSLContext.getSocketFactory());
                        } else if (request.ignoreCertificate()) {
                            httpsURLConnection.setSSLSocketFactory(SSLCertificateSocketFactory.getInsecure(0, null));
                            httpsURLConnection.setHostnameVerifier(new AllowAllHostnameVerifier());
                        }
                    }
                    if (openConnection instanceof HttpURLConnection) {
                        httpURLConnection2 = (HttpURLConnection) openConnection;
                    } else {
                        httpURLConnection2 = null;
                    }
                    if (httpURLConnection2 != null) {
                        if (request.verb() == HttpVerb.HEAD) {
                            httpURLConnection2.setRequestMethod(HttpMethods.HEAD);
                        } else if (request.verb() == HttpVerb.PUT) {
                            httpURLConnection2.setRequestMethod(HttpMethods.PUT);
                        } else if (request.verb() == HttpVerb.DELETE) {
                            httpURLConnection2.setRequestMethod(HttpMethods.DELETE);
                        } else if (request.verb() == HttpVerb.POST) {
                            httpURLConnection2.setRequestMethod(HttpMethods.POST);
                        } else {
                            httpURLConnection2.setRequestMethod(HttpMethods.GET);
                        }
                    }
                    String[] headers = request.headers();
                    Object obj = null;
                    if (headers != null) {
                        for (i3 = 0; i3 + 1 < headers.length; i3 += 2) {
                            Log.d("NetworkProtocol", headers[i3] + ": " + headers[i3 + 1]);
                            openConnection.addRequestProperty(headers[i3], headers[i3 + 1]);
                            if (headers[i3].compareToIgnoreCase("If-None-Match") == 0) {
                                obj = 1;
                            }
                        }
                    }
                    openConnection.setUseCaches(false);
                    openConnection.setConnectTimeout(request.connectTimeout() * 1000);
                    openConnection.setReadTimeout(request.requestTimeout() * 1000);
                    if (!(request.verb() == HttpVerb.HEAD || httpURLConnection2 == null)) {
                        if (request.postData() != null) {
                            httpURLConnection2.setFixedLengthStreamingMode(request.postData().length);
                        } else {
                            httpURLConnection2.setChunkedStreamingMode(8192);
                        }
                    }
                    if (VERSION.SDK_INT < 21 && (request.verb() == HttpVerb.HEAD || r2 != null)) {
                        openConnection.setRequestProperty(AsyncHttpClient.HEADER_ACCEPT_ENCODING, "");
                    }
                    openConnection.setRequestProperty(d.ck, "Close");
                    openConnection.setDoInput(true);
                    if (request.postData() != null) {
                        openConnection.setDoOutput(true);
                        openConnection.getOutputStream().write(request.postData());
                    } else {
                        openConnection.setDoOutput(false);
                    }
                    String str = "";
                    if (httpURLConnection2 != null) {
                        i3 = httpURLConnection2.getResponseCode();
                        responseMessage = httpURLConnection2.getResponseMessage();
                        i = i3;
                    } else {
                        responseMessage = str;
                        i = 0;
                    }
                    if (isCancelled()) {
                        cleanup(httpURLConnection2);
                        NetworkProtocol.this.completeRequest(request.clientId(), request.requestId(), -5, a.t, 0, "", "");
                        cleanup(httpURLConnection2);
                    } else {
                        String str2;
                        String str3;
                        int indexOf;
                        int i4;
                        InputStream bufferedInputStream;
                        long j;
                        int i5;
                        byte[] bArr;
                        str = openConnection.getHeaderField("ETag");
                        if (str == null) {
                            str2 = "";
                        } else {
                            str2 = str;
                        }
                        str = openConnection.getHeaderField(AsyncHttpClient.HEADER_CONTENT_TYPE);
                        if (str == null) {
                            str3 = "";
                        } else {
                            str3 = str;
                        }
                        long headerFieldDate = openConnection.getHeaderFieldDate("Date", 0);
                        String headerField = openConnection.getHeaderField("Cache-Control");
                        if (headerField != null) {
                            indexOf = headerField.indexOf("max-age=");
                            if (indexOf >= 0) {
                                indexOf += 8;
                                int indexOf2 = headerField.indexOf(44, indexOf);
                                if (indexOf2 > indexOf) {
                                    try {
                                        headerField = headerField.substring(indexOf, indexOf2);
                                    } catch (Exception e) {
                                        i4 = 0;
                                    } catch (Throwable th2) {
                                        th = th2;
                                    }
                                } else {
                                    try {
                                        headerField = headerField.substring(indexOf);
                                    } catch (FileNotFoundException e2) {
                                        if (httpURLConnection2 != null) {
                                            bufferedInputStream = new BufferedInputStream(httpURLConnection2.getErrorStream());
                                        } else {
                                            throw e2;
                                        }
                                    } catch (FileNotFoundException e22) {
                                        if (i == 0) {
                                            throw e22;
                                        }
                                    } catch (Exception e3) {
                                        exception = e3;
                                        httpURLConnection = httpURLConnection2;
                                    } catch (Throwable th22) {
                                        th = th22;
                                    }
                                }
                                i4 = Integer.parseInt(headerField);
                                j = 0;
                                str = openConnection.getHeaderField(AsyncHttpClient.HEADER_CONTENT_RANGE);
                                if (str != null) {
                                    i3 = str.indexOf("bytes ");
                                    if (i3 >= 0) {
                                        i3 += 6;
                                        indexOf = str.indexOf(45, i3);
                                        if (indexOf <= i3) {
                                            try {
                                                str = str.substring(i3, indexOf);
                                            } catch (Exception e32) {
                                                Log.d("NetworkProtocol", "parseInt: " + e32);
                                            } catch (Throwable th222) {
                                                th = th222;
                                            }
                                        } else {
                                            str = str.substring(i3);
                                        }
                                        j = Long.parseLong(str);
                                    }
                                }
                                i3 = 0;
                                while (openConnection.getHeaderFieldKey(i3) != null) {
                                    i3++;
                                }
                                headers = new String[(i3 * 2)];
                                for (i5 = 0; i5 < i3; i5++) {
                                    headers[i5 * 2] = openConnection.getHeaderFieldKey(i5);
                                    headers[(i5 * 2) + 1] = openConnection.getHeaderField(i5);
                                }
                                if (isCancelled()) {
                                    NetworkProtocol.this.headersCallback(request.clientId(), request.requestId(), headers);
                                    NetworkProtocol.this.dateAndOffsetCallback(request.clientId(), request.requestId(), headerFieldDate, j);
                                    bufferedInputStream = new BufferedInputStream(openConnection.getInputStream());
                                    bArr = new byte[8192];
                                    while (!isCancelled()) {
                                        indexOf = bufferedInputStream.read(bArr);
                                        if (indexOf >= 0) {
                                            NetworkProtocol.this.dataCallback(request.clientId(), request.requestId(), bArr, indexOf);
                                        }
                                    }
                                    if (isCancelled()) {
                                        NetworkProtocol.this.completeRequest(request.clientId(), request.requestId(), i, responseMessage, i4, str2, str3);
                                    } else {
                                        NetworkProtocol.this.completeRequest(request.clientId(), request.requestId(), -5, a.t, 0, "", "");
                                    }
                                    cleanup(httpURLConnection2);
                                } else {
                                    cleanup(httpURLConnection2);
                                    NetworkProtocol.this.completeRequest(request.clientId(), request.requestId(), -5, a.t, 0, "", "");
                                    cleanup(httpURLConnection2);
                                }
                            }
                        }
                        i4 = 0;
                        j = 0;
                        str = openConnection.getHeaderField(AsyncHttpClient.HEADER_CONTENT_RANGE);
                        if (str != null) {
                            i3 = str.indexOf("bytes ");
                            if (i3 >= 0) {
                                i3 += 6;
                                indexOf = str.indexOf(45, i3);
                                if (indexOf <= i3) {
                                    str = str.substring(i3);
                                } else {
                                    str = str.substring(i3, indexOf);
                                }
                                j = Long.parseLong(str);
                            }
                        }
                        i3 = 0;
                        while (openConnection.getHeaderFieldKey(i3) != null) {
                            i3++;
                        }
                        headers = new String[(i3 * 2)];
                        for (i5 = 0; i5 < i3; i5++) {
                            headers[i5 * 2] = openConnection.getHeaderFieldKey(i5);
                            headers[(i5 * 2) + 1] = openConnection.getHeaderField(i5);
                        }
                        if (isCancelled()) {
                            NetworkProtocol.this.headersCallback(request.clientId(), request.requestId(), headers);
                            NetworkProtocol.this.dateAndOffsetCallback(request.clientId(), request.requestId(), headerFieldDate, j);
                            bufferedInputStream = new BufferedInputStream(openConnection.getInputStream());
                            bArr = new byte[8192];
                            while (!isCancelled()) {
                                indexOf = bufferedInputStream.read(bArr);
                                if (indexOf >= 0) {
                                    NetworkProtocol.this.dataCallback(request.clientId(), request.requestId(), bArr, indexOf);
                                }
                            }
                            if (isCancelled()) {
                                NetworkProtocol.this.completeRequest(request.clientId(), request.requestId(), i, responseMessage, i4, str2, str3);
                            } else {
                                NetworkProtocol.this.completeRequest(request.clientId(), request.requestId(), -5, a.t, 0, "", "");
                            }
                            cleanup(httpURLConnection2);
                        } else {
                            cleanup(httpURLConnection2);
                            NetworkProtocol.this.completeRequest(request.clientId(), request.requestId(), -5, a.t, 0, "", "");
                            cleanup(httpURLConnection2);
                        }
                    }
                } catch (Exception e322) {
                    exception = e322;
                    httpURLConnection = null;
                    try {
                        Log.e("NetworkProtocol", "NetworkProtocol::GetTask::run exception: " + exception);
                        exception.printStackTrace();
                        NetworkProtocol.this.completeRequest(requestArr[i2].clientId(), requestArr[i2].requestId(), -1, exception.toString(), 0, "", "");
                        cleanup(httpURLConnection);
                    } catch (Throwable th3) {
                        th = th3;
                        httpURLConnection2 = httpURLConnection;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    httpURLConnection2 = null;
                }
            }
            return null;
            cleanup(httpURLConnection2);
            throw th;
        }

        private final void cleanup(HttpURLConnection httpURLConnection) {
            if (httpURLConnection != null) {
                if (httpURLConnection.getDoOutput()) {
                    try {
                        if (httpURLConnection.getOutputStream() != null) {
                            httpURLConnection.getOutputStream().flush();
                        }
                    } catch (IOException e) {
                    }
                }
                try {
                    clearInputStream(httpURLConnection.getInputStream());
                } catch (IOException e2) {
                }
                try {
                    clearInputStream(httpURLConnection.getErrorStream());
                } catch (IOException e3) {
                }
                if (httpURLConnection.getDoOutput()) {
                    try {
                        if (httpURLConnection.getOutputStream() != null) {
                            httpURLConnection.getOutputStream().close();
                        }
                    } catch (IOException e4) {
                    }
                }
                try {
                    if (httpURLConnection.getInputStream() != null) {
                        httpURLConnection.getInputStream().close();
                    }
                } catch (IOException e5) {
                }
                try {
                    if (httpURLConnection.getErrorStream() != null) {
                        httpURLConnection.getErrorStream().close();
                    }
                } catch (IOException e6) {
                }
                httpURLConnection.disconnect();
            }
        }

        private final void clearInputStream(InputStream inputStream) throws IOException {
            if (inputStream != null) {
                while (inputStream.read(new byte[8192]) > 0) {
                }
            }
        }
    }

    public enum HttpVerb {
        GET,
        POST,
        HEAD,
        PUT,
        DELETE
    }

    public class Request {
        private final String m_certificatePath;
        private final int m_clientId;
        private final int m_connectTimeout;
        private final String[] m_headers;
        private final boolean m_ignoreCertificate;
        private final byte[] m_postData;
        private final int m_proxyPort;
        private final String m_proxyServer;
        private final int m_requestId;
        private final int m_requestTimeout;
        private final String m_url;
        private final HttpVerb m_verb;

        public Request(String str, HttpVerb httpVerb, int i, int i2, int i3, int i4, String[] strArr, byte[] bArr, boolean z, String str2, int i5, String str3) {
            this.m_url = str;
            this.m_verb = httpVerb;
            this.m_clientId = i;
            this.m_requestId = i2;
            this.m_connectTimeout = i3;
            this.m_requestTimeout = i4;
            this.m_headers = strArr;
            this.m_postData = bArr;
            this.m_ignoreCertificate = z;
            this.m_proxyServer = str2;
            this.m_proxyPort = i5;
            this.m_certificatePath = str3;
        }

        public final String url() {
            return this.m_url;
        }

        public final HttpVerb verb() {
            return this.m_verb;
        }

        public final int clientId() {
            return this.m_clientId;
        }

        public final int requestId() {
            return this.m_requestId;
        }

        public final int connectTimeout() {
            return this.m_connectTimeout;
        }

        public final int requestTimeout() {
            return this.m_requestTimeout;
        }

        public final String[] headers() {
            return this.m_headers;
        }

        public final byte[] postData() {
            return this.m_postData;
        }

        public final boolean ignoreCertificate() {
            return this.m_ignoreCertificate;
        }

        public final String proxyServer() {
            return this.m_proxyServer;
        }

        public final int proxyPort() {
            return this.m_proxyPort;
        }

        public final boolean hasProxy() {
            return (this.m_proxyServer == null || this.m_proxyServer.isEmpty()) ? false : true;
        }

        public final boolean noProxy() {
            return hasProxy() && this.m_proxyServer.equals("No");
        }

        public final String certificatePath() {
            return this.m_certificatePath;
        }
    }

    private native void completeRequest(int i, int i2, int i3, String str, int i4, String str2, String str3);

    private native void dataCallback(int i, int i2, byte[] bArr, int i3);

    private native void dateAndOffsetCallback(int i, int i2, long j, long j2);

    private native void headersCallback(int i, int i2, String[] strArr);

    public static HttpVerb toHttpVerb(int i) {
        switch (i) {
            case 0:
                return HttpVerb.GET;
            case 1:
                return HttpVerb.POST;
            case 2:
                return HttpVerb.HEAD;
            case 3:
                return HttpVerb.PUT;
            case 4:
                return HttpVerb.DELETE;
            default:
                return HttpVerb.GET;
        }
    }

    public NetworkProtocol() {
        Log.d("NetworkProtocol", "NetworkProtocol::NetworkProtocol");
        this.m_executor = Executors.newFixedThreadPool(8);
    }

    public void shutdown() {
        if (this.m_executor != null) {
            this.m_executor.shutdown();
            this.m_executor = null;
        }
    }

    public int registerClient() {
        int i = this.m_clientId;
        this.m_clientId = i + 1;
        return i;
    }

    public GetTask send(String str, int i, int i2, int i3, int i4, int i5, String[] strArr, byte[] bArr, boolean z, String str2, int i6, String str3) {
        Request request = new Request(str, toHttpVerb(i), i2, i3, i4, i5, strArr, bArr, z, str2, i6, str3);
        GetTask getTask = new GetTask();
        getTask.executeOnExecutor(this.m_executor, new Request[]{request});
        return getTask;
    }
}
