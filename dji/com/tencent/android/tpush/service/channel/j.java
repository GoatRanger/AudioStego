package com.tencent.android.tpush.service.channel;

import com.tencent.android.tpush.service.channel.a.a;
import com.tencent.android.tpush.service.channel.b.i;
import com.tencent.android.tpush.service.channel.c.d;
import com.tencent.android.tpush.service.o;

class j implements Runnable {
    final /* synthetic */ b a;
    private a b = null;
    private i c = null;

    public j(b bVar, a aVar, i iVar) {
        this.a = bVar;
        this.b = aVar;
        this.c = iVar;
    }

    public void run() {
        try {
            o.a().a(d.a(this.c.h(), this.c.k()), this.b.f());
        } catch (Throwable e) {
            com.tencent.android.tpush.a.a.c("TpnsChannel", "run", e);
        }
    }
}
