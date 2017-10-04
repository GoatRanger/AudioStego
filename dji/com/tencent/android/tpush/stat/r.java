package com.tencent.android.tpush.stat;

import com.tencent.android.tpush.XGPush4Msdk;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.common.p;
import com.tencent.android.tpush.stat.event.c;
import com.tencent.android.tpush.stat.event.d;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Arrays;

class r implements UncaughtExceptionHandler {
    r() {
    }

    public void uncaughtException(Thread thread, Throwable th) {
        p.a();
        if (c.c() && h.f != null) {
            int i;
            long accessId = XGPushConfig.getAccessId(h.f);
            if (accessId <= 0) {
                accessId = XGPush4Msdk.getQQAccessId(h.f);
            }
            if (h.g == null || th.toString().contains(h.g)) {
                i = 1;
            } else {
                i = 0;
            }
            if (i != 0) {
                c cVar = new c(h.f, h.b(h.f, accessId), 2, th, thread, accessId);
                h.b(Arrays.asList(new d[]{cVar}));
                h.d.g("has caught the following uncaught exception:");
                h.d.a(th);
            }
            if (h.e != null) {
                h.d.h("Call the original uncaught exception handler.");
                if (!(h.e instanceof r)) {
                    h.e.uncaughtException(thread, th);
                }
            }
        }
    }
}
