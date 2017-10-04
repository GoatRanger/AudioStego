package com.alipay.sdk.auth;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class h implements OnClickListener {
    final /* synthetic */ g a;

    h(g gVar) {
        this.a = gVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.b.a.g = true;
        this.a.a.proceed();
        dialogInterface.dismiss();
    }
}
