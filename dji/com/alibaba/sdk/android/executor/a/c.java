package com.alibaba.sdk.android.executor.a;

import com.alibaba.sdk.android.SdkConstants;
import com.alibaba.sdk.android.trace.AliSDKLogger;

final class c implements Runnable {
    final /* synthetic */ Runnable a;
    final /* synthetic */ a b;

    c(a aVar, Runnable runnable) {
        this.b = aVar;
        this.a = runnable;
    }

    public final void run() {
        try {
            this.a.run();
        } catch (Throwable th) {
            AliSDKLogger.e(SdkConstants.KERNEL_NAME, th.getMessage(), th);
        }
    }
}
