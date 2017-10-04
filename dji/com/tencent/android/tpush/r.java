package com.tencent.android.tpush;

import android.content.Context;
import android.content.Intent;
import com.d.a;
import com.d.d;
import com.tencent.android.tpush.service.d.e;

final class r implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ boolean b;

    r(Context context, boolean z) {
        this.a = context;
        this.b = z;
    }

    @d(a = 1, b = 3, c = "20150316", e = {a.RECEIVERCHECK, a.INTENTCHECK}, f = "确认已进行安全校验")
    public void run() {
        e.a(this.a, "com.tencent.android.tpush.debug," + this.a.getPackageName(), this.b ? 1 : 0);
        Intent intent = new Intent("com.tencent.android.tpush.action.ENABLE_DEBUG");
        intent.putExtra("debugMode", this.b);
        this.a.sendBroadcast(intent);
    }
}
