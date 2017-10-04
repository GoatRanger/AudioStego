package com.alibaba.sdk.android.task;

import android.app.Activity;
import com.alibaba.sdk.android.util.DialogHelper;

public abstract class TaskWithDialog<Params, Progress, Result> extends AbsAsyncTask<Params, Progress, Result> {
    protected Activity activity;
    protected DialogHelper dialogHelper;

    public TaskWithDialog(Activity activity) {
        this.dialogHelper = new DialogHelper(activity);
        this.activity = activity;
    }

    protected void onPreExecute() {
        if (this.activity != null) {
            this.dialogHelper.showLoadingProgressDialog();
        }
    }

    protected void doFinally() {
        if (this.activity != null) {
            this.dialogHelper.dismissProgressDialog();
        }
    }
}
