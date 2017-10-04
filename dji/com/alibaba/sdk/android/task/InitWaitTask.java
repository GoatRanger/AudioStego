package com.alibaba.sdk.android.task;

import android.app.Activity;
import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.b.a;
import com.alibaba.sdk.android.callback.FailureCallback;
import com.alibaba.sdk.android.kernel.message.KernelMessageConstants;
import com.alibaba.sdk.android.message.MessageUtils;
import com.alibaba.sdk.android.trace.ActionTraceLogger;
import com.alibaba.sdk.android.trace.TraceLoggerService;
import com.alibaba.sdk.android.ut.UserTrackerService;
import com.alibaba.sdk.android.util.CommonUtils;
import com.alibaba.sdk.android.util.ReflectionUtils;

public class InitWaitTask extends TaskWithDialog<Void, Void, Void> {
    private Runnable a;
    private String b;
    private Class<?> c;
    protected FailureCallback failureCallback;

    public InitWaitTask(Activity activity, FailureCallback failureCallback, Runnable runnable, String str) {
        super(activity);
        this.a = runnable;
        this.failureCallback = failureCallback;
        this.b = str;
        this.c = ReflectionUtils.loadClassQuietly("com.alibaba.sdk.android.session.CredentialService");
    }

    public InitWaitTask(Activity activity, FailureCallback failureCallback, Runnable runnable) {
        this(activity, failureCallback, runnable, null);
    }

    protected Void asyncExecute(Void... voidArr) {
        UserTrackerService userTrackerService = (UserTrackerService) a.e.a(UserTrackerService.class, null);
        ActionTraceLogger begin = ((TraceLoggerService) a.e.a(TraceLoggerService.class, null)).action(1, this.b == null ? "asyncExecute" : this.b).begin();
        if (!(this.b == null || userTrackerService == null)) {
            userTrackerService.sendCustomHit(this.b, this.activity);
        }
        Boolean a = a.a();
        if (a == null) {
            begin.failed("error", "init failed");
            CommonUtils.onFailure(this.failureCallback, MessageUtils.createMessage(KernelMessageConstants.SDK_NOT_INIT, new Object[0]));
        } else if (a.booleanValue()) {
            if (this.c != null) {
                Object a2 = a.e.a(this.c, null);
                if (a2 != null) {
                    ReflectionUtils.invoke("com.alibaba.sdk.android.session.CredentialService", "init", null, a2, null);
                }
            }
            begin.success();
            this.a.run();
        } else {
            AlibabaSDK.asyncInitWithFinish(a.a, new f(this, begin));
        }
        return null;
    }

    protected void doWhenException(Throwable th) {
        CommonUtils.toastSystemException();
    }
}
