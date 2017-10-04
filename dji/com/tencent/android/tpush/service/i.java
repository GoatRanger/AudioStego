package com.tencent.android.tpush.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.common.g;

class i extends BroadcastReceiver {
    final /* synthetic */ a a;

    private i(a aVar) {
        this.a = aVar;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null && context != null) {
            g.a().a(new j(this.a, context, intent));
        }
    }
}
