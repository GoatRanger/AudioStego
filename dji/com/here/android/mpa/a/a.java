package com.here.android.mpa.a;

import com.nokia.maps.annotation.Internal;
import com.nokia.maps.ck;
import com.nokia.maps.k;

@Internal
public final class a {
    private static a a;
    private ck b;

    public static a a() {
        if (a == null) {
            a = new a(ck.a());
        }
        return a;
    }

    private a(ck ckVar) {
        this.b = ckVar;
    }

    public void a(String str, double d, double d2, boolean z) {
        this.b.a(str, d, d2, z);
    }

    static {
        ck.a(new k<a, ck>() {
            public ck a(a aVar) {
                return aVar.b;
            }
        });
    }
}
