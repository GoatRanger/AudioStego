package dji.thirdparty.f;

import dji.thirdparty.f.d.b;
import dji.thirdparty.f.g.a;
import dji.thirdparty.f.m.c;
import java.util.concurrent.TimeUnit;

class b$4 implements b$a {
    final /* synthetic */ g a;
    final /* synthetic */ long b;
    final /* synthetic */ TimeUnit c;

    b$4(g gVar, long j, TimeUnit timeUnit) {
        this.a = gVar;
        this.b = j;
        this.c = timeUnit;
    }

    public void a(final b$c dji_thirdparty_f_b_c) {
        l cVar = new c();
        dji_thirdparty_f_b_c.a(cVar);
        if (!cVar.b()) {
            final a a = this.a.a();
            cVar.a(a);
            a.a(new b(this) {
                final /* synthetic */ b$4 c;

                public void a() {
                    try {
                        dji_thirdparty_f_b_c.b();
                    } finally {
                        a.n_();
                    }
                }
            }, this.b, this.c);
        }
    }
}
