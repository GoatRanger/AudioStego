package com.a.a;

import android.content.Context;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

class d {
    final Context a;
    final e b;
    final String c;

    static abstract class a implements Closeable {
        protected final HttpURLConnection a;
        final InputStream b;
        final OutputStream c;

        a(HttpURLConnection httpURLConnection, InputStream inputStream, OutputStream outputStream) {
            if (httpURLConnection == null) {
                throw new IllegalArgumentException("connection == null");
            }
            this.a = httpURLConnection;
            this.b = inputStream;
            this.c = outputStream;
        }

        public void close() throws IOException {
            this.a.disconnect();
        }
    }

    static class b extends IOException {
        final int a;
        final String b;
        final String c;

        b(int i, String str, String str2) {
            super("HTTP " + i + ": " + str + ". Response: " + str2);
            this.a = i;
            this.b = str;
            this.c = str2;
        }
    }

    private static a a(HttpURLConnection httpURLConnection) throws IOException {
        return new a(httpURLConnection, null, httpURLConnection.getOutputStream()) {
            public void close() throws IOException {
                String b;
                try {
                    int responseCode = this.a.getResponseCode();
                    if (responseCode >= 300) {
                        b = com.a.a.a.b.b(this.a.getInputStream());
                        throw new b(responseCode, this.a.getResponseMessage(), b);
                    }
                    super.close();
                    this.c.close();
                } catch (IOException e) {
                    b = "Could not read response body for rejected message: " + e.toString();
                } catch (Throwable th) {
                    super.close();
                    this.c.close();
                }
            }
        };
    }

    private static a b(HttpURLConnection httpURLConnection) throws IOException {
        return new a(httpURLConnection, httpURLConnection.getInputStream(), null) {
            public void close() throws IOException {
                super.close();
                this.b.close();
            }
        };
    }

    d(Context context, String str, e eVar) {
        this.a = context;
        this.c = str;
        this.b = eVar;
    }

    public e a() {
        return this.b;
    }

    a b() throws IOException {
        return a(this.b.d(this.c));
    }

    a c() throws IOException {
        HttpURLConnection c = this.b.c(this.c);
        int responseCode = c.getResponseCode();
        if (responseCode == 200) {
            return b(c);
        }
        c.disconnect();
        throw new IOException("HTTP " + responseCode + ": " + c.getResponseMessage());
    }
}
