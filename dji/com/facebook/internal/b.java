package com.facebook.internal;

import android.content.Intent;
import java.util.UUID;

public class b {
    private static b a;
    private UUID b;
    private Intent c;
    private int d;

    public static b a() {
        return a;
    }

    public static synchronized b a(UUID uuid, int i) {
        b a;
        synchronized (b.class) {
            a = a();
            if (a != null && a.c().equals(uuid) && a.d() == i) {
                a(null);
            } else {
                a = null;
            }
        }
        return a;
    }

    private static synchronized boolean a(b bVar) {
        boolean z;
        synchronized (b.class) {
            b a = a();
            a = bVar;
            z = a != null;
        }
        return z;
    }

    public b(int i) {
        this(i, UUID.randomUUID());
    }

    public b(int i, UUID uuid) {
        this.b = uuid;
        this.d = i;
    }

    public Intent b() {
        return this.c;
    }

    public UUID c() {
        return this.b;
    }

    public int d() {
        return this.d;
    }

    public void a(int i) {
        this.d = i;
    }

    public void a(Intent intent) {
        this.c = intent;
    }

    public boolean e() {
        return a(this);
    }
}
