package dji.thirdparty.b.a.b;

import dji.thirdparty.b.a;
import dji.thirdparty.b.a.c.b;
import dji.thirdparty.b.a.d;
import dji.thirdparty.b.a.i;
import dji.thirdparty.b.a.j;
import dji.thirdparty.b.af;
import dji.thirdparty.b.k;
import dji.thirdparty.c.v;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;

public final class r {
    public final a a;
    private af b;
    private final k c;
    private p d;
    private b e;
    private boolean f;
    private boolean g;
    private i h;

    public r(k kVar, a aVar) {
        this.c = kVar;
        this.a = aVar;
        this.d = new p(aVar, f());
    }

    public i a(int i, int i2, int i3, boolean z, boolean z2) throws o, IOException {
        try {
            i eVar;
            b b = b(i, i2, i3, z, z2);
            if (b.c != null) {
                eVar = new e(this, b.c);
            } else {
                b.b().setSoTimeout(i2);
                b.e.a().a((long) i2, TimeUnit.MILLISECONDS);
                b.f.a().a((long) i3, TimeUnit.MILLISECONDS);
                eVar = new d(this, b.e, b.f);
            }
            synchronized (this.c) {
                this.h = eVar;
            }
            return eVar;
        } catch (IOException e) {
            throw new o(e);
        }
    }

    private b b(int i, int i2, int i3, boolean z, boolean z2) throws IOException, o {
        b a;
        while (true) {
            a = a(i, i2, i3, z);
            synchronized (this.c) {
                if (a.d != 0) {
                    if (a.a(z2)) {
                        break;
                    }
                    a(new IOException());
                } else {
                    break;
                }
            }
        }
        return a;
    }

    private b a(int i, int i2, int i3, boolean z) throws IOException, o {
        b bVar;
        synchronized (this.c) {
            if (this.f) {
                throw new IllegalStateException("released");
            } else if (this.h != null) {
                throw new IllegalStateException("stream != null");
            } else if (this.g) {
                throw new IOException("Canceled");
            } else {
                bVar = this.e;
                if (bVar == null || bVar.i) {
                    bVar = d.b.a(this.c, this.a, this);
                    if (bVar != null) {
                        this.e = bVar;
                    } else {
                        af afVar;
                        af afVar2 = this.b;
                        if (afVar2 == null) {
                            afVar2 = this.d.b();
                            synchronized (this.c) {
                                this.b = afVar2;
                            }
                            afVar = afVar2;
                        } else {
                            afVar = afVar2;
                        }
                        bVar = new b(afVar);
                        a(bVar);
                        synchronized (this.c) {
                            d.b.b(this.c, bVar);
                            this.e = bVar;
                            if (this.g) {
                                throw new IOException("Canceled");
                            }
                        }
                        bVar.a(i, i2, i3, this.a.f(), z);
                        f().b(bVar.a());
                    }
                }
            }
        }
        return bVar;
    }

    public void a(boolean z, i iVar) {
        synchronized (this.c) {
            if (iVar != null) {
                if (iVar == this.h) {
                    if (!z) {
                        b bVar = this.e;
                        bVar.d++;
                    }
                }
            }
            throw new IllegalStateException("expected " + this.h + " but was " + iVar);
        }
        a(z, false, true);
    }

    public i a() {
        i iVar;
        synchronized (this.c) {
            iVar = this.h;
        }
        return iVar;
    }

    private i f() {
        return d.b.a(this.c);
    }

    public synchronized b b() {
        return this.e;
    }

    public void c() {
        a(false, true, false);
    }

    public void d() {
        a(true, false, false);
    }

    private void a(boolean z, boolean z2, boolean z3) {
        b bVar = null;
        synchronized (this.c) {
            if (z3) {
                this.h = null;
            }
            if (z2) {
                this.f = true;
            }
            if (this.e != null) {
                if (z) {
                    this.e.i = true;
                }
                if (this.h == null && (this.f || this.e.i)) {
                    b(this.e);
                    if (this.e.h.isEmpty()) {
                        this.e.j = System.nanoTime();
                        if (d.b.a(this.c, this.e)) {
                            bVar = this.e;
                        }
                    }
                    this.e = null;
                }
            }
        }
        if (bVar != null) {
            j.a(bVar.b());
        }
    }

    public void e() {
        synchronized (this.c) {
            this.g = true;
            i iVar = this.h;
            b bVar = this.e;
        }
        if (iVar != null) {
            iVar.a();
        } else if (bVar != null) {
            bVar.f();
        }
    }

    public void a(IOException iOException) {
        synchronized (this.c) {
            if (this.e != null && this.e.d == 0) {
                if (!(this.b == null || iOException == null)) {
                    this.d.a(this.b, iOException);
                }
                this.b = null;
            }
        }
        a(true, false, true);
    }

    public void a(b bVar) {
        bVar.h.add(new WeakReference(this));
    }

    private void b(b bVar) {
        int size = bVar.h.size();
        for (int i = 0; i < size; i++) {
            if (((Reference) bVar.h.get(i)).get() == this) {
                bVar.h.remove(i);
                return;
            }
        }
        throw new IllegalStateException();
    }

    public boolean a(IOException iOException, v vVar) {
        if (this.e != null) {
            a(iOException);
        }
        boolean z = vVar == null || (vVar instanceof n);
        if ((this.d == null || this.d.a()) && b(iOException) && z) {
            return true;
        }
        return false;
    }

    private boolean b(IOException iOException) {
        if (iOException instanceof ProtocolException) {
            return false;
        }
        if (iOException instanceof InterruptedIOException) {
            return iOException instanceof SocketTimeoutException;
        }
        if (((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return this.a.toString();
    }
}
