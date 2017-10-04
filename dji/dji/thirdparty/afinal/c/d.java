package dji.thirdparty.afinal.c;

import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class d<Params, Progress, Result> {
    private static final String a = "AsyncTask";
    private static final int b = 5;
    private static final int c = 128;
    private static final int d = 1;
    private static final ThreadFactory e = new ThreadFactory() {
        private final AtomicInteger a = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "AsyncTask #" + this.a.getAndIncrement());
            thread.setPriority(4);
            return thread;
        }
    };
    private static final BlockingQueue<Runnable> f = new LinkedBlockingQueue(10);
    public static final Executor g = new ThreadPoolExecutor(5, 128, 1, TimeUnit.SECONDS, f, e, new DiscardOldestPolicy());
    public static final Executor h = new c();
    public static final Executor i = Executors.newFixedThreadPool(3, e);
    private static final int j = 1;
    private static final int k = 2;
    private static final b l = new b();
    private static volatile Executor m = h;
    private final e<Params, Result> n = new e<Params, Result>(this) {
        final /* synthetic */ d a;

        {
            this.a = r2;
        }

        public Result call() throws Exception {
            this.a.r.set(true);
            Process.setThreadPriority(10);
            return this.a.d(this.a.b(this.b));
        }
    };
    private final FutureTask<Result> o = new FutureTask<Result>(this, this.n) {
        final /* synthetic */ d a;

        protected void done() {
            try {
                this.a.c(get());
            } catch (Throwable e) {
                Log.w(d.a, e);
            } catch (ExecutionException e2) {
                throw new RuntimeException("An error occured while executing doInBackground()", e2.getCause());
            } catch (CancellationException e3) {
                this.a.c(null);
            }
        }
    };
    private volatile d p = d.PENDING;
    private final AtomicBoolean q = new AtomicBoolean();
    private final AtomicBoolean r = new AtomicBoolean();

    private static abstract class e<Params, Result> implements Callable<Result> {
        Params[] b;

        private e() {
        }
    }

    private static class a<Data> {
        final d a;
        final Data[] b;

        a(d dVar, Data... dataArr) {
            this.a = dVar;
            this.b = dataArr;
        }
    }

    private static class b extends Handler {
        private b() {
        }

        public void handleMessage(Message message) {
            a aVar = (a) message.obj;
            switch (message.what) {
                case 1:
                    aVar.a.e(aVar.b[0]);
                    return;
                case 2:
                    aVar.a.c(aVar.b);
                    return;
                default:
                    return;
            }
        }
    }

    private static class c implements Executor {
        final b<Runnable> a;
        Runnable b;

        private c() {
            this.a = new b();
        }

        public synchronized void execute(final Runnable runnable) {
            this.a.g(new Runnable(this) {
                final /* synthetic */ c b;

                public void run() {
                    try {
                        runnable.run();
                    } finally {
                        this.b.a();
                    }
                }
            });
            if (this.b == null) {
                a();
            }
        }

        protected synchronized void a() {
            Runnable runnable = (Runnable) this.a.j();
            this.b = runnable;
            if (runnable != null) {
                d.g.execute(this.b);
            }
        }
    }

    public enum d {
        PENDING,
        RUNNING,
        FINISHED
    }

    protected abstract Result b(Params... paramsArr);

    public static void a() {
        l.getLooper();
    }

    public static void a(Executor executor) {
        m = executor;
    }

    private void c(Result result) {
        if (!this.r.get()) {
            d((Object) result);
        }
    }

    private Result d(Result result) {
        l.obtainMessage(1, new a(this, result)).sendToTarget();
        return result;
    }

    public final d b() {
        return this.p;
    }

    protected void c() {
    }

    protected void b(Result result) {
    }

    protected void c(Progress... progressArr) {
    }

    protected void a(Result result) {
        d();
    }

    protected void d() {
    }

    public final boolean e() {
        return this.q.get();
    }

    public final boolean a(boolean z) {
        this.q.set(true);
        return this.o.cancel(z);
    }

    public final Result f() throws InterruptedException, ExecutionException {
        return this.o.get();
    }

    public final Result a(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.o.get(j, timeUnit);
    }

    public final d<Params, Progress, Result> d(Params... paramsArr) {
        return a(m, (Object[]) paramsArr);
    }

    public final d<Params, Progress, Result> a(Executor executor, Params... paramsArr) {
        if (this.p != d.PENDING) {
            switch (this.p) {
                case RUNNING:
                    throw new IllegalStateException("Cannot execute task: the task is already running.");
                case FINISHED:
                    throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        this.p = d.RUNNING;
        c();
        this.n.b = paramsArr;
        executor.execute(this.o);
        return this;
    }

    public static void a(Runnable runnable) {
        m.execute(runnable);
    }

    protected final void e(Progress... progressArr) {
        if (!e()) {
            l.obtainMessage(2, new a(this, progressArr)).sendToTarget();
        }
    }

    private void e(Result result) {
        if (e()) {
            a((Object) result);
        } else {
            b((Object) result);
        }
        this.p = d.FINISHED;
    }
}
