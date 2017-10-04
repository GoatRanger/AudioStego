package com.google.api.client.http;

import com.google.api.client.util.StreamingContent;
import com.loopj.android.http.AsyncHttpClient;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

public class GZipEncoding implements HttpEncoding {
    public String getName() {
        return AsyncHttpClient.ENCODING_GZIP;
    }

    public void encode(StreamingContent streamingContent, OutputStream outputStream) throws IOException {
        OutputStream gZIPOutputStream = new GZIPOutputStream(new FilterOutputStream(outputStream) {
            public void close() throws IOException {
                try {
                    flush();
                } catch (IOException e) {
                }
            }
        });
        streamingContent.writeTo(gZIPOutputStream);
        gZIPOutputStream.close();
    }
}
