package com.tencent.android.tpush.rpc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.d.a;
import com.d.c;
import com.tencent.android.tpush.service.l;

@c(a = 1, b = 3, c = "20150316", e = {a.SERVICESCHECK}, f = "确认已进行安全校验")
public class XGRemoteService extends Service {
    private b a = new h();

    public IBinder onBind(Intent intent) {
        l.c(getApplicationContext());
        return this.a;
    }
}
