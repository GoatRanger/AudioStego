package com.tencent.android.tpush.service;

import android.os.PowerManager.WakeLock;

public class u {
    private static u a = null;
    private WakeLock b = null;

    private u() {
    }

    public static u a() {
        if (a == null) {
            a = new u();
        }
        return a;
    }

    public WakeLock b() {
        return this.b;
    }

    public void a(WakeLock wakeLock) {
        this.b = wakeLock;
    }
}
