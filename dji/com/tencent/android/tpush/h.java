package com.tencent.android.tpush;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

class h implements OnClickListener {
    final /* synthetic */ Intent a;
    final /* synthetic */ XGPushActivity b;

    h(XGPushActivity xGPushActivity, Intent intent) {
        this.b = xGPushActivity;
        this.a = intent;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.putExtra("action", 3);
        this.b.broadcastToTPushService(this.a);
        try {
            this.b.startActivity(this.a);
        } catch (ActivityNotFoundException e) {
        }
        this.b.finish();
    }
}
