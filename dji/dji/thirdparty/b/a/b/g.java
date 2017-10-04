package dji.thirdparty.b.a.b;

import com.alibaba.sdk.android.man.util.MANConfig;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpStatusCodes;
import com.here.posclient.UpdateOptions;
import com.loopj.android.http.AsyncHttpClient;
import dji.thirdparty.b.a.k;
import dji.thirdparty.b.ab;
import dji.thirdparty.b.ad;
import dji.thirdparty.b.ae;
import dji.thirdparty.b.j;
import dji.thirdparty.b.m;
import dji.thirdparty.b.n;
import dji.thirdparty.b.t;
import dji.thirdparty.b.u;
import dji.thirdparty.b.w;
import dji.thirdparty.b.y;
import dji.thirdparty.b.z;
import dji.thirdparty.c.c;
import dji.thirdparty.c.d;
import dji.thirdparty.c.e;
import dji.thirdparty.c.l;
import dji.thirdparty.c.p;
import dji.thirdparty.c.v;
import dji.thirdparty.c.x;
import it.sauronsoftware.ftp4j.FTPCodes;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

public final class g {
    public static final int a = 20;
    private static final ae f = new ae() {
        public w a() {
            return null;
        }

        public long b() {
            return 0;
        }

        public e c() {
            return new c();
        }
    };
    final y b;
    public final r c;
    long d = -1;
    public final boolean e;
    private final ad g;
    private i h;
    private boolean i;
    private final ab j;
    private ab k;
    private ad l;
    private ad m;
    private v n;
    private d o;
    private final boolean p;
    private final boolean q;
    private a r;
    private b s;

    class a implements dji.thirdparty.b.v.a {
        final /* synthetic */ g a;
        private final int b;
        private final ab c;
        private int d;

        a(g gVar, int i, ab abVar) {
            this.a = gVar;
            this.b = i;
            this.c = abVar;
        }

        public j b() {
            return this.a.c.b();
        }

        public ab a() {
            return this.c;
        }

        public ad a(ab abVar) throws IOException {
            dji.thirdparty.b.v vVar;
            this.d++;
            if (this.b > 0) {
                vVar = (dji.thirdparty.b.v) this.a.b.x().get(this.b - 1);
                dji.thirdparty.b.a a = b().a().a();
                if (!abVar.a().i().equals(a.a().i()) || abVar.a().j() != a.a().j()) {
                    throw new IllegalStateException("network interceptor " + vVar + " must retain the same host and port");
                } else if (this.d > 1) {
                    throw new IllegalStateException("network interceptor " + vVar + " must call proceed() exactly once");
                }
            }
            if (this.b < this.a.b.x().size()) {
                Object aVar = new a(this.a, this.b + 1, abVar);
                vVar = (dji.thirdparty.b.v) this.a.b.x().get(this.b);
                ad a2 = vVar.a(aVar);
                if (aVar.d != 1) {
                    throw new IllegalStateException("network interceptor " + vVar + " must call proceed() exactly once");
                } else if (a2 != null) {
                    return a2;
                } else {
                    throw new NullPointerException("network interceptor " + vVar + " returned null");
                }
            }
            this.a.h.a(abVar);
            this.a.k = abVar;
            if (this.a.a(abVar) && abVar.d() != null) {
                d a3 = p.a(this.a.h.a(abVar, abVar.d().c()));
                abVar.d().a(a3);
                a3.close();
            }
            ad b = this.a.q();
            int c = b.c();
            if ((c != HttpStatusCodes.STATUS_CODE_NO_CONTENT && c != 205) || b.h().b() <= 0) {
                return b;
            }
            throw new ProtocolException("HTTP " + c + " had non-zero Content-Length: " + b.h().b());
        }
    }

    public g(y yVar, ab abVar, boolean z, boolean z2, boolean z3, r rVar, n nVar, ad adVar) {
        this.b = yVar;
        this.j = abVar;
        this.e = z;
        this.p = z2;
        this.q = z3;
        if (rVar == null) {
            rVar = new r(yVar.p(), a(yVar, abVar));
        }
        this.c = rVar;
        this.n = nVar;
        this.g = adVar;
    }

    public void a() throws l, o, IOException {
        if (this.s == null) {
            if (this.h != null) {
                throw new IllegalStateException();
            }
            ab b = b(this.j);
            dji.thirdparty.b.a.e a = dji.thirdparty.b.a.d.b.a(this.b);
            ad a2 = a != null ? a.a(b) : null;
            this.s = new dji.thirdparty.b.a.b.b.a(System.currentTimeMillis(), b, a2).a();
            this.k = this.s.a;
            this.l = this.s.b;
            if (a != null) {
                a.a(this.s);
            }
            if (a2 != null && this.l == null) {
                dji.thirdparty.b.a.j.a(a2.h());
            }
            if (this.k == null && this.l == null) {
                this.m = new dji.thirdparty.b.ad.a().a(this.j).c(b(this.g)).a(z.HTTP_1_1).a((int) FTPCodes.COMMAND_PARAMETER_NOT_IMPLEMENTED).a("Unsatisfiable Request (only-if-cached)").a(f).a();
            } else if (this.k == null) {
                this.m = this.l.i().a(this.j).c(b(this.g)).b(b(this.l)).a();
                this.m = c(this.m);
            } else {
                try {
                    this.h = o();
                    this.h.a(this);
                    if (n()) {
                        long a3 = j.a(b);
                        if (!this.e) {
                            this.h.a(this.k);
                            this.n = this.h.a(this.k, a3);
                        } else if (a3 > UpdateOptions.SOURCE_ANY) {
                            throw new IllegalStateException("Use setFixedLengthStreamingMode() or setChunkedStreamingMode() for requests larger than 2 GiB.");
                        } else if (a3 != -1) {
                            this.h.a(this.k);
                            this.n = new n((int) a3);
                        } else {
                            this.n = new n();
                        }
                    }
                } catch (Throwable th) {
                    if (a2 != null) {
                        dji.thirdparty.b.a.j.a(a2.h());
                    }
                }
            }
        }
    }

    private boolean n() {
        return this.p && a(this.k) && this.n == null;
    }

    private i o() throws o, l, IOException {
        return this.c.a(this.b.a(), this.b.b(), this.b.c(), this.b.s(), !this.k.b().equals(HttpMethods.GET));
    }

    private static ad b(ad adVar) {
        return (adVar == null || adVar.h() == null) ? adVar : adVar.i().a(null).a();
    }

    public void b() {
        if (this.d != -1) {
            throw new IllegalStateException();
        }
        this.d = System.currentTimeMillis();
    }

    boolean a(ab abVar) {
        return h.c(abVar.b());
    }

    public v c() {
        if (this.s != null) {
            return this.n;
        }
        throw new IllegalStateException();
    }

    public d d() {
        d dVar = this.o;
        if (dVar != null) {
            return dVar;
        }
        v c = c();
        if (c == null) {
            return null;
        }
        dVar = p.a(c);
        this.o = dVar;
        return dVar;
    }

    public boolean e() {
        return this.m != null;
    }

    public ab f() {
        return this.j;
    }

    public ad g() {
        if (this.m != null) {
            return this.m;
        }
        throw new IllegalStateException();
    }

    public j h() {
        return this.c.b();
    }

    public g a(IOException iOException, v vVar) {
        if (!this.c.a(iOException, vVar) || !this.b.s()) {
            return null;
        }
        return new g(this.b, this.j, this.e, this.p, this.q, k(), (n) vVar, this.g);
    }

    public g a(IOException iOException) {
        return a(iOException, this.n);
    }

    private void p() throws IOException {
        dji.thirdparty.b.a.e a = dji.thirdparty.b.a.d.b.a(this.b);
        if (a != null) {
            if (b.a(this.m, this.k)) {
                this.r = a.a(b(this.m));
            } else if (h.a(this.k.b())) {
                try {
                    a.b(this.k);
                } catch (IOException e) {
                }
            }
        }
    }

    public void i() throws IOException {
        this.c.c();
    }

    public void j() {
        this.c.e();
    }

    public r k() {
        if (this.o != null) {
            dji.thirdparty.b.a.j.a(this.o);
        } else if (this.n != null) {
            dji.thirdparty.b.a.j.a(this.n);
        }
        if (this.m != null) {
            dji.thirdparty.b.a.j.a(this.m.h());
        } else {
            this.c.a(null);
        }
        return this.c;
    }

    private ad c(ad adVar) throws IOException {
        if (!this.i || !AsyncHttpClient.ENCODING_GZIP.equalsIgnoreCase(this.m.b(AsyncHttpClient.HEADER_CONTENT_ENCODING)) || adVar.h() == null) {
            return adVar;
        }
        dji.thirdparty.c.w lVar = new l(adVar.h().c());
        t a = adVar.g().c().c(AsyncHttpClient.HEADER_CONTENT_ENCODING).c("Content-Length").a();
        return adVar.i().a(a).a(new k(a, p.a(lVar))).a();
    }

    public static boolean a(ad adVar) {
        if (adVar.a().b().equals(HttpMethods.HEAD)) {
            return false;
        }
        int c = adVar.c();
        if ((c < 100 || c >= 200) && c != HttpStatusCodes.STATUS_CODE_NO_CONTENT && c != HttpStatusCodes.STATUS_CODE_NOT_MODIFIED) {
            return true;
        }
        if (j.a(adVar) != -1 || "chunked".equalsIgnoreCase(adVar.b("Transfer-Encoding"))) {
            return true;
        }
        return false;
    }

    private ab b(ab abVar) throws IOException {
        dji.thirdparty.b.ab.a f = abVar.f();
        if (abVar.a(MANConfig.NETWORK_SINGLE_REQUEST_HOST_KEY) == null) {
            f.a(MANConfig.NETWORK_SINGLE_REQUEST_HOST_KEY, dji.thirdparty.b.a.j.a(abVar.a(), false));
        }
        if (abVar.a(dji.sdksharedlib.b.d.ck) == null) {
            f.a(dji.sdksharedlib.b.d.ck, "Keep-Alive");
        }
        if (abVar.a(AsyncHttpClient.HEADER_ACCEPT_ENCODING) == null) {
            this.i = true;
            f.a(AsyncHttpClient.HEADER_ACCEPT_ENCODING, AsyncHttpClient.ENCODING_GZIP);
        }
        List a = this.b.f().a(abVar.a());
        if (!a.isEmpty()) {
            f.a("Cookie", a(a));
        }
        if (abVar.a("User-Agent") == null) {
            f.a("User-Agent", k.a());
        }
        return f.d();
    }

    private String a(List<m> list) {
        StringBuilder stringBuilder = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                stringBuilder.append("; ");
            }
            m mVar = (m) list.get(i);
            stringBuilder.append(mVar.a()).append('=').append(mVar.b());
        }
        return stringBuilder.toString();
    }

    public void l() throws IOException {
        if (this.m == null) {
            if (this.k == null && this.l == null) {
                throw new IllegalStateException("call sendRequest() first!");
            } else if (this.k != null) {
                ad q;
                if (this.q) {
                    this.h.a(this.k);
                    q = q();
                } else if (this.p) {
                    if (this.o != null && this.o.c().b() > 0) {
                        this.o.f();
                    }
                    if (this.d == -1) {
                        if (j.a(this.k) == -1 && (this.n instanceof n)) {
                            this.k = this.k.f().a("Content-Length", Long.toString(((n) this.n).b())).d();
                        }
                        this.h.a(this.k);
                    }
                    if (this.n != null) {
                        if (this.o != null) {
                            this.o.close();
                        } else {
                            this.n.close();
                        }
                        if (this.n instanceof n) {
                            this.h.a((n) this.n);
                        }
                    }
                    q = q();
                } else {
                    q = new a(this, 0, this.k).a(this.k);
                }
                a(q.g());
                if (this.l != null) {
                    if (a(this.l, q)) {
                        this.m = this.l.i().a(this.j).c(b(this.g)).a(a(this.l.g(), q.g())).b(b(this.l)).a(b(q)).a();
                        q.h().close();
                        i();
                        dji.thirdparty.b.a.e a = dji.thirdparty.b.a.d.b.a(this.b);
                        a.a();
                        a.a(this.l, b(this.m));
                        this.m = c(this.m);
                        return;
                    }
                    dji.thirdparty.b.a.j.a(this.l.h());
                }
                this.m = q.i().a(this.j).c(b(this.g)).b(b(this.l)).a(b(q)).a();
                if (a(this.m)) {
                    p();
                    this.m = c(a(this.r, this.m));
                }
            }
        }
    }

    private ad q() throws IOException {
        this.h.d();
        ad a = this.h.b().a(this.k).a(this.c.b().c()).a(j.b, Long.toString(this.d)).a(j.c, Long.toString(System.currentTimeMillis())).a();
        if (!this.q) {
            a = a.i().a(this.h.a(a)).a();
        }
        if ("close".equalsIgnoreCase(a.a().a(dji.sdksharedlib.b.d.ck)) || "close".equalsIgnoreCase(a.b(dji.sdksharedlib.b.d.ck))) {
            this.c.d();
        }
        return a;
    }

    private ad a(final a aVar, ad adVar) throws IOException {
        if (aVar == null) {
            return adVar;
        }
        v b = aVar.b();
        if (b == null) {
            return adVar;
        }
        final e c = adVar.h().c();
        final d a = p.a(b);
        return adVar.i().a(new k(adVar.g(), p.a(new dji.thirdparty.c.w(this) {
            boolean a;
            final /* synthetic */ g e;

            public long a(c cVar, long j) throws IOException {
                try {
                    long a = c.a(cVar, j);
                    if (a == -1) {
                        if (!this.a) {
                            this.a = true;
                            a.close();
                        }
                        return -1;
                    }
                    cVar.a(a.c(), cVar.b() - a, a);
                    a.F();
                    return a;
                } catch (IOException e) {
                    if (!this.a) {
                        this.a = true;
                        aVar.a();
                    }
                    throw e;
                }
            }

            public x a() {
                return c.a();
            }

            public void close() throws IOException {
                if (!(this.a || dji.thirdparty.b.a.j.a((dji.thirdparty.c.w) this, 100, TimeUnit.MILLISECONDS))) {
                    this.a = true;
                    aVar.a();
                }
                c.close();
            }
        }))).a();
    }

    private static boolean a(ad adVar, ad adVar2) {
        if (adVar2.c() == HttpStatusCodes.STATUS_CODE_NOT_MODIFIED) {
            return true;
        }
        Date b = adVar.g().b("Last-Modified");
        if (b != null) {
            Date b2 = adVar2.g().b("Last-Modified");
            if (b2 != null && b2.getTime() < b.getTime()) {
                return true;
            }
        }
        return false;
    }

    private static t a(t tVar, t tVar2) throws IOException {
        int i;
        int i2 = 0;
        dji.thirdparty.b.t.a aVar = new dji.thirdparty.b.t.a();
        int a = tVar.a();
        for (i = 0; i < a; i++) {
            String a2 = tVar.a(i);
            String b = tVar.b(i);
            if (!("Warning".equalsIgnoreCase(a2) && b.startsWith("1")) && (!j.a(a2) || tVar2.a(a2) == null)) {
                aVar.a(a2, b);
            }
        }
        i = tVar2.a();
        while (i2 < i) {
            String a3 = tVar2.a(i2);
            if (!"Content-Length".equalsIgnoreCase(a3) && j.a(a3)) {
                aVar.a(a3, tVar2.b(i2));
            }
            i2++;
        }
        return aVar.a();
    }

    public void a(t tVar) throws IOException {
        if (this.b.f() != n.a) {
            List a = m.a(this.j.a(), tVar);
            if (!a.isEmpty()) {
                this.b.f().a(this.j.a(), a);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public dji.thirdparty.b.ab m() throws java.io.IOException {
        /*
        r5 = this;
        r1 = 0;
        r0 = r5.m;
        if (r0 != 0) goto L_0x000b;
    L_0x0005:
        r0 = new java.lang.IllegalStateException;
        r0.<init>();
        throw r0;
    L_0x000b:
        r0 = r5.c;
        r0 = r0.b();
        if (r0 == 0) goto L_0x0027;
    L_0x0013:
        r0 = r0.a();
    L_0x0017:
        r2 = r5.m;
        r2 = r2.c();
        r3 = r5.j;
        r3 = r3.b();
        switch(r2) {
            case 300: goto L_0x0063;
            case 301: goto L_0x0063;
            case 302: goto L_0x0063;
            case 303: goto L_0x0063;
            case 307: goto L_0x0053;
            case 308: goto L_0x0053;
            case 401: goto L_0x0046;
            case 407: goto L_0x0029;
            case 408: goto L_0x00dc;
            default: goto L_0x0026;
        };
    L_0x0026:
        return r1;
    L_0x0027:
        r0 = r1;
        goto L_0x0017;
    L_0x0029:
        if (r0 == 0) goto L_0x003f;
    L_0x002b:
        r1 = r0.b();
    L_0x002f:
        r1 = r1.type();
        r2 = java.net.Proxy.Type.HTTP;
        if (r1 == r2) goto L_0x0046;
    L_0x0037:
        r0 = new java.net.ProtocolException;
        r1 = "Received HTTP_PROXY_AUTH (407) code while not using proxy";
        r0.<init>(r1);
        throw r0;
    L_0x003f:
        r1 = r5.b;
        r1 = r1.d();
        goto L_0x002f;
    L_0x0046:
        r1 = r5.b;
        r1 = r1.n();
        r2 = r5.m;
        r1 = r1.a(r0, r2);
        goto L_0x0026;
    L_0x0053:
        r0 = "GET";
        r0 = r3.equals(r0);
        if (r0 != 0) goto L_0x0063;
    L_0x005b:
        r0 = "HEAD";
        r0 = r3.equals(r0);
        if (r0 == 0) goto L_0x0026;
    L_0x0063:
        r0 = r5.b;
        r0 = r0.r();
        if (r0 == 0) goto L_0x0026;
    L_0x006b:
        r0 = r5.m;
        r2 = "Location";
        r0 = r0.b(r2);
        if (r0 == 0) goto L_0x0026;
    L_0x0075:
        r2 = r5.j;
        r2 = r2.a();
        r0 = r2.e(r0);
        if (r0 == 0) goto L_0x0026;
    L_0x0081:
        r2 = r0.c();
        r4 = r5.j;
        r4 = r4.a();
        r4 = r4.c();
        r2 = r2.equals(r4);
        if (r2 != 0) goto L_0x009d;
    L_0x0095:
        r2 = r5.b;
        r2 = r2.q();
        if (r2 == 0) goto L_0x0026;
    L_0x009d:
        r2 = r5.j;
        r2 = r2.f();
        r4 = dji.thirdparty.b.a.b.h.c(r3);
        if (r4 == 0) goto L_0x00c3;
    L_0x00a9:
        r4 = dji.thirdparty.b.a.b.h.d(r3);
        if (r4 == 0) goto L_0x00d8;
    L_0x00af:
        r3 = "GET";
        r2.a(r3, r1);
    L_0x00b4:
        r1 = "Transfer-Encoding";
        r2.b(r1);
        r1 = "Content-Length";
        r2.b(r1);
        r1 = "Content-Type";
        r2.b(r1);
    L_0x00c3:
        r1 = r5.a(r0);
        if (r1 != 0) goto L_0x00ce;
    L_0x00c9:
        r1 = "Authorization";
        r2.b(r1);
    L_0x00ce:
        r0 = r2.a(r0);
        r1 = r0.d();
        goto L_0x0026;
    L_0x00d8:
        r2.a(r3, r1);
        goto L_0x00b4;
    L_0x00dc:
        r0 = r5.n;
        if (r0 == 0) goto L_0x00e6;
    L_0x00e0:
        r0 = r5.n;
        r0 = r0 instanceof dji.thirdparty.b.a.b.n;
        if (r0 == 0) goto L_0x00f1;
    L_0x00e6:
        r0 = 1;
    L_0x00e7:
        r2 = r5.p;
        if (r2 == 0) goto L_0x00ed;
    L_0x00eb:
        if (r0 == 0) goto L_0x0026;
    L_0x00ed:
        r1 = r5.j;
        goto L_0x0026;
    L_0x00f1:
        r0 = 0;
        goto L_0x00e7;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.b.a.b.g.m():dji.thirdparty.b.ab");
    }

    public boolean a(u uVar) {
        u a = this.j.a();
        return a.i().equals(uVar.i()) && a.j() == uVar.j() && a.c().equals(uVar.c());
    }

    private static dji.thirdparty.b.a a(y yVar, ab abVar) {
        SSLSocketFactory k;
        HostnameVerifier l;
        dji.thirdparty.b.g gVar = null;
        if (abVar.h()) {
            k = yVar.k();
            l = yVar.l();
            gVar = yVar.m();
        } else {
            l = null;
            k = null;
        }
        return new dji.thirdparty.b.a(abVar.a().i(), abVar.a().j(), yVar.i(), yVar.j(), k, l, gVar, yVar.o(), yVar.d(), yVar.u(), yVar.v(), yVar.e());
    }
}
