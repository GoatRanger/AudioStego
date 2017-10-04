package com.facebook.internal;

import android.content.Context;
import android.net.Uri;
import com.facebook.internal.n.d;
import com.facebook.y;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

class t {
    static final String a = t.class.getSimpleName();
    private static volatile n b;

    private static class a extends BufferedInputStream {
        HttpURLConnection a;

        a(InputStream inputStream, HttpURLConnection httpURLConnection) {
            super(inputStream, 8192);
            this.a = httpURLConnection;
        }

        public void close() throws IOException {
            super.close();
            ah.a(this.a);
        }
    }

    t() {
    }

    static synchronized n a(Context context) throws IOException {
        n nVar;
        synchronized (t.class) {
            if (b == null) {
                b = new n(a, new d());
            }
            nVar = b;
        }
        return nVar;
    }

    static InputStream a(Uri uri, Context context) {
        InputStream inputStream = null;
        if (uri != null && a(uri)) {
            try {
                inputStream = a(context).a(uri.toString());
            } catch (IOException e) {
                x.a(y.CACHE, 5, a, e.toString());
            }
        }
        return inputStream;
    }

    static InputStream a(Context context, HttpURLConnection httpURLConnection) throws IOException {
        InputStream inputStream = null;
        if (httpURLConnection.getResponseCode() == 200) {
            Uri parse = Uri.parse(httpURLConnection.getURL().toString());
            inputStream = httpURLConnection.getInputStream();
            try {
                if (a(parse)) {
                    inputStream = a(context).a(parse.toString(), new a(inputStream, httpURLConnection));
                }
            } catch (IOException e) {
            }
        }
        return inputStream;
    }

    private static boolean a(Uri uri) {
        if (uri != null) {
            String host = uri.getHost();
            if (host.endsWith("fbcdn.net")) {
                return true;
            }
            if (host.startsWith("fbcdn") && host.endsWith("akamaihd.net")) {
                return true;
            }
        }
        return false;
    }

    static void b(Context context) {
        try {
            a(context).b();
        } catch (IOException e) {
            x.a(y.CACHE, 5, a, "clearCache failed " + e.getMessage());
        }
    }
}
