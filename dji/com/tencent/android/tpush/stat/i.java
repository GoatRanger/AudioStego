package com.tencent.android.tpush.stat;

import android.content.Context;
import com.tencent.android.tpush.stat.a.e;

final class i implements Runnable {
    final /* synthetic */ Context a;

    i(Context context) {
        this.a = context;
    }

    public void run() {
        a.a(h.f).e();
        e.a(this.a, true);
        f.b(this.a);
        h.e = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new r());
        if (c.a() == StatReportStrategy.APP_LAUNCH) {
            h.a(this.a, -1);
        }
        if (c.b()) {
            h.d.h("Init MTA StatService success.");
        }
        String h = e.h(h.f);
        if (h == null || h.trim().length() == 0) {
            h = "default";
        }
        h.h = h.f.getSharedPreferences("." + h, 0);
    }
}
