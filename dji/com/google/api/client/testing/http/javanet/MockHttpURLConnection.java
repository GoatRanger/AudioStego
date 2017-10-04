package com.google.api.client.testing.http.javanet;

import com.google.api.client.util.Beta;
import com.google.api.client.util.Preconditions;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Beta
public class MockHttpURLConnection extends HttpURLConnection {
    public static final byte[] ERROR_BUF = new byte[5];
    public static final byte[] INPUT_BUF = new byte[1];
    private boolean doOutputCalled;
    private InputStream errorStream = new ByteArrayInputStream(ERROR_BUF);
    private InputStream inputStream = new ByteArrayInputStream(INPUT_BUF);
    private OutputStream outputStream = new ByteArrayOutputStream(0);

    public MockHttpURLConnection(URL url) {
        super(url);
    }

    public void disconnect() {
    }

    public boolean usingProxy() {
        return false;
    }

    public void connect() throws IOException {
    }

    public int getResponseCode() throws IOException {
        return this.responseCode;
    }

    public void setDoOutput(boolean z) {
        this.doOutputCalled = true;
    }

    public OutputStream getOutputStream() throws IOException {
        if (this.outputStream != null) {
            return this.outputStream;
        }
        return super.getOutputStream();
    }

    public final boolean doOutputCalled() {
        return this.doOutputCalled;
    }

    public MockHttpURLConnection setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
        return this;
    }

    public MockHttpURLConnection setResponseCode(int i) {
        Preconditions.checkArgument(i >= -1);
        this.responseCode = i;
        return this;
    }

    public InputStream getInputStream() throws IOException {
        if (this.responseCode < 400) {
            return this.inputStream;
        }
        throw new IOException();
    }

    public InputStream getErrorStream() {
        return this.errorStream;
    }
}
