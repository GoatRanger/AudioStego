package dji.thirdparty.b;

import com.here.posclient.UpdateOptions;
import dji.thirdparty.b.a.b.r;
import dji.thirdparty.b.a.c.b;
import dji.thirdparty.b.a.d.d;
import dji.thirdparty.b.a.d.f;
import dji.thirdparty.b.a.e;
import dji.thirdparty.b.a.h;
import dji.thirdparty.b.a.i;
import dji.thirdparty.b.a.j;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

public class y implements dji.thirdparty.b.e.a, Cloneable {
    private static final List<l> A = j.a(l.a, l.b, l.c);
    private static final List<z> z = j.a(z.HTTP_2, z.SPDY_3, z.HTTP_1_1);
    final p a;
    final Proxy b;
    final List<z> c;
    final List<l> d;
    final List<v> e;
    final List<v> f;
    final ProxySelector g;
    final n h;
    final c i;
    final e j;
    final SocketFactory k;
    final SSLSocketFactory l;
    final f m;
    final HostnameVerifier n;
    final g o;
    final b p;
    final b q;
    final k r;
    final q s;
    final boolean t;
    final boolean u;
    final boolean v;
    final int w;
    final int x;
    final int y;

    public static final class a {
        p a;
        Proxy b;
        List<z> c;
        List<l> d;
        final List<v> e;
        final List<v> f;
        ProxySelector g;
        n h;
        c i;
        e j;
        SocketFactory k;
        SSLSocketFactory l;
        f m;
        HostnameVerifier n;
        g o;
        b p;
        b q;
        k r;
        q s;
        boolean t;
        boolean u;
        boolean v;
        int w;
        int x;
        int y;

        public a() {
            this.e = new ArrayList();
            this.f = new ArrayList();
            this.a = new p();
            this.c = y.z;
            this.d = y.A;
            this.g = ProxySelector.getDefault();
            this.h = n.a;
            this.k = SocketFactory.getDefault();
            this.n = d.a;
            this.o = g.a;
            this.p = b.a;
            this.q = b.a;
            this.r = new k();
            this.s = q.a;
            this.t = true;
            this.u = true;
            this.v = true;
            this.w = 10000;
            this.x = 10000;
            this.y = 10000;
        }

        a(y yVar) {
            this.e = new ArrayList();
            this.f = new ArrayList();
            this.a = yVar.a;
            this.b = yVar.b;
            this.c = yVar.c;
            this.d = yVar.d;
            this.e.addAll(yVar.e);
            this.f.addAll(yVar.f);
            this.g = yVar.g;
            this.h = yVar.h;
            this.j = yVar.j;
            this.i = yVar.i;
            this.k = yVar.k;
            this.l = yVar.l;
            this.m = yVar.m;
            this.n = yVar.n;
            this.o = yVar.o;
            this.p = yVar.p;
            this.q = yVar.q;
            this.r = yVar.r;
            this.s = yVar.s;
            this.t = yVar.t;
            this.u = yVar.u;
            this.v = yVar.v;
            this.w = yVar.w;
            this.x = yVar.x;
            this.y = yVar.y;
        }

        public a a(long j, TimeUnit timeUnit) {
            if (j < 0) {
                throw new IllegalArgumentException("timeout < 0");
            } else if (timeUnit == null) {
                throw new IllegalArgumentException("unit == null");
            } else {
                long toMillis = timeUnit.toMillis(j);
                if (toMillis > UpdateOptions.SOURCE_ANY) {
                    throw new IllegalArgumentException("Timeout too large.");
                } else if (toMillis != 0 || j <= 0) {
                    this.w = (int) toMillis;
                    return this;
                } else {
                    throw new IllegalArgumentException("Timeout too small.");
                }
            }
        }

        public a b(long j, TimeUnit timeUnit) {
            if (j < 0) {
                throw new IllegalArgumentException("timeout < 0");
            } else if (timeUnit == null) {
                throw new IllegalArgumentException("unit == null");
            } else {
                long toMillis = timeUnit.toMillis(j);
                if (toMillis > UpdateOptions.SOURCE_ANY) {
                    throw new IllegalArgumentException("Timeout too large.");
                } else if (toMillis != 0 || j <= 0) {
                    this.x = (int) toMillis;
                    return this;
                } else {
                    throw new IllegalArgumentException("Timeout too small.");
                }
            }
        }

        public a c(long j, TimeUnit timeUnit) {
            if (j < 0) {
                throw new IllegalArgumentException("timeout < 0");
            } else if (timeUnit == null) {
                throw new IllegalArgumentException("unit == null");
            } else {
                long toMillis = timeUnit.toMillis(j);
                if (toMillis > UpdateOptions.SOURCE_ANY) {
                    throw new IllegalArgumentException("Timeout too large.");
                } else if (toMillis != 0 || j <= 0) {
                    this.y = (int) toMillis;
                    return this;
                } else {
                    throw new IllegalArgumentException("Timeout too small.");
                }
            }
        }

        public a a(Proxy proxy) {
            this.b = proxy;
            return this;
        }

        public a a(ProxySelector proxySelector) {
            this.g = proxySelector;
            return this;
        }

        public a a(n nVar) {
            if (nVar == null) {
                throw new NullPointerException("cookieJar == null");
            }
            this.h = nVar;
            return this;
        }

        void a(e eVar) {
            this.j = eVar;
            this.i = null;
        }

        public a a(c cVar) {
            this.i = cVar;
            this.j = null;
            return this;
        }

        public a a(q qVar) {
            if (qVar == null) {
                throw new NullPointerException("dns == null");
            }
            this.s = qVar;
            return this;
        }

        public a a(SocketFactory socketFactory) {
            if (socketFactory == null) {
                throw new NullPointerException("socketFactory == null");
            }
            this.k = socketFactory;
            return this;
        }

        public a a(SSLSocketFactory sSLSocketFactory) {
            if (sSLSocketFactory == null) {
                throw new NullPointerException("sslSocketFactory == null");
            }
            this.l = sSLSocketFactory;
            this.m = null;
            return this;
        }

        public a a(HostnameVerifier hostnameVerifier) {
            if (hostnameVerifier == null) {
                throw new NullPointerException("hostnameVerifier == null");
            }
            this.n = hostnameVerifier;
            return this;
        }

        public a a(g gVar) {
            if (gVar == null) {
                throw new NullPointerException("certificatePinner == null");
            }
            this.o = gVar;
            return this;
        }

        public a a(b bVar) {
            if (bVar == null) {
                throw new NullPointerException("authenticator == null");
            }
            this.q = bVar;
            return this;
        }

        public a b(b bVar) {
            if (bVar == null) {
                throw new NullPointerException("proxyAuthenticator == null");
            }
            this.p = bVar;
            return this;
        }

        public a a(k kVar) {
            if (kVar == null) {
                throw new NullPointerException("connectionPool == null");
            }
            this.r = kVar;
            return this;
        }

        public a a(boolean z) {
            this.t = z;
            return this;
        }

        public a b(boolean z) {
            this.u = z;
            return this;
        }

        public a c(boolean z) {
            this.v = z;
            return this;
        }

        public a a(p pVar) {
            if (pVar == null) {
                throw new IllegalArgumentException("dispatcher == null");
            }
            this.a = pVar;
            return this;
        }

        public a a(List<z> list) {
            List a = j.a((List) list);
            if (!a.contains(z.HTTP_1_1)) {
                throw new IllegalArgumentException("protocols doesn't contain http/1.1: " + a);
            } else if (a.contains(z.HTTP_1_0)) {
                throw new IllegalArgumentException("protocols must not contain http/1.0: " + a);
            } else if (a.contains(null)) {
                throw new IllegalArgumentException("protocols must not contain null");
            } else {
                this.c = j.a(a);
                return this;
            }
        }

        public a b(List<l> list) {
            this.d = j.a((List) list);
            return this;
        }

        public List<v> a() {
            return this.e;
        }

        public a a(v vVar) {
            this.e.add(vVar);
            return this;
        }

        public List<v> b() {
            return this.f;
        }

        public a b(v vVar) {
            this.f.add(vVar);
            return this;
        }

        public y c() {
            return new y();
        }
    }

    static {
        dji.thirdparty.b.a.d.b = new dji.thirdparty.b.a.d() {
            public void a(dji.thirdparty.b.t.a aVar, String str) {
                aVar.a(str);
            }

            public void a(dji.thirdparty.b.t.a aVar, String str, String str2) {
                aVar.b(str, str2);
            }

            public void a(a aVar, e eVar) {
                aVar.a(eVar);
            }

            public e a(y yVar) {
                return yVar.h();
            }

            public boolean a(k kVar, b bVar) {
                return kVar.b(bVar);
            }

            public b a(k kVar, a aVar, r rVar) {
                return kVar.a(aVar, rVar);
            }

            public void b(k kVar, b bVar) {
                kVar.a(bVar);
            }

            public i a(k kVar) {
                return kVar.a;
            }

            public void a(e eVar, f fVar, boolean z) {
                ((aa) eVar).a(fVar, z);
            }

            public r a(e eVar) {
                return ((aa) eVar).c.c;
            }

            public void a(l lVar, SSLSocket sSLSocket, boolean z) {
                lVar.a(sSLSocket, z);
            }

            public u a(String str) throws MalformedURLException, UnknownHostException {
                return u.h(str);
            }
        };
    }

    public y() {
        this(new a());
    }

    private y(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = j.a(aVar.e);
        this.f = j.a(aVar.f);
        this.g = aVar.g;
        this.h = aVar.h;
        this.i = aVar.i;
        this.j = aVar.j;
        this.k = aVar.k;
        Object obj = null;
        for (l lVar : this.d) {
            Object obj2;
            if (obj != null || lVar.a()) {
                obj2 = 1;
            } else {
                obj2 = null;
            }
            obj = obj2;
        }
        if (aVar.l != null || obj == null) {
            this.l = aVar.l;
        } else {
            try {
                SSLContext instance = SSLContext.getInstance("TLS");
                instance.init(null, null, null);
                this.l = instance.getSocketFactory();
            } catch (GeneralSecurityException e) {
                throw new AssertionError();
            }
        }
        if (this.l == null || aVar.m != null) {
            this.m = aVar.m;
            this.o = aVar.o;
        } else {
            X509TrustManager a = h.a().a(this.l);
            if (a == null) {
                throw new IllegalStateException("Unable to extract the trust manager on " + h.a() + ", sslSocketFactory is " + this.l.getClass());
            }
            this.m = h.a().a(a);
            this.o = aVar.o.a().a(this.m).a();
        }
        this.n = aVar.n;
        this.p = aVar.p;
        this.q = aVar.q;
        this.r = aVar.r;
        this.s = aVar.s;
        this.t = aVar.t;
        this.u = aVar.u;
        this.v = aVar.v;
        this.w = aVar.w;
        this.x = aVar.x;
        this.y = aVar.y;
    }

    public int a() {
        return this.w;
    }

    public int b() {
        return this.x;
    }

    public int c() {
        return this.y;
    }

    public Proxy d() {
        return this.b;
    }

    public ProxySelector e() {
        return this.g;
    }

    public n f() {
        return this.h;
    }

    public c g() {
        return this.i;
    }

    e h() {
        return this.i != null ? this.i.a : this.j;
    }

    public q i() {
        return this.s;
    }

    public SocketFactory j() {
        return this.k;
    }

    public SSLSocketFactory k() {
        return this.l;
    }

    public HostnameVerifier l() {
        return this.n;
    }

    public g m() {
        return this.o;
    }

    public b n() {
        return this.q;
    }

    public b o() {
        return this.p;
    }

    public k p() {
        return this.r;
    }

    public boolean q() {
        return this.t;
    }

    public boolean r() {
        return this.u;
    }

    public boolean s() {
        return this.v;
    }

    public p t() {
        return this.a;
    }

    public List<z> u() {
        return this.c;
    }

    public List<l> v() {
        return this.d;
    }

    public List<v> w() {
        return this.e;
    }

    public List<v> x() {
        return this.f;
    }

    public e a(ab abVar) {
        return new aa(this, abVar);
    }

    public a y() {
        return new a(this);
    }
}
