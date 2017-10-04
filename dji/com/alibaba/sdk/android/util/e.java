package com.alibaba.sdk.android.util;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class e implements OnClickListener {
    final /* synthetic */ Runnable a;

    e(Runnable runnable) {
        this.a = runnable;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.a.run();
    }
}
