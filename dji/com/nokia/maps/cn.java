package com.nokia.maps;

import android.os.AsyncTask;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpStatusCodes;
import com.here.android.mpa.search.ErrorCode;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import dji.pilot.flyunlimit.a;
import dji.pilot.usercenter.protocol.d;
import it.sauronsoftware.ftp4j.FTPCodes;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;

public abstract class cn<Progress, Result> extends AsyncTask<String, Progress, co<Result>> {
    private static final String a = cn.class.getSimpleName();
    private static String e = "User-Agent";
    private static String f = AsyncHttpClient.HEADER_CONTENT_TYPE;
    private static String g = "Accept";
    private static String h = AsyncHttpClient.HEADER_ACCEPT_ENCODING;
    private static String i = "Accept-Language";
    private static String j = d.t;
    private long b;
    private HashMap<String, String> c;
    private final boolean d;
    private long k;

    protected abstract Result a(byte[] bArr) throws ak;

    protected abstract void a(ErrorCode errorCode);

    protected abstract void a(Result result);

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((co) obj);
    }

    public cn() {
        this(false);
    }

    public cn(boolean z) {
        this.k = System.currentTimeMillis();
        this.c = c();
        this.d = z;
    }

    protected co<Result> a(String... strArr) {
        co<Result> coVar = null;
        try {
            coVar = a(strArr[0], this.c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coVar;
    }

    protected void a(final co<Result> coVar) {
        bj.b(a, "onPostExecute", new Object[0]);
        bj.e(a(), "Networktime [ms]: " + (System.currentTimeMillis() - this.k), new Object[0]);
        if (!isCancelled()) {
            switch (coVar.a) {
                case OK:
                    new Thread(new Runnable(this) {
                        final /* synthetic */ cn b;

                        public void run() {
                            this.b.a(coVar.d);
                        }
                    }).start();
                    return;
                case ERROR:
                    bj.c(a, "Error code=%s,  reason=%s", new Object[]{coVar.b.toString(), coVar.c});
                    a(coVar.b);
                    return;
                default:
                    return;
            }
        }
    }

    private String a() {
        for (Class cls = getClass(); cls != null; cls = cls.getSuperclass()) {
            String simpleName = cls.getSimpleName();
            if (simpleName.length() > 0) {
                return simpleName;
            }
        }
        return a;
    }

    protected co<Result> a(String str, HashMap<String, String> hashMap) {
        bj.a(a, ">> url=%s", new Object[]{str});
        co<Result> coVar = new co();
        try {
            if (this.d) {
                b(coVar, str, hashMap);
            } else {
                a(coVar, str, hashMap);
            }
        } catch (IOException e) {
            bj.b(a, "IOException: %s", new Object[]{e.getLocalizedMessage()});
            coVar.b = ErrorCode.NETWORK_COMMUNICATION;
            coVar.a = cp.ERROR;
            coVar.c = e.getLocalizedMessage();
            coVar.a = cp.ERROR;
            bj.a(a, "<< url=%s", new Object[]{str});
            return coVar;
        } catch (SecurityException e2) {
            bj.b(a, "SecurityException: %s", new Object[]{e2.getLocalizedMessage()});
            coVar.b = ErrorCode.NETWORK_COMMUNICATION;
            coVar.a = cp.ERROR;
            coVar.c = e2.getLocalizedMessage();
            coVar.a = cp.ERROR;
            bj.a(a, "<< url=%s", new Object[]{str});
            return coVar;
        } catch (ak e3) {
            bj.b(a, "ContentException: %s", new Object[]{e3.getLocalizedMessage()});
            coVar.b = ErrorCode.NO_CONTENT;
            coVar.a = cp.ERROR;
            bj.a(a, "<< url=%s", new Object[]{str});
            return coVar;
        } catch (Exception e4) {
            bj.c(a, "Failed for unknown reason.  Exception: %s", new Object[]{e4.getLocalizedMessage()});
            coVar.b = ErrorCode.UNKNOWN;
            coVar.a = cp.ERROR;
            bj.a(a, "<< url=%s", new Object[]{str});
            return coVar;
        }
        return coVar;
    }

    private void a(HttpURLConnection httpURLConnection, HashMap<String, String> hashMap) {
        httpURLConnection.setAllowUserInteraction(false);
        httpURLConnection.setInstanceFollowRedirects(true);
        if (hashMap != null) {
            for (Entry entry : hashMap.entrySet()) {
                String str = (String) entry.getKey();
                if (str.compareTo(e) == 0) {
                    httpURLConnection.setRequestProperty(str, (String) entry.getValue());
                } else {
                    httpURLConnection.addRequestProperty(str, (String) entry.getValue());
                }
            }
        }
        httpURLConnection.addRequestProperty(g, RequestParams.APPLICATION_JSON);
        httpURLConnection.addRequestProperty(h, AsyncHttpClient.ENCODING_GZIP);
        httpURLConnection.addRequestProperty(i, Locale.getDefault().getLanguage());
    }

    private void a(co<Result> coVar, String str, HashMap<String, String> hashMap) throws ak, IOException {
        Throwable th;
        HttpURLConnection httpURLConnection = null;
        try {
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection2.setRequestMethod(HttpMethods.GET);
                a(httpURLConnection2, (HashMap) hashMap);
                b();
                b(httpURLConnection2);
                if (isCancelled()) {
                    coVar.a = cp.CANCELED;
                    httpURLConnection2.disconnect();
                    if (httpURLConnection2 != null) {
                        try {
                            httpURLConnection2.disconnect();
                            return;
                        } catch (Exception e) {
                            bj.c(a, "error disconnecting: %s", new Object[]{e.getLocalizedMessage()});
                            return;
                        }
                    }
                    return;
                }
                httpURLConnection2.connect();
                b(httpURLConnection2);
                a(httpURLConnection2, (co) coVar);
                if (httpURLConnection2 != null) {
                    try {
                        httpURLConnection2.disconnect();
                    } catch (Exception e2) {
                        bj.c(a, "error disconnecting: %s", new Object[]{e2.getLocalizedMessage()});
                    }
                }
            } catch (Throwable th2) {
                Throwable th3 = th2;
                httpURLConnection = httpURLConnection2;
                th = th3;
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect();
                    } catch (Exception e3) {
                        bj.c(a, "error disconnecting: %s", new Object[]{e3.getLocalizedMessage()});
                    }
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    private void b(co<Result> coVar, String str, HashMap<String, String> hashMap) throws ak, IOException {
        String str2;
        Throwable th;
        HttpURLConnection httpURLConnection = null;
        int indexOf = str.indexOf(63);
        if (indexOf < 0 || indexOf == str.length() - 1) {
            str2 = null;
        } else {
            str2 = str.substring(0, indexOf);
            String substring = str.substring(indexOf + 1);
            str = str2;
            str2 = substring;
        }
        try {
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection2.setRequestMethod(HttpMethods.POST);
                httpURLConnection2.setDoOutput(true);
                a(httpURLConnection2, (HashMap) hashMap);
                httpURLConnection2.setRequestProperty(f, "application/x-www-form-urlencoded; charset=utf-8");
                b();
                b(httpURLConnection2);
                if (isCancelled()) {
                    coVar.a = cp.CANCELED;
                    httpURLConnection2.disconnect();
                    if (httpURLConnection2 != null) {
                        try {
                            httpURLConnection2.disconnect();
                            return;
                        } catch (Exception e) {
                            bj.c(a, "error disconnecting: %s", new Object[]{e.getLocalizedMessage()});
                            return;
                        }
                    }
                    return;
                }
                if (str2 != null) {
                    OutputStream outputStream = httpURLConnection2.getOutputStream();
                    outputStream.write(str2.getBytes(Charset.forName("UTF-8")));
                    outputStream.close();
                } else {
                    httpURLConnection2.connect();
                }
                b(httpURLConnection2);
                a(httpURLConnection2, (co) coVar);
                if (httpURLConnection2 != null) {
                    try {
                        httpURLConnection2.disconnect();
                    } catch (Exception e2) {
                        bj.c(a, "error disconnecting: %s", new Object[]{e2.getLocalizedMessage()});
                    }
                }
            } catch (Throwable th2) {
                Throwable th3 = th2;
                httpURLConnection = httpURLConnection2;
                th = th3;
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect();
                    } catch (Exception e3) {
                        bj.c(a, "error disconnecting: %s", new Object[]{e3.getLocalizedMessage()});
                    }
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    private void a(HttpURLConnection httpURLConnection, co<Result> coVar) throws IOException, ak {
        int responseCode = httpURLConnection.getResponseCode();
        b(httpURLConnection);
        if (responseCode == 200) {
            InputStream inputStream = null;
            try {
                if (AsyncHttpClient.ENCODING_GZIP.equals(httpURLConnection.getHeaderField(AsyncHttpClient.HEADER_CONTENT_ENCODING))) {
                    inputStream = new BufferedInputStream(new GZIPInputStream(httpURLConnection.getInputStream()));
                } else {
                    inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                }
                a((co) coVar, inputStream);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                        bj.c(a, "error closing: %s", new Object[]{e.getLocalizedMessage()});
                    }
                }
            } catch (Throwable th) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        bj.c(a, "error closing: %s", new Object[]{e2.getLocalizedMessage()});
                    }
                }
            }
        } else {
            String str;
            bj.c(a, "Failed loading from: %s", new Object[]{httpURLConnection.getURL()});
            bj.c(a, "Response code: %s", new Object[]{Integer.valueOf(responseCode)});
            bj.c(a, "Response message: %s", new Object[]{httpURLConnection.getResponseMessage()});
            String str2 = a;
            String str3 = "Response: %s";
            Object[] objArr = new Object[1];
            if (AsyncHttpClient.ENCODING_GZIP.equals(httpURLConnection.getHeaderField(AsyncHttpClient.HEADER_CONTENT_ENCODING))) {
                str = new String(a(new GZIPInputStream(httpURLConnection.getErrorStream())), Charset.forName("UTF-8"));
            } else {
                str = new String(a(httpURLConnection.getErrorStream()), Charset.forName("UTF-8"));
            }
            objArr[0] = str;
            bj.c(str2, str3, objArr);
            bj.c(a, "failed request: %s", new Object[]{a(httpURLConnection)});
            coVar.a = cp.ERROR;
            coVar.b = a(responseCode);
            coVar.c = httpURLConnection.getResponseMessage();
        }
    }

    private static String a(HttpURLConnection httpURLConnection) {
        StringBuilder stringBuilder = new StringBuilder("curl");
        try {
            Map headerFields = httpURLConnection.getHeaderFields();
            if (headerFields != null) {
                for (Entry entry : headerFields.entrySet()) {
                    String str = (String) entry.getKey();
                    if (!"Transfer-Encoding".equals(str)) {
                        for (String str2 : (List) entry.getValue()) {
                            stringBuilder.append(" --header '");
                            stringBuilder.append(str);
                            stringBuilder.append(": ");
                            stringBuilder.append(str2);
                            stringBuilder.append("'");
                        }
                    }
                }
            }
            stringBuilder.append(" '");
            stringBuilder.append(httpURLConnection.getURL().toString());
            stringBuilder.append("'");
            return stringBuilder.toString();
        } catch (Exception e) {
            bj.c(a, "Unable to construct a CURL command.  Exception: %s", new Object[]{e.getLocalizedMessage()});
            return null;
        }
    }

    private void b() {
        this.b = System.currentTimeMillis();
    }

    private void b(HttpURLConnection httpURLConnection) {
        int max = Math.max(1, (int) ((30000 + this.b) - System.currentTimeMillis()));
        httpURLConnection.setReadTimeout(max);
        httpURLConnection.setConnectTimeout(max);
    }

    private void a(co<Result> coVar, InputStream inputStream) throws IOException, ak {
        Object a = a(a(inputStream));
        if (a == null) {
            coVar.a = cp.ERROR;
            coVar.b = ErrorCode.NO_CONTENT;
            return;
        }
        coVar.a = cp.OK;
        coVar.d = a;
    }

    private byte[] a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr, 0, 1024);
            if (read != -1 && !isCancelled()) {
                byteArrayOutputStream.write(bArr, 0, read);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    private HashMap<String, String> c() {
        String str = "";
        ApplicationContext b = ApplicationContext.b();
        if (b.f().length() > 0 && ConnectionInfoImpl.getApplicationVersion().length() > 0) {
            str = str + b.f() + j + ConnectionInfoImpl.getApplicationVersion();
            if (ConnectionInfoImpl.getClientSDKName().length() > 0 && ConnectionInfoImpl.getClientSDKVersion().length() > 0) {
                str = str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + ConnectionInfoImpl.getClientSDKName() + j + ConnectionInfoImpl.getClientSDKVersion();
            }
            if (ConnectionInfoImpl.getPlatformName().length() <= 0 || ConnectionInfoImpl.getPlatformVersion().length() <= 0) {
                str = str + " (";
            } else {
                str = str + " (" + ConnectionInfoImpl.getPlatformName() + j + ConnectionInfoImpl.getPlatformVersion();
            }
            str = ConnectionInfoImpl.getDeviceName().length() > 0 ? (ConnectionInfoImpl.getPlatformName().length() <= 0 || ConnectionInfoImpl.getPlatformVersion().length() <= 0) ? str + ConnectionInfoImpl.getDeviceName() + ") " : str + "; " + ConnectionInfoImpl.getDeviceName() + ") " : str + ")";
        }
        HashMap<String, String> hashMap = new HashMap();
        if (!str.isEmpty()) {
            hashMap.put(e, str);
        }
        return hashMap;
    }

    protected void a(Exception exception) {
        if (bj.a() != bk.NONE) {
            bj.c(exception.getClass().getSimpleName(), "%s", new Object[]{exception});
            exception.printStackTrace();
        }
    }

    private ErrorCode a(int i) {
        if (i == 200) {
            return ErrorCode.NONE;
        }
        if (i == dji.pilot.usercenter.f.d.y) {
            return ErrorCode.CREATED;
        }
        if (i == FTPCodes.SUPERFLOUS_COMMAND) {
            return ErrorCode.ACCEPTED;
        }
        if (i == 203) {
            return ErrorCode.HTTP;
        }
        if (i == HttpStatusCodes.STATUS_CODE_NO_CONTENT) {
            return ErrorCode.NO_CONTENT;
        }
        if (i == 205) {
            return ErrorCode.HTTP;
        }
        if (i == 206) {
            return ErrorCode.HTTP;
        }
        if (i == 300) {
            return ErrorCode.HTTP;
        }
        if (i == HttpStatusCodes.STATUS_CODE_MOVED_PERMANENTLY) {
            return ErrorCode.HTTP;
        }
        if (i == HttpStatusCodes.STATUS_CODE_FOUND) {
            return ErrorCode.HTTP;
        }
        if (i == HttpStatusCodes.STATUS_CODE_SEE_OTHER) {
            return ErrorCode.HTTP;
        }
        if (i == HttpStatusCodes.STATUS_CODE_NOT_MODIFIED) {
            return ErrorCode.HTTP;
        }
        if (i == d.b) {
            return ErrorCode.HTTP;
        }
        if (i == 400) {
            return ErrorCode.BAD_REQUEST;
        }
        if (i == a.y) {
            return ErrorCode.HTTP;
        }
        if (i == 401) {
            return ErrorCode.UNAUTHORIZED;
        }
        if (i == HttpStatusCodes.STATUS_CODE_FORBIDDEN) {
            return ErrorCode.FORBIDDEN;
        }
        if (i == 404) {
            return ErrorCode.NOT_FOUND;
        }
        if (i == a.A) {
            return ErrorCode.HTTP;
        }
        if (i == a.B) {
            return ErrorCode.HTTP;
        }
        if (i == a.C) {
            return ErrorCode.UNAUTHORIZED;
        }
        if (i == 408) {
            return ErrorCode.NETWORK_COMMUNICATION;
        }
        if (i == 409) {
            return ErrorCode.HTTP;
        }
        if (i == a.w) {
            return ErrorCode.HTTP;
        }
        if (i == 411) {
            return ErrorCode.HTTP;
        }
        if (i == 412) {
            return ErrorCode.HTTP;
        }
        if (i == 413) {
            return ErrorCode.HTTP;
        }
        if (i == 414) {
            return ErrorCode.HTTP;
        }
        if (i == 415) {
            return ErrorCode.HTTP;
        }
        if (i == 500) {
            return ErrorCode.SERVER_INTERNAL;
        }
        if (i == FTPCodes.SYNTAX_ERROR_IN_PARAMETERS) {
            return ErrorCode.HTTP;
        }
        if (i == 502) {
            return ErrorCode.HTTP;
        }
        if (i == 503) {
            return ErrorCode.SERVICE_UNAVAILABLE;
        }
        if (i == FTPCodes.COMMAND_PARAMETER_NOT_IMPLEMENTED) {
            return ErrorCode.NETWORK_COMMUNICATION;
        }
        if (i == 505) {
            return ErrorCode.HTTP;
        }
        return ErrorCode.HTTP;
    }
}
