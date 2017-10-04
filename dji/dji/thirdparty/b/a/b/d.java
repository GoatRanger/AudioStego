package dji.thirdparty.b.a.b;

import com.alipay.sdk.j.i;
import com.here.odnp.debug.DebugFile;
import dji.thirdparty.b.ab;
import dji.thirdparty.b.ad;
import dji.thirdparty.b.ae;
import dji.thirdparty.b.t;
import dji.thirdparty.c.j;
import dji.thirdparty.c.p;
import dji.thirdparty.c.v;
import dji.thirdparty.c.w;
import dji.thirdparty.c.x;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;

public final class d implements i {
    private static final int b = 0;
    private static final int c = 1;
    private static final int d = 2;
    private static final int e = 3;
    private static final int f = 4;
    private static final int g = 5;
    private static final int h = 6;
    private final r i;
    private final dji.thirdparty.c.e j;
    private final dji.thirdparty.c.d k;
    private g l;
    private int m = 0;

    private abstract class a implements w {
        protected final j a;
        protected boolean b;
        final /* synthetic */ d c;

        private a(d dVar) {
            this.c = dVar;
            this.a = new j(this.c.j.a());
        }

        public x a() {
            return this.a;
        }

        protected final void a(boolean z) throws IOException {
            if (this.c.m != 6) {
                if (this.c.m != 5) {
                    throw new IllegalStateException("state: " + this.c.m);
                }
                this.c.a(this.a);
                this.c.m = 6;
                if (this.c.i != null) {
                    this.c.i.a(!z, this.c);
                }
            }
        }
    }

    private final class b implements v {
        final /* synthetic */ d a;
        private final j b;
        private boolean c;

        private b(d dVar) {
            this.a = dVar;
            this.b = new j(this.a.k.a());
        }

        public x a() {
            return this.b;
        }

        public void a_(dji.thirdparty.c.c cVar, long j) throws IOException {
            if (this.c) {
                throw new IllegalStateException("closed");
            } else if (j != 0) {
                this.a.k.m(j);
                this.a.k.b(DebugFile.EOL);
                this.a.k.a_(cVar, j);
                this.a.k.b(DebugFile.EOL);
            }
        }

        public synchronized void flush() throws IOException {
            if (!this.c) {
                this.a.k.flush();
            }
        }

        public synchronized void close() throws IOException {
            if (!this.c) {
                this.c = true;
                this.a.k.b("0\r\n\r\n");
                this.a.a(this.b);
                this.a.m = 3;
            }
        }
    }

    private class c extends a {
        private static final long e = -1;
        final /* synthetic */ d d;
        private long f = -1;
        private boolean g = true;
        private final g h;

        c(d dVar, g gVar) throws IOException {
            this.d = dVar;
            super();
            this.h = gVar;
        }

        public long a(dji.thirdparty.c.c cVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (this.b) {
                throw new IllegalStateException("closed");
            } else if (!this.g) {
                return -1;
            } else {
                if (this.f == 0 || this.f == -1) {
                    b();
                    if (!this.g) {
                        return -1;
                    }
                }
                long a = this.d.j.a(cVar, Math.min(j, this.f));
                if (a == -1) {
                    a(false);
                    throw new ProtocolException("unexpected end of stream");
                }
                this.f -= a;
                return a;
            }
        }

        private void b() throws IOException {
            if (this.f != -1) {
                this.d.j.v();
            }
            try {
                this.f = this.d.j.r();
                String trim = this.d.j.v().trim();
                if (this.f < 0 || !(trim.isEmpty() || trim.startsWith(i.b))) {
                    throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.f + trim + "\"");
                } else if (this.f == 0) {
                    this.g = false;
                    this.h.a(this.d.f());
                    a(true);
                }
            } catch (NumberFormatException e) {
                throw new ProtocolException(e.getMessage());
            }
        }

        public void close() throws IOException {
            if (!this.b) {
                if (this.g && !dji.thirdparty.b.a.j.a((w) this, 100, TimeUnit.MILLISECONDS)) {
                    a(false);
                }
                this.b = true;
            }
        }
    }

    private final class d implements v {
        final /* synthetic */ d a;
        private final j b;
        private boolean c;
        private long d;

        private d(d dVar, long j) {
            this.a = dVar;
            this.b = new j(this.a.k.a());
            this.d = j;
        }

        public x a() {
            return this.b;
        }

        public void a_(dji.thirdparty.c.c cVar, long j) throws IOException {
            if (this.c) {
                throw new IllegalStateException("closed");
            }
            dji.thirdparty.b.a.j.a(cVar.b(), 0, j);
            if (j > this.d) {
                throw new ProtocolException("expected " + this.d + " bytes but received " + j);
            }
            this.a.k.a_(cVar, j);
            this.d -= j;
        }

        public void flush() throws IOException {
            if (!this.c) {
                this.a.k.flush();
            }
        }

        public void close() throws IOException {
            if (!this.c) {
                this.c = true;
                if (this.d > 0) {
                    throw new ProtocolException("unexpected end of stream");
                }
                this.a.a(this.b);
                this.a.m = 3;
            }
        }
    }

    private class e extends a {
        final /* synthetic */ d d;
        private long e;

        public e(d dVar, long j) throws IOException {
            this.d = dVar;
            super();
            this.e = j;
            if (this.e == 0) {
                a(true);
            }
        }

        public long a(dji.thirdparty.c.c cVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (this.b) {
                throw new IllegalStateException("closed");
            } else if (this.e == 0) {
                return -1;
            } else {
                long a = this.d.j.a(cVar, Math.min(this.e, j));
                if (a == -1) {
                    a(false);
                    throw new ProtocolException("unexpected end of stream");
                }
                this.e -= a;
                if (this.e == 0) {
                    a(true);
                }
                return a;
            }
        }

        public void close() throws IOException {
            if (!this.b) {
                if (!(this.e == 0 || dji.thirdparty.b.a.j.a((w) this, 100, TimeUnit.MILLISECONDS))) {
                    a(false);
                }
                this.b = true;
            }
        }
    }

    private class f extends a {
        final /* synthetic */ d d;
        private boolean e;

        private f(d dVar) {
            this.d = dVar;
            super();
        }

        public long a(dji.thirdparty.c.c cVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (this.b) {
                throw new IllegalStateException("closed");
            } else if (this.e) {
                return -1;
            } else {
                long a = this.d.j.a(cVar, j);
                if (a != -1) {
                    return a;
                }
                this.e = true;
                a(true);
                return -1;
            }
        }

        public void close() throws IOException {
            if (!this.b) {
                if (!this.e) {
                    a(false);
                }
                this.b = true;
            }
        }
    }

    public d(r rVar, dji.thirdparty.c.e eVar, dji.thirdparty.c.d dVar) {
        this.i = rVar;
        this.j = eVar;
        this.k = dVar;
    }

    public void a(g gVar) {
        this.l = gVar;
    }

    public v a(ab abVar, long j) throws IOException {
        if ("chunked".equalsIgnoreCase(abVar.a("Transfer-Encoding"))) {
            return g();
        }
        if (j != -1) {
            return a(j);
        }
        throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
    }

    public void a() {
        dji.thirdparty.b.a.c.b b = this.i.b();
        if (b != null) {
            b.f();
        }
    }

    public void a(ab abVar) throws IOException {
        this.l.b();
        a(abVar.c(), m.a(abVar, this.l.h().a().b().type()));
    }

    public dji.thirdparty.b.ad.a b() throws IOException {
        return e();
    }

    public ae a(ad adVar) throws IOException {
        return new k(adVar.g(), p.a(b(adVar)));
    }

    private w b(ad adVar) throws IOException {
        if (!g.a(adVar)) {
            return b(0);
        }
        if ("chunked".equalsIgnoreCase(adVar.b("Transfer-Encoding"))) {
            return b(this.l);
        }
        long a = j.a(adVar);
        if (a != -1) {
            return b(a);
        }
        return h();
    }

    public boolean c() {
        return this.m == 6;
    }

    public void d() throws IOException {
        this.k.flush();
    }

    public void a(t tVar, String str) throws IOException {
        if (this.m != 0) {
            throw new IllegalStateException("state: " + this.m);
        }
        this.k.b(str).b(DebugFile.EOL);
        int a = tVar.a();
        for (int i = 0; i < a; i++) {
            this.k.b(tVar.a(i)).b(": ").b(tVar.b(i)).b(DebugFile.EOL);
        }
        this.k.b(DebugFile.EOL);
        this.m = 1;
    }

    public dji.thirdparty.b.ad.a e() throws IOException {
        if (this.m == 1 || this.m == 3) {
            dji.thirdparty.b.ad.a a;
            q a2;
            do {
                try {
                    a2 = q.a(this.j.v());
                    a = new dji.thirdparty.b.ad.a().a(a2.d).a(a2.e).a(a2.f).a(f());
                } catch (Throwable e) {
                    IOException iOException = new IOException("unexpected end of stream on " + this.i);
                    iOException.initCause(e);
                    throw iOException;
                }
            } while (a2.e == 100);
            this.m = 4;
            return a;
        }
        throw new IllegalStateException("state: " + this.m);
    }

    public t f() throws IOException {
        dji.thirdparty.b.t.a aVar = new dji.thirdparty.b.t.a();
        while (true) {
            String v = this.j.v();
            if (v.length() == 0) {
                return aVar.a();
            }
            dji.thirdparty.b.a.d.b.a(aVar, v);
        }
    }

    public v g() {
        if (this.m != 1) {
            throw new IllegalStateException("state: " + this.m);
        }
        this.m = 2;
        return new b();
    }

    public v a(long j) {
        if (this.m != 1) {
            throw new IllegalStateException("state: " + this.m);
        }
        this.m = 2;
        return new d(j);
    }

    public void a(n nVar) throws IOException {
        if (this.m != 1) {
            throw new IllegalStateException("state: " + this.m);
        }
        this.m = 3;
        nVar.a(this.k);
    }

    public w b(long j) throws IOException {
        if (this.m != 4) {
            throw new IllegalStateException("state: " + this.m);
        }
        this.m = 5;
        return new e(this, j);
    }

    public w b(g gVar) throws IOException {
        if (this.m != 4) {
            throw new IllegalStateException("state: " + this.m);
        }
        this.m = 5;
        return new c(this, gVar);
    }

    public w h() throws IOException {
        if (this.m != 4) {
            throw new IllegalStateException("state: " + this.m);
        } else if (this.i == null) {
            throw new IllegalStateException("streamAllocation == null");
        } else {
            this.m = 5;
            this.i.d();
            return new f();
        }
    }

    private void a(j jVar) {
        x a = jVar.a();
        jVar.a(x.b);
        a.f();
        a.m_();
    }
}
