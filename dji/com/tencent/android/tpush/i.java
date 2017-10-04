package com.tencent.android.tpush;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;

class i implements OnCancelListener {
    final /* synthetic */ Intent a;
    final /* synthetic */ XGPushActivity b;

    i(XGPushActivity xGPushActivity, Intent intent) {
        this.b = xGPushActivity;
        this.a = intent;
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.a.putExtra("action", 4);
        this.b.broadcastToTPushService(this.a);
        this.b.finish();
    }
}
