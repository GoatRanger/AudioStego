package dji.thirdparty.f.e.a;

import dji.thirdparty.f.b;
import dji.thirdparty.f.b$a;
import dji.thirdparty.f.b$c;
import dji.thirdparty.f.g;
import dji.thirdparty.f.i.d;
import dji.thirdparty.f.l;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

public final class q implements b$a {
    final b a;
    final long b;
    final TimeUnit c;
    final g d;
    final b e;

    public q(b bVar, long j, TimeUnit timeUnit, g gVar, b bVar2) {
        this.a = bVar;
        this.b = j;
        this.c = timeUnit;
        this.d = gVar;
        this.e = bVar2;
    }

    public void a(final b$c dji_thirdparty_f_b_c) {
        final l bVar = new dji.thirdparty.f.m.b();
        dji_thirdparty_f_b_c.a(bVar);
        final AtomicBoolean atomicBoolean = new AtomicBoolean();
        l a = this.d.a();
        bVar.a(a);
        a.a(new dji.thirdparty.f.d.b(this) {
            final /* synthetic */ q d;

            public void a() {
                if (atomicBoolean.compareAndSet(false, true)) {
                    bVar.c();
                    if (this.d.e == null) {
                        dji_thirdparty_f_b_c.a(new TimeoutException());
                    } else {
                        this.d.e.a(new b$c(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void a(l lVar) {
                                bVar.a(lVar);
                            }

                            public void a(Throwable th) {
                                bVar.n_();
                                dji_thirdparty_f_b_c.a(th);
                            }

                            public void b() {
                                bVar.n_();
                                dji_thirdparty_f_b_c.b();
                            }
                        });
                    }
                }
            }
        }, this.b, this.c);
        this.a.a(new b$c(this) {
            final /* synthetic */ q d;

            public void a(l lVar) {
                bVar.a(lVar);
            }

            public void a(Throwable th) {
                if (atomicBoolean.compareAndSet(false, true)) {
                    bVar.n_();
                    dji_thirdparty_f_b_c.a(th);
                    return;
                }
                d.getInstance().b().a(th);
            }

            public void b() {
                if (atomicBoolean.compareAndSet(false, true)) {
                    bVar.n_();
                    dji_thirdparty_f_b_c.b();
                }
            }
        });
    }
}
