package com.facebook.share.internal;

import android.content.Context;
import android.os.Bundle;
import com.facebook.internal.ab;
import com.facebook.internal.ad;

final class g extends ad {
    private String a;

    g(Context context, String str, String str2) {
        super(context, ab.L, ab.M, ab.n, str);
        this.a = str2;
    }

    protected void a(Bundle bundle) {
        bundle.putString(n.W, this.a);
    }
}
