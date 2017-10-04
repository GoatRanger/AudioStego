package com.alibaba.sdk.android.util;

import com.alibaba.sdk.android.SdkConstants;
import com.alibaba.sdk.android.trace.AliSDKLogger;

final class g implements Runnable {
    final /* synthetic */ DialogHelper a;

    g(DialogHelper dialogHelper) {
        this.a = dialogHelper;
    }

    public final void run() {
        if (this.a.a != null && this.a.a.isShowing()) {
            try {
                this.a.a.dismiss();
            } catch (Throwable e) {
                AliSDKLogger.e(SdkConstants.KERNEL_NAME, e.getMessage(), e);
            } finally {
                this.a.a = null;
            }
        }
    }
}
