package dji.thirdparty.f.e.d;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public final class k extends AtomicLong implements ThreadFactory {
    final String a;

    public k(String str) {
        this.a = str;
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, this.a + incrementAndGet());
        thread.setDaemon(true);
        return thread;
    }
}
