package com.tencent.android.tpush;

import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.common.a;
import com.tencent.android.tpush.common.m;
import com.tencent.android.tpush.service.XGWatchdog;

class ab implements Runnable {
    private Context a;
    private Intent b;
    private XGIOperateCallback c;
    private int d;
    private int e = 0;

    public ab(XGIOperateCallback xGIOperateCallback, Context context, Intent intent, int i, int i2) {
        this.c = xGIOperateCallback;
        this.a = context;
        this.b = intent;
        this.d = i;
        this.e = i2;
    }

    public void run() {
        try {
            XGWatchdog.getInstance(this.a).startWatchdog();
            if (this.d != 1) {
                if (this.d == 0 && this.b != null) {
                    switch (this.b.getIntExtra("operation", -1)) {
                        case 100:
                            XGPushManager.b(this.a, this.b, this.c);
                            break;
                        case 101:
                            XGPushManager.a(this.a, this.b, this.c);
                            break;
                        default:
                            break;
                    }
                }
            } else if (!(this.c == null || this.b == null)) {
                String stringExtra = this.b.getStringExtra("data");
                switch (this.b.getIntExtra("operation", -1)) {
                    case 0:
                        this.c.onSuccess(stringExtra, this.b.getIntExtra("flag", -1));
                        if (this.e == 0) {
                            m.b(this.a, ".firstregister", 0);
                            break;
                        }
                        break;
                    case 1:
                        this.c.onFail(stringExtra, this.b.getIntExtra("code", -1), this.b.getStringExtra("msg"));
                        break;
                }
            }
            XGWatchdog.getInstance(this.a).sendAllLocalXGAppList();
            a.a(this.a);
        } catch (Throwable th) {
            com.tencent.android.tpush.a.a.h(XGPushManager.a(), th.toString());
        }
    }
}
