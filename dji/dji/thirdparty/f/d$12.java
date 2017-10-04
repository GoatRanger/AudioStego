package dji.thirdparty.f;

import dji.thirdparty.f.c.b;

class d$12 implements d$f<R> {
    final /* synthetic */ d$g a;
    final /* synthetic */ d b;

    d$12(d dVar, d$g dji_thirdparty_f_d_g) {
        this.b = dVar;
        this.a = dji_thirdparty_f_d_g;
    }

    public void a(k<? super R> kVar) {
        k kVar2;
        try {
            kVar2 = (k) d.b.a(this.a).a(kVar);
            kVar2.c();
            this.b.a.a(kVar2);
        } catch (Throwable th) {
            b.b(th);
            kVar.a(th);
        }
    }
}
