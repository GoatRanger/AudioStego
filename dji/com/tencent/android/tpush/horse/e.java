package com.tencent.android.tpush.horse;

import com.tencent.android.tpush.service.a.a;

public class e {
    public static boolean a(long j) {
        if (j != 0 && ((a() * 1000) * 60) + j > System.currentTimeMillis()) {
            return false;
        }
        return true;
    }

    public static long a() {
        return (long) a.q;
    }

    public static int b() {
        return a.o;
    }

    public static int c() {
        return a.p;
    }
}
