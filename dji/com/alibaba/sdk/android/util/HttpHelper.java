package com.alibaba.sdk.android.util;

import com.alibaba.sdk.android.b.a;
import com.alibaba.sdk.android.trace.AliSDKLogger;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.UrlEncodedParser;
import com.loopj.android.http.AsyncHttpClient;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicHeaderValueParser;

public class HttpHelper {
    private static final String a = HttpHelper.class.getSimpleName();

    public static String get(String str, Map<String, String> map) {
        return IOUtils.toString(get(str + "?" + encodeRequest(map)), "UTF-8");
    }

    public static InputStream get(String str) {
        try {
            HttpURLConnection a = a(str);
            a(a.getResponseCode());
            return a.getInputStream();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private static void a(int i) {
        if (i != 200) {
            throw new RuntimeException("http request exception, response code: " + i);
        }
    }

    private static HttpURLConnection a(String str) {
        try {
            HttpURLConnection httpURLConnection = HttpDNSUtils.getHttpURLConnection(str, a.a);
            httpURLConnection.setConnectTimeout(16000);
            return httpURLConnection;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static String post(Map<String, String> map, String str) {
        HttpURLConnection a;
        Throwable th;
        Closeable closeable = null;
        if (AliSDKLogger.isDebugEnabled()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("request ").append(str).append('\n');
            if (map == null || map.size() == 0) {
                stringBuilder.append("with no param");
            } else {
                for (Entry entry : map.entrySet()) {
                    stringBuilder.append((String) entry.getKey()).append('=').append((String) entry.getValue()).append('\n');
                }
            }
            AliSDKLogger.d(a, stringBuilder.toString());
        }
        try {
            byte[] bytes = encodeRequest(map).getBytes("UTF-8");
            a = a(str);
            try {
                a.setDoInput(true);
                a.setDoOutput(true);
                a.setRequestMethod(HttpMethods.POST);
                a.setUseCaches(false);
                a.setRequestProperty(AsyncHttpClient.HEADER_CONTENT_TYPE, UrlEncodedParser.CONTENT_TYPE);
                closeable = a.getOutputStream();
                closeable.write(bytes);
                closeable.flush();
                a(a.getResponseCode());
                String a2 = a(a.getInputStream(), getCharset(a.getContentType()));
                IOUtils.closeQuietly(closeable);
                if (a != null) {
                    try {
                        a.disconnect();
                    } catch (Exception e) {
                    }
                }
                return a2;
            } catch (Throwable th2) {
                th = th2;
                try {
                    throw new RuntimeException(th);
                } catch (Throwable th3) {
                    th = th3;
                    IOUtils.closeQuietly(closeable);
                    if (a != null) {
                        try {
                            a.disconnect();
                        } catch (Exception e2) {
                        }
                    }
                    throw th;
                }
            }
        } catch (Throwable th4) {
            th = th4;
            a = null;
            IOUtils.closeQuietly(closeable);
            if (a != null) {
                a.disconnect();
            }
            throw th;
        }
    }

    public static String encodeRequest(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = null;
        for (Entry entry : map.entrySet()) {
            Object obj2;
            if (obj != null) {
                try {
                    stringBuilder.append(com.alipay.sdk.h.a.b);
                    obj2 = obj;
                } catch (Throwable e) {
                    AliSDKLogger.e(a, e.getMessage(), e);
                    throw new RuntimeException(e);
                }
            }
            obj2 = 1;
            stringBuilder.append((String) entry.getKey()).append("=").append(URLEncoder.encode((String) entry.getValue(), "UTF-8"));
            obj = obj2;
        }
        return stringBuilder.toString();
    }

    private static String a(InputStream inputStream, String str) throws UnsupportedEncodingException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    return new String(byteArrayOutputStream.toByteArray(), str);
                }
                byteArrayOutputStream.write(bArr, 0, read);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String getCharset(String str) {
        String str2 = null;
        HeaderElement[] parseElements = BasicHeaderValueParser.parseElements(str, null);
        if (parseElements.length > 0) {
            NameValuePair parameterByName = parseElements[0].getParameterByName("charset");
            if (parameterByName != null) {
                str2 = parameterByName.getValue();
            }
        }
        return str2 == null ? "UTF-8" : str2;
    }
}
