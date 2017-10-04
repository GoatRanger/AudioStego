package com.tencent.android.tpush.rpc;

import android.content.Intent;
import com.d.a;
import com.d.d;
import com.tencent.android.tpush.b.f;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.service.l;

public class h extends b {
    @d(a = 1, b = 3, c = "20150316", e = {a.INTENTSCHEMECHECK, a.INTENTCHECK}, f = "确认已进行安全校验")
    public void a(String str, d dVar) {
        try {
            f.a(l.e()).a(Intent.parseUri(str, 0));
            dVar.a();
        } catch (Throwable th) {
            com.tencent.android.tpush.a.a.c(Constants.ServiceLogTag, "Show", th);
        }
    }

    public void a() {
        try {
            l.a(l.e());
        } catch (Throwable th) {
            com.tencent.android.tpush.a.a.c(Constants.ServiceLogTag, "startService", th);
        }
    }

    public void b() {
    }
}
