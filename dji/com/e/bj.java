package com.e;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import lecho.lib.hellocharts.model.h;

public class bj<K, V> {
    private final LinkedHashMap<K, V> a;
    private int b;
    private int c;

    public bj(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.c = i;
        this.a = new LinkedHashMap(0, h.l, true);
    }

    private void a(int i) {
        while (true) {
            Object key;
            Object value;
            synchronized (this) {
                if (this.b >= 0 && (!this.a.isEmpty() || this.b == 0)) {
                    if (this.b <= i) {
                        return;
                    }
                    Entry entry = null;
                    for (Entry entry2 : this.a.entrySet()) {
                    }
                    if (entry == null) {
                        return;
                    }
                    key = entry.getKey();
                    value = entry.getValue();
                    this.a.remove(key);
                    this.b -= c(key, value);
                }
            }
            a(true, key, value, null);
        }
        throw new IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
    }

    private int c(K k, V v) {
        int a = a(k, v);
        if (a >= 0) {
            return a;
        }
        throw new IllegalStateException("Negative size: " + k + "=" + v);
    }

    protected int a(K k, V v) {
        return 1;
    }

    protected V a(K k) {
        return null;
    }

    public final void a() {
        a(-1);
    }

    protected void a(boolean z, K k, V v, V v2) {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final V b(K r5) {
        /*
        r4 = this;
        if (r5 != 0) goto L_0x000a;
    L_0x0002:
        r0 = new java.lang.NullPointerException;
        r1 = "key == null";
        r0.<init>(r1);
        throw r0;
    L_0x000a:
        monitor-enter(r4);
        r0 = r4.a;	 Catch:{ all -> 0x001e }
        r0 = r0.get(r5);	 Catch:{ all -> 0x001e }
        if (r0 == 0) goto L_0x0015;
    L_0x0013:
        monitor-exit(r4);	 Catch:{ all -> 0x001e }
    L_0x0014:
        return r0;
    L_0x0015:
        monitor-exit(r4);	 Catch:{ all -> 0x001e }
        r1 = r4.a(r5);
        if (r1 != 0) goto L_0x0021;
    L_0x001c:
        r0 = 0;
        goto L_0x0014;
    L_0x001e:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x001e }
        throw r0;
    L_0x0021:
        monitor-enter(r4);
        r0 = r4.a;	 Catch:{ all -> 0x0041 }
        r0 = r0.put(r5, r1);	 Catch:{ all -> 0x0041 }
        if (r0 == 0) goto L_0x0037;
    L_0x002a:
        r2 = r4.a;	 Catch:{ all -> 0x0041 }
        r2.put(r5, r0);	 Catch:{ all -> 0x0041 }
    L_0x002f:
        monitor-exit(r4);	 Catch:{ all -> 0x0041 }
        if (r0 == 0) goto L_0x0044;
    L_0x0032:
        r2 = 0;
        r4.a(r2, r5, r1, r0);
        goto L_0x0014;
    L_0x0037:
        r2 = r4.b;	 Catch:{ all -> 0x0041 }
        r3 = r4.c(r5, r1);	 Catch:{ all -> 0x0041 }
        r2 = r2 + r3;
        r4.b = r2;	 Catch:{ all -> 0x0041 }
        goto L_0x002f;
    L_0x0041:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0041 }
        throw r0;
    L_0x0044:
        r0 = r4.c;
        r4.a(r0);
        r0 = r1;
        goto L_0x0014;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.bj.b(java.lang.Object):V");
    }

    public final V b(K k, V v) {
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        V put;
        synchronized (this) {
            this.b += c(k, v);
            put = this.a.put(k, v);
            if (put != null) {
                this.b -= c(k, put);
            }
        }
        if (put != null) {
            a(false, k, put, v);
        }
        a(this.c);
        return put;
    }

    public final V c(K k) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        V remove;
        synchronized (this) {
            remove = this.a.remove(k);
            if (remove != null) {
                this.b -= c(k, remove);
            }
        }
        if (remove != null) {
            a(false, k, remove, null);
        }
        return remove;
    }
}
