package dji.thirdparty.f;

import dji.thirdparty.f.d.n;
import dji.thirdparty.f.m.f;

class b$29 implements b$a {
    final /* synthetic */ n a;

    b$29(n nVar) {
        this.a = nVar;
    }

    public void a(b$c dji_thirdparty_f_b_c) {
        try {
            b bVar = (b) this.a.call();
            if (bVar == null) {
                dji_thirdparty_f_b_c.a(f.b());
                dji_thirdparty_f_b_c.a(new NullPointerException("The completable returned is null"));
                return;
            }
            bVar.a(dji_thirdparty_f_b_c);
        } catch (Throwable th) {
            dji_thirdparty_f_b_c.a(f.b());
            dji_thirdparty_f_b_c.a(th);
        }
    }
}
