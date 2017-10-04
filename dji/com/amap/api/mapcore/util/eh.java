package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Looper;
import java.util.List;

public class eh extends ei {
    private static boolean a = true;

    protected eh(int i) {
        super(i);
    }

    protected boolean a(Context context) {
        if (dq.m(context) != 1 || !a) {
            return false;
        }
        a = false;
        synchronized (Looper.getMainLooper()) {
            ev evVar = new ev(context);
            ew a = evVar.a();
            if (a == null) {
                return true;
            } else if (a.b()) {
                a.b(false);
                evVar.a(a);
                return true;
            } else {
                return false;
            }
        }
    }

    protected String a(List<dv> list) {
        return null;
    }
}
