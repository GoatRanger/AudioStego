package dji.thirdparty.f.e.d;

import dji.thirdparty.f.l;
import java.util.concurrent.CountDownLatch;

@dji.thirdparty.f.b.b
public final class b {
    private b() {
    }

    @dji.thirdparty.f.b.b
    public static void a(CountDownLatch countDownLatch, l lVar) {
        if (countDownLatch.getCount() != 0) {
            try {
                countDownLatch.await();
            } catch (Throwable e) {
                lVar.n_();
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interrupted while waiting for subscription to complete.", e);
            }
        }
    }
}
