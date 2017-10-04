package com.alipay.sdk.k;

import com.alipay.sdk.k.a.a;

final class b implements Runnable {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final void run() {
        if (this.a.e == null) {
            this.a.e = new a(this.a, this.a.f);
        }
        try {
            if (!this.a.e.isShowing()) {
                this.a.e.show();
            }
        } catch (Exception e) {
        }
    }
}
