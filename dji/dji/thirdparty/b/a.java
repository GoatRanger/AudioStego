package dji.thirdparty.b;

import com.alipay.sdk.b.b;
import dji.thirdparty.b.a.j;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

public final class a {
    final u a;
    final q b;
    final SocketFactory c;
    final b d;
    final List<z> e;
    final List<l> f;
    final ProxySelector g;
    final Proxy h;
    final SSLSocketFactory i;
    final HostnameVerifier j;
    final g k;

    public a(String str, int i, q qVar, SocketFactory socketFactory, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier, g gVar, b bVar, Proxy proxy, List<z> list, List<l> list2, ProxySelector proxySelector) {
        this.a = new dji.thirdparty.b.u.a().a(sSLSocketFactory != null ? b.a : "http").f(str).a(i).c();
        if (qVar == null) {
            throw new IllegalArgumentException("dns == null");
        }
        this.b = qVar;
        if (socketFactory == null) {
            throw new IllegalArgumentException("socketFactory == null");
        }
        this.c = socketFactory;
        if (bVar == null) {
            throw new IllegalArgumentException("proxyAuthenticator == null");
        }
        this.d = bVar;
        if (list == null) {
            throw new IllegalArgumentException("protocols == null");
        }
        this.e = j.a((List) list);
        if (list2 == null) {
            throw new IllegalArgumentException("connectionSpecs == null");
        }
        this.f = j.a((List) list2);
        if (proxySelector == null) {
            throw new IllegalArgumentException("proxySelector == null");
        }
        this.g = proxySelector;
        this.h = proxy;
        this.i = sSLSocketFactory;
        this.j = hostnameVerifier;
        this.k = gVar;
    }

    public u a() {
        return this.a;
    }

    public q b() {
        return this.b;
    }

    public SocketFactory c() {
        return this.c;
    }

    public b d() {
        return this.d;
    }

    public List<z> e() {
        return this.e;
    }

    public List<l> f() {
        return this.f;
    }

    public ProxySelector g() {
        return this.g;
    }

    public Proxy h() {
        return this.h;
    }

    public SSLSocketFactory i() {
        return this.i;
    }

    public HostnameVerifier j() {
        return this.j;
    }

    public g k() {
        return this.k;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        if (this.a.equals(aVar.a) && this.b.equals(aVar.b) && this.d.equals(aVar.d) && this.e.equals(aVar.e) && this.f.equals(aVar.f) && this.g.equals(aVar.g) && j.a(this.h, aVar.h) && j.a(this.i, aVar.i) && j.a(this.j, aVar.j) && j.a(this.k, aVar.k)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int hashCode2 = (((((((((((this.a.hashCode() + 527) * 31) + this.b.hashCode()) * 31) + this.d.hashCode()) * 31) + this.e.hashCode()) * 31) + this.f.hashCode()) * 31) + this.g.hashCode()) * 31;
        if (this.h != null) {
            hashCode = this.h.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.i != null) {
            hashCode = this.i.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.j != null) {
            hashCode = this.j.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + hashCode2) * 31;
        if (this.k != null) {
            i = this.k.hashCode();
        }
        return hashCode + i;
    }
}
