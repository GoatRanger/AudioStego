package dji.thirdparty.b.a.b;

import com.loopj.android.http.AsyncHttpClient;
import dji.thirdparty.b.ae;
import dji.thirdparty.b.t;
import dji.thirdparty.b.w;
import dji.thirdparty.c.e;

public final class k extends ae {
    private final t a;
    private final e b;

    public k(t tVar, e eVar) {
        this.a = tVar;
        this.b = eVar;
    }

    public w a() {
        String a = this.a.a(AsyncHttpClient.HEADER_CONTENT_TYPE);
        return a != null ? w.a(a) : null;
    }

    public long b() {
        return j.a(this.a);
    }

    public e c() {
        return this.b;
    }
}
