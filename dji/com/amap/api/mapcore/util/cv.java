package com.amap.api.mapcore.util;

import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import java.util.ArrayDeque;
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
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class cv<Params, Progress, Result> {
    private static final ThreadFactory a = new cw();
    public static final Executor b = new ThreadPoolExecutor(5, 128, 1, TimeUnit.SECONDS, e, a, new DiscardOldestPolicy());
    public static final Executor c;
    public static final Executor d = Executors.newFixedThreadPool(2, a);
    private static final BlockingQueue<Runnable> e = new LinkedBlockingQueue(10);
    private static final b f = new b();
    private static volatile Executor g = c;
    private final e<Params, Result> h = new e<Params, Result>(this) {
        final /* synthetic */ cv a;

        {
            this.a = r2;
        }

        public Result call() throws Exception {
            this.a.l.set(true);
            Process.setThreadPriority(10);
            return this.a.d(this.a.a(this.b));
        }
    };
    private final FutureTask<Result> i = new FutureTask<Result>(this, this.h) {
        final /* synthetic */ cv a;

        protected void done() {
            try {
                this.a.c(this.a.i.get());
            } catch (Throwable e) {
                Log.w("AsyncTask", e);
            } catch (ExecutionException e2) {
                throw new RuntimeException("An error occured while executing doInBackground()", e2.getCause());
            } catch (CancellationException e3) {
                this.a.c(null);
            }
        }
    };
    private volatile d j = d.PENDING;
    private final AtomicBoolean k = new AtomicBoolean();
    private final AtomicBoolean l = new AtomicBoolean();

    private static abstract class e<Params, Result> implements Callable<Result> {
        Params[] b;

        private e() {
        }
    }

    private static class a<Data> {
        final cv a;
        final Data[] b;

        a(cv cvVar, Data... dataArr) {
            this.a = cvVar;
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
                    aVar.a.b(aVar.b);
                    return;
                default:
                    return;
            }
        }
    }

    private static class c implements Executor {
        final ArrayDeque<Runnable> a;
        Runnable b;

        private c() {
            this.a = new ArrayDeque();
        }

        public synchronized void execute(final Runnable runnable) {
            this.a.offer(new Runnable(this) {
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
            Runnable runnable = (Runnable) this.a.poll();
            this.b = runnable;
            if (runnable != null) {
                cv.b.execute(this.b);
            }
        }
    }

    public enum d {
        PENDING,
        RUNNING,
        FINISHED
    }

    protected abstract Result a(Params... paramsArr);

    static {
        Executor cVar;
        if (dj.c()) {
            cVar = new c();
        } else {
            cVar = Executors.newSingleThreadExecutor(a);
        }
        c = cVar;
    }

    private void c(Result result) {
        if (!this.l.get()) {
            d(result);
        }
    }

    private Result d(Result result) {
        f.obtainMessage(1, new a(this, result)).sendToTarget();
        return result;
    }

    public final d a() {
        return this.j;
    }

    protected void b() {
    }

    protected void a(Result result) {
    }

    protected void b(Progress... progressArr) {
    }

    protected void b(Result result) {
        c();
    }

    protected void c() {
    }

    public final boolean d() {
        return this.k.get();
    }

    public final boolean a(boolean z) {
        this.k.set(true);
        return this.i.cancel(z);
    }

    public final cv<Params, Progress, Result> c(Params... paramsArr) {
        return a(g, (Object[]) paramsArr);
    }

    public final cv<Params, Progress, Result> a(Executor executor, Params... paramsArr) {
        if (this.j != d.PENDING) {
            switch (cx.a[this.j.ordinal()]) {
                case 1:
                    throw new IllegalStateException("Cannot execute task: the task is already running.");
                case 2:
                    throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        this.j = d.RUNNING;
        b();
        this.h.b = paramsArr;
        executor.execute(this.i);
        return this;
    }

    private void e(Result result) {
        if (d()) {
            b((Object) result);
        } else {
            a((Object) result);
        }
        this.j = d.FINISHED;
    }
}
