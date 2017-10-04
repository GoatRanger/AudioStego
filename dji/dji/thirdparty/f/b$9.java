package dji.thirdparty.f;

import dji.thirdparty.f.c.a;
import dji.thirdparty.f.d.b;
import dji.thirdparty.f.d.c;
import dji.thirdparty.f.m.f;
import java.util.Arrays;

class b$9 implements b$a {
    final /* synthetic */ b a;
    final /* synthetic */ b b;
    final /* synthetic */ c c;
    final /* synthetic */ c d;
    final /* synthetic */ b e;
    final /* synthetic */ b f;

    b$9(b bVar, b bVar2, b bVar3, c cVar, c cVar2, b bVar4) {
        this.f = bVar;
        this.a = bVar2;
        this.b = bVar3;
        this.c = cVar;
        this.d = cVar2;
        this.e = bVar4;
    }

    public void a(final b$c dji_thirdparty_f_b_c) {
        this.f.a(new b$c(this) {
            final /* synthetic */ b$9 b;

            public void b() {
                try {
                    this.b.a.a();
                    dji_thirdparty_f_b_c.b();
                    try {
                        this.b.b.a();
                    } catch (Throwable th) {
                        b.c.a(th);
                    }
                } catch (Throwable th2) {
                    dji_thirdparty_f_b_c.a(th2);
                }
            }

            public void a(Throwable th) {
                try {
                    this.b.c.a(th);
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    th = new a(Arrays.asList(new Throwable[]{th, th3}));
                }
                dji_thirdparty_f_b_c.a(th);
            }

            public void a(final l lVar) {
                try {
                    this.b.d.a(lVar);
                    dji_thirdparty_f_b_c.a(f.a(new b(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public void a() {
                            try {
                                this.b.b.e.a();
                            } catch (Throwable th) {
                                b.c.a(th);
                            }
                            lVar.n_();
                        }
                    }));
                } catch (Throwable th) {
                    lVar.n_();
                    dji_thirdparty_f_b_c.a(f.b());
                    dji_thirdparty_f_b_c.a(th);
                }
            }
        });
    }
}
