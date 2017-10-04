package com.tencent.android.tpush.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.common.g;

class k extends BroadcastReceiver {
    final /* synthetic */ a a;

    private k(a aVar) {
        this.a = aVar;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null && context != null) {
            g.a().a(new j(this.a, context, intent));
        }
    }
}
