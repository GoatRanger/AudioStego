package dji.thirdparty.afinal;

import java.util.concurrent.ThreadFactory;

class a$1 implements ThreadFactory {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setPriority(4);
        return thread;
    }
}
