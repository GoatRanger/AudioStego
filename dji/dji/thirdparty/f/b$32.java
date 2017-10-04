package dji.thirdparty.f;

import dji.thirdparty.f.d.b;
import dji.thirdparty.f.m.a;

class b$32 implements b$a {
    final /* synthetic */ b a;

    b$32(b bVar) {
        this.a = bVar;
    }

    public void a(b$c dji_thirdparty_f_b_c) {
        l aVar = new a();
        dji_thirdparty_f_b_c.a(aVar);
        try {
            this.a.a();
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
