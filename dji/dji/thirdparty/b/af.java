package dji.thirdparty.b;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;

public final class af {
    final a a;
    final Proxy b;
    final InetSocketAddress c;

    public af(a aVar, Proxy proxy, InetSocketAddress inetSocketAddress) {
        if (aVar == null) {
            throw new NullPointerException("address == null");
        } else if (proxy == null) {
            throw new NullPointerException("proxy == null");
        } else if (inetSocketAddress == null) {
            throw new NullPointerException("inetSocketAddress == null");
        } else {
            this.a = aVar;
            this.b = proxy;
            this.c = inetSocketAddress;
        }
    }

    public a a() {
        return this.a;
    }

    public Proxy b() {
        return this.b;
    }

    public InetSocketAddress c() {
        return this.c;
    }

    public boolean d() {
        return this.a.i != null && this.b.type() == Type.HTTP;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof af)) {
            return false;
        }
        af afVar = (af) obj;
        if (this.a.equals(afVar.a) && this.b.equals(afVar.b) && this.c.equals(afVar.c)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((this.a.hashCode() + 527) * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
    }
}
