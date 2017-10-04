package dji.thirdparty.f;

import dji.thirdparty.f.c.a;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.m.e;
import java.util.Arrays;

class b$17 implements b$a {
    final /* synthetic */ o a;
    final /* synthetic */ b b;

    b$17(b bVar, o oVar) {
        this.b = bVar;
        this.a = oVar;
    }

    public void a(final b$c dji_thirdparty_f_b_c) {
        final e eVar = new e();
        this.b.a(new b$c(this) {
            final /* synthetic */ b$17 c;

            public void b() {
                dji_thirdparty_f_b_c.b();
            }

            public void a(Throwable th) {
                try {
                    b bVar = (b) this.c.a.a(th);
                    if (bVar == null) {
                        NullPointerException nullPointerException = new NullPointerException("The completable returned is null");
                        dji_thirdparty_f_b_c.a(new a(Arrays.asList(new Throwable[]{th, nullPointerException})));
                        return;
                    }
                    bVar.a(new b$c(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void b() {
                            dji_thirdparty_f_b_c.b();
                        }

                        public void a(Throwable th) {
                            dji_thirdparty_f_b_c.a(th);
                        }

                        public void a(l lVar) {
                            eVar.a(lVar);
                        }
                    });
                } catch (Throwable th2) {
                    dji_thirdparty_f_b_c.a(new a(Arrays.asList(new Throwable[]{th, th2})));
                }
            }

            public void a(l lVar) {
                eVar.a(lVar);
            }
        });
    }
}
