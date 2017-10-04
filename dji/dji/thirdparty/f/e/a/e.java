package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

public final class e {
    private e() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Future<T> a(d<? extends T> dVar) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final AtomicReference atomicReference = new AtomicReference();
        final AtomicReference atomicReference2 = new AtomicReference();
        final l b = dVar.B().b(new k<T>() {
            public void o_() {
                countDownLatch.countDown();
            }

            public void a(Throwable th) {
                atomicReference2.compareAndSet(null, th);
                countDownLatch.countDown();
            }

            public void a_(T t) {
                atomicReference.set(t);
            }
        });
        return new Future<T>() {
            private volatile boolean e = false;

            public boolean cancel(boolean z) {
                if (countDownLatch.getCount() <= 0) {
                    return false;
                }
                this.e = true;
                b.n_();
                countDownLatch.countDown();
                return true;
            }

            public boolean isCancelled() {
                return this.e;
            }

            public boolean isDone() {
                return countDownLatch.getCount() == 0;
            }

            public T get() throws InterruptedException, ExecutionException {
                countDownLatch.await();
                return a();
            }

            public T get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
                if (countDownLatch.await(j, timeUnit)) {
                    return a();
                }
                throw new TimeoutException("Timed out after " + timeUnit.toMillis(j) + "ms waiting for underlying Observable.");
            }

            private T a() throws ExecutionException {
                Throwable th = (Throwable) atomicReference2.get();
                if (th != null) {
                    throw new ExecutionException("Observable onError", th);
                } else if (!this.e) {
                    return atomicReference.get();
                } else {
                    throw new CancellationException("Subscription unsubscribed");
                }
            }
        };
    }
}
