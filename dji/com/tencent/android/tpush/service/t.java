package com.tencent.android.tpush.service;

import android.app.AlarmManager;
import android.app.PendingIntent;

public class t {
    private static t a = new t();
    private static AlarmManager b = null;

    private t() {
    }

    public static t a() {
        if (b == null) {
            b();
        }
        return a;
    }

    public void a(int i, long j, PendingIntent pendingIntent) {
        if (b != null) {
            b.set(i, j, pendingIntent);
        }
    }

    private static synchronized void b() {
        synchronized (t.class) {
            if (b == null && l.e() != null) {
                b = (AlarmManager) l.e().getSystemService("alarm");
            }
        }
    }
}
