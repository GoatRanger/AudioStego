package com.alibaba.sdk.android.task;

import com.alibaba.sdk.android.ResultCode;
import com.alibaba.sdk.android.callback.InitResultCallback;
import com.alibaba.sdk.android.trace.ActionTraceLogger;

final class b implements InitResultCallback {
    final /* synthetic */ ActionTraceLogger a;
    final /* synthetic */ InitResultCallback b;
    final /* synthetic */ a c;

    b(a aVar, ActionTraceLogger actionTraceLogger, InitResultCallback initResultCallback) {
        this.c = aVar;
        this.a = actionTraceLogger;
        this.b = initResultCallback;
    }

    public final void onFailure(int i, String str) {
        a aVar = this.c;
        a.a(false, this.a.getCaseTime(), str);
        this.a.failed();
        if (this.b != null) {
            this.b.onFailure(i, str);
        }
        a.a(this.c, false, i, str);
    }

    public final void onSuccess() {
        a aVar = this.c;
        a.a(true, this.a.getCaseTime(), null);
        this.a.success();
        if (this.b != null) {
            this.b.onSuccess();
        }
        a.a(this.c, true, ResultCode.SUCCESS.code, null);
    }
}
