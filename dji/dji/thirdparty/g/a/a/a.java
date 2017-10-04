package dji.thirdparty.g.a.a;

import dji.thirdparty.g.a.b;
import java.io.IOException;
import java.io.InputStream;

public abstract class a extends b {
    protected final String k;

    public abstract InputStream a() throws IOException;

    public abstract byte[] b() throws IOException;

    public abstract long c() throws IOException;

    public abstract byte[] c(int i, int i2) throws IOException;

    public abstract String d();

    public a(String str) {
        this.k = str;
    }

    public final InputStream a(int i) throws IOException {
        InputStream a = a();
        b(a, i);
        return a;
    }

    public final String e() {
        return this.k;
    }
}
