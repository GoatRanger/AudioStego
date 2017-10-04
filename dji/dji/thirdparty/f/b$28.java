package dji.thirdparty.f;

import dji.thirdparty.f.m.b;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

class b$28 implements b$a {
    final /* synthetic */ Iterable a;

    b$28(Iterable iterable) {
        this.a = iterable;
    }

    public void a(final b$c dji_thirdparty_f_b_c) {
        Throwable nullPointerException;
        final l bVar = new b();
        dji_thirdparty_f_b_c.a(bVar);
        final AtomicBoolean atomicBoolean = new AtomicBoolean();
        b$c anonymousClass1 = new b$c(this) {
            final /* synthetic */ b$28 d;

            public void b() {
                if (atomicBoolean.compareAndSet(false, true)) {
                    bVar.n_();
                    dji_thirdparty_f_b_c.b();
                }
            }

            public void a(Throwable th) {
                if (atomicBoolean.compareAndSet(false, true)) {
                    bVar.n_();
                    dji_thirdparty_f_b_c.a(th);
                    return;
                }
                b.c.a(th);
            }

            public void a(l lVar) {
                bVar.a(lVar);
            }
        };
        try {
            Iterator it = this.a.iterator();
            if (it == null) {
                dji_thirdparty_f_b_c.a(new NullPointerException("The iterator returned is null"));
                return;
            }
            boolean z = true;
            while (!atomicBoolean.get() && !bVar.b()) {
                try {
                    if (it.hasNext()) {
                        if (!atomicBoolean.get() && !bVar.b()) {
                            try {
                                b bVar2 = (b) it.next();
                                if (bVar2 == null) {
                                    nullPointerException = new NullPointerException("One of the sources is null");
                                    if (atomicBoolean.compareAndSet(false, true)) {
                                        bVar.n_();
                                        dji_thirdparty_f_b_c.a(nullPointerException);
                                        return;
                                    }
                                    b.c.a(nullPointerException);
                                    return;
                                } else if (!atomicBoolean.get() && !bVar.b()) {
                                    bVar2.a(anonymousClass1);
                                    z = false;
                                } else {
                                    return;
                                }
                            } catch (Throwable nullPointerException2) {
                                if (atomicBoolean.compareAndSet(false, true)) {
                                    bVar.n_();
                                    dji_thirdparty_f_b_c.a(nullPointerException2);
                                    return;
                                }
                                b.c.a(nullPointerException2);
                                return;
                            }
                        }
                        return;
                    } else if (z) {
                        dji_thirdparty_f_b_c.b();
                        return;
                    } else {
                        return;
                    }
                } catch (Throwable nullPointerException22) {
                    if (atomicBoolean.compareAndSet(false, true)) {
                        bVar.n_();
                        dji_thirdparty_f_b_c.a(nullPointerException22);
                        return;
                    }
                    b.c.a(nullPointerException22);
                    return;
                }
            }
        } catch (Throwable nullPointerException222) {
            dji_thirdparty_f_b_c.a(nullPointerException222);
        }
    }
}
