package com.alibaba.sdk.android.trace;

import com.alibaba.sdk.android.SdkConstants;
import java.lang.Thread.UncaughtExceptionHandler;

public class b implements UncaughtExceptionHandler {
    private boolean a;
    private UncaughtExceptionHandler b;

    public final synchronized void a() {
        if (!this.a) {
            this.a = true;
            UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            if (this.b == null || !(this.b.equals(this) || this.b.getClass().equals(b.class))) {
                this.b = defaultUncaughtExceptionHandler;
                Thread.setDefaultUncaughtExceptionHandler(this);
            }
        }
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (th != null) {
            AliSDKLogger.e(3, SdkConstants.KERNEL_NAME, "UncaughtException", thread.getName(), th);
        }
        if (this.b != null && this.b != this && !this.b.getClass().equals(b.class)) {
            this.b.uncaughtException(thread, th);
        }
    }
}
