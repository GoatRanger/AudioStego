package dji.thirdparty.e;

import com.google.api.client.http.HttpStatusCodes;
import dji.thirdparty.b.ab;
import dji.thirdparty.b.ad;
import dji.thirdparty.b.ae;
import dji.thirdparty.b.e;
import dji.thirdparty.b.f;
import dji.thirdparty.b.w;
import dji.thirdparty.c.c;
import dji.thirdparty.c.i;
import dji.thirdparty.c.p;
import java.io.IOException;

final class h<T> implements b<T> {
    private final n<T> a;
    private final Object[] b;
    private volatile boolean c;
    private e d;
    private Throwable e;
    private boolean f;

    static final class a extends ae {
        IOException a;
        private final ae b;

        a(ae aeVar) {
            this.b = aeVar;
        }

        public w a() {
            return this.b.a();
        }

        public long b() {
            return this.b.b();
        }

        public dji.thirdparty.c.e c() {
            return p.a(new i(this, this.b.c()) {
                final /* synthetic */ a a;

                public long a(c cVar, long j) throws IOException {
                    try {
                        return super.a(cVar, j);
                    } catch (IOException e) {
                        this.a.a = e;
                        throw e;
                    }
                }
            });
        }

        public void close() {
            this.b.close();
        }

        void h() throws IOException {
            if (this.a != null) {
                throw this.a;
            }
        }
    }

    static final class b extends ae {
        private final w a;
        private final long b;

        b(w wVar, long j) {
            this.a = wVar;
            this.b = j;
        }

        public w a() {
            return this.a;
        }

        public long b() {
            return this.b;
        }

        public dji.thirdparty.c.e c() {
            throw new IllegalStateException("Cannot read raw response body of a converted body.");
        }
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return g();
    }

    public /* synthetic */ b e() {
        return g();
    }

    h(n<T> nVar, Object[] objArr) {
        this.a = nVar;
        this.b = objArr;
    }

    public h<T> g() {
        return new h(this.a, this.b);
    }

    public synchronized ab f() {
        ab a;
        e eVar = this.d;
        if (eVar != null) {
            a = eVar.a();
        } else if (this.e == null) {
            try {
                eVar = h();
                this.d = eVar;
                a = eVar.a();
            } catch (Throwable e) {
                this.e = e;
                throw e;
            } catch (Throwable e2) {
                this.e = e2;
                throw new RuntimeException("Unable to create request.", e2);
            }
        } else if (this.e instanceof IOException) {
            throw new RuntimeException("Unable to create request.", this.e);
        } else {
            throw ((RuntimeException) this.e);
        }
        return a;
    }

    public void a(final d<T> dVar) {
        if (dVar == null) {
            throw new NullPointerException("callback == null");
        }
        synchronized (this) {
            if (this.f) {
                throw new IllegalStateException("Already executed.");
            }
            e h;
            this.f = true;
            e eVar = this.d;
            Throwable th = this.e;
            if (eVar == null && th == null) {
                try {
                    h = h();
                    this.d = h;
                } catch (Throwable th2) {
                    th = th2;
                    this.e = th;
                    h = eVar;
                }
            } else {
                h = eVar;
            }
        }
        if (th != null) {
            dVar.a((b) this, th);
            return;
        }
        if (this.c) {
            h.c();
        }
        h.a(new f(this) {
            final /* synthetic */ h b;

            public void a(e eVar, ad adVar) throws IOException {
                try {
                    a(this.b.a(adVar));
                } catch (Throwable th) {
                    a(th);
                }
            }

            public void a(e eVar, IOException iOException) {
                try {
                    dVar.a(this.b, (Throwable) iOException);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            private void a(Throwable th) {
                try {
                    dVar.a(this.b, th);
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
            }

            private void a(l<T> lVar) {
                try {
                    dVar.a(this.b, (l) lVar);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    public synchronized boolean b() {
        return this.f;
    }

    public l<T> a() throws IOException {
        e eVar;
        Throwable e;
        synchronized (this) {
            if (this.f) {
                throw new IllegalStateException("Already executed.");
            }
            this.f = true;
            if (this.e == null) {
                eVar = this.d;
                if (eVar == null) {
                    try {
                        eVar = h();
                        this.d = eVar;
                    } catch (IOException e2) {
                        e = e2;
                        this.e = e;
                        throw e;
                    } catch (RuntimeException e3) {
                        e = e3;
                        this.e = e;
                        throw e;
                    }
                }
            } else if (this.e instanceof IOException) {
                throw ((IOException) this.e);
            } else {
                throw ((RuntimeException) this.e);
            }
        }
        if (this.c) {
            eVar.c();
        }
        return a(eVar.b());
    }

    private e h() throws IOException {
        e a = this.a.d.a(this.a.a(this.b));
        if (a != null) {
            return a;
        }
        throw new NullPointerException("Call.Factory returned null.");
    }

    l<T> a(ad adVar) throws IOException {
        ae h = adVar.h();
        ad a = adVar.i().a(new b(h.a(), h.b())).a();
        int c = a.c();
        if (c < 200 || c >= 300) {
            try {
                l<T> a2 = l.a(o.a(h), a);
                return a2;
            } finally {
                h.close();
            }
        } else if (c == HttpStatusCodes.STATUS_CODE_NO_CONTENT || c == 205) {
            return l.a(null, a);
        } else {
            ae aVar = new a(h);
            try {
                return l.a(this.a.a(aVar), a);
            } catch (RuntimeException e) {
                aVar.h();
                throw e;
            }
        }
    }

    public void c() {
        this.c = true;
        synchronized (this) {
            e eVar = this.d;
        }
        if (eVar != null) {
            eVar.c();
        }
    }

    public boolean d() {
        return this.c;
    }
}
