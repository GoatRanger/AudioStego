package com.tencent.android.tpush.service;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.os.Process;
import com.d.a;
import com.d.c;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.common.p;
import com.tencent.android.tpush.service.d.e;

@c(a = 1, b = 3, c = "20150316", e = {a.SERVICESCHECK}, f = "确认已进行安全校验")
public class XGPushService extends Service {
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void a() {
        if (e.b(getApplicationContext(), "com.tencent.android.tpush.debug," + getApplicationContext().getPackageName(), 0) != 1) {
        }
    }

    public void onCreate() {
        super.onCreate();
        if (VERSION.SDK_INT < 18) {
            startForeground(-1998, new Notification());
        }
        Context applicationContext = getApplicationContext();
        com.tencent.android.tpush.service.c.a.a(applicationContext);
        l.c(applicationContext);
        a();
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.a.a.a("XGPushService", "onCreate() : " + getPackageName());
        }
        l.a().b();
    }

    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, 1, i2);
        if (p.a(getApplicationContext()) > 0) {
            Process.killProcess(Process.myPid());
            return 2;
        }
        a();
        e.a(l.e(), "tpush.wifi.bandon", "", true);
        l.a().a(intent);
        return 1;
    }

    public void onDestroy() {
        l.a().c();
        super.onDestroy();
    }
}
