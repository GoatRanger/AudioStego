package dji.thirdparty.b;

import com.here.posclient.UpdateOptions;
import dji.thirdparty.b.a.j;
import dji.thirdparty.c.c;
import dji.thirdparty.c.e;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

public abstract class ae implements Closeable {
    private Reader a;

    public abstract w a();

    public abstract long b();

    public abstract e c();

    public final InputStream d() {
        return c().h();
    }

    public final byte[] e() throws IOException {
        long b = b();
        if (b > UpdateOptions.SOURCE_ANY) {
            throw new IOException("Cannot buffer entire body for content length: " + b);
        }
        Closeable c = c();
        try {
            byte[] x = c.x();
            if (b == -1 || b == ((long) x.length)) {
                return x;
            }
            throw new IOException("Content-Length and stream length disagree");
        } finally {
            j.a(c);
        }
    }

    public final Reader f() {
        Reader reader = this.a;
        if (reader != null) {
            return reader;
        }
        reader = new InputStreamReader(d(), h());
        this.a = reader;
        return reader;
    }

    public final String g() throws IOException {
        return new String(e(), h().name());
    }

    private Charset h() {
        w a = a();
        return a != null ? a.a(j.c) : j.c;
    }

    public void close() {
        j.a(c());
    }

    public static ae a(w wVar, String str) {
        Charset charset = j.c;
        if (wVar != null) {
            charset = wVar.c();
            if (charset == null) {
                charset = j.c;
                wVar = w.a(wVar + "; charset=utf-8");
            }
        }
        e a = new c().a(str, charset);
        return a(wVar, a.b(), a);
    }

    public static ae a(w wVar, byte[] bArr) {
        return a(wVar, (long) bArr.length, new c().c(bArr));
    }

    public static ae a(final w wVar, final long j, final e eVar) {
        if (eVar != null) {
            return new ae() {
                public w a() {
                    return wVar;
                }

                public long b() {
                    return j;
                }

                public e c() {
                    return eVar;
                }
            };
        }
        throw new NullPointerException("source == null");
    }
}
