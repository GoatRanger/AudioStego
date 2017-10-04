package dji.thirdparty.f.e.a;

import dji.thirdparty.f.b;
import dji.thirdparty.f.b$a;
import dji.thirdparty.f.b$c;
import dji.thirdparty.f.i.d;
import dji.thirdparty.f.l;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public final class p implements b$a {
    final Iterable<? extends b> a;

    public p(Iterable<? extends b> iterable) {
        this.a = iterable;
    }

    public void a(b$c dji_thirdparty_f_b_c) {
        final l bVar = new dji.thirdparty.f.m.b();
        final AtomicInteger atomicInteger = new AtomicInteger(1);
        final AtomicBoolean atomicBoolean = new AtomicBoolean();
        dji_thirdparty_f_b_c.a(bVar);
        Throwable nullPointerException;
        try {
            Iterator it = this.a.iterator();
            if (it == null) {
                dji_thirdparty_f_b_c.a(new NullPointerException("The source iterator returned is null"));
                return;
            }
            while (!bVar.b()) {
                try {
                    if (it.hasNext()) {
                        if (!bVar.b()) {
                            try {
                                b bVar2 = (b) it.next();
                                if (!bVar.b()) {
                                    if (bVar2 == null) {
                                        bVar.n_();
                                        nullPointerException = new NullPointerException("A completable source is null");
                                        if (atomicBoolean.compareAndSet(false, true)) {
                                            dji_thirdparty_f_b_c.a(nullPointerException);
                                            return;
                                        } else {
                                            d.getInstance().b().a(nullPointerException);
                                            return;
                                        }
                                    }
                                    atomicInteger.getAndIncrement();
                                    final b$c dji_thirdparty_f_b_c2 = dji_thirdparty_f_b_c;
                                    bVar2.a(new b$c(this) {
                                        final /* synthetic */ p e;

                                        public void a(l lVar) {
                                            bVar.a(lVar);
                                        }

                                        public void a(Throwable th) {
                                            bVar.n_();
                                            if (atomicBoolean.compareAndSet(false, true)) {
                                                dji_thirdparty_f_b_c2.a(th);
                                            } else {
                                                d.getInstance().b().a(th);
                                            }
                                        }

                                        public void b() {
                                            if (atomicInteger.decrementAndGet() == 0 && atomicBoolean.compareAndSet(false, true)) {
                                                dji_thirdparty_f_b_c2.b();
                                            }
                                        }
                                    });
                                } else {
                                    return;
                                }
                            } catch (Throwable nullPointerException2) {
                                bVar.n_();
                                if (atomicBoolean.compareAndSet(false, true)) {
                                    dji_thirdparty_f_b_c.a(nullPointerException2);
                                    return;
                                } else {
                                    d.getInstance().b().a(nullPointerException2);
                                    return;
                                }
                            }
                        }
                        return;
                    } else if (atomicInteger.decrementAndGet() == 0 && atomicBoolean.compareAndSet(false, true)) {
                        dji_thirdparty_f_b_c.b();
                        return;
                    } else {
                        return;
                    }
                } catch (Throwable nullPointerException22) {
                    bVar.n_();
                    if (atomicBoolean.compareAndSet(false, true)) {
                        dji_thirdparty_f_b_c.a(nullPointerException22);
                        return;
                    } else {
                        d.getInstance().b().a(nullPointerException22);
                        return;
                    }
                }
            }
        } catch (Throwable nullPointerException222) {
            dji_thirdparty_f_b_c.a(nullPointerException222);
        }
    }
}
