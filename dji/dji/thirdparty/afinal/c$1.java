package dji.thirdparty.afinal;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

class c$1 implements ThreadFactory {
    private final AtomicInteger a = new AtomicInteger(1);

    c$1() {
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, "FinalHttp #" + this.a.getAndIncrement());
        thread.setPriority(4);
        return thread;
    }
}
