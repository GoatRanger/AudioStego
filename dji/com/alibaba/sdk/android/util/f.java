package com.alibaba.sdk.android.util;

import android.content.DialogInterface.OnCancelListener;
import com.alibaba.sdk.android.c.a;

final class f implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ boolean b;
    final /* synthetic */ boolean c;
    final /* synthetic */ OnCancelListener d;
    final /* synthetic */ DialogHelper e;

    f(DialogHelper dialogHelper, String str, boolean z, boolean z2, OnCancelListener onCancelListener) {
        this.e = dialogHelper;
        this.a = str;
        this.b = z;
        this.c = z2;
        this.d = onCancelListener;
    }

    public final void run() {
        if (this.e.activity != null && !this.e.activity.isFinishing()) {
            this.e.a = new a(this.e.activity);
            this.e.a.setMessage(this.a);
            ((a) this.e.a).a(this.b);
            this.e.a.setCancelable(this.c);
            this.e.a.setOnCancelListener(this.d);
            this.e.a.show();
            this.e.a.setCanceledOnTouchOutside(false);
        }
    }
}
