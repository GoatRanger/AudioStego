package com.tencent.android.tpush;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

class o implements OnClickListener {
    final /* synthetic */ Intent a;
    final /* synthetic */ XGPushActivity b;

    o(XGPushActivity xGPushActivity, Intent intent) {
        this.b = xGPushActivity;
        this.a = intent;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.b.broadcastToTPushService(this.a);
        dialogInterface.cancel();
        this.b.finish();
    }
}
