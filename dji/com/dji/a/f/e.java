package com.dji.a.f;

import com.dji.a.e.a.a;
import com.dji.a.e.a.b;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.loopj.android.http.AsyncHttpClient;
import dji.pilot.phonecamera.h;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

public final class e {
    private int a = CommonStatusCodes.AUTH_API_INVALID_CREDENTIALS;
    private int b = CommonStatusCodes.AUTH_API_INVALID_CREDENTIALS;
    private Map<String, String> c;
    private byte[] d;
    private int e;

    public void a(int i) {
        this.a = i;
    }

    public void b(int i) {
        this.b = i;
    }

    public void a(Map<String, String> map) {
        this.c = map;
    }

    public void a(URL url, a aVar, byte[] bArr, b bVar) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(this.a);
        httpURLConnection.setReadTimeout(this.b);
        httpURLConnection.setRequestProperty(AsyncHttpClient.HEADER_CONTENT_TYPE, bVar.a());
        if (this.c != null) {
            for (Entry entry : this.c.entrySet()) {
                httpURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
            }
        }
        httpURLConnection.setRequestMethod(aVar.name());
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setFixedLengthStreamingMode(bArr.length);
        System.setProperty("http.keepAlive", h.e);
        httpURLConnection.connect();
        Closeable bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
        try {
            bufferedOutputStream.write(bArr);
            bufferedOutputStream.flush();
            this.e = httpURLConnection.getResponseCode();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr2 = new byte[1024];
            InputStream inputStream = httpURLConnection.getInputStream();
            while (true) {
                int read = inputStream.read(bArr2);
                if (read <= 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr2, 0, read);
            }
            this.d = byteArrayOutputStream.toByteArray();
            httpURLConnection.disconnect();
            if (com.dji.a.a.b) {
                com.dji.a.a.c.a(com.dji.a.a.a, "Connect to " + url.toString() + " res content = " + new String(this.d, "utf-8"));
                if (this.e == 200) {
                    com.dji.a.a.c.a(com.dji.a.a.a, "Connect to " + url.toString() + " success.");
                } else {
                    com.dji.a.a.c.a(com.dji.a.a.a, "Connect to " + url.toString() + " fail." + " resCode = " + this.e);
                }
            }
        } finally {
            f.a(bufferedOutputStream);
        }
    }

    public byte[] a() {
        return this.d;
    }

    public int b() {
        return this.e;
    }
}
