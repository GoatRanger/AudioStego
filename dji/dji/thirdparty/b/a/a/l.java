package dji.thirdparty.b.a.a;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public final class l {
    private final CountDownLatch a = new CountDownLatch(1);
    private long b = -1;
    private long c = -1;

    l() {
    }

    void a() {
        if (this.b != -1) {
            throw new IllegalStateException();
        }
        this.b = System.nanoTime();
    }

    void b() {
        if (this.c != -1 || this.b == -1) {
            throw new IllegalStateException();
        }
        this.c = System.nanoTime();
        this.a.countDown();
    }

    void c() {
        if (this.c != -1 || this.b == -1) {
            throw new IllegalStateException();
        }
        this.c = this.b - 1;
        this.a.countDown();
    }

    public long d() throws InterruptedException {
        this.a.await();
        return this.c - this.b;
    }

    public long a(long j, TimeUnit timeUnit) throws InterruptedException {
        if (this.a.await(j, timeUnit)) {
            return this.c - this.b;
        }
        return -2;
    }
}
