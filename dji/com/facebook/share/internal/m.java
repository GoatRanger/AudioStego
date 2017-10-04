package com.facebook.share.internal;

import android.os.Bundle;
import com.facebook.h;
import com.facebook.internal.b;
import com.facebook.k;

public abstract class m {
    private h a;

    public abstract void a(b bVar, Bundle bundle);

    public m(h hVar) {
        this.a = hVar;
    }

    public void a(b bVar) {
        if (this.a != null) {
            this.a.onCancel();
        }
    }

    public void a(b bVar, k kVar) {
        if (this.a != null) {
            this.a.onError(kVar);
        }
    }
}
