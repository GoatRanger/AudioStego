package dji.sdksharedlib.e;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import dji.log.DJILog;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class b {
    private static final String a = "DJISDKCacheThreadManager";
    private static boolean f = false;
    private Handler b;
    private Handler c;
    private HandlerThread d;
    private ExecutorService e;

    private static class a {
        private static b a = new b();

        private a() {
        }
    }

    public static b getInstance() {
        return a.a;
    }

    public static void a(boolean z) {
        f = z;
    }

    public static Looper a() {
        return getInstance().b();
    }

    private b() {
        this.b = new Handler(Looper.getMainLooper());
        this.e = Executors.newCachedThreadPool();
        this.d = new HandlerThread("SingleBackgroundLooper");
        this.d.start();
        this.c = new Handler(this.d.getLooper());
    }

    public Looper b() {
        return this.d.getLooper();
    }

    public static void a(Runnable runnable, boolean z) {
        getInstance().d(runnable, z);
    }

    public static void b(Runnable runnable, boolean z) {
        getInstance().c(runnable, z);
    }

    public static void a(Runnable runnable, long j, boolean z) {
        getInstance().b(runnable, j, z);
    }

    private Runnable a(final Runnable runnable) {
        return new Runnable(this) {
            final /* synthetic */ b b;

            public void run() {
                long currentTimeMillis = System.currentTimeMillis();
                runnable.run();
                DJILog.d(b.a, "task execution time: " + (((float) (System.currentTimeMillis() - currentTimeMillis)) / 1000.0f) + " seconds.", true, false);
            }
        };
    }

    private void c(Runnable runnable, boolean z) {
        if (f) {
            new Thread(runnable).start();
        } else if (z) {
            this.b.post(runnable);
        } else {
            this.c.post(runnable);
        }
    }

    private void d(Runnable runnable, boolean z) {
        if (f) {
            new Thread(runnable).start();
        } else if (z) {
            this.b.post(runnable);
        } else {
            try {
                this.e.execute(a(runnable));
            } catch (RejectedExecutionException e) {
                DJILog.d(a, "task has been rejected!", true, true);
            }
        }
    }

    private void b(final Runnable runnable, long j, boolean z) {
        if (z) {
            this.b.postDelayed(runnable, j);
            return;
        }
        this.b.postDelayed(new Runnable(this) {
            final /* synthetic */ b b;

            public void run() {
                try {
                    this.b.e.execute(this.b.a(runnable));
                } catch (RejectedExecutionException e) {
                    DJILog.d(b.a, "task has been rejected!", true, true);
                }
            }
        }, j);
    }
}
