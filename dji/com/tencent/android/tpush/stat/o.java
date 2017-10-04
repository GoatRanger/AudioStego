package com.tencent.android.tpush.stat;

final class o implements Runnable {
    o() {
    }

    public void run() {
        try {
            h.b();
        } catch (Throwable th) {
            h.d.b(th);
        }
    }
}
