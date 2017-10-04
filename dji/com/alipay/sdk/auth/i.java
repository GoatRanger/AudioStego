package com.alipay.sdk.auth;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class i implements OnClickListener {
    final /* synthetic */ g a;

    i(g gVar) {
        this.a = gVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.a.cancel();
        this.a.b.a.g = false;
        j.a(this.a.b.a, this.a.b.a.d + "?resultCode=150");
        this.a.b.a.finish();
    }
}
