package dji.thirdparty.f;

import dji.thirdparty.f.c.a;
import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d.c;
import dji.thirdparty.f.d.n;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.m.f;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

class b$5 implements b$a {
    final /* synthetic */ n a;
    final /* synthetic */ o b;
    final /* synthetic */ c c;
    final /* synthetic */ boolean d;

    b$5(n nVar, o oVar, c cVar, boolean z) {
        this.a = nVar;
        this.b = oVar;
        this.c = cVar;
        this.d = z;
    }

    public void a(final b$c dji_thirdparty_f_b_c) {
        try {
            final Object call = this.a.call();
            try {
                b bVar = (b) this.b.a(call);
                if (bVar == null) {
                    try {
                        this.c.a(call);
                        dji_thirdparty_f_b_c.a(f.b());
                        dji_thirdparty_f_b_c.a(new NullPointerException("The completable supplied is null"));
                        return;
                    } catch (Throwable th) {
                        b.b(th);
                        dji_thirdparty_f_b_c.a(f.b());
                        dji_thirdparty_f_b_c.a(new a(Arrays.asList(new Throwable[]{new NullPointerException("The completable supplied is null"), th})));
                        return;
                    }
                }
                final AtomicBoolean atomicBoolean = new AtomicBoolean();
                bVar.a(new b$c(this) {
                    l a;
                    final /* synthetic */ b$5 e;

                    void a() {
                        this.a.n_();
                        if (atomicBoolean.compareAndSet(false, true)) {
                            try {
                                this.e.c.a(call);
                            } catch (Throwable th) {
                                b.c.a(th);
                            }
                        }
                    }

                    public void b() {
                        if (this.e.d && atomicBoolean.compareAndSet(false, true)) {
                            try {
                                this.e.c.a(call);
                            } catch (Throwable th) {
                                dji_thirdparty_f_b_c.a(th);
                                return;
                            }
                        }
                        dji_thirdparty_f_b_c.b();
                        if (!this.e.d) {
                            a();
                        }
                    }

                    public void a(Throwable th) {
                        if (this.e.d && atomicBoolean.compareAndSet(false, true)) {
                            try {
                                this.e.c.a(call);
                            } catch (Throwable th2) {
                                Throwable th3 = th2;
                                th = new a(Arrays.asList(new Throwable[]{th, th3}));
                            }
                        }
                        dji_thirdparty_f_b_c.a(th);
                        if (!this.e.d) {
                            a();
                        }
                    }

                    public void a(l lVar) {
                        this.a = lVar;
                        dji_thirdparty_f_b_c.a(f.a(new dji.thirdparty.f.d.b(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void a() {
                                this.a.a();
                            }
                        }));
                    }
                });
            } catch (Throwable th2) {
                b.b(th);
                b.b(th2);
                dji_thirdparty_f_b_c.a(f.b());
                dji_thirdparty_f_b_c.a(new a(Arrays.asList(new Throwable[]{th, th2})));
            }
        } catch (Throwable th3) {
            dji_thirdparty_f_b_c.a(f.b());
            dji_thirdparty_f_b_c.a(th3);
        }
    }
}
