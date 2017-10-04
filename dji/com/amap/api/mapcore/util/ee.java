package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Looper;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class ee extends eb implements UncaughtExceptionHandler {
    private static ExecutorService e;
    private Context d;

    private static class a implements fu {
        private Context a;

        a(Context context) {
            this.a = context;
        }

        public void a() {
            try {
                ec.b(this.a);
            } catch (Throwable th) {
                eb.a(th, "LogNetListener", "onNetCompleted");
            }
        }
    }

    public static synchronized ee a(Context context, dv dvVar) throws dk {
        ee eeVar;
        synchronized (ee.class) {
            if (dvVar == null) {
                throw new dk("sdk info is null");
            } else if (dvVar.a() == null || "".equals(dvVar.a())) {
                throw new dk("sdk name is invalid");
            } else {
                try {
                    if (eb.a == null) {
                        eb.a = new ee(context, dvVar);
                    } else {
                        eb.a.c = false;
                    }
                    eb.a.a(context, dvVar, eb.a.c);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                eeVar = (ee) eb.a;
            }
        }
        return eeVar;
    }

    public static synchronized ee a() {
        ee eeVar;
        synchronized (ee.class) {
            eeVar = (ee) eb.a;
        }
        return eeVar;
    }

    public static void a(Throwable th, String str, String str2) {
        if (eb.a != null) {
            eb.a.a(th, 1, str, str2);
        }
    }

    public static synchronized void b() {
        synchronized (ee.class) {
            try {
                if (e != null) {
                    e.shutdown();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            try {
                if (!(eb.a == null || Thread.getDefaultUncaughtExceptionHandler() != eb.a || eb.a.b == null)) {
                    Thread.setDefaultUncaughtExceptionHandler(eb.a.b);
                }
                eb.a = null;
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
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

    protected void a(Throwable th, int i, String str, String str2) {
        ec.a(this.d, th, i, str, str2);
    }

    protected void a(final Context context, final dv dvVar, final boolean z) {
        try {
            ExecutorService c = c();
            if (c != null && !c.isShutdown()) {
                c.submit(new Runnable(this) {
                    final /* synthetic */ ee d;

                    public void run() {
                        try {
                            synchronized (Looper.getMainLooper()) {
                                new eu(context, true).a(dvVar);
                            }
                            if (z) {
                                synchronized (Looper.getMainLooper()) {
                                    ev evVar = new ev(context);
                                    ew ewVar = new ew();
                                    ewVar.c(true);
                                    ewVar.a(true);
                                    ewVar.b(true);
                                    evVar.a(ewVar);
                                }
                                ec.a(this.d.d);
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

    private ee(Context context, dv dvVar) {
        this.d = context;
        fs.a(new a(context));
        d();
    }

    private void d() {
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

    public void b(Throwable th, String str, String str2) {
        if (th != null) {
            try {
                a(th, 1, str, str2);
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
    }

    static synchronized ExecutorService c() {
        ExecutorService executorService;
        synchronized (ee.class) {
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
}
