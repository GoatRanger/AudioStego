package com.a.a;

import android.util.Base64;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class e {
    private String b(String str) {
        return "Basic " + Base64.encodeToString((str + ":").getBytes(), 2);
    }

    public boolean a() {
        return true;
    }

    public HttpURLConnection c(String str) throws IOException {
        HttpURLConnection a = a("https://cdn.segment.com/v1/projects/" + str + "/settings");
        a.setRequestProperty(AsyncHttpClient.HEADER_CONTENT_TYPE, RequestParams.APPLICATION_JSON);
        return a;
    }

    public HttpURLConnection d(String str) throws IOException {
        HttpURLConnection a = a("https://api.segment.io/v1/import");
        a.setRequestProperty(AsyncHttpClient.HEADER_CONTENT_TYPE, RequestParams.APPLICATION_JSON);
        a.setRequestProperty("Authorization", b(str));
        a.setDoOutput(true);
        a.setChunkedStreamingMode(0);
        return a;
    }

    protected HttpURLConnection a(String str) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setConnectTimeout(15000);
        httpURLConnection.setReadTimeout(20000);
        httpURLConnection.setDoInput(true);
        return httpURLConnection;
    }
}
