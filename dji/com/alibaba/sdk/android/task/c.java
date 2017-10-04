package com.alibaba.sdk.android.task;

import com.alibaba.sdk.android.SdkConstants;
import com.alibaba.sdk.android.callback.InitResultCallback;
import com.alibaba.sdk.android.trace.AliSDKLogger;

final class c implements Runnable {
    final /* synthetic */ InitResultCallback[] a;
    final /* synthetic */ boolean b;
    final /* synthetic */ int c;
    final /* synthetic */ String d;
    final /* synthetic */ a e;

    c(a aVar, InitResultCallback[] initResultCallbackArr, boolean z, int i, String str) {
        this.e = aVar;
        this.a = initResultCallbackArr;
        this.b = z;
        this.c = i;
        this.d = str;
    }

    public final void run() {
        for (InitResultCallback initResultCallback : this.a) {
            try {
                if (this.b) {
                    initResultCallback.onSuccess();
                } else {
                    initResultCallback.onFailure(this.c, this.d);
                }
            } catch (Throwable e) {
                AliSDKLogger.e(SdkConstants.KERNEL_NAME, e.getMessage(), e);
            }
        }
    }
}
