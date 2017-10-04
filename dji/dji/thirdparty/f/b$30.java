package dji.thirdparty.f;

import dji.thirdparty.f.d.n;
import dji.thirdparty.f.m.f;

class b$30 implements b$a {
    final /* synthetic */ n a;

    b$30(n nVar) {
        this.a = nVar;
    }

    public void a(b$c dji_thirdparty_f_b_c) {
        Throwable th;
        dji_thirdparty_f_b_c.a(f.b());
        try {
            th = (Throwable) this.a.call();
        } catch (Throwable th2) {
            th = th2;
        }
        if (th == null) {
            th = new NullPointerException("The error supplied is null");
        }
        dji_thirdparty_f_b_c.a(th);
    }
}
