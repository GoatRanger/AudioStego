package dji.thirdparty.b.a;

import dji.thirdparty.b.a;
import dji.thirdparty.b.a.b.r;
import dji.thirdparty.b.a.c.b;
import dji.thirdparty.b.e;
import dji.thirdparty.b.f;
import dji.thirdparty.b.k;
import dji.thirdparty.b.l;
import dji.thirdparty.b.t;
import dji.thirdparty.b.u;
import dji.thirdparty.b.y;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;

public abstract class d {
    public static final Logger a = Logger.getLogger(y.class.getName());
    public static d b;

    public abstract r a(e eVar);

    public abstract b a(k kVar, a aVar, r rVar);

    public abstract e a(y yVar);

    public abstract i a(k kVar);

    public abstract u a(String str) throws MalformedURLException, UnknownHostException;

    public abstract void a(e eVar, f fVar, boolean z);

    public abstract void a(l lVar, SSLSocket sSLSocket, boolean z);

    public abstract void a(t.a aVar, String str);

    public abstract void a(t.a aVar, String str, String str2);

    public abstract void a(y.a aVar, e eVar);

    public abstract boolean a(k kVar, b bVar);

    public abstract void b(k kVar, b bVar);

    public static void a() {
        y yVar = new y();
    }
}
