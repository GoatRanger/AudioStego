package com.dji.a.d;

import com.dji.a.f.d;

public class g {
    private static String a = "";

    public static String a() {
        synchronized (a) {
            if (a.isEmpty()) {
                a = d.a();
            }
        }
        return a;
    }
}
