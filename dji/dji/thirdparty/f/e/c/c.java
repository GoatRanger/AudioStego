package dji.thirdparty.f.e.c;

import dji.thirdparty.f.c.b;
import dji.thirdparty.f.e.d.h;
import dji.thirdparty.f.e.d.k;
import dji.thirdparty.f.e.d.n;
import dji.thirdparty.f.g.a;
import dji.thirdparty.f.i.d;
import dji.thirdparty.f.i.e;
import dji.thirdparty.f.l;
import dji.thirdparty.f.m.f;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class c extends a implements l {
    public static final int b = Integer.getInteger(e, 1000).intValue();
    private static final String e = "rx.scheduler.jdk6.purge-frequency-millis";
    private static final String f = "rx.scheduler.jdk6.purge-force";
    private static final String g = "RxSchedulerPurge-";
    private static final boolean h;
    private static final ConcurrentHashMap<ScheduledThreadPoolExecutor, ScheduledThreadPoolExecutor> i = new ConcurrentHashMap();
    private static final AtomicReference<ScheduledExecutorService> j = new AtomicReference();
    private static volatile Object k;
    private static final Object l = new Object();
    volatile boolean a;
    private final ScheduledExecutorService c;
    private final e d;

    static {
        boolean z = Boolean.getBoolean(f);
        int b = h.b();
        if (z || (b != 0 && b < 21)) {
            z = false;
        } else {
            z = true;
        }
        h = z;
    }

    public static void a(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
        while (((ScheduledExecutorService) j.get()) == null) {
            ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, new k(g));
            if (j.compareAndSet(null, newScheduledThreadPool)) {
                newScheduledThreadPool.scheduleAtFixedRate(new Runnable() {
                    public void run() {
                        c.c();
                    }
                }, (long) b, (long) b, TimeUnit.MILLISECONDS);
                break;
            }
            newScheduledThreadPool.shutdownNow();
        }
        i.putIfAbsent(scheduledThreadPoolExecutor, scheduledThreadPoolExecutor);
    }

    public static void a(ScheduledExecutorService scheduledExecutorService) {
        i.remove(scheduledExecutorService);
    }

    static void c() {
        try {
            Iterator it = i.keySet().iterator();
            while (it.hasNext()) {
                ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor) it.next();
                if (scheduledThreadPoolExecutor.isShutdown()) {
                    it.remove();
                } else {
                    scheduledThreadPoolExecutor.purge();
                }
            }
        } catch (Throwable th) {
            b.b(th);
            d.getInstance().b().a(th);
        }
    }

    public static boolean b(ScheduledExecutorService scheduledExecutorService) {
        if (h) {
            Method c;
            if (scheduledExecutorService instanceof ScheduledThreadPoolExecutor) {
                Object obj = k;
                if (obj == l) {
                    return false;
                }
                if (obj == null) {
                    c = c(scheduledExecutorService);
                    if (c != null) {
                        obj = c;
                    } else {
                        obj = l;
                    }
                    k = obj;
                } else {
                    c = (Method) obj;
                }
            } else {
                c = c(scheduledExecutorService);
            }
            if (c != null) {
                try {
                    c.invoke(scheduledExecutorService, new Object[]{Boolean.valueOf(true)});
                    return true;
                } catch (Throwable e) {
                    d.getInstance().b().a(e);
                }
            }
        }
        return false;
    }

    static Method c(ScheduledExecutorService scheduledExecutorService) {
        for (Method method : scheduledExecutorService.getClass().getMethods()) {
            if (method.getName().equals("setRemoveOnCancelPolicy")) {
                Class[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1 && parameterTypes[0] == Boolean.TYPE) {
                    return method;
                }
            }
        }
        return null;
    }

    public c(ThreadFactory threadFactory) {
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, threadFactory);
        if (!b(newScheduledThreadPool) && (newScheduledThreadPool instanceof ScheduledThreadPoolExecutor)) {
            a((ScheduledThreadPoolExecutor) newScheduledThreadPool);
        }
        this.d = d.getInstance().e();
        this.c = newScheduledThreadPool;
    }

    public l a(dji.thirdparty.f.d.b bVar) {
        return a(bVar, 0, null);
    }

    public l a(dji.thirdparty.f.d.b bVar, long j, TimeUnit timeUnit) {
        if (this.a) {
            return f.b();
        }
        return b(bVar, j, timeUnit);
    }

    public d b(dji.thirdparty.f.d.b bVar, long j, TimeUnit timeUnit) {
        Future submit;
        Runnable dVar = new d(this.d.a(bVar));
        if (j <= 0) {
            submit = this.c.submit(dVar);
        } else {
            submit = this.c.schedule(dVar, j, timeUnit);
        }
        dVar.a(submit);
        return dVar;
    }

    public d a(dji.thirdparty.f.d.b bVar, long j, TimeUnit timeUnit, dji.thirdparty.f.m.b bVar2) {
        Future submit;
        l dVar = new d(this.d.a(bVar), bVar2);
        bVar2.a(dVar);
        if (j <= 0) {
            submit = this.c.submit(dVar);
        } else {
            submit = this.c.schedule(dVar, j, timeUnit);
        }
        dVar.a(submit);
        return dVar;
    }

    public d a(dji.thirdparty.f.d.b bVar, long j, TimeUnit timeUnit, n nVar) {
        Future submit;
        l dVar = new d(this.d.a(bVar), nVar);
        nVar.a(dVar);
        if (j <= 0) {
            submit = this.c.submit(dVar);
        } else {
            submit = this.c.schedule(dVar, j, timeUnit);
        }
        dVar.a(submit);
        return dVar;
    }

    public void n_() {
        this.a = true;
        this.c.shutdownNow();
        a(this.c);
    }

    public boolean b() {
        return this.a;
    }
}
