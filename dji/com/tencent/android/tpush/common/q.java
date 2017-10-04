package com.tencent.android.tpush.common;

import android.content.Context;
import com.tencent.android.tpush.service.l;

final class q implements Runnable {
    final /* synthetic */ Context a;

    q(Context context) {
        this.a = context;
    }

    public void run() {
        if (p.d(this.a) != 1) {
            l.a(this.a);
        }
    }
}
