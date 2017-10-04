package dji.thirdparty.f.e.a;

import dji.thirdparty.f.b;
import dji.thirdparty.f.b$a;
import dji.thirdparty.f.b$c;
import dji.thirdparty.f.l;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public final class n implements b$a {
    final b[] a;

    public n(b[] bVarArr) {
        this.a = bVarArr;
    }

    public void a(b$c dji_thirdparty_f_b_c) {
        final l bVar = new dji.thirdparty.f.m.b();
        final AtomicInteger atomicInteger = new AtomicInteger(this.a.length + 1);
        final Queue concurrentLinkedQueue = new ConcurrentLinkedQueue();
        dji_thirdparty_f_b_c.a(bVar);
        b[] bVarArr = this.a;
        int length = bVarArr.length;
        int i = 0;
        while (i < length) {
            b bVar2 = bVarArr[i];
            if (!bVar.b()) {
                if (bVar2 == null) {
                    concurrentLinkedQueue.offer(new NullPointerException("A completable source is null"));
                    atomicInteger.decrementAndGet();
                } else {
                    final b$c dji_thirdparty_f_b_c2 = dji_thirdparty_f_b_c;
                    bVar2.a(new b$c(this) {
                        final /* synthetic */ n e;

                        public void a(l lVar) {
                            bVar.a(lVar);
                        }

                        public void a(Throwable th) {
                            concurrentLinkedQueue.offer(th);
                            a();
                        }

                        public void b() {
                            a();
                        }

                        void a() {
                            if (atomicInteger.decrementAndGet() != 0) {
                                return;
                            }
                            if (concurrentLinkedQueue.isEmpty()) {
                                dji_thirdparty_f_b_c2.b();
                            } else {
                                dji_thirdparty_f_b_c2.a(l.a(concurrentLinkedQueue));
                            }
                        }
                    });
                }
                i++;
            } else {
                return;
            }
        }
        if (atomicInteger.decrementAndGet() != 0) {
            return;
        }
        if (concurrentLinkedQueue.isEmpty()) {
            dji_thirdparty_f_b_c.b();
        } else {
            dji_thirdparty_f_b_c.a(l.a(concurrentLinkedQueue));
        }
    }
}
