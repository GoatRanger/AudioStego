package com.tencent.android.tpush;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

class n implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ Intent b;
    final /* synthetic */ XGPushActivity c;

    n(XGPushActivity xGPushActivity, String str, Intent intent) {
        this.c = xGPushActivity;
        this.a = str;
        this.b = intent;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.c.openUrl(this.a, this.b);
        this.c.finish();
    }
}
