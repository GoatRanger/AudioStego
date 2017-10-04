package com.tencent.android.tpush.service;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.l;
import com.tencent.android.tpush.service.channel.b;
import com.tencent.android.tpush.service.d.e;

class m extends Handler {
    final /* synthetic */ l a;

    m(l lVar, Looper looper) {
        this.a = lVar;
        super(looper);
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message != null) {
            switch (message.what) {
                case 1:
                    if (l.a(this.a)) {
                        if (!l.h()) {
                            if (XGPushConfig.enableDebug) {
                                a.e("PushServiceManager", "Service's first running at " + l.g().getPackageName() + " version : " + Constants.PUSH_SDK_VERSION);
                            }
                            l.a(true);
                            if (!l.a(l.g())) {
                                a.h(Constants.ServiceLogTag, "permission check failed, kill service!");
                                this.a.d();
                                Process.killProcess(Process.myPid());
                            }
                            e.b(l.g());
                            a.a().a(l.g());
                        }
                        b.a().b();
                        return;
                    }
                    Intent intent = new Intent();
                    intent.setClass(l.g(), XGPushService.class);
                    l.g().stopService(intent);
                    return;
                case 2:
                    b.a().b();
                    return;
                case 3:
                    b.a().c();
                    return;
                default:
                    a.h("PushServiceManager", "unknown handler msg = " + message.what);
                    return;
            }
        }
    }
}
