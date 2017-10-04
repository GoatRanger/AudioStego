package com.alibaba.sdk.android.task;

import com.alibaba.sdk.android.callback.InitResultCallback;
import com.alibaba.sdk.android.trace.ActionTraceLogger;
import com.alibaba.sdk.android.util.CommonUtils;

final class f implements InitResultCallback {
    final /* synthetic */ ActionTraceLogger a;
    final /* synthetic */ InitWaitTask b;

    f(InitWaitTask initWaitTask, ActionTraceLogger actionTraceLogger) {
        this.b = initWaitTask;
        this.a = actionTraceLogger;
    }

    public final void onFailure(int i, String str) {
        this.a.failed("error", i + ":" + str);
        CommonUtils.onFailure(this.b.failureCallback, i, str);
    }

    public final void onSuccess() {
        this.a.success();
        this.b.a.run();
    }
}
