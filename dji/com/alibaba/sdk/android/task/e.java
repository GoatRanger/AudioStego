package com.alibaba.sdk.android.task;

final class e implements Runnable {
    final /* synthetic */ a a;

    e(a aVar) {
        this.a = aVar;
    }

    public final void run() {
        if (this.a.a != null) {
            this.a.a.onSuccess();
        }
    }
}
