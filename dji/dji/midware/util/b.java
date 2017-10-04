package dji.midware.util;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

public class b {
    private static b c;
    public HandlerThread a = new HandlerThread("dji_background_thread");
    public Handler b;

    public static b a() {
        if (c == null) {
            c = new b();
        }
        return c;
    }

    private b() {
        this.a.start();
        this.b = new Handler(this.a.getLooper());
    }

    public static Looper b() {
        return a().a.getLooper();
    }

    public static void a(Runnable runnable) {
        a().b.post(runnable);
    }

    public static void a(Runnable runnable, long j) {
        a().b.postDelayed(runnable, j);
    }

    public static void b(Runnable runnable) {
        a().b.removeCallbacks(runnable);
    }
}
