package com.tencent.android.tpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.common.g;
import com.tencent.android.tpush.common.p;

class ac extends BroadcastReceiver {
    Context a = null;
    Intent b = null;
    XGIOperateCallback c = null;
    int d = 0;

    public ac(Context context, Intent intent, XGIOperateCallback xGIOperateCallback) {
        this.a = context;
        this.b = intent;
        this.c = xGIOperateCallback;
        this.d = intent.getIntExtra("opType", 0);
    }

    public void onReceive(Context context, Intent intent) {
        try {
            g.a().b().removeCallbacks((ad) XGPushManager.b().remove(this));
            switch (this.d) {
                case 0:
                    XGPushManager.b(this.a, this.b, this.c);
                    break;
                case 1:
                    XGPushManager.a(this.a, this.b, this.c);
                    break;
                default:
                    a.h(XGPushManager.a(), "RegisterStartReceiver error optype:" + this.d);
                    break;
            }
            p.a(this.a, this);
        } catch (Throwable e) {
            a.c(XGPushManager.a(), "RegisterStartReceiver error", e);
        }
    }
}
