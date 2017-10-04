package com.facebook.internal;

import com.facebook.o;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;

public class w<T> {
    private T a;
    private CountDownLatch b;

    public w(T t) {
        this.a = t;
    }

    public w(final Callable<T> callable) {
        this.b = new CountDownLatch(1);
        o.f().execute(new FutureTask(new Callable<Void>(this) {
            final /* synthetic */ w b;

            public /* synthetic */ Object call() throws Exception {
                return a();
            }

            public Void a() throws Exception {
                try {
                    this.b.a = callable.call();
                    return null;
                } finally {
                    this.b.b.countDown();
                }
            }
        }));
    }

    public T a() {
        b();
        return this.a;
    }

    private void b() {
        if (this.b != null) {
            try {
                this.b.await();
            } catch (InterruptedException e) {
            }
        }
    }
}
