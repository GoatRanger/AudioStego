package dji.thirdparty.f;

import dji.thirdparty.f.m.b;
import java.util.concurrent.TimeUnit;

class b$8 implements b$a {
    final /* synthetic */ g a;
    final /* synthetic */ long b;
    final /* synthetic */ TimeUnit c;
    final /* synthetic */ boolean d;
    final /* synthetic */ b e;

    b$8(b bVar, g gVar, long j, TimeUnit timeUnit, boolean z) {
        this.e = bVar;
        this.a = gVar;
        this.b = j;
        this.c = timeUnit;
        this.d = z;
    }

    public void a(final b$c dji_thirdparty_f_b_c) {
        final b bVar = new b();
        final l a = this.a.a();
        bVar.a(a);
        this.e.a(new b$c(this) {
            final /* synthetic */ b$8 d;

            public void b() {
                bVar.a(a.a(new dji.thirdparty.f.d.b(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        try {
                            dji_thirdparty_f_b_c.b();
                        } finally {
                            a.n_();
                        }
                    }
                }, this.d.b, this.d.c));
            }

            public void a(final Throwable th) {
                if (this.d.d) {
                    bVar.a(a.a(new dji.thirdparty.f.d.b(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public void a() {
                            try {
                                dji_thirdparty_f_b_c.a(th);
                            } finally {
                                a.n_();
                            }
                        }
                    }, this.d.b, this.d.c));
                } else {
                    dji_thirdparty_f_b_c.a(th);
                }
            }

            public void a(l lVar) {
                bVar.a(lVar);
                dji_thirdparty_f_b_c.a(bVar);
            }
        });
    }
}
