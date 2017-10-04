package dji.thirdparty.f;

import dji.thirdparty.f.c.a;
import dji.thirdparty.f.d.o;
import java.util.Arrays;

class b$16 implements b$a {
    final /* synthetic */ o a;
    final /* synthetic */ b b;

    b$16(b bVar, o oVar) {
        this.b = bVar;
        this.a = oVar;
    }

    public void a(final b$c dji_thirdparty_f_b_c) {
        this.b.a(new b$c(this) {
            final /* synthetic */ b$16 b;

            public void b() {
                dji_thirdparty_f_b_c.b();
            }

            public void a(Throwable th) {
                try {
                    if (((Boolean) this.b.a.a(th)).booleanValue()) {
                        dji_thirdparty_f_b_c.b();
                    } else {
                        dji_thirdparty_f_b_c.a(th);
                    }
                } catch (Throwable th2) {
                    a aVar = new a(Arrays.asList(new Throwable[]{th, th2}));
                }
            }

            public void a(l lVar) {
                dji_thirdparty_f_b_c.a(lVar);
            }
        });
    }
}
