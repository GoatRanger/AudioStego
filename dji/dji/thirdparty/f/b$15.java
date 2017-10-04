package dji.thirdparty.f;

import dji.thirdparty.f.d.b;
import dji.thirdparty.f.e.d.n;

class b$15 implements b$a {
    final /* synthetic */ g a;
    final /* synthetic */ b b;

    b$15(b bVar, g gVar) {
        this.b = bVar;
        this.a = gVar;
    }

    public void a(final b$c dji_thirdparty_f_b_c) {
        final l nVar = new n();
        final l a = this.a.a();
        nVar.a(a);
        dji_thirdparty_f_b_c.a(nVar);
        this.b.a(new b$c(this) {
            final /* synthetic */ b$15 d;

            public void b() {
                a.a(new b(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        try {
                            dji_thirdparty_f_b_c.b();
                        } finally {
                            nVar.n_();
                        }
                    }
                });
            }

            public void a(final Throwable th) {
                a.a(new b(this) {
                    final /* synthetic */ AnonymousClass1 b;

                    public void a() {
                        try {
                            dji_thirdparty_f_b_c.a(th);
                        } finally {
                            nVar.n_();
                        }
                    }
                });
            }

            public void a(l lVar) {
                nVar.a(lVar);
            }
        });
    }
}
