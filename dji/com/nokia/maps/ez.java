package com.nokia.maps;

import android.os.Handler;
import android.os.Looper;

public class ez {
    private static final Handler a = new Handler(Looper.getMainLooper());

    public static void a(Runnable runnable) {
        a.post(runnable);
    }

    public static void a(Runnable runnable, long j) {
        a.postDelayed(runnable, j);
    }

    public static void b(Runnable runnable) {
        a.removeCallbacks(runnable);
    }
}
