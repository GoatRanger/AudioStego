package com.google.api.client.http.javanet;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.util.Preconditions;
import com.here.posclient.UpdateOptions;
import com.loopj.android.http.AsyncHttpClient;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

final class NetHttpRequest extends LowLevelHttpRequest {
    private final HttpURLConnection connection;

    NetHttpRequest(HttpURLConnection httpURLConnection) {
        this.connection = httpURLConnection;
        httpURLConnection.setInstanceFollowRedirects(false);
    }

    public void addHeader(String str, String str2) {
        this.connection.addRequestProperty(str, str2);
    }

    public void setTimeout(int i, int i2) {
        this.connection.setReadTimeout(i2);
        this.connection.setConnectTimeout(i);
    }

    public LowLevelHttpResponse execute() throws IOException {
        HttpURLConnection httpURLConnection = this.connection;
        if (getStreamingContent() != null) {
            String contentType = getContentType();
            if (contentType != null) {
                addHeader(AsyncHttpClient.HEADER_CONTENT_TYPE, contentType);
            }
            contentType = getContentEncoding();
            if (contentType != null) {
                addHeader(AsyncHttpClient.HEADER_CONTENT_ENCODING, contentType);
            }
            long contentLength = getContentLength();
            if (contentLength >= 0) {
                addHeader("Content-Length", Long.toString(contentLength));
            }
            String requestMethod = httpURLConnection.getRequestMethod();
            if (HttpMethods.POST.equals(requestMethod) || HttpMethods.PUT.equals(requestMethod)) {
                httpURLConnection.setDoOutput(true);
                if (contentLength < 0 || contentLength > UpdateOptions.SOURCE_ANY) {
                    httpURLConnection.setChunkedStreamingMode(0);
                } else {
                    httpURLConnection.setFixedLengthStreamingMode((int) contentLength);
                }
                OutputStream outputStream = httpURLConnection.getOutputStream();
                try {
                    getStreamingContent().writeTo(outputStream);
                } finally {
                    outputStream.close();
                }
            } else {
                Preconditions.checkArgument(contentLength == 0, "%s with non-zero content length is not supported", requestMethod);
            }
        }
        try {
            httpURLConnection.connect();
            return new NetHttpResponse(httpURLConnection);
        } catch (Throwable th) {
            httpURLConnection.disconnect();
        }
    }
}
