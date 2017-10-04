package com.tencent.android.tpush;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

class j implements OnClickListener {
    final /* synthetic */ Intent a;
    final /* synthetic */ XGPushActivity b;

    j(XGPushActivity xGPushActivity, Intent intent) {
        this.b = xGPushActivity;
        this.a = intent;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.putExtra("action", 6);
        this.b.broadcastToTPushService(this.a);
        dialogInterface.dismiss();
        this.b.finish();
    }
}
