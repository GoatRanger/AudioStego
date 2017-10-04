package com.tencent.android.tpush.stat.b;

import android.content.Context;
import android.provider.Settings.System;
import com.tencent.android.tpush.stat.a.h;

public class f extends g {
    public f(Context context) {
        super(context);
    }

    protected boolean a() {
        return h.a(this.a, "android.permission.WRITE_SETTINGS");
    }

    protected String b() {
        String string;
        synchronized (this) {
            string = System.getString(this.a.getContentResolver(), f());
        }
        return string;
    }

    protected void a(String str) {
        synchronized (this) {
            System.putString(this.a.getContentResolver(), f(), str);
        }
    }
}
