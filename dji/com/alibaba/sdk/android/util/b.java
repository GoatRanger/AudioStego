package com.alibaba.sdk.android.util;

import com.alibaba.sdk.android.ResultCode;
import com.alibaba.sdk.android.callback.FailureCallback;

final class b implements Runnable {
    final /* synthetic */ FailureCallback a;
    final /* synthetic */ ResultCode b;

    b(FailureCallback failureCallback, ResultCode resultCode) {
        this.a = failureCallback;
        this.b = resultCode;
    }

    public final void run() {
        if (this.a != null) {
            this.a.onFailure(this.b.code, this.b.message);
        }
    }
}
