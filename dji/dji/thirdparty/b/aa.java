package dji.thirdparty.b;

import com.loopj.android.http.AsyncHttpClient;
import dji.thirdparty.b.a.b.g;
import dji.thirdparty.b.a.b.l;
import dji.thirdparty.b.a.b.o;
import dji.thirdparty.b.a.b.r;
import dji.thirdparty.b.a.d;
import dji.thirdparty.b.a.f;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.logging.Level;

final class aa implements e {
    volatile boolean a;
    ab b;
    g c;
    private final y d;
    private boolean e;

    class a implements dji.thirdparty.b.v.a {
        final /* synthetic */ aa a;
        private final int b;
        private final ab c;
        private final boolean d;

        a(aa aaVar, int i, ab abVar, boolean z) {
            this.a = aaVar;
            this.b = i;
            this.c = abVar;
            this.d = z;
        }

        public j b() {
            return null;
        }

        public ab a() {
            return this.c;
        }

        public ad a(ab abVar) throws IOException {
            if (this.b >= this.a.d.w().size()) {
                return this.a.a(abVar, this.d);
            }
            v vVar = (v) this.a.d.w().get(this.b);
            ad a = vVar.a(new a(this.a, this.b + 1, abVar, this.d));
            if (a != null) {
                return a;
            }
            throw new NullPointerException("application interceptor " + vVar + " returned null");
        }
    }

    final class b extends f {
        final /* synthetic */ aa a;
        private final f c;
        private final boolean d;

        private b(aa aaVar, f fVar, boolean z) {
            this.a = aaVar;
            super("OkHttp %s", aaVar.b.a().toString());
            this.c = fVar;
            this.d = z;
        }

        String a() {
            return this.a.b.a().i();
        }

        ab b() {
            return this.a.b;
        }

        Object c() {
            return this.a.b.e();
        }

        void d() {
            this.a.c();
        }

        aa e() {
            return this.a;
        }

        protected void f() {
            IOException e;
            Object obj = 1;
            Object obj2 = null;
            try {
                ad a = this.a.a(this.d);
                if (this.a.a) {
                    try {
                        this.c.a(this.a, new IOException("Canceled"));
                    } catch (IOException e2) {
                        e = e2;
                        if (obj == null) {
                            this.c.a(this.a, e);
                        } else {
                            try {
                                d.a.log(Level.INFO, "Callback failure for " + this.a.g(), e);
                            } catch (Throwable th) {
                                this.a.d.t().b(this);
                            }
                        }
                        this.a.d.t().b(this);
                    }
                }
                this.c.a(this.a, a);
                this.a.d.t().b(this);
            } catch (IOException e3) {
                e = e3;
                obj = obj2;
                if (obj == null) {
                    d.a.log(Level.INFO, "Callback failure for " + this.a.g(), e);
                } else {
                    this.c.a(this.a, e);
                }
                this.a.d.t().b(this);
            }
        }
    }

    protected aa(y yVar, ab abVar) {
        this.d = yVar;
        this.b = abVar;
    }

    public ab a() {
        return this.b;
    }

    public ad b() throws IOException {
        synchronized (this) {
            if (this.e) {
                throw new IllegalStateException("Already Executed");
            }
            this.e = true;
        }
        try {
            this.d.t().a(this);
            ad a = a(false);
            if (a != null) {
                return a;
            }
            throw new IOException("Canceled");
        } finally {
            this.d.t().a((e) this);
        }
    }

    Object f() {
        return this.b.e();
    }

    public void a(f fVar) {
        a(fVar, false);
    }

    void a(f fVar, boolean z) {
        synchronized (this) {
            if (this.e) {
                throw new IllegalStateException("Already Executed");
            }
            this.e = true;
        }
        this.d.t().a(new b(fVar, z));
    }

    public void c() {
        this.a = true;
        if (this.c != null) {
            this.c.j();
        }
    }

    public synchronized boolean d() {
        return this.e;
    }

    public boolean e() {
        return this.a;
    }

    private String g() {
        return (this.a ? "canceled call" : "call") + " to " + this.b.a().e("/...");
    }

    private ad a(boolean z) throws IOException {
        return new a(this, 0, this.b, z).a(this.b);
    }

    ad a(ab abVar, boolean z) throws IOException {
        ab d;
        g a;
        Object obj;
        Throwable th;
        ac d2 = abVar.d();
        if (d2 != null) {
            dji.thirdparty.b.ab.a f = abVar.f();
            w b = d2.b();
            if (b != null) {
                f.a(AsyncHttpClient.HEADER_CONTENT_TYPE, b.toString());
            }
            long c = d2.c();
            if (c != -1) {
                f.a("Content-Length", Long.toString(c));
                f.b("Transfer-Encoding");
            } else {
                f.a("Transfer-Encoding", "chunked");
                f.b("Content-Length");
            }
            d = f.d();
        } else {
            d = abVar;
        }
        this.c = new g(this.d, d, false, false, z, null, null, null);
        int i = 0;
        while (!this.a) {
            try {
                this.c.a();
                this.c.l();
                ad g = this.c.g();
                d = this.c.m();
                if (d == null) {
                    if (!z) {
                        this.c.i();
                    }
                    return g;
                }
                r k = this.c.k();
                int i2 = i + 1;
                if (i2 > 20) {
                    k.c();
                    throw new ProtocolException("Too many follow-up requests: " + i2);
                }
                if (!this.c.a(d.a())) {
                    k.c();
                    k = null;
                }
                this.c = new g(this.d, d, false, false, z, k, null, g);
                i = i2;
            } catch (l e) {
                throw e.a();
            } catch (o e2) {
                a = this.c.a(e2.a(), null);
                if (a != null) {
                    obj = null;
                    this.c = a;
                } else {
                    throw e2.a();
                }
            } catch (IOException e3) {
                a = this.c.a(e3, null);
                if (a != null) {
                    obj = null;
                    this.c = a;
                } else {
                    throw e3;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        this.c.i();
        throw new IOException("Canceled");
        if (obj != null) {
            this.c.k().c();
        }
        throw th;
    }
}
