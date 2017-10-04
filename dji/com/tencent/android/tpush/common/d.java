package com.tencent.android.tpush.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class d extends BroadcastReceiver {
    private d() {
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null && context != null) {
            g.a().a(new c(context, intent));
        }
    }
}
