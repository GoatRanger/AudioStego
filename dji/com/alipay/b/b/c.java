package com.alipay.b.b;

import com.alipay.e.a.a.b.d;

final class c implements Runnable {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public final void run() {
        try {
            this.a.a();
        } catch (Throwable th) {
            d.a(th);
        }
    }
}
