package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Looper;
import java.util.Date;
import java.util.List;

public class eg extends ei {
    private static boolean a = true;

    protected eg(int i) {
        super(i);
    }

    protected boolean a(Context context) {
        if (!a) {
            return false;
        }
        a = false;
        synchronized (Looper.getMainLooper()) {
            ev evVar = new ev(context);
            ew a = evVar.a();
            if (a == null) {
                return true;
            } else if (a.a()) {
                a.a(false);
                evVar.a(a);
                return true;
            } else {
                return false;
            }
        }
    }

    protected String a(String str) {
        return ds.c(str + dx.a(new Date().getTime()));
    }

    protected String a(List<dv> list) {
        return null;
    }
}
