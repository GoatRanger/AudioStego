package dji.sdksharedlib.a;

import dji.common.error.DJIError;
import dji.sdksharedlib.c.c;
import dji.sdksharedlib.d.a;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

class a$a implements c {
    public CountDownLatch a;
    public a b;
    public boolean c;
    public DJIError d;

    public a$a(CountDownLatch countDownLatch) {
        this.a = countDownLatch;
        if (countDownLatch == null) {
            this.a = new CountDownLatch(1);
        }
    }

    public void a(a aVar) {
        this.b = aVar;
        this.c = true;
        this.a.countDown();
    }

    public void a(DJIError dJIError) {
        this.d = dJIError;
        this.c = false;
        this.a.countDown();
    }

    public void a() {
        try {
            this.a.await();
        } catch (InterruptedException e) {
        }
    }

    public void a(int i, TimeUnit timeUnit) {
        try {
            this.a.await((long) i, timeUnit);
        } catch (InterruptedException e) {
        }
    }
}
