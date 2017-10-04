package com.e;

import android.content.Context;
import android.os.Looper;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class dj extends dg implements UncaughtExceptionHandler {
    private static ExecutorService e;
    private Context d;

    private static class a implements an {
        private Context a;

        a(Context context) {
            this.a = context;
        }

        public void a() {
            try {
                dh.b(this.a);
            } catch (Throwable th) {
                dg.a(th, "LogNetListener", "onNetCompleted");
            }
        }
    }

    private dj(Context context, dc dcVar) {
        this.d = context;
        am.a(new a(context));
        c();
    }

    public static synchronized dj a(Context context, dc dcVar) throws ct {
        dj djVar;
        synchronized (dj.class) {
            if (dcVar == null) {
                throw new ct("sdk info is null");
            } else if (dcVar.a() == null || "".equals(dcVar.a())) {
                throw new ct("sdk name is invalid");
            } else {
                try {
                    if (dg.a == null) {
                        dg.a = new dj(context, dcVar);
                    } else {
                        dg.a.c = false;
                    }
                    dg.a.a(context, dcVar, dg.a.c);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                djVar = (dj) dg.a;
            }
        }
        return djVar;
    }

    public static synchronized void a() {
        synchronized (dj.class) {
            try {
                if (e != null) {
                    e.shutdown();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            try {
                if (!(dg.a == null || Thread.getDefaultUncaughtExceptionHandler() != dg.a || dg.a.b == null)) {
                    Thread.setDefaultUncaughtExceptionHandler(dg.a.b);
                }
                dg.a = null;
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
    }

    static synchronized ExecutorService b() {
        ExecutorService executorService;
        synchronized (dj.class) {
            try {
                if (e == null || e.isShutdown()) {
                    e = Executors.newSingleThreadExecutor();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            executorService = e;
        }
        return executorService;
    }

    public static void b(dc dcVar, String str) {
        if (dg.a != null) {
            dg.a.a(dcVar, str);
        }
    }

    public static void b(Throwable th, String str, String str2) {
        if (dg.a != null) {
            dg.a.a(th, 1, str, str2);
        }
    }

    private void c() {
        try {
            this.b = Thread.getDefaultUncaughtExceptionHandler();
            if (this.b == null) {
                Thread.setDefaultUncaughtExceptionHandler(this);
                this.c = true;
            } else if (this.b.toString().indexOf("com.amap.api") != -1) {
                this.c = false;
            } else {
                Thread.setDefaultUncaughtExceptionHandler(this);
                this.c = true;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    protected void a(final Context context, final dc dcVar, final boolean z) {
        try {
            ExecutorService b = b();
            if (b != null && !b.isShutdown()) {
                b.submit(new Runnable(this) {
                    final /* synthetic */ dj d;

                    public void run() {
                        try {
                            synchronized (Looper.getMainLooper()) {
                                new p(context, true).a(dcVar);
                            }
                            if (z) {
                                synchronized (Looper.getMainLooper()) {
                                    q qVar = new q(context);
                                    r rVar = new r();
                                    rVar.c(true);
                                    rVar.a(true);
                                    rVar.b(true);
                                    qVar.a(rVar);
                                }
                                dh.a(this.d.d);
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
            }
        } catch (RejectedExecutionException e) {
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    protected void a(dc dcVar, String str) {
        dh.a(this.d, dcVar, str);
    }

    protected void a(Throwable th, int i, String str, String str2) {
        dh.a(this.d, th, i, str, str2);
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (th != null) {
            a(th, 0, null, null);
            if (this.b != null) {
                try {
                    Thread.setDefaultUncaughtExceptionHandler(this.b);
                } catch (Throwable th2) {
                }
                this.b.uncaughtException(thread, th);
            }
        }
    }
}
