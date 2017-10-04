package com.amap.api.mapcore.util;

import java.net.Proxy;

public class fr {
    private fs a;
    private fw b;

    public interface a {
        void a(Throwable th);

        void a(byte[] bArr, long j);

        void d();

        void e();
    }

    public fr(fw fwVar) {
        this(fwVar, 0, -1);
    }

    public fr(fw fwVar, long j, long j2) {
        Proxy proxy;
        this.b = fwVar;
        if (fwVar.i == null) {
            proxy = null;
        } else {
            proxy = fwVar.i;
        }
        this.a = new fs(this.b.g, this.b.h, proxy);
        this.a.b(j2);
        this.a.a(j);
    }

    public void a(a aVar) {
        this.a.a(this.b.a(), this.b.c(), this.b.b(), aVar);
    }

    public void a() {
        this.a.a();
    }
}
