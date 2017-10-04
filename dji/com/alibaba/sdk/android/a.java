package com.alibaba.sdk.android;

import com.alibaba.sdk.android.callback.ResultCallback;

final class a implements Runnable {
    final /* synthetic */ Class a;
    final /* synthetic */ ResultCallback b;

    a(Class cls, ResultCallback resultCallback) {
        this.a = cls;
        this.b = resultCallback;
    }

    public final void run() {
        this.b.onSuccess(AlibabaSDK.getService(this.a));
    }
}
