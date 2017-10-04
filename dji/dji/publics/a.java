package dji.publics;

import android.os.Handler;
import android.os.Looper;

public class a {
    public static Handler a = new Handler(Looper.getMainLooper());

    public static void a(Runnable runnable) {
        a.post(runnable);
    }

    public static Handler a() {
        return a;
    }
}
