package dji.thirdparty.f;

import dji.thirdparty.f.d.b;
import dji.thirdparty.f.g.a;
import dji.thirdparty.f.m.f;

class b$27 implements b$a {
    final /* synthetic */ g a;
    final /* synthetic */ b b;

    b$27(b bVar, g gVar) {
        this.b = bVar;
        this.a = gVar;
    }

    public void a(final b$c dji_thirdparty_f_b_c) {
        this.b.a(new b$c(this) {
            final /* synthetic */ b$27 b;

            public void b() {
                dji_thirdparty_f_b_c.b();
            }

            public void a(Throwable th) {
                dji_thirdparty_f_b_c.a(th);
            }

            public void a(final l lVar) {
                dji_thirdparty_f_b_c.a(f.a(new b(this) {
                    final /* synthetic */ AnonymousClass1 b;

                    public void a() {
                        final a a = this.b.b.a.a();
                        a.a(new b(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            public void a() {
                                try {
                                    lVar.n_();
                                } finally {
                                    a.n_();
                                }
                            }
                        });
                    }
                }));
            }
        });
    }
}
