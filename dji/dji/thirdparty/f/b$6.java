package dji.thirdparty.f;

import java.util.concurrent.CountDownLatch;

class b$6 implements b$c {
    final /* synthetic */ CountDownLatch a;
    final /* synthetic */ Throwable[] b;
    final /* synthetic */ b c;

    b$6(b bVar, CountDownLatch countDownLatch, Throwable[] thArr) {
        this.c = bVar;
        this.a = countDownLatch;
        this.b = thArr;
    }

    public void b() {
        this.a.countDown();
    }

    public void a(Throwable th) {
        this.b[0] = th;
        this.a.countDown();
    }

    public void a(l lVar) {
    }
}
