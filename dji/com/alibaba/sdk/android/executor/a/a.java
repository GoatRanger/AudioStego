package com.alibaba.sdk.android.executor.a;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.alibaba.sdk.android.executor.ExecutorService;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class a implements ExecutorService, java.util.concurrent.ExecutorService {
    private final Handler a = new Handler(Looper.getMainLooper());
    private ThreadPoolExecutor b;
    private final BlockingQueue<Runnable> c = new LinkedBlockingQueue(128);
    private final HandlerThread d = new HandlerThread("SDK Looper Thread");
    private final Handler e;

    public static class a implements RejectedExecutionHandler {
        private BlockingQueue<Runnable> a;

        public a(BlockingQueue<Runnable> blockingQueue) {
            this.a = blockingQueue;
        }

        public final void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            Object[] toArray = this.a.toArray();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append('[');
            for (Object obj : toArray) {
                if (obj.getClass().isAnonymousClass()) {
                    stringBuilder.append(a(obj));
                    stringBuilder.append(',').append(' ');
                } else {
                    stringBuilder.append(obj.getClass());
                    stringBuilder.append(',').append(' ');
                }
            }
            stringBuilder.append(']');
            throw new RejectedExecutionException("Task " + runnable.toString() + " rejected from " + threadPoolExecutor.toString() + " in " + stringBuilder.toString());
        }

        private static Object a(Object obj) {
            try {
                Field declaredField = obj.getClass().getDeclaredField("this$0");
                declaredField.setAccessible(true);
                obj = declaredField.get(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return obj;
        }
    }

    public a() {
        this.d.start();
        synchronized (this.d) {
            while (this.d.getLooper() == null) {
                try {
                    this.d.wait();
                } catch (InterruptedException e) {
                }
            }
        }
        this.e = new Handler(this.d.getLooper());
        this.b = new ThreadPoolExecutor(8, 16, 1, TimeUnit.SECONDS, this.c, new b(this), new a(this.c));
    }

    public final void postHandlerTask(Runnable runnable) {
        this.e.post(runnable);
    }

    public final void postUITask(Runnable runnable) {
        this.a.post(new c(this, runnable));
    }

    public final void postTask(Runnable runnable) {
        this.b.execute(runnable);
    }

    public final Looper getDefaultLooper() {
        return this.d.getLooper();
    }

    public final void execute(Runnable runnable) {
        this.b.execute(runnable);
    }

    public final void shutdown() {
        this.b.shutdown();
    }

    public final List<Runnable> shutdownNow() {
        return this.b.shutdownNow();
    }

    public final boolean isShutdown() {
        return this.b.isShutdown();
    }

    public final boolean isTerminated() {
        return this.b.isTerminated();
    }

    public final boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.b.awaitTermination(j, timeUnit);
    }

    public final <T> Future<T> submit(Callable<T> callable) {
        return this.b.submit(callable);
    }

    public final <T> Future<T> submit(Runnable runnable, T t) {
        return this.b.submit(runnable, t);
    }

    public final Future<?> submit(Runnable runnable) {
        return this.b.submit(runnable);
    }

    public final <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) throws InterruptedException {
        return this.b.invokeAll(collection);
    }

    public final <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException {
        return this.b.invokeAll(collection, j, timeUnit);
    }

    public final <T> T invokeAny(Collection<? extends Callable<T>> collection) throws InterruptedException, ExecutionException {
        return this.b.invokeAny(collection);
    }

    public final <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.b.invokeAny(collection, j, timeUnit);
    }
}
