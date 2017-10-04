package com.e;

import android.content.Context;
import android.os.Looper;
import java.util.List;

public class c extends d {
    private static boolean a = true;

    protected c(int i) {
        super(i);
    }

    protected String a(List<dc> list) {
        return null;
    }

    protected boolean a(Context context) {
        if (cx.m(context) != 1 || !a) {
            return false;
        }
        a = false;
        synchronized (Looper.getMainLooper()) {
            q qVar = new q(context);
            r a = qVar.a();
            if (a == null) {
                return true;
            } else if (a.b()) {
                a.b(false);
                qVar.a(a);
                return true;
            } else {
                return false;
            }
        }
    }
}
