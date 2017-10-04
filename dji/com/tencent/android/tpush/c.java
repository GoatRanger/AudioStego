package com.tencent.android.tpush;

import android.content.Context;
import com.tencent.android.tpush.common.m;
import com.tencent.android.tpush.horse.Tools;
import com.tencent.android.tpush.service.d.e;

final class c implements Runnable {
    final /* synthetic */ Context a;

    c(Context context) {
        this.a = context;
    }

    public void run() {
        if (!e.a(m.a(this.a, XGPush4Msdk.b(this.a), null))) {
            m.a(this.a, XGPush4Msdk.b(this.a));
            Tools.clearCacheServerItems(this.a);
            Tools.clearOptStrategyItem(this.a);
        }
    }
}
