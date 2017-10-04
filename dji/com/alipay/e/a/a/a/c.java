package com.alipay.e.a.a.a;

import com.alipay.f.a.a.b.a.b;
import com.alipay.f.a.a.b.b.d;

final class c implements Runnable {
    final /* synthetic */ b a;
    final /* synthetic */ b b;

    c(b bVar, b bVar2) {
        this.b = bVar;
        this.a = bVar2;
    }

    public final void run() {
        try {
            b.g = this.b.d.a(this.a);
            com.alipay.e.a.a.b.b.a("Rpc success.");
        } catch (Throwable th) {
            b.g = new d();
            b.g.d = false;
            b.g.e = "static data rpc upload error, " + com.alipay.e.a.a.b.d.b(th);
            com.alipay.e.a.a.b.b.a("Rpc failed.");
            com.alipay.e.a.a.b.b.a(com.alipay.e.a.a.b.d.b(th));
        }
    }
}
