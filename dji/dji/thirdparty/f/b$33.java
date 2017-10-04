package dji.thirdparty.f;

import dji.thirdparty.f.m.a;
import java.util.concurrent.Callable;

class b$33 implements b$a {
    final /* synthetic */ Callable a;

    b$33(Callable callable) {
        this.a = callable;
    }

    public void a(b$c dji_thirdparty_f_b_c) {
        l aVar = new a();
        dji_thirdparty_f_b_c.a(aVar);
        try {
            this.a.call();
            if (!aVar.b()) {
                dji_thirdparty_f_b_c.b();
            }
        } catch (Throwable th) {
            if (!aVar.b()) {
                dji_thirdparty_f_b_c.a(th);
            }
        }
    }
}
