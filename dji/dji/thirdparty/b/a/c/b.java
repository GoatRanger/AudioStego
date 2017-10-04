package dji.thirdparty.b.a.c;

import com.alibaba.sdk.android.man.util.MANConfig;
import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.b.a.a;
import dji.thirdparty.b.a.a.d;
import dji.thirdparty.b.a.b.o;
import dji.thirdparty.b.a.b.r;
import dji.thirdparty.b.a.h;
import dji.thirdparty.b.a.k;
import dji.thirdparty.b.ab;
import dji.thirdparty.b.ad;
import dji.thirdparty.b.af;
import dji.thirdparty.b.g;
import dji.thirdparty.b.j;
import dji.thirdparty.b.l;
import dji.thirdparty.b.s;
import dji.thirdparty.b.z;
import dji.thirdparty.c.e;
import dji.thirdparty.c.p;
import dji.thirdparty.c.w;
import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownServiceException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;

public final class b extends dji.thirdparty.b.a.a.d.b implements j {
    public Socket b;
    public volatile d c;
    public int d;
    public e e;
    public dji.thirdparty.c.d f;
    public int g;
    public final List<Reference<r>> h = new ArrayList();
    public boolean i;
    public long j = IPositioningSession.NotSet;
    private final af k;
    private Socket l;
    private s m;
    private z n;

    public b(af afVar) {
        this.k = afVar;
    }

    public void a(int i, int i2, int i3, List<l> list, boolean z) throws o {
        if (this.n != null) {
            throw new IllegalStateException("already connected");
        }
        a aVar = new a(list);
        Proxy b = this.k.b();
        dji.thirdparty.b.a a = this.k.a();
        if (this.k.a().i() != null || list.contains(l.c)) {
            o oVar = null;
            while (this.n == null) {
                Socket createSocket;
                if (b.type() == Type.DIRECT || b.type() == Type.HTTP) {
                    createSocket = a.c().createSocket();
                } else {
                    try {
                        createSocket = new Socket(b);
                    } catch (IOException e) {
                        dji.thirdparty.b.a.j.a(this.b);
                        dji.thirdparty.b.a.j.a(this.l);
                        this.b = null;
                        this.l = null;
                        this.e = null;
                        this.f = null;
                        this.m = null;
                        this.n = null;
                        if (oVar == null) {
                            oVar = new o(e);
                        } else {
                            oVar.a(e);
                        }
                        if (!z || !aVar.a(e)) {
                            throw oVar;
                        }
                    }
                }
                this.l = createSocket;
                a(i, i2, i3, aVar);
            }
            return;
        }
        throw new o(new UnknownServiceException("CLEARTEXT communication not supported: " + list));
    }

    private void a(int i, int i2, int i3, a aVar) throws IOException {
        this.l.setSoTimeout(i2);
        try {
            h.a().a(this.l, this.k.c(), i);
            this.e = p.a(p.b(this.l));
            this.f = p.a(p.a(this.l));
            if (this.k.a().i() != null) {
                a(i2, i3, aVar);
            } else {
                this.n = z.HTTP_1_1;
                this.b = this.l;
            }
            if (this.n == z.SPDY_3 || this.n == z.HTTP_2) {
                this.b.setSoTimeout(0);
                d a = new d.a(true).a(this.b, this.k.a().a().i(), this.e, this.f).a(this.n).a((dji.thirdparty.b.a.a.d.b) this).a();
                a.h();
                this.g = a.d();
                this.c = a;
                return;
            }
            this.g = 1;
        } catch (ConnectException e) {
            throw new ConnectException("Failed to connect to " + this.k.c());
        }
    }

    private void a(int i, int i2, a aVar) throws IOException {
        Throwable th;
        Socket socket;
        AssertionError assertionError;
        Throwable th2;
        String str = null;
        if (this.k.d()) {
            a(i, i2);
        }
        dji.thirdparty.b.a a = this.k.a();
        try {
            Socket socket2 = (SSLSocket) a.i().createSocket(this.l, a.a().i(), a.a().j(), true);
            try {
                l a2 = aVar.a((SSLSocket) socket2);
                if (a2.d()) {
                    h.a().a((SSLSocket) socket2, a.a().i(), a.e());
                }
                socket2.startHandshake();
                s a3 = s.a(socket2.getSession());
                if (a.j().verify(a.a().i(), socket2.getSession())) {
                    a.k().a(a.a().i(), a3.c());
                    if (a2.d()) {
                        str = h.a().b((SSLSocket) socket2);
                    }
                    this.b = socket2;
                    this.e = p.a(p.b(this.b));
                    this.f = p.a(p.a(this.b));
                    this.m = a3;
                    this.n = str != null ? z.get(str) : z.HTTP_1_1;
                    if (socket2 != null) {
                        h.a().a((SSLSocket) socket2);
                        return;
                    }
                    return;
                }
                Certificate certificate = (X509Certificate) a3.c().get(0);
                throw new SSLPeerUnverifiedException("Hostname " + a.a().i() + " not verified:" + "\n    certificate: " + g.a(certificate) + "\n    DN: " + certificate.getSubjectDN().getName() + "\n    subjectAltNames: " + dji.thirdparty.b.a.d.d.a(certificate));
            } catch (Throwable e) {
                th = e;
                socket = socket2;
                assertionError = th;
                try {
                    if (dji.thirdparty.b.a.j.a(assertionError)) {
                        throw new IOException(assertionError);
                    }
                    throw assertionError;
                } catch (Throwable th3) {
                    th2 = th3;
                }
            } catch (Throwable e2) {
                th = e2;
                socket = socket2;
                th2 = th;
                if (socket != null) {
                    h.a().a((SSLSocket) socket);
                }
                dji.thirdparty.b.a.j.a(socket);
                throw th2;
            }
        } catch (AssertionError e3) {
            assertionError = e3;
            if (dji.thirdparty.b.a.j.a(assertionError)) {
                throw new IOException(assertionError);
            }
            throw assertionError;
        }
    }

    private void a(int i, int i2) throws IOException {
        ab h = h();
        String str = "CONNECT " + dji.thirdparty.b.a.j.a(h.a(), true) + " HTTP/1.1";
        do {
            dji.thirdparty.b.a.b.d dVar = new dji.thirdparty.b.a.b.d(null, this.e, this.f);
            this.e.a().a((long) i, TimeUnit.MILLISECONDS);
            this.f.a().a((long) i2, TimeUnit.MILLISECONDS);
            dVar.a(h.c(), str);
            dVar.d();
            ad a = dVar.e().a(h).a();
            long a2 = dji.thirdparty.b.a.b.j.a(a);
            if (a2 == -1) {
                a2 = 0;
            }
            w b = dVar.b(a2);
            dji.thirdparty.b.a.j.b(b, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
            b.close();
            switch (a.c()) {
                case 200:
                    if (!this.e.c().g() || !this.f.c().g()) {
                        throw new IOException("TLS tunnel buffered too many bytes!");
                    }
                    return;
                case dji.pilot.flyunlimit.a.C /*407*/:
                    h = this.k.a().d().a(this.k, a);
                    break;
                default:
                    throw new IOException("Unexpected response code for CONNECT: " + a.c());
            }
        } while (h != null);
        throw new IOException("Failed to authenticate with proxy");
    }

    private ab h() throws IOException {
        return new ab.a().a(this.k.a().a()).a(MANConfig.NETWORK_SINGLE_REQUEST_HOST_KEY, dji.thirdparty.b.a.j.a(this.k.a().a(), true)).a("Proxy-Connection", "Keep-Alive").a("User-Agent", k.a()).d();
    }

    boolean e() {
        return this.n != null;
    }

    public af a() {
        return this.k;
    }

    public void f() {
        dji.thirdparty.b.a.j.a(this.l);
    }

    public Socket b() {
        return this.b;
    }

    public boolean a(boolean z) {
        if (this.b.isClosed() || this.b.isInputShutdown() || this.b.isOutputShutdown()) {
            return false;
        }
        if (this.c != null || !z) {
            return true;
        }
        int soTimeout;
        try {
            soTimeout = this.b.getSoTimeout();
            this.b.setSoTimeout(1);
            if (this.e.g()) {
                this.b.setSoTimeout(soTimeout);
                return false;
            }
            this.b.setSoTimeout(soTimeout);
            return true;
        } catch (SocketTimeoutException e) {
            return true;
        } catch (IOException e2) {
            return false;
        } catch (Throwable th) {
            this.b.setSoTimeout(soTimeout);
        }
    }

    public void a(dji.thirdparty.b.a.a.e eVar) throws IOException {
        eVar.a(dji.thirdparty.b.a.a.a.REFUSED_STREAM);
    }

    public void a(d dVar) {
        this.g = dVar.d();
    }

    public s c() {
        return this.m;
    }

    public boolean g() {
        return this.c != null;
    }

    public z d() {
        if (this.c == null) {
            return this.n != null ? this.n : z.HTTP_1_1;
        } else {
            return this.c.a();
        }
    }

    public String toString() {
        return "Connection{" + this.k.a().a().i() + ":" + this.k.a().a().j() + ", proxy=" + this.k.b() + " hostAddress=" + this.k.c() + " cipherSuite=" + (this.m != null ? this.m.b() : "none") + " protocol=" + this.n + '}';
    }
}
