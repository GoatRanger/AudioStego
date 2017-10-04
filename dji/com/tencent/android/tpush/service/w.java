package com.tencent.android.tpush.service;

import com.tencent.android.tpush.a.a;

class w implements Runnable {
    final /* synthetic */ XGWatchdog a;

    w(XGWatchdog xGWatchdog) {
        this.a = xGWatchdog;
    }

    public void run() {
        try {
            String access$000 = this.a.directSendContent("ver:");
            Integer valueOf = Integer.valueOf(0);
            if (access$000 != null) {
                try {
                    valueOf = Integer.valueOf(access$000);
                } catch (NumberFormatException e) {
                }
            }
            if (valueOf.intValue() == 0) {
                this.a.directSendContent("exit:");
                Thread.sleep(500);
                this.a.directStartWatchdog();
            }
            this.a.isStarted = true;
        } catch (Throwable th) {
            a.h(XGWatchdog.TAG, "jniStartWatchdog error:" + th.getMessage());
        }
    }
}
