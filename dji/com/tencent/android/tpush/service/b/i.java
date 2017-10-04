package com.tencent.android.tpush.service.b;

import android.content.Context;
import com.tencent.android.tpush.a.a;

class i implements Runnable {
    int a;
    Context b;
    final /* synthetic */ a c;

    public i(a aVar, Context context, int i) {
        this.c = aVar;
        this.b = context;
        this.a = i;
    }

    public void run() {
        switch (this.a) {
            case 1:
                this.c.b(this.b, null);
                return;
            case 2:
                this.c.a(this.b, Long.valueOf(-1));
                return;
            default:
                a.h("MessageManager", "unknown report type");
                return;
        }
    }
}
