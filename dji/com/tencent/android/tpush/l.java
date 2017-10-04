package com.tencent.android.tpush;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;

class l implements OnCancelListener {
    final /* synthetic */ Intent a;
    final /* synthetic */ XGPushActivity b;

    l(XGPushActivity xGPushActivity, Intent intent) {
        this.b = xGPushActivity;
        this.a = intent;
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.a.putExtra("action", 6);
        this.b.broadcastToTPushService(this.a);
        this.b.finish();
    }
}
