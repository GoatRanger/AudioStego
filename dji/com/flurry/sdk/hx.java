package com.flurry.sdk;

import com.here.odnp.config.OdnpConfigStatic;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public final class hx extends im<iv> {
    private static hx a = null;

    public static synchronized hx a() {
        hx hxVar;
        synchronized (hx.class) {
            if (a == null) {
                a = new hx();
            }
            hxVar = a;
        }
        return hxVar;
    }

    public static synchronized void b() {
        synchronized (hx.class) {
            if (a != null) {
                a.c();
            }
            a = null;
        }
    }

    protected hx() {
        super(hx.class.getName(), 0, 5, OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(11, new ik()));
    }
}
