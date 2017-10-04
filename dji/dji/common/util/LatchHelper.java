package dji.common.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class LatchHelper {
    private static LatchHelper uniqueInstance = null;
    private CountDownLatch latch = null;

    public static LatchHelper getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new LatchHelper();
        }
        return uniqueInstance;
    }

    public synchronized void setUpLatch(int i) {
        this.latch = new CountDownLatch(i);
    }

    public void countDownLatch() {
        if (this.latch != null) {
            this.latch.countDown();
        }
    }

    public void waitForLatch(long j) {
        try {
            this.latch.await(j, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
