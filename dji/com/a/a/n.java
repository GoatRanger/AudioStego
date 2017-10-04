package com.a.a;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import java.util.HashMap;
import java.util.Map;

class n {
    final HandlerThread a = new HandlerThread("SegmentAnalytics-Stats", 10);
    final a b;
    long c;
    long d;
    long e;
    long f;
    Map<String, Long> g = new HashMap();

    private static class a extends Handler {
        private final n a;

        a(Looper looper, n nVar) {
            super(looper);
            this.a = nVar;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.b(message.arg1);
                    return;
                case 2:
                    this.a.a((Pair) message.obj);
                    return;
                default:
                    throw new AssertionError("Unknown Stats handler message: " + message);
            }
        }
    }

    n() {
        this.a.start();
        this.b = new a(this.a.getLooper(), this);
    }

    void a(int i) {
        this.b.sendMessage(this.b.obtainMessage(1, i, 0));
    }

    void b(int i) {
        this.c++;
        this.d += (long) i;
    }

    void a(String str, long j) {
        this.b.sendMessage(this.b.obtainMessage(2, new Pair(str, Long.valueOf(j))));
    }

    void a(Pair<String, Long> pair) {
        this.e++;
        this.f = ((Long) pair.second).longValue() + this.f;
        Long l = (Long) this.g.get(pair.first);
        if (l == null) {
            this.g.put(pair.first, pair.second);
        } else {
            this.g.put(pair.first, Long.valueOf(l.longValue() + ((Long) pair.second).longValue()));
        }
    }
}
