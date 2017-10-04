package com.tencent.android.tpush.service;

class v implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ x b;
    final /* synthetic */ XGWatchdog c;

    v(XGWatchdog xGWatchdog, String str, x xVar) {
        this.c = xGWatchdog;
        this.a = str;
        this.b = xVar;
    }

    public void run() {
        String access$000 = this.c.directSendContent(this.a);
        if (this.b != null) {
            this.b.a(access$000);
        }
    }
}
