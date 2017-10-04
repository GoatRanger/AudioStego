package com.alibaba.sdk.android.util;

import android.widget.Toast;
import com.alibaba.sdk.android.b.a;

final class d implements Runnable {
    final /* synthetic */ String a;

    d(String str) {
        this.a = str;
    }

    public final void run() {
        Toast.makeText(a.a, ResourceUtils.getString(this.a), 0).show();
    }
}
