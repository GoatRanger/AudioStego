package com.tencent.android.tpush;

import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.common.p;

class ad implements Runnable {
    Context a = null;
    Intent b = null;
    XGIOperateCallback c = null;
    int d = 0;

    public ad(Context context, Intent intent, XGIOperateCallback xGIOperateCallback) {
        this.a = context;
        this.b = intent;
        this.c = xGIOperateCallback;
        this.d = intent.getIntExtra("opType", 0);
    }

    public void run() {
        try {
            switch (this.d) {
                case 0:
                    XGPushManager.b(this.a, this.b, this.c);
                    break;
                case 1:
                    XGPushManager.a(this.a, this.b, this.c);
                    break;
                default:
                    a.h(XGPushManager.a(), "TimeoutRunnable error optype:" + this.d);
                    break;
            }
            for (ac a : XGPushManager.b().keySet()) {
                p.a(this.a, a);
            }
            XGPushManager.b().clear();
        } catch (Throwable e) {
            a.c(XGPushManager.a(), " RegisterTimeoutRunnable run error", e);
        }
    }
}
