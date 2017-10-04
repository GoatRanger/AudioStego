package com.e;

import android.content.Context;
import android.os.Looper;
import java.util.Date;
import java.util.List;

public class b extends d {
    private static boolean a = true;

    protected b(int i) {
        super(i);
    }

    protected String a(String str) {
        return cz.c(str + dd.a(new Date().getTime()));
    }

    protected String a(List<dc> list) {
        return null;
    }

    protected boolean a(Context context) {
        if (!a) {
            return false;
        }
        a = false;
        synchronized (Looper.getMainLooper()) {
            q qVar = new q(context);
            r a = qVar.a();
            if (a == null) {
                return true;
            } else if (a.a()) {
                a.a(false);
                qVar.a(a);
                return true;
            } else {
                return false;
            }
        }
    }
}
