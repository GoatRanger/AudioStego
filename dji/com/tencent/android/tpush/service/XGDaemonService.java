package com.tencent.android.tpush.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.d.a;
import com.d.c;

@c(a = 1, b = 3, c = "20150316", e = {a.SERVICESCHECK}, f = "确认已进行安全校验")
public class XGDaemonService extends Service {
    public int onStartCommand(Intent intent, int i, int i2) {
        return 2;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }
}
