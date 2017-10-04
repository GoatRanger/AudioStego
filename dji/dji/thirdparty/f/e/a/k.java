package dji.thirdparty.f.e.a;

import dji.thirdparty.f.b;
import dji.thirdparty.f.b$a;
import dji.thirdparty.f.b$c;
import dji.thirdparty.f.l;
import dji.thirdparty.f.m.e;
import dji.thirdparty.f.m.f;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public final class k implements b$a {
    final Iterable<? extends b> a;

    static final class a extends AtomicInteger implements b$c {
        private static final long e = -7965400327305809232L;
        final b$c a;
        final Iterator<? extends b> b;
        int c;
        final e d = new e();

        public a(b$c dji_thirdparty_f_b_c, Iterator<? extends b> it) {
            this.a = dji_thirdparty_f_b_c;
            this.b = it;
        }

        public void a(l lVar) {
            this.d.a(lVar);
        }

        public void a(Throwable th) {
            this.a.a(th);
        }

        public void b() {
            a();
        }

        void a() {
            if (!this.d.b() && getAndIncrement() == 0) {
                Iterator it = this.b;
                while (!this.d.b()) {
                    try {
                        if (it.hasNext()) {
                            try {
                                b bVar = (b) it.next();
                                if (bVar == null) {
                                    this.a.a(new NullPointerException("The completable returned is null"));
                                    return;
                                }
                                bVar.a(this);
                                if (decrementAndGet() == 0) {
                                    return;
                                }
                            } catch (Throwable th) {
                                this.a.a(th);
                                return;
                            }
                        }
                        this.a.b();
                        return;
                    } catch (Throwable th2) {
                        this.a.a(th2);
                        return;
                    }
                }
            }
        }
    }

    public k(Iterable<? extends b> iterable) {
        this.a = iterable;
    }

    public void a(b$c dji_thirdparty_f_b_c) {
        try {
            Iterator it = this.a.iterator();
            if (it == null) {
                dji_thirdparty_f_b_c.a(f.b());
                dji_thirdparty_f_b_c.a(new NullPointerException("The iterator returned is null"));
                return;
            }
            a aVar = new a(dji_thirdparty_f_b_c, it);
            dji_thirdparty_f_b_c.a(aVar.d);
            aVar.a();
        } catch (Throwable th) {
            dji_thirdparty_f_b_c.a(f.b());
            dji_thirdparty_f_b_c.a(th);
        }
    }
}
