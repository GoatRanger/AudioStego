package dji.thirdparty.b.a.b;

import dji.thirdparty.b.a;
import dji.thirdparty.b.a.i;
import dji.thirdparty.b.af;
import dji.thirdparty.b.u;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public final class p {
    private final a a;
    private final i b;
    private Proxy c;
    private InetSocketAddress d;
    private List<Proxy> e = Collections.emptyList();
    private int f;
    private List<InetSocketAddress> g = Collections.emptyList();
    private int h;
    private final List<af> i = new ArrayList();

    public p(a aVar, i iVar) {
        this.a = aVar;
        this.b = iVar;
        a(aVar.a(), aVar.h());
    }

    public boolean a() {
        return e() || c() || g();
    }

    public af b() throws IOException {
        if (!e()) {
            if (c()) {
                this.c = d();
            } else if (g()) {
                return h();
            } else {
                throw new NoSuchElementException();
            }
        }
        this.d = f();
        af afVar = new af(this.a, this.c, this.d);
        if (!this.b.c(afVar)) {
            return afVar;
        }
        this.i.add(afVar);
        return b();
    }

    public void a(af afVar, IOException iOException) {
        if (!(afVar.b().type() == Type.DIRECT || this.a.g() == null)) {
            this.a.g().connectFailed(this.a.a().b(), afVar.b().address(), iOException);
        }
        this.b.a(afVar);
    }

    private void a(u uVar, Proxy proxy) {
        if (proxy != null) {
            this.e = Collections.singletonList(proxy);
        } else {
            this.e = new ArrayList();
            Collection select = this.a.g().select(uVar.b());
            if (select != null) {
                this.e.addAll(select);
            }
            this.e.removeAll(Collections.singleton(Proxy.NO_PROXY));
            this.e.add(Proxy.NO_PROXY);
        }
        this.f = 0;
    }

    private boolean c() {
        return this.f < this.e.size();
    }

    private Proxy d() throws IOException {
        if (c()) {
            List list = this.e;
            int i = this.f;
            this.f = i + 1;
            Proxy proxy = (Proxy) list.get(i);
            a(proxy);
            return proxy;
        }
        throw new SocketException("No route to " + this.a.a().i() + "; exhausted proxy configurations: " + this.e);
    }

    private void a(Proxy proxy) throws IOException {
        int j;
        String str;
        this.g = new ArrayList();
        String i;
        if (proxy.type() == Type.DIRECT || proxy.type() == Type.SOCKS) {
            i = this.a.a().i();
            j = this.a.a().j();
            str = i;
        } else {
            SocketAddress address = proxy.address();
            if (address instanceof InetSocketAddress) {
                InetSocketAddress inetSocketAddress = (InetSocketAddress) address;
                i = a(inetSocketAddress);
                j = inetSocketAddress.getPort();
                str = i;
            } else {
                throw new IllegalArgumentException("Proxy.address() is not an InetSocketAddress: " + address.getClass());
            }
        }
        if (j < 1 || j > 65535) {
            throw new SocketException("No route to " + str + ":" + j + "; port is out of range");
        }
        if (proxy.type() == Type.SOCKS) {
            this.g.add(InetSocketAddress.createUnresolved(str, j));
        } else {
            List a = this.a.b().a(str);
            int size = a.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.g.add(new InetSocketAddress((InetAddress) a.get(i2), j));
            }
        }
        this.h = 0;
    }

    static String a(InetSocketAddress inetSocketAddress) {
        InetAddress address = inetSocketAddress.getAddress();
        if (address == null) {
            return inetSocketAddress.getHostName();
        }
        return address.getHostAddress();
    }

    private boolean e() {
        return this.h < this.g.size();
    }

    private InetSocketAddress f() throws IOException {
        if (e()) {
            List list = this.g;
            int i = this.h;
            this.h = i + 1;
            return (InetSocketAddress) list.get(i);
        }
        throw new SocketException("No route to " + this.a.a().i() + "; exhausted inet socket addresses: " + this.g);
    }

    private boolean g() {
        return !this.i.isEmpty();
    }

    private af h() {
        return (af) this.i.remove(0);
    }
}
