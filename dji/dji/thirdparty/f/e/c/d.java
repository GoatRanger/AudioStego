package dji.thirdparty.f.e.c;

import dji.thirdparty.f.c.f;
import dji.thirdparty.f.e.d.n;
import dji.thirdparty.f.l;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class d extends AtomicReference<Thread> implements l, Runnable {
    private static final long c = -3962399486978279857L;
    final n a;
    final dji.thirdparty.f.d.b b;

    private final class a implements l {
        final /* synthetic */ d a;
        private final Future<?> b;

        a(d dVar, Future<?> future) {
            this.a = dVar;
            this.b = future;
        }

        public void n_() {
            if (this.a.get() != Thread.currentThread()) {
                this.b.cancel(true);
            } else {
                this.b.cancel(false);
            }
        }

        public boolean b() {
            return this.b.isCancelled();
        }
    }

    private static final class b extends AtomicBoolean implements l {
        private static final long c = 247232374289553518L;
        final d a;
        final dji.thirdparty.f.m.b b;

        public b(d dVar, dji.thirdparty.f.m.b bVar) {
            this.a = dVar;
            this.b = bVar;
        }

        public boolean b() {
            return this.a.b();
        }

        public void n_() {
            if (compareAndSet(false, true)) {
                this.b.b(this.a);
            }
        }
    }

    private static final class c extends AtomicBoolean implements l {
        private static final long c = 247232374289553518L;
        final d a;
        final n b;

        public c(d dVar, n nVar) {
            this.a = dVar;
            this.b = nVar;
        }

        public boolean b() {
            return this.a.b();
        }

        public void n_() {
            if (compareAndSet(false, true)) {
                this.b.b(this.a);
            }
        }
    }

    public d(dji.thirdparty.f.d.b bVar) {
        this.b = bVar;
        this.a = new n();
    }

    public d(dji.thirdparty.f.d.b bVar, dji.thirdparty.f.m.b bVar2) {
        this.b = bVar;
        this.a = new n(new b(this, bVar2));
    }

    public d(dji.thirdparty.f.d.b bVar, n nVar) {
        this.b = bVar;
        this.a = new n(new c(this, nVar));
    }

    public void run() {
        try {
            lazySet(Thread.currentThread());
            this.b.a();
        } catch (Throwable th) {
            Throwable th2;
            if (th2 instanceof f) {
                th2 = new IllegalStateException("Exception thrown on Scheduler.Worker thread. Add `onError` handling.", th2);
            } else {
                th2 = new IllegalStateException("Fatal Exception thrown on Scheduler.Worker thread.", th2);
            }
            dji.thirdparty.f.i.d.getInstance().b().a(th2);
            Thread currentThread = Thread.currentThread();
            currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, th2);
        } finally {
            n_();
        }
    }

    public boolean b() {
        return this.a.b();
    }

    public void n_() {
        if (!this.a.b()) {
            this.a.n_();
        }
    }

    public void a(l lVar) {
        this.a.a(lVar);
    }

    public void a(Future<?> future) {
        this.a.a(new a(this, future));
    }

    public void a(dji.thirdparty.f.m.b bVar) {
        this.a.a(new b(this, bVar));
    }

    public void a(n nVar) {
        this.a.a(new c(this, nVar));
    }
}
