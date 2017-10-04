package com.tencent.android.tpush.common;

import android.content.Context;

public class o {
    private static volatile o a = null;
    private boolean b;

    private o(Context context) {
        this.b = false;
        this.b = j.a();
    }

    public static o a(Context context) {
        if (a == null) {
            synchronized (o.class) {
                if (a == null) {
                    a = new o(context);
                }
            }
        }
        return a;
    }

    public boolean a() {
        return this.b;
    }
}
