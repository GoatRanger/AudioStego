package com.alibaba.sdk.android.util;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface.OnCancelListener;

public class DialogHelper {
    private ProgressDialog a;
    public Activity activity;

    public DialogHelper(Activity activity) {
        this.activity = activity;
    }

    public static void showAlertDialog(Activity activity, Runnable runnable) {
        Builder builder = new Builder(activity);
        builder.setTitle(17039380);
        builder.setMessage(ResourceUtils.getString("com_taobao_tae_sdk_alert_message"));
        builder.setPositiveButton(17039370, new e(runnable));
        builder.create().show();
    }

    public void showProgressDialog(boolean z, String str) {
        showProgressDialog(str, true, null, z);
    }

    public void showLoadingProgressDialog() {
        showProgressDialog(ResourceUtils.getString("com_taobao_tae_sdk_loading_progress_message"));
    }

    public void showProgressDialog(String str) {
        showProgressDialog(str, false, null, true);
    }

    public void showProgressDialog(String str, boolean z, OnCancelListener onCancelListener, boolean z2) {
        dismissProgressDialog();
        this.activity.runOnUiThread(new f(this, str, z2, z, onCancelListener));
    }

    public void dismissProgressDialog() {
        this.activity.runOnUiThread(new g(this));
    }
}
