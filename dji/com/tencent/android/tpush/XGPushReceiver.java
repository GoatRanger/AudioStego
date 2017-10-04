package com.tencent.android.tpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.d.a;
import com.d.c;
import com.tencent.android.tpush.b.f;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.p;
import com.tencent.android.tpush.horse.g;
import com.tencent.android.tpush.service.l;

@c(a = 1, b = 3, c = "20150316", e = {a.RECEIVERCHECK}, f = "确认已进行安全校验")
public class XGPushReceiver extends BroadcastReceiver {
    private static final String a = XGPushReceiver.class.getSimpleName();

    public void onReceive(Context context, Intent intent) {
        if (context != null && intent != null && p.h(context)) {
            String action = intent.getAction();
            if (action != null) {
                l.c(context.getApplicationContext());
                if (XGPushConfig.enableDebug) {
                    com.tencent.android.tpush.a.a.c(a, "PushReceiver received " + action + " @@ " + context.getPackageName());
                }
                if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
                    g.a().a(intent);
                } else if (Constants.ACTION_INTERNAL_PUSH_MESSAGE.equals(action)) {
                    f.a(context).a(intent);
                } else {
                    l.a(context);
                }
            }
        }
    }
}
