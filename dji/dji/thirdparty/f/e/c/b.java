package dji.thirdparty.f.e.c;

import dji.thirdparty.f.e.d.k;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicReference;

public final class b implements e {
    public static final b a = new b();
    private static final String b = "RxScheduledExecutorPool-";
    private static final k c = new k(b);
    private static final ScheduledExecutorService[] d = new ScheduledExecutorService[0];
    private static final ScheduledExecutorService e = Executors.newScheduledThreadPool(0);
    private static int g;
    private final AtomicReference<ScheduledExecutorService[]> f = new AtomicReference(d);

    static {
        e.shutdown();
    }

    private b() {
        c();
    }

    public void c() {
        int i = 8;
        int i2 = 0;
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        if (availableProcessors > 4) {
            availableProcessors /= 2;
        }
        if (availableProcessors <= 8) {
            i = availableProcessors;
        }
        Object obj = new ScheduledExecutorService[i];
        for (availableProcessors = 0; availableProcessors < i; availableProcessors++) {
            obj[availableProcessors] = Executors.newScheduledThreadPool(1, c);
        }
        if (this.f.compareAndSet(d, obj)) {
            availableProcessors = obj.length;
            while (i2 < availableProcessors) {
                ScheduledExecutorService scheduledExecutorService = obj[i2];
                if (!c.b(scheduledExecutorService) && (scheduledExecutorService instanceof ScheduledThreadPoolExecutor)) {
                    c.a((ScheduledThreadPoolExecutor) scheduledExecutorService);
                }
                i2++;
            }
            return;
        }
        for (ScheduledExecutorService shutdownNow : obj) {
            shutdownNow.shutdownNow();
        }
    }

    public void d() {
        ScheduledExecutorService[] scheduledExecutorServiceArr;
        do {
            scheduledExecutorServiceArr = (ScheduledExecutorService[]) this.f.get();
            if (scheduledExecutorServiceArr == d) {
                return;
            }
        } while (!this.f.compareAndSet(scheduledExecutorServiceArr, d));
        for (ScheduledExecutorService scheduledExecutorService : scheduledExecutorServiceArr) {
            c.a(scheduledExecutorService);
            scheduledExecutorService.shutdownNow();
        }
    }

    public static ScheduledExecutorService getInstance() {
        ScheduledExecutorService[] scheduledExecutorServiceArr = (ScheduledExecutorService[]) a.f.get();
        if (scheduledExecutorServiceArr == d) {
            return e;
        }
        int i = g + 1;
        if (i >= scheduledExecutorServiceArr.length) {
            i = 0;
        }
        g = i;
        return scheduledExecutorServiceArr[i];
    }
}
