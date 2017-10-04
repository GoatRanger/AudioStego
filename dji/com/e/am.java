package com.e;

import android.os.Build.VERSION;
import com.alipay.sdk.h.a;
import com.amap.api.maps.AMapException;
import com.google.api.client.http.HttpMethods;
import dji.sdksharedlib.b.d;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.PushbackInputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

public class am {
    private static an a;
    private int b;
    private int c;
    private boolean d;
    private SSLContext e;
    private Proxy f;
    private volatile boolean g;
    private long h;
    private long i;
    private HostnameVerifier j;

    am(int i, int i2, Proxy proxy) {
        this(i, i2, proxy, false);
    }

    am(int i, int i2, Proxy proxy, boolean z) {
        this.g = false;
        this.h = -1;
        this.i = 0;
        this.j = new HostnameVerifier(this) {
            final /* synthetic */ am a;

            {
                this.a = r1;
            }

            public boolean verify(String str, SSLSession sSLSession) {
                return HttpsURLConnection.getDefaultHostnameVerifier().verify("*.amap.com", sSLSession);
            }
        };
        this.b = i;
        this.c = i2;
        this.f = proxy;
        this.d = z;
        if (z) {
            try {
                SSLContext instance = SSLContext.getInstance("TLS");
                instance.init(null, null, null);
                this.e = instance;
            } catch (Throwable th) {
                dg.a(th, "HttpUtil", "HttpUtil");
            }
        }
    }

    private ap a(HttpURLConnection httpURLConnection) throws ct, IOException {
        InputStream inputStream;
        InputStream pushbackInputStream;
        IOException e;
        Throwable th;
        InputStream inputStream2;
        PushbackInputStream pushbackInputStream2 = null;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            Map headerFields = httpURLConnection.getHeaderFields();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != 200) {
                throw new ct("网络异常原因：" + httpURLConnection.getResponseMessage() + " 网络异常状态码：" + responseCode);
            }
            byte[] bArr;
            InputStream gZIPInputStream;
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                inputStream = httpURLConnection.getInputStream();
                try {
                    pushbackInputStream = new PushbackInputStream(inputStream, 2);
                } catch (IOException e2) {
                    e = e2;
                    pushbackInputStream = null;
                    try {
                        throw e;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    pushbackInputStream = null;
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable th4) {
                            dg.a(th4, "HttpUrlUtil", "parseResult");
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable th5) {
                            dg.a(th5, "HttpUrlUtil", "parseResult");
                        }
                    }
                    if (pushbackInputStream2 != null) {
                        try {
                            pushbackInputStream2.close();
                        } catch (Throwable th6) {
                            dg.a(th6, "HttpUrlUtil", "parseResult");
                        }
                    }
                    if (pushbackInputStream != null) {
                        try {
                            pushbackInputStream.close();
                        } catch (Throwable th62) {
                            dg.a(th62, "HttpUrlUtil", "parseResult");
                        }
                    }
                    if (httpURLConnection != null) {
                        try {
                            httpURLConnection.disconnect();
                        } catch (Throwable th622) {
                            dg.a(th622, "HttpUrlUtil", "parseResult");
                        }
                    }
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
                pushbackInputStream = null;
                inputStream = null;
                throw e;
            } catch (Throwable th7) {
                th = th7;
                pushbackInputStream = null;
                inputStream = null;
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (pushbackInputStream2 != null) {
                    pushbackInputStream2.close();
                }
                if (pushbackInputStream != null) {
                    pushbackInputStream.close();
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
            try {
                bArr = new byte[2];
                pushbackInputStream.read(bArr);
                pushbackInputStream.unread(bArr);
                gZIPInputStream = (bArr[0] == (byte) 31 && bArr[1] == (byte) -117) ? new GZIPInputStream(pushbackInputStream) : pushbackInputStream;
            } catch (IOException e4) {
                e = e4;
                inputStream2 = pushbackInputStream;
                pushbackInputStream = null;
                gZIPInputStream = inputStream2;
                throw e;
            } catch (Throwable th8) {
                th = th8;
                inputStream2 = pushbackInputStream;
                pushbackInputStream = null;
                gZIPInputStream = inputStream2;
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (pushbackInputStream2 != null) {
                    pushbackInputStream2.close();
                }
                if (pushbackInputStream != null) {
                    pushbackInputStream.close();
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
            try {
                bArr = new byte[1024];
                while (true) {
                    int read = gZIPInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                if (a != null) {
                    a.a();
                }
                ap apVar = new ap();
                apVar.a = byteArrayOutputStream.toByteArray();
                apVar.b = headerFields;
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th42) {
                        dg.a(th42, "HttpUrlUtil", "parseResult");
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable th52) {
                        dg.a(th52, "HttpUrlUtil", "parseResult");
                    }
                }
                if (pushbackInputStream != null) {
                    try {
                        pushbackInputStream.close();
                    } catch (Throwable th9) {
                        dg.a(th9, "HttpUrlUtil", "parseResult");
                    }
                }
                if (gZIPInputStream != null) {
                    try {
                        gZIPInputStream.close();
                    } catch (Throwable th6222) {
                        dg.a(th6222, "HttpUrlUtil", "parseResult");
                    }
                }
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect();
                    } catch (Throwable th62222) {
                        dg.a(th62222, "HttpUrlUtil", "parseResult");
                    }
                }
                return apVar;
            } catch (IOException e5) {
                e = e5;
                inputStream2 = pushbackInputStream;
                pushbackInputStream = gZIPInputStream;
                gZIPInputStream = inputStream2;
                throw e;
            } catch (Throwable th10) {
                th = th10;
                inputStream2 = pushbackInputStream;
                pushbackInputStream = gZIPInputStream;
                gZIPInputStream = inputStream2;
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (pushbackInputStream2 != null) {
                    pushbackInputStream2.close();
                }
                if (pushbackInputStream != null) {
                    pushbackInputStream.close();
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (IOException e6) {
            e = e6;
            pushbackInputStream = null;
            inputStream = null;
            byteArrayOutputStream = null;
            throw e;
        } catch (Throwable th11) {
            th = th11;
            pushbackInputStream = null;
            inputStream = null;
            byteArrayOutputStream = null;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (pushbackInputStream2 != null) {
                pushbackInputStream2.close();
            }
            if (pushbackInputStream != null) {
                pushbackInputStream.close();
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    static String a(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            if (str2 == null) {
                str2 = "";
            }
            if (stringBuilder.length() > 0) {
                stringBuilder.append(a.b);
            }
            stringBuilder.append(URLEncoder.encode(str));
            stringBuilder.append("=");
            stringBuilder.append(URLEncoder.encode(str2));
        }
        return stringBuilder.toString();
    }

    public static void a(an anVar) {
        a = anVar;
    }

    private void a(Map<String, String> map, HttpURLConnection httpURLConnection) {
        if (map != null) {
            for (String str : map.keySet()) {
                httpURLConnection.addRequestProperty(str, (String) map.get(str));
            }
        }
        try {
            httpURLConnection.addRequestProperty("csid", UUID.randomUUID().toString().replaceAll("-", "").toLowerCase());
        } catch (Throwable th) {
            dg.a(th, "HttpUrlUtil", "addHeaders");
        }
        httpURLConnection.setConnectTimeout(this.b);
        httpURLConnection.setReadTimeout(this.c);
    }

    ap a(String str, Map<String, String> map, byte[] bArr) throws ct {
        try {
            HttpURLConnection a = a(str, (Map) map, true);
            if (bArr != null && bArr.length > 0) {
                DataOutputStream dataOutputStream = new DataOutputStream(a.getOutputStream());
                dataOutputStream.write(bArr);
                dataOutputStream.close();
            }
            a.connect();
            return a(a);
        } catch (ConnectException e) {
            e.printStackTrace();
            throw new ct(AMapException.ERROR_CONNECTION);
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
            throw new ct(AMapException.ERROR_URL);
        } catch (UnknownHostException e3) {
            e3.printStackTrace();
            throw new ct(AMapException.ERROR_UNKNOW_HOST);
        } catch (SocketException e4) {
            e4.printStackTrace();
            throw new ct(AMapException.ERROR_SOCKET);
        } catch (SocketTimeoutException e5) {
            e5.printStackTrace();
            throw new ct(AMapException.ERROR_SOCKE_TIME_OUT);
        } catch (InterruptedIOException e6) {
            throw new ct(AMapException.ERROR_UNKNOWN);
        } catch (IOException e7) {
            e7.printStackTrace();
            throw new ct(AMapException.ERROR_IO);
        } catch (Throwable th) {
            dg.a(th, "HttpUrlUtil", "makePostReqeust");
            ct ctVar = new ct(AMapException.ERROR_UNKNOWN);
        }
    }

    HttpURLConnection a(String str, Map<String, String> map, boolean z) throws IOException {
        HttpURLConnection httpURLConnection;
        cx.a();
        URL url = new URL(str);
        if (this.f != null) {
            URLConnection openConnection = url.openConnection(this.f);
        } else {
            httpURLConnection = (HttpURLConnection) url.openConnection();
        }
        if (this.d) {
            httpURLConnection = (HttpsURLConnection) openConnection;
            ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(this.e.getSocketFactory());
            ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(this.j);
        } else {
            httpURLConnection = (HttpURLConnection) openConnection;
        }
        if (VERSION.SDK != null && VERSION.SDK_INT > 13) {
            httpURLConnection.setRequestProperty(d.ck, "close");
        }
        a(map, httpURLConnection);
        if (z) {
            httpURLConnection.setRequestMethod(HttpMethods.POST);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
        } else {
            httpURLConnection.setRequestMethod(HttpMethods.GET);
            httpURLConnection.setDoInput(true);
        }
        return httpURLConnection;
    }

    void a(long j) {
        this.i = j;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void a(java.lang.String r11, java.util.Map<java.lang.String, java.lang.String> r12, java.util.Map<java.lang.String, java.lang.String> r13, com.e.al.a r14) {
        /*
        r10 = this;
        r1 = 0;
        r0 = 1;
        r8 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r3 = 0;
        r2 = 0;
        r4 = 0;
        if (r14 != 0) goto L_0x0038;
    L_0x0009:
        if (r1 == 0) goto L_0x000e;
    L_0x000b:
        r2.close();	 Catch:{ IOException -> 0x0014, Throwable -> 0x0020 }
    L_0x000e:
        if (r1 == 0) goto L_0x0013;
    L_0x0010:
        r4.disconnect();	 Catch:{ Throwable -> 0x002c }
    L_0x0013:
        return;
    L_0x0014:
        r0 = move-exception;
        r0.printStackTrace();
        r2 = "HttpUrlUtil";
        r3 = "makeDownloadGetRequest";
        com.e.dg.a(r0, r2, r3);
        goto L_0x000e;
    L_0x0020:
        r0 = move-exception;
        r0.printStackTrace();
        r2 = "HttpUrlUtil";
        r3 = "makeDownloadGetRequest";
        com.e.dg.a(r0, r2, r3);
        goto L_0x000e;
    L_0x002c:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "HttpUrlUtil";
        r2 = "makeDownloadGetRequest";
        com.e.dg.a(r0, r1, r2);
        goto L_0x0013;
    L_0x0038:
        r2 = a(r13);	 Catch:{ Throwable -> 0x01af, all -> 0x01a5 }
        r4 = new java.lang.StringBuffer;	 Catch:{ Throwable -> 0x01af, all -> 0x01a5 }
        r4.<init>();	 Catch:{ Throwable -> 0x01af, all -> 0x01a5 }
        r4.append(r11);	 Catch:{ Throwable -> 0x01af, all -> 0x01a5 }
        if (r2 == 0) goto L_0x004f;
    L_0x0046:
        r5 = "?";
        r5 = r4.append(r5);	 Catch:{ Throwable -> 0x01af, all -> 0x01a5 }
        r5.append(r2);	 Catch:{ Throwable -> 0x01af, all -> 0x01a5 }
    L_0x004f:
        r2 = r4.toString();	 Catch:{ Throwable -> 0x01af, all -> 0x01a5 }
        r4 = 0;
        r2 = r10.a(r2, r12, r4);	 Catch:{ Throwable -> 0x01af, all -> 0x01a5 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x01b3, all -> 0x0120 }
        r4.<init>();	 Catch:{ Throwable -> 0x01b3, all -> 0x0120 }
        r5 = "bytes=";
        r4 = r4.append(r5);	 Catch:{ Throwable -> 0x01b3, all -> 0x0120 }
        r6 = r10.i;	 Catch:{ Throwable -> 0x01b3, all -> 0x0120 }
        r4 = r4.append(r6);	 Catch:{ Throwable -> 0x01b3, all -> 0x0120 }
        r5 = "-";
        r4 = r4.append(r5);	 Catch:{ Throwable -> 0x01b3, all -> 0x0120 }
        r4 = r4.toString();	 Catch:{ Throwable -> 0x01b3, all -> 0x0120 }
        r5 = "RANGE";
        r2.setRequestProperty(r5, r4);	 Catch:{ Throwable -> 0x01b3, all -> 0x0120 }
        r2.connect();	 Catch:{ Throwable -> 0x01b3, all -> 0x0120 }
        r5 = r2.getResponseCode();	 Catch:{ Throwable -> 0x01b3, all -> 0x0120 }
        r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r5 == r4) goto L_0x010d;
    L_0x0083:
        r4 = r0;
    L_0x0084:
        r6 = 206; // 0xce float:2.89E-43 double:1.02E-321;
        if (r5 == r6) goto L_0x0110;
    L_0x0088:
        r0 = r0 & r4;
        if (r0 == 0) goto L_0x00b4;
    L_0x008b:
        r0 = new com.e.ct;	 Catch:{ Throwable -> 0x01b3, all -> 0x0120 }
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x01b3, all -> 0x0120 }
        r3.<init>();	 Catch:{ Throwable -> 0x01b3, all -> 0x0120 }
        r4 = "网络异常原因：";
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x01b3, all -> 0x0120 }
        r4 = r2.getResponseMessage();	 Catch:{ Throwable -> 0x01b3, all -> 0x0120 }
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x01b3, all -> 0x0120 }
        r4 = " 网络异常状态码：";
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x01b3, all -> 0x0120 }
        r3 = r3.append(r5);	 Catch:{ Throwable -> 0x01b3, all -> 0x0120 }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x01b3, all -> 0x0120 }
        r0.<init>(r3);	 Catch:{ Throwable -> 0x01b3, all -> 0x0120 }
        r14.a(r0);	 Catch:{ Throwable -> 0x01b3, all -> 0x0120 }
    L_0x00b4:
        r1 = r2.getInputStream();	 Catch:{ Throwable -> 0x01b3, all -> 0x0120 }
        r0 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r0 = new byte[r0];	 Catch:{ Throwable -> 0x00ed, all -> 0x0120 }
    L_0x00bc:
        r3 = java.lang.Thread.interrupted();	 Catch:{ Throwable -> 0x00ed, all -> 0x0120 }
        if (r3 != 0) goto L_0x012c;
    L_0x00c2:
        r3 = r10.g;	 Catch:{ Throwable -> 0x00ed, all -> 0x0120 }
        if (r3 != 0) goto L_0x012c;
    L_0x00c6:
        r3 = 0;
        r4 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r3 = r1.read(r0, r3, r4);	 Catch:{ Throwable -> 0x00ed, all -> 0x0120 }
        if (r3 <= 0) goto L_0x012c;
    L_0x00cf:
        r4 = r10.h;	 Catch:{ Throwable -> 0x00ed, all -> 0x0120 }
        r6 = -1;
        r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r4 == 0) goto L_0x00df;
    L_0x00d7:
        r4 = r10.i;	 Catch:{ Throwable -> 0x00ed, all -> 0x0120 }
        r6 = r10.h;	 Catch:{ Throwable -> 0x00ed, all -> 0x0120 }
        r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r4 >= 0) goto L_0x012c;
    L_0x00df:
        if (r3 != r8) goto L_0x0113;
    L_0x00e1:
        r4 = r10.i;	 Catch:{ Throwable -> 0x00ed, all -> 0x0120 }
        r14.a(r0, r4);	 Catch:{ Throwable -> 0x00ed, all -> 0x0120 }
    L_0x00e6:
        r4 = r10.i;	 Catch:{ Throwable -> 0x00ed, all -> 0x0120 }
        r6 = (long) r3;	 Catch:{ Throwable -> 0x00ed, all -> 0x0120 }
        r4 = r4 + r6;
        r10.i = r4;	 Catch:{ Throwable -> 0x00ed, all -> 0x0120 }
        goto L_0x00bc;
    L_0x00ed:
        r0 = move-exception;
        r9 = r2;
        r2 = r1;
        r1 = r9;
    L_0x00f1:
        r14.a(r0);	 Catch:{ all -> 0x01a9 }
        if (r2 == 0) goto L_0x00f9;
    L_0x00f6:
        r2.close();	 Catch:{ IOException -> 0x0168, Throwable -> 0x0174 }
    L_0x00f9:
        if (r1 == 0) goto L_0x0013;
    L_0x00fb:
        r1.disconnect();	 Catch:{ Throwable -> 0x0100 }
        goto L_0x0013;
    L_0x0100:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "HttpUrlUtil";
        r2 = "makeDownloadGetRequest";
        com.e.dg.a(r0, r1, r2);
        goto L_0x0013;
    L_0x010d:
        r4 = r3;
        goto L_0x0084;
    L_0x0110:
        r0 = r3;
        goto L_0x0088;
    L_0x0113:
        r4 = new byte[r3];	 Catch:{ Throwable -> 0x00ed, all -> 0x0120 }
        r5 = 0;
        r6 = 0;
        java.lang.System.arraycopy(r0, r5, r4, r6, r3);	 Catch:{ Throwable -> 0x00ed, all -> 0x0120 }
        r6 = r10.i;	 Catch:{ Throwable -> 0x00ed, all -> 0x0120 }
        r14.a(r4, r6);	 Catch:{ Throwable -> 0x00ed, all -> 0x0120 }
        goto L_0x00e6;
    L_0x0120:
        r0 = move-exception;
    L_0x0121:
        if (r1 == 0) goto L_0x0126;
    L_0x0123:
        r1.close();	 Catch:{ IOException -> 0x0181, Throwable -> 0x018d }
    L_0x0126:
        if (r2 == 0) goto L_0x012b;
    L_0x0128:
        r2.disconnect();	 Catch:{ Throwable -> 0x0199 }
    L_0x012b:
        throw r0;
    L_0x012c:
        r0 = r10.g;	 Catch:{ Throwable -> 0x00ed, all -> 0x0120 }
        if (r0 == 0) goto L_0x014c;
    L_0x0130:
        r14.c();	 Catch:{ Throwable -> 0x00ed, all -> 0x0120 }
    L_0x0133:
        if (r1 == 0) goto L_0x0138;
    L_0x0135:
        r1.close();	 Catch:{ IOException -> 0x0150, Throwable -> 0x015c }
    L_0x0138:
        if (r2 == 0) goto L_0x0013;
    L_0x013a:
        r2.disconnect();	 Catch:{ Throwable -> 0x013f }
        goto L_0x0013;
    L_0x013f:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "HttpUrlUtil";
        r2 = "makeDownloadGetRequest";
        com.e.dg.a(r0, r1, r2);
        goto L_0x0013;
    L_0x014c:
        r14.b();	 Catch:{ Throwable -> 0x00ed, all -> 0x0120 }
        goto L_0x0133;
    L_0x0150:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "HttpUrlUtil";
        r3 = "makeDownloadGetRequest";
        com.e.dg.a(r0, r1, r3);
        goto L_0x0138;
    L_0x015c:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "HttpUrlUtil";
        r3 = "makeDownloadGetRequest";
        com.e.dg.a(r0, r1, r3);
        goto L_0x0138;
    L_0x0168:
        r0 = move-exception;
        r0.printStackTrace();
        r2 = "HttpUrlUtil";
        r3 = "makeDownloadGetRequest";
        com.e.dg.a(r0, r2, r3);
        goto L_0x00f9;
    L_0x0174:
        r0 = move-exception;
        r0.printStackTrace();
        r2 = "HttpUrlUtil";
        r3 = "makeDownloadGetRequest";
        com.e.dg.a(r0, r2, r3);
        goto L_0x00f9;
    L_0x0181:
        r1 = move-exception;
        r1.printStackTrace();
        r3 = "HttpUrlUtil";
        r4 = "makeDownloadGetRequest";
        com.e.dg.a(r1, r3, r4);
        goto L_0x0126;
    L_0x018d:
        r1 = move-exception;
        r1.printStackTrace();
        r3 = "HttpUrlUtil";
        r4 = "makeDownloadGetRequest";
        com.e.dg.a(r1, r3, r4);
        goto L_0x0126;
    L_0x0199:
        r1 = move-exception;
        r1.printStackTrace();
        r2 = "HttpUrlUtil";
        r3 = "makeDownloadGetRequest";
        com.e.dg.a(r1, r2, r3);
        goto L_0x012b;
    L_0x01a5:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0121;
    L_0x01a9:
        r0 = move-exception;
        r9 = r1;
        r1 = r2;
        r2 = r9;
        goto L_0x0121;
    L_0x01af:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00f1;
    L_0x01b3:
        r0 = move-exception;
        r9 = r2;
        r2 = r1;
        r1 = r9;
        goto L_0x00f1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.am.a(java.lang.String, java.util.Map, java.util.Map, com.e.al$a):void");
    }

    void b(long j) {
        this.h = j;
    }
}
