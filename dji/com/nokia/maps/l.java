package com.nokia.maps;

import android.content.Context;

public class l {
    private static p a = (m.k() ? new r() : new q());

    public static p a() {
        return a;
    }

    static synchronized void a(Context context, boolean z) {
        synchronized (l.class) {
            a.a(context, z);
            a.a();
        }
    }
}
