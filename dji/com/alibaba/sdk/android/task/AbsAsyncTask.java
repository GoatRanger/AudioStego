package com.alibaba.sdk.android.task;

import android.os.AsyncTask;
import com.alibaba.sdk.android.SdkConstants;
import com.alibaba.sdk.android.trace.AliSDKLogger;

public abstract class AbsAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {
    protected abstract Result asyncExecute(Params... paramsArr);

    protected abstract void doFinally();

    protected abstract void doWhenException(Throwable th);

    protected Result doInBackground(Params... paramsArr) {
        Result asyncExecute;
        try {
            asyncExecute = asyncExecute(paramsArr);
            return asyncExecute;
        } catch (Throwable th) {
            asyncExecute = th;
            AliSDKLogger.e(SdkConstants.KERNEL_NAME, asyncExecute.getMessage(), asyncExecute);
            doWhenException(asyncExecute);
            return null;
        } finally {
            doFinally();
        }
    }
}
