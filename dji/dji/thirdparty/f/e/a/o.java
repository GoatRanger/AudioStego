package dji.thirdparty.f.e.a;

import dji.thirdparty.f.b;
import dji.thirdparty.f.b$a;
import dji.thirdparty.f.b$c;
import dji.thirdparty.f.l;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public final class o implements b$a {
    final Iterable<? extends b> a;

    public o(Iterable<? extends b> iterable) {
        this.a = iterable;
    }

    public void a(b$c dji_thirdparty_f_b_c) {
        final l bVar = new dji.thirdparty.f.m.b();
        final AtomicInteger atomicInteger = new AtomicInteger(1);
        final Queue oVar = new dji.thirdparty.f.e.d.b.o();
        dji_thirdparty_f_b_c.a(bVar);
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
                                        oVar.offer(new NullPointerException("A completable source is null"));
                                        if (atomicInteger.decrementAndGet() != 0) {
                                            return;
                                        }
                                        if (oVar.isEmpty()) {
                                            dji_thirdparty_f_b_c.b();
                                            return;
                                        } else {
                                            dji_thirdparty_f_b_c.a(l.a(oVar));
                                            return;
                                        }
                                    }
                                    atomicInteger.getAndIncrement();
                                    final b$c dji_thirdparty_f_b_c2 = dji_thirdparty_f_b_c;
                                    bVar2.a(new b$c(this) {
                                        final /* synthetic */ o e;

                                        public void a(l lVar) {
                                            bVar.a(lVar);
                                        }

                                        public void a(Throwable th) {
                                            oVar.offer(th);
                                            a();
                                        }

                                        public void b() {
                                            a();
                                        }

                                        void a() {
                                            if (atomicInteger.decrementAndGet() != 0) {
                                                return;
                                            }
                                            if (oVar.isEmpty()) {
                                                dji_thirdparty_f_b_c2.b();
                                            } else {
                                                dji_thirdparty_f_b_c2.a(l.a(oVar));
                                            }
                                        }
                                    });
                                } else {
                                    return;
                                }
                            } catch (Throwable th) {
                                oVar.offer(th);
                                if (atomicInteger.decrementAndGet() != 0) {
                                    return;
                                }
                                if (oVar.isEmpty()) {
                                    dji_thirdparty_f_b_c.b();
                                    return;
                                } else {
                                    dji_thirdparty_f_b_c.a(l.a(oVar));
                                    return;
                                }
                            }
                        }
                        return;
                    } else if (atomicInteger.decrementAndGet() != 0) {
                        return;
                    } else {
                        if (oVar.isEmpty()) {
                            dji_thirdparty_f_b_c.b();
                            return;
                        } else {
                            dji_thirdparty_f_b_c.a(l.a(oVar));
                            return;
                        }
                    }
                } catch (Throwable th2) {
                    oVar.offer(th2);
                    if (atomicInteger.decrementAndGet() != 0) {
                        return;
                    }
                    if (oVar.isEmpty()) {
                        dji_thirdparty_f_b_c.b();
                        return;
                    } else {
                        dji_thirdparty_f_b_c.a(l.a(oVar));
                        return;
                    }
                }
            }
        } catch (Throwable th22) {
            dji_thirdparty_f_b_c.a(th22);
        }
    }
}
