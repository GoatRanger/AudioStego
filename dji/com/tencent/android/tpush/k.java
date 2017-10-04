package com.tencent.android.tpush;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import com.tencent.android.tpush.common.Constants;

class k implements OnClickListener {
    final /* synthetic */ Intent a;
    final /* synthetic */ XGPushActivity b;

    k(XGPushActivity xGPushActivity, Intent intent) {
        this.b = xGPushActivity;
        this.a = intent;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.putExtra("action", 5);
        this.b.broadcastToTPushService(this.a);
        Intent intent = new Intent(this.b, XGDownloadService.class);
        intent.putExtras(this.a);
        intent.putExtra(Constants.FLAG_PACKAGE_DOWNLOAD_URL, this.a.getStringExtra(Constants.FLAG_PACKAGE_DOWNLOAD_URL));
        this.b.startService(intent);
        this.b.finish();
    }
}
